package com.DSA.lab1;

import java.io.*;
import java.util.Scanner;

import static com.DSA.lab1.task1.writeFile;

/**
 * Created by nicholas on 16/01/2017.
 */
public class task2 {

    private static int x;   //x coordinate
    private static int y;   //y coordinate

    public static void main (String[] args) throws IOException {

        String sequince = readString(); //read from input file
        for (int i = 0; i < sequince.length(); i++) { //go through all the letters
            makeMove(sequince.charAt(i));
        }
        writeString(x+" "+y);

    }

    /**
     * read string from input file
      * @return string
     */
    private static String readString() {
        try {
            return new Scanner(new File("input.txt")).nextLine();
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    /**
     * write string to output file
     * @param s string to write
     */

    private static void writeString(String s) {
        try {
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt"), "ascii"))) {
                writer.write(s);
            }
        } catch (IOException ex) {
        }
    }

    /**
     * change coordinates according to command
     * @param command letter representing command
     */

    public static void makeMove(char command) {
        switch (command){
            case 'U':
                y += 1;
                break;
            case 'D':
                y -= 1;
                break;
            case 'L':
                x -= 1;
                break;
            case 'R':
                x += 1;
                break;
        }
    }
}
