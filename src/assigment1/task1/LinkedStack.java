package assigment1.task1;


import assigment4.task1.SinglyLinkedList;

/**
 * Created by nicholas on 30/01/2017.
 */
public class LinkedStack<E> implements Stack<E>{

    private SinglyLinkedList<E> list = new SinglyLinkedList<E>();
     public LinkedStack(){}


    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public E top() {
        return list.first();
    }

    @Override
    public void push(E element) {
        list.addFirst(element);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    public String toString() {
         return list.toString();
    }
}
