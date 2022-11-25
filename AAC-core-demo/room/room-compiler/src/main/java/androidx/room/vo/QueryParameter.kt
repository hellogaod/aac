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
import androidx.room.solver.query.parameter.QueryParameterAdapter

/**
 * Holds the parameter for a {@link QueryMethod}.
 *
 * 查询字段参数
 */
data class QueryParameter(
    // this is name seen by java
    val name: String,//Dao方法参数名
    // this is the name used in the query. Might be different for kotlin queries
    val sqlName: String,//在query使用，默认是Dao方法参数名
    val type: XType,//Dao方法参数类型
    val queryParamAdapter: QueryParameterAdapter?//Dao方法参数适配，有collection，数组，其他。在solver.query.parameter
)
