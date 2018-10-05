package assigment1.task1;

/**
 * Created by nicholas on 30/01/2017.
 */

public interface Queue<E> {
    /**
     * @return how many elements the queue has
     */
    int size();

    /**
     * @return true if the queue has no elements
     */
    boolean isEmpty();

    /**
     * @return the first element of the queue if one exists
     */
    E first();

    /**
     * adds new element to the back of the queue
     * @param element
     */
    void enqueue(E element);

    /**
     * removes the first element from the front of the queue
     * @return the remioved element
     */
    E dequeue(); }