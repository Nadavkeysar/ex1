package ex1;

import java.util.*;

public class WGraph_DS implements weighted_graph {
	
	//first method
	private HashMap<Integer, node_info> graph;	//g = {n0.key = n0, n1.key = n1, ... , }
	private HashMap<node_info, HashMap<node_info, Double>> neighbors; //ni = {n0.key = {n1, n2}, n1.key = {}, n2.key = {n3} .. }
	//private HashMap<Integer, HashMap<Integer,Double>> weights;
	//second method
	//private HashMap<Integer, HashMap<Integer, List<Integer>>> map; // m = { n0.key = {n0.key = {n1, n2} } }
	
	private int mc;
	private int ec;
	
	private class Node implements node_info {

		private double tag;
		private int key;
		private String info;
		private int count = 0;
		
		public Node() {
			this.tag = 0.0;
			this.key = count++;
			this.info = "";
		}
		
		public Node(int key) {
			this.tag = 0.0;
			this.key = key;
			this.info = "";
		}
		
		
		@Override
		public int getKey() {
			return this.key;
		}

		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return this.info;
		}

		@Override
		public void setInfo(String s) {
			this.info = s;
			
		}

		@Override
		public double getTag() {
			// TODO Auto-generated method stub
			return this.tag;
		}

		@Override
		public void setTag(double t) {
			this.tag = t;
			
		}

		public String toString() {
			return "" + this.key;
		}

//		@Override
//		public boolean equals(Object o) {
//			if (this == o) return true;
//			if (o == null || getClass() != o.getClass()) return false;
//			Node node = (Node) o;
//			return Double.compare(node.tag, tag) == 0 &&
//					key == node.key &&
//					info.equals(node.info);
//		}
//
//		@Override
//		public int hashCode() {
//			return Objects.hash(tag, key, info);
//		}
	}
	
	public WGraph_DS() {
		// TODO Auto-generated constructor stub
		this.mc = 0;
		this.ec = 0;
		
		this.graph = new HashMap<>();
		this.neighbors  = new HashMap<>();
		//this.weights = new HashMap<>();

	}
	
    /**
     * return the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
	@Override
	public node_info getNode(int key) {
		return this.graph.get(key);
	}
	
    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
	@Override
	public boolean hasEdge(int node1, int node2) {
		//if(this.neighbors.containsKey(node1) && this.neighbors.containsKey(node2))
			if(this.neighbors.get(getNode(node1)) != null
					&& this.neighbors.get(getNode(node1)).get(getNode(node2)) != null)
				//	&& this.neighbors.get(this.getNode(node1).getKey()).get(this.getNode(node2).getKey()) != null)
				return true;
			return false;
	}

//		if(this.graph.size() < 2 || node1 == node2) return false;
//
//		node_info a = this.graph.get(node1);
//		node_info b = this.graph.get(node2);
//
//		if(a == null || b == null) return false;
//
//		List<node_info> list = this.neighbors.get(node1);
//		if(list == null || list.size() == 0) return false;
//
//		if(list.contains(b)) { //o(1)
//			return true;
//		}
//		return false;


    /**
     * return the weight if the edge (node1, node2) exist. In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
	@Override
	public double getEdge(int node1, int node2) {
		if (!hasEdge(node1, node2)) return -1;
		return this.neighbors.get(getNode(node1)).get(getNode(node2));
	}

    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     * @param key
     */
	@Override
	public void addNode(int key) {
		if (!this.graph.containsKey(key)) { // first solution -> node.key is new
			this.graph.put(key, new Node(key));
			this.neighbors.put(getNode(key), new HashMap<>());
			//this.weights.put(key, new HashMap<Integer,Double>());
			
			this.mc++;
		}
		
//		if(!this.graph.containsKey(key)) { //second solution -> node.key is NOT new (have neighbors)
//			this.neighbors.get(key).contains(node2);
//		}

		
	}

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     */
	@Override
	public void connect(int node1, int node2, double w) {

		node_info a = this.graph.get(node1);
		node_info b = this.graph.get(node2);

		if ((this.graph.containsKey(node1)) && (this.graph.containsKey(node2))) {
			if(node1 != node2) {
				if (hasEdge(node1, node2)) {
					getEdge(node1, node2);
					mc++;
				}
				if (!hasEdge(node1, node2) && !hasEdge(node2, node1)) {
					this.neighbors.get(getNode(node1)).put(getNode(node2), w);
					this.neighbors.get(getNode(node2)).put(getNode(node1), w);

//			this.weights.get(node1).put(node2, w);
//			this.weights.get(node2).put(node1, w);
					this.ec++; //increase edge count
					this.mc++;
				}
			}

//				else {
//			//  1  <->  2   <->   3
//
//			HashMap<Integer,Double> l1 = this.weights.get(node1);
//			HashMap<Integer,Double> l2 = this.weights.get(node2);
//
//			if(l1.get(node2) != w && l2.get(node1) != w) {
//
//			l1.put(node2, w);
//			l2.put(node1, w);
//
//			this.ec++; //increase edge count
//			this.mc++;
//		}
		}
	}

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) tim
     * @return Collection<node_data>
     */
	@Override
	public Collection<node_info> getV() {
		return this.graph.values();
	}

    /**
    *
    * This method returns a Collection containing all the
    * nodes connected to node_id
    * Note: this method can run in O(k) time, k - being the degree of node_id.
    * @return Collection<node_info>
    */
	@Override
	public Collection<node_info> getV(int node_id) {
		if(this.neighbors.containsKey(getNode(node_id)))
		return this.neighbors.get(getNode(node_id)).keySet();
			return null;//o(1)
	}

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
	@Override
	public node_info removeNode(int key) {
		if(this.nodeSize() == 0) return null;
		if(this.graph.containsKey(key)) {
			node_info a = this.getNode(key);
			if (this.neighbors.containsKey(getNode(key))) {
				Iterator<node_info>iterator = this.getV(key).iterator();
				while(iterator.hasNext()) {
					node_info n = iterator.next();
					removeEdge(key, n.getKey());
					iterator = getV(key).iterator();
				}
				this.neighbors.get(getNode(key)).remove(key);
				//this.weights.remove(key, a);
			}
			this.graph.remove(key);
			this.mc++;
			return a;
		}
		return null;
	}

    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     */
	@Override
	public void removeEdge(int node1, int node2) {
			if (this.neighbors.get(getNode(node1)).get(getNode(node2)) != null && hasEdge(node1, node2)) {
				node_info a = this.graph.get(getNode(node1).getKey());
				node_info b = this.graph.get(getNode(node2).getKey());
				this.neighbors.get(getNode(node1)).remove(b);
				this.neighbors.get(getNode(node2)).remove(a);
				//double temp = this.getEdge(node1, node2);
				//this.weights.get(node1).remove(node2, temp);
				//this.weights.get(node2).remove(node1, temp);
				this.mc++;
				this.ec--;
			}
		}

    /** return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     * @return
     */
	@Override
	public int nodeSize() {
		return this.graph.size();
	}

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     * @return
     */
	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return this.ec;
	}

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     * @return
     */
	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return this.mc;
	}

	public String toString() {
		return "" + this.getV();
	}


	@Override
	public boolean equals(Object o) {
		if (!(o instanceof weighted_graph)) return false;
		weighted_graph g = (weighted_graph) o;
		if (this.nodeSize() != g.nodeSize()) return false;
		if (this.edgeSize() != g.edgeSize()) return false;
		for (node_info i : this.getV()) {
			if (this.getNode(i.getKey()) != g.getNode(i.getKey())) return false;
		}
		for (node_info i : this.getV()) {
			for (node_info j : this.getV(i.getKey())) {
				if (this.getV(i.getKey()) != g.getV(i.getKey())) return false;
				else{ if(this.getEdge(i.getKey(), j.getKey()) != g.getEdge(i.getKey(), j.getKey())) return false;}
			}
		}
		return true;
	}
}
