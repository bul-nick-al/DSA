package assigment1.task3;

/**
 * Created by nicholas on 27/02/2017.
 */
public interface Vocabulary {
    boolean add(String word);
    boolean isPrefix(String prefix);
    boolean contains(String word);
    String getName();
}
