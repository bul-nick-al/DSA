package assigment3.task2;

/**
 * class for Entry with value and key
 */
public class Entry<K,V> {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;
    private Entry<K, V> leftChild, rightChild, parent;

    public Entry<K, V> getParent() {
        return parent;
    }

    public void setParent(Entry<K, V> parent) {
        this.parent = parent;
    }

    private K key;
    private V value;
    private boolean color; //true if red false if black

    /**
     *
     * @return
     */
    public boolean getColor() {
        return color;
    }

    public Entry<K, V> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Entry<K, V> leftChild) {
        this.leftChild = leftChild;
    }

    public Entry<K, V> getRightChild() {
        return rightChild;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setRightChild(Entry<K, V> rightChild) {
        this.rightChild = rightChild;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void makeRed() {
        this.color = RED;

    }
    public void makeBlack() {
        this.color = BLACK;
    }

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
        leftChild = null;
        rightChild = null;
    }
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}