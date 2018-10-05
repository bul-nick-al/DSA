package com.DSA.lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by nicholas on 16/01/2017.
 */
public class task1 {

    public static void main(String[] args) throws IOException {
        long messNum = Double.doubleToLongBits(readDouble());    //read double number from input file and convert it
        long sign = messNum << 63; //variable for sign
        long exp = (messNum >> 1) & 0x7FF;    //variable for exponent
        long mant = (messNum >> 12) & 0xFFFFFFFFFFFFFL;  //variable for mantissa



        writeFile(Double.toString(Double.longBitsToDouble(sign | exp << 52 | mant)));


    }

    public static double readDouble() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            br.close();
            String everything = sb.toString();
            return Double.parseDouble(everything);

        }
    }

    public static void writeFile(String output) {
        try {
            FileWriter writer = new FileWriter("output.txt", false);
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
