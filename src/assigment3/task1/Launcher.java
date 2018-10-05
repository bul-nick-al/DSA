package assigment3.task1;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by nicholas on 20/03/2017.
 */
public class Launcher {
    public static void main(String[] args) throws IOException {
        ArrayList<Process> data = getProcesses();
        Scheduler scheduler = new Scheduler(120000);
        scheduler.run(data);
        writeString(scheduler.getCurrent() == null ? "" : scheduler.getCurrent().getName());

    }
    /**
     * reads cvs file with a list of processes
     */
    public static ArrayList<Process> getProcesses() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.csv"));
        br.readLine();
        ArrayList<Process> processes = new ArrayList<>();
        String line;
        String[] data;
        while ((line = br.readLine()) != null) {
            data = line.split(",");
            processes.add(new Process(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3])));
        }
        return processes;
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
