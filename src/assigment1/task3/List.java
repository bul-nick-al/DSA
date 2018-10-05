package assigment1.task3;

/**
 * Created by nicholas on 31/01/2017.
 */
public interface List<E> {
    /**
     * add an item at the end
     * @param element
     */
    void add(E element);

    /**
     * add an item at position i
     * @param index
     * @param element
     */
    void add(int index, E element);

    /**
     * remove first appearance of the item from the list
     * @param element
     */
    void remove(E element);

    /**
     * remove the item at position I
     * @param index
     */
    void remove(int index);

    /**
     * return the number of items in the list
     * @return
     */
    int size();

    /**
     * @return true if the list has no items
     */
    boolean isEmpty();

    /**
     * @param element
     * @return true if x is in the list
     */
    boolean contains(E element);

    /**
     *
     * @param index
     * @return item at position index
     */
    E get(int index);

    /**
     * replce the element at index with element
     * @param index
     * @param element
     * @return the replaced value
     */
    E set(int index, E element);
}
