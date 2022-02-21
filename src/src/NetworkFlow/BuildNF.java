package NetworkFlow;

import java.util.*;

/**
 * WARNING: The spec tests are not equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined solely by a manual inspection of your implementation.
 */
public class BuildNF {

	/**
	 *   You should implement the method below. Note that you can use the GraphExam structure below.
	 *   @param n The number of code changes.
	 *   @param benefits An array of dimension n+1 containing the benefits of all the code changes for 1 <= i <= n
	 *   @param costs An array of dimension n+1 containing the costs of all the code changes for 1 <= i <= n
	 *   @param dependencies is an array of dimension (n+1)*(n+1) that contains value 1 iff code change i depends on j and 0 otherwise, for all 1 <= i,j <= n.
	 *   @return
	 */
	public static int solve(int n, int[] benefits, int[] costs, int[][] dependencies) {
		ArrayList<NodeExam> nodes = new ArrayList<>();
		NodeExam source = new NodeExam(-1);
		NodeExam sink = new NodeExam(-2);
		nodes.add(source);
		nodes.add(sink);

		NodeExam[] benefitsList = new NodeExam[n + 1];
		NodeExam[] costsList = new NodeExam[n + 1];
		for (int i = 1; i <= n; i ++) {
			NodeExam benefit = new NodeExam(i);
			NodeExam cost = new NodeExam(i);
			benefitsList[i] = benefit;
			costsList[i] = cost;
			source.addEdgeExam(benefit, benefits[i]);
			cost.addEdgeExam(sink, costs[i]);
		}

		for (int i = 1; i <= n; i ++) {
			for (int j = 1; j <= n; j ++) {
				if (dependencies[i][j] == 1) {
					benefitsList[i].addEdgeExam(costsList[i], Integer.MAX_VALUE);
				}
			}
		}
		GraphExam graphExam = new GraphExam(nodes, source, sink);
		graphExam.maximizeFlow();
		int f = 0;
		for (EdgeExam edgeExam: graphExam.getSource().getEdgeExams()) {
			f += edgeExam.getFlow();
		}
		int profit = 0;
		for (Integer benefit: benefits) profit += benefit;
		return profit - f;
	}
}

class MaxFlowExam {

	private static List<EdgeExam> findPath(GraphExam g, NodeExam start, NodeExam end) {
		Map<NodeExam, EdgeExam> mapPath = new HashMap<NodeExam, EdgeExam>();
		Queue<NodeExam> sQueue = new LinkedList<NodeExam>();
		NodeExam currentNodeExam = start;
		sQueue.add(currentNodeExam);
		while (!sQueue.isEmpty() && currentNodeExam != end) {
			currentNodeExam = sQueue.remove();
			for (EdgeExam e : currentNodeExam.getEdgeExams()) {
				NodeExam to = e.getTo();
				if (to != start && mapPath.get(to) == null && e.getResidual() > 0) {
					sQueue.add(e.getTo());
					mapPath.put(to, e);
				}
			}
		}
		if (sQueue.isEmpty() && currentNodeExam != end)
			return null;
		LinkedList<EdgeExam> path = new LinkedList<EdgeExam>();
		NodeExam current = end;
		while (mapPath.get(current) != null) {
			EdgeExam e = mapPath.get(current);
			path.addFirst(e);
			current = e.getFrom();
		}
		return path;
	}

	public static void maximizeFlow(GraphExam g) {
		NodeExam sink = g.getSink();
		NodeExam source = g.getSource();
		List<EdgeExam> path;
		while ((path = findPath(g, source, sink)) != null) {
			int r = Integer.MAX_VALUE;
			for (EdgeExam e : path) {
				r = Math.min(r, e.getResidual());
			}
			for (EdgeExam e : path) {
				e.augmentFlow(r);
			}
		}
	}
}

class GraphExam {

	private List<NodeExam> NodeExams;

	private NodeExam source;

	private NodeExam sink;

	public GraphExam(List<NodeExam> NodeExams, NodeExam source, NodeExam sink) {
		this.NodeExams = NodeExams;
		this.source = source;
		this.sink = sink;
	}

	public NodeExam getSink() {
		return sink;
	}

	public NodeExam getSource() {
		return source;
	}

	public List<NodeExam> getNodeExams() {
		return NodeExams;
	}

	public boolean equals(Object other) {
		if (other instanceof GraphExam) {
			GraphExam that = (GraphExam) other;
			return this.NodeExams.equals(that.NodeExams);
		}
		return false;
	}

	public void maximizeFlow() {
		MaxFlowExam.maximizeFlow(this);
	}
}

class NodeExam {

	protected int id;

	protected Collection<EdgeExam> EdgeExams;

	public NodeExam(int id) {
		this.id = id;
		this.EdgeExams = new ArrayList<EdgeExam>();
	}

	public void addEdgeExam(NodeExam to, int capacity) {
		EdgeExam e = new EdgeExam(capacity, this, to);
		EdgeExams.add(e);
		to.getEdgeExams().add(e.getBackwards());
	}

	public Collection<EdgeExam> getEdgeExams() {
		return EdgeExams;
	}

	public int getId() {
		return id;
	}

	public boolean equals(Object other) {
		if (other instanceof NodeExam) {
			NodeExam that = (NodeExam) other;
			if (id == that.getId())
				return EdgeExams.equals(that.getEdgeExams());
		}
		return false;
	}
}

class EdgeExam {

	protected int capacity;

	protected int flow;

	protected NodeExam from;

	protected NodeExam to;

	protected EdgeExam backwards;

	private EdgeExam(EdgeExam e) {
		this.flow = e.getCapacity();
		this.capacity = e.getCapacity();
		this.from = e.getTo();
		this.to = e.getFrom();
		this.backwards = e;
	}

	protected EdgeExam(int capacity, NodeExam from, NodeExam to) {
		this.capacity = capacity;
		this.from = from;
		this.to = to;
		this.flow = 0;
		this.backwards = new EdgeExam(this);
	}

	public void augmentFlow(int add) {
		assert (flow + add <= capacity);
		flow += add;
		backwards.setFlow(getResidual());
	}

	public EdgeExam getBackwards() {
		return backwards;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getFlow() {
		return flow;
	}

	public NodeExam getFrom() {
		return from;
	}

	public int getResidual() {
		return capacity - flow;
	}

	public NodeExam getTo() {
		return to;
	}

	private void setFlow(int f) {
		assert (f <= capacity);
		this.flow = f;
	}

	public boolean equals(Object other) {
		if (other instanceof EdgeExam) {
			EdgeExam that = (EdgeExam) other;
			return this.capacity == that.capacity && this.flow == that.flow && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
		}
		return false;
	}
}
