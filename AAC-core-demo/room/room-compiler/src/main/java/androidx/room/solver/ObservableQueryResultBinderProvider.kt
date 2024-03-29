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

package androidx.room.solver

import androidx.room.compiler.processing.XType
import androidx.room.parser.ParsedQuery
import androidx.room.processor.Context
import androidx.room.processor.ProcessorErrors
import androidx.room.solver.query.result.QueryResultAdapter
import androidx.room.solver.query.result.QueryResultBinder

/**
 * Binder provider class that has common functionality for observables.
 */
abstract class ObservableQueryResultBinderProvider(val context: Context) :
    QueryResultBinderProvider {
    protected abstract fun extractTypeArg(declared: XType): XType
    protected abstract fun create(
        typeArg: XType,
        resultAdapter: QueryResultAdapter?,
        tableNames: Set<String>
    ): QueryResultBinder

    final override fun provide(
        declared: XType,
        query: ParsedQuery,
        extras: TypeAdapterExtras
    ): QueryResultBinder {
        val typeArg = extractTypeArg(declared)
        val adapter = context.typeAdapterStore.findQueryResultAdapter(typeArg, query, extras)

        val tableNames = (
            (adapter?.accessedTableNames() ?: emptyList()) +
                query.tables.map { it.name }
            ).toSet()

        //方法返回类型是 LiveData, Flowable , DataSource, DataSourceFactory等，必须通过直接select查询（或间接使用@Relection）一起使用；
        //如果是@RawQuery，应该通过observedEntities属性指定要查询的表信息
        if (tableNames.isEmpty()) {
            context.logger.e(ProcessorErrors.OBSERVABLE_QUERY_NOTHING_TO_OBSERVE)
        }
        return create(
            typeArg = typeArg,
            resultAdapter = adapter,
            tableNames = tableNames
        )
    }
}