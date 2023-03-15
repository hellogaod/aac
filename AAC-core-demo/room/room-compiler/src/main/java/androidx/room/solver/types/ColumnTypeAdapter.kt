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

import androidx.room.parser.SQLTypeAffinity
import androidx.room.compiler.processing.XType
import com.squareup.javapoet.TypeName

/**
 * A code generator that can read a field from Cursor and write a field to a Statement
 *
 * 表字段类型适配：
 * （1）PrimitiveColumnTypeAdapter：7种基本类型
 * （2）BoxedPrimitiveColumnTypeAdapter：7种基本类型转换成包装类
 * （3）StringColumnTypeAdapter：String类型字段适配
 * （4）ByteArrayColumnTypeAdapter：byte数组类型字段适配
 * （5）ByteBufferColumnTypeAdapter：ByteBuffer类型字段适配
 * （6）EnumColumnTypeAdapter：枚举类型，排在类型转换之后；
 *  (7)UuidColumnTypeAdapter:UUID类型，排在类型转换之后。
 */
abstract class ColumnTypeAdapter(val out: XType, val typeAffinity: SQLTypeAffinity) :
    StatementValueBinder, CursorValueReader {
    val outTypeName: TypeName by lazy { out.typeName }
    override fun typeMirror() = out
    override fun affinity(): SQLTypeAffinity = typeAffinity
}
