package lab12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by nicholas on 03/04/2017.
 */
public class Launcher {
    public static void main(String[] args) {
        Graph<String,Integer> xx = new Graph<>(false);

        xx.addVertex("A1");
        xx.addVertex("B2");
        xx.addEdge(xx.vertices_array()[0], xx.vertices_array()[1], 1, 1);
        System.out.println(xx.areAdjacent(xx.vertices_array()[0], xx.vertices_array()[1]));


    }

    /**
     * read a long number from the input file
     * @return
     */
    private static String readString() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            return sc.nextLine();
        } catch (FileNotFoundException ex) {
            return "";
        }
    }
}
