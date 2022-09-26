/*
 * Copyright 2021 The Android Open Source Project
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

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Type elements that represent Enum declarations.
 *
 * 枚举声明的类型节点
 */
interface XEnumTypeElement : XTypeElement {
    val entries: Set<XEnumEntry>

    override fun getEnclosedElements(): List<XElement> {
        return super.getEnclosedElements() + entries
    }
}

@ExperimentalContracts
fun XTypeElement.isEnum(): Boolean {
    contract {
        returns(true) implies (this@isEnum is XEnumTypeElement)
    }
    return this is XEnumTypeElement
}
