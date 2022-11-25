/*
 * Copyright 2019 The Android Open Source Project
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

/**
 * Represents a shortcut method parameter entity.
 */
data class ShortcutEntity(
    //dao方法参数生成的entity
    private val entity: Entity, // the actual entity
    //如果dao方法注解的entity属性存在，并且属性对象和dao方法参数（如果是集合或数组，判断的是item类型）不一致，当前entity属性对象生成的pojo对象
    private val partialEntity: Pojo? // the partial entity
) {
    val tableName = entity.tableName
    val entityTypeName = entity.typeName
    val primaryKey by lazy {
        if (partialEntity == null) {
            entity.primaryKey
        } else {
            val partialEntityPrimaryKeyFields = entity.primaryKey.fields.mapNotNull {
                partialEntity.findFieldByColumnName(it.columnName)
            }
            entity.primaryKey.copy(fields = Fields(partialEntityPrimaryKeyFields))
        }
    }
    val pojo = partialEntity ?: entity
    val isPartialEntity = partialEntity != null
}