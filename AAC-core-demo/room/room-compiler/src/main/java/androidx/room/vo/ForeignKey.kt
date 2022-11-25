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

import androidx.room.migration.bundle.ForeignKeyBundle

/**
 * Keeps information about a foreign key.
 *
 * 外键
 */
data class ForeignKey(
    val parentTable: String,//外键指向的表（外键表）名
    val parentColumns: List<String>,//外键表常规字段
    val childFields: List<Field>,//@ForeignKey#childColumns属性
    val onDelete: ForeignKeyAction,
    val onUpdate: ForeignKeyAction,
    val deferred: Boolean//@ForeignKey#deferred属性
) : HasSchemaIdentity {
    override fun getIdKey(): String {
        return parentTable +
                "-${parentColumns.joinToString(",")}" +
                "-${childFields.joinToString(",") { it.columnName }}" +
                "-${onDelete.sqlName}" +
                "-${onUpdate.sqlName}" +
                "-$deferred"
    }

    fun databaseDefinition(): String {
        return "FOREIGN KEY(${joinEscaped(childFields.map { it.columnName })})" +
                " REFERENCES `$parentTable`(${joinEscaped(parentColumns)})" +
                " ON UPDATE ${onUpdate.sqlName}" +
                " ON DELETE ${onDelete.sqlName}" +
                " ${deferredDeclaration()}"
    }

    private fun deferredDeclaration(): String {
        return if (deferred) {
            "DEFERRABLE INITIALLY DEFERRED"
        } else {
            ""
        }
    }

    private fun joinEscaped(values: Iterable<String>) = values.joinToString(", ") { "`$it`" }

    fun toBundle(): ForeignKeyBundle = ForeignKeyBundle(
        parentTable, onDelete.sqlName, onUpdate.sqlName,
        childFields.map { it.columnName },
        parentColumns
    )
}
