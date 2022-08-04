/*
 * Copyright 2019 The Android Open Source Project
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

@file:Suppress("DEPRECATED_JAVA_ANNOTATION")

package androidx.annotation

import java.lang.annotation.ElementType
import kotlin.annotation.Retention
import kotlin.annotation.Target
import kotlin.reflect.KClass

/**
 * Allows use of an opt-in API denoted by the given markers in the annotated file, declaration,
 * or expression. If a declaration is annotated with [OptIn], its usages are **not** required to
 * opt-in to that API.
 */
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FILE,
    AnnotationTarget.TYPEALIAS
)
@java.lang.annotation.Target(
    ElementType.CONSTRUCTOR,
    ElementType.FIELD,
    ElementType.LOCAL_VARIABLE,
    ElementType.METHOD,
    ElementType.PACKAGE,
    ElementType.TYPE,
)
public annotation class OptIn(
    /**
     * Defines the opt-in API(s) whose usage this annotation allows.
     */
    @get:Suppress("ArrayReturn") // Kotlin generates a raw array for annotation vararg
    vararg val markerClass: KClass<out Annotation>
)
