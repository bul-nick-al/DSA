package assigment4.task2;

/**
 * class representing an edge
 * Created by nicholas on 27/03/2017.
 */
public class Edge<Weight> {
    private Weight weight;
    //adjacent vertices
    private Vertex outGoingVertex;
    private Vertex inGoingVertex;
    //pointer to the Edge list
    private Node<Edge<Weight>> edgeListPos;
    //pointers to the lists of edges of adjacent vertices
    private Node<Edge<Weight>> outGoingAdjacencyListPos;
    private Node<Edge<Weight>> inGoingAdjacencyListPos;


    public Edge(Weight weight, Vertex outGoing, Vertex inGoing) {
        this.weight = weight;
        this.outGoingVertex = outGoing;
        this.inGoingVertex = inGoing;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }


    public Vertex getOutGoingVertex() {
        return outGoingVertex;
    }

    public void setOutGoingVertex(Vertex outGoingVertex) {
        this.outGoingVertex = outGoingVertex;
    }

    public Vertex getInGoingVertex() {
        return inGoingVertex;
    }

    public void setInGoingVertex(Vertex inGoingVertex) {
        this.inGoingVertex = inGoingVertex;
    }

    public Node<Edge<Weight>> getOutGoingAdjacencyListPos() {
        return outGoingAdjacencyListPos;
    }

    public void setOutGoingAdjacencyListPos(Node<Edge<Weight>> outGoingAdjacencyListPos) {
        this.outGoingAdjacencyListPos = outGoingAdjacencyListPos;
    }

    public Node<Edge<Weight>> getInGoingAdjacencyListPos() {
        return inGoingAdjacencyListPos;
    }

    public void setInGoingAdjacencyListPos(Node<Edge<Weight>> inGoingAdjacencyListPos) {
        this.inGoingAdjacencyListPos = inGoingAdjacencyListPos;
    }

    public Node<Edge<Weight>> getEdgeListPos() {
        return edgeListPos;
    }

    public void setEdgeListPos(Node<Edge<Weight>> edgeListPos) {
        this.edgeListPos = edgeListPos;
    }
}
