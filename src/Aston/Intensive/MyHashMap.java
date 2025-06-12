package Aston.Intensive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

/**
 * Реализация хэш-таблицы для хранения пар ключ-значение.
 * Использует метод цепочек с LinkedList для обработки коллизий.
 * Поддерживает основные операции: put, get, remove и другие.
 *
 * @param <K> тип ключей, поддерживаемых этой хэш-таблицей
 * @param <V> тип значений, поддерживаемых этой хэщ-таблицей
 */
public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final float loadFactor;
    private int capacity;
    private int length;
    private LinkedList<Entry<K, V>>[] buckets;

    /**
     * Внутренний класс, представляющий пару ключ-значение.
     */
    private static class Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Создает пустую хэш-таблицу с начальной емкостью (16) и коэффициентом загрузки (0.75).
     */
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        capacity = DEFAULT_CAPACITY;
        loadFactor = DEFAULT_LOAD_FACTOR;
        length = 0;
        buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Создает пустую хэш-таблицу с указанной начальной емкостью и коэффициентом загрузки по умолчанию (0.75).
     *
     * @param initCapacity начальная емкость
     * @throws IllegalArgumentException если начальная емкость отрицательна
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(int initCapacity) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }

        capacity = initCapacity;
        loadFactor = DEFAULT_LOAD_FACTOR;
        length = 0;
        buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Создает пустую хэш-таблицу с указанной начальной емкостью и коэффициентом загрузки.
     *
     * @param initCapacity начальная емкость
     * @param loadFactor   коэффициент загрузки
     * @throws IllegalArgumentException если начальная емкость отрицательна или коэффициент загрузки некорректен
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(int initCapacity, float loadFactor) {
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

    /**
     * Связывает указанное значение с указанным ключом в этой хэш-таблице.
     * Если хэш-таблица уже содержит ключ, старое значение заменяется.
     *
     * @param key   ключ, с которым связано значение
     * @param value значение, связываемое с ключом
     */
    public void put(K key, V value) {
        if (length >= capacity * loadFactor) {
            resize();
        }
        putInternal(key, value);
    }

    /**
     * Возвращает значение, связанное с указанным ключом, или null, если ключ отсутствует.
     *
     * @param key ключ, чье значение нужно вернуть
     * @return значение, связанное с ключом, или null, если ключ не найден
     */
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

    /**
     * Удаляет пару ключ-значение для указанного ключа, если он присутствует.
     *
     * @param key ключ, чье значение нужно удалить
     * @return true, если ключ был найден и удален, false в противном случае
     */
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

    /**
     * Возвращает количество пар ключ-значение в этой хэш-таблице.
     *
     * @return количество пар ключ-значение
     */
    public int size() {
        return length;
    }

    /**
     * Проверяет, содержит ли хэш-таблица указанный ключ.
     *
     * @param key ключ, чье присутствие нужно проверить
     * @return true, если ключ найден, false в противном случае
     */
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


    /**
     * Проверяет, пуста ли хэш-таблица.
     *
     * @return true, если хэш-таблица не содержит пар ключ-значение
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Удаляет все записи из хэш-таблицы.
     */
    public void clear() {
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            bucket.clear();
        }
        length = 0;
    }

    /**
     * Возвращает множество всех ключей, содержащихся в хэш-таблице.
     *
     * @return множество всех ключей
     */
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

    /**
     * Возвращает коллекцию всех значений, содержащихся в хэш-таблице.
     *
     * @return коллекция всех значений
     */
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                values.add(entry.value);
            }
        }
        return values;
    }

    /**
     * Проверяет, содержит ли хэш-таблица указанное значение.
     *
     * @param value значение, чье присутствие нужно проверить
     * @return true, если значение найдено, false в противном случае
     */
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

    /**
     * Заменяет значение для указанного ключа, если ключ уже существует.
     *
     * @param key   ключ, с которым связано значение
     * @param value новое значение для ключа
     * @return предыдущее значение, связанное с ключом, или null, если ключ не найден
     */
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

    /**
     * Заменяет значение для указанного ключа, если текущее значение совпадает с указанным старым значением.
     *
     * @param key      ключ, с которым связано значение
     * @param oldValue ожидаемое текущее значение
     * @param newValue новое значение
     * @return true, если замена выполнена, false в противном случае
     */
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

    /**
     * Возвращает значение, связанное с ключом, или значение по умолчанию, если ключ отсутствует.
     *
     * @param key          ключ, чье значение нужно вернуть
     * @param defaultValue значение по умолчанию, возвращаемое при отсутствии ключа
     * @return значение, связанное с ключом, или значение по умолчанию
     */
    public V getOrDefault(K key, V defaultValue) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return defaultValue;
    }


    /**
     * Связывает значение с ключом, если ключ еще не имеет значения.
     *
     * @param key   ключ, с которым связано значение
     * @param value значение, связываемое с ключом
     */
    public void putIfAbsent(K key, V value) {
        if (!containsKey(key)) {
            put(key, value);
        }
    }

    /**
     * Вычисляет индекс bucket для указанного ключа.
     *
     * @param key ключ для хэширования
     * @return индекс bucket
     */
    private int hash(K key) {
        int h = key == null ? 0 : key.hashCode();
        return Math.abs(h % capacity);
    }

    /**
     * Внутренний метод для вставки или обновления пары ключ-значение.
     *
     * @param key   ключ
     * @param value значение
     */
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

    /**
     * Изменяет размер хэш-таблицы, удваивая емкость и перехэшируя все записи.
     *
     * @throws IllegalStateException если емкость превышает максимально допустимое значение
     */
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
}
