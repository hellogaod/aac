/*
 * Copyright (C) 2020 The Android Open Source Project
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

package androidx.room.compiler.processing

/**
 * This wraps an annotation element that is both accessible from the processor and runtime.
 *
 * 这包装了一个注释元素，可以从处理器和运行时访问。
 *
 * It won't scale to a general purpose processing APIs where an equivalent of the AnnotationMirror
 * API needs to be provided but works well for Room's case.
 */
interface XAnnotationBox<T> {
    /**
     * The value field of the annotation
     */
    val value: T

    /**
     * Returns the value of the given [methodName] as a type reference.
     */
    fun getAsType(methodName: String): XType?

    /**
     * Returns the value of the given [methodName] as a list of type references.
     */
    fun getAsTypeList(methodName: String): List<XType>

    /**
     * Returns the value of the given [methodName] as another boxed annotation.
     */
    fun <T : Annotation> getAsAnnotationBox(methodName: String): XAnnotationBox<T>

    /**
     * Returns the value of the given [methodName] as an array of boxed annotations.
     */
    fun <T : Annotation> getAsAnnotationBoxArray(methodName: String): Array<XAnnotationBox<T>>
}
