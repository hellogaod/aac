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

import androidx.room.compiler.processing.XFieldElement
import androidx.room.compiler.processing.XNullability
import androidx.room.compiler.processing.XType
import androidx.room.ext.capitalize
import androidx.room.ext.decapitalize
import androidx.room.migration.bundle.FieldBundle
import androidx.room.parser.Collate
import androidx.room.parser.SQLTypeAffinity
import androidx.room.solver.types.CursorValueReader
import androidx.room.solver.types.StatementValueBinder
import com.squareup.javapoet.TypeName
import java.util.Locale

// used in cache matching, must stay as a data class or implement equals
//表字段数据对象
data class Field(
    val element: XFieldElement,//表字段节点
    val name: String,//当前变量名称
    val type: XType,//表字段类型
    var affinity: SQLTypeAffinity?,//@ColumnInfo#typeAffinity转换表字段偏向类型
    val collate: Collate? = null,//字段的排序规则，将在构建数据库时使用。
    val columnName: String = name,//表字段名
    val defaultValue: String? = null,//表字段默认值
    // null here means that this field does not belong to parent, instead, it belongs to an
    // embedded child of the main Pojo
    val parent: EmbeddedField? = null,//@Embedded修饰的变量才会出现该对象
    // index might be removed when being merged into an Entity
    var indexed: Boolean = false,//表字段是否创建了索引
    /** Whether the table column for this field should be NOT NULL */
    val nonNull: Boolean = calcNonNull(type, parent)//是否允许null
) : HasSchemaIdentity {
    lateinit var getter: FieldGetter
    lateinit var setter: FieldSetter
    // binds the field into a statement 存入字段
    var statementBinder: StatementValueBinder? = null
    // reads this field from a cursor column 读取字段
    var cursorValueReader: CursorValueReader? = null
    val typeName: TypeName by lazy { type.typeName }

    override fun getIdKey(): String {
        return buildString {
            // we don't get the collate information from sqlite so ignoring it here.
            append("$columnName-${affinity?.name ?: SQLTypeAffinity.TEXT.name}-$nonNull")
            // defaultValue was newly added; it should affect the ID only when it is used.
            if (defaultValue != null) {
                append("-defaultValue=$defaultValue")
            }
        }
    }

    /**
     * Used when reporting errors on duplicate names
     */
    fun getPath(): String {
        return if (parent == null) {
            name
        } else {
            "${parent.field.getPath()} > $name"
        }
    }

    private val pathWithDotNotation: String by lazy {
        if (parent == null) {
            name
        } else {
            "${parent.field.pathWithDotNotation}.$name"
        }
    }

    /**
     * List of names that include variations.
     * e.g. if it is mUser, user is added to the list
     * or if it is isAdmin, admin is added to the list
     *
     * 前缀如 _ m is has去掉
     */
    val nameWithVariations by lazy {
        val result = arrayListOf(name)
        if (name.length > 1) {
            if (name.startsWith('_')) {
                result.add(name.substring(1))
            }
            if (name.startsWith("m") && name[1].isUpperCase()) {
                result.add(name.substring(1).decapitalize(Locale.US))
            }

            if (typeName == TypeName.BOOLEAN || typeName == TypeName.BOOLEAN.box()) {
                if (name.length > 2 && name.startsWith("is") && name[2].isUpperCase()) {
                    result.add(name.substring(2).decapitalize(Locale.US))
                }
                if (name.length > 3 && name.startsWith("has") && name[3].isUpperCase()) {
                    result.add(name.substring(3).decapitalize(Locale.US))
                }
            }
        }
        result
    }

    val getterNameWithVariations by lazy {
        nameWithVariations.map { "get${it.capitalize(Locale.US)}" } +
            if (typeName == TypeName.BOOLEAN || typeName == TypeName.BOOLEAN.box()) {
                nameWithVariations.flatMap {
                    listOf("is${it.capitalize(Locale.US)}", "has${it.capitalize(Locale.US)}")
                }
            } else {
                emptyList()
            }
    }

    val setterNameWithVariations by lazy {
        nameWithVariations.map { "set${it.capitalize(Locale.US)}" }
    }

    /**
     * definition to be used in create query
     */
    fun databaseDefinition(autoIncrementPKey: Boolean): String {
        val columnSpec = StringBuilder("")
        if (autoIncrementPKey) {
            columnSpec.append(" PRIMARY KEY AUTOINCREMENT")
        }
        if (nonNull) {
            columnSpec.append(" NOT NULL")
        }
        if (collate != null) {
            columnSpec.append(" COLLATE ${collate.name}")
        }
        if (defaultValue != null) {
            columnSpec.append(" DEFAULT $defaultValue")
        }
        return "`$columnName` ${(affinity ?: SQLTypeAffinity.TEXT).name}$columnSpec"
    }

    fun toBundle(): FieldBundle = FieldBundle(
        pathWithDotNotation, columnName,
        affinity?.name ?: SQLTypeAffinity.TEXT.name, nonNull, defaultValue
    )

    companion object {
        fun calcNonNull(type: XType, parent: EmbeddedField?): Boolean {
            return XNullability.NONNULL == type.nullability &&
                (parent == null || parent.isNonNullRecursively())
        }
    }
}
