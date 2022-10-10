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

package androidx.room.vo

import androidx.room.FtsOptions.MatchInfo
import androidx.room.FtsOptions.Order
import androidx.room.migration.bundle.FtsOptionsBundle
import java.util.Locale
//@Fts3或@Fts4修饰的注解生成的对象
data class FtsOptions(
    val tokenizer: String,//@Fts3#tokenizer或@Fts4#tokenizer中的属性值
    val tokenizerArgs: List<String>,//@Fts3#tokenizerArgs或@Fts4#tokenizerArgs中的属性值
    val contentEntity: Entity?,//如果是@Fst3注解，该属性为null；@Fts4#contentEntity属性
    val languageIdColumnName: String,//如果是@Fst3注解，该属性为null；@Fts4#languageId属性
    val matchInfo: MatchInfo,//如果是@Fst3注解，该属性为MatchInfo.FTS4；否则，@Fts4#matchInfo属性，默认是MatchInfo.FTS4
    val notIndexedColumns: List<String>,//如果是@Fst3注解，该属性为null；@Fts4#notIndexed属性
    val prefixSizes: List<Int>,//如果是@Fst3注解，该属性为null；@Fts4#prefix属性
    val preferredOrder: Order//如果是@Fst3注解，该属性为Order.ASC；否则，@Fts4#order属性，默认是Order.ASC
) : HasSchemaIdentity {

    override fun getIdKey(): String {
        val identityKey = SchemaIdentityKey()
        identityKey.append(tokenizer)
        identityKey.append(tokenizerArgs.joinToString())
        identityKey.append(contentEntity?.tableName ?: "")
        identityKey.append(languageIdColumnName)
        identityKey.append(matchInfo.name)
        identityKey.append(notIndexedColumns.joinToString())
        identityKey.append(prefixSizes.joinToString { it.toString() })
        identityKey.append(preferredOrder.name)
        return identityKey.hash()
    }

    fun databaseDefinition(includeTokenizer: Boolean = true): List<String> {
        return mutableListOf<String>().apply {
            if (includeTokenizer && (
                tokenizer != androidx.room.FtsOptions.TOKENIZER_SIMPLE ||
                    tokenizerArgs.isNotEmpty()
                )
            ) {
                val tokenizeAndArgs = listOf("tokenize=$tokenizer") +
                    tokenizerArgs.map { "`$it`" }
                add(tokenizeAndArgs.joinToString(separator = " "))
            }

            if (contentEntity != null) {
                add("content=`${contentEntity.tableName}`")
            }

            if (languageIdColumnName.isNotEmpty()) {
                add("languageid=`$languageIdColumnName`")
            }

            if (matchInfo != MatchInfo.FTS4) {
                add("matchinfo=${matchInfo.name.lowercase(Locale.US)}")
            }

            notIndexedColumns.forEach {
                add("notindexed=`$it`")
            }

            if (prefixSizes.isNotEmpty()) {
                add("prefix=`${prefixSizes.joinToString(separator = ",") { it.toString() }}`")
            }

            if (preferredOrder != Order.ASC) {
                add("order=$preferredOrder")
            }
        }
    }

    fun toBundle() = FtsOptionsBundle(
        tokenizer,
        tokenizerArgs,
        contentEntity?.tableName ?: "",
        languageIdColumnName,
        matchInfo.name,
        notIndexedColumns,
        prefixSizes,
        preferredOrder.name
    )

    companion object {
        val defaultTokenizers = listOf(
            androidx.room.FtsOptions.TOKENIZER_SIMPLE,
            androidx.room.FtsOptions.TOKENIZER_PORTER,
            // Even though ICU is one of the default tokenizer in Room's API and in Android, the
            // SQLite JDBC library is not compiled with ICU turned ON and Room will fail to create
            // the table, therefore treat it as a custom tokenizer. b/201753224
            // androidx.room.FtsOptions.TOKENIZER_ICU,
            androidx.room.FtsOptions.TOKENIZER_UNICODE61
        )
    }
}