package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Graph_DS implements graph {


    private HashMap<Integer, node_data> nodesMap;
    private int edges;
    private int mc;

    //Constructor to this class
    public Graph_DS() {
        this.nodesMap = new HashMap<>();
        this.edges = 0;
        this.mc = 0;
    }

    //A copy constructor that get a graph and copy
    // the nodes and edges in the graph
    public Graph_DS(graph g) {
        this.deepCopyForAlgo((Graph_DS)g);
    }

    
    private void deepCopyForAlgo(Graph_DS g) {
        this.nodesMap = g.nodesMap;
        this.edges = g.edges;
        this.mc = g.mc;
    }

    //this method return the data of this node
    @Override
    public node_data getNode(int key) {
        return this.nodesMap.get(key);
    }

    //this method return true if they have an edge else false
    @Override
    public boolean hasEdge(int node1, int node2) {
        node_data a = this.getNode(node1);
        node_data b = this.getNode(node2);
        if(a == null || b == null || a.equals(b)) return false;
        else return (a.hasNi(node2) && b.hasNi(node1));
    }

    // this method add a new node to the graph
    @Override
    public void addNode(node_data n) {
        this.nodesMap.put(n.getKey(), n);
        this.mc++;
    }

    //this method connect between two nodes with edge if they have not edge
    @Override
    public void connect(int node1, int node2) {
        node_data a = this.getNode(node1);
        node_data b = this.getNode(node2);

        if(a == null || b == null || a.equals(b)) return;
        else if(!a.hasNi(node2) && !b.hasNi(node1)) {
            a.addNi(b);
            b.addNi(a);

            this.edges++;
            this.mc++;
        }
    }

    //this method return a collection of all the node in the graph
    @Override
    public Collection<node_data> getV() {
        return new ArrayList<node_data>(this.nodesMap.values());
    }

    //this method return a collection of all neighbors of a node
    @Override
    public Collection<node_data> getV(int node_id) {
        node_data a = nodesMap.get(node_id);
        if(a == null) return null;
        else return a.getNi();
    }

    //this method return the node that removed
    @Override
    public node_data removeNode(int key) {
        node_data a = nodesMap.get(key);
        if(a == null) return null;
        else {
            Collection<node_data> temp = a.getNi();
            for (node_data node : temp) {
                removeEdge(key, node.getKey());
            }
            nodesMap.remove(key, a);
            this.mc++;
            return a;
        }
    }

    //this method removes an edge between two nodes if they are connected
    @Override
    public void removeEdge(int node1, int node2) {
        node_data a = this.getNode(node1);
        node_data b = this.getNode(node2);

        if(a == null || b == null || a.equals(b)) return;
        else if(a.hasNi(node2) && b.hasNi(node1)) {
            a.removeNode(b);
            b.removeNode(a);

            this.edges--;
            this.mc++;
        }
    }

    //this method return the number of nodes that the graph has
    @Override
    public int nodeSize() {
        return this.nodesMap.size();
    }

    //this method return the number of edges that the graph has
    @Override
    public int edgeSize() {
        return this.edges;
    }

    //this method return the number of operations that do a the graph
    @Override
    public int getMC() {
        return this.mc;
    }
}