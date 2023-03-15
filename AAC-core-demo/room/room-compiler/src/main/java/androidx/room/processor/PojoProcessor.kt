/*
 * Copyright (C) 2017 The Android Open Source Project
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

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.compiler.processing.XExecutableElement
import androidx.room.compiler.processing.XFieldElement
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XTypeElement
import androidx.room.compiler.processing.XVariableElement
import androidx.room.compiler.processing.isCollection
import androidx.room.compiler.processing.isVoid
import androidx.room.ext.isNotVoid
import androidx.room.processor.ProcessorErrors.CANNOT_FIND_GETTER_FOR_FIELD
import androidx.room.processor.ProcessorErrors.CANNOT_FIND_SETTER_FOR_FIELD
import androidx.room.processor.ProcessorErrors.POJO_FIELD_HAS_DUPLICATE_COLUMN_NAME
import androidx.room.processor.autovalue.AutoValuePojoProcessorDelegate
import androidx.room.processor.cache.Cache
import androidx.room.vo.CallType
import androidx.room.vo.Constructor
import androidx.room.vo.EmbeddedField
import androidx.room.vo.Entity
import androidx.room.vo.EntityOrView
import androidx.room.vo.Field
import androidx.room.vo.FieldGetter
import androidx.room.vo.FieldSetter
import androidx.room.vo.Pojo
import androidx.room.vo.PojoMethod
import androidx.room.vo.Warning
import androidx.room.vo.columnNames
import androidx.room.vo.findFieldByColumnName
import com.google.auto.value.AutoValue

/**
 * Processes any class as if it is a Pojo.
 */
class PojoProcessor private constructor(
    baseContext: Context,
    val element: XTypeElement,
    val bindingScope: FieldProcessor.BindingScope,
    val parent: EmbeddedField?,
    val referenceStack: LinkedHashSet<String> = LinkedHashSet(),
    private val delegate: Delegate
) {
    val context = baseContext.fork(element)

    companion object {
        val PROCESSED_ANNOTATIONS = listOf(ColumnInfo::class, Embedded::class, Relation::class)

        val TARGET_METHOD_ANNOTATIONS = arrayOf(
            PrimaryKey::class, ColumnInfo::class,
            Embedded::class, Relation::class
        )

        fun createFor(
            context: Context,
            element: XTypeElement,
            bindingScope: FieldProcessor.BindingScope,
            parent: EmbeddedField?,
            referenceStack: LinkedHashSet<String> = LinkedHashSet()
        ): PojoProcessor {
            //如果pojo节点又使用了@AutoValue修饰，处理的节点是AutoValue_原先类名
            val (pojoElement, delegate) = if (element.hasAnnotation(AutoValue::class)) {
                val processingEnv = context.processingEnv
                val autoValueGeneratedTypeName =
                    AutoValuePojoProcessorDelegate.getGeneratedClassName(element)
                val autoValueGeneratedElement =
                    processingEnv.findTypeElement(autoValueGeneratedTypeName)
                if (autoValueGeneratedElement != null) {
                    autoValueGeneratedElement to AutoValuePojoProcessorDelegate(context, element)
                } else {
                    context.reportMissingType(autoValueGeneratedTypeName)
                    element to EmptyDelegate
                }
            } else {
                element to DefaultDelegate(context)
            }

            return PojoProcessor(
                baseContext = context,
                element = pojoElement,//如果pojo节点使用了@AutoValue修饰，当前节点是 ： AutoValue_原先类名，并且delete使用AutoValuePojoProcessorDelegate；
                bindingScope = bindingScope,
                parent = parent,
                referenceStack = referenceStack,
                delegate = delegate
            )
        }
    }

    fun process(): Pojo {
        return context.cache.pojos.get(Cache.PojoKey(element, bindingScope, parent)) {
            referenceStack.add(element.qualifiedName)
            try {
                doProcess()
            } finally {
                referenceStack.remove(element.qualifiedName)
            }
        }
    }

    private fun doProcess(): Pojo {
        if (!element.validate()) {
            context.reportMissingTypeReference(element.qualifiedName)
            return delegate.createPojo(
                element = element,
                declaredType = element.type,
                fields = emptyList(),
                embeddedFields = emptyList(),
                relations = emptyList(),
                constructor = null
            )
        }
        delegate.onPreProcess(element)


        val declaredType = element.type
        // TODO handle conflicts with super: b/35568142
        //所有字段，包括父类私有字段
        val allFields = element.getAllFieldsIncludingPrivateSupers()
            .filter {
                //有效字段：没有使用@Ignore注解 &&
                // 没有使用static修饰 &&
                // （没有使用transient修饰 || 使用@ColumnInfo、@Embedded或@Relation修饰）
                !it.hasAnnotation(Ignore::class) &&
                        !it.isStatic() &&
                        (
                                !it.isTransient() ||
                                        it.hasAnyAnnotation(
                                            ColumnInfo::class,
                                            Embedded::class,
                                            Relation::class
                                        )
                                )
            }
            .groupBy { field ->
                //@ColumnInfo、@Embedded或@Relation修饰字段，该字段只允许出现这三种中的一种；
                context.checker.check(
                    PROCESSED_ANNOTATIONS.count { field.hasAnnotation(it) } < 2, field,
                    ProcessorErrors.CANNOT_USE_MORE_THAN_ONE_POJO_FIELD_ANNOTATION
                )
                if (field.hasAnnotation(Embedded::class)) {
                    Embedded::class
                } else if (field.hasAnnotation(Relation::class)) {
                    Relation::class
                } else {
                    null
                }
            }

        //使用@Entity修饰的类，@Entity#ignoredColumns值
        val ignoredColumns =
            element.getAnnotation(androidx.room.Entity::class)?.value?.ignoredColumns?.toSet()
                ?: emptySet()

        val fieldBindingErrors = mutableMapOf<Field, String>()

        //常规表字段
        val unfilteredMyFields = allFields[null]
            ?.map {
                FieldProcessor(
                    baseContext = context,
                    containing = declaredType,//字段所在类
                    element = it,//有效变量节点
                    bindingScope = bindingScope,
                    fieldParent = parent,
                    onBindingError = { field, errorMsg ->
                        fieldBindingErrors[field] = errorMsg
                    }
                ).process()
            } ?: emptyList()

        //没有被忽略的表常规字段
        val myFields = unfilteredMyFields.filterNot { ignoredColumns.contains(it.columnName) }
        myFields.forEach { field ->
            fieldBindingErrors[field]?.let {
                context.logger.e(field.element, it)
            }
        }

        //使用@Embedded修饰修饰的有效字段
        val unfilteredEmbeddedFields =
            allFields[Embedded::class]
                ?.mapNotNull {
                    //嵌入式
                    processEmbeddedField(
                        declaredType, //字段所在类
                        it//@Embedded修饰修饰的有效字段
                    )
                }
                ?: emptyList()

        //@Embedded修饰的有效字段筛选下被忽略的字段
        val embeddedFields =
            unfilteredEmbeddedFields.filterNot { ignoredColumns.contains(it.field.columnName) }

        //@Embedded类中的表字段
        val subFields = embeddedFields.flatMap { it.pojo.fields }

        //表字段 = 当前表字段 + 内嵌表字段
        val fields = myFields + subFields

        val unfilteredCombinedFields =
            unfilteredMyFields + unfilteredEmbeddedFields.map { it.field }

        val missingIgnoredColumns = ignoredColumns.filterNot { ignoredColumn ->
            unfilteredCombinedFields.any { it.columnName == ignoredColumn }
        }

        // `@Entity#ignoredColumns`属性值只能是当前表字段 或当前表`@Embedded`嵌入字段
        context.checker.check(
            missingIgnoredColumns.isEmpty(), element,
            ProcessorErrors.missingIgnoredColumns(missingIgnoredColumns)
        )

        val myRelationsList = allFields[Relation::class]
            ?.mapNotNull {
                processRelationField(
                    fields, //表常规字段 + 嵌入表常规字段
                    declaredType, //字段所在类
                    it//使用@Relation修饰的有效字段
                )
            }
            ?: emptyList()

        //嵌入表关系字段
        val subRelations = embeddedFields.flatMap { it.pojo.relations }

        //当前表关系对象 + 嵌入表关系字段
        val relations = myRelationsList + subRelations

        //表常规字段和嵌入表常规字段名称不允许重复
        fields.groupBy { it.columnName }
            .filter { it.value.size > 1 }
            .forEach {
                context.logger.e(
                    element,
                    ProcessorErrors.pojoDuplicateFieldNames(
                        it.key, it.value.map(Field::getPath)
                    )
                )
                it.value.forEach {
                    context.logger.e(it.element, POJO_FIELD_HAS_DUPLICATE_COLUMN_NAME)
                }
            }


        //获取所有非private修饰的方法
        val methods = element.getAllNonPrivateInstanceMethods()
            .asSequence()
            .filter {
                //筛选出非抽象 && 没有使用@Ignore修饰的方法
                !it.isAbstract() && !it.hasAnnotation(Ignore::class)
            }.map {
                PojoMethodProcessor(
                    context = context,
                    element = it,//方法节点
                    owner = declaredType//@Entity修饰的类
                ).process()
            }.toList()


        //获取getter方法：无参，返回类型不是void
        val getterCandidates = methods.filter {
            it.element.parameters.size == 0 && it.resolvedType.returnType.isNotVoid()
        }

        //获取setter方法，参数有且仅有一个，返回类型是void
        val setterCandidates = methods.filter {
            it.element.parameters.size == 1 && it.resolvedType.returnType.isVoid()
        }

        // don't try to find a constructor for binding to statement.
        val constructor = if (bindingScope == FieldProcessor.BindingScope.BIND_TO_STMT) {//如果是存入
            // we don't need to construct this POJO.
            null
        } else {
            //如果当前pojo对象表示的是从room中查询时
            chooseConstructor(
                myFields,//没有被忽略掉的表常规字段
                embeddedFields,//没有被忽略掉的表嵌入字段
                relations//当前表关系对象 + 当前表的嵌入表关系对象
            )
        }

        //校验get和set方法
        assignGetters(myFields, getterCandidates)
        assignSetters(myFields, setterCandidates, constructor)

        embeddedFields.forEach {
            assignGetter(it.field, getterCandidates)
            assignSetter(it.field, setterCandidates, constructor)
        }

        myRelationsList.forEach {
            assignGetter(it.field, getterCandidates)
            assignSetter(it.field, setterCandidates, constructor)
        }

        //新建Pojo对象
        return delegate.createPojo(
            element,
            declaredType,
            fields,
            embeddedFields,
            relations,
            constructor
        )
    }

    private fun chooseConstructor(
        myFields: List<Field>,
        embedded: List<EmbeddedField>,
        relations: List<androidx.room.vo.Relation>
    ): Constructor? {
        //查找构造函数：
        //（1）pojo节点（没有使用@AutoValue修饰）构造函数条件：当前构造函数没有被@Ignore修饰 || 当前构造函数不是private修饰
        //（2）pojo节点（同时使用@AutoValue修饰）构造函数条件：方法没有被@Ignore修饰 && 方法没有被private修饰 && 方法是static修饰 && 方法返回类型是当前类类型
        val constructors = delegate.findConstructors(element)

        //表常规字段名
        val fieldMap = myFields.associateBy { it.name }

        //表嵌入字段名
        val embeddedMap = embedded.associateBy { it.field.name }

        //表关联字段名
        val relationMap = relations.associateBy { it.field.name }

        val failedConstructors = arrayListOf<FailedConstructor>()

        val goodConstructors = constructors.map { constructor ->
            //方法参数名
            val parameterNames = constructor.parameters.map { it.name }

            //param@表示循环控制，如果满足条件输出 return@param
            val params = constructor.parameters.mapIndexed param@{ index, param ->
                val paramName = parameterNames[index]
                val paramType = param.type

                //从字段对象、字段名称和字段类型判断是否匹配
                val matches = fun(field: Field?): Boolean {
                    return if (field == null) {
                        false
                    } else if (!field.nameWithVariations.contains(paramName)) {
                        false
                    } else {
                        // see: b/69164099
                        field.type.isAssignableFromWithoutVariance(paramType)
                    }
                }

                //构造函数中的参数根据以下顺序匹配（**一般不需要设置构造函数，或者不会存在过于麻烦的构造函数**）：
                // - ① 参数名和当前`pojo`表某个常规字段或`pojo`对象的内嵌对象常规字段一致；参数类型可以是字段类型子类；
                // - ② 参数名和当前`pojo`表某个内嵌对象一致；参数类型可以是字段类型子类；；
                // - ③ 参数名和当前`pojo`对象表某个关联对象一致；参数类型可以是字段类型子类；
                val exactFieldMatch = fieldMap[paramName]
                if (matches(exactFieldMatch)) {
                    return@param Constructor.Param.FieldParam(exactFieldMatch!!)
                }

                //当前构造函数参数 存在于 表嵌入字段中
                val exactEmbeddedMatch = embeddedMap[paramName]
                if (matches(exactEmbeddedMatch?.field)) {
                    return@param Constructor.Param.EmbeddedParam(exactEmbeddedMatch!!)
                }

                //当前构造函数参数 存在于 表关系字段中
                val exactRelationMatch = relationMap[paramName]
                if (matches(exactRelationMatch?.field)) {
                    return@param Constructor.Param.RelationParam(exactRelationMatch!!)
                }

                //以上条件满足，则不往下校验，否则继续往下校验：
                //
                // - ④ 参数名去掉`_或m或is或has`后把首字母置为小写字母匹配上当前`pojo`表某个常规字段或`pojo`对象的内嵌对象常规字段；参数类型可以是字段类型子类；
                // - ⑤ 参数名去掉`_或m或is或has`后把首字母置为小写字母匹配上当前`pojo`表某个内嵌字段；参数类型可以是字段类型子类；
                // - ⑥ 参数名去掉`_或m或is或has`后把首字母置为小写字母匹配上当前`pojo`表某个关联字段；参数类型可以是字段类型子类；
                // - 如果参数匹配 ④⑤⑥ 超过1个，直接报错，当前参数必须明确匹配哪一个字段。

                val matchingFields = myFields.filter {
                    matches(it)
                }
                val embeddedMatches = embedded.filter {
                    matches(it.field)
                }
                val relationMatches = relations.filter {
                    matches(it.field)
                }

                when (matchingFields.size + embeddedMatches.size + relationMatches.size) {
                    0 -> null
                    1 -> when {
                        matchingFields.isNotEmpty() ->
                            Constructor.Param.FieldParam(matchingFields.first())
                        embeddedMatches.isNotEmpty() ->
                            Constructor.Param.EmbeddedParam(embeddedMatches.first())
                        else ->
                            Constructor.Param.RelationParam(relationMatches.first())
                    }
                    else -> {
                        context.logger.e(
                            param,
                            ProcessorErrors.ambiguousConstructor(
                                pojo = element.qualifiedName,
                                paramName = paramName,
                                matchingFields = matchingFields.map { it.getPath() } +
                                        embeddedMatches.map { it.field.getPath() } +
                                        relationMatches.map { it.field.getPath() }
                            )
                        )
                        null
                    }
                }
            }

            if (params.any { it == null }) {
                failedConstructors.add(FailedConstructor(constructor, parameterNames, params))
                null
            } else {
                @Suppress("UNCHECKED_CAST")
                Constructor(constructor, params as List<Constructor.Param>)
            }
        }.filterNotNull()

        when {
            //构造函数参数如果没有匹配到当前`pojo`对象中的任何字段，表示当前构造函数是失败构造函数；否则都是成功构造函数；
            // - ① 不存在成功构造函数，但是却存在失败构造函数则报错；
            // - ② 存在一个成功构造函数，直接使用当前成功构造函数，失败构造函数不用管；
            // - ③ 成功构造函数超过一个，是kotlin则返回主构造函数；否则返回不带参的成功构造函数，否则直接报错，不允许存在两个都带参成功构造函数。
            goodConstructors.isEmpty() -> {
                if (failedConstructors.isNotEmpty()) {
                    val failureMsg = failedConstructors.joinToString("\n") { entry ->
                        entry.log()
                    }
                    context.logger.e(
                        element,
                        ProcessorErrors.MISSING_POJO_CONSTRUCTOR +
                                "\nTried the following constructors but they failed to match:" +
                                "\n$failureMsg"
                    )
                }
                context.logger.e(element, ProcessorErrors.MISSING_POJO_CONSTRUCTOR)
                return null
            }

            goodConstructors.size > 1 -> {
                // if the Pojo is a Kotlin data class then pick its primary constructor. This is
                // better than picking the no-arg constructor and forcing users to define fields as
                // vars.
                val primaryConstructor =
                    element.findPrimaryConstructor()?.let { primary ->
                        goodConstructors.firstOrNull { candidate ->
                            candidate.element == primary
                        }
                    }

                if (primaryConstructor != null) {
                    return primaryConstructor
                }
                // if there is a no-arg constructor, pick it. Even though it is weird, easily happens
                // with kotlin data classes.
                val noArg = goodConstructors.firstOrNull { it.params.isEmpty() }
                if (noArg != null) {
                    context.logger.w(
                        Warning.DEFAULT_CONSTRUCTOR, element,
                        ProcessorErrors.TOO_MANY_POJO_CONSTRUCTORS_CHOOSING_NO_ARG
                    )
                    return noArg
                }
                goodConstructors.forEach {
                    context.logger.e(it.element, ProcessorErrors.TOO_MANY_POJO_CONSTRUCTORS)
                }
                return null
            }

            else -> return goodConstructors.first()
        }
    }

    private fun processEmbeddedField(
        declaredType: XType,
        variableElement: XFieldElement
    ): EmbeddedField? {
        val asMemberType = variableElement.asMemberOf(declaredType)
        val asTypeElement = asMemberType.typeElement
        //@Embedded修饰的字段类型必须是类或接口
        if (asTypeElement == null) {
            context.logger.e(
                variableElement,
                ProcessorErrors.EMBEDDED_TYPES_MUST_BE_A_CLASS_OR_INTERFACE
            )
            return null
        }

        if (detectReferenceRecursion(asTypeElement)) {
            return null
        }

        val fieldPrefix = variableElement.getAnnotation(Embedded::class)?.value?.prefix ?: ""
        val inheritedPrefix = parent?.prefix ?: ""

        //@Embedded修饰的有效字段生成Field对象
        val embeddedField = Field(
            variableElement,//@Embedded修饰的有效字段
            variableElement.name,//@Embedded修饰的有效字段名
            type = asMemberType,//@Embedded修饰的有效字段类型
            affinity = null,
            parent = parent
        )

        val subParent = EmbeddedField(
            field = embeddedField,
            prefix = inheritedPrefix + fieldPrefix,
            parent = parent
        )

        //对当前@Embedded修饰的类或接口生成Pojo对象
        subParent.pojo = createFor(
            context = context.fork(variableElement),
            element = asTypeElement,//@Embedded修饰的节点
            bindingScope = bindingScope,
            parent = subParent,//@Embedded修饰的变量生成的对象
            referenceStack = referenceStack
        ).process()

        return subParent
    }

    //使用@Relation修饰的有效字段的处理
    private fun processRelationField(
        myFields: List<Field>,
        container: XType,
        relationElement: XFieldElement
    ): androidx.room.vo.Relation? {
        val annotation = relationElement.getAnnotation(Relation::class)!!

        //`@Relation#parentColumn`的属性值必须存在，并且存在于`@Relation`修饰字段所在类的表字段或内嵌对象表字段中；
        val parentField = myFields.firstOrNull {
            it.columnName == annotation.value.parentColumn
        }

        if (parentField == null) {
            context.logger.e(
                relationElement,
                ProcessorErrors.relationCannotFindParentEntityField(
                    entityName = element.qualifiedName,
                    columnName = annotation.value.parentColumn,
                    availableColumns = myFields.map { it.columnName }
                )
            )
            return null
        }
        // parse it as an entity.
        val asMember = relationElement.asMemberOf(container)

        //如果是List<T>或Set<T>类型，返回其中T；如果是<? extends T>返回其中的T
        val asType = if (asMember.isCollection()) {
            asMember.typeArguments.first().extendsBoundOrSelf()
        } else {
            asMember
        }
        //`@Relation`修饰的必须是类或接口;如果是集合，只允许是`List< T>或Set< T>`集合,T必须是类或接口；
        val typeElement = asType.typeElement
        if (typeElement == null) {
            context.logger.e(
                relationElement,
                ProcessorErrors.RELATION_TYPE_MUST_BE_A_CLASS_OR_INTERFACE
            )
            return null
        }

        val entityClassInput = annotation.getAsType("entity")

        // do we need to decide on the entity?
        val inferEntity = (entityClassInput == null || entityClassInput.isTypeOf(Any::class))
        val entityElement = if (inferEntity) {
            typeElement
        } else {
            entityClassInput!!.typeElement
        }
        // `@Relation#entity`属性值表示关联对象，必须是类或接口；默认为空，使用`@Relation`修饰类表示（如果修饰类是集合，使用集合中T表示）；
        if (entityElement == null) {
            // this should not happen as we check for declared above but for compile time
            // null safety, it is still good to have this additional check here.
            context.logger.e(
                typeElement,
                ProcessorErrors.RELATION_TYPE_MUST_BE_A_CLASS_OR_INTERFACE
            )
            return null
        }

        if (detectReferenceRecursion(entityElement)) {
            return null
        }

        //entity关系节点必须使用@Entity或@DatabaseView修饰
        val entity = EntityOrViewProcessor(context, entityElement, referenceStack).process()

        // now find the field in the entity.
        //`@Relation#entityColumn`属性值必须存在，并且存在于当前关联对象表字段或其内嵌对象表字段中；
        val entityField = entity.findFieldByColumnName(annotation.value.entityColumn)
        if (entityField == null) {
            context.logger.e(
                relationElement,
                ProcessorErrors.relationCannotFindEntityField(
                    entityName = entity.typeName.toString(),
                    columnName = annotation.value.entityColumn,
                    availableColumns = entity.columnNames
                )
            )
            return null
        }

        // do we have a join entity?
        //@Relation#associateBy
        val junctionAnnotation = annotation.getAsAnnotationBox<Junction>("associateBy")

        val junctionClassInput = junctionAnnotation.getAsType("value")

        //@Relation#associateBy的属性@Junction注解，@Junction#value的属性值类型必须是@Entity 或 @DatabaseView修饰
        val junctionElement: XTypeElement? = if (junctionClassInput != null &&
            !junctionClassInput.isTypeOf(Any::class)
        ) {
            junctionClassInput.typeElement.also {
                if (it == null) {
                    context.logger.e(
                        relationElement,
                        ProcessorErrors.NOT_ENTITY_OR_VIEW
                    )
                }
            }
        } else {
            null
        }

        val junction = junctionElement?.let {

            val entityOrView = EntityOrViewProcessor(context, it, referenceStack).process()


            fun findAndValidateJunctionColumn(
                columnName: String,
                onMissingField: () -> Unit
            ): Field? {
                //关联父表字段必须存在于多对多连接对象中
                val field = entityOrView.findFieldByColumnName(columnName)
                if (field == null) {
                    onMissingField()
                    return null
                }

                //如果当前@Junction#value的属性值类型是entity节点（表信息），那么当前表的主键和索引字段应该包含parentColunm属性和entityColumn属性，否则警告-因为会导致全表扫描，影响效率；
                if (entityOrView is Entity) {
                    // warn about not having indices in the junction columns, only considering
                    // 1st column in composite primary key and indices, since order matters.
                    val coveredColumns = entityOrView.primaryKey.fields.columnNames.first() +
                            entityOrView.indices.map { it.columnNames.first() }
                    if (!coveredColumns.contains(field.columnName)) {
                        context.logger.w(
                            Warning.MISSING_INDEX_ON_JUNCTION, field.element,
                            ProcessorErrors.junctionColumnWithoutIndex(
                                entityName = entityOrView.typeName.toString(),
                                columnName = columnName
                            )
                        )
                    }
                }
                return field
            }

            // `@Relation#associateBy`属性中，如果`@Junction#parentColumn`存在，使用该属性多对多关联父表；可以不设置，直接使用`@Relation#parentColunm`属性值多对多关联父表
            val junctionParentColumn = if (junctionAnnotation.value.parentColumn.isNotEmpty()) {
                junctionAnnotation.value.parentColumn
            } else {
                parentField.columnName
            }

            val junctionParentField = findAndValidateJunctionColumn(
                columnName = junctionParentColumn,
                onMissingField = {
                    context.logger.e(
                        junctionElement,
                        ProcessorErrors.relationCannotFindJunctionParentField(
                            entityName = entityOrView.typeName.toString(),
                            columnName = junctionParentColumn,
                            availableColumns = entityOrView.columnNames
                        )
                    )
                }
            )

            //在`@Relation#associateBy`属性中，如果`@Junction#entityColumn`存在，使用当前字段作为**多对多关联子表字段**；可以不设置，直接使用`@Relation#entityColumn`作为多对多关联子表字段；
            val junctionEntityColumn = if (junctionAnnotation.value.entityColumn.isNotEmpty()) {
                junctionAnnotation.value.entityColumn
            } else {
                entityField.columnName
            }
            val junctionEntityField = findAndValidateJunctionColumn(
                columnName = junctionEntityColumn,
                onMissingField = {
                    context.logger.e(
                        junctionElement,
                        ProcessorErrors.relationCannotFindJunctionEntityField(
                            entityName = entityOrView.typeName.toString(),
                            columnName = junctionEntityColumn,
                            availableColumns = entityOrView.columnNames
                        )
                    )
                }
            )

            if (junctionParentField == null || junctionEntityField == null) {
                return null
            }

            androidx.room.vo.Junction(
                entity = entityOrView,
                parentField = junctionParentField,
                entityField = junctionEntityField
            )
        }

        val field = Field(
            element = relationElement,//@Relation修饰的有效字段节点
            name = relationElement.name,//@Relation修饰的有效字段名称
            type = relationElement.asMemberOf(container),//@Relation修饰的有效字段类型
            affinity = null,
            parent = parent
        )

        val projection = if (annotation.value.projection.isEmpty()) {
            // we need to infer the projection from inputs.
            //@Relation#projection为空
            createRelationshipProjection(
                inferEntity,//@Relation#entity不存在或者里面的对象是any类型，则为true
                asType,//关联对象类型
                entity,//关联对象
                entityField,//@Relation#entityColumn
                typeElement//关联对象节点
            )
        } else {
            // make sure projection makes sense
            //@Relation#projection如果存在，必须存在于关联对象中；
            validateRelationshipProjection(
                annotation.value.projection,
                entity,
                relationElement
            )
            annotation.value.projection.asList()
        }
        // if types don't match, row adapter prints a warning
        return androidx.room.vo.Relation(
            entity = entity,
            pojoType = asType,
            field = field,
            parentField = parentField,
            entityField = entityField,
            junction = junction,
            projection = projection
        )
    }

    private fun validateRelationshipProjection(
        projectionInput: Array<String>,
        entity: EntityOrView,
        relationElement: XVariableElement
    ) {
        //@Relation#projection属性如果存在，那么该属性值必须存在于关联对象（术语解释8）表或视图的表字段中；
        val missingColumns = projectionInput.toList() - entity.columnNames
        if (missingColumns.isNotEmpty()) {
            context.logger.e(
                relationElement,
                ProcessorErrors.relationBadProject(
                    entity.typeName.toString(),
                    missingColumns, entity.columnNames
                )
            )
        }
    }

    /**
     * Create the projection column list based on the relationship args.
     *
     *  if entity field in the annotation is not specified, it is the method return type
     *  if it is specified in the annotation:
     *       still check the method return type, if the same, use it
     *       if not, check to see if we can find a column Adapter, if so use the childField
     *       last resort, try to parse it as a pojo to infer it.
     */
    private fun createRelationshipProjection(
        inferEntity: Boolean,
        typeArg: XType,
        entity: EntityOrView,
        entityField: Field,
        typeArgElement: XTypeElement
    ): List<String> {
        return if (inferEntity || typeArg.typeName == entity.typeName) {
            entity.columnNames
        } else {
            val columnAdapter = context.typeAdapterStore.findCursorValueReader(typeArg, null)
            if (columnAdapter != null) {
                // nice, there is a column adapter for this, assume single column response
                listOf(entityField.name)
            } else {
                // last resort, it needs to be a pojo
                val pojo = createFor(
                    context = context,
                    element = typeArgElement,
                    bindingScope = FieldProcessor.BindingScope.READ_FROM_CURSOR,
                    parent = parent,
                    referenceStack = referenceStack
                ).process()
                pojo.columnNames
            }
        }
    }

    private fun detectReferenceRecursion(typeElement: XTypeElement): Boolean {
        //@Embedded、@Relation修饰的有效字段类型，不能存在递归引用
        if (referenceStack.contains(typeElement.qualifiedName)) {
            context.logger.e(
                typeElement,
                ProcessorErrors
                    .RECURSIVE_REFERENCE_DETECTED
                    .format(computeReferenceRecursionString(typeElement))
            )
            return true
        }
        return false
    }

    private fun computeReferenceRecursionString(typeElement: XTypeElement): String {
        val recursiveTailTypeName = typeElement.qualifiedName

        val referenceRecursionList = mutableListOf<String>()
        with(referenceRecursionList) {
            add(recursiveTailTypeName)
            addAll(referenceStack.toList().takeLastWhile { it != recursiveTailTypeName })
            add(recursiveTailTypeName)
        }

        return referenceRecursionList.joinToString(" -> ")
    }

    private fun assignGetters(fields: List<Field>, getterCandidates: List<PojoMethod>) {
        fields.forEach { field ->
            assignGetter(field, getterCandidates)
        }
    }

    private fun assignGetter(field: Field, getterCandidates: List<PojoMethod>) {

        val success = chooseAssignment(
            field = field,
            candidates = getterCandidates,
            nameVariations = field.getterNameWithVariations,
            getType = { method ->
                method.resolvedType.returnType
            },
            assignFromField = {
                field.getter = FieldGetter(
                    jvmName = field.name,
                    type = field.type,
                    callType = CallType.FIELD
                )
            },
            assignFromMethod = { match ->
                field.getter = FieldGetter(
                    jvmName = match.element.jvmName,
                    type = match.resolvedType.returnType,
                    callType = CallType.METHOD
                )
            },
            reportAmbiguity = { matching ->
                context.logger.e(
                    field.element,
                    ProcessorErrors.tooManyMatchingGetters(field, matching)
                )
            }
        )

        context.checker.check(
            success || bindingScope == FieldProcessor.BindingScope.READ_FROM_CURSOR,
            field.element, CANNOT_FIND_GETTER_FOR_FIELD
        )

        if (success && !field.getter.type.isSameType(field.type)) {
            // getter's parameter type is not exactly the same as the field type.
            // put a warning and update the value statement binder.
            context.logger.w(
                warning = Warning.MISMATCHED_GETTER_TYPE,
                element = field.element,
                msg = ProcessorErrors.mismatchedGetter(
                    fieldName = field.name,
                    ownerType = element.type.typeName,
                    getterType = field.getter.type.typeName,
                    fieldType = field.typeName
                )
            )
            field.statementBinder = context.typeAdapterStore.findStatementValueBinder(
                input = field.getter.type,
                affinity = field.affinity
            )
        }
    }

    private fun assignSetters(
        fields: List<Field>,
        setterCandidates: List<PojoMethod>,
        constructor: Constructor?
    ) {
        fields.forEach { field ->
            assignSetter(field, setterCandidates, constructor)
        }
    }

    private fun assignSetter(
        field: Field,
        setterCandidates: List<PojoMethod>,
        constructor: Constructor?
    ) {
        if (constructor != null && constructor.hasField(field)) {
            field.setter = FieldSetter(
                jvmName = field.name,
                type = field.type,
                callType = CallType.CONSTRUCTOR
            )
            return
        }
        val success = chooseAssignment(
            field = field,
            candidates = setterCandidates,
            nameVariations = field.setterNameWithVariations,
            getType = { method ->
                method.resolvedType.parameterTypes.first()
            },
            assignFromField = {
                field.setter = FieldSetter(
                    jvmName = field.name,
                    type = field.type,
                    callType = CallType.FIELD
                )
            },
            assignFromMethod = { match ->
                val paramType = match.resolvedType.parameterTypes.first()
                field.setter = FieldSetter(
                    jvmName = match.element.jvmName,
                    type = paramType,
                    callType = CallType.METHOD
                )
            },
            reportAmbiguity = { matching ->
                context.logger.e(
                    field.element,
                    ProcessorErrors.tooManyMatchingSetter(field, matching)
                )
            }
        )

        context.checker.check(
            success || bindingScope == FieldProcessor.BindingScope.BIND_TO_STMT,
            field.element, CANNOT_FIND_SETTER_FOR_FIELD
        )
        if (success && !field.setter.type.isSameType(field.type)) {
            // setter's parameter type is not exactly the same as the field type.
            // put a warning and update the value reader adapter.
            context.logger.w(
                warning = Warning.MISMATCHED_SETTER_TYPE,
                element = field.element,
                msg = ProcessorErrors.mismatchedSetter(
                    fieldName = field.name,
                    ownerType = element.type.typeName,
                    setterType = field.setter.type.typeName,
                    fieldType = field.typeName
                )
            )
            field.cursorValueReader = context.typeAdapterStore.findCursorValueReader(
                output = field.setter.type,
                affinity = field.affinity
            )
        }
    }

    /**
     * Finds a setter/getter from available list of methods.
     * It returns true if assignment is successful, false otherwise.
     * At worst case, it sets to the field as if it is accessible so that the rest of the
     * compilation can continue.
     */
    private fun chooseAssignment(
        field: Field,
        candidates: List<PojoMethod>,
        nameVariations: List<String>,
        getType: (PojoMethod) -> XType,
        assignFromField: () -> Unit,
        assignFromMethod: (PojoMethod) -> Unit,
        reportAmbiguity: (List<String>) -> Unit
    ): Boolean {
        //有效字段使用public修饰
        if (field.element.isPublic()) {
            assignFromField()
            return true
        }

        //有效字段使用了getter或setter方法，并且当前getter或setter方法是public修饰
        val matching = candidates
            .filter {
                // b/69164099
                // use names in source (rather than jvmName) for matching since that is what user
                // sees in code
                field.type.isAssignableFromWithoutVariance(getType(it)) &&
                        (
                                field.nameWithVariations.contains(it.element.name) ||
                                        nameVariations.contains(it.element.name)
                                )
            }
            .groupBy {
                it.element.isPublic()
            }
        //如果不存在于getter或setter方法中，那么当前有效字段不能是private
        if (matching.isEmpty()) {
            // we always assign to avoid NPEs in the rest of the compilation.
            assignFromField()
            // if field is not private, assume it works (if we are on the same package).
            // if not, compiler will tell, we didn't have any better alternative anyways.
            return !field.element.isPrivate()
        }
        // first try public ones, then try non-public
        //常规字段最多只允许存在一个public修饰的set方法，也只允许存在一个public修饰的get方法
        val match = verifyAndChooseOneFrom(matching[true], reportAmbiguity)
            ?: verifyAndChooseOneFrom(matching[false], reportAmbiguity)
        if (match == null) {
            assignFromField()
            return false
        } else {
            assignFromMethod(match)
            return true
        }
    }

    private fun verifyAndChooseOneFrom(
        candidates: List<PojoMethod>?,
        reportAmbiguity: (List<String>) -> Unit
    ): PojoMethod? {
        if (candidates == null) {
            return null
        }
        if (candidates.size > 1) {
            reportAmbiguity(candidates.map { it.element.name })
        }
        return candidates.first()
    }

    interface Delegate {

        fun onPreProcess(element: XTypeElement)

        /**
         * Constructors are XExecutableElement rather than XConstructorElement to account for
         * factory methods.
         */
        fun findConstructors(element: XTypeElement): List<XExecutableElement>

        fun createPojo(
            element: XTypeElement,
            declaredType: XType,
            fields: List<Field>,
            embeddedFields: List<EmbeddedField>,
            relations: List<androidx.room.vo.Relation>,
            constructor: Constructor?
        ): Pojo
    }

    private class DefaultDelegate(private val context: Context) : Delegate {
        override fun onPreProcess(element: XTypeElement) {
            // Check that certain Room annotations with @Target(METHOD) are not used in the POJO
            // since it is not annotated with AutoValue.
            // pojo节点中的所有方法不允许使用@PrimaryKey, @ColumnInfo,@Embedded, @Relation修饰
            element.getAllMethods()
                .filter { it.hasAnyAnnotation(*TARGET_METHOD_ANNOTATIONS) }
                .forEach { method ->
                    val annotationName = TARGET_METHOD_ANNOTATIONS
                        .first { method.hasAnnotation(it) }
                        .java.simpleName
                    context.logger.e(
                        method,
                        ProcessorErrors.invalidAnnotationTarget(annotationName, method.kindName())
                    )
                }
        }

        override fun findConstructors(element: XTypeElement) = element.getConstructors().filterNot {
            it.hasAnnotation(Ignore::class) || it.isPrivate()
        }

        override fun createPojo(
            element: XTypeElement,
            declaredType: XType,
            fields: List<Field>,
            embeddedFields: List<EmbeddedField>,
            relations: List<androidx.room.vo.Relation>,
            constructor: Constructor?
        ): Pojo {
            return Pojo(
                element = element,
                type = declaredType,
                fields = fields,
                embeddedFields = embeddedFields,
                relations = relations,
                constructor = constructor
            )
        }
    }

    private object EmptyDelegate : Delegate {
        override fun onPreProcess(element: XTypeElement) {}

        override fun findConstructors(element: XTypeElement): List<XExecutableElement> = emptyList()

        override fun createPojo(
            element: XTypeElement,
            declaredType: XType,
            fields: List<Field>,
            embeddedFields: List<EmbeddedField>,
            relations: List<androidx.room.vo.Relation>,
            constructor: Constructor?
        ): Pojo {
            return Pojo(
                element = element,
                type = declaredType,
                fields = emptyList(),
                embeddedFields = emptyList(),
                relations = emptyList(),
                constructor = null
            )
        }
    }

    private data class FailedConstructor(
        val method: XExecutableElement,
        val params: List<String>,
        val matches: List<Constructor.Param?>
    ) {
        fun log(): String {
            val logPerParam = params.withIndex().joinToString(", ") {
                "param:${it.value} -> matched field:" + (matches[it.index]?.log() ?: "unmatched")
            }
            return "$method -> [$logPerParam]"
        }
    }
}
