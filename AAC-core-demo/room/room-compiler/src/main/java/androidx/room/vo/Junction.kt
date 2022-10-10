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
 * Value object defining a junction table for a [Relation].
 */
data class Junction(
    val entity: EntityOrView,//@Relation#associateBy的属性@Junction，@Junction#value的属性值类型必须是@Entity 或 @DatabaseView修饰
    val parentField: Field,//连接父级表的列属性：在@Relation#associateBy属性中，如果@Junction#parentColumn存在，使用当前字段作为连接父级表的列属性；否则使用@Relation#parentColunm
    val entityField: Field//连接实体表的列属性：在@Relation#associateBy属性中，如果@Junction#entityColumn存在，使用当前字段作为连接实体表的列属性；否则使用@Relation#entityColumn
)