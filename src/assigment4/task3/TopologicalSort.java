package assigment4.task3;

import java.util.ArrayList;

/**
 * Created by nicholas on 20/04/2017.
 */
public class TopologicalSort<Value> {
    //list with sorted sequence
    ArrayList <Value> sortedList;
    //list with vertices that have no incoming edges
    ArrayList <Vertex<Value>> noIncomingEdgesList;
    Graph graph;

    public TopologicalSort(Graph graph) {
        this.graph = graph;
        sortedList = new ArrayList<>();
        noIncomingEdgesList = new ArrayList<>();
    }

    /**
     * implementation of hte topological sort itself. Works even for graphs with cycles, in this case returns only
     * elements that can be sorted (i.e. they are not a part of any cycle)
     * @return
     */
    public ArrayList <Value> sort(){
        findNoIncomingEdges();
        while (noIncomingEdgesList.size() != 0){
            //retrieving a vertex for the no incoming edges list (it takes the last one because it's good for time complexity when deleting t)
            Vertex vertex = noIncomingEdgesList.get(noIncomingEdgesList.size()-1);
            sortedList.add((Value) vertex.getValue());
            noIncomingEdgesList.remove(vertex);
            processNeighbours(vertex);
        }
        return sortedList;
    }

    /**
     * checks initial graph and finds there vertices with no incoming edges
     */
    public void findNoIncomingEdges(){
        Node<Vertex> current = graph.getVertices().head;
        //going through all vertices an checing incoming vertices
        while (current != null){
            if (current.getElement().getInGoingEdges().size() == 0)
                noIncomingEdgesList.add(current.getElement());
            current = current.getNext();
        }
    }

    /**
     * goes through the neighbours of the given vertex(which has just been taken out of the graph
     * and put to the sorted list) and deletes connections
     * @param vertex
     */
    public void processNeighbours (Vertex vertex){
        ArrayList<Vertex> list = graph.getNeighbours(vertex.getValue());
        for (int i = 0; i < list.size(); i++) {
            graph.removeEdge(graph.getEdge(list.get(i), vertex));
            //if a neighbour now also has no incoming edges it is put to the no incoming edges list
            if (list.get(i).getInGoingEdges().size() == 0)
                noIncomingEdgesList.add(list.get(i));
        }
    }
}
