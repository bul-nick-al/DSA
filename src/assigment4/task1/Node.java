package assigment4.task1;

/**
 * Created by nicholas on 03/02/2017.
 */
/**
 * Class representing a node for Linked List
 * @param <E>
 */
public class Node<E>{
    private E element;
    private Node<E> next;

    public Node (E element, Node<E> next){
        this.element = element;
        this.next = next;
    }

    public E getElement(){
        return element;
    }

    public Node<E> getNext(){
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
