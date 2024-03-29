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

import androidx.room.compiler.processing.XMethodElement
import androidx.room.compiler.processing.XType
import com.squareup.javapoet.TypeName

/**
 * Generated when we parse a method annotated with TypeConverter.
 *
 * @TypeConverter修饰的方法，该方法有且仅有一个参数，方法使用public修饰，
 * 返回类型不能是void，none，error，
 * 如果是泛型，必须是如List<String>而不是List<T>格式；
 */
data class CustomTypeConverter(
    val enclosingClass: XType,//方法所在类
    val isEnclosingClassKotlinObject: Boolean,//方法所在类 `object`或`companion object`kotlin类型
    val method: XMethodElement,//@TypeConverter修饰的方法
    val from: XType,//方法参数：当前方法所在类
    val to: XType,//方法返回类型
    val isProvidedConverter: Boolean//当前方法所在类是否使用了@ProvidedTypeConverter修饰
) {
    val typeName: TypeName by lazy { enclosingClass.typeName }
    val fromTypeName: TypeName by lazy { from.typeName }
    val toTypeName: TypeName by lazy { to.typeName }
    val methodName by lazy { method.jvmName }
    val isStatic by lazy { method.isStatic() }
}
