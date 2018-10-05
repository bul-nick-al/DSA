package com.DSA.lab1;


import java.io.*;
import java.util.Scanner;

/**
 * Created by nicholas 
 */
public class task3 {

    public static void main(String[] args){

        String[] nums = readString().split(" ");
        int num1 = Integer.parseInt(nums[0]); //first number
        int num2 = Integer.parseInt(nums[1]); //second
        writeString(Integer.toString(num1 + num2));
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

