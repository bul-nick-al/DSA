package assigment1.task1;

/**
 * Created by nicholas on 30/01/2017.
 */
public interface Stack<E>{

    /**
     * @return how many elements the stack has
     */
    int size();

    /**
     * @return true if the stack has no elements
     */
    boolean isEmpty();

    /**
     * @return the element which is currently on the top of the stack
     */
    E top();

    /**
     * adds new element at the top of the stack
     * @param element
     */
    void push(E element);

    /**
     * removes the element from the top of the stack
     * @return the removed element
     */
    E pop();
}
