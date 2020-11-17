package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {

    private graph k;

    // Constructor to this graph
    public Graph_Algo() {
        this.k = new Graph_DS();
    }

    // copy constructor to the graph
    public Graph_Algo(graph g) {
        this.k = g;
    }

    //this method to initialize the graph
    @Override
    public void init(graph g) {
        this.k = g;
    }

    //this method return a deep copy of the object's graph by create a new graph and save the
    // source graph in the new graph
    @Override
    public graph copy() { return new Graph_DS(this.k); }

    //this method return true if all the vertexes in the graph have a valid edge to any
    // vertex in the the graph, mean that the graph is connected
    @Override
    public boolean isConnected() {

        // checking if graph is empty
        if(this.k.nodeSize() == 0 || this.k.nodeSize() == 1) return true;

        // change all the tags of nodes in the graph to 0 - like never visited
        Collection<node_data> nodesCo = this.k.getV();
        for(node_data node : nodesCo) node.setTag(0);

        // getting first element (node_data) from the graph collection
        Collection<node_data> nodes = this.k.getV();
        ArrayList<node_data> newNodes = new ArrayList<>(nodes);
        node_data first = newNodes.get(0);

        // visited nodes contains all the nodes that have been visited
        ArrayList<Integer> visitedNodes = new ArrayList<>();

        // queue for the BFS
        Queue<node_data> queue = new LinkedList();

        // checking the first node_data
        visitedNodes.add(first.getKey());
        queue.add(first);
        first.setTag(1);

        // BFS algorithm
        while (!queue.isEmpty()) {
            node_data t = queue.poll();
            for (node_data m : t.getNi()) {
                if (m.getTag() == 0) {
                    visitedNodes.add(m.getKey());
                    m.setTag(1);
                    queue.add(m);
                }
            }
        }

        if (visitedNodes.size() == this.k.nodeSize()) return true;
        else return false;
    }

    //this method reurn the shortest path
    @Override
    public int shortestPathDist(int src, int dest) {
        if(src == dest) return 0;
        List<node_data> sp = shortestPath(src, dest);
        if(sp == null) return -1;
        else return sp.size() - 1;
    }

    //this method return list of the vertices that we passed of them to arrive the destination in shortest path
    @Override
    public List<node_data> shortestPath(int src, int dest) {

        if(this.k.nodeSize() == 0) return null;

        if(src == dest) {
            List<node_data> l1 = new ArrayList<>();
            l1.add(this.k.getNode(src));
            return l1;
        }
        // change all the tags of nodes in the graph to 0 - like never visited
        Collection<node_data> nodesCo = this.k.getV();
        for(node_data node : nodesCo) node.setTag(0);

        ArrayList<node_data> path = new ArrayList<>();
        ArrayList<NodeAlgo> prevs = new ArrayList<>();

        node_data a = this.k.getNode(src);
        node_data b = this.k.getNode(dest);

        if(a == null || b == null) return null;

        Queue<node_data> queue = new LinkedList();
        queue.add(a);
        a.setTag(1);

        // BFS algorithm
        while (!queue.isEmpty()) {
            node_data t = queue.poll();
            if (t == b) {
                path.add(t);
                while(t != a) {
                    for(int i = 0; i < prevs.size(); i++) {
                        if(t.getKey() == prevs.get(i).key.getKey()) {
                            path.add(t);
                            t = prevs.get(i).prev;
                            if(t == a) {
                                return path;
                            }
                        }
                    }
                }
            }
            for (node_data m : t.getNi()) {
                if (m.getTag() == 0) {
                    NodeAlgo temp = new NodeAlgo(m, t);
                    prevs.add(temp);
                    m.setTag(1);
                    queue.add(m);
                }
            }
        }

        return null;
    }
}

//This Graph_Algo class is implements the graph_algorithms interface,
//and his purpose is to execute specific algorithms on a graph like,
// initialize, create deep copy, check if the graph is connected,
// search the shortest path between a vertix to another vertix
class NodeAlgo {
    public node_data key;
    public node_data prev;
    public NodeAlgo(node_data key, node_data prev) {
        this.key = key;
        this.prev = prev;
    }
}