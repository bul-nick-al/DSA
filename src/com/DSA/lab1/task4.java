package com.DSA.lab1;



import java.io.*;
import java.util.Scanner;

/**
 * Created by nicholas
 */
public class task4 {

    public static void main(String[] args){

//        String text = readString().toUpperCase();
//        writeString(text);
        writeString(readString());
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

}
