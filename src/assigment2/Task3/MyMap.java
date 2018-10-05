package assigment2.Task3;
import java.util.Random;

/**
 * Created by nicholas on 22/02/2017.
 */
public class MyMap<K,V> implements Map<K,V> {

    private final Entry DEFUNC; //empty element which puts when an entry is removed
    private int numberOfItems;
    private int PRIME;
    private int capacity;
    private final int QUEFFICIENT;
    private final int SHIFT;
    private Entry<K,V>[] table;
    private final double LOAD_FACTOR = 0.75;

    public MyMap() {
        capacity = 97; //initial capacity
        table = new Entry[capacity];
        DEFUNC = new Entry(true);
        numberOfItems = 0;
        //initialising variables for MAD (inspired by the "DSA in java" book, Chapter 10.2)
        PRIME = 299709; // a prime number big enough, gotten from http://oeis.org/A000040/a000040.txt
        Random random = new Random( );
        QUEFFICIENT = random.nextInt(PRIME - 1) + 1;
        SHIFT = random.nextInt(PRIME);
    }

    @Override
    public V get(K key) {
        int position = findSlot(hashCode(key), key);
        if (table[position] == null)
            return null;
        else
            return table[position].getValue();
    }

    @Override
    public void put(K key, V value) {
        manageSize();
        int position  = findSlot(hashCode(key), key);
        if (table[position] != null && table[position].getValue() != null){
            numberOfItems++; //increments number of item only if there was no mapping to the key before
        }
        table[position] = new Entry<>(key, value);

    }

    @Override
    public V remove(K key) {
        int position  = findSlot(hashCode(key), key);
        if(isFree(position))
            return null;
        V value = table[position].getValue();
        table[position] = DEFUNC;
        numberOfItems--;
        return value;
    }

    /**
     * finds an empty slot or a slot with the key starting from position and moving with quadratic probing
     * @param position 
     * @param key
     * @return number of appropirate empty slot in the table
     */
    private int findSlot(int position, K key) {
        int newPosition = position;
        int base = 0; //base for shifting for quadratic probing
        while (!isFree(newPosition%capacity)){
            if (table[newPosition%capacity].getKey().equals(key)){
                return newPosition%capacity; //if found a slot with the key
            }
            base++;
            newPosition = position + base * base;
        }
        return newPosition%capacity;
    }

    /**
     * Chacks if the slot at position contains no entry
     * @param position
     * @return
     */
    private boolean isFree(int position){
        return table[position] == null || table[position].equals(DEFUNC);
    }

    /**
     * Calculates hashCode making native java's hashcode more efficient using MAD technique
     * @param key
     * @return
     */
    private int hashCode(K key) {
        return ((Math.abs(key.hashCode()*QUEFFICIENT + SHIFT) % PRIME) % capacity);
    }

    @Override
    public ArrayList<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> result = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (!isFree(i)){
                result.add(table[i]);
            }
        }
        return result;
    }

    /**
     * extends the table if the load factor is over 0.75
     */
    private void manageSize(){
        if ((double)(numberOfItems/capacity) > LOAD_FACTOR){
            Entry<K,V>[] temp = table;
            capacity = capacity*2 - 1;
            table = new Entry[capacity];

            for (int i = 0; i < temp.length; i++)
                table[i] = temp[i];
        }
    }
    @Override
    public int size() {
        return numberOfItems;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

}
