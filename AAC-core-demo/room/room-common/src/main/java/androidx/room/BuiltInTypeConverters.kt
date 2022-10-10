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

package androidx.room

/**
 * Flags to turn on/off extra type converters provided by Room.
 *
 * 用于打开/关闭 Room 提供的额外类型转换器的标志。
 *
 * For certain commonly used types (enums, UUID), Room provides automatic type converters. By
 * default, these type converters are enabled but have lower priority than user provided type
 * converters.
 *
 * You can set these flags in the [TypeConverters] annotation to turn them off / on. It
 * might be useful if you want to have more strict control over how these types are saved into
 * the database.
 */
@Target(allowedTargets = []) // Complex annotation target
@Retention(AnnotationRetention.BINARY)
public annotation class BuiltInTypeConverters(
    /**
     * Controls whether Room can generate a TypeConverter for enum types and use their
     * `name()` in the database.
     *
     * 控制 Room 是否可以为枚举类型生成 TypeConverter 并在数据库中使用它们的 `name()`。
     *
     * By default, it is set to [State.INHERITED] (on by default unless set to another
     * value in a higher scope).
     */
    val enums: State = State.INHERITED,

    /**
     * Controls whether Room can generate a TypeConverter for [java.util.UUID] and use its
     * `byte[]` representation while saving it into database.
     *
     * 控制 Room 是否可以为 [java.util.UUID] 生成 TypeConverter 并在将其保存到数据库时使用其 `byte[]` 表示。
     *
     * By default, it is set to [State.INHERITED] (on by default unless set to another
     * value in a higher scope).
     */
    val uuid: State = State.INHERITED,
) {
    /**
     * Control flags for built in converters.
     */
    public enum class State {
        /**
         * Room can use the built in converter.
         *
         * room可以使用内置转换器
         */
        ENABLED,

        /**
         * Room cannot use the built in converter.
         *
         * room不可以使用内置转换器
         */
        DISABLED,

        /**
         * The value is inherited from the higher scope. See [TypeConverters] documentation
         * to learn more about [TypeConverter] scoping.
         * If this value is never set, it defaults to [ENABLED].
         *
         * 根据之前的高一级范围继承过来的，之前高一级从未设置，那么默认是enabled
         */
        INHERITED
    }
}
