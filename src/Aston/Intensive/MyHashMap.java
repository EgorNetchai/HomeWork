package Aston.Intensive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

public class MyHashMap<K, V> {
    private int capacity;
    private final float loadFactor;
    private int length;
    private LinkedList<Entry<K, V>>[] buckets;

    private static class Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    MyHashMap() {
        capacity = 16;
        loadFactor = 0.75f;
        length = 0;
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    @SuppressWarnings("unchecked")
    MyHashMap(int initCapacity) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        capacity = initCapacity;
        loadFactor = 0.75f;
        length = 0;
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    @SuppressWarnings("unchecked")
    MyHashMap(int initCapacity, float loadFactor) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        if (loadFactor < 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be positive");
        }
        capacity = initCapacity;
        this.loadFactor = loadFactor;
        length = 0;
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        int h = key == null ? 0 : key.hashCode();
        return Math.abs(h % capacity);
    }

    private void putInternal(K key, V value) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        length++;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if (capacity > Integer.MAX_VALUE / 2) {
            throw new IllegalStateException("HashMap capacity overflow");
        }

        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
        capacity *= 2;
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
        length = 0;

        for (LinkedList<Entry<K, V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    putInternal(entry.key, entry.value);
                }
            }
        }
    }

    public void put(K key, V value) {
        if (length >= capacity * loadFactor) {
            resize();
        }
        putInternal(key, value);
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {
            if (Objects.equals(bucket.get(i).key, key)) {
                bucket.remove(i);
                length--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return length;
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void clear() {
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            bucket.clear();
        }
        length = 0;
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    keys.add(entry.key);
                }
            }
        }
        return keys;
    }

    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                values.add(entry.value);
            }
        }
        return values;
    }

    public boolean containsValue(V value) {
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (Objects.equals(entry.value, value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public V replace(K key, V value) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        return null;
    }

    public boolean replace(K key, V oldValue, V newValue) {
        if (Objects.equals(oldValue, newValue)) {
            return false;
        }
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                if (!Objects.equals(entry.value, oldValue)) {
                    return false;
                } else {
                    entry.value = newValue;
                    return true;
                }
            }
        }
        return false;
    }

    public V getOrDefault(K key, V defaultValue) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if(Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return defaultValue;
    }

    public void putIfAbsent(K key, V value) {
        if (!containsKey(key)) {
            put(key, value);
        }
    }
}
