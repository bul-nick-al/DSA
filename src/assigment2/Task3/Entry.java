package assigment2.Task3;

/**
 * Created by nicholas on 22/02/2017.
 */
public class Entry<K,V> {
    private K key;
    private V value;
    private boolean defunc;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    Entry(boolean defunc) {
        this.defunc = defunc;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
    protected void setKey(K key)
    { this.key = key; }

    protected V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    /**
     * uses for creating a "null" element
     * @return
     */
    public boolean isDefunc() {
        return defunc;
    }
}
