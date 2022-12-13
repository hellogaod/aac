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

package androidx.room.vo

import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XVariableElement

/**
 * Parameters used in DAO methods that are annotated with Insert, Delete, Update.
 */
data class ShortcutQueryParameter(
    val element: XVariableElement,//当前dao方法参数节点
    val name: String,//当前dao方法参数名称
    //actual param type (List<Foo>, Set<Foo>, Foo, etc...)
    val type: XType, // 当前到方法参数类型，
    //方法参数中包裹的实际类型：1.如果是集合或数组，使用当前item类型（如果item是泛型，那么剥离直到不再存在泛型类型为止）；2.如果不是集合或数组，直接使用当前参数类型（同样的，剥离泛型直到不存在泛型为止）
    val pojoType: XType?, //extracted type, never a Collection
    val isMultiple: Boolean//当前方法参数是否是Iterable类型集合或数组
) {
    /**
     * Method name in entity insertion or update adapter.
     */
    fun handleMethodName(): String {
        return if (isMultiple) {
            "handleMultiple"
        } else {
            "handle"
        }
    }
}
