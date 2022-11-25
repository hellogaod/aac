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

import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.room.ext.SupportDbTypeNames
import androidx.room.ext.isEntityElement
import androidx.room.parser.SqlParser
import androidx.room.compiler.processing.XMethodElement
import androidx.room.compiler.processing.XNullability
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XVariableElement
import androidx.room.processor.ProcessorErrors.RAW_QUERY_STRING_PARAMETER_REMOVED
import androidx.room.vo.MapInfo
import androidx.room.vo.RawQueryMethod

class RawQueryMethodProcessor(
    baseContext: Context,
    val containing: XType,
    val executableElement: XMethodElement
) {
    val context = baseContext.fork(executableElement)

    fun process(): RawQueryMethod {
        val delegate = MethodProcessorDelegate.createFor(context, containing, executableElement)
        val returnType = delegate.extractReturnType()

        context.checker.check(
            executableElement.hasAnnotation(RawQuery::class), executableElement,
            ProcessorErrors.MISSING_RAWQUERY_ANNOTATION
        )

        //rawQuery方法返回类型可以是泛型，但是泛型类型必须是实体类，e.g.List< T>错误，List< String>正确
        val returnTypeName = returnType.typeName
        context.checker.notUnbound(
            returnTypeName, executableElement,
            ProcessorErrors.CANNOT_USE_UNBOUND_GENERICS_IN_QUERY_METHODS
        )

        context.checker.check(
            !delegate.isSuspendAndReturnsDeferredType(),
            executableElement,
            ProcessorErrors.suspendReturnsDeferredType(returnType.rawType.typeName.toString())
        )

        //@RawQuery#observedEntities属性值观察的表集合，及其query解析
        val observedTableNames = processObservedTables()
        val query = SqlParser.rawQueryForTables(observedTableNames)

        // build the query but don't calculate result info since we just guessed it.
        val resultBinder = delegate.findResultBinder(returnType, query) {
            delegate.executableElement.getAnnotation(androidx.room.MapInfo::class)?.let {
                val keyColumn = it.value.keyColumn.toString()
                val valueColumn = it.value.valueColumn.toString()

                //如果使用@MapInfo注解，不允许keyColumn和valueColumn两个属性同时唯恐
                context.checker.check(
                    keyColumn.isNotEmpty() || valueColumn.isNotEmpty(),
                    executableElement,
                    ProcessorErrors.MAP_INFO_MUST_HAVE_AT_LEAST_ONE_COLUMN_PROVIDED
                )
                putData(MapInfo::class, MapInfo(keyColumn, valueColumn))
            }
        }

        val runtimeQueryParam = findRuntimeQueryParameter(delegate.extractParams())

        val inTransaction = executableElement.hasAnnotation(Transaction::class)
        val rawQueryMethod = RawQueryMethod(
            element = executableElement,
            name = executableElement.jvmName,
            observedTableNames = observedTableNames,
            returnType = returnType,
            runtimeQueryParam = runtimeQueryParam,
            inTransaction = inTransaction,
            queryResultBinder = resultBinder
        )
        // TODO: Lift this restriction, to allow for INSERT, UPDATE and DELETE raw statements.
        context.checker.check(
            rawQueryMethod.returnsValue, executableElement,
            ProcessorErrors.RAW_QUERY_BAD_RETURN_TYPE
        )
        return rawQueryMethod
    }

    private fun processObservedTables(): Set<String> {
        val annotation = executableElement.getAnnotation(RawQuery::class)
        //@RawQuery#observedEntities属性值
        //1. @Entity修饰的类；生成的表名称
        //2. @DatabaseView修饰的类，生成的视图名（可以没有@DatabaseView修饰） + 表嵌入字段产生的表名 + 表关联字段关联的表名  - 为空，则报错；
        return annotation?.getAsTypeList("observedEntities")
            ?.mapNotNull {
                it.typeElement.also { typeElement ->
                    if (typeElement == null) {
                        context.logger.e(
                            executableElement,
                            ProcessorErrors.NOT_ENTITY_OR_VIEW
                        )
                    }
                }
            }
            ?.flatMap {
                if (it.isEntityElement()) {
                    val entity = EntityProcessor(
                        context = context,
                        element = it
                    ).process()
                    arrayListOf(entity.tableName)
                } else {
                    val pojo = PojoProcessor.createFor(
                        context = context,
                        element = it,
                        bindingScope = FieldProcessor.BindingScope.READ_FROM_CURSOR,
                        parent = null
                    ).process()
                    val tableNames = pojo.accessedTableNames()
                    // if it is empty, report error as it does not make sense
                    if (tableNames.isEmpty()) {
                        context.logger.e(
                            executableElement,
                            ProcessorErrors.rawQueryBadEntity(it.type.typeName)
                        )
                    }
                    tableNames
                }
            }?.toSet() ?: emptySet()
    }

    private fun findRuntimeQueryParameter(
        extractParams: List<XVariableElement>
    ): RawQueryMethod.RuntimeQueryParameter? {
        //方法参数有且仅有一个 && 方法参数不允许是可变数量参数
        if (extractParams.size == 1 && !executableElement.isVarArgs()) {
            val param = extractParams.first().asMemberOf(containing)
            val processingEnv = context.processingEnv
            //参数不可为null值
            if (param.nullability == XNullability.NULLABLE) {
                context.logger.e(
                    element = extractParams.first(),
                    msg = ProcessorErrors.parameterCannotBeNullable(
                        parameterName = extractParams.first().name
                    )
                )
            }
            // use nullable type to catch bad nullability. Because it is non-null by default in
            // KSP, assignability will fail and we'll print a generic error instead of a specific
            // one
            //参数必须是`androidx.sqlite.db.SupportSQLiteQuery`类型
            val supportQueryType = processingEnv.requireType(SupportDbTypeNames.QUERY)
            val isSupportSql = supportQueryType.isAssignableFrom(param)
            if (isSupportSql) {
                return RawQueryMethod.RuntimeQueryParameter(
                    paramName = extractParams[0].name,
                    type = supportQueryType.typeName
                )
            }
            //参数不允许是String类型
            val stringType = processingEnv.requireType("java.lang.String")
            val isString = stringType.isAssignableFrom(param)
            if (isString) {
                // special error since this was initially allowed but removed in 1.1 beta1
                context.logger.e(executableElement, RAW_QUERY_STRING_PARAMETER_REMOVED)
                return null
            }
        }
        context.logger.e(executableElement, ProcessorErrors.RAW_QUERY_BAD_PARAMS)
        return null
    }
}