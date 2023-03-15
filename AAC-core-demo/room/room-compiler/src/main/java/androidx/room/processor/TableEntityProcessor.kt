/*
 * Copyright (C) 2016 The Android Open Source Project
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

import androidx.room.parser.SQLTypeAffinity
import androidx.room.parser.SqlParser
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XTypeElement
import androidx.room.ext.isNotNone
import androidx.room.processor.EntityProcessor.Companion.createIndexName
import androidx.room.processor.EntityProcessor.Companion.extractForeignKeys
import androidx.room.processor.EntityProcessor.Companion.extractIndices
import androidx.room.processor.EntityProcessor.Companion.extractTableName
import androidx.room.processor.ProcessorErrors.INDEX_COLUMNS_CANNOT_BE_EMPTY
import androidx.room.processor.ProcessorErrors.INVALID_INDEX_ORDERS_SIZE
import androidx.room.processor.ProcessorErrors.RELATION_IN_ENTITY
import androidx.room.processor.cache.Cache
import androidx.room.vo.EmbeddedField
import androidx.room.vo.Entity
import androidx.room.vo.Field
import androidx.room.vo.Fields
import androidx.room.vo.ForeignKey
import androidx.room.vo.Index
import androidx.room.vo.Pojo
import androidx.room.vo.PrimaryKey
import androidx.room.vo.Warning
import androidx.room.vo.columnNames
import androidx.room.vo.findFieldByColumnName

class TableEntityProcessor internal constructor(
    baseContext: Context,
    val element: XTypeElement,
    private val referenceStack: LinkedHashSet<String> = LinkedHashSet()
) : EntityProcessor {
    val context = baseContext.fork(element)

    override fun process(): Entity {
        return context.cache.entities.get(Cache.EntityKey(element)) {
            doProcess()
        }
    }

    private fun doProcess(): Entity {
        // @Database entities属性中的类必须使用@Entity注解修饰
        context.checker.hasAnnotation(
            element, androidx.room.Entity::class,
            ProcessorErrors.ENTITY_MUST_BE_ANNOTATED_WITH_ENTITY
        )
        val annotationBox = element.getAnnotation(androidx.room.Entity::class)
        val tableName: String
        val entityIndices: List<IndexInput>
        val foreignKeyInputs: List<ForeignKeyInput>
        val inheritSuperIndices: Boolean
        if (annotationBox != null) {
            tableName = extractTableName(element, annotationBox.value)
            entityIndices = extractIndices(
                annotationBox, //@Entity注解
                tableName//表名
            )

            inheritSuperIndices = annotationBox.value.inheritSuperIndices

            foreignKeyInputs = extractForeignKeys(annotationBox)
        } else {
            tableName = element.name
            foreignKeyInputs = emptyList()
            entityIndices = emptyList()
            inheritSuperIndices = false
        }
        //@Entity修饰的类表示表名，表名要么通过@Entity的tableName属性获取，如果不存在tableName属性，那么将当前修饰的类名作为表名
        context.checker.notBlank(
            tableName, element,
            ProcessorErrors.ENTITY_TABLE_NAME_CANNOT_BE_EMPTY
        )
        //表或视图名，不允许使用“sqlite_”前缀
        context.checker.check(
            !tableName.startsWith("sqlite_", true), element,
            ProcessorErrors.ENTITY_TABLE_NAME_CANNOT_START_WITH_SQLITE
        )

        //@Entity修饰的节点生成Pojo对象
        val pojo = PojoProcessor.createFor(
            context = context,
            element = element,//@Entity修饰的节点
            bindingScope = FieldProcessor.BindingScope.TWO_WAY,
            parent = null,
            referenceStack = referenceStack
        ).process()

        //`@Entity`修饰的类不允许存在`@Relation`修饰的变量
        context.checker.check(pojo.relations.isEmpty(), element, RELATION_IN_ENTITY)

        val fieldIndices = pojo.fields
            .filter { it.indexed }.mapNotNull {
                if (it.parent != null) {
                    //`@Embedded`内嵌对象不要使用索引，否则警告
                    it.indexed = false
                    context.logger.w(
                        Warning.INDEX_FROM_EMBEDDED_FIELD_IS_DROPPED, it.element,
                        ProcessorErrors.droppedEmbeddedFieldIndex(
                            it.getPath(), element.qualifiedName
                        )
                    )
                    null
                } else if (it.element.enclosingElement != element && !inheritSuperIndices) {
                    //当前索引字段如果是entity节点继承过来的，那么必须设置当前entity的@Entity#inheritSuperIndices = true
                    it.indexed = false
                    context.logger.w(
                        Warning.INDEX_FROM_PARENT_FIELD_IS_DROPPED,
                        ProcessorErrors.droppedSuperClassFieldIndex(
                            it.columnName, element.qualifiedName,
                            it.element.enclosingElement.className.toString()
                        )
                    )
                    null
                } else {
                    IndexInput(
                        name = createIndexName(listOf(it.columnName), tableName),
                        unique = false,
                        columnNames = listOf(it.columnName),
                        orders = emptyList()
                    )
                }
            }

        val superIndices = loadSuperIndices(element.superClass, tableName, inheritSuperIndices)

        //索引来源：（1）@ColumnInfo#index = true；（2）@Entity#indices；（3）@Entity#inheritSuperIndices = true，并且@Entity修饰的父类也是用了@Entity修饰，其#indices属性集合
        val indexInputs = entityIndices + fieldIndices + superIndices

        //校验索引：索引必须是针对表常规字段
        val indices = validateAndCreateIndices(indexInputs, pojo)

        //校验和获取主键
        val primaryKey = findAndValidatePrimaryKey(pojo.fields, pojo.embeddedFields)

        // 主键默认是`String` ↔ `SQLTypeAffinity.TEXT`类型，
        // 如果`@PrimaryKey#autoGenerate = true`，必须是`int/Integer、shor/Short、byte/Byte、long/Long、char/Char` ↔ `SQLTypeAffinity.INTEGER`类型；
        val affinity = primaryKey.fields.firstOrNull()?.affinity ?: SQLTypeAffinity.TEXT
        context.checker.check(
            !primaryKey.autoGenerateId || affinity == SQLTypeAffinity.INTEGER,
            primaryKey.fields.firstOrNull()?.element ?: element,
            ProcessorErrors.AUTO_INCREMENTED_PRIMARY_KEY_IS_NOT_INT
        )

        val entityForeignKeys = validateAndCreateForeignKeyReferences(foreignKeyInputs, pojo)

        checkIndicesForForeignKeys(entityForeignKeys, primaryKey, indices)

        //表名不允许使用 ` 和 \" 特殊字符；
        context.checker.check(
            SqlParser.isValidIdentifier(tableName), element,
            ProcessorErrors.INVALID_TABLE_NAME
        )

        //表字段名不允许使用 ` 和 \" 特殊字符
        pojo.fields.forEach {
            context.checker.check(
                SqlParser.isValidIdentifier(it.columnName), it.element,
                ProcessorErrors.INVALID_COLUMN_NAME
            )
        }

        val entity = Entity(
            element = element,
            tableName = tableName,
            type = pojo.type,
            fields = pojo.fields,
            embeddedFields = pojo.embeddedFields,
            indices = indices,
            primaryKey = primaryKey,
            foreignKeys = entityForeignKeys,
            constructor = pojo.constructor,
            shadowTableName = null
        )

        return entity
    }

    private fun checkIndicesForForeignKeys(
        entityForeignKeys: List<ForeignKey>,
        primaryKey: PrimaryKey,
        indices: List<Index>
    ) {
        fun covers(columnNames: List<String>, fields: List<Field>): Boolean =
            fields.size >= columnNames.size && columnNames.withIndex().all {
                fields[it.index].columnName == it.value
            }

        //表外键字段指向另外表的字段必须是主键或索引键，否则表示无效并且会报警告；
        entityForeignKeys.forEach { fKey ->
            val columnNames = fKey.childFields.map { it.columnName }
            val exists = covers(columnNames, primaryKey.fields) || indices.any { index ->
                covers(columnNames, index.fields)
            }
            if (!exists) {
                if (columnNames.size == 1) {
                    context.logger.w(
                        Warning.MISSING_INDEX_ON_FOREIGN_KEY_CHILD, element,
                        ProcessorErrors.foreignKeyMissingIndexInChildColumn(columnNames[0])
                    )
                } else {
                    context.logger.w(
                        Warning.MISSING_INDEX_ON_FOREIGN_KEY_CHILD, element,
                        ProcessorErrors.foreignKeyMissingIndexInChildColumns(columnNames)
                    )
                }
            }
        }
    }

    /**
     * Does a validation on foreign keys except the parent table's columns.
     */
    private fun validateAndCreateForeignKeyReferences(
        foreignKeyInputs: List<ForeignKeyInput>,
        pojo: Pojo
    ): List<ForeignKey> {
        return foreignKeyInputs.map {
            if (it.onUpdate == null) {
                context.logger.e(element, ProcessorErrors.INVALID_FOREIGN_KEY_ACTION)
                return@map null
            }
            if (it.onDelete == null) {
                context.logger.e(element, ProcessorErrors.INVALID_FOREIGN_KEY_ACTION)
                return@map null
            }

            //`@ForeignKey#childColumns`必须存在，当前`Library`受关联的外键字段；
            if (it.childColumns.isEmpty()) {
                context.logger.e(element, ProcessorErrors.FOREIGN_KEY_EMPTY_CHILD_COLUMN_LIST)
                return@map null
            }
            //`@ForeignKey#parentColumns`必须存在，当前`Library`关联的`User`表主键；
            if (it.parentColumns.isEmpty()) {
                context.logger.e(element, ProcessorErrors.FOREIGN_KEY_EMPTY_PARENT_COLUMN_LIST)
                return@map null
            }
            if (it.childColumns.size != it.parentColumns.size) {
                context.logger.e(
                    element,
                    ProcessorErrors.foreignKeyColumnNumberMismatch(
                        it.childColumns, it.parentColumns
                    )
                )
                return@map null
            }
            //`@ForeignKey#entity`必须存在，`@ForeignKey#childColumns`外键字段指向当前表的主键；
            val parentElement = it.parent.typeElement
            if (parentElement == null) {
                context.logger.e(element, ProcessorErrors.FOREIGN_KEY_CANNOT_FIND_PARENT)
                return@map null
            }
            //`@ForeignKey#entity`必须`@Entity`修饰；
            val parentAnnotation = parentElement.getAnnotation(androidx.room.Entity::class)
            if (parentAnnotation == null) {
                context.logger.e(
                    element,
                    ProcessorErrors.foreignKeyNotAnEntity(parentElement.qualifiedName)
                )
                return@map null
            }
            val tableName = extractTableName(parentElement, parentAnnotation.value)
            val fields = it.childColumns.mapNotNull { columnName ->
                val field = pojo.findFieldByColumnName(columnName)
                if (field == null) {
                    context.logger.e(
                        pojo.element,
                        ProcessorErrors.foreignKeyChildColumnDoesNotExist(
                            columnName,
                            pojo.columnNames
                        )
                    )
                }
                field
            }
            if (fields.size != it.childColumns.size) {
                return@map null
            }
            ForeignKey(
                parentTable = tableName,
                childFields = fields,
                parentColumns = it.parentColumns,
                onDelete = it.onDelete,
                onUpdate = it.onUpdate,
                deferred = it.deferred
            )
        }.filterNotNull()
    }

    private fun findAndValidatePrimaryKey(
        fields: List<Field>,
        embeddedFields: List<EmbeddedField>
    ): PrimaryKey {

        val candidates = collectPrimaryKeysFromEntityAnnotations(element, fields) +
                collectPrimaryKeysFromPrimaryKeyAnnotations(fields) +
                collectPrimaryKeysFromEmbeddedFields(embeddedFields)

        //`Room数`据库每张表有且至少存在一个主键；如果当前`@Entity`类设置了主键，那么`@Entity`父类也存在主键就会报错。
        context.checker.check(candidates.isNotEmpty(), element, ProcessorErrors.MISSING_PRIMARY_KEY)

        // 1. If a key is not autogenerated, but is Primary key or is part of Primary key we
        // force the @NonNull annotation. If the key is a single Primary Key, Integer or Long, we
        // don't force the @NonNull annotation since SQLite will automatically generate IDs.
        // 2. If a key is autogenerate, we generate NOT NULL in table spec, but we don't require
        // @NonNull annotation on the field itself.
        //如果@PrimaryKey#autoGenerate = true，那么主键字段不是int类型，必须使用@NonNull修饰，表示不允许null值；
        val verifiedFields = mutableSetOf<Field>() // track verified fields to not over report
        candidates.filterNot { it.autoGenerateId }.forEach { candidate ->
            candidate.fields.forEach { field ->

                if (candidate.fields.size > 1 ||
                    (candidate.fields.size == 1 && field.affinity != SQLTypeAffinity.INTEGER)
                ) {
                    if (!verifiedFields.contains(field)) {
                        context.checker.check(
                            field.nonNull,
                            field.element,
                            ProcessorErrors.primaryKeyNull(field.getPath())
                        )
                        verifiedFields.add(field)
                    }
                    // Validate parents for nullability
                    var parent = field.parent
                    while (parent != null) {
                        val parentField = parent.field
                        if (!verifiedFields.contains(parentField)) {
                            context.checker.check(
                                parentField.nonNull,
                                parentField.element,
                                ProcessorErrors.primaryKeyNull(parentField.getPath())
                            )
                            verifiedFields.add(parentField)
                        }
                        parent = parentField.parent
                    }
                }
            }
        }

        if (candidates.size == 1) {
            // easy :)
            return candidates.first()
        }

        return choosePrimaryKey(candidates, element)
    }

    /**
     * Check fields for @PrimaryKey.
     */
    private fun collectPrimaryKeysFromPrimaryKeyAnnotations(fields: List<Field>): List<PrimaryKey> {
        return fields.mapNotNull { field ->
            field.element.getAnnotation(androidx.room.PrimaryKey::class)?.let {
                if (field.parent != null) {
                    //`@PrimaryKey`不能修饰`@Entity`类的`@Embedded`变量；
                    // the field in the entity that contains this error.
                    val grandParentField = field.parent.mRootParent.field.element
                    // bound for entity.
                    context.fork(grandParentField).logger.w(
                        Warning.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED,
                        grandParentField,
                        ProcessorErrors.embeddedPrimaryKeyIsDropped(
                            element.qualifiedName, field.name
                        )
                    )
                    null
                } else {
                    PrimaryKey(
                        declaredIn = field.element.enclosingElement,
                        fields = Fields(field),
                        autoGenerateId = it.value.autoGenerate
                    )
                }
            }
        }
    }

    /**
     * Check classes for @Entity(primaryKeys = ?).
     */
    private fun collectPrimaryKeysFromEntityAnnotations(
        typeElement: XTypeElement,
        availableFields: List<Field>
    ): List<PrimaryKey> {
        val myPkeys = typeElement.getAnnotation(androidx.room.Entity::class)?.let {
            //@Entity#primaryKeys中的属性值表示主键，并且该属性值必须存在于当前表常规字段或嵌入表常规字段；
            val primaryKeyColumns = it.value.primaryKeys
            if (primaryKeyColumns.isEmpty()) {
                emptyList()
            } else {
                val fields = primaryKeyColumns.mapNotNull { pKeyColumnName ->
                    val field = availableFields.firstOrNull { it.columnName == pKeyColumnName }
                    context.checker.check(
                        field != null, typeElement,
                        ProcessorErrors.primaryKeyColumnDoesNotExist(
                            pKeyColumnName,
                            availableFields.map { it.columnName }
                        )
                    )
                    field
                }
                listOf(
                    PrimaryKey(
                        declaredIn = typeElement,
                        fields = Fields(fields),
                        autoGenerateId = false
                    )
                )
            }
        } ?: emptyList()
        // checks supers.
        val mySuper = typeElement.superClass
        val superPKeys = if (mySuper != null && mySuper.isNotNone()) {
            // my super cannot see my fields so remove them.
            val remainingFields = availableFields.filterNot {
                it.element.enclosingElement == typeElement
            }
            //主键具有继承性，e.g.@Entity修饰的类的父类如果也使用@Entity修饰，那么父类中的@Entity#primaryKeys会被当前类继承；
            collectPrimaryKeysFromEntityAnnotations(mySuper.typeElement!!, remainingFields)
        } else {
            emptyList()
        }
        return superPKeys + myPkeys
    }

    private fun collectPrimaryKeysFromEmbeddedFields(
        embeddedFields: List<EmbeddedField>
    ): List<PrimaryKey> {
        //如果修饰的是表嵌入字段，即@PrimaryKey和@Embedded同时修饰的节点：@PrimaryKey#autoGenerate = false || @Embedded修饰对象的表常规字段有且仅有一个
        return embeddedFields.mapNotNull { embeddedField ->
            embeddedField.field.element.getAnnotation(androidx.room.PrimaryKey::class)?.let {
                context.checker.check(
                    !it.value.autoGenerate || embeddedField.pojo.fields.size == 1,
                    embeddedField.field.element,
                    ProcessorErrors.AUTO_INCREMENT_EMBEDDED_HAS_MULTIPLE_FIELDS
                )
                PrimaryKey(
                    declaredIn = embeddedField.field.element.enclosingElement,
                    fields = embeddedField.pojo.fields,
                    autoGenerateId = it.value.autoGenerate
                )
            }
        }
    }

    // start from my element and check if anywhere in the list we can find the only well defined
    // pkey, if so, use it.
    private fun choosePrimaryKey(
        candidates: List<PrimaryKey>,
        typeElement: XTypeElement
    ): PrimaryKey {
        // If 1 of these primary keys is declared in this class, then it is the winner. Just print
        //    a note for the others.
        // If 0 is declared, check the parent.
        // If more than 1 primary key is declared in this class, it is an error.
        val myPKeys = candidates.filter { candidate ->
            candidate.declaredIn == typeElement
        }
        return if (myPKeys.size == 1) {
            // just note, this is not worth an error or warning
            (candidates - myPKeys).forEach {
                context.logger.d(
                    element,
                    "${it.toHumanReadableString()} is" +
                            " overridden by ${myPKeys.first().toHumanReadableString()}"
                )
            }
            myPKeys.first()
        } else if (myPKeys.isEmpty()) {
            // i have not declared anything, delegate to super
            val mySuper = typeElement.superClass
            if (mySuper != null && mySuper.isNotNone()) {
                return choosePrimaryKey(candidates, mySuper.typeElement!!)
            }
            PrimaryKey.MISSING
        } else {
            //表中最多一个主键，主键可以由多个表字段构成
            context.logger.e(
                element,
                ProcessorErrors.multiplePrimaryKeyAnnotations(
                    myPKeys.map(PrimaryKey::toHumanReadableString)
                )
            )
            PrimaryKey.MISSING
        }
    }

    private fun validateAndCreateIndices(
        inputs: List<IndexInput>,
        pojo: Pojo
    ): List<Index> {
        // check for columns
        val indices = inputs.mapNotNull { input ->
            context.checker.check(
                input.columnNames.isNotEmpty(), element,
                INDEX_COLUMNS_CANNOT_BE_EMPTY
            )
            val fields = input.columnNames.mapNotNull { columnName ->
                val field = pojo.findFieldByColumnName(columnName)
                context.checker.check(
                    field != null, element,
                    ProcessorErrors.indexColumnDoesNotExist(columnName, pojo.columnNames)
                )
                field
            }
            if (input.orders.isNotEmpty()) {
                context.checker.check(
                    input.columnNames.size == input.orders.size, element,
                    INVALID_INDEX_ORDERS_SIZE
                )
            }
            if (fields.isEmpty()) {
                null
            } else {
                Index(
                    name = input.name,
                    unique = input.unique,
                    fields = fields,
                    orders = input.orders
                )
            }
        }

        // check for duplicate indices
        //一个表中新建的索引名称只能出现一次
        indices
            .groupBy { it.name }
            .filter { it.value.size > 1 }
            .forEach {
                context.logger.e(element, ProcessorErrors.duplicateIndexInEntity(it.key))
            }

        // see if any embedded field is an entity with indices, if so, report a warning
        //嵌入表中最好不要存在使用索引，否则会报警告，表示当前索引无效
        pojo.embeddedFields.forEach { embedded ->
            val embeddedElement = embedded.pojo.element
            embeddedElement.getAnnotation(androidx.room.Entity::class)?.let {
                val subIndices = extractIndices(it, "")
                if (subIndices.isNotEmpty()) {
                    context.logger.w(
                        Warning.INDEX_FROM_EMBEDDED_ENTITY_IS_DROPPED,
                        embedded.field.element,
                        ProcessorErrors.droppedEmbeddedIndex(
                            entityName = embedded.pojo.typeName.toString(),
                            fieldPath = embedded.field.getPath(),
                            grandParent = element.qualifiedName
                        )
                    )
                }
            }
        }
        return indices
    }

    // check if parent is an Entity, if so, report its annotation indices
    private fun loadSuperIndices(
        typeMirror: XType?,
        tableName: String,
        inherit: Boolean
    ): List<IndexInput> {
        if (typeMirror == null || typeMirror.isNone()) {
            return emptyList()
        }
        val parentTypeElement = typeMirror.typeElement
        @Suppress("FoldInitializerAndIfToElvis")
        if (parentTypeElement == null) {
            // this is coming from a parent, shouldn't happen so no reason to report an error
            return emptyList()
        }

        //`@Entity`类的继承类也是一个`@Entity`类，如果父`@Entity`类中存在索引，那么当前`@Entity`类要设置`@Entity#inheritSuperIndices = true`表示沿用父类的索引，否则警告；
        val myIndices = parentTypeElement
            .getAnnotation(androidx.room.Entity::class)?.let { annotation ->
                val indices = extractIndices(annotation, tableName = "super")
                if (indices.isEmpty()) {
                    emptyList()
                } else if (inherit) {//表示当前索引可以传递给子类
                    // rename them
                    indices.map {
                        IndexInput(
                            name = createIndexName(it.columnNames, tableName),
                            unique = it.unique,
                            columnNames = it.columnNames,
                            orders = it.orders
                        )
                    }
                } else {
                    context.logger.w(
                        Warning.INDEX_FROM_PARENT_IS_DROPPED,
                        parentTypeElement,
                        ProcessorErrors.droppedSuperClassIndex(
                            childEntity = element.qualifiedName,
                            superEntity = parentTypeElement.qualifiedName
                        )
                    )
                    emptyList()
                }
            } ?: emptyList()
        return myIndices + loadSuperIndices(parentTypeElement.superClass, tableName, inherit)
    }
}
