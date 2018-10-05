package assigment4.task3;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * class representing graph
 * implemented according to the Adjacency List Structure implementation given in the lecture 11
 * Created by nicholas on 27/03/2017.
 */
public class Graph<Value, Weight> {
    //mapping value to vertex, for convenience
    private HashMap<Value, Vertex<Value>> map = new HashMap<>();
    //lists with edges and vertices
    private SinglyLinkedList<Vertex<Value>> vertices;
    private SinglyLinkedList<Edge<Weight>> edges;
    //whether directed or not
    private boolean isDirected;

    /**
     * constructor, input defines if it is directed or not
     * @param isDirected
     */
    public Graph(boolean isDirected){
        vertices = new SinglyLinkedList<>();
        edges = new SinglyLinkedList<>();
        this.isDirected = isDirected;
    }

    /**
     * inserts a new vertex with Value in the graph
     * @param value
     * @return
     */
    public Vertex<Value> insertVertex (Value value){
        Vertex<Value> vertex = new Vertex<Value>(value, isDirected);
        map.put(value, vertex);
        vertices.addLast(vertex);
        vertex.setVertexListPos(vertices.tail);
        return vertex;
    }

    /**
     * inserts a new edge with Weight between vertices with values inGoing and outGoing
     * @param inGoing
     * @param outGoing
     * @param weight
     */
    public void insertEdge (Value inGoing, Value outGoing, Weight weight){
        //finds vertices with given values and calls method with vertices
        insertEdge(map.get(inGoing), map.get(outGoing), weight);
    }

    public SinglyLinkedList<Vertex<Value>> getVertices() {
        return vertices;
    }

    /**
     * inserts a new edge with Weight between vertices inGoing and outGoing
     * @param inGoing
     * @param outGoing
     * @param weight
     */
    private void insertEdge (Vertex<Value> inGoing, Vertex<Value> outGoing, Weight weight){
        //prevents inserting more than one edge between two vertices if the graph is not directed
        if (!isDirected && getEdge(inGoing, outGoing) != null){
            return;
        }
        Edge<Weight> edge = new Edge<Weight>(weight, outGoing, inGoing);
        //adding edge to the edges lists of vertices
        outGoing.getOutGoingEdges().addLast(edge);
        inGoing.getInGoingEdges().addLast(edge);
        //adding outGoing and inGoing to the edge as adjacent vertices
        edge.setOutGoingAdjacencyListPos(outGoing.getOutGoingEdges().tail);
        edge.setInGoingAdjacencyListPos(inGoing.getInGoingEdges().tail);
        //adding to the list og all edges
        edges.addLast(edge);
        edge.setEdgeListPos(edges.tail);
    }

    /**
     * removes vertex with Value
     * @param vertex
     */
    public void removeVertex(Value vertex){
        //finds vertex with Value and calls method with vertex
        removeVertex(map.get(vertex));
    }

    /**
     * removes vertex from the graph
     * @param vertex
     */
    private void removeVertex(Vertex<Value> vertex){
        vertices.remove(vertex);
        Node<Edge<Weight>> temp = vertex.getInGoingEdges().head;
        //going through all vertex's edges and removing them
        while (temp != null){
            removeEdge(temp.getElement());
            temp = temp.getNext();
        }
        temp = vertex.getOutGoingEdges().head;
        while (temp!= null){
            removeEdge(temp.getElement());
            temp = temp.getNext();
        }
    }

    /**
     * removes edge
     * @param edge
     */
    public void removeEdge(Edge<Weight> edge){
        edges.remove(edge);
        edge.getInGoingVertex().getInGoingEdges().remove(edge);
        edge.getOutGoingVertex().getOutGoingEdges().remove(edge);
    }

    /**
     * returns edge between vertex1, vertex2, if any
     * @param vertex1
     * @param vertex2
     * @return
     */
    public Edge<Weight> getEdge(Value vertex1, Value vertex2){
        return getEdge(map.get(vertex1), map.get(vertex2));
    }

    /**
     * returns edge between vertex1, vertex2, if any
     * @param vertex1
     * @param vertex2
     * @return
     */
    public Edge<Weight> getEdge(Vertex<Value> vertex1, Vertex<Value> vertex2){
        Node<Edge<Weight>> temp = vertex1.getInGoingEdges().head;
        while (temp != null){
            if (vertex2.getOutGoingEdges().has(temp.getElement()))
                return temp.getElement();
            temp = temp.getNext();
        }
        temp = vertex1.getOutGoingEdges().head;
        while (temp != null){
            if (vertex2.getInGoingEdges().has(temp.getElement()))
                return temp.getElement();
            temp = temp.getNext();
        }
        return null;

    }

    /**
     * finds the opposite vertex to the given vertex through the given edge
     * @param value
     * @param edge
     * @return
     */
    public Vertex<Value> opposite (Value value, Edge<Weight> edge){
        return opposite(map.get(value), edge);
    }

    /**
     * finds the opposite vertex to the given vertex through the given edge
     * @param vertex
     * @param edge
     * @return
     */
    public Vertex<Value> opposite (Vertex<Value> vertex, Edge<Weight> edge){
        if (edge.getOutGoingVertex().equals(vertex))
            return edge.getInGoingVertex();
        if (edge.getInGoingVertex().equals(vertex))
            return edge.getOutGoingVertex();
        return null;
    }

    public boolean has (Value value){
        return map.get(value) != null;
    }

    /**
     * number of ingoing edges to the given vertex
     * @param vertex
     * @return
     */
    public int inDegree (Vertex<Value> vertex){
        return vertex.getInGoingEdges().size();
    }
    /**
     * number of outgoing edges out of the the given vertex
     * @param vertex
     * @return
     */
    public int outDegree (Vertex<Value> vertex){
        return vertex.getOutGoingEdges().size();
    }

    /**
     * total number of edges adjacent to the given vertex
     * @param vertex
     * @return
     */
    public int degree (Vertex<Value> vertex){
        return isDirected ? vertex.getInGoingEdges().size() + vertex.getOutGoingEdges().size() : vertex.getInGoingEdges().size();
    }

    /**
     * finds the path between startPoint and endPoint in a MST in form of array list with the track
     * @param startPoint
     * @param endpoint
     * @return
     */
    public ArrayList<Vertex<Value>> BFS(Value startPoint, Value  endpoint){
        ArrayList<Vertex<Value>> result = new ArrayList<>();
        //calling actual BSF method
        BFS(result, map.get(startPoint), map.get(endpoint));
        //making vertices unvisited again
        Node<Vertex<Value>> current = vertices.head;
        while (current != null){
            current.getElement().setVisited(false);
            current = current.getNext();
        }
        return result;

    }

    /**
     * recursivesly finding path with BFS
     * @param track
     * @param startPoint
     * @param endpoint
     * @return
     */
    public boolean BFS(ArrayList<Vertex<Value>> track, Vertex<Value> startPoint, Vertex<Value> endpoint){
        //makes current vertex visited
        startPoint.setVisited(true);
        //base case
        if (startPoint == endpoint) {
            track.add(startPoint);
            return true;
        }
        Node<Edge<Weight>> current = startPoint.getInGoingEdges().head;
        //back tracking
        while (current != null){
            if (!opposite(startPoint, current.getElement()).isVisited() &&BFS(track, opposite(startPoint, current.getElement()), endpoint)){
                track.add(startPoint);
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public SinglyLinkedList<Edge<Weight>> getEdges() {
        return edges;
    }

    /**
     * returns a vertex from the graph to start
     * @return
     */
    public Vertex<Value> getStartVertex (){
        return vertices.head.getElement();
    }

    /**
     * returns a list of vertices adjacent to the given one
     * @param value
     * @return
     */
    public ArrayList<Vertex<Value>>getNeighbours(Value value){
        return getNeighbours(map.get(value));
    }

    private ArrayList<Vertex<Value>>getNeighbours(Vertex<Value> vertex){
        ArrayList<Vertex<Value>> result = new ArrayList<>();
        Node<Edge<Weight>> current =  vertex.getInGoingEdges().head;
        while (current != null){
            result.add(opposite(vertex, current.getElement()));
            current = current.getNext();
        }
        if (isDirected){
            current = vertex.getOutGoingEdges().head;
            while (current != null){
                result.add(opposite(vertex, current.getElement()));
                current = current.getNext();
            }
        }
        return result;
    }

    /**
     * returns object vertex mapped to value
     * @param value
     * @return
     */
    public Vertex<Value> getVertex (Value value){
        return map.get(value);
    }

}
