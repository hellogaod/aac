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
 *
 * 1. entity节点：@Entity修饰的节点；
 * 2. fts节点：@Fts3或@Fts3修饰的节点同时使用@Entity修饰；
 * 3. databaseView节点：@DatabaseView修饰的节点；
 * 4. @Embedded修饰的节点对象：@Embedded修饰的节点是一个变量或方法返回类型，该变量或方法返回类型表示的对象节点；
 * 5. @Relation注解 && @Relation#projection为空 && @Relation修饰的节点类型不是表字段类型（自行查看表字段支持类型），
 * 那么对relation节点（如果relation节点类型是List< T>或List<? extends T>或Set< T>或Set<? extends T>,那么针对的是T的节点）
 *
 */
open class Pojo(
    val element: XTypeElement,//用于创建Pojo的节点；如果节点同时使用@AutovAlue表示新生成的节点：Auto_原先节点；
    val type: XType,//用于创建Pojo的节点类型；如果节点同时使用@AutovAlue表示新生成的节点类型：Auto_原先节点类型；
    fields: List<Field>,//表常规字段 + 嵌入表常规字段
    val embeddedFields: List<EmbeddedField>,//嵌入表字段
    val relations: List<Relation>,//关系表字段
    val constructor: Constructor? = null//构造函数
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
