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

package androidx.room.solver.query.parameter

import androidx.room.solver.CodeGenScope

/**
 * Knows how to convert a query parameter into query arguments.
 *
 * 了解如何将查询参数转换为参数
 */
abstract class QueryParameterAdapter(val isMultiple: Boolean) {
    /**
     * Must bind the value into the statement at the given index.
     *
     * 必须将值绑定到给定index处的语句中。
     */
    abstract fun bindToStmt(
        inputVarName: String,
        stmtVarName: String,
        startIndexVarName: String,
        scope: CodeGenScope
    )

    /**
     * Should declare and set the given value with the count
     *
     * 应使用计数声明并设置给定值
     */
    abstract fun getArgCount(inputVarName: String, outputVarName: String, scope: CodeGenScope)
}
