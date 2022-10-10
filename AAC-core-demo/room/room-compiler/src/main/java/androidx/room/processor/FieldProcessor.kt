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

package androidx.room.processor

import androidx.room.ColumnInfo
import androidx.room.compiler.processing.XFieldElement
import androidx.room.compiler.processing.XType
import androidx.room.parser.Collate
import androidx.room.parser.SQLTypeAffinity
import androidx.room.vo.EmbeddedField
import androidx.room.vo.Field
import java.util.Locale
//@Entity修饰的类中的变量处理
class FieldProcessor(
    baseContext: Context,
    val containing: XType,
    val element: XFieldElement,
    val bindingScope: BindingScope,
    val fieldParent: EmbeddedField?, // pass only if this is processed as a child of Embedded field
    val onBindingError: (field: Field, errorMsg: String) -> Unit
) {
    val context = baseContext.fork(element)
    fun process(): Field {
        val member = element.asMemberOf(containing)
        val type = member.typeName
        val columnInfo = element.getAnnotation(ColumnInfo::class)?.value
        val name = element.name

        val rawCName = if (columnInfo != null && columnInfo.name != ColumnInfo.INHERIT_FIELD_NAME) {
            columnInfo.name
        } else {
            name
        }
        //字段名称规则：@Entity修饰的类（如果该类还是用了@AutoValue修饰，那么处理的是`AutoValue_原先类`）的有效变量，如果变量使用了@ColumInfo修饰并且name没有使用默认"[field-name]"，
        // 那么当前@ColumInfo#name作为变量名；否则当前有效变量作为变量名
        val columnName = (fieldParent?.prefix ?: "") + rawCName
        val affinity = try {
            SQLTypeAffinity.fromAnnotationValue(columnInfo?.typeAffinity)
        } catch (ex: NumberFormatException) {
            null
        }

        context.checker.notBlank(
            columnName, element,
            ProcessorErrors.COLUMN_NAME_CANNOT_BE_EMPTY
        )
        //有效变量支持泛型，但是泛型中的类型必须是实体类型（例如List<T>肯定不行，必须使用List<String>）
        context.checker.notUnbound(
            type, element,
            ProcessorErrors.CANNOT_USE_UNBOUND_GENERICS_IN_ENTITY_FIELDS
        )

        val adapter = context.typeAdapterStore.findColumnTypeAdapter(
            member,
            affinity,
            skipDefaultConverter = false
        )

        val adapterAffinity = adapter?.typeAffinity ?: affinity

        //使用@NonNull修饰的字段
        val nonNull = Field.calcNonNull(member, fieldParent)

        val field = Field(
            name = name,
            type = member,
            element = element,
            columnName = columnName,
            affinity = affinity,
            collate = Collate.fromAnnotationValue(columnInfo?.collate),
            defaultValue = extractDefaultValue(
                columnInfo?.defaultValue, adapterAffinity, nonNull
            ),
            parent = fieldParent,
            indexed = columnInfo?.index ?: false,
            nonNull = nonNull
        )

        when (bindingScope) {
            BindingScope.TWO_WAY -> {
                field.statementBinder = adapter
                field.cursorValueReader = adapter
                field.affinity = adapterAffinity
                if (adapter == null) {
                    onBindingError(field, ProcessorErrors.CANNOT_FIND_COLUMN_TYPE_ADAPTER)
                }
            }
            BindingScope.BIND_TO_STMT -> {
                field.statementBinder = context.typeAdapterStore
                    .findStatementValueBinder(field.type, field.affinity)
                if (field.statementBinder == null) {
                    onBindingError(field, ProcessorErrors.CANNOT_FIND_STMT_BINDER)
                }
            }
            BindingScope.READ_FROM_CURSOR -> {
                field.cursorValueReader = context.typeAdapterStore
                    .findCursorValueReader(field.type, field.affinity)
                if (field.cursorValueReader == null) {
                    onBindingError(field, ProcessorErrors.CANNOT_FIND_CURSOR_READER)
                }
            }
        }

        return field
    }

    private fun extractDefaultValue(
        value: String?,
        affinity: SQLTypeAffinity?,
        fieldNonNull: Boolean
    ): String? {
        if (value == null) {
            return null
        }
        val trimmed = value.trim().lowercase(Locale.ENGLISH)
        val defaultValue = if (affinity == SQLTypeAffinity.TEXT) {
            if (value == ColumnInfo.VALUE_UNSPECIFIED) {
                null
            } else if (trimmed.startsWith("(") || trimmed in SQLITE_VALUE_CONSTANTS) {
                value
            } else {
                "'${value.trim('\'')}'"
            }
        } else {
            if (value == ColumnInfo.VALUE_UNSPECIFIED || trimmed == "") {
                null
            } else {
                value
            }
        }
        if (trimmed == "null" && fieldNonNull) {
            context.logger.e(element, ProcessorErrors.DEFAULT_VALUE_NULLABILITY)
        }
        return defaultValue
    }

    /**
     * Defines what we need to assign
     */
    enum class BindingScope {
        TWO_WAY, // both bind and read.
        BIND_TO_STMT, // just value to statement
        READ_FROM_CURSOR // just cursor to value
    }
}

internal val SQLITE_VALUE_CONSTANTS = listOf(
    "null",
    "current_time",
    "current_date",
    "current_timestamp",
    "true",
    "false"
)
