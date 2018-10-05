package assigment4.task3;

/**
 * Created by nicholas on 30/01/2017.
 */
public class SinglyLinkedList<E> {


    public Node<E> head = null;

    public Node<E> tail = null;

    private int size = 0;

    public SinglyLinkedList() {}

    /**
     * Inserts the specified element at the beginning of this list
     * @param element
     */
    public void addFirst(E element){
        head = new Node<>(element, head);
        if (size == 0)
            tail = head;
        size++;
    }

    /**
     * Appends the specified element to the end of this list
     * @param element
     */
    public void addLast(E element){
        Node<E> newNode = new Node<>(element, null);
        if (isEmpty())
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    /**
     * Removes the first element from this list
     * @return the removed element
     */
    public E removeFirst(){
        if (isEmpty())
            return null;
        E first = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0)
            tail = null;
        return first;
    }

    public void remove(E element){
        if (head.getElement().equals(element)) {
            removeFirst();
            return;
        }
        Node<E> current = head.getNext();
        Node<E> temp = head;
        do {
            if(current.getElement().equals(element)){
                temp.setNext(current.getNext());
                size--;
                break;
            }
            temp = current;
            current = current.getNext();
        }while (current != null);
    }

    public boolean has (E element){
        Node<E> temp = head;
        while (temp != null){
            if (temp.getElement().equals(element))
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {return size == 0;}

    /**
     * @return the first element of the list if one exists
     */
    public E first(){
        if (isEmpty())
            return null;
        return head.getElement();
    }

    /**
     * @return the last element of the list if it exists
     */
    public E last(){
        if(isEmpty())
            return null;
        return tail.getElement();
    }

    /**
     * @return how many elements the list has
     */
    public int size(){return size;}
}
