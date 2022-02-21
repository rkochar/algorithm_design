package NetworkFlow;

import java.util.ArrayList;

public class Aeronauts {

	/**
	 * @param l the number of flights Lee has already done
	 * @param n the number of competitors
	 * @param m the number of open slots left
	 * @param flights the number of flights each of the competitors has done. You should use flights[1] to flights[n]
	 * @param compatible 2D boolean array such that slot i can be used by competitor j iff compatible[i][j] is true. Note that compatible[0][x] and compatible[x][0] should not be used.
	 * @return
	 */
	public static boolean solve(int l, int n, int m, int[] flights, boolean[][] compatible) {
		ArrayList<Node> nodes = new ArrayList<>();
		Node source = new Node(-1, 0);
		Node sink = new Node(-2, 0);
		nodes.add(source);
		nodes.add(sink);

		Node[] competitors = new Node[n + 1];
		for (int i = 1; i <= n; i ++) {
			Node competitor = new Node(i, 0);
			competitors[i] = competitor;
			if (flights[i] >= l) return false;
			competitor.addEdge(sink, 0, l - flights[i] - 1);
		}

		Node[] slots = new Node[m + 1];
		for (int j = 1; j <= m; j ++) {
			Node slot = new Node(j, 0);
			nodes.add(slot);
			source.addEdge(slot, 0, 1);
		}

		for (int i = 1; i <= n; i ++) {
			for (int j = 1; j <= m; j ++) {
				if (compatible[i][j]) slots[i].addEdge(competitors[j], 0, 1);
			}
		}

		Graph graph = new Graph(nodes, source, sink);
		MaxFlow.maximizeFlow(graph);
		for (Edge edge: graph.getSource().getEdges()) {
			if (edge.getResidual() > 0) return false;
		}

		return true;
	}
}