package assigment4.task3;


/**
 * class representing a Vertex
 * Created by nicholas on 27/03/2017.
 */
public class Vertex<Value> {
    private boolean visited;
    private Value value;
    //lists of adjacent edges
    private SinglyLinkedList<Edge> inGoingEdges;
    private SinglyLinkedList<Edge> outGoingEdges;
    //pointer to the vertices list
    private Node<Vertex<Value>> VertexListPos;

    public Vertex(Value value, boolean isDirected) {
        visited = false;
        this.value = value;
        outGoingEdges = new SinglyLinkedList<>();
        //if graph is undirected, inGoing and outGoing Lists with edges are the same list
        if (isDirected)
            inGoingEdges = new SinglyLinkedList<>();
        else
            inGoingEdges = outGoingEdges;

    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }


    public SinglyLinkedList getInGoingEdges() {
        return inGoingEdges;
    }


    public SinglyLinkedList getOutGoingEdges() {
        return outGoingEdges;
    }

    public Node<Vertex<Value>> getVertexListPos() {
        return VertexListPos;
    }

    public void setVertexListPos(Node<Vertex<Value>> vertexListPos) {
        VertexListPos = vertexListPos;
    }
}
