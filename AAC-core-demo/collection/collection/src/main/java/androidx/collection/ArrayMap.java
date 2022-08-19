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

package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.internal.ContainerHelpersKt;

import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * ArrayMap is a generic key->value mapping data structure that is
 * designed to be more memory efficient than a traditional {@link java.util.HashMap},
 * this implementation is a version of the platform's
 * {@code android.util.ArrayMap} that can be used on older versions of the platform.
 * It keeps its mappings in an array data structure -- an integer array of hash
 * codes for each item, and an Object array of the key/value pairs.  This allows it to
 * avoid having to create an extra object for every entry put in to the map, and it
 * also tries to control the growth of the size of these arrays more aggressively
 * (since growing them only requires copying the entries in the array, not rebuilding
 * a hash map).
 *
 * <p>If you don't need the standard Java container APIs provided here (iterators etc),
 * consider using {@link SimpleArrayMap} instead.</p>
 *
 * <p>Note that this implementation is not intended to be appropriate for data structures
 * that may contain large numbers of items.  It is generally slower than a traditional
 * HashMap, since lookups require a binary search and adds and removes require inserting
 * and deleting entries in the array.  For containers holding up to hundreds of items,
 * the performance difference is not significant, less than 50%.</p>
 *
 * <p>Because this container is intended to better balance memory use, unlike most other
 * standard Java containers it will shrink its array as items are removed from it.  Currently
 * you have no control over this shrinking -- if you set a capacity and then remove an
 * item, it may reduce the capacity to better match the current size.  In the future an
 * explicit call to set the capacity should turn off this aggressive shrinking behavior.</p>
 */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    @Nullable
    EntrySet mEntrySet;
    @Nullable
    KeySet mKeySet;
    @Nullable
    ValueCollection mValues;

    public ArrayMap() {
        super();
    }

    /**
     * Create a new ArrayMap with a given initial capacity.
     */
    public ArrayMap(int capacity) {
        super(capacity);
    }

    /**
     * Create a new ArrayMap with the mappings from the given ArrayMap.
     */
    @SuppressWarnings("unchecked")
    public ArrayMap(@Nullable SimpleArrayMap map) {
        super(map);
    }

    /**
     * Determine if the array map contains all of the keys in the given collection.
     *
     * @param collection The collection whose contents are to be checked against.
     * @return Returns true if this array map contains a key for every entry
     * in <var>collection</var>, else returns false.
     */
    public boolean containsAll(@NonNull Collection<?> collection) {
        for (Object o : collection) {
            if (!containsKey(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether a key exists in the array.
     *
     * @param key The key to search for. ** This must be the same type as <var>K</var> **
     * @return Returns {@code true} if the key exists, else {@code false}.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean containsKey(@Nullable Object key) {
        return super.containsKey((K) key);
    }

    /**
     * Check whether a value exists in the array. This requires a linear search
     * through the entire array.
     *
     * @param value The value to search for. ** This must be the same type as <var>V</var> **
     * @return Returns {@code true} if the value exists, else {@code false}.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean containsValue(@Nullable Object value) {
        return super.containsValue((V) value);
    }

    /**
     * Retrieve a value from the array.
     *
     * @param key The key of the value to retrieve. ** This must be the same type as <var>K</var> **
     * @return Returns the value associated with the given key, or {@code null} if there is no such
     * key.
     */
    @Override
    @SuppressWarnings("unchecked")
    public V get(@Nullable Object key) {
        return super.get((K) key);
    }

    /**
     * Remove an existing key from the array map.
     *
     * @param key The key of the mapping to remove. ** This must be the same type as <var>V</var> **
     * @return Returns the value that was stored under the key, or {@code null} if there was no
     * such key.
     */
    @Override
    @SuppressWarnings("unchecked")
    public V remove(@Nullable Object key) {
        return super.remove((K) key);
    }

    /**
     * Perform a {@link #put(Object, Object)} of all key/value pairs in <var>map</var>
     *
     * @param map The map whose contents are to be retrieved.
     */
    @Override
    public void putAll(@NonNull Map<? extends K, ? extends V> map) {
        ensureCapacity(size() + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Remove all keys in the array map that exist in the given collection.
     *
     * @param collection The collection whose contents are to be used to remove keys.
     * @return Returns true if any keys were removed from the array map, else false.
     */
    public boolean removeAll(@NonNull Collection<?> collection) {
        int oldSize = size();
        for (Object o : collection) {
            remove(o);
        }
        return oldSize != size();
    }

    /**
     * Remove all keys in the array map that do <b>not</b> exist in the given collection.
     *
     * @param collection The collection whose contents are to be used to determine which
     *                   keys to keep.
     * @return Returns true if any keys were removed from the array map, else false.
     */
    public boolean retainAll(@NonNull Collection<?> collection) {
        int oldSize = size();
        for (int i = size() - 1; i >= 0; i--) {
            if (!collection.contains(keyAt(i))) {
                removeAt(i);
            }
        }
        return oldSize != size();
    }

    /**
     * Return a {@link java.util.Set} for iterating over and interacting with all mappings
     * in the array map.
     *
     * <p><b>Note:</b> this is a very inefficient way to access the array contents, it
     * requires generating a number of temporary objects.</p>
     *
     * <p><b>Note:</b></p> the semantics of this
     * Set are subtly different than that of a {@link java.util.HashMap}: most important,
     * the {@link java.util.Map.Entry Map.Entry} object returned by its iterator is a single
     * object that exists for the entire iterator, so you can <b>not</b> hold on to it
     * after calling {@link java.util.Iterator#next() Iterator.next}.</p>
     */
    @NonNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = mEntrySet;
        if (entrySet == null) {
            entrySet = mEntrySet = new EntrySet();
        }
        return entrySet;
    }

    /**
     * Return a {@link java.util.Set} for iterating over and interacting with all keys
     * in the array map.
     *
     * <p><b>Note:</b> this is a fairly inefficient way to access the array contents, it
     * requires generating a number of temporary objects.</p>
     */
    @NonNull
    @Override
    public Set<K> keySet() {
        Set<K> keySet = mKeySet;
        if (keySet == null) {
            keySet = mKeySet = new KeySet();
        }
        return keySet;
    }

    /**
     * Return a {@link java.util.Collection} for iterating over and interacting with all values
     * in the array map.
     *
     * <p><b>Note:</b> this is a fairly inefficient way to access the array contents, it
     * requires generating a number of temporary objects.</p>
     */
    @NonNull
    @Override
    public Collection<V> values() {
        Collection<V> values = mValues;
        if (values == null) {
            values = mValues = new ValueCollection();
        }
        return values;
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        @NonNull
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new MapIterator();
        }

        @Override
        public int size() {
            return ArrayMap.this.size();
        }
    }

    final class KeySet implements Set<K> {
        @Override
        public boolean add(K object) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            ArrayMap.this.clear();
        }

        @Override
        public boolean contains(Object object) {
            return containsKey(object);
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> collection) {
            return ArrayMap.this.containsAll(collection);
        }

        @Override
        public boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        @NonNull
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean remove(Object object) {
            int index = indexOfKey((K) object);
            if (index >= 0) {
                removeAt(index);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            return ArrayMap.this.removeAll(collection);
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            return ArrayMap.this.retainAll(collection);
        }

        @Override
        public int size() {
            return ArrayMap.this.size();
        }

        @NonNull
        @Override
        public Object[] toArray() {
            final int N = ArrayMap.this.size();
            Object[] result = new Object[N];
            for (int i = 0; i < N; i++) {
                result[i] = keyAt(i);
            }
            return result;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(@NonNull T[] array) {
            final int mySize = size();
            if (array.length < mySize) {
                array = (T[]) Array.newInstance(array.getClass().getComponentType(), mySize);
            }
            for (int i = 0; i < mySize; i++) {
                array[i] = (T) keyAt(i);
            }
            if (array.length > mySize) {
                array[mySize] = null;
            }
            return array;
        }

        @Override
        public boolean equals(Object object) {
            return equalsSetHelper(this, object);
        }

        @Override
        public int hashCode() {
            int result = 0;
            for (int i = ArrayMap.this.size() - 1; i >= 0; i--) {
                K obj = keyAt(i);
                result += obj == null ? 0 : obj.hashCode();
            }
            return result;
        }
    }

    final class ValueCollection implements Collection<V> {
        @Override
        public boolean add(V object) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            ArrayMap.this.clear();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean contains(Object object) {
            return __restricted$indexOfValue((V) object) >= 0;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object o : collection) {
                if (!contains(o)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        @NonNull
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean remove(Object object) {
            int index = __restricted$indexOfValue((V) object);
            if (index >= 0) {
                removeAt(index);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            int N = ArrayMap.this.size();
            boolean changed = false;
            for (int i = 0; i < N; i++) {
                V cur = valueAt(i);
                if (collection.contains(cur)) {
                    removeAt(i);
                    i--;
                    N--;
                    changed = true;
                }
            }
            return changed;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            int N = ArrayMap.this.size();
            boolean changed = false;
            for (int i = 0; i < N; i++) {
                V cur = valueAt(i);
                if (!collection.contains(cur)) {
                    removeAt(i);
                    i--;
                    N--;
                    changed = true;
                }
            }
            return changed;
        }

        @Override
        public int size() {
            return ArrayMap.this.size();
        }

        @NonNull
        @Override
        public Object[] toArray() {
            final int N = ArrayMap.this.size();
            Object[] result = new Object[N];
            for (int i = 0; i < N; i++) {
                result[i] = valueAt(i);
            }
            return result;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(@NonNull T[] array) {
            final int mySize = size();
            if (array.length < mySize) {
                array = (T[]) Array.newInstance(array.getClass().getComponentType(), mySize);
            }
            for (int i = 0; i < mySize; i++) {
                array[i] = (T) valueAt(i);
            }
            if (array.length > mySize) {
                array[mySize] = null;
            }
            return array;
        }
    }

    final class KeyIterator extends IndexBasedArrayIterator<K> {
        KeyIterator() {
            super(ArrayMap.this.size());
        }

        @Override
        protected K elementAt(int index) {
            return keyAt(index);
        }

        @Override
        protected void removeAt(int index) {
            ArrayMap.this.removeAt(index);
        }
    }

    final class ValueIterator extends IndexBasedArrayIterator<V> {
        ValueIterator() {
            super(ArrayMap.this.size());
        }

        @Override
        protected V elementAt(int index) {
            return valueAt(index);
        }

        @Override
        protected void removeAt(int index) {
            ArrayMap.this.removeAt(index);
        }
    }

    final class MapIterator implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        int mEnd;
        int mIndex;
        boolean mEntryValid;

        MapIterator() {
            mEnd = size() - 1;
            mIndex = -1;
        }

        @Override
        public boolean hasNext() {
            return mIndex < mEnd;
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) throw new NoSuchElementException();
            mIndex++;
            mEntryValid = true;
            return this;
        }

        @Override
        public void remove() {
            if (!mEntryValid) {
                throw new IllegalStateException();
            }
            removeAt(mIndex);
            mIndex--;
            mEnd--;
            mEntryValid = false;
        }

        @Override
        public K getKey() {
            if (!mEntryValid) {
                throw new IllegalStateException(
                        "This container does not support retaining Map.Entry objects");
            }
            return keyAt(mIndex);
        }

        @Override
        public V getValue() {
            if (!mEntryValid) {
                throw new IllegalStateException(
                        "This container does not support retaining Map.Entry objects");
            }
            return valueAt(mIndex);
        }

        @Override
        public V setValue(V object) {
            if (!mEntryValid) {
                throw new IllegalStateException(
                        "This container does not support retaining Map.Entry objects");
            }
            return setValueAt(mIndex, object);
        }

        @Override
        public boolean equals(Object o) {
            if (!mEntryValid) {
                throw new IllegalStateException(
                        "This container does not support retaining Map.Entry objects");
            }
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            return ContainerHelpersKt.equal(e.getKey(), keyAt(mIndex))
                    && ContainerHelpersKt.equal(e.getValue(), valueAt(mIndex));
        }

        @Override
        public int hashCode() {
            if (!mEntryValid) {
                throw new IllegalStateException(
                        "This container does not support retaining Map.Entry objects");
            }
            K key = keyAt(mIndex);
            V value = valueAt(mIndex);
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        @Override
        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    static <T> boolean equalsSetHelper(Set<T> set, Object object) {
        if (set == object) {
            return true;
        }
        if (object instanceof Set) {
            Set<?> s = (Set<?>) object;

            try {
                return set.size() == s.size() && set.containsAll(s);
            } catch (NullPointerException | ClassCastException ignored) {
            }
        }
        return false;
    }
}
