package assigment3.task1;

import java.util.ArrayList;

/**
 * Class for BinaryHeap ADT
 */
public class BinaryHeap<K,V> {
    private ArrayList<Entry<K,V>> heap = new ArrayList<>();
    private MyComparator<K> comparator = new MyComparator<K>();
    //bounds for priority value
    private K upperBound;
    private K lowerBound;
    private boolean isBounded = false;

    public BinaryHeap() {}
    /**
     * constructor with restrictions for priority
     */
    public BinaryHeap(K lowerBound,K upperBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        isBounded = true;
    }
    /**
     * Inserts a value with a priority key
     */
    public void insert (K key, V value)throws IllegalArgumentException{
        checkBounds(key);
        heap.add (new Entry<K, V>(key, value)); //putting new element at the lowest available position in the tree
        upHeap(heap.size() - 1); //moving the element to the proper position

    }
    /**
     * @return an element with maximal priority
     */
    public Entry<K,V> max(){
        if (! heap.isEmpty())
            return heap.get(0);
        else return null;
    }

    /**
     * removes an element with maximal priority from the heap
     */
    public Entry<K,V> removeMax(){
        if (heap.isEmpty())
            return null;
        //swapping the max element with the lowest one
        Entry<K,V> result = heap.get(0);
        swap(0, size() - 1);
        //removing the max element and restoring the properties of the tree
        heap.remove(size() - 1);
        downHeap(0);
        return result;
    }
    /**
     * moves the element at the given position up until it is less then its parent to restore the heap property
     * @param position
     */
    private void upHeap (int position){
        int parentPosition = parent(position);
        while (parentPosition >= 0 && compare(position, parentPosition) > 0){
            swap(position, parentPosition);
            position = parentPosition;
            parentPosition = parent(position);
        }
    }
    /**
     * moves the element at the given position down until it is greater then its children to restore the heap property
     * @param position
     */
    private void downHeap (int position){
        int leftChild, rightChild, bigger;
        //checking the left child existence
        while (hasLeft(position)){
            leftChild = leftChild(position);
            bigger = leftChild;
            //checking the right child existence
            if(hasRight(position)){
                rightChild = rightChild(position);
                //finding the greatest child
                if (compare(rightChild, leftChild) > 0)
                    bigger = rightChild;
            }
            //comparing the greatest child to the parent
            if (compare(bigger, position) <= 0)
                break;
            swap(position, bigger);
            position = bigger;
        }
    }
    /**
     * @return the position of the parent of the node at position childPosition
     */
    private int parent(int childPosition) {
        if (childPosition - 1 >= 0)
            return (childPosition - 1) / 2;
        else
            return -1; //if no parent found
    }
    /**
     *@return the position of the right child of the node at position parentPosition
     */
    private int leftChild(int parentPosition) {
        return 2 * parentPosition + 1;
    }
    /**
     *@return the position of the right child of the node at position parentPosition
     */
    private int rightChild(int parentPosition) {
        return 2 * parentPosition + 2;
    }
    /**
     * whether the node at position parentPosition has a left child
     */
    private boolean hasLeft(int parentPosition) {
        return leftChild(parentPosition) < heap.size();
    }
    /**
     * whether the node at position parentPosition has a child
     */
    private boolean hasRight (int parentPosition) {
        return rightChild(parentPosition) < heap.size();
    }
    /**
     * checks if a key is valid in case if the priority is bounded
     */
    private void checkBounds (K key) {
        if (isBounded){
            if (comparator.compare(key, lowerBound) < 0 || comparator.compare(key, upperBound) > 0)
                throw new IllegalArgumentException();
        }
    }
    /**
     * @return the size of the heap
     */
    public int size(){
        return heap.size();
    }
    /**
     * compares elements at the given positions
     */
    private int compare(int position1, int position2){
        return comparator.compare(heap.get(position1).getKey(), heap.get(position2).getKey());
    }
    /**
     * swaps elements at position1 and position2
     */
    private void swap (int position1, int position2){
        Entry<K, V> temp = heap.get(position1);
        heap.set(position1, heap.get(position2));
        heap.set(position2, temp);
    }
    public boolean isEmpty(){
        return size() == 0;
    }
}
