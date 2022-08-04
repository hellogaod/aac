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
 * Processing step to simplify processing a set of annotations.
 */
interface XProcessingStep {
    /**
     * The implementation of processing logic for the step. It is guaranteed that the keys in
     * [elementsByAnnotation] will be a subset of the set returned by [annotations].
     *
     * @return the elements (a subset of the values of [elementsByAnnotation]) that this step
     *     is unable to process, possibly until a later processing round. These elements will be
     *     passed back to this step at the next round of processing.
     */
    @Deprecated(
        message = "We're combining processOver() and this process() overload.",
        replaceWith = ReplaceWith(
            "process(XProcessingEnv, Map<String, Set<XElement>>, Boolean)"),
        level = DeprecationLevel.WARNING
    )
    fun process(
        env: XProcessingEnv,
        elementsByAnnotation: Map<String, Set<XElement>>
    ): Set<XElement> = emptySet()

    /**
     * The implementation of processing logic for the step. It is guaranteed that the keys in
     * [elementsByAnnotation] will be a subset of the set returned by [annotations].
     *
     * @return the elements (a subset of the values of [elementsByAnnotation]) that this step
     *     is unable to process, possibly until a later processing round. These elements will be
     *     passed back to this step at the next round of processing.
     */
    @Suppress("deprecation")
    fun process(
        env: XProcessingEnv,
        elementsByAnnotation: Map<String, Set<XElement>>,
        isLastRound: Boolean
    ): Set<XElement> = if (isLastRound) {
        processOver(env, elementsByAnnotation)
        emptySet()
    } else {
        process(env, elementsByAnnotation)
    }

    /**
     * An optional hook for logic to be executed in the last round of processing.
     *
     * Unlike [process], the elements in [elementsByAnnotation] are not validated and are those
     * that have been kept being deferred.
     *
     * @see [XRoundEnv.isProcessingOver]
     */
    @Deprecated(
        message = "We're combining processOver() and the original process().",
        replaceWith = ReplaceWith(
            "process(XProcessingEnv, Map<String, Set<XElement>>, Boolean)"),
        level = DeprecationLevel.WARNING
    )
    fun processOver(env: XProcessingEnv, elementsByAnnotation: Map<String, Set<XElement>>) { }

    /**
     * The set of annotation qualified names processed by this step.
     */
    fun annotations(): Set<String>
}
