package assigment4.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nicholas on 20/04/2017.
 */
public class Launcher {
    public static void main(String[] args) throws IOException {
        //map is needed to find an equation by its left hand side
        HashMap<String, Equation> map = new HashMap<>();
        Graph<Equation, Integer> graph = new Graph<>(true);
        insertVertices(graph, readOrderList(), map);
        insertEdges(graph, map);
        writeString(String.valueOf(solveSystem(new TopologicalSort<Equation>(graph).sort())));
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
        while ((line = br.readLine()) != null ){
            if (!line.equals(""))
                //splitting left- and right hand side
                result.add(line.split("="));
        }
        return result;
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
     * inserts new vertices
     * @param graph
     * @param equations
     * @param map
     */
    public static void insertVertices (Graph<Equation, Integer> graph, ArrayList<String[]> equations, HashMap<String, Equation> map){
        for (String[] equation1 : equations) {
            Equation equation = new Equation(equation1);
            graph.insertVertex(equation);
            map.put(equation1[0], equation);
        }
    }

    /**
     * inserts edges to the graph
     * @param graph
     * @param map
     */
    public static void insertEdges(Graph<Equation, Integer> graph, HashMap<String, Equation> map){
        for (Equation equation: map.values()) {
            //relations are only needed for equations with unknowns
            if (equation.hasUnknowns()){
                //if some members of an expression are numeric, they are not inserted to the graph
                if (!isNumber(equation.expression[0]))
                    graph.insertEdge(equation,map.get(equation.expression[0]), 0);
                if (!isNumber(equation.expression[2]))
                    graph.insertEdge(equation,map.get(equation.expression[2]), 0);
            }
        }
    }

    /**
     * check whether the given string is a number
     * @param string
     * @return true if a number, false if not
     */
    public static boolean isNumber (String string){
        try
        {
            Double.parseDouble(string);
        }
        catch (NumberFormatException e ){
            return false;
        }
        return true;
    }

    /**
     * Solves topologically sorted sequence of equations
     * @param system
     * @return
     */
    public static String solveSystem(ArrayList<Equation> system){
        //R is a compulsory part of the expression. If it's absent in the sorted sequence, hte system is unsolvable
        if (!system.get(system.size()-1).getName().equals("R"))
            return "ERROR";
        HashMap<String, Integer> map = new HashMap<>();
        for (Equation equation:system) {
            if (!equation.hasUnknowns()){
                map.put(equation.getName(), equation.getValue());
            }
            else {
                map.put(equation.getName(),
                        calculateExpression(isNumber(equation.getExpression()[0]) ? Integer.parseInt(equation.getExpression()[0]) : map.get(equation.getExpression()[0]),
                                equation.getExpression()[1],isNumber(equation.getExpression()[2]) ? Integer.parseInt(equation.getExpression()[2]) : map.get(equation.getExpression()[2])));
            }
        }
        return String.valueOf(map.get(system.get(system.size()-1).getName()));
    }

    /**
     * Calculates and expression
     * @param member1
     * @param operation
     * @param member2
     * @return
     */
    public static int calculateExpression (int member1, String operation, int member2){
        switch (operation) {
            case "+":
                return member1 + member2;
            case "*":
                return member1 * member2;
        }
        return 0;
    }
}
