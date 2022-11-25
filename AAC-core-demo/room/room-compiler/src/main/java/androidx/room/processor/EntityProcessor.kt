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

package androidx.room.processor

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Fts3
import androidx.room.Fts4
import androidx.room.compiler.processing.XAnnotationBox
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XTypeElement
import androidx.room.vo.ForeignKeyAction
import androidx.room.vo.Index

interface EntityProcessor : EntityOrViewProcessor {
    override fun process(): androidx.room.vo.Entity

    companion object {
        //表名：@Entity#tableName属性值如果存在，则使用该属性值；否则使用@Entity修饰的类名
        fun extractTableName(element: XTypeElement, annotation: Entity): String {
            return if (annotation.tableName == "") {
                element.name
            } else {
                annotation.tableName
            }
        }

        fun extractIndices(
            annotation: XAnnotationBox<Entity>,
            tableName: String
        ): List<IndexInput> {
            //索引
            return annotation.getAsAnnotationBoxArray<androidx.room.Index>("indices").map {
                val indexAnnotation = it.value
                val nameValue = indexAnnotation.name
                val columns = indexAnnotation.value.asList()
                val orders = indexAnnotation.orders.asList()
                //如果当前所有不存在名称，根据规则新建一个
                val name = if (nameValue == "") {
                    createIndexName(columns, tableName)
                } else {
                    nameValue
                }
                IndexInput(name, indexAnnotation.unique, columns, orders)
            }
        }

        fun createIndexName(columnNames: List<String>, tableName: String): String {
            return Index.DEFAULT_PREFIX + tableName + "_" + columnNames.joinToString("_")
        }

        fun extractForeignKeys(annotation: XAnnotationBox<Entity>): List<ForeignKeyInput> {
            //外键 @Entity#ForeignKey
            return annotation.getAsAnnotationBoxArray<ForeignKey>("foreignKeys")
                .mapNotNull { annotationBox ->
                    val foreignKey = annotationBox.value
                    //当前表的外键指引的表
                    val parent = annotationBox.getAsType("entity")
                    if (parent != null) {
                        ForeignKeyInput(
                            parent = parent,
                            parentColumns = foreignKey.parentColumns.asList(),
                            childColumns = foreignKey.childColumns.asList(),
                            onDelete = ForeignKeyAction.fromAnnotationValue(foreignKey.onDelete),
                            onUpdate = ForeignKeyAction.fromAnnotationValue(foreignKey.onUpdate),
                            deferred = foreignKey.deferred
                        )
                    } else {
                        null
                    }
                }
        }
    }
}

/**
 * Processed Index annotation output.
 *
 * 转换成索引对象输出
 */
data class IndexInput(
    val name: String,//索引名称
    val unique: Boolean,//是否唯一索引
    val columnNames: List<String>,//索引的表字段
    val orders: List<androidx.room.Index.Order>//索引排序：升序还是降序
)

/**
 * ForeignKey, before it is processed in the context of a database.
 *
 * 外键对象
 */
data class ForeignKeyInput(
    val parent: XType,//当前表的外键指引的表
    val parentColumns: List<String>,//外键指引的表中的字段
    val childColumns: List<String>,//当前表中的字段
    val onDelete: ForeignKeyAction?,//外键所在父级表中的表字段被删除，当前外键的行为
    val onUpdate: ForeignKeyAction?,//外键所在父级表中的表字段被修改，当前外键的行为
    val deferred: Boolean//外键约束是否根据事务延时处理
)

fun EntityProcessor(
    context: Context,
    element: XTypeElement,
    referenceStack: LinkedHashSet<String> = LinkedHashSet()
): EntityProcessor {
    return if (element.hasAnyAnnotation(Fts3::class, Fts4::class)) {
        FtsTableEntityProcessor(context, element, referenceStack)
    } else {
        TableEntityProcessor(context, element, referenceStack)
    }
}