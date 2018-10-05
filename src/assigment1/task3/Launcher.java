package assigment1.task3;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by nicholas on 01/02/2017.
 */
public class Launcher {
    public static void main(String args[]) throws FileNotFoundException {
        StringTokenizer[] numbers = readTokens();
//        ArrayList<Boolean> result = checkPresence(numbers[1],putToBitSet(numbers[0]));
//        writeString(result);
    }

    /**
     * read two sequences from input file
     * @return array of two
     */
    private static StringTokenizer[] readTokens() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            StringTokenizer[] stringTokenizers = new StringTokenizer[2];
            stringTokenizers[0] = new StringTokenizer(sc.nextLine(), " ", false);
            stringTokenizers[1] = new StringTokenizer(sc.nextLine(), " ", false);
            return stringTokenizers;
        } catch (FileNotFoundException e) {
           return null;
        }
    }

    /**
     * write string to output file
     * @param result string to write
     */

    private static void writeString(ArrayList<Boolean> result) {
        try {
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt"), "ascii"))) {
                for (int i = 0; i < result.size(); i++) {
                    writer.write(result.get(i).toString());
                    writer.write(" ");
                }
            }
        } catch (IOException ex) {
        }
    }

    /**
     * puts values from tokenizer to naive set
     * @param tokens values to put to the set
     * @return naive set
     */
    public static LowercaseTrieVocabulary putToBitSet(StringTokenizer tokens){
        LowercaseTrieVocabulary lowercaseTrieVocabulary = new LowercaseTrieVocabulary();
//        BitSet bitSet = new BitSet();
        while(tokens.hasMoreTokens()){
//            bitSet.set(Integer.parseInt(tokens.nextToken()));
            lowercaseTrieVocabulary.add(tokens.nextToken());
        }
        return lowercaseTrieVocabulary;
    }


    /**
     * check if bit set has numbers from tokenizer
     * @param tokens numbers which presence is checked
     * @param bitSet
     * @return boolean array list with values according to the order in tokenizer
     */
    public static ArrayList<Boolean> checkPresence(StringTokenizer tokens, BitSet bitSet){
        ArrayList<Boolean> result =new ArrayList<>();
        while(tokens.hasMoreTokens()){
            result.add(bitSet.get(Integer.parseInt(tokens.nextToken()))); //parsing token to integer and requesting naive set for its presence
        }
        return result;
    }
}
