package NetworkFlow;

import java.util.*;

public class NetworkFlow {
}

class Graph {

	private List<Node> nodes;

	private Node source;

	private Node sink;

	public Graph(List<Node> nodes) {
		this.nodes = nodes;
		this.source = null;
		this.sink = null;
	}

	public Node getSink() {
		return sink;
	}

	public Node getSource() {
		return source;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public boolean equals(Object other) {
		if (other instanceof Graph) {
			Graph that = (Graph) other;
			return this.nodes.equals(that.nodes);
		}
		return false;
	}

	/** You should implement this method. */
	public boolean hasCirculation() {
		// TODO
		this.removeLowerBounds();
		int supplyDemandRemoved = this.removeSupplyDemand();
		int flow = MaxFlow.maximizeFlow(this);
		return flow == supplyDemandRemoved;
	}

	/** Should remove all lower bounds on edges. */
	private void removeLowerBounds() {
		// TODO
		for (Node node: this.getNodes()) {
			for (Edge edge: node.getEdges()) {
				if (edge.lower > 0) {
					edge.capacity -= edge.lower;
					edge.from.d += edge.lower;
					edge.to.d -= edge.lower;
					edge.backwards.capacity -= edge.lower;
					edge.backwards.flow -= edge.lower;
					edge.lower = 0;
				}
			}
		}
	}

	/**
	 * Should remove all supply and demand from nodes.
	 *
	 * @return the sum of demands
	 */
	private int removeSupplyDemand() {
		// TODO
		Node source = new Node(-10, 0);
		Node sink = new Node(-11, 0);

		int demand = 0;
		for (Node node: this.getNodes()) {
			// Supply
			if (node.d < 0) {
				source.addEdge(node, 0, -node.d);
			} else if (node.d > 0) {
			// Demand
			node.addEdge(sink, 0, node.d);
			demand += node.d;
			}
		}

		this.source = source;
		this.sink = sink;
		nodes.add(sink);
		nodes.add(source);
		return demand;
	}
}

class Node {

	protected int id;

	protected int d;

	protected Collection<Edge> edges;

	/**
	 * Create a new node
	 *
	 * @param id: Id for the node.
	 * @param d: demand for the node. Remember that supply is represented as a negative demand.
	 */
	public Node(int id, int d) {
		this.id = id;
		this.d = d;
		this.edges = new ArrayList<Edge>();
	}

	public void addEdge(Node to, int lower, int upper) {
		Edge e = new Edge(lower, upper, this, to);
		edges.add(e);
		to.getEdges().add(e.getBackwards());
	}

	public Collection<Edge> getEdges() {
		return edges;
	}

	public int getId() {
		return id;
	}

	public boolean equals(Object other) {
		if (other instanceof Node) {
			Node that = (Node) other;
			if (id == that.getId()) return edges.equals(that.getEdges());
		}
		return false;
	}
}

class Edge {

	protected int lower;

	protected int capacity;

	protected int flow;

	protected Node from;

	protected Node to;

	protected Edge backwards;

	private Edge(Edge e) {
		this.lower = 0;
		this.flow = e.getCapacity();
		this.capacity = e.getCapacity();
		this.from = e.getTo();
		this.to = e.getFrom();
		this.backwards = e;
	}

	protected Edge(int lower, int capacity, Node from, Node to) {
		this.lower = lower;
		this.capacity = capacity;
		this.from = from;
		this.to = to;
		this.flow = 0;
		this.backwards = new Edge(this);
	}

	public void augmentFlow(int add) {
		assert (flow + add <= capacity);
		flow += add;
		backwards.setFlow(getResidual());
	}

	public Edge getBackwards() {
		return backwards;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getFlow() {
		return flow;
	}

	public Node getFrom() {
		return from;
	}

	public int getResidual() {
		return capacity - flow;
	}

	public Node getTo() {
		return to;
	}

	private void setFlow(int f) {
		assert (f <= capacity);
		this.flow = f;
	}

	public boolean equals(Object other) {
		if (other instanceof Edge) {
			Edge that = (Edge) other;
			return this.capacity == that.capacity
					&& this.flow == that.flow
					&& this.from.getId() == that.getFrom().getId()
					&& this.to.getId() == that.getTo().getId();
		}
		return false;
	}
}

class MaxFlow {

	private static List<Edge> findPath(Graph g, Node start, Node end) {
		Map<Node, Edge> mapPath = new HashMap<Node, Edge>();
		Queue<Node> sQueue = new LinkedList<Node>();
		Node currentNode = start;
		sQueue.add(currentNode);
		while (!sQueue.isEmpty() && currentNode != end) {
			currentNode = sQueue.remove();
			for (Edge e : currentNode.getEdges()) {
				Node to = e.getTo();
				if (to != start && mapPath.get(to) == null && e.getResidual() > 0) {
					sQueue.add(e.getTo());
					mapPath.put(to, e);
				}
			}
		}
		if (sQueue.isEmpty() && currentNode != end) return null;
		LinkedList<Edge> path = new LinkedList<Edge>();
		Node current = end;
		while (mapPath.get(current) != null) {
			Edge e = mapPath.get(current);
			path.addFirst(e);
			current = e.getFrom();
		}
		return path;
	}

	public static int maximizeFlow(Graph g) {
		int f = 0;
		Node sink = g.getSink();
		Node source = g.getSource();
		List<Edge> path;
		while ((path = findPath(g, source, sink)) != null) {
			int r = Integer.MAX_VALUE;
			for (Edge e : path) {
				r = Math.min(r, e.getResidual());
			}
			for (Edge e : path) {
				e.augmentFlow(r);
			}
			f += r;
		}
		return f;
	}
}


