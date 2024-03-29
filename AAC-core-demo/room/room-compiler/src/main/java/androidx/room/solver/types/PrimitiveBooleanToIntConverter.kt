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

package androidx.room.solver.types

import androidx.room.ext.L
import androidx.room.compiler.processing.XProcessingEnv
import androidx.room.solver.CodeGenScope
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.TypeName

/**
 * int to boolean adapter.
 *
 * 基础类型int转换成boolean转换器
 */
object PrimitiveBooleanToIntConverter {
    fun create(processingEnvironment: XProcessingEnv): List<TypeConverter> {
        val tBoolean = processingEnvironment.requireType(TypeName.BOOLEAN)
        val tInt = processingEnvironment.requireType(TypeName.INT)
        return listOf(
            object : SingleStatementTypeConverter(tBoolean, tInt) {
                override fun buildStatement(inputVarName: String, scope: CodeGenScope): CodeBlock {
                    return CodeBlock.of(
                        "$L ? 1 : 0", inputVarName
                    )
                }
            },
            object : SingleStatementTypeConverter(tInt, tBoolean) {
                override fun buildStatement(inputVarName: String, scope: CodeGenScope): CodeBlock {
                    return CodeBlock.of(
                        "$L != 0", inputVarName
                    )
                }
            }
        )
    }
}
