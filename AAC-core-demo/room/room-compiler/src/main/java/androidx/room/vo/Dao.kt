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

import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XTypeElement
import androidx.room.compiler.processing.isTypeElement
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName

data class Dao(
    val element: XTypeElement,//dao节点
    val type: XType,//dao节点类型
    val queryMethods: List<QueryMethod>,//query方法
    val rawQueryMethods: List<RawQueryMethod>,//requery方法
    val insertionMethods: List<InsertionMethod>,//insert方法
    val deletionMethods: List<DeletionMethod>,//delete方法
    val updateMethods: List<UpdateMethod>,//update方法
    val transactionMethods: List<TransactionMethod>,//transaction方法
    //dao节点存在继承的接口或类，如果排除dao方法和transaction方法以外的方法，该方法存在于dao方法和transaction方法中（参数或返回类型存在包装被包装关系）
    val delegatingMethods: List<KotlinBoxedPrimitiveMethodDelegate>,
    val kotlinDefaultMethodDelegates: List<KotlinDefaultMethodDelegate>,//如果dao节点是接口，那么除了dao方法和transaction方法以外kotlin默认实现方法
    val constructorParamType: TypeName?//
) {
    // parsed dao might have a suffix if it is used in multiple databases.
    private var suffix: String? = null

    fun setSuffix(newSuffix: String) {
        if (this.suffix != null) {
            throw IllegalStateException("cannot set suffix twice")
        }
        this.suffix = if (newSuffix == "") "" else "_$newSuffix"
    }

    val typeName: ClassName by lazy { element.className }

    val shortcutMethods: List<ShortcutMethod> by lazy {
        deletionMethods + updateMethods
    }

    //dao节点生成的类名
    private val implClassName by lazy {
        if (suffix == null) {
            suffix = ""
        }
        val path = arrayListOf<String>()
        var enclosing = element.enclosingTypeElement
        while (enclosing?.isTypeElement() == true) {
            path.add(enclosing!!.name)
            enclosing = enclosing!!.enclosingTypeElement
        }
        path.reversed().joinToString("_") + "${typeName.simpleName()}${suffix}_Impl"
    }

    val implTypeName: ClassName by lazy {
        ClassName.get(typeName.packageName(), implClassName)
    }
}
