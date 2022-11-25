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

package androidx.room.processor

import androidx.room.DeleteColumn
import androidx.room.DeleteTable
import androidx.room.ProvidedAutoMigrationSpec
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.compiler.processing.XType
import androidx.room.ext.RoomTypeNames
import androidx.room.migration.bundle.DatabaseBundle
import androidx.room.processor.ProcessorErrors.AUTOMIGRATION_SPEC_MUST_BE_CLASS
import androidx.room.processor.ProcessorErrors.INNER_CLASS_AUTOMIGRATION_SPEC_MUST_BE_STATIC
import androidx.room.processor.ProcessorErrors.autoMigrationElementMustImplementSpec
import androidx.room.processor.ProcessorErrors.autoMigrationToVersionMustBeGreaterThanFrom
import androidx.room.util.DiffException
import androidx.room.util.SchemaDiffer
import androidx.room.vo.AutoMigration

// TODO: (b/183435544) Support downgrades in AutoMigrations.
class AutoMigrationProcessor(
    val context: Context,
    val spec: XType,
    val fromSchemaBundle: DatabaseBundle,
    val toSchemaBundle: DatabaseBundle
) {
    /**
     * Retrieves two schemas of the same database provided in the @AutoMigration annotation,
     * detects the schema changes that occurred between the two versions.
     *
     * @return the AutoMigrationResult containing the schema changes detected
     */
    fun process(): AutoMigration? {

        val (specElement, isSpecProvided) = if (!spec.isTypeOf(Any::class)) {
            val typeElement = spec.typeElement
            //@AutoMigratio#spec如果不是Any类型（默认是Any类型），那么必须是一个类
            if (typeElement == null) {
                context.logger.e(AUTOMIGRATION_SPEC_MUST_BE_CLASS)
                return null
            }
            //不是抽象类或接口
            if (typeElement.isInterface() || typeElement.isAbstract()) {
                context.logger.e(typeElement, AUTOMIGRATION_SPEC_MUST_BE_CLASS)
                return null
            }

            //@AutoMigratio#spec属性中的类如果没有使用@ProvidedAutoMigrationSpec修饰，那么当前类如果存在构造函数，构造函数不允许存在参数；
            val isSpecProvided = typeElement.hasAnnotation(ProvidedAutoMigrationSpec::class)
            if (!isSpecProvided) {
                val constructors = typeElement.getConstructors()
                context.checker.check(
                    constructors.isEmpty() || constructors.any { it.parameters.isEmpty() },
                    typeElement,
                    ProcessorErrors.AUTOMIGRATION_SPEC_MISSING_NOARG_CONSTRUCTOR
                )
            }

            //@AutoMigratio#spec属性中的类如果是内部类，必须是static修饰的静态内部类；
            context.checker.check(
                typeElement.enclosingTypeElement == null || typeElement.isStatic(),
                typeElement,
                INNER_CLASS_AUTOMIGRATION_SPEC_MUST_BE_STATIC
            )

            //@AutoMigratio#spec属性中的类必须继承`androidx.room.migration.AutoMigrationSpec`
            val implementsMigrationSpec =
                context.processingEnv.requireType(RoomTypeNames.AUTO_MIGRATION_SPEC)
                    .isAssignableFrom(spec)
            if (!implementsMigrationSpec) {
                context.logger.e(
                    typeElement,
                    autoMigrationElementMustImplementSpec(typeElement.className.simpleName())
                )
                return null
            }
            typeElement to isSpecProvided
        } else {
            null to false
        }

        if (toSchemaBundle.version <= fromSchemaBundle.version) {
            context.logger.e(
                autoMigrationToVersionMustBeGreaterThanFrom(
                    toSchemaBundle.version,
                    fromSchemaBundle.version
                )
            )
            return null
        }

        //表示迁移数据后，当前表需要删减的表字段
        val specClassName = specElement?.className?.simpleName()
        val deleteColumnEntries = specElement?.let { element ->
            element.getAnnotations(DeleteColumn::class).map {
                AutoMigration.DeletedColumn(
                    tableName = it.value.tableName,
                    columnName = it.value.columnName
                )
            }
        } ?: emptyList()

        //表示迁移数据后，当前需要删减的表
        val deleteTableEntries = specElement?.let { element ->
            element.getAnnotations(DeleteTable::class).map {
                AutoMigration.DeletedTable(
                    deletedTableName = it.value.tableName
                )
            }
        } ?: emptyList()

        //表示迁移数据后，修改表名
        val renameTableEntries = specElement?.let { element ->
            element.getAnnotations(RenameTable::class).map {
                AutoMigration.RenamedTable(
                    originalTableName = it.value.fromTableName,
                    newTableName = it.value.toTableName
                )
            }
        } ?: emptyList()

        //表示迁移数据后，修改表字段名
        val renameColumnEntries = specElement?.let { element ->
            element.getAnnotations(RenameColumn::class).map {
                AutoMigration.RenamedColumn(
                    tableName = it.value.tableName,
                    originalColumnName = it.value.fromColumnName,
                    newColumnName = it.value.toColumnName
                )
            }
        } ?: emptyList()

        val schemaDiff = try {
            SchemaDiffer(
                fromSchemaBundle = fromSchemaBundle,
                toSchemaBundle = toSchemaBundle,
                className = specClassName,
                deleteColumnEntries = deleteColumnEntries,
                deleteTableEntries = deleteTableEntries,
                renameTableEntries = renameTableEntries,
                renameColumnEntries = renameColumnEntries
            ).diffSchemas()
        } catch (ex: DiffException) {
            context.logger.e(ex.errorMessage)
            return null
        }

        return AutoMigration(
            from = fromSchemaBundle.version,
            to = toSchemaBundle.version,
            schemaDiff = schemaDiff,
            specElement = specElement,
            isSpecProvided = isSpecProvided,
        )
    }
}
