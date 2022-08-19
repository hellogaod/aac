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

package androidx.resourceinspection.annotation;

import androidx.annotation.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Map a getter to its Android resources attribute.
 * <p>
 * This annotation is consumed by the
 * {@code androidx.resourceinspection:resourceinspection-processor} annotation processor to map
 * a getter on a custom {@link android.view.View} back to its XML attribute name and ID. This
 * enables Android Studio's layout inspection tool to read the runtime values of the attributes
 * of a custom {@link android.view.View}.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface Attribute {
    /**
     * The full name of the attribute, including namespace.
     * <p>
     * For example: {@code "android:color"} or {@code "my.library:myAttribute"}.
     *
     * @return the attribute name
     */
    @NonNull String value();

    /**
     * Map semantic names to int values.
     * <p>
     * Populating this field with an array of {@link IntMap} entries provides the layout
     * inspection tool with the semantic names of enumerations stored as integers, instead of the
     * numeric value. This is used for attributes with an {@link androidx.annotation.IntDef}
     * mapping to flags or enumerations.
     * <p>
     * By default, the annotation processor assumes that the mapping defines an enumeration. If
     * any one of the entries has its {@link IntMap#mask()} set, the mapping will be interpreted
     * as flags.
     *
     * @return an array of map entries
     */
    @NonNull IntMap[] intMapping() default {};

    /**
     * One entry in a mapping of int values to enum or flag names.
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({})
    @interface IntMap {
        /**
         * Name of the entry.
         *
         * @return the entry name
         */
        @NonNull String name();

        /**
         * Enumeration ordinal or flag target value.
         *
         * @return the entry value
         */
        int value();

        /**
         * Bit mask for this entry.
         * <p>
         * If set to zero, the default, the annotation processor will assume the bitmask is the
         * same as the target {@link #value()}. Setting this value marks the entire
         * {@link #intMapping()} as flag values.
         *
         * @return the bit
         */
        int mask() default 0;
    }
}
