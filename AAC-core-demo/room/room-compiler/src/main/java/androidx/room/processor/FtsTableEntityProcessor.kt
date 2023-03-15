/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.room.processor

import androidx.room.Fts3
import androidx.room.Fts4
import androidx.room.FtsOptions.MatchInfo
import androidx.room.FtsOptions.Order
import androidx.room.compiler.processing.XAnnotationBox
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XTypeElement
import androidx.room.parser.FtsVersion
import androidx.room.parser.SQLTypeAffinity
import androidx.room.processor.EntityProcessor.Companion.extractForeignKeys
import androidx.room.processor.EntityProcessor.Companion.extractIndices
import androidx.room.processor.EntityProcessor.Companion.extractTableName
import androidx.room.processor.cache.Cache
import androidx.room.vo.Entity
import androidx.room.vo.Field
import androidx.room.vo.Fields
import androidx.room.vo.FtsEntity
import androidx.room.vo.FtsOptions
import androidx.room.vo.LanguageId
import androidx.room.vo.PrimaryKey
import androidx.room.vo.columnNames

class FtsTableEntityProcessor internal constructor(
    baseContext: Context,
    val element: XTypeElement,
    private val referenceStack: LinkedHashSet<String> = LinkedHashSet()
) : EntityProcessor {

    val context = baseContext.fork(element)

    override fun process(): FtsEntity {
        return context.cache.entities.get(Cache.EntityKey(element)) {
            doProcess()
        } as FtsEntity
    }

    private fun doProcess(): FtsEntity {

        //该类必须使用@Entity注解修饰
        context.checker.hasAnnotation(
            element, androidx.room.Entity::class,
            ProcessorErrors.ENTITY_MUST_BE_ANNOTATED_WITH_ENTITY
        )
        val entityAnnotation = element.getAnnotation(androidx.room.Entity::class)
        val tableName: String

        //fts表不允许创建索引，不允许使用外键
        if (entityAnnotation != null) {
            tableName = extractTableName(element, entityAnnotation.value)

            context.checker.check(
                extractIndices(entityAnnotation, tableName).isEmpty(),
                element, ProcessorErrors.INDICES_IN_FTS_ENTITY
            )
            context.checker.check(
                extractForeignKeys(entityAnnotation).isEmpty(),
                element, ProcessorErrors.FOREIGN_KEYS_IN_FTS_ENTITY
            )
        } else {
            tableName = element.name
        }

        val pojo = PojoProcessor.createFor(
            context = context,
            element = element,
            bindingScope = FieldProcessor.BindingScope.TWO_WAY,
            parent = null,
            referenceStack = referenceStack
        ).process()

        //不允许使用`@Relation`关系字段
        context.checker.check(pojo.relations.isEmpty(), element, ProcessorErrors.RELATION_IN_ENTITY)


        val (ftsVersion, ftsOptions) = if (element.hasAnnotation(androidx.room.Fts3::class)) {
            FtsVersion.FTS3 to getFts3Options(element.getAnnotation(Fts3::class)!!)
        } else {
            FtsVersion.FTS4 to getFts4Options(element.getAnnotation(Fts4::class)!!)
        }

        // fts类映射表名:（1）如果是fts4类并且存在`@Fts4##contentEntity`，那么就是用当前该属性值中的表名；（2）如果fts4不存在该属性值或者当前是fts3类，则使用: 表名 + "_content";
        val shadowTableName = if (ftsOptions.contentEntity != null) {
            // In 'external content' mode the FTS table content is in another table.
            // See: https://www.sqlite.org/fts3.html#_external_content_fts4_tables_
            ftsOptions.contentEntity.tableName
        } else {
            // The %_content table contains the unadulterated data inserted by the user into the FTS
            // virtual table. See: https://www.sqlite.org/fts3.html#shadow_tables
            "${tableName}_content"
        }

        val primaryKey = findAndValidatePrimaryKey(entityAnnotation, pojo.fields)

        findAndValidateLanguageId(pojo.fields, ftsOptions.languageIdColumnName)

        //fts表目的是检索，@Fts4#notIndexed属性表示不被索引字段，该字段必须存在于fts表中
        val missingNotIndexed = ftsOptions.notIndexedColumns - pojo.columnNames
        context.checker.check(
            missingNotIndexed.isEmpty(), element,
            ProcessorErrors.missingNotIndexedField(missingNotIndexed)
        )

        //@Fts4#prefix属性值如果存在，必须大于0
        context.checker.check(
            ftsOptions.prefixSizes.all { it > 0 },
            element, ProcessorErrors.INVALID_FTS_ENTITY_PREFIX_SIZES
        )

        val entity = FtsEntity(
            element = element,
            tableName = tableName,
            type = pojo.type,
            fields = pojo.fields,
            embeddedFields = pojo.embeddedFields,
            primaryKey = primaryKey,
            constructor = pojo.constructor,
            ftsVersion = ftsVersion,
            ftsOptions = ftsOptions,
            shadowTableName = shadowTableName
        )

        validateExternalContentEntity(entity)

        return entity
    }

    private fun getFts3Options(annotation: XAnnotationBox<Fts3>) =
        FtsOptions(
            tokenizer = annotation.value.tokenizer,
            tokenizerArgs = annotation.value.tokenizerArgs.asList(),
            contentEntity = null,
            languageIdColumnName = "",
            matchInfo = MatchInfo.FTS4,
            notIndexedColumns = emptyList(),
            prefixSizes = emptyList(),
            preferredOrder = Order.ASC
        )

    private fun getFts4Options(annotation: XAnnotationBox<Fts4>): FtsOptions {
        val contentEntity: Entity? = getContentEntity(annotation.getAsType("contentEntity"))
        return FtsOptions(
            tokenizer = annotation.value.tokenizer,
            tokenizerArgs = annotation.value.tokenizerArgs.asList(),
            contentEntity = contentEntity,
            languageIdColumnName = annotation.value.languageId,
            matchInfo = annotation.value.matchInfo,
            notIndexedColumns = annotation.value.notIndexed.asList(),
            prefixSizes = annotation.value.prefix.asList(),
            preferredOrder = annotation.value.order
        )
    }

    //@Fts4#contentEntity必须存在，并且是使用@Entity注解修饰的类
    private fun getContentEntity(entityType: XType?): Entity? {
        if (entityType == null) {
            context.logger.e(element, ProcessorErrors.FTS_EXTERNAL_CONTENT_CANNOT_FIND_ENTITY)
            return null
        }

        val defaultType = context.processingEnv.requireType(Object::class)
        if (entityType.isSameType(defaultType)) {
            return null
        }
        val contentEntityElement = entityType.typeElement
        if (contentEntityElement == null) {
            context.logger.e(element, ProcessorErrors.FTS_EXTERNAL_CONTENT_CANNOT_FIND_ENTITY)
            return null
        }
        if (!contentEntityElement.hasAnnotation(androidx.room.Entity::class)) {
            context.logger.e(
                contentEntityElement,
                ProcessorErrors.externalContentNotAnEntity(
                    contentEntityElement.className.canonicalName()
                )
            )
            return null
        }
        return EntityProcessor(context, contentEntityElement, referenceStack).process()
    }

    private fun findAndValidatePrimaryKey(
        entityAnnotation: XAnnotationBox<androidx.room.Entity>?,
        fields: List<Field>
    ): PrimaryKey {
        val keysFromEntityAnnotation =
            entityAnnotation?.value?.primaryKeys?.mapNotNull { pkColumnName ->
                val field = fields.firstOrNull { it.columnName == pkColumnName }
                context.checker.check(
                    field != null, element,
                    ProcessorErrors.primaryKeyColumnDoesNotExist(
                        pkColumnName,
                        fields.map { it.columnName }
                    )
                )
                field?.let { pkField ->
                    PrimaryKey(
                        declaredIn = pkField.element.enclosingElement,
                        fields = Fields(pkField),
                        autoGenerateId = true
                    )
                }
            } ?: emptyList()

        val keysFromPrimaryKeyAnnotations = fields.mapNotNull { field ->
            if (field.element.hasAnnotation(androidx.room.PrimaryKey::class)) {
                PrimaryKey(
                    declaredIn = field.element.enclosingElement,
                    fields = Fields(field),
                    autoGenerateId = true
                )
            } else {
                null
            }
        }
        //如果当前fts类没有设置主键，那么不允许存在rowid字段，因为rowid必须是主键字段
        val primaryKeys = keysFromEntityAnnotation + keysFromPrimaryKeyAnnotations
        if (primaryKeys.isEmpty()) {
            fields.firstOrNull { it.columnName == "rowid" }?.let {
                context.checker.check(
                    it.element.hasAnnotation(androidx.room.PrimaryKey::class),
                    it.element, ProcessorErrors.MISSING_PRIMARY_KEYS_ANNOTATION_IN_ROW_ID
                )
            }
            return PrimaryKey.MISSING
        }
        //最多只允许存在一个主键，但是可以是多个字段组成
        context.checker.check(
            primaryKeys.size == 1, element,
            ProcessorErrors.TOO_MANY_PRIMARY_KEYS_IN_FTS_ENTITY
        )
        //如果存在主键，那么主键中的第一个字段必须是rowid，类型是SQLTypeAffinity.INTEGER
        val primaryKey = primaryKeys.first()
        context.checker.check(
            primaryKey.columnNames.first() == "rowid",
            primaryKey.declaredIn ?: element,
            ProcessorErrors.INVALID_FTS_ENTITY_PRIMARY_KEY_NAME
        )
        context.checker.check(
            primaryKey.fields.first().affinity == SQLTypeAffinity.INTEGER,
            primaryKey.declaredIn ?: element,
            ProcessorErrors.INVALID_FTS_ENTITY_PRIMARY_KEY_AFFINITY
        )
        return primaryKey
    }

    private fun validateExternalContentEntity(ftsEntity: FtsEntity) {
        val contentEntity = ftsEntity.ftsOptions.contentEntity
        if (contentEntity == null) {
            return
        }

        // Verify external content columns are a superset of those defined in the FtsEntity
        //` @Fts4#contentEntity`属性表示fts表的映射表，如果存在：fts表除了rowid字段和`@Fts4#languageId`属性字段，其他字段必须都存在于映射表中；
        ftsEntity.nonHiddenFields.filterNot {
            contentEntity.fields.any { contentField -> contentField.columnName == it.columnName }
        }.forEach {
            context.logger.e(
                it.element,
                ProcessorErrors.missingFtsContentField(
                    element.qualifiedName, it.columnName,
                    contentEntity.element.qualifiedName
                )
            )
        }
    }

    private fun findAndValidateLanguageId(
        fields: List<Field>,
        languageIdColumnName: String
    ): LanguageId {
        if (languageIdColumnName.isEmpty()) {
            return LanguageId.MISSING
        }

        // `@Fts4#languageId`如果设置，属性值必须是当前`@Entity`类中的字段，
        // 并且字段类型必须`SQLTypeAffinity.INTEGER`类型 ↔`int/Integer、shor/Short、byte/Byte、long/Long、char/Char`  `SQLTypeAffinity.INTEGER`；
        val languageIdField = fields.firstOrNull { it.columnName == languageIdColumnName }
        if (languageIdField == null) {
            context.logger.e(element, ProcessorErrors.missingLanguageIdField(languageIdColumnName))
            return LanguageId.MISSING
        }

        context.checker.check(
            languageIdField.affinity == SQLTypeAffinity.INTEGER,
            languageIdField.element, ProcessorErrors.INVALID_FTS_ENTITY_LANGUAGE_ID_AFFINITY
        )
        return LanguageId(languageIdField.element, languageIdField)
    }
}