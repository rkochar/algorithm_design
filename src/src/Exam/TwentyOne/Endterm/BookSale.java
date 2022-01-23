package Exam.TwentyOne.Endterm;

import java.util.*;

public class BookSale {

	/**
	 * You should implement this method
	 *
	 * @param n the number of volunteers
	 * @param m the number of books
	 * @param k the number of days
	 * @param p the number of books a volunteer can deliver from index _1_ to _n_. You should ignore a[0].
	 * @param d the day on which the books need to be delivered from index _1_ to _m_. You should ignore d[0].
	 * @param a the compatibility of volunteers and books. Book 1 <= i <= m can be delivered on day 1 <= j <= k iff a[i][j] = true. You should ignore a[0][x] and a[x][0].
	 * @return true iff CH can deliver all the books, reme  mbering that volunteers can do at most 3 deliveries per day.
	 */
	public static boolean gettingThoseBooksOut(int n, int m, int k, int[] p, int[] d, boolean[][] a) {
		// General idea.
		// Connect source -> books -> delivery days -> delivery volunteers -> sink.
		// Assume all volunteers can be asked to deliver a compatible book on any day.
		Node source = new Node(-1, -m);
		Node sink = new Node(-2, m);
		ArrayList<Node> nodes = new ArrayList<>();
		nodes.add(source);
		nodes.add(sink);

		Node[] days = new Node[k + 1];
		for (int i = 1; i <= k; i++) {
			Node day = new Node(i, 0);
			days[i] = day;
			nodes.add(day);
		}

		// Create nodes for books.
		Node[] books = new Node[m + 1];
		for (int i = 1; i <= m; i++) {
			Node book = new Node(i, 0);
			books[i] = book;
			nodes.add(book);
			// Each book needs to be delivered at most once.
			source.addEdge(book, 0, 1);
			// Each book has specific delivery day.
			books[i].addEdge(days[d[i]], 0, 1);
		}

		Node[] volunteers = new Node[n + 1];
		for (int i = 1; i <= n; i++) {
			Node volunteer = new Node(i, 0);
			volunteers[i] = volunteer;
			nodes.add(volunteer);
			// Each volunteer can deliver at most p_i books.
			volunteer.addEdge(sink, 0, p[i]);
		}

		for (int i = 1; i <= k; i++) {
			for (int j = 1; j <= n; j++) {
				// Each volunteer can deliver at most 3 books each day.
				days[i].addEdge(volunteers[j], 0, 3);
			}
		}

		Node[] book_two = new Node[m + 1];
		for (int i = 1; i <= m; i++) {
			Node book = new Node(i, 0);
			nodes.add(book);
			book_two[i] = book;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				//
				if (a[i][j]) {
					volunteers[i].addEdge(book_two[i], 0, 1);
				}
			}
		}

		sink.addEdge(source, 0, Integer.MAX_VALUE / 2);
		Graph graph = new Graph(nodes);
		return graph.hasCirculation();
	}

	private static boolean solveProper(int n, int m, int k, int[] p, int[] d, boolean[][] c) {
		List<Node> nodes = new ArrayList<>();
		// Alternative is to not use a supply, but to check later that the max flow = m
		Node s = new Node(-1, -m);
		nodes.add(s);
		Node[] members = new Node[n + 1];
		for (int i = 1; i <= n; i++) {
			members[i] = new Node(i, 0);
			s.addEdge(members[i], 0, p[i]);
			nodes.add(members[i]);
		}
		Node[][] member_to_day = new Node[n + 1][k + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				member_to_day[i][j] = new Node(j, 0);
				nodes.add(member_to_day[i][j]);
				// At most 3 per day!
				members[i].addEdge(member_to_day[i][j], 0, 3);
			}
		}
		Node[] books = new Node[m + 1];
		for (int z = 1; z <= m; z++) {
			// Alternative is to not use a demand, but to check later that the max flow = m
			books[z] = new Node(z, 1);
			nodes.add(books[z]);
			for (int i = 1; i <= n; i++) {
				if (c[i][z]) {
					member_to_day[i][d[z]].addEdge(books[z], 0, 1);
				}
			}
		}
		Graph g = new Graph(nodes);
		return g.hasCirculation();
	}
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

	public Graph(List<Node> nodes, Node source, Node sink) {
		this.nodes = nodes;
		this.source = source;
		this.sink = sink;
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

	public boolean hasCirculation() {
		this.removeLowerBounds();
		int D = this.removeSupplyDemand();
		int x = MaxFlow.maximizeFlow(this);
		return x == D;
	}

	private void removeLowerBounds() {
		for (Node n : this.getNodes()) {
			for (Edge e : n.edges) {
				if (e.lower > 0) {
					e.capacity -= e.lower;
					e.backwards.capacity -= e.lower;
					e.backwards.flow -= e.lower;
					e.from.d += e.lower;
					e.to.d -= e.lower;
					e.lower = 0;
				}
			}
		}
	}

	private int removeSupplyDemand() {
		int Dplus = 0, Dmin = 0;
		int maxId = 0;
		for (Node n : this.getNodes()) {
			maxId = Math.max(n.id, maxId);
		}
		Node newSource = new Node(maxId + 1, 0);
		Node newSink = new Node(maxId + 2, 0);
		for (Node n : this.getNodes()) {
			if (n.d < 0) {
				newSource.addEdge(n, 0, -n.d);
				Dmin -= n.d;
			} else if (n.d > 0) {
				n.addEdge(newSink, 0, n.d);
				Dplus += n.d;
			}
			n.d = 0;
		}
		if (Dmin != Dplus) {
			throw new IllegalArgumentException("Demand and supply are not equal!");
		}
		this.nodes.add(newSource);
		this.nodes.add(newSink);
		this.source = newSource;
		this.sink = newSink;
		return Dplus;
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
	 * @param d:  demand for the node. Remember that supply is represented as a negative demand.
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
			if (id == that.getId())
				return edges.equals(that.getEdges());
		}
		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getId());
		sb.append(" ");
		sb.append(this.getEdges().size());
		sb.append(":");
		for (Edge e : this.getEdges()) {
			sb.append("(");
			sb.append(e.from.getId());
			sb.append(" --[");
			sb.append(e.lower);
			sb.append(',');
			sb.append(e.capacity);
			sb.append("]-> ");
			sb.append(e.to.getId());
			sb.append(")");
		}
		return sb.toString();
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
			return this.capacity == that.capacity && this.flow == that.flow && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
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
		if (sQueue.isEmpty() && currentNode != end)
			return null;
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

