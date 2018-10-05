package assigment2.task2;
import java.io.*;

/**
 * Created by nicholas on 20/02/2017.
 */
public class Launcher {
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Double>> data = getData();
        writeString(String.valueOf(findTimeColumn(data)));
    }

    /**
     * finds the column with time
     * @param data
     * @return index of the column, -1 otherwise
     */
    public static int findTimeColumn(ArrayList<ArrayList<Double>> data){
        for (int i = 0; i < data.size(); i++) {
            if (isTime(data.get(i)))
                return i;
        }
        return -1;
    }

    /**
     * Returns true if @param data after sorting satisfies time properties
     */
    public static boolean isTime (ArrayList<Double> data){
        QuickSort sort = new QuickSort();
        data = sort.sort(data, new MyComparator());
        //find the period form time recording
        Double difference = data.get(1) - data.get(0);
        //check if all numbers have the same period
        for (int i = 0; i < data.size() - 1; i++) {
            if (Math.abs(data.get(i+1) - data.get(i) - difference) >= 0.0001 || data.get(i) < 0 || data.get(i+1) <= data.get(i))
                return false;
        }
        return true;
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
            }
        } catch (IOException ex) {
        }
    }

    /**
     * Reads data from csv file
     * @return a list with "columns", each column is a list itself with double values
     * @throws IOException
     */
    public static ArrayList<ArrayList<Double>> getData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        br.mark(1);
        String line;
        String cvsSplitBy = ",";
        int numberOfColumns = br.readLine().split(cvsSplitBy).length; //finding the number of columns
        br.reset();
        //filling the list with "columns"
        ArrayList<ArrayList<Double>> data = new ArrayList<>();
        for (int i = 0; i < numberOfColumns; i++) {
            data.add(new ArrayList<Double>());
        }
        //filling each column
        while ((line = br.readLine()) != null){
            String[] doubles =  line.split(cvsSplitBy);
            for (int i = 0; i < numberOfColumns; i++) {
                data.get(i).add(Double.parseDouble(doubles[i]));
            }
        }
        return data;
    }
}
