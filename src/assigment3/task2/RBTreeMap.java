package assigment3.task2;

/**
 * Created by nicholas on 23/03/2017.
 */
public class RBTreeMap<K, V> {
    private Entry<K, V> root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private MyComparator<K> comparator = new MyComparator<K>();
    public RBTreeMap() {
    }

    /**
     * @param key
     * @return true if the tree has an entry with key
     */
    public boolean has(K key){
        return get(key) != null;
    }

    /**
     * @return value mapped to @param key. Null if there is no mapping
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException();
        Entry<K, V> current = root;
        while (current != null) {
            if (compare(current, key) > 0) current = current.getLeftChild();
            else if (compare(current, key) < 0) current = current.getRightChild();
            else  return current.getValue();
        }
        return null;
    }
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        Entry<K, V> current = root;
        Entry<K, V> currentParent = null;
        Entry<K, V> inserted = new Entry<K, V>(key, value);
        inserted.makeRed();
        //BST insertion
        while (current != null) {
            if (compare(current, inserted) > 0) {
                currentParent = current;
                current = current.getLeftChild();
            } else if (compare(current, inserted) < 0) {
                currentParent = current;
                current = current.getRightChild();
            } else {
                current.setValue(inserted.getValue());
                inserted = current;
            }
        }
        if (root == null)
            root = inserted;
        else if (compare(currentParent, inserted) > 0)
            connectParentLeftChild(currentParent, inserted);
        else
            connectParentRightChild(currentParent, inserted);
        root.makeBlack(); //root must always stay black
        //rebalancing after insertion
        rebalance(inserted);
    }
    /**
     * rebalances tree according to the properties of RBtree
     * @param current
     */
    private void rebalance(Entry<K, V> current) {
        //rebalance is required only if we have red parent - red child pair
        while (isRed(current)&&isRed(current.getParent())){
            Entry<K,V> grandParent = current.getParent().getParent();
            //case when parent anf uncle are red
            if (isRed(grandParent.getRightChild())&&isRed(grandParent.getLeftChild())){
                grandParent.makeRed();
                grandParent.getRightChild().makeBlack();
                grandParent.getLeftChild().makeBlack();
            }
            //two cases when uncle is black
            else if (current.getParent() == grandParent.getLeftChild()){
                //making grand parent, parent and child lie on one left line
                if (grandParent.getLeftChild().getRightChild() == current){
                    Entry<K,V> temp = current.getParent();
                    connectParentLeftChild(grandParent, current);
                    connectParentRightChild(temp, current.getLeftChild());
                    connectParentLeftChild(current, temp);
                }
                rotateRight(grandParent);
            }
            else if (current.getParent() == grandParent.getRightChild()){
                //making grand parent, parent and child line on one right line
                if (grandParent.getRightChild().getLeftChild() == current){
                    Entry<K,V> temp = current.getParent();
                    connectParentRightChild(grandParent, current);
                    connectParentLeftChild(temp, current.getRightChild());
                    connectParentRightChild(current, temp);
                }
                rotateLeft(grandParent);
            }
            current = grandParent;
        }
        root.makeBlack();
    }

    /**
     * make a left-leaning link lean to the right
     * @param
     */
    private void rotateRight(Entry<K, V> grandParent){
        Entry<K,V> temp = grandParent.getLeftChild();
        temp.makeBlack();
        grandParent.makeRed();
        //handling root case
        if (grandParent.getParent() == null){
            root = temp;
            root.setParent(null);
        }
        //right rotation itself
        else if (grandParent.getParent().getLeftChild() == grandParent){
            connectParentLeftChild(grandParent.getParent(), temp);
        }
        else {
            connectParentRightChild(grandParent.getParent(), temp);
        }
        connectParentLeftChild(grandParent, temp.getRightChild());
        connectParentRightChild(temp, grandParent);
    }

    /**
     * make a right-leaning link lean to the left
     * @param
     */
    private void rotateLeft(Entry<K, V> grandParent){
        Entry<K,V> temp = grandParent.getRightChild();
        temp.makeBlack();
        grandParent.makeRed();
        //handling root case
        if (grandParent.getParent() == null){
            root = temp;
            root.setParent(null);
        }
        //left rotation itself
        else if (grandParent.getParent().getLeftChild() == grandParent){
            connectParentLeftChild(grandParent.getParent(), temp);
        }
        else {
            connectParentRightChild(grandParent.getParent(), temp);
        }
        connectParentRightChild(grandParent, temp.getLeftChild());
        connectParentLeftChild(temp, grandParent);
    }

    private void connectParentLeftChild(Entry<K,V> parent, Entry<K,V> child){
        if (child == null)
            parent.setLeftChild(null);
        else{
            parent.setLeftChild(child);
            child.setParent(parent);
        }
    }
    private void connectParentRightChild(Entry<K,V> parent, Entry<K,V> child){
        if (child == null)
            parent.setRightChild(null);
        else{
            parent.setRightChild(child);
            child.setParent(parent);
        }
    }
    /**
     * compares two elements by key
     */
    private int compare(Entry<K, V> entry1, Entry<K, V> entry2) {
        return comparator.compare(entry1.getKey(), entry2.getKey());
    }
    /**
     * compares element's key with another key
     */
    private int compare(Entry<K, V> entry, K key) {
        return comparator.compare(entry.getKey(), key);
    }

    private boolean isRed(Entry<K, V> entry) {
        return entry != null && entry.getColor() == RED;
    }

    private boolean isBlack(Entry<K, V> entry) {
        return entry == null || entry.getColor() == BLACK;
    }


}
