package lab4;

import java.util.Hashtable;

/**
 * Created by nicholas on 06/02/2017.
 */
public class task1 {
    public static void main(String[] args) {
//        Hashtable<Integer, String> map = new Hashtable<>();
//        map.put(102, "Let's C");
//        map.put(103, "Operating System");
//        map.put(101, "Data c and N");
//        System.out.println("Values bef remove "+map);
//        map.remove(102);
        // Java uses polynomial function with base 31 // for string hashing
        String a = "A Test";
        String b = "B Test";
        String c = "C Test";
        long power4 = 31 * 31 * 31 * 31;
        System.out.println(a.hashCode() % power4);
        System.out.println(b.hashCode() % power4);
        System.out.println(c.hashCode() % power4);
        a = "Question.";
        b = "Question!";
        System.out.println(a.hashCode() / 31);
        System.out.println(b.hashCode() / 31);
    }
}
