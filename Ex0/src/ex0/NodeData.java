package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NodeData implements node_data {

    private static int idsCounter = 0;

    private int node_id;
    private String info;
    private int tag;
    private HashMap<Integer, node_data> niMap;


     // Constructor to this class
    public NodeData() {
        this.node_id = idsCounter;
        idsCounter++;
        this.tag = 0;
        this.info = null;
        this.niMap = new HashMap<>();
    }

    //copy constructor to node data
    public NodeData(int key) {
        this.node_id = key;
        this.tag = 0;
        this.niMap = new HashMap<>();
    }

    // this method return the node_data key - node_id
    @Override
    public int getKey() {
        return this.node_id;
    }

    //this method return true or false if a code is next to the current vertex
    @Override
    public Collection<node_data> getNi() {
        return new ArrayList<node_data>(this.niMap.values());
    }

    //this method return the collection of the node's neighbors
    @Override
    public boolean hasNi(int key) {
        return this.niMap.containsKey(key);
    }

    //this method a dds a vertex as a neighbor to the current vertex
    @Override
    public void addNi(node_data t) {
        if(!this.niMap.containsValue(t)) {
            this.niMap.put(t.getKey(), t);
        }
    }

    //this method Checks if this node contains this HashMap, if any, then remove it
    @Override
    public void removeNode(node_data node) {
        if(this.niMap.containsValue(node)) this.niMap.remove(node.getKey(), node);
    }

    //this method return the information of the node
    @Override
    public String getInfo() {
        return this.info;
    }

    //this method Change the information of this node
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    //this method return the tag of this node
    @Override
    public int getTag() {
        return this.tag;
    }

    //this method change to the new value of the tag
    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
