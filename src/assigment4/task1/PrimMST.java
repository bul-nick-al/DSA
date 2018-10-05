package assigment4.task1;

import java.util.PriorityQueue;

/**
 * Created by nicholas on 18/04/2017.
 */
public class PrimMST<Value, Weight> {
    //graph for Minimal Spanning Tree
    Graph<Value, Weight> graphMST = new Graph<>(false);
    //priority queue, which can work with roads due to the comparator
    PriorityQueue<Edge<Weight>> priorityQueue = new PriorityQueue<>(11, new MyComparator());
    public PrimMST(){

    }

    /**
     * makes Minimal Spanning Tree from the given graph
     * @param graph
     * @return
     */
    public Graph<Value, Weight> makeMST(Graph<Value, Weight> graph){
        //starting with the first vertex
        Vertex<Value> current = graph.getStartVertex();
        insertEdgesIntoPq(current);
        graphMST.insertVertex(current.getValue());
        //Sequentially retrieving edges with the highest priority
        while (!priorityQueue.isEmpty()){
            if (isRedundant(priorityQueue.peek())){
                priorityQueue.poll();
            }
            else {
                current = getAbsentVertex(priorityQueue.peek());
                //insertion
                graphMST.insertVertex(getAbsentVertex(priorityQueue.peek()).getValue());
                graphMST.insertEdge((Value) priorityQueue.peek().getInGoingVertex().getValue(), (Value) priorityQueue.peek().getOutGoingVertex().getValue(), priorityQueue.peek().getWeight());
                priorityQueue.poll();
                insertEdgesIntoPq(current);
            }
        }
        return graphMST;
    }

    public void insertEdgesIntoPq (Vertex<Value> vertex){
        Node<Edge<Weight>> current = vertex.getInGoingEdges().head;
        while (current != null){
            priorityQueue.add( current.getElement());
            current = current.getNext();
        }
    }

    /**
     * gets rid of redundant edges
     * @param edge
     * @return
     */
    public boolean isRedundant(Edge<Weight> edge){
        if (graphMST.has((Value) edge.getInGoingVertex().getValue()) && graphMST.has((Value) edge.getOutGoingVertex().getValue()))
            return true;
        return false;
    }

    /**
     * returns a vertex adjacent to edge but that is not yet in the graph
     * @param edge
     * @return
     */
    public  Vertex<Value> getAbsentVertex(Edge<Weight> edge){
        if (graphMST.has((Value)edge.getInGoingVertex().getValue()))
            return edge.getOutGoingVertex();
        else return edge.getInGoingVertex();
    }
}
