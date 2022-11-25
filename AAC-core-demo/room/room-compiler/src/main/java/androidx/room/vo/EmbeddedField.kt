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

import androidx.room.compiler.processing.XNullability

/**
 * Used when a field is embedded inside an Entity or Pojo.
 *
 * @Embedded修饰的有效字段生成EmbeddedField
 */
// used in cache matching, must stay as a data class or implement equals
data class EmbeddedField(
    val field: Field,//@Embedded修饰的有效字段生成的Field对象
    val prefix: String = "",//前缀
    val parent: EmbeddedField?//如果当前embedded节点的父节点还是embedded节点，该父embedded节点表示的对象
) {
    val getter by lazy { field.getter }
    val setter by lazy { field.setter }
    val nonNull = field.type.nullability == XNullability.NONNULL
    lateinit var pojo: Pojo//@Embedded修饰的有效字段类型生成的Pojo对象
    val mRootParent: EmbeddedField by lazy {
        parent?.mRootParent ?: this
    }

    fun isNonNullRecursively(): Boolean {
        return field.nonNull && (parent == null || parent.isNonNullRecursively())
    }
}
