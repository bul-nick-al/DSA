package assigment2.Task3;

/**
 * Created by nicholas on 22/02/2017.
 */
public interface Map<K,V> {
    /**
     * return the number of items in the map
     * @return
     */
    int size();

    /**
     * @return true if the map has no items
     */
    boolean isEmpty();

    /**
     * @return value mapped to @param key
     */
    V get(K key);

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * Removes the mapping for the specified key from this map if present
     */
    V remove(K key);

    /**
     *Puts all entries in the map to the list
     * @return
     */
    ArrayList<Entry<K,V>>entrySet();
}
