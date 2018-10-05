package assigment2.Task3;
import java.io.*;
import java.util.Scanner;

/**
 * Created by nicholas on 22/02/2017.
 */
public class Launcher {
    public static void main(String[] args) {
        String[] x = args;

        eraseOutput();
        ArrayList<String> text = readStrings();
        MyMap<String, Integer> map = putToMap(text);
        writeString(findMostFrequent(map));
        map = deleteStopWords(map);
        writeString(findMostFrequent(map));
    }

    /**
     * Splits a string to words excluding punctuation marks
     * @param line
     * @return
     */
    public static String[] toWords (String line){
        line = line.replaceAll("[.?,:]",""); //getting rid of all punctuation marks
        line = line.toLowerCase();
        return line.split("[ ']");
    }

    /**
     * Puts all words from the read lines to the map as keys, and number of their appearance as value
     * @param text
     * @return
     */
    public static MyMap<String, Integer> putToMap (ArrayList<String> text){
        MyMap<String, Integer> map = new MyMap<>();
        String[] words;
        for (int i = 0; i < text.size(); i++) {
            words = toWords(text.get(i));
            for (int j = 0; j < words.length; j++) {
                putAndCount(map, words[j]);
            }
        }
        return map;
    }

    /**
     * Stores to the map the word and the number of its appearances
     * (increments appearance by one if there already is a mapping to this word as a key)
     * @param map
     * @param word
     * @return
     */
    public static MyMap<String, Integer> putAndCount(MyMap<String, Integer> map, String word){
        Integer n;
        if ( (n = map.get(word)) == null){
            map.put(word,1);
        }
        else {
            map.put(word,n + 1);
        }
        return map;
    }

    /**
     * Deletes words from the stop list from the map
     * @param map
     * @return
     */
    public static MyMap<String, Integer> deleteStopWords(MyMap<String, Integer> map){
        String [] stopList = {"a", "the", "in", "at", "to", "on", "not", "for", "s", "d", "re", "is", "are", "am", "has", "i", "we", "you"};
        for (int i = 0; i < stopList.length; i++) {
            map.remove(stopList[i]);
        }
        return map;
    }

    /**
     * Finds the most frequent word from the map where the keys are words and the value is the number of theirs appearance
     * @param map
     * @return string with the most frequent word and the number of its appearances separated by a space
     */
    public static String findMostFrequent (MyMap<String, Integer> map){
        int max = 0;
        String word = "";
        ArrayList<Entry<String, Integer>> set = map.entrySet();
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i).getValue() > max){
                max = set.get(i).getValue();
                word = set.get(i).getKey();
            }
        }
        return word+" "+max;
    }

    /**
     * reads two strings from the file
     * @return array of strings
     */
    private static ArrayList<String> readStrings() {
        ArrayList<String> result = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while (sc.hasNextLine()){
                result.add(sc.nextLine());
            }
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
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("output.txt", true), "ascii"))) {
            writer.append(s);
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
        }
    }
    private static void eraseOutput (){
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("output.txt", false), "ascii"))) {
            writer.append("");
            writer.close();
        } catch (IOException ex) {
        }
    }
}
