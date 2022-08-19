/*
 * Copyright 2022 The Android Open Source Project
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

@file:RestrictTo(RestrictTo.Scope.LIBRARY)

package androidx.collection.internal

import androidx.annotation.RestrictTo

@Suppress("ACTUAL_WITHOUT_EXPECT") // https://youtrack.jetbrains.com/issue/KT-37316
internal  class LruHashMap<K : Any, V : Any>  constructor(
    initialCapacity: Int,
    loadFactor: Float,
) {

//     constructor(original: LruHashMap<out K, V>) : this() {
//        for ((key, value) in original.entries) {
//            put(key, value)
//        }
//    }

    private val map = LinkedHashMap<K, V>(initialCapacity, loadFactor, true)

     val isEmpty: Boolean get() = map.isEmpty()
     val entries: Set<Map.Entry<K, V>> get() = map.entries

     operator fun get(key: K): V? = map[key]

     fun put(key: K, value: V): V? = map.put(key, value)

     fun remove(key: K): V? = map.remove(key)
}
