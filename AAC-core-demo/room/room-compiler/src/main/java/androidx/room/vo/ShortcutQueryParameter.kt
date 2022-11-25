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

    //1.先判断当前方法参数类型是否集合或数组，如果是使用item类型；否则直接使用dao方法参数类型；
    //2.根据1的类型，判断是否是泛型类型，如果是获取泛型类型（递归深入查询，直到不存在泛型类型），使用该泛型类型；如果不存在泛型类型，那么直接使用1中的类型
    val pojoType: XType?, //extracted type, never a Collection
    val isMultiple: Boolean//当前方法是否是list集合或数组
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
