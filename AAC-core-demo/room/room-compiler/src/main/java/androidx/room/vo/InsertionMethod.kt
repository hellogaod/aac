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

import androidx.room.OnConflictStrategy
import androidx.room.compiler.processing.XMethodElement
import androidx.room.compiler.processing.XType
import androidx.room.solver.shortcut.binder.InsertMethodBinder

data class InsertionMethod(
    val element: XMethodElement,//insert方法节点
    @OnConflictStrategy val onConflict: Int,//@Insert#onConflict
    //insert方法参数生成的entity对象；如果@Insert#entity属性存在并且和方法参数（如果参数是数组或集合，那么是item类型）对象不匹配，当前表示@Insert#entity属性对象生成的entity对象
    val entities: Map<String, ShortcutEntity>,
    val returnType: XType,//普通方法表示方法返回类型，suspend方法：suspend方法最后一个参数，该参数的第一个泛型类型
    val parameters: List<ShortcutQueryParameter>,//insert方法参数生成的对象
    val methodBinder: InsertMethodBinder//方法返回类型匹配的binder，在solver.shortcut.binder包下
)