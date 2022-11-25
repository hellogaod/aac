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

package androidx.room.vo

import androidx.room.OnConflictStrategy
import androidx.room.compiler.processing.XMethodElement
import androidx.room.solver.shortcut.binder.DeleteOrUpdateMethodBinder

class UpdateMethod(
    element: XMethodElement,//update方法节点
    //update方法参数生成的entity对象；如果@Update#entity属性存在并且和方法参数（如果参数是数组或集合，那么是item类型）对象不匹配，entity属性对象生成的entity对象
    entities: Map<String, ShortcutEntity>,
    parameters: List<ShortcutQueryParameter>,//update方法参数生成的对象
    methodBinder: DeleteOrUpdateMethodBinder?,//update方法匹配绑定类型
    @OnConflictStrategy val onConflictStrategy: Int//@Update#onConflict
) : ShortcutMethod(element, entities, parameters, methodBinder)
