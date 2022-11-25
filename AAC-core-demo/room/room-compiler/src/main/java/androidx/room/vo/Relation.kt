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
    val entity: EntityOrView,//关联表对象
    // return type. e..g. String in @Relation List<String>
    val pojoType: XType,//关联表对象类型
    // field in Pojo that holds these relations (e.g. List<Pet> pets)
    val field: Field,//@Relation修饰的有效字段节点生成的表字段对象
    // the parent field referenced for matching
    val parentField: Field,//@Relation#parentColumn的属性值必须存在，关联关联表entityField字段的字段
    // the field referenced for querying. does not need to be in the response but the query
    // we generate always has it in the response.
    val entityField: Field,//@Relation#entityColumn必须存在，关联表上的表常规字段
    // Used for joining on a many-to-many relation
    val junction: Junction?,//处理多对多关系，在@Relation#associateBy属性中使用的是@Junction注解生成的对象
    // the projection for the query
    val projection: List<String>//提取关联表中的字段
) {
    val pojoTypeName by lazy { pojoType.typeName }

    fun createLoadAllSql(): String {
        val resultFields = projection.toSet()
        return createSelect(resultFields)
    }

    //传入返回字段，生成sql查询语句，如果是多对多关系（junction != null情况下表示多对多）还需要关联上多对多
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
