/*
 * Copyright 2020 The Android Open Source Project
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

package androidx.room.compiler.processing.ksp

import androidx.room.compiler.processing.XRawType
import androidx.room.compiler.processing.rawTypeName
import com.squareup.javapoet.TypeName

internal class KspRawType constructor(
    private val original: KspType
) : XRawType {
    private val ksType by lazy {
        original.ksType.starProjection().makeNotNullable()
    }

    override val typeName: TypeName by lazy {
        original.typeName.rawTypeName()
    }

    override fun isAssignableFrom(other: XRawType): Boolean {
        check(other is KspRawType)
        return ksType.isAssignableFrom(other.ksType)
    }

    override fun equals(other: Any?): Boolean {
        return this === other || typeName == (other as? XRawType)?.typeName
    }

    override fun hashCode(): Int {
        return typeName.hashCode()
    }

    override fun toString(): String {
        return typeName.toString()
    }
}
