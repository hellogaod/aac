/*
 * Copyright 2018 The Android Open Source Project
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

package androidx.room.solver.prepared.result

import androidx.room.ext.KotlinTypeNames
import androidx.room.ext.L
import androidx.room.ext.N
import androidx.room.ext.T
import androidx.room.parser.QueryType
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.isInt
import androidx.room.compiler.processing.isKotlinUnit
import androidx.room.compiler.processing.isLong
import androidx.room.compiler.processing.isVoid
import androidx.room.compiler.processing.isVoidObject
import androidx.room.solver.CodeGenScope
import androidx.room.solver.prepared.binder.PreparedQueryResultBinder
import com.squareup.javapoet.FieldSpec

/**
 * An adapter for [PreparedQueryResultBinder] that executes queries with INSERT, UPDATE or DELETE
 * statements.
 *
 * （1）如果返回类型是void ，Void 或kotlin [Unit] type类型生成当前适配对象；
 * （2）insert，如果返回类型是long生成适配对象；
 * （3）update或delete，如果返回类型是int生成当前适配对象；
 * （4）其他情况不生成适配对象；
 */
class PreparedQueryResultAdapter(
    private val returnType: XType,
    private val queryType: QueryType
) {
    companion object {
        fun create(
            returnType: XType,
            queryType: QueryType
        ) = if (isValidReturnType(returnType, queryType)) {
            PreparedQueryResultAdapter(returnType, queryType)
        } else {
            null
        }

        //方法返回类型是void ，voidObject 或KotlinUnit 都返回true;
        //如果以上条件不满足，那么判断sql类型：
        //(1)如果是insert，那么返回类型是long或Long，返回true；否则返回false；
        //(2)如果是update或delete，那么返回类型是int或Integer，返回true；否则返回false；
        //(3)其他情况一律返回false；
        private fun isValidReturnType(returnType: XType, queryType: QueryType): Boolean {
            if (returnType.isVoid() || returnType.isVoidObject() || returnType.isKotlinUnit()) {
                return true
            } else {
                return when (queryType) {
                    QueryType.INSERT -> returnType.isLong()
                    QueryType.UPDATE, QueryType.DELETE -> returnType.isInt()
                    else -> false
                }
            }
        }
    }

    fun executeAndReturn(
        stmtQueryVal: String,
        preparedStmtField: String?,
        dbField: FieldSpec,
        scope: CodeGenScope
    ) {
        scope.builder().apply {
            val stmtMethod = if (queryType == QueryType.INSERT) {
                "executeInsert"
            } else {
                "executeUpdateDelete"
            }
            addStatement("$N.beginTransaction()", dbField)
            beginControlFlow("try").apply {
                if (returnType.isVoid() || returnType.isVoidObject() || returnType.isKotlinUnit()) {
                    addStatement("$L.$L()", stmtQueryVal, stmtMethod)
                    addStatement("$N.setTransactionSuccessful()", dbField)
                    if (returnType.isVoidObject()) {
                        addStatement("return null")
                    } else if (returnType.isKotlinUnit()) {
                        addStatement("return $T.INSTANCE", KotlinTypeNames.UNIT)
                    }
                } else {
                    val resultVar = scope.getTmpVar("_result")
                    addStatement(
                        "final $L $L = $L.$L()",
                        returnType.typeName, resultVar, stmtQueryVal, stmtMethod
                    )
                    addStatement("$N.setTransactionSuccessful()", dbField)
                    addStatement("return $L", resultVar)
                }
            }
            nextControlFlow("finally").apply {
                addStatement("$N.endTransaction()", dbField)
                if (preparedStmtField != null) {
                    addStatement("$N.release($L)", preparedStmtField, stmtQueryVal)
                }
            }
            endControlFlow()
        }
    }
}