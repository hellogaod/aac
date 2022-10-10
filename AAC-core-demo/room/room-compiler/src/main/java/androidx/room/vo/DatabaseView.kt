/*
 * Copyright 2018 The Android Open Source Project
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
import androidx.room.migration.bundle.DatabaseViewBundle
import androidx.room.parser.ParsedQuery
import androidx.room.compiler.processing.XTypeElement
import androidx.room.migration.bundle.VIEW_NAME_PLACEHOLDER

//@DatabaseView修饰的节点生成对象
class DatabaseView(
    element: XTypeElement,//@DatabaseView修饰的节点
    val viewName: String,//视图名称
    val query: ParsedQuery,//@DatabaseView#value查询语句生成的解析查询对象
    type: XType,//@DatabaseView修饰的节点类型
    fields: List<Field>,//@DatabaseView修饰的类下的所有有效节点
    embeddedFields: List<EmbeddedField>,//@DatabaseView修饰的类中使用@Embedded修饰的有效字段生成的对象
    constructor: Constructor?//@DatabaseView修饰的类构造函数
) : Pojo(element, type, fields, embeddedFields, emptyList(), constructor),
    HasSchemaIdentity,
    EntityOrView {

    override val tableName = viewName

    val createViewQuery by lazy {
        createViewQuery(viewName)
    }

    /**
     * List of all the underlying tables including those that are indirectly referenced.
     *
     * This is populated by DatabaseProcessor. This cannot be an immutable constructor parameter
     * as it can only be known after all the other views are initialized and parsed.
     */
    val tables = mutableSetOf<String>()

    fun toBundle() = DatabaseViewBundle(viewName, createViewQuery(VIEW_NAME_PLACEHOLDER))

    override fun getIdKey(): String {
        val identityKey = SchemaIdentityKey()
        identityKey.append(query.original)
        return identityKey.hash()
    }

    private fun createViewQuery(viewName: String): String {
        // This query should match exactly like it is stored in sqlite_master. The query is
        // trimmed. "IF NOT EXISTS" should not be included.
        return "CREATE VIEW `$viewName` AS ${query.original.trim()}"
    }
}
