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
import androidx.room.ext.T
import androidx.room.compiler.processing.XType
import androidx.room.solver.CodeGenScope

/**
 * A column adapter that uses a type converter to do the conversion. The type converter may be
 * a composite one.
 *
 * （1）PrimitiveBooleanToIntConverter：系统自动转换int <-> boolean；
 * （2）BoxedBooleanToBoxedIntConverter：系统自动转换Integer <-> Boolean；
 *
 * 当前字段适配通过类型转换：
 *
 */
class CompositeAdapter(
    out: XType,//需要做类型转换的类型
    val columnTypeAdapter: ColumnTypeAdapter,//类型转换适配的数据库字段
    val intoStatementConverter: TypeConverter?,//类型转入Room
    val fromCursorConverter: TypeConverter?//类型转出Room
) :
    ColumnTypeAdapter(out, columnTypeAdapter.typeAffinity) {
    override fun readFromCursor(
        outVarName: String,
        cursorVarName: String,
        indexVarName: String,
        scope: CodeGenScope
    ) {
        if (fromCursorConverter == null) {
            return
        }
        scope.builder().apply {
            val tmpCursorValue = scope.getTmpVar()
            addStatement("final $T $L", columnTypeAdapter.outTypeName, tmpCursorValue)
            columnTypeAdapter.readFromCursor(tmpCursorValue, cursorVarName, indexVarName, scope)
            fromCursorConverter.convert(tmpCursorValue, outVarName, scope)
        }
    }

    override fun bindToStmt(
        stmtName: String,
        indexVarName: String,
        valueVarName: String,
        scope: CodeGenScope
    ) {
        if (intoStatementConverter == null) {
            return
        }
        scope.builder().apply {
            val bindVar = intoStatementConverter.convert(valueVarName, scope)
            columnTypeAdapter.bindToStmt(stmtName, indexVarName, bindVar, scope)
        }
    }
}
