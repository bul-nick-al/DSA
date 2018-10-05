package assigment3.task1;

/**
 * class for Entry with value and key
 */
public class Entry<K,V> {
    private K key;
    private V value;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
