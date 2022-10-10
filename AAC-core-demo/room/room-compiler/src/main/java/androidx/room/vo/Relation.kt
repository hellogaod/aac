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

/**
 * Value object created from processing a @Relation annotation.
 */
class Relation(
    val entity: EntityOrView,//@Relation#entity属性；如果不存在，使用当前@Relation修饰的有效节点类型生成的EntityOrView对象
    // return type. e..g. String in @Relation List<String>
    val pojoType: XType,//@Relation修饰的有效节点（如果是List<T>或Set<T>类型，有效节点类型使用T；如果是<? extends T>使用的T）
    // field in Pojo that holds these relations (e.g. List<Pet> pets)
    val field: Field,//@Relation修饰的有效字段节点生成的对象
    // the parent field referenced for matching
    val parentField: Field,//@Relation#parentColumn的属性值必须存在，而且必须包含在当前@Entity修饰的有效字段被@ColumnInfo修饰（或没有被@Embedded、@ColumnInfo和@Relation修饰）或 @Embedded修饰的有效字段类型中的所有有效字段
    // the field referenced for querying. does not need to be in the response but the query
    // we generate always has it in the response.
    val entityField: Field,//@Relation#entityColumn必须存在，并且该字段存在于Relation关联Entity类型中的所有有效字段中
    // Used for joining on a many-to-many relation
    val junction: Junction?,//在@Relation#associateBy属性中使用的是@Junction注解生成的对象
    // the projection for the query
    val projection: List<String>//@Relation#projection如果存在,则使用这里面的属性；如果不存在使用其他（自行看代码，写的比较清楚，但是无法用一两句表达，艹）
) {
    val pojoTypeName by lazy { pojoType.typeName }

    fun createLoadAllSql(): String {
        val resultFields = projection.toSet()
        return createSelect(resultFields)
    }

    private fun createSelect(resultFields: Set<String>) = buildString {
        if (junction != null) {
            val resultColumns = resultFields.map { "`${entity.tableName}`.`$it` AS `$it`" } +
                "_junction.`${junction.parentField.columnName}`"
            append("SELECT ${resultColumns.joinToString(",")}")
            append(" FROM `${junction.entity.tableName}` AS _junction")
            append(
                " INNER JOIN `${entity.tableName}` ON" +
                    " (_junction.`${junction.entityField.columnName}`" +
                    " = `${entity.tableName}`.`${entityField.columnName}`)"
            )
            append(" WHERE _junction.`${junction.parentField.columnName}` IN (:args)")
        } else {
            val resultColumns = resultFields.map { "`$it`" }.toSet() + "`${entityField.columnName}`"
            append("SELECT ${resultColumns.joinToString(",")}")
            append(" FROM `${entity.tableName}`")
            append(" WHERE `${entityField.columnName}` IN (:args)")
        }
    }
}
