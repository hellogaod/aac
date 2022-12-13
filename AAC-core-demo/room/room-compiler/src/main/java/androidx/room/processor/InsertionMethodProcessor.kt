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

@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package androidx.room.processor

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.compiler.processing.XMethodElement
import androidx.room.compiler.processing.XType
import androidx.room.vo.InsertionMethod
import androidx.room.vo.findFieldByColumnName

//@Insert修饰的方法处理
class InsertionMethodProcessor(
    baseContext: Context,
    val containing: XType,
    val executableElement: XMethodElement
) {
    val context = baseContext.fork(executableElement)

    fun process(): InsertionMethod {
        val delegate = ShortcutMethodProcessor(context, containing, executableElement)
        val annotation = delegate.extractAnnotation(
            Insert::class,
            ProcessorErrors.MISSING_INSERT_ANNOTATION
        )

        //@Insert#onConflict：必填项，默认是3，值得范围存在于@OnConflictStrategy注解值范围（0~5）中，自行查看；
        val onConflict = annotation?.value?.onConflict ?: OnConflictProcessor.INVALID_ON_CONFLICT
        context.checker.check(
            onConflict in OnConflictStrategy.NONE..OnConflictStrategy.IGNORE,
            executableElement, ProcessorErrors.INVALID_ON_CONFLICT_VALUE
        )

        val returnType = delegate.extractReturnType()
        val returnTypeName = returnType.typeName
        context.checker.notUnbound(
            returnTypeName, executableElement,
            ProcessorErrors.CANNOT_USE_UNBOUND_GENERICS_IN_INSERTION_METHODS
        )

        val (entities, params) = delegate.extractParams(
            targetEntityType = annotation?.getAsType("entity"),
            missingParamError = ProcessorErrors.INSERTION_DOES_NOT_HAVE_ANY_PARAMETERS_TO_INSERT,
            onValidatePartialEntity = { entity, pojo ->
                val missingPrimaryKeys = entity.primaryKey.fields.any {
                    pojo.findFieldByColumnName(it.columnName) == null
                }
                //除非@Insert#entity中的表主键是自动生成的，否则当前主键必须存在于表常规字段中
                context.checker.check(
                    entity.primaryKey.autoGenerateId || !missingPrimaryKeys,
                    executableElement,
                    ProcessorErrors.missingPrimaryKeysInPartialEntityForInsert(
                        partialEntityName = pojo.typeName.toString(),
                        primaryKeyNames = entity.primaryKey.fields.columnNames
                    )
                )

                // Verify all non null columns without a default value are in the POJO otherwise
                // the INSERT will fail with a NOT NULL constraint.
                val missingRequiredFields = (entity.fields - entity.primaryKey.fields).filter {
                    it.nonNull && it.defaultValue == null &&
                            pojo.findFieldByColumnName(it.columnName) == null
                }

                //@Insert#entity中的表除了主键字段，其他表常规字段或嵌入表常规字段不允许： 默认值为null && 字段不允许null && 字段不存在于insert方法的参数生成的pojo对象表示的表常规字段或嵌入表常规字段中
                context.checker.check(
                    missingRequiredFields.isEmpty(),
                    executableElement,
                    ProcessorErrors.missingRequiredColumnsInPartialEntity(
                        partialEntityName = pojo.typeName.toString(),
                        missingColumnNames = missingRequiredFields.map { it.columnName }
                    )
                )
            }
        )

        val methodBinder = delegate.findInsertMethodBinder(returnType, params)

        context.checker.check(
            methodBinder.adapter != null,
            executableElement,
            ProcessorErrors.CANNOT_FIND_INSERT_RESULT_ADAPTER
        )

        return InsertionMethod(
            element = executableElement,
            returnType = returnType,
            entities = entities,
            parameters = params,
            onConflict = onConflict,
            methodBinder = methodBinder
        )
    }
}
