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

    //当前传入的类型如果是Iterable或数组，那么返回的是Pari<xx,true>；xx表示当前item下的对象，如果该对象不是泛型类型；否则该对象如果是泛型类型，那么使用当前泛型类型（递归直到不存在泛型类型）
    // 否则返回Pari<xx,false>，xx表示当前的对象，如果该对象不是泛型类型；否则该对象如果是泛型类型，那么使用当前泛型类型（递归直到不存在泛型类型）
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
