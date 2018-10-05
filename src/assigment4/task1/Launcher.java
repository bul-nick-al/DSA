package assigment4.task1;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by nicholas on 03/04/2017.
 */
public class Launcher {
    public static void main(String[] args) throws IOException {
        Graph<String, Road> graph = new Graph<>(false);
        insertVertices(graph, readCities());
        insertEdges(graph, readRoads());
        //inputs Minimal Spanning Tree made of the graph along with orders
        outputResult(constructOutput(new PrimMST<String, Road>().makeMST(graph), readOrderList()));
    }
    /**
     * goes through the rout list calculating the total cost for one kilo and time
     * @param mst
     * @param route
     * @return
     */
    public static double[] countTimeCost (Graph<String, Road> mst, ArrayList<Vertex<String>> route){
        double[] result = new double[2];
        for (int i = 0; i < route.size() - 1; i++) {
            //time is stored at position 0 and cost is at position 1
            result[0] = result[0] + mst.getEdge(route.get(i), route.get(i + 1)).getWeight().getTime();
            result[1] = result[1] + mst.getEdge(route.get(i), route.get(i + 1)).getWeight().getCost();
        }
        return result;
    }

    /**
     * reads information about orders
     * @return
     * @throws IOException
     */
    public static ArrayList<String[]> readOrderList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        ArrayList<String[]> result =new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null){
            result.add(line.split(" "));
        }
        return result;
    }

    /**
     * reads information about cities
     * @return
     * @throws IOException
     */
    public static String[] readCities() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("russia.txt"));
        return br.readLine().split(" ");
    }

    /**
     * reads information about roads
     * @return
     * @throws IOException
     */
    public static String[] readRoads() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("russia.txt"));
        br.readLine();
        return br.readLine().split(" ");
    }

    /**
     * inserts cities to the graph
     * @param graph
     * @param cities
     */
    public static void insertVertices (Graph<String, Road> graph, String[] cities){
        for (int i = 0; i < cities.length; i++) {
            graph.insertVertex(cities[i]);
        }
    }
    /**
     * inserts roads to the graph
     * @param graph
     * @param roads
     */
    public static void insertEdges(Graph<String, Road> graph, String[] roads){
        //since data is stored as City City Info, we divide it into three parts
        for (int i = 0; i < roads.length/3; i++) {
            graph.insertEdge(roads[3 * i], roads[3 * i + 1 ], new Road(roads[3 * i + 2].split(":")));
        }
    }

    /**
     * assembles all the information
     * @param mst
     * @param list
     * @return
     */
    public static ArrayList<String> constructOutput(Graph<String, Road> mst, ArrayList<String[]> list){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Vertex<String>> route;
        for (int i = 0; i < list.size(); i++) {
            route = mst.BFS(list.get(i)[0], list.get(i)[1]);
            double[] data = countTimeCost(mst, route);
            result.add(list.get(i)[0] + " "+list.get(i)[1]+" "+Double.parseDouble(list.get(i)[2])+" "+Double.parseDouble(new DecimalFormat("##.#").format(data[0]))+" "+(Double.parseDouble(list.get(i)[2]))*data[1]);
        }
        return result;
    }

    /**
     * writes all the assembled information to the file
     * @param result
     */
    public static void outputResult(ArrayList<String> result){
        try {
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt")))) {
                for (int i = 0; i < result.size()-1; i++) {
                    writer.write(result.get(i)+"\n");
                }
                writer.write(result.get(result.size()-1));
            }
        } catch (IOException ex) {
        }
    }

}
