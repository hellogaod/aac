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

import androidx.room.compiler.processing.XType
import androidx.room.migration.bundle.EntityBundle
import androidx.room.compiler.processing.XTypeElement
import androidx.room.migration.bundle.TABLE_NAME_PLACEHOLDER

/**
 * A Pojo with a mapping SQLite table.
 *
 * 带有映射 SQLite 表的 Pojo。
 */
open class Entity(
    element: XTypeElement,//@Entity修饰的节点，或@Entity和@AutoValue同时修饰生成的原先类_AutoValue节点
    override val tableName: String,//生成的表名
    type: XType,//用于创建Pojo的节点类型；如果节点同时使用@AutovAlue表示新生成的节点类型：Auto_原先节点类型；
    fields: List<Field>,//表常规字段和嵌入表常规字段
    embeddedFields: List<EmbeddedField>,//表嵌入字段
    val primaryKey: PrimaryKey,//主键
    val indices: List<Index>,//索引
    val foreignKeys: List<ForeignKey>,//外键
    constructor: Constructor?,//Pojo节点构造函数
    val shadowTableName: String?//null
) : Pojo(element, type, fields, embeddedFields, emptyList(), constructor),//
    HasSchemaIdentity,
    EntityOrView {

    open val createTableQuery by lazy {
        createTableQuery(tableName)
    }

    // a string defining the identity of this entity, which can be used for equality checks
    override fun getIdKey(): String {
        val identityKey = SchemaIdentityKey()
        identityKey.append(tableName)
        identityKey.append(primaryKey)
        identityKey.appendSorted(fields)
        identityKey.appendSorted(indices)
        identityKey.appendSorted(foreignKeys)
        return identityKey.hash()
    }

    //当前entity和entity中的表字段生成一段用于生成表和表字段的sql代码
    private fun createTableQuery(tableName: String): String {
        val definitions = (
            fields.map {
                val autoIncrement = primaryKey.autoGenerateId && primaryKey.fields.contains(it)
                it.databaseDefinition(autoIncrement)
            } + createPrimaryKeyDefinition() + createForeignKeyDefinitions()
            ).filterNotNull()
        return "CREATE TABLE IF NOT EXISTS `$tableName` (${definitions.joinToString(", ")})"
    }

    private fun createForeignKeyDefinitions(): List<String> {
        return foreignKeys.map { it.databaseDefinition() }
    }

    private fun createPrimaryKeyDefinition(): String? {
        return if (primaryKey.fields.isEmpty() || primaryKey.autoGenerateId) {
            null
        } else {
            val keys = primaryKey.columnNames.joinToString(", ") { "`$it`" }
            "PRIMARY KEY($keys)"
        }
    }

    fun shouldBeDeletedAfter(other: Entity): Boolean {
        return foreignKeys.any {
            it.parentTable == other.tableName &&
                (
                    (!it.deferred && it.onDelete == ForeignKeyAction.NO_ACTION) ||
                        it.onDelete == ForeignKeyAction.RESTRICT
                    )
        }
    }

    open fun toBundle(): EntityBundle = EntityBundle(
        tableName,
        createTableQuery(TABLE_NAME_PLACEHOLDER),
        fields.map { it.toBundle() },
        primaryKey.toBundle(),
        indices.map { it.toBundle() },
        foreignKeys.map { it.toBundle() }
    )

    fun isUnique(columns: List<String>): Boolean {
        return if (primaryKey.columnNames.size == columns.size &&
            primaryKey.columnNames.containsAll(columns)
        ) {
            true
        } else {
            indices.any { index ->
                index.unique &&
                    index.fields.size == columns.size &&
                    index.columnNames.containsAll(columns)
            }
        }
    }
}
