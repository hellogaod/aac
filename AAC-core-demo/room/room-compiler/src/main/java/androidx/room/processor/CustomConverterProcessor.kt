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

import androidx.room.BuiltInTypeConverters
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.compiler.processing.XElement
import androidx.room.compiler.processing.XMethodElement
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XTypeElement
import androidx.room.compiler.processing.isVoid
import androidx.room.processor.ProcessorErrors.INNER_CLASS_TYPE_CONVERTER_MUST_BE_STATIC
import androidx.room.processor.ProcessorErrors.TYPE_CONVERTER_BAD_RETURN_TYPE
import androidx.room.processor.ProcessorErrors.TYPE_CONVERTER_EMPTY_CLASS
import androidx.room.processor.ProcessorErrors.TYPE_CONVERTER_MISSING_NOARG_CONSTRUCTOR
import androidx.room.processor.ProcessorErrors.TYPE_CONVERTER_MUST_BE_PUBLIC
import androidx.room.processor.ProcessorErrors.TYPE_CONVERTER_MUST_RECEIVE_1_PARAM
import androidx.room.processor.ProcessorErrors.TYPE_CONVERTER_UNBOUND_GENERIC
import androidx.room.solver.types.CustomTypeConverterWrapper
import androidx.room.vo.BuiltInConverterFlags
import androidx.room.vo.CustomTypeConverter
import java.util.LinkedHashSet

/**
 * Processes classes that are referenced in TypeConverters annotations.
 *
 * 使用了@TypeConverters注解处理
 */
class CustomConverterProcessor(
    val context: Context,
    val element: XTypeElement
) {
    companion object {
        private fun XType.isInvalidReturnType() =
            isError() || isVoid() || isNone()

        fun findConverters(context: Context, element: XElement): ProcessResult {

            if (!element.hasAnnotation(TypeConverters::class)) {
                return ProcessResult.EMPTY
            }
            if (!element.validate()) {
                context.reportMissingTypeReference(element.toString())
                return ProcessResult.EMPTY
            }
            val annotation = element.requireAnnotation(TypeConverters::class)
            val classes = annotation.getAsTypeList("value").mapTo(LinkedHashSet()) { it }
            val converters = classes.flatMap {
                val typeElement = it.typeElement//@TypeConverters注解的value属性中的类型必须是一个类
                if (typeElement == null) {
                    context.logger.e(
                        element,
                        ProcessorErrors.typeConverterMustBeDeclared(it.typeName)
                    )
                    emptyList()
                } else {
                    CustomConverterProcessor(context, typeElement).process()
                }
            }

            reportDuplicates(context, converters)

            //@TypeConverters#builtInTypeConverters值
            val builtInStates =
                annotation.getAsAnnotationBox<BuiltInTypeConverters>("builtInTypeConverters").let {
                    BuiltInConverterFlags(
                        enums = it.value.enums,
                        uuid = it.value.uuid
                    )
                }

            return ProcessResult(
                classes = classes,//@TypeConverters#values
                converters = converters.map(::CustomTypeConverterWrapper),//@TypeConverters#values中的item类
                builtInConverterFlags = builtInStates//@TypeConverters#builtInTypeConverters
            )
        }

        //转换类中不允许存在方法返回类型和方法参数类型都一致的`@ProvidedTypeConverter`方法；
        private fun reportDuplicates(context: Context, converters: List<CustomTypeConverter>) {
            converters
                .groupBy { it.from.typeName to it.to.typeName }
                .filterValues { it.size > 1 }
                .values.forEach { possiblyDuplicateConverters ->
                    possiblyDuplicateConverters.forEach { converter ->
                        val duplicates = possiblyDuplicateConverters.filter { duplicate ->
                            duplicate !== converter &&
                                    duplicate.from.isSameType(converter.from) &&
                                    duplicate.to.isSameType(converter.to)
                        }
                        if (duplicates.isNotEmpty()) {
                            context.logger.e(
                                converter.method,
                                ProcessorErrors.duplicateTypeConverters(duplicates)
                            )
                        }
                    }
                }
        }
    }

    fun process(): List<CustomTypeConverter> {
        if (!element.validate()) {
            context.reportMissingTypeReference(element.qualifiedName)
        }
        val methods = element.getAllMethods()
        val converterMethods = methods.filter {
            it.hasAnnotation(TypeConverter::class)
        }.toList()

        val isProvidedConverter = element.hasAnnotation(ProvidedTypeConverter::class)

        //typeConverters对象中必须存在被@TypeConverter修饰的方法；
        context.checker.check(converterMethods.isNotEmpty(), element, TYPE_CONVERTER_EMPTY_CLASS)

        val allStatic = converterMethods.all { it.isStatic() }
        val constructors = element.getConstructors()
        val isKotlinObjectDeclaration = element.isKotlinObject()

        //typeConverters对象没有使用@ProvidedTypeConverter修饰
        if (!isProvidedConverter) {

            //typeConverters对象是内部类，除非使用@ProvidedTypeConverter修饰，否则必须使用static修饰；
            context.checker.check(
                element.enclosingTypeElement == null || element.isStatic(),
                element,
                INNER_CLASS_TYPE_CONVERTER_MUST_BE_STATIC
            )

            //typeConverters对象除非使用@ProvidedTypeConverter修饰，否则支持一下条件中的至少一个条件：
            //
            // - （1）typeConverters对象是`object`或`companion object`kotlin类型；
            // - （2）typeConverter方法必须全部static修饰；
            // - （3）typeConverters对象构造函数为空
            // - （4）typeConverters对象构造函数存在，但是参数为空；
            context.checker.check(
                isKotlinObjectDeclaration || allStatic || constructors.isEmpty() ||
                        constructors.any {
                            it.parameters.isEmpty()
                        },
                element, TYPE_CONVERTER_MISSING_NOARG_CONSTRUCTOR
            )
        }
        //只会处理@TypeConverter修饰的方法
        return converterMethods.mapNotNull {
            processMethod(
                container = element.type,//当前方法所在类
                isContainerKotlinObject = isKotlinObjectDeclaration,//方法所在类 object` or `companion object` in Kotlin
                methodElement = it,//@TypeConverter修饰的方法节点
                isProvidedConverter = isProvidedConverter//当前方法所在类是否使用了ProvidedTypeConverter修饰
            )
        }
    }

    private fun processMethod(
        container: XType,
        methodElement: XMethodElement,
        isContainerKotlinObject: Boolean,
        isProvidedConverter: Boolean
    ): CustomTypeConverter? {

        val asMember = methodElement.asMemberOf(container)
        val returnType = asMember.returnType
        val invalidReturnType = returnType.isInvalidReturnType()

        //该方法必须public修饰
        context.checker.check(
            methodElement.isPublic(), methodElement, TYPE_CONVERTER_MUST_BE_PUBLIC
        )
        //typeConverter方法返回类型不允许void(error ,none)类型
        if (invalidReturnType) {
            context.logger.e(methodElement, TYPE_CONVERTER_BAD_RETURN_TYPE)
            return null
        }
        val returnTypeName = returnType.typeName
        //typeConverter方法返回类型如果是泛型，必须是实体类型（如List<String>），不允许出现List< T>或List<?>类型；
        context.checker.notUnbound(
            returnTypeName, methodElement,
            TYPE_CONVERTER_UNBOUND_GENERIC
        )
        val params = methodElement.parameters
        //typeConverter方法参数必须有且仅有一个
        if (params.size != 1) {
            context.logger.e(methodElement, TYPE_CONVERTER_MUST_RECEIVE_1_PARAM)
            return null
        }
        val param = params.map {
            it.asMemberOf(container)
        }.first()
        //typeConverter方法参数类型如果是泛型，必须是实体类型（如List<String>），不允许出现List< T>或List<?>类型；
        context.checker.notUnbound(param.typeName, params[0], TYPE_CONVERTER_UNBOUND_GENERIC)

        return CustomTypeConverter(
            enclosingClass = container,//方法所在类
            isEnclosingClassKotlinObject = isContainerKotlinObject,//方法所在类 `object`或`companion object`kotlin类型
            method = methodElement,//@TypeConverter修饰的方法
            from = param,//方法参数：当前方法所在类
            to = returnType,//方法返回类型
            isProvidedConverter = isProvidedConverter//当前方法所在类是否使用了@ProvidedTypeConverter修饰
        )
    }

    /**
     * Order of classes is important hence they are a LinkedHashSet not a set.
     */
    open class ProcessResult(
        val classes: LinkedHashSet<XType>,//@TypeConverters#values
        val converters: List<CustomTypeConverterWrapper>,//@TypeConverters#values中的item类被@TypeConverter修饰的方法
        val builtInConverterFlags: BuiltInConverterFlags//@TypeConverters#builtInTypeConverters
    ) {
        object EMPTY : ProcessResult(
            classes = LinkedHashSet(),
            converters = emptyList(),
            builtInConverterFlags = BuiltInConverterFlags.DEFAULT
        )

        operator fun plus(other: ProcessResult): ProcessResult {
            val newClasses = LinkedHashSet<XType>()
            newClasses.addAll(classes)
            newClasses.addAll(other.classes)
            return ProcessResult(
                classes = newClasses,
                converters = converters + other.converters,
                builtInConverterFlags = other.builtInConverterFlags.withNext(
                    builtInConverterFlags
                )
            )
        }
    }
}
