package Exam.TwentyOne.Resit;

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
		List<VaccineNode> vaccineNodes = new ArrayList<>();
		VaccineNode s = new VaccineNode(-1, 0);
		VaccineNode sink = new VaccineNode(-2, 0);
		vaccineNodes.add(s);
		VaccineNode[] events = new VaccineNode[n + 1];
		for (int i = 1; i <= n; i++) {
			events[i] = new VaccineNode(i, 0);
			s.addEdge(events[i], 0, k);
			vaccineNodes.add(events[i]);
		}
		VaccineNode[][] companySensor = new VaccineNode[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				companySensor[i][j] = new VaccineNode(i + j, 0);
				// Ensure this company cannot do _all_.
				events[i].addEdge(companySensor[i][j], 0, k - 1);
				vaccineNodes.add(companySensor[i][j]);
			}
		}
		VaccineNode[] sensors = new VaccineNode[z + 1];
		for (int j = 1; j <= z; j++) {
			sensors[j] = new VaccineNode(j, 0);
			for (int i = 1; i <= n; i++) {
				if (c[i][j]) {
					companySensor[i][t[j]].addEdge(sensors[j], 0, 1);
				}
			}
			sensors[j].addEdge(sink, 0, 1);
			vaccineNodes.add(sensors[j]);
		}
		VaccineGraph g = new VaccineGraph(vaccineNodes, s, sink);
		return VaccineMaxFlow.maximizeFlow(g) == n * k;
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

class VaccineGraph {

	private List<VaccineNode> vaccineNodes;

	private VaccineNode source;

	private VaccineNode sink;

	public VaccineGraph(List<VaccineNode> vaccineNodes) {
		this.vaccineNodes = vaccineNodes;
		this.source = null;
		this.sink = null;
	}

	public VaccineGraph(List<VaccineNode> vaccineNodes, VaccineNode source, VaccineNode sink) {
		this.vaccineNodes = vaccineNodes;
		this.source = source;
		this.sink = sink;
	}

	public VaccineNode getSink() {
		return sink;
	}

	public VaccineNode getSource() {
		return source;
	}

	public List<VaccineNode> getNodes() {
		return vaccineNodes;
	}

	public boolean equals(Object other) {
		if (other instanceof VaccineGraph) {
			VaccineGraph that = (VaccineGraph) other;
			return this.vaccineNodes.equals(that.vaccineNodes);
		}
		return false;
	}

	public boolean hasCirculation() {
		this.removeLowerBounds();
		int D = this.removeSupplyDemand();
		int x = VaccineMaxFlow.maximizeFlow(this);
		return x == D;
	}

	private void removeLowerBounds() {
		for (VaccineNode n : this.getNodes()) {
			for (VaccineEdge e : n.edges) {
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
		for (VaccineNode n : this.getNodes()) {
			maxId = Math.max(n.id, maxId);
		}
		VaccineNode newSource = new VaccineNode(maxId + 1, 0);
		VaccineNode newSink = new VaccineNode(maxId + 2, 0);
		for (VaccineNode n : this.getNodes()) {
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
		this.vaccineNodes.add(newSource);
		this.vaccineNodes.add(newSink);
		this.source = newSource;
		this.sink = newSink;
		return Dplus;
	}
}

class VaccineNode {

	protected int id;

	protected int d;

	protected Collection<VaccineEdge> edges;

	/**
	 * Create a new node
	 *
	 * @param id: Id for the node.
	 * @param d:  demand for the node. Remember that supply is represented as a negative demand.
	 */
	public VaccineNode(int id, int d) {
		this.id = id;
		this.d = d;
		this.edges = new ArrayList<VaccineEdge>();
	}

	public void addEdge(VaccineNode to, int lower, int upper) {
		VaccineEdge e = new VaccineEdge(lower, upper, this, to);
		edges.add(e);
		to.getEdges().add(e.getBackwards());
	}

	public Collection<VaccineEdge> getEdges() {
		return edges;
	}

	public int getId() {
		return id;
	}

	public boolean equals(Object other) {
		if (other instanceof VaccineNode) {
			VaccineNode that = (VaccineNode) other;
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
		for (VaccineEdge e : this.getEdges()) {
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

class VaccineEdge {

	protected int lower;

	protected int capacity;

	protected int flow;

	protected VaccineNode from;

	protected VaccineNode to;

	protected VaccineEdge backwards;

	private VaccineEdge(VaccineEdge e) {
		this.lower = 0;
		this.flow = e.getCapacity();
		this.capacity = e.getCapacity();
		this.from = e.getTo();
		this.to = e.getFrom();
		this.backwards = e;
	}

	protected VaccineEdge(int lower, int capacity, VaccineNode from, VaccineNode to) {
		this.lower = lower;
		this.capacity = capacity;
		this.from = from;
		this.to = to;
		this.flow = 0;
		this.backwards = new VaccineEdge(this);
	}

	public void augmentFlow(int add) {
		assert (flow + add <= capacity);
		flow += add;
		backwards.setFlow(getResidual());
	}

	public VaccineEdge getBackwards() {
		return backwards;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getFlow() {
		return flow;
	}

	public VaccineNode getFrom() {
		return from;
	}

	public int getResidual() {
		return capacity - flow;
	}

	public VaccineNode getTo() {
		return to;
	}

	private void setFlow(int f) {
		assert (f <= capacity);
		this.flow = f;
	}

	public boolean equals(Object other) {
		if (other instanceof VaccineEdge) {
			VaccineEdge that = (VaccineEdge) other;
			return this.capacity == that.capacity && this.flow == that.flow && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
		}
		return false;
	}
}

class VaccineMaxFlow {

	private static List<VaccineEdge> findPath(VaccineGraph g, VaccineNode start, VaccineNode end) {
		Map<VaccineNode, VaccineEdge> mapPath = new HashMap<VaccineNode, VaccineEdge>();
		Queue<VaccineNode> sQueue = new LinkedList<VaccineNode>();
		VaccineNode currentVaccineNode = start;
		sQueue.add(currentVaccineNode);
		while (!sQueue.isEmpty() && currentVaccineNode != end) {
			currentVaccineNode = sQueue.remove();
			for (VaccineEdge e : currentVaccineNode.getEdges()) {
				VaccineNode to = e.getTo();
				if (to != start && mapPath.get(to) == null && e.getResidual() > 0) {
					sQueue.add(e.getTo());
					mapPath.put(to, e);
				}
			}
		}
		if (sQueue.isEmpty() && currentVaccineNode != end)
			return null;
		LinkedList<VaccineEdge> path = new LinkedList<VaccineEdge>();
		VaccineNode current = end;
		while (mapPath.get(current) != null) {
			VaccineEdge e = mapPath.get(current);
			path.addFirst(e);
			current = e.getFrom();
		}
		return path;
	}

	public static int maximizeFlow(VaccineGraph g) {
		int f = 0;
		VaccineNode sink = g.getSink();
		VaccineNode source = g.getSource();
		List<VaccineEdge> path;
		while ((path = findPath(g, source, sink)) != null) {
			int r = Integer.MAX_VALUE;
			for (VaccineEdge e : path) {
				r = Math.min(r, e.getResidual());
			}
			for (VaccineEdge e : path) {
				e.augmentFlow(r);
			}
			f += r;
		}
		return f;
	}
}
