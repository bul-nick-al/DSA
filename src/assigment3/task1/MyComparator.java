package assigment3.task1;

/**
 * Created by nicholas on 24/02/2017.
 */
public class MyComparator<E> {
    public int compare(E a, E b) throws ClassCastException {
        return ((Comparable<E>) a).compareTo(b);
    }
}
