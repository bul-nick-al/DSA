package assigment4.task2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nicholas on 20/04/2017.
 */
public class DijkstrasAlgo<Value, Weight> {
    //initial graph
    private Graph<Value, Weight> graph;
    //mapping vertex to the minimal distance to it
    private HashMap<Vertex<Value>, Double> distance;
    //mapping vertex to its predecessor
    private HashMap<Vertex<Value>, Vertex<Value>> previousVertices;
    //list with visited and unvisited nodes
    private ArrayList<Vertex<Value>> visitedList;
    private ArrayList<Vertex<Value>> unvisitedList;
    //my comparator for comparing edges by time
    private MyComparator comparator;

    public DijkstrasAlgo(Graph<Value, Weight> graph){
        this.graph = graph;
    }

    /**
     * the dijkstra's algorithm itself
     * @param startPoint
     */
    public void process(Value startPoint){
        process(graph.getVertex(startPoint));
    }
    /**
     * the dijkstra's algorithm itself
     * @param startPoint
     */
    private void process(Vertex<Value> startPoint){
        distance = new HashMap<>();
        previousVertices = new HashMap<>();
        visitedList = new ArrayList<>();
        unvisitedList = new ArrayList<>();
        comparator = new MyComparator();
        //setting time to the initial vertex to 0
        distance.put(startPoint, 0.0);
        unvisitedList.add(startPoint);
        Vertex<Value> vertex;
        while (unvisitedList.size() > 0){
            //finding the vertex with the smallest time from the available ones
            vertex = getMinTimeVertex(unvisitedList);
            //make it visited
            visitedList.add(vertex);
            unvisitedList.remove(vertex);
            findMinTime(vertex);
        }
    }

    public double findLeastTime(Value endPoint){
        return distance.get(graph.getVertex(endPoint));
    }

    /**
     * updates minimal time for all vertices adjacent to the given one and put them to the unvisited list
     * @param vertex
     */
    private void findMinTime(Vertex<Value> vertex){
        ArrayList<Vertex<Value>> neighbours = graph.getNeighbours(vertex.getValue());
        for (Vertex<Value> neighbour : neighbours) {
            //checks if time through current vertex to its neighbour is less the the least time to its neighbour calculayed so far
            if (getMinTime(neighbour) > getMinTime(vertex) + getTime(neighbour, vertex)) {
                //if it is, recalculates the least time
                distance.put(neighbour, getMinTime(vertex) + getTime(neighbour, vertex));
                previousVertices.put(neighbour, vertex);
                unvisitedList.add(neighbour);
            }
        }
    }

    /**
     * finds the vertex with the smallest time among the given ones
     * @param vertices
     * @return
     */
    private Vertex<Value> getMinTimeVertex(ArrayList<Vertex<Value>> vertices){
        Vertex<Value> min = vertices.get(0);
        for (int i = 1; i < vertices.size(); i++) {
            if (getMinTime(vertices.get(i)) < getMinTime(min))
                min = vertices.get(i);
        }
        return min;
    }

    /**
     * finds how much time it takes to reach vertex2 from vertex1
     * @param vertex1
     * @param vertex2
     * @return
     */
    private double getTime(Vertex<Value> vertex1, Vertex<Value> vertex2){
        Road road = (Road)(graph.getEdge(vertex1.getValue(), vertex2.getValue())).getWeight();
        return road.getTime();
    }

    /**
     * returns the least time vrtex can be reached from the vertex put to the process method
     * @param vertex
     * @return
     */
    private double getMinTime(Vertex<Value> vertex){
        Double result = distance.get(vertex);
        if (result == null)
            return Double.POSITIVE_INFINITY;
        return result;
    }

    /**
     * construct the most efficient route from the vertex put to process method to the given vertex
     * @param vertex
     * @return
     */
    public ArrayList<Vertex<Value>> getRoute (Value vertex){
        return getRoute(graph.getVertex(vertex));
    }
    /**
     * construct the most efficient route from the vertex put to process method to the given vertex
     * @param vertex
     * @return
     */
    private ArrayList<Vertex<Value>> getRoute (Vertex<Value> vertex){
        ArrayList<Vertex<Value>> route = new ArrayList<>();
        Vertex<Value> current = vertex;
        route.add(vertex);
        while (previousVertices.get(current) != null){
            current = previousVertices.get(current);
            route.add(current);
        }
        return route;
    }
}
