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

import androidx.room.AutoMigration
import androidx.room.SkipQueryVerification
import androidx.room.compiler.processing.XAnnotationBox
import androidx.room.compiler.processing.XElement
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XTypeElement
import androidx.room.ext.RoomTypeNames
import androidx.room.migration.bundle.DatabaseBundle
import androidx.room.migration.bundle.SchemaBundle
import androidx.room.processor.ProcessorErrors.AUTO_MIGRATION_FOUND_BUT_EXPORT_SCHEMA_OFF
import androidx.room.processor.ProcessorErrors.AUTO_MIGRATION_SCHEMA_OUT_FOLDER_NULL
import androidx.room.processor.ProcessorErrors.autoMigrationSchemasMustBeRoomGenerated
import androidx.room.processor.ProcessorErrors.invalidAutoMigrationSchema
import androidx.room.util.SchemaFileResolver
import androidx.room.verifier.DatabaseVerificationErrors
import androidx.room.verifier.DatabaseVerifier
import androidx.room.vo.Dao
import androidx.room.vo.DaoMethod
import androidx.room.vo.Database
import androidx.room.vo.DatabaseView
import androidx.room.vo.Entity
import androidx.room.vo.FtsEntity
import androidx.room.vo.Warning
import androidx.room.vo.columnNames
import androidx.room.vo.findFieldByColumnName
import com.squareup.javapoet.TypeName
import java.io.File
import java.io.FileInputStream
import java.nio.file.Path
import java.util.Locale

class DatabaseProcessor(baseContext: Context, val element: XTypeElement) {
    //Context叠加生成新的Context对象
    val context = baseContext.fork(element)

    val roomDatabaseType: XType by lazy {
        context.processingEnv.requireType(
            RoomTypeNames.ROOM_DB.packageName() + "." + RoomTypeNames.ROOM_DB.simpleName()
        )
    }

    fun process(): Database {
        try {
            return doProcess()
        } finally {
            context.databaseVerifier?.closeConnection(context)
        }
    }

    private fun doProcess(): Database {
        val dbAnnotation = element.getAnnotation(androidx.room.Database::class)!!
        //entity实体对象生成
        val entities = processEntities(
            dbAnnotation, //Database注解
            element//使用Database注解修饰的节点
        )

        //views视图
        val viewsMap = processDatabaseViews(dbAnnotation)

        //外键校验
        validateForeignKeys(element, entities)

        validateExternalContentFts(element, entities)

        //`@Database`类必须继承`androidx.room.RoomDatabase`；
        val extendsRoomDb = roomDatabaseType.isAssignableFrom(element.type)
        context.checker.check(extendsRoomDb, element, ProcessorErrors.DB_MUST_EXTEND_ROOM_DB)

        val views = resolveDatabaseViews(viewsMap.values.toList())

        //数据库验证器
        val dbVerifier = if (element.hasAnnotation(SkipQueryVerification::class)) {
            null
        } else {
            DatabaseVerifier.create(context, element, entities, views)
        }

        if (dbVerifier != null) {
            context.attachDatabaseVerifier(dbVerifier)
            verifyDatabaseViews(viewsMap, dbVerifier)
        }

        //`@Database`类下的数据库表和视图名称一定不可以重复；
        // - 不区分大小写的，e.g.entUser表存在，那么一定不允许存在entuser表；
        validateUniqueTableAndViewNames(element, entities, views)

        val declaredType = element.type
        val daoMethods = element.getAllMethods().filter {
            it.isAbstract()
        }.filterNot {
            // remove methods that belong to room
            it.enclosingElement.className == RoomTypeNames.ROOM_DB
        }.mapNotNull { executable ->
            // TODO when we add support for non Dao return types (e.g. database), this code needs
            // to change
            val daoType = executable.returnType
            val daoElement = daoType.typeElement
            //`@Database`类中必须存在`abstract`方法（返回类型是@Dao修饰的类型），否则报错；
            if (daoElement == null) {
                context.logger.e(
                    executable,
                    ProcessorErrors.DATABASE_INVALID_DAO_METHOD_RETURN_TYPE
                )
                null
            } else {
                //d`@Database`类中的`abstract`方法最好不要使用@JvmName修饰，否则警告；
                if (executable.hasAnnotation(JvmName::class)) {
                    context.logger.w(
                        Warning.JVM_NAME_ON_OVERRIDDEN_METHOD,
                        executable,
                        ProcessorErrors.JVM_NAME_ON_OVERRIDDEN_METHOD
                    )
                }

                //操作数据库
                val dao = DaoProcessor(
                    context,
                    daoElement,//dao节点
                    declaredType,//dao节点所在父级节点，即@Database修饰的节点
                    dbVerifier
                )
                    .process()

                DaoMethod(executable, dao)
            }
        }.toList()

        validateUniqueDaoClasses(element, daoMethods, entities)
        validateUniqueIndices(element, entities)

        val hasForeignKeys = entities.any { it.foreignKeys.isNotEmpty() }

        val database = Database(
            version = dbAnnotation.value.version,
            element = element,
            type = element.type,
            entities = entities,
            views = views,
            daoMethods = daoMethods,
            exportSchema = dbAnnotation.value.exportSchema,
            enableForeignKeys = hasForeignKeys
        )



        database.autoMigrations = processAutoMigrations(element, database.bundle)
        return database
    }

    private fun processAutoMigrations(
        element: XTypeElement,
        latestDbSchema: DatabaseBundle
    ): List<androidx.room.vo.AutoMigration> {

        val dbAnnotation = element.getAnnotation(androidx.room.Database::class)!!

        val autoMigrationList = dbAnnotation
            .getAsAnnotationBoxArray<AutoMigration>("autoMigrations")

        // `@Database#autoMigrations`属性不为空，表示当前数据库需要迁移；
        if (autoMigrationList.isNotEmpty()) {
            // @Database#exportSchema属性必须是true，默认就是true
            if (!dbAnnotation.value.exportSchema) {
                context.logger.e(
                    element,
                    AUTO_MIGRATION_FOUND_BUT_EXPORT_SCHEMA_OFF
                )
                return emptyList()
            }
            // 在项目中`gradle`中通过 `annotationProcessorOptions` 注解，为`room.schemaLocation`指定`schemas`的子文件夹。
            // (当执行项目后，在Android Studio 的Project视图下，查看项目，会发现`< Module>`生成了一个`schemas`的文件夹，文件夹和`src`同级)
            if (context.schemaOutFolderPath == null) {
                context.logger.e(
                    element,
                    AUTO_MIGRATION_SCHEMA_OUT_FOLDER_NULL
                )
                return emptyList()
            }
        }



        return autoMigrationList.mapNotNull {
            //room.schemaLocation
            val databaseSchemaFolderPath = Path.of(
                context.schemaOutFolderPath!!,
                element.className.canonicalName()
            )
            //`@AutoMigratio#from`和`@AutoMigratio#to`属性值必须存在，两个属性值表示版本。会分别生成两个`json`文件，根据属性值命名
            val autoMigration = it.value
            val validatedFromSchemaFile = getValidatedSchemaFile(
                autoMigration.from,
                databaseSchemaFolderPath
            )

            fun deserializeSchemaFile(fileInputStream: FileInputStream, versionNumber: Int): Any {
                return try {
                    SchemaBundle.deserialize(fileInputStream).database
                } catch (th: Throwable) {
                    invalidAutoMigrationSchema(
                        "$versionNumber.json",
                        databaseSchemaFolderPath.toString()
                    )
                }
            }


            if (validatedFromSchemaFile != null) {
                val fromSchemaBundle = validatedFromSchemaFile.inputStream().use {
                    deserializeSchemaFile(it, autoMigration.from)
                }
                //如果`@AutoMigratio#to == @Database#version`，表示迁移版本就是当前数据库版本；
                val toSchemaBundle = if (autoMigration.to == latestDbSchema.version) {
                    latestDbSchema
                } else {
                    val validatedToSchemaFile = getValidatedSchemaFile(
                        autoMigration.to,
                        databaseSchemaFolderPath
                    )
                    if (validatedToSchemaFile != null) {
                        validatedToSchemaFile.inputStream().use {
                            deserializeSchemaFile(it, autoMigration.to)
                        }
                    } else {
                        return@mapNotNull null
                    }
                }

                //无法自动生成迁移
                if (fromSchemaBundle !is DatabaseBundle || toSchemaBundle !is DatabaseBundle) {
                    context.logger.e(
                        element,
                        autoMigrationSchemasMustBeRoomGenerated(
                            autoMigration.from,
                            autoMigration.to
                        )
                    )
                    return@mapNotNull null
                }

                AutoMigrationProcessor(
                    context = context,
                    spec = it.getAsType("spec")!!,
                    fromSchemaBundle = fromSchemaBundle,
                    toSchemaBundle = toSchemaBundle
                ).process()
            } else {
                null
            }
        }
    }

    //项目的`< Module>`生成了一个`schemas`的文件夹,该文件夹中会生成`$version.json`文件。如下`@AutoMigratio#from`属性是1，所以生成`1.json`文件
    private fun getValidatedSchemaFile(version: Int, schemaFolderPath: Path): File? {
        val schemaFile = SchemaFileResolver.RESOLVER.getFile(
            schemaFolderPath.resolve("$version.json")
        )
        if (!schemaFile.exists()) {
            context.logger.e(
                ProcessorErrors.autoMigrationSchemasNotFound(
                    "$version.json",
                    schemaFolderPath.toString()
                ),
                element
            )
            return null
        }

        if (schemaFile.length() <= 0) {
            context.logger.e(
                ProcessorErrors.autoMigrationSchemaIsEmpty(
                    "$version.json",
                    schemaFolderPath.toString()
                ),
                element
            )
            return null
        }
        return schemaFile
    }

    private fun validateForeignKeys(element: XTypeElement, entities: List<Entity>) {
        val byTableName = entities.associateBy { it.tableName }
        entities.forEach { entity ->
            entity.foreignKeys.forEach foreignKeyLoop@{ foreignKey ->
                //外键指向的表必须存在于@DatabaseView#entities中
                val parent = byTableName[foreignKey.parentTable]
                if (parent == null) {
                    context.logger.e(
                        element,
                        ProcessorErrors
                            .foreignKeyMissingParentEntityInDatabase(
                                foreignKey.parentTable,
                                entity.element.qualifiedName
                            )
                    )
                    return@foreignKeyLoop
                }
                //外键表字段必须存在于外键表中
                val parentFields = foreignKey.parentColumns.mapNotNull { columnName ->
                    val parentField = parent.findFieldByColumnName(columnName)
                    if (parentField == null) {
                        context.logger.e(
                            entity.element,
                            ProcessorErrors.foreignKeyParentColumnDoesNotExist(
                                parentEntity = parent.element.qualifiedName,
                                missingColumn = columnName,
                                allColumns = parent.columnNames
                            )
                        )
                    }
                    parentField
                }
                if (parentFields.size != foreignKey.parentColumns.size) {
                    return@foreignKeyLoop
                }
                // ensure that it is indexed in the parent
                //表外键字段指向外键表字段：该外键表字段要么是主键，要么创建了唯一性索引；否则会报警告；
                if (!parent.isUnique(foreignKey.parentColumns)) {
                    context.logger.e(
                        parent.element,
                        ProcessorErrors
                            .foreignKeyMissingIndexInParent(
                                parentEntity = parent.element.qualifiedName,
                                childEntity = entity.element.qualifiedName,
                                parentColumns = foreignKey.parentColumns,
                                childColumns = foreignKey.childFields
                                    .map { it.columnName }
                            )
                    )
                    return@foreignKeyLoop
                }
            }
        }
    }

    //`@Database`类中的所有索引名不允许重复
    private fun validateUniqueIndices(element: XTypeElement, entities: List<Entity>) {
        entities
            .flatMap { entity ->
                // associate each index with its entity
                entity.indices.map { Pair(it.name, entity) }
            }
            .groupBy { it.first } // group by index name
            .filter { it.value.size > 1 } // get the ones with duplicate names
            .forEach {
                // do not report duplicates from the same entity
                if (it.value.distinctBy { it.second.typeName }.size > 1) {
                    context.logger.e(
                        element,
                        ProcessorErrors.duplicateIndexInDatabase(
                            it.key,
                            it.value.map { "${it.second.typeName} > ${it.first}" }
                        )
                    )
                }
            }
    }

    private fun validateUniqueDaoClasses(
        dbElement: XTypeElement,
        daoMethods: List<DaoMethod>,
        entities: List<Entity>
    ) {
        val entityTypeNames = entities.map { it.typeName }.toSet()

        //`@Database`类中的`abstract`方法返回类型一定是`@Dao`修饰的类，`@Database`类中不允许出现返回类型相同的`abstract`方法；
        daoMethods.groupBy { it.dao.typeName }
            .forEach {
                if (it.value.size > 1) {
                    val error = ProcessorErrors.duplicateDao(it.key,
                        it.value.map { it.element.jvmName }
                    )
                    it.value.forEach { daoMethod ->
                        context.logger.e(
                            daoMethod.element,
                            ProcessorErrors.DAO_METHOD_CONFLICTS_WITH_OTHERS
                        )
                    }
                    // also report the full error for the database
                    context.logger.e(dbElement, error)
                }
            }

        val check = fun(
            element: XElement,
            dao: Dao,
            typeName: TypeName?
        ) {
            typeName?.let {
                if (!entityTypeNames.contains(typeName)) {
                    context.logger.e(
                        element,
                        ProcessorErrors.shortcutEntityIsNotInDatabase(
                            database = dbElement.qualifiedName,
                            dao = dao.typeName.toString(),
                            entity = typeName.toString()
                        )
                    )
                }
            }
        }

        daoMethods.forEach { daoMethod ->
            daoMethod.dao.shortcutMethods.forEach { method ->
                method.entities.forEach {
                    check(method.element, daoMethod.dao, it.value.entityTypeName)
                }
            }
            daoMethod.dao.insertionMethods.forEach { method ->
                method.entities.forEach {
                    check(method.element, daoMethod.dao, it.value.entityTypeName)
                }
            }
        }
    }

    private fun validateUniqueTableAndViewNames(
        dbElement: XTypeElement,
        entities: List<Entity>,
        views: List<DatabaseView>
    ) {
        val entitiesInfo = entities.map {
            Triple(it.tableName.lowercase(Locale.US), it.typeName.toString(), it.element)
        }
        val viewsInfo = views.map {
            Triple(it.viewName.lowercase(Locale.US), it.typeName.toString(), it.element)
        }
        (entitiesInfo + viewsInfo)
            .groupBy { (name, _, _) -> name }
            .filter { it.value.size > 1 }
            .forEach { byName ->
                val error = ProcessorErrors.duplicateTableNames(
                    byName.key,
                    byName.value.map { (_, typeName, _) -> typeName }
                )
                // report it for each of them and the database to make it easier
                // for the developer
                byName.value.forEach { (_, _, element) ->
                    context.logger.e(element, error)
                }
                context.logger.e(dbElement, error)
            }
    }

    //@Fts4#contentEntity中的对象必须存在于@Database#entities中
    private fun validateExternalContentFts(dbElement: XTypeElement, entities: List<Entity>) {
        // Validate FTS external content entities are present in the same database.
        //验证FTS外部内容实体是否存在于同一数据库中。
        entities.filterIsInstance(FtsEntity::class.java)
            .filterNot {
                it.ftsOptions.contentEntity == null ||
                        entities.contains(it.ftsOptions.contentEntity)
            }
            .forEach {
                context.logger.e(
                    dbElement,
                    ProcessorErrors.missingExternalContentEntity(
                        it.element.qualifiedName,
                        it.ftsOptions.contentEntity!!.element.qualifiedName
                    )
                )
            }
    }

    private fun processEntities(
        dbAnnotation: XAnnotationBox<androidx.room.Database>,
        element: XTypeElement
    ): List<Entity> {
        val entityList = dbAnnotation.getAsTypeList("entities")
        //@Database必须有entities属性
        context.checker.check(
            entityList.isNotEmpty(), element,
            ProcessorErrors.DATABASE_ANNOTATION_MUST_HAVE_LIST_OF_ENTITIES
        )

        return entityList.mapNotNull {
            val typeElement = it.typeElement //@Database entities中的类
            if (typeElement == null) {
                //@Database entities属性中必须是一个类
                context.logger.e(
                    element,
                    ProcessorErrors.invalidEntityTypeInDatabaseAnnotation(
                        it.typeName
                    )
                )
                null
            } else {
                EntityProcessor(
                    context,
                    typeElement//@Database#entities属性中的类
                ).process()
            }
        }
    }

    private fun processDatabaseViews(
        dbAnnotation: XAnnotationBox<androidx.room.Database>
    ): Map<XTypeElement, DatabaseView> {
        val viewList = dbAnnotation.getAsTypeList("views")
        return viewList.mapNotNull {
            val viewElement = it.typeElement
            if (viewElement == null) {
                context.logger.e(
                    element,
                    ProcessorErrors.invalidViewTypeInDatabaseAnnotation(
                        it.typeName
                    )
                )
                null
            } else {
                viewElement to DatabaseViewProcessor(
                    context,
                    viewElement// @Database#views节点
                ).process()
            }
        }.toMap()
    }

    private fun verifyDatabaseViews(
        map: Map<XTypeElement, DatabaseView>,
        dbVerifier: DatabaseVerifier
    ) {
        for ((viewElement, view) in map) {
            if (viewElement.hasAnnotation(SkipQueryVerification::class)) {
                continue
            }
            view.query.resultInfo = dbVerifier.analyze(view.query.original)
            if (view.query.resultInfo?.error != null) {
                context.logger.e(
                    viewElement,
                    DatabaseVerificationErrors.cannotVerifyQuery(
                        view.query.resultInfo!!.error!!
                    )
                )
            }
        }
    }

    /**
     * Resolves all the underlying tables for each of the [DatabaseView]. All the tables
     * including those that are indirectly referenced are included.
     *
     * 解析每个[DatabaseView]的所有底层表。包括间接引用的所有表。
     *
     * @param views The list of all the [DatabaseView]s in this database. The order in this list is
     * important. A view always comes after all of the tables and views that it depends on.
     */
    fun resolveDatabaseViews(views: List<DatabaseView>): List<DatabaseView> {
        if (views.isEmpty()) {
            return emptyList()
        }
        val viewNames = views.map { it.viewName }

        fun isTable(name: String) = viewNames.none { it.equals(name, ignoreCase = true) }

        for (view in views) {
            // Some of these "tables" might actually be views.
            view.tables.addAll(view.query.tables.map { (name, _) -> name })
        }
        val unresolvedViews = views.toMutableList()
        // We will resolve nested views step by step, and store the results here.
        val resolvedViews = mutableMapOf<String, Set<String>>()
        val result = mutableListOf<DatabaseView>()
        do {
            for ((viewName, tables) in resolvedViews) {
                for (view in unresolvedViews) {
                    // If we find a nested view, replace it with the list of concrete tables.
                    //如果我们找到一个嵌套视图，请将其替换为具体表的列表。
                    if (view.tables.removeIf { it.equals(viewName, ignoreCase = true) }) {
                        view.tables.addAll(tables)
                    }
                }
            }
            var countNewlyResolved = 0
            // Separate out views that have all of their underlying tables resolved.
            unresolvedViews
                .filter { view -> view.tables.all { isTable(it) } }
                .forEach { view ->
                    resolvedViews[view.viewName] = view.tables
                    unresolvedViews.remove(view)
                    result.add(view)
                    countNewlyResolved++
                }
            // We couldn't resolve a single view in this step. It indicates circular reference.
            if (countNewlyResolved == 0) {
                context.logger.e(
                    element,
                    ProcessorErrors.viewCircularReferenceDetected(
                        unresolvedViews.map { it.viewName }
                    )
                )
                break
            }
            // We are done if we have resolved tables for all the views.
        } while (unresolvedViews.isNotEmpty())
        return result
    }
}
