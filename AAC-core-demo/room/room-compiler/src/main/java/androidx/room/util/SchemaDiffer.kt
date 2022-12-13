/*
 * Copyright 2021 The Android Open Source Project
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

package androidx.room.util

import androidx.room.migration.bundle.DatabaseBundle
import androidx.room.migration.bundle.DatabaseViewBundle
import androidx.room.migration.bundle.EntityBundle
import androidx.room.migration.bundle.FieldBundle
import androidx.room.migration.bundle.ForeignKeyBundle
import androidx.room.migration.bundle.FtsEntityBundle
import androidx.room.migration.bundle.IndexBundle
import androidx.room.processor.ProcessorErrors.deletedOrRenamedTableFound
import androidx.room.processor.ProcessorErrors.tableRenameError
import androidx.room.processor.ProcessorErrors.conflictingRenameColumnAnnotationsFound
import androidx.room.processor.ProcessorErrors.conflictingRenameTableAnnotationsFound
import androidx.room.processor.ProcessorErrors.newNotNullColumnMustHaveDefaultValue
import androidx.room.processor.ProcessorErrors.deletedOrRenamedColumnFound
import androidx.room.processor.ProcessorErrors.tableWithConflictingPrefixFound
import androidx.room.vo.AutoMigration

/**
 * This exception should be thrown to abandon processing an @AutoMigration.
 *
 * @param errorMessage Error message to be thrown with the exception
 * @return RuntimeException with the provided error message
 */
class DiffException(val errorMessage: String) : RuntimeException(errorMessage)

/**
 * Contains the changes detected between the two schema versions provided.
 */
data class SchemaDiffResult(
    val addedColumns: List<AutoMigration.AddedColumn>,//新增了哪些字段
    val deletedColumns: List<AutoMigration.DeletedColumn>,//删除了哪些字段
    val addedTables: Set<AutoMigration.AddedTable>,
    val renamedTables: Map<String, String>,//重命名了哪些表名
    val complexChangedTables: Map<String, AutoMigration.ComplexChangedTable>,//哪些表架构做了调整
    val deletedTables: List<String>,//删除了哪些表
    val fromViews: List<DatabaseViewBundle>,//旧数据库中的视图
    val toViews: List<DatabaseViewBundle>//新数据库中的视图
)

/**
 * Receives the two bundles, detects all changes between the two versions and returns a
 * @SchemaDiffResult.
 *
 * Throws an @DiffException with a detailed error message when an AutoMigration cannot
 * be generated.
 *
 * @param fromSchemaBundle Original database schema to migrate from
 * @param toSchemaBundle New database schema to migrate to
 * @param className Name of the user implemented AutoMigrationSpec interface, if available
 * @param renameColumnEntries List of repeatable annotations specifying column renames
 * @param deleteColumnEntries List of repeatable annotations specifying column deletes
 * @param renameTableEntries List of repeatable annotations specifying table renames
 * @param deleteTableEntries List of repeatable annotations specifying table deletes
 */
class SchemaDiffer(
    private val fromSchemaBundle: DatabaseBundle,// @AutoMigratio#from生成的对象
    private val toSchemaBundle: DatabaseBundle,// @AutoMigratio#to生成的对象
    private val className: String?,//@AutoMigratio#spec中的类名
    private val renameColumnEntries: List<AutoMigration.RenamedColumn>,//@AutoMigratio#spec中的类,修改表字段名
    private val deleteColumnEntries: List<AutoMigration.DeletedColumn>,//@AutoMigratio#spec中的类,表需要删减的表字段
    private val renameTableEntries: List<AutoMigration.RenamedTable>,//@AutoMigratio#spec中的类，修改表名
    private val deleteTableEntries: List<AutoMigration.DeletedTable>//@AutoMigratio#spec中的类,当前需要删减的表
) {
    //可能被删除的表
    private val potentiallyDeletedTables = mutableSetOf<String>()

    // Maps FTS tables in the to version to the name of their content tables in the from version
    // for easy lookup.
    //收集旧数据库中的fts表，k：fts关联的表名，V：fts表对象
    private val contentTableToFtsEntities = mutableMapOf<String, MutableList<EntityBundle>>()

    private val addedTables = mutableSetOf<AutoMigration.AddedTable>()

    // Any table that has been renamed, but also does not contain any complex changes.
    //收集重命名的表，但是这个表结构没有改动，仅仅改了名称
    private val renamedTables = mutableMapOf<String, String>()

    // Map of tables with complex changes, keyed by the table name, note that if the table is
    // renamed, the original table name is used as key.
    //表结构发生了变化，k：旧数据库表名，v：改动对象
    private val complexChangedTables =
        mutableMapOf<String, AutoMigration.ComplexChangedTable>()
    private val deletedTables = deleteTableEntries.map { it.deletedTableName }.toSet()

    // Map of columns that have been added in the database, note that the table these columns
    // have been added to will not contain any complex schema changes.
    private val addedColumns = mutableListOf<AutoMigration.AddedColumn>()
    private val deletedColumns = deleteColumnEntries

    /**
     * Compares the two versions of the database based on the schemas provided, and detects
     * schema changes.
     *
     * 根据提供的模式比较数据库的两个版本，并检测模式更改。
     *
     * @return the AutoMigrationResult containing the schema changes detected
     */
    fun diffSchemas(): SchemaDiffResult {
        //存储新版本数据库 表 -> 表字段 信息
        val processedTablesAndColumnsInNewVersion = mutableMapOf<String, List<String>>()

        // Check going from the original version of the schema to the new version for changed and
        // deleted columns/tables
        fromSchemaBundle.entitiesByTableName.values.forEach { fromTable ->

            val toTable = detectTableLevelChanges(fromTable)

            // Check for column related changes. Since we require toTable to not be null, any
            // deleted tables will be skipped here.
            //当前数据库中的表要么做了重命名操作，要么在旧数据库中被当前数据库沿用
            if (toTable != null) {

                //将目标版本中的FTS表映射到源版本中其内容表的名称，以便于查找
                if (fromTable is FtsEntityBundle &&
                    fromTable.ftsOptions.contentTable.isNotEmpty()
                ) {
                    contentTableToFtsEntities.getOrPut(fromTable.ftsOptions.contentTable) {
                        mutableListOf()
                    }.add(fromTable)
                }

                //当前表的表字段是否更改
                val fromColumns = fromTable.fieldsByColumnName
                val processedColumnsInNewVersion = fromColumns.values.mapNotNull { fromColumn ->
                    detectColumnLevelChanges(
                        fromTable,
                        toTable,
                        fromColumn
                    )
                }
                processedTablesAndColumnsInNewVersion[toTable.tableName] =
                    processedColumnsInNewVersion
            }
        }

        // Check going from the new version of the schema to the original version for added
        // tables/columns. Skip the columns that have been processed already.
        //新增了哪些表和表字段
        toSchemaBundle.entitiesByTableName.forEach { toTable ->
            processAddedTableAndColumns(toTable.value, processedTablesAndColumnsInNewVersion)
        }

        potentiallyDeletedTables.forEach { tableName ->
            diffError(
                deletedOrRenamedTableFound(
                    className = className,
                    tableName = tableName
                )
            )
        }
        processDeletedColumns()

        processContentTables()

        return SchemaDiffResult(
            addedColumns = addedColumns,
            deletedColumns = deletedColumns,
            addedTables = addedTables,
            renamedTables = renamedTables,
            complexChangedTables = complexChangedTables,
            deletedTables = deletedTables.toList(),
            fromViews = fromSchemaBundle.views,
            toViews = toSchemaBundle.views
        )
    }

    /**
     * Checks if any content tables have been renamed, and if so, marks the FTS table referencing
     * the content table as a complex changed table.
     *
     * 被删除的表，如果是把表改成fts表了，并且不存在于 改动表架构集合中，将其收集到改动表架构集合中
     */
    private fun processContentTables() {
        renameTableEntries.forEach { renamedTable ->
            contentTableToFtsEntities[renamedTable.originalTableName]?.filter {
                !complexChangedTables.containsKey(it.tableName)
            }?.forEach { ftsTable ->
                complexChangedTables[ftsTable.tableName] =
                    AutoMigration.ComplexChangedTable(
                        tableName = ftsTable.tableName,
                        tableNameWithNewPrefix = ftsTable.newTableName,
                        oldVersionEntityBundle = ftsTable,
                        newVersionEntityBundle = ftsTable,
                        renamedColumnsMap = mutableMapOf()
                    )
            }
        }
    }

    /**
     * Detects any changes at the table-level, independent of any changes that may be present at
     * the column-level (e.g. column add/rename/delete).
     *
     * 数据库中的表：
     * 1. 要么出现在@RenameTable#newTableName中，表示表重命名；
     * 2. 要么出现在toSchema中表示当前表迁移时沿用之前的；
     * 3. 要么以上都没有出现，表示当前表在新版数据库中被删除；
     *
     * @param fromTable The original version of the table
     * @return The EntityBundle of the table in the new version of the database. If the
     * table was renamed, this will be reflected in the return value. If the table was removed, a
     * null object will be returned.
     */
    private fun detectTableLevelChanges(
        fromTable: EntityBundle
    ): EntityBundle? {
        // Check if the table was renamed. If so, check for other complex changes that could
        // be found on the table level. Save the end result to the complex changed tables map.
        val renamedTable = isTableRenamed(fromTable.tableName)

        if (renamedTable != null) {
            //@RenameTable#toTableName表示新命名的表，当前新表名必须存在于新版数据库中；
            val toTable = toSchemaBundle.entitiesByTableName[renamedTable.newTableName]
            if (toTable != null) {
                val isComplexChangedTable = tableContainsComplexChanges(
                    fromTable,
                    toTable
                )
                val isFtsEntity = fromTable is FtsEntityBundle
                if (isComplexChangedTable || isFtsEntity) {
                    //如果表结构（主键、外键、索引或者本身就是fts表）发生变化，那么当前 “_new_” +@RenameTable#toTableName 不允许存在于新版数据库中
                    if (toSchemaBundle.entitiesByTableName.containsKey(toTable.newTableName)) {
                        diffError(tableWithConflictingPrefixFound(toTable.newTableName))
                    }

                    renamedTables.remove(renamedTable.originalTableName)

                    complexChangedTables[renamedTable.originalTableName] =
                        AutoMigration.ComplexChangedTable(
                            tableName = toTable.tableName,
                            tableNameWithNewPrefix = toTable.newTableName,
                            oldVersionEntityBundle = fromTable,
                            newVersionEntityBundle = toTable,
                            renamedColumnsMap = mutableMapOf()
                        )
                } else {
                    renamedTables[fromTable.tableName] = toTable.tableName
                }
            } else {
                // The table we renamed TO does not exist in the new version
                diffError(
                    tableRenameError(
                        className!!,
                        renamedTable.originalTableName,
                        renamedTable.newTableName
                    )
                )
            }
            return toTable
        }

        //如果旧数据库中的表在@RenameTable不存在，表示没有对当前数据库中修改；当前表在新数据库中被沿用，那么不允许出现在@DeleteTable#tableName中（表示表被删除了，肯定报错）；
        val toTable = toSchemaBundle.entitiesByTableName[fromTable.tableName]
        val isDeletedTable = deletedTables.contains(fromTable.tableName)
        if (toTable != null) {
            if (isDeletedTable) {
                diffError(
                    deletedOrRenamedTableFound(className, toTable.tableName)
                )
            }

            // Check if this table exists in both versions of the schema, hence is not renamed or
            // deleted, but contains other complex changes (index/primary key/foreign key change).
            val isComplexChangedTable = tableContainsComplexChanges(
                fromTable = fromTable,
                toTable = toTable
            )
            if (isComplexChangedTable) {
                complexChangedTables[fromTable.tableName] =
                    AutoMigration.ComplexChangedTable(
                        tableName = toTable.tableName,
                        tableNameWithNewPrefix = toTable.newTableName,
                        oldVersionEntityBundle = fromTable,
                        newVersionEntityBundle = toTable,
                        renamedColumnsMap = mutableMapOf()
                    )
            }
            return toTable
        }
        if (!isDeletedTable) {
            potentiallyDeletedTables.add(fromTable.tableName)
        }

        // Table was deleted.
        return null
    }

    /**
     * Detects any changes at the column-level.
     *
     * 表字段迁移，前提条件是当前表一定要存在：
     *
     * 1. 表字段出现在@RenameColumn#fromColumnName中，表示当前表字段重命名；
     * 2. 表字段在新表中还存在，表示当前表字段沿用；
     * 3. 条件1和2都不满足，表示当前表字段被删除；
     *
     * @param fromTable The original version of the table
     * @param toTable The new version of the table
     * @param fromColumn The original version of the column
     * @return The name of the column in the new version of the database. Will return a null
     * value if the column was deleted.
     */
    private fun detectColumnLevelChanges(
        fromTable: EntityBundle,
        toTable: EntityBundle,
        fromColumn: FieldBundle,
    ): String? {
        // Check if this column was renamed. If so, no need to check further, we can mark this
        // table as a complex change and include the renamed column.
        val renamedToColumn = isColumnRenamed(fromColumn.columnName, fromTable.tableName)
        if (renamedToColumn != null) {
            val renamedColumnsMap = mutableMapOf(
                renamedToColumn.newColumnName to fromColumn.columnName
            )
            // Make sure there are no conflicts in the new version of the table with the
            // temporary new table name
            //新版数据库不要出现之前的表名拼接了“_new_”前缀 后的新表名，表示冲突
            if (toSchemaBundle.entitiesByTableName.containsKey(toTable.newTableName)) {
                diffError(tableWithConflictingPrefixFound(toTable.newTableName))
            }

            renamedTables.remove(fromTable.tableName)

            //表字段更改了，那么当前表当然也算做了更改
            complexChangedTables[fromTable.tableName] =
                AutoMigration.ComplexChangedTable(
                    tableName = fromTable.tableName,
                    tableNameWithNewPrefix = toTable.newTableName,
                    oldVersionEntityBundle = fromTable,
                    newVersionEntityBundle = toTable,
                    renamedColumnsMap = renamedColumnsMap
                )
            return renamedToColumn.newColumnName
        }
        // The column was not renamed. So we check if the column was deleted, and
        // if not, we check for column level complex changes.
        //当前表字段不存在于修改表字段注解中（@RenameColumn），存在于新表中，表示沿用；
        val match = toTable.fieldsByColumnName[fromColumn.columnName]
        if (match != null) {
            val columnChanged = !match.isSchemaEqual(fromColumn)
            //如果表字段改变了，并且当前 改变表集合中不存在该表信息
            if (columnChanged && !complexChangedTables.containsKey(fromTable.tableName)) {
                // Make sure there are no conflicts in the new version of the table with the
                // temporary new table name
                if (toSchemaBundle.entitiesByTableName.containsKey(toTable.newTableName)) {
                    diffError(tableWithConflictingPrefixFound(toTable.newTableName))
                }
                renamedTables.remove(fromTable.tableName)
                complexChangedTables[fromTable.tableName] =
                    AutoMigration.ComplexChangedTable(
                        tableName = fromTable.tableName,
                        tableNameWithNewPrefix = toTable.newTableName,
                        oldVersionEntityBundle = fromTable,
                        newVersionEntityBundle = toTable,
                        renamedColumnsMap = mutableMapOf()
                    )
            }
            return match.columnName
        }

        val isColumnDeleted = deletedColumns.any {
            it.tableName == fromTable.tableName && it.columnName == fromColumn.columnName
        }

        //旧数据库中的表字段，如果没有更改表字段名称（使用@RenameColumn注解）并且当前表字段在新数据库中的表中不存在，那么该表字段必须存在于@DeleteColumn注解中，@DeleteColumn#tableName表示表名，@DeleteColumn#columnName表示被删除的表字段；
        if (!isColumnDeleted) {
            // We have encountered an ambiguous scenario, need more input from the user.
            diffError(
                deletedOrRenamedColumnFound(
                    className = className,
                    tableName = fromTable.tableName,
                    columnName = fromColumn.columnName
                )
            )
        }

        // Column was deleted
        return null
    }

    /**
     * Checks for complex schema changes at a Table level and returns a ComplexTableChange
     * including information on which table changes were found on, and whether foreign key or
     * index related changes have occurred.
     *
     * 判断表是否做了结构更改，如果是则返回true
     *
     * @param fromTable The original version of the table
     * @param toTable The new version of the table
     * @return A ComplexChangedTable object, null if complex schema change has not been found
     */
    private fun tableContainsComplexChanges(
        fromTable: EntityBundle,
        toTable: EntityBundle
    ): Boolean {
        // If we have an FTS table, check if options have changed
        if (fromTable is FtsEntityBundle &&
            toTable is FtsEntityBundle &&
            !fromTable.ftsOptions.isSchemaEqual(toTable.ftsOptions)
        ) {
            return true
        }
        // Check if the to table or the from table is an FTS table while the other is not.
        if (fromTable is FtsEntityBundle && toTable !is FtsEntityBundle ||
            toTable is FtsEntityBundle && fromTable !is FtsEntityBundle
        ) {
            return true
        }

        if (!isForeignKeyBundlesListEqual(fromTable.foreignKeys, toTable.foreignKeys)) {
            return true
        }
        if (!isIndexBundlesListEqual(fromTable.indices, toTable.indices)) {
            return true
        }

        if (!fromTable.primaryKey.isSchemaEqual(toTable.primaryKey)) {
            return true
        }
        // Check if any foreign keys are referencing a renamed table.
        //当前外键是否引用新命名的表
        return fromTable.foreignKeys.any { foreignKey ->
            renameTableEntries.any {
                it.originalTableName == foreignKey.table
            }
        }
    }

    /**
     * Throws a DiffException with the provided error message.
     *
     * @param errorMsg Error message to be thrown with the exception
     */
    private fun diffError(errorMsg: String): Nothing {
        throw DiffException(errorMsg)
    }

    /**
     * Takes in two ForeignKeyBundle lists, attempts to find potential matches based on the columns
     * of the Foreign Keys. Processes these potential matches by checking for schema equality.
     *
     * 判断两个外键信息是否更改，false表示更改；true表示未更改；
     *
     * @param fromBundle List of foreign keys in the old schema version
     * @param toBundle List of foreign keys in the new schema version
     * @return true if the two lists of foreign keys are equal
     */
    private fun isForeignKeyBundlesListEqual(
        fromBundle: List<ForeignKeyBundle>,
        toBundle: List<ForeignKeyBundle>
    ): Boolean {
        val set = fromBundle + toBundle
        val matches = set.groupBy { it.columns }.entries

        matches.forEach { (_, bundles) ->
            if (bundles.size < 2) {
                // A bundle was not matched at all, there must be a change between two versions
                return false
            }
            val fromForeignKeyBundle = bundles[0]
            val toForeignKeyBundle = bundles[1]
            if (!fromForeignKeyBundle.isSchemaEqual(toForeignKeyBundle)) {
                // A potential match for a bundle was found, but schemas did not match
                return false
            }
        }
        return true
    }

    /**
     * Takes in two IndexBundle lists, attempts to find potential matches based on the names
     * of the indexes. Processes these potential matches by checking for schema equality.
     *
     * 判断两个索引信息是否更改，false表示更改；true表示未更改；
     *
     * @param fromBundle List of indexes in the old schema version
     * @param toBundle List of indexes in the new schema version
     * @return true if the two lists of indexes are equal
     */
    private fun isIndexBundlesListEqual(
        fromBundle: List<IndexBundle>,
        toBundle: List<IndexBundle>
    ): Boolean {
        val set = fromBundle + toBundle
        val matches = set.groupBy { it.name }.entries

        matches.forEach { bundlesWithSameName ->
            if (bundlesWithSameName.value.size < 2) {
                // A bundle was not matched at all, there must be a change between two versions
                return false
            } else if (!bundlesWithSameName.value[0].isSchemaEqual(bundlesWithSameName.value[1])) {
                // A potential match for a bundle was found, but schemas did not match
                return false
            }
        }
        return true
    }

    /**
     * Checks if the table provided has been renamed in the new version of the database.
     *
     * 更改表名，首先确保表名不会和当前数据库其他表名冲突：
     *
     * 1. @RenameTable修改表对象没有找到，返回null；
     * 2. @RenameTable修改表对象找到了一个，返回当前对象；
     * 3. @RenameTable#fromTableName属性值相同的情况不允许出现，当前表示对同一个表做了多次重命名操作
     *
     * @param tableName Name of the table in the original database version
     * @return A RenameTable object if the table has been renamed, otherwise null
     */
    private fun isTableRenamed(tableName: String): AutoMigration.RenamedTable? {
        val annotations = renameTableEntries
        val renamedTableAnnotations = annotations.filter {
            it.originalTableName == tableName
        }

        // Make sure there aren't multiple renames on the same table
        //@RenameTable可以多次使用，@RenameTable#fromTableName属性值相同的情况不允许出现，因为这样表示对同一个表做了多次重命名操作；
        if (renamedTableAnnotations.size > 1) {
            diffError(
                conflictingRenameTableAnnotationsFound(
                    renamedTableAnnotations.joinToString(",")
                )
            )
        }
        return renamedTableAnnotations.firstOrNull()
    }

    /**
     * Checks if the column provided has been renamed in the new version of the database.
     *
     * @param columnName Name of the column in the original database version
     * @param tableName Name of the table the column belongs to in the original database version
     * @return A RenameColumn object if the column has been renamed, otherwise null
     */
    private fun isColumnRenamed(
        columnName: String,
        tableName: String
    ): AutoMigration.RenamedColumn? {
        //旧版本表中的表字段 去匹配@RenameColumn#fromColumnName中的所有字段，
        val annotations = renameColumnEntries
        val renamedColumnAnnotations = annotations.filter {
            it.originalColumnName == columnName && it.tableName == tableName
        }

        // Make sure there aren't multiple renames on the same column
        //@RenameColumn注解可以有多个，如果出现@RenameColumn#tableName相同表示操作同一个表，那么这种情况下@RenameColumn#fromColumnName不允许相同，这样表示同一个表重命名同一个表字段，是不被允许的；
        if (renamedColumnAnnotations.size > 1) {
            diffError(
                conflictingRenameColumnAnnotationsFound(renamedColumnAnnotations.joinToString(","))
            )
        }
        return renamedColumnAnnotations.firstOrNull()
    }

    /**
     * Looks for any new tables and columns that have been added between versions.
     *
     * 查找在版本之间添加的任何新表和列。
     *
     * @param toTable The new version of the table
     * @param processedTablesAndColumnsInNewVersion List of all columns in the new version of the
     * database that have been already processed
     */
    private fun processAddedTableAndColumns(
        toTable: EntityBundle,
        processedTablesAndColumnsInNewVersion: MutableMap<String, List<String>>
    ) {
        // Old table bundle will be found even if table is renamed.
        //@RenameTable修改表名集合，该集合中是否能匹配上新数据库中的表名
        val isRenamed = renameTableEntries.firstOrNull {
            it.newTableName == toTable.tableName
        }
        val fromTable = if (isRenamed != null) {
            fromSchemaBundle.entitiesByTableName[isRenamed.originalTableName]
        } else {
            fromSchemaBundle.entitiesByTableName[toTable.tableName]
        }
        //在旧数据库中找存在于新数据库中的表，如果不存在，表示当前表是新家的
        if (fromTable == null) {
            // It's a new table
            addedTables.add(AutoMigration.AddedTable(toTable))
            return
        }
        //当前表存在于旧数据库中，那么在对表字段就行检查，如果不存在于旧数据库表字段中，那么添加；
        val fromColumns = fromTable.fieldsByColumnName
        val toColumns =
            processedTablesAndColumnsInNewVersion[toTable.tableName]?.let { processedColumns ->
                toTable.fieldsByColumnName.filterKeys { !processedColumns.contains(it) }
            } ?: toTable.fieldsByColumnName

        toColumns.values.forEach { toColumn ->
            val match = fromColumns[toColumn.columnName]
            if (match == null) {
                //新增的表字段如果是非空，那么必须有默认值；
                if (toColumn.isNonNull && toColumn.defaultValue == null) {
                    diffError(
                        newNotNullColumnMustHaveDefaultValue(toColumn.columnName)
                    )
                }
                // Check if the new column is on a table with complex changes. If so, no
                // need to account for it as the table will be recreated already with the new
                // table.
                if (!complexChangedTables.containsKey(fromTable.tableName)) {
                    addedColumns.add(
                        AutoMigration.AddedColumn(
                            toTable.tableName,
                            toColumn
                        )
                    )
                }
            }
        }
    }

    /**
     * Goes through the deleted columns list and marks the table of each as a complex changed
     * table if it was not already.
     *
     * 被删除的字段，不存在于表架构更改集合中，那么将其添加到表架构更改集合中
     */
    private fun processDeletedColumns() {
        deletedColumns.filterNot {
            complexChangedTables.contains(it.tableName)
        }.forEach { deletedColumn ->
            val fromTableBundle =
                fromSchemaBundle.entitiesByTableName.getValue(deletedColumn.tableName)
            val toTableBundle =
                toSchemaBundle.entitiesByTableName.getValue(deletedColumn.tableName)
            complexChangedTables[deletedColumn.tableName] =
                AutoMigration.ComplexChangedTable(
                    tableName = deletedColumn.tableName,
                    tableNameWithNewPrefix = fromTableBundle.newTableName,
                    oldVersionEntityBundle = fromTableBundle,
                    newVersionEntityBundle = toTableBundle,
                    renamedColumnsMap = mutableMapOf()
                )
        }
    }
}
