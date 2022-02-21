package NetworkFlow;

import java.util.*;

/**
 * WARNING: The spec tests are not equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined solely by a manual inspection of your implementation.
 */
public class DelfFlext {

	/**
	 *   You should implement the method below. Note that you can use the GraphPractice structure below.
	 *   @param n The number of students.
	 *   @param m The number of tasks.
	 *   @param hours is an array of size (n+1)*2 that contains the minimum hours for student i in hours[i][0] and the maximum hours for student i in hours[i][1]. You should ignore hours[0].
	 *   @param work is an array of size (m+1) that contains the number of hours tasks 1 through m take. You should ignore work[0].
	 *   @param skilled is an array of size (n+1)*(m+1) such that skilled[i][j] == 1 iff student i can do task j. You should ignore all skilled[i][0] and skilled[0][j].
	 *   @return true iff all tasks can be completed.
	 */
	public static boolean solve(int n, int m, int[][] hours, int[] work, int[][] skilled) {
		ArrayList<NodePractice> nodes = new ArrayList<>();
		int totalWork = 0;
		for (Integer w: work) totalWork += w;
		NodePractice source = new NodePractice(-1, -totalWork);
		nodes.add(source);
//		NodePractice sink = new NodePractice(-2, -n);

		NodePractice[] students = new NodePractice[n + 1];
		for (int i = 1; i <= n; i ++) {
			NodePractice student = new NodePractice(i, 0);//hours[i][0]);
			students[i] = student;
			nodes.add(student);
			source.addEdgePractice(student, hours[i][0], hours[i][1]);
		}

		NodePractice[] tasks = new NodePractice[m + 1];
		for (int i = 1; i <= m; i ++) {
			NodePractice task = new NodePractice(i, work[i]);
			tasks[i] = task;
			nodes.add(task);
		}

		for (int i = 1; i <= n; i ++) {
			for (int j = 1; j <= m; j ++) {
				if (skilled[i][j] == 1) {
					students[i].addEdgePractice(tasks[j], 0, hours[i][1]);
				}
			}
		}

		GraphPractice graphPractice = new GraphPractice(nodes);
		return graphPractice.hasCirculationOfAtLeast(totalWork);
	}
}

class GraphPractice {

	private List<NodePractice> NodePractices;

	private NodePractice source;

	private NodePractice sink;

	public GraphPractice(List<NodePractice> NodePractices) {
		this.NodePractices = NodePractices;
		this.source = null;
		this.sink = null;
	}

	public NodePractice getSink() {
		return sink;
	}

	public NodePractice getSource() {
		return source;
	}

	public List<NodePractice> getNodePractices() {
		return NodePractices;
	}

	public boolean equals(Object other) {
		if (other instanceof GraphPractice) {
			GraphPractice that = (GraphPractice) other;
			return this.NodePractices.equals(that.NodePractices);
		}
		return false;
	}

	public boolean hasCirculationOfAtLeast(int y) {
		this.removeLowerBounds();
		this.removeSupplyDemand();
		int x = MaxFlowPractice.maximizeFlow(this);
		return x >= y;
	}

	private void removeLowerBounds() {
		for (NodePractice n : this.getNodePractices()) {
			for (EdgePractice e : n.EdgePractices) {
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

	private void removeSupplyDemand() {
		int maxId = 0;
		for (NodePractice n : this.getNodePractices()) {
			maxId = Math.max(n.id, maxId);
		}
		NodePractice newSource = new NodePractice(maxId + 1, 0);
		NodePractice newSink = new NodePractice(maxId + 2, 0);
		for (NodePractice n : this.getNodePractices()) {
			if (n.d < 0) {
				newSource.addEdgePractice(n, 0, -n.d);
			} else if (n.d > 0) {
				n.addEdgePractice(newSink, 0, n.d);
			}
			n.d = 0;
		}
		this.NodePractices.add(newSource);
		this.NodePractices.add(newSink);
		this.source = newSource;
		this.sink = newSink;
	}
}

class NodePractice {

	protected int id;

	protected int d;

	protected Collection<EdgePractice> EdgePractices;

	/**
	 *  Create a new NodePractice
	 *  @param id: Id for the NodePractice.
	 *  @param d: demand for the NodePractice. Remember that supply is represented as a negative demand.
	 */
	public NodePractice(int id, int d) {
		this.id = id;
		this.d = d;
		this.EdgePractices = new ArrayList<EdgePractice>();
	}

	public void addEdgePractice(NodePractice to, int lower, int upper) {
		EdgePractice e = new EdgePractice(lower, upper, this, to);
		EdgePractices.add(e);
		to.getEdgePractices().add(e.getBackwards());
	}

	public Collection<EdgePractice> getEdgePractices() {
		return EdgePractices;
	}

	public int getId() {
		return id;
	}

	public boolean equals(Object other) {
		if (other instanceof NodePractice) {
			NodePractice that = (NodePractice) other;
			if (id == that.getId())
				return EdgePractices.equals(that.getEdgePractices());
		}
		return false;
	}
}

class EdgePractice {

	protected int lower;

	protected int capacity;

	protected int flow;

	protected NodePractice from;

	protected NodePractice to;

	protected EdgePractice backwards;

	private EdgePractice(EdgePractice e) {
		this.lower = 0;
		this.flow = e.getCapacity();
		this.capacity = e.getCapacity();
		this.from = e.getTo();
		this.to = e.getFrom();
		this.backwards = e;
	}

	protected EdgePractice(int lower, int capacity, NodePractice from, NodePractice to) {
		this.lower = lower;
		this.capacity = capacity;
		this.from = from;
		this.to = to;
		this.flow = 0;
		this.backwards = new EdgePractice(this);
	}

	public void augmentFlow(int add) {
		assert (flow + add <= capacity);
		flow += add;
		backwards.setFlow(getResidual());
	}

	public EdgePractice getBackwards() {
		return backwards;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getFlow() {
		return flow;
	}

	public NodePractice getFrom() {
		return from;
	}

	public int getResidual() {
		return capacity - flow;
	}

	public NodePractice getTo() {
		return to;
	}

	private void setFlow(int f) {
		assert (f <= capacity);
		this.flow = f;
	}

	public boolean equals(Object other) {
		if (other instanceof EdgePractice) {
			EdgePractice that = (EdgePractice) other;
			return this.capacity == that.capacity && this.flow == that.flow && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
		}
		return false;
	}
}

class MaxFlowPractice {

	private static List<EdgePractice> findPath(GraphPractice g, NodePractice start, NodePractice end) {
		Map<NodePractice, EdgePractice> mapPath = new HashMap<NodePractice, EdgePractice>();
		Queue<NodePractice> sQueue = new LinkedList<NodePractice>();
		NodePractice currentNodePractice = start;
		sQueue.add(currentNodePractice);
		while (!sQueue.isEmpty() && currentNodePractice != end) {
			currentNodePractice = sQueue.remove();
			for (EdgePractice e : currentNodePractice.getEdgePractices()) {
				NodePractice to = e.getTo();
				if (to != start && mapPath.get(to) == null && e.getResidual() > 0) {
					sQueue.add(e.getTo());
					mapPath.put(to, e);
				}
			}
		}
		if (sQueue.isEmpty() && currentNodePractice != end)
			return null;
		LinkedList<EdgePractice> path = new LinkedList<EdgePractice>();
		NodePractice current = end;
		while (mapPath.get(current) != null) {
			EdgePractice e = mapPath.get(current);
			path.addFirst(e);
			current = e.getFrom();
		}
		return path;
	}

	public static int maximizeFlow(GraphPractice g) {
		int f = 0;
		NodePractice sink = g.getSink();
		NodePractice source = g.getSource();
		List<EdgePractice> path;
		while ((path = findPath(g, source, sink)) != null) {
			int r = Integer.MAX_VALUE;
			for (EdgePractice e : path) {
				r = Math.min(r, e.getResidual());
			}
			for (EdgePractice e : path) {
				e.augmentFlow(r);
			}
			f += r;
		}
		return f;
	}
}


