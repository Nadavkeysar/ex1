package ex1;

import java.util.*;
import java.io.*;


public class WGraph_Algo implements weighted_graph_algorithms {

	weighted_graph graph;

	private HashMap<Integer, Double> dist;
	private HashMap<Integer, Integer> prev;



	/**
	 * Init the graph on which this set of algorithms operates on.
	 * @param g
	 */
	@Override
	public void init(weighted_graph g) {
		this.graph = g;

		this.dist = new HashMap<>();
		this.prev = new HashMap<>();
	}

	/**
	 * Return the underlying graph of which this class works.
	 * @return
	 */
	@Override
	public weighted_graph getGraph() {
		return this.graph;
	}

	/**
	 * Compute a deep copy of this weighted graph.
	 * @return
	 */
	@Override
	public weighted_graph copy() {

		weighted_graph deep_copy = new WGraph_DS();

		for (node_info vertex : this.graph.getV()) {
			//new node based on same KEY.
			//add to the new graph (deep_copy)

			deep_copy.addNode(vertex.getKey());

			node_info node = deep_copy.getNode(vertex.getKey());

			node.setInfo(vertex.getInfo());
			node.setTag(vertex.getTag());

		}


		//connect all new nodes in the new graph
		//based on the old connections in the old graph

		for (node_info vertex : this.graph.getV()) { //for each vertex in graph

			for (node_info neighbor : this.graph.getV(vertex.getKey())) { //for each neighbor of vertex

				deep_copy.connect(vertex.getKey(), neighbor.getKey(),
						this.graph.getEdge(vertex.getKey(), neighbor.getKey()));

			}

		}


		//update all new nodes information:
		//update weight, tag, info

		return deep_copy;
	}




	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node. NOTE: assume ubdirectional graph.
	 * @return
	 */
	@Override
	public boolean isConnected() {

		// checking if graph is empty
		if(this.graph.nodeSize() == 0 || this.graph.nodeSize() == 1) return true;

		// change all the tags of nodes in the graph to 0 - like never visited
		Collection<node_info> nodesCo = this.graph.getV();
		for(node_info node : nodesCo) node.setTag(0);

		// getting first element (node_info) from the graph collection
		Collection<node_info> nodes = this.graph.getV();
		ArrayList<node_info> newNodes = new ArrayList<>(nodes);
		node_info first = newNodes.get(0);

		// visited nodes contains all the nodes that have been visited
		ArrayList<Integer> visitedNodes = new ArrayList<>();

		// queue for the BFS
		Queue<node_info> queue = new LinkedList();

		// checking the first node_info
		visitedNodes.add(first.getKey());
		queue.add(first);
		first.setTag(1);

		// BFS algorithm
		while (!queue.isEmpty()) {
			node_info t = queue.poll();
			for (node_info m : this.graph.getV(t.getKey())) {
				if (m.getTag() == 0) {
					visitedNodes.add(m.getKey());
					m.setTag(1);
					queue.add(m);
				}
			}
		}

		if (visitedNodes.size() == this.graph.nodeSize()) return true;
		else return false;
	}

	/**
	 * returns the length of the shortest path between src to dest
	 * Note: if no such path --> returns -1
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		List<node_info> list = shortestPath(src, dest);

		if(list == null) return  -1;
		else if(list.size() == 1) return 0;
		else return this.graph.getNode(dest).getTag();
	}

	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * Note if no such path --> returns null;
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {

		node_info a = this.graph.getNode(src);
		node_info b = this.graph.getNode(dest);

		if(this.graph.nodeSize() == 0 || a == null || b == null) return null;

		if(src == dest) {
			List<node_info> alist = new ArrayList<>();
			alist.add(a);
			return alist;
		}

		List<node_info> res = new ArrayList<>();

		PriorityQueue<Integer> p = new PriorityQueue<>();

		for(node_info i : this.graph.getV()) {
			i.setTag(Double.MAX_VALUE);
		}

		a.setTag(0);
		p.add(a.getKey());

		while(!p.isEmpty()) {

			int temp = p.poll();

			for(node_info i : this.graph.getV(temp)) {
				double distNew = this.graph.getNode(temp).getTag() + this.graph.getEdge(i.getKey(),temp);
				if(distNew < i.getTag()) {
					i.setTag(distNew);
					p.add(i.getKey());
					this.prev.put(i.getKey(), temp);
				}
			}
		}


		node_info current = b;
		res.add(b);

		while (true) {

			res.add(this.graph.getNode(this.prev.get(current.getKey())));
			current = this.graph.getNode(this.prev.get(current.getKey()));

			if (current == a) {
				break;
			}

		}
		Collections.reverse(res);

		return res;
	}

	/**
	 * Saves this weighted (undirected) graph to the given
	 * file name
	 * @param file - the file name (may include a relative path).
	 * @return true - iff the file was successfully saved
	 */
	@Override
	public boolean save(String file)  {

		// trying to save the object to a file
		try {

			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			// write to file
			out.writeObject((WGraph_DS)this.graph);

			// close file
			out.close();
			fileOut.close();
			return true;

			// fail write to file
		} catch (IOException e) { return false; }
	}

	// load graph object from file
	@Override
	public boolean load(String file) {

		// trying to read the file
		try {

			// open the file
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			// init the graph object
			this.init((weighted_graph)in.readObject());

			// close the file
			in.close();
			fileIn.close();
			return true;

			// fail read a file

			} catch (IOException i) {
				return false;
			} catch (ClassNotFoundException c) {
				return false;
			}
	}

}

