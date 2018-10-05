package assigment3.task2;

import java.io.*;
import java.util.Scanner;

/**
 * Created by nicholas on 22/03/2017.
 */
public class Launcher {
    public static void main(String[] args) {
        writeString(Integer.toString(findPeriod(readLong())));
    }

    /**
     * finds the period of MyRandom with the given seed
     * @param seed
     */
    public static int findPeriod (long seed){
        MyRandom x = new MyRandom(seed);
        RBTreeMap<Double, Integer> tree = new RBTreeMap<>();
        int i = 0;
        double value = x.nextDouble();
        do {
            tree.put(value, i); //putting random value with its position
            value = x.nextDouble();
            i++;
        }while (!tree.has(value));
        return i - tree.get(value); //returning difference of position of the fist and second appearances of the value
    }
    /**
     * read a long number from the input file
     * @return
     */
    private static long readLong() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            return sc.nextLong();
        } catch (FileNotFoundException ex) {
            return 0;
        }
    }
    /**
     * write string to output file
     * @param result string to write
     */
    private static void writeString(String result) {
        try {
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt"), "ascii"))) {
                writer.write(result);
            }
        } catch (IOException ex) {
        }
    }
}
