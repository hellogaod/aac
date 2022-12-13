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

import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XVariableElement
import androidx.room.compiler.processing.isArray
import androidx.room.vo.ShortcutQueryParameter

/**
 * Processes parameters of methods that are annotated with Insert, Update or Delete.
 *
 * dao方法参数处理
 */
class ShortcutParameterProcessor(
    baseContext: Context,
    val containing: XType,
    val element: XVariableElement
) {
    val context = baseContext.fork(element)
    fun process(): ShortcutQueryParameter {
        val asMember = element.asMemberOf(containing)
        val name = element.name
        //dao方法参数不允许使用"_"开头命名
        context.checker.check(
            !name.startsWith("_"), element,
            ProcessorErrors.QUERY_PARAMETERS_CANNOT_START_WITH_UNDERSCORE
        )

        val (pojoType, isMultiple) = extractPojoType(asMember)
        return ShortcutQueryParameter(
            element = element,
            name = name,
            type = asMember,
            pojoType = pojoType,
            isMultiple = isMultiple
        )
    }

    //1. 如果typeMirror是Iterable集合（如果是集合必须是Iterable类型，否则报错）：返回的是Pari<xx,true>，xx表示当前数组item类型（如果item还是一个泛型，那么继续剥离获取第一个泛型类型，直到不存在泛型类型为止）
    //2. 如果typeMirror是数组：返回的是Pari<xx,true>，xx表示数组item类型（如果item还是一个泛型，那么继续剥离获取第一个泛型类型，直到不存在泛型类型为止）
    //3. 不满足条件1和2的情况下：返回的是Pari<xx,false>,xx表示参数实际对象类型（如果参数类型还是一个泛型，那么继续剥离获取第一个泛型类型，直到不存在泛型类型为止）
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    private fun extractPojoType(typeMirror: XType): Pair<XType?, Boolean> {

        val processingEnv = context.processingEnv

        //如果是pojoType是泛型类型，生成的Pair对象的第一个参数使用当前泛型类型；否则直接使用pojoType
        fun verifyAndPair(pojoType: XType, isMultiple: Boolean): Pair<XType?, Boolean> {
            // kotlin may generate ? extends T so we should reduce it.
            val boundedVar = pojoType.extendsBound()
            return if (boundedVar != null) {
                verifyAndPair(boundedVar, isMultiple)
            } else {
                Pair(pojoType, isMultiple)
            }
        }

        //当前对象的非private iterator方法，该方法返回类型的第一个泛型参数类型
        fun extractPojoTypeFromIterator(iterableType: XType): XType {
            //如果参数是集合类型，那么当前参数类型必须是Iterable类型；
            iterableType.typeElement!!.getAllNonPrivateInstanceMethods().forEach {
                if (it.jvmName == "iterator") {
                    return it.asMemberOf(iterableType)
                        .returnType
                        .typeArguments
                        .first()
                }
            }
            throw IllegalArgumentException("iterator() not found in Iterable $iterableType")
        }

        val iterableType = processingEnv
            .requireType("java.lang.Iterable").rawType
        if (iterableType.isAssignableFrom(typeMirror)) {
            val pojo = extractPojoTypeFromIterator(typeMirror)
            return verifyAndPair(pojo, true)
        }
        if (typeMirror.isArray()) {
            val pojo = typeMirror.componentType
            return verifyAndPair(pojo, true)
        }
        return verifyAndPair(typeMirror, false)
    }
}
