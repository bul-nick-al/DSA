package assigment2.task1;

import java.io.*;
import java.util.Scanner;

/**
 * Created by nicholas on 21/02/2017.
 */
public class Launcher {
    public static void main(String[] args) {
        String[] data = readStrings();
        Item[] clocks = convertToItems(data[0].split(" "));
        writeString(String.valueOf(Knapsack.knapsack(clocks, (int)(Double.parseDouble(data[1])*100))));
    }
    /**
     * creates an array of Items with values and weights from the string
     * @param heap string with values of time and weights together
     * @return an array with items
     */
    public static Item[] convertToItems(String[] heap){
        Item[] items = new Item[heap.length/2];
        for (int j = 0, i = 0; i < heap.length; i+=2, j++) {
//            items[j] = new Item(convertToMinutes(heap[i]), (int)(Double.parseDouble(heap[i+1]) * 100));
            items[j] = new Item(justParsToInt(heap[i]), (int)(Double.parseDouble(heap[i+1]) * 100));
        }
        return items;
    }
    /**
     * converts a string with digital representation of time to minutes
     * @param time
     */
    private static int convertToMinutes(String time) {
        String[] splitedTime = time.split(":");
        return Integer.parseInt(splitedTime[1]) + Integer.parseInt(splitedTime[0])*60;
    }
    /**
     * created on a an AI lab session just for solving another task
     * @param time
     */
    private static int justParsToInt(String time) {
        return Integer.parseInt(time);
    }
    /**
     * reads two strings from the file
     * @return array of strings
     */
    private static String[] readStrings() {
        String[] result = new String[2];
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            result[0] = sc.nextLine();
            result[1] = sc.nextLine();

        } catch (FileNotFoundException ex) {
            return null;
        }
        return result;
    }
    /**
     * writes a string to the file
     * @param s
     */
    private static void writeString(String s) {
        try {
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt"), "ascii"))) {
                writer.write(s);
                writer.close();
            }
        } catch (IOException ex) {
        }
    }
}
