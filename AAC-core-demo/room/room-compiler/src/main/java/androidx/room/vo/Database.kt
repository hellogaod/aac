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

import androidx.room.RoomMasterTable
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XTypeElement
import androidx.room.migration.bundle.DatabaseBundle
import androidx.room.migration.bundle.SchemaBundle
import com.squareup.javapoet.ClassName
import org.apache.commons.codec.digest.DigestUtils
import java.io.File

/**
 * Holds information about a class annotated with Database.
 */
data class Database(
    val element: XTypeElement,//@Database修饰的节点
    val type: XType,//@Database修饰的节点类型
    val entities: List<Entity>,//@Database#entities表生成的对象；
    val views: List<DatabaseView>,//@@Database#views视图生成的对象
    val daoMethods: List<DaoMethod>,//@Database修饰的节点中的xxxDao方法
    val version: Int,//@Database#version
    val exportSchema: Boolean,//@Database#exportSchema,默认true
    val enableForeignKeys: Boolean//@Database#entities表只要有某一个存在外键
) {
    // This variable will be set once auto-migrations are processed given the DatabaseBundle from
    // this object. This is necessary for tracking the versions involved in the auto-migration.
    //数据库迁移
    lateinit var autoMigrations: List<AutoMigration>
    val typeName: ClassName by lazy { element.className }

    private val implClassName by lazy {
        "${typeName.simpleNames().joinToString("_")}_Impl"
    }

    val implTypeName: ClassName by lazy {
        ClassName.get(typeName.packageName(), implClassName)
    }

    val bundle by lazy {
        DatabaseBundle(
            version,
            identityHash,
            entities.map(Entity::toBundle),
            views.map(DatabaseView::toBundle),
            //新建一个room_master_table表，字段有id和identity_hash
            listOf(
                RoomMasterTable.CREATE_QUERY,
                RoomMasterTable.createInsertQuery(identityHash)
            )
        )
    }

    /**
     * Create a has that identifies this database definition so that at runtime we can check to
     * ensure developer didn't forget to update the version.
     *
     * 创建一个标识此数据库定义的has，以便在运行时我们可以检查以确保开发人员不会忘记更新版本。
     */
    val identityHash: String by lazy {
        val idKey = SchemaIdentityKey()
        idKey.appendSorted(entities)
        idKey.appendSorted(views)
        idKey.hash()
    }

    val legacyIdentityHash: String by lazy {
        val entityDescriptions = entities
            .sortedBy { it.tableName }
            .map { it.createTableQuery }
        val indexDescriptions = entities
            .flatMap { entity ->
                entity.indices.map { index ->
                    // For legacy purposes we need to remove the later added 'IF NOT EXISTS'
                    // part of the create statement, otherwise old valid legacy hashes stop
                    // being accepted even though the schema has not changed. b/139306173
                    if (index.unique) {
                        "CREATE UNIQUE INDEX"
                    } else {
                        // The extra space between 'CREATE' and 'INDEX' is on purpose, this
                        // is a typo we have to live with.
                        "CREATE  INDEX"
                    } + index.createQuery(entity.tableName).substringAfter("IF NOT EXISTS")
                }
            }
        val viewDescriptions = views
            .sortedBy { it.viewName }
            .map { it.viewName + it.query.original }
        val input = (entityDescriptions + indexDescriptions + viewDescriptions)
            .joinToString("¯\\_(ツ)_/¯")
        DigestUtils.md5Hex(input)
    }

    fun exportSchema(file: File) {
        val schemaBundle = SchemaBundle(SchemaBundle.LATEST_FORMAT, bundle)
        if (file.exists()) {
            val existing = try {
                file.inputStream().use {
                    SchemaBundle.deserialize(it)
                }
            } catch (th: Throwable) {
                throw IllegalStateException(
                    """
                    Cannot parse existing schema file: ${file.absolutePath}.
                    If you've modified the file, you might've broken the JSON format, try
                    deleting the file and re-running the compiler.
                    If you've not modified the file, please file a bug at
                    https://issuetracker.google.com/issues/new?component=413107&template=1096568
                    with a sample app to reproduce the issue.
                    """.trimIndent()
                )
            }
            if (existing.isSchemaEqual(schemaBundle)) {
                return
            }
        }
        SchemaBundle.serialize(schemaBundle, file)
    }
}
