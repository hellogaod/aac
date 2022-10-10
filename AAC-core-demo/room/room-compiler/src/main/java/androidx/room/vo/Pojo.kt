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
import androidx.room.compiler.processing.XTypeElement
import androidx.room.processor.DatabaseViewProcessor
import androidx.room.processor.EntityProcessor
import com.squareup.javapoet.TypeName

/**
 * A class is turned into a Pojo if it is used in a query response.
 *
 * 如果在查询响应中使用一个类，它就会变成一个 Pojo。
 */
open class Pojo(
    val element: XTypeElement,//@Entity修饰的节点（如果同时与@AutovAlue一起使用，表示新生成的节点：Auto_原先节点）表节点
    val type: XType,//表节点类型
    fields: List<Field>,//当前@Entity修饰的有效字段被@ColumnInfo修饰（或没有被@Embedded、@ColumnInfo和@Relation修饰） + @Embedded修饰的有效字段类型中的所有有效字段
    val embeddedFields: List<EmbeddedField>,//@Embedded修饰的有效字段没有被忽略的字段
    val relations: List<Relation>,//当前@Relation修饰的有效字段生成的Relation对象 + 当前@Embedded有效字段的类型，该类型中使用@Relation修饰的有效字段生成的Relation对象
    val constructor: Constructor? = null//当前表节点的构造函数
) : HasFields {
    val typeName: TypeName by lazy { type.typeName }

    override val fields = Fields(fields)

    /**
     * All table or view names that are somehow accessed by this Pojo.
     * Might be via Embedded or Relation.
     */
    fun accessedTableNames(): List<String> {
        val entityAnnotation = element.getAnnotation(androidx.room.Entity::class)
        return if (entityAnnotation != null) {
            listOf(EntityProcessor.extractTableName(element, entityAnnotation.value))
        } else {
            val viewAnnotation = element.getAnnotation(androidx.room.DatabaseView::class)
            if (viewAnnotation != null) {
                listOf(DatabaseViewProcessor.extractViewName(element, viewAnnotation.value))
            } else {
                emptyList()
            } + embeddedFields.flatMap {
                it.pojo.accessedTableNames()
            } + relations.map {
                it.entity.tableName
            }
        }
    }
}
