package Exam.Resit;

import java.io.*;
import java.util.*;

public class VaccineSensorFlow {

	/**
	 * You should implement this method
	 *
	 * @param n the number of events
	 * @param m the number of companies
	 * @param z the total number of sensors
	 * @param k the number of measurements required per event.
	 * @param t the company a sensor belongs to. Sensor 1 <= j <= z belongs to company 1 <= t[j] <= m. You should ignore t[0].
	 * @param c the compatibility of events and sensors. Event 1 <= i <= n can use sensor 1 <= j <= z iff c[i][j] = true. You should ignore c[0][x] and c[x][0].
	 * @return true iff we can organise all events with this set of sensors.
	 */
	public static boolean organisingEvents(int n, int m, int z, int k, int[] t, boolean[][] c) {
		return solveProper(n, m, z, k, t, c);
	}

	private static boolean solveProper(int n, int m, int z, int k, int[] t, boolean[][] c) {
		List<Node> nodes = new ArrayList<>();
		Node s = new Node(-1, 0);
		Node sink = new Node(-2, 0);
		nodes.add(s);
		Node[] events = new Node[n + 1];
		for (int i = 1; i <= n; i++) {
			events[i] = new Node(i, 0);
			s.addEdge(events[i], 0, k);
			nodes.add(events[i]);
		}
		Node[][] companySensor = new Node[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				companySensor[i][j] = new Node(i + j, 0);
				// Ensure this company cannot do _all_.
				events[i].addEdge(companySensor[i][j], 0, k - 1);
				nodes.add(companySensor[i][j]);
			}
		}
		Node[] sensors = new Node[z + 1];
		for (int j = 1; j <= z; j++) {
			sensors[j] = new Node(j, 0);
			for (int i = 1; i <= n; i++) {
				if (c[i][j]) {
					companySensor[i][t[j]].addEdge(sensors[j], 0, 1);
				}
			}
			sensors[j].addEdge(sink, 0, 1);
			nodes.add(sensors[j]);
		}
		Graph g = new Graph(nodes, s, sink);
		return MaxFlow.maximizeFlow(g) == n * k;
	}

	public static void main(String[] args) throws FileNotFoundException {
		String dirName = "src/main/java/adweblab/exams/resit_2020_2021/fff_nf/implementation/fieldlabs/data/secret/";
		File dir = new File(dirName);
		System.out.println(dir.exists());
		for (File f : dir.listFiles()) {
			if (f.getName().endsWith("in")) {
				FileInputStream in = new FileInputStream(f);
				System.out.println(f.getAbsolutePath());
				String ans = run(in);
				System.out.println(ans);
				PrintWriter out = new PrintWriter(f.getAbsolutePath().replace(".in", ".out"));
				out.println(ans);
				out.close();
				System.out.println();
			}
		}
	}

	public static String run(InputStream in) {
		Scanner sc = new Scanner(in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int z = sc.nextInt();
		int k = sc.nextInt();
		int numPairs = sc.nextInt();
		int[] t = new int[z + 1];
		boolean[][] c = new boolean[n + 1][z + 1];
		for (int j = 1; j <= z; j++) {
			t[j] = sc.nextInt();
		}
		for (int i = 1; i <= numPairs; i++) {
			c[sc.nextInt()][sc.nextInt()] = true;
		}
		sc.close();
		boolean ans = organisingEvents(n, m, z, k, t, c);
		return Boolean.toString(ans);
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
