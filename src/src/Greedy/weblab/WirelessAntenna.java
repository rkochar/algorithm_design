package Greedy.weblab;

import java.util.Arrays;
import java.util.Comparator;

public class WirelessAntenna {
	public long cost, number;

	public WirelessAntenna(long cost, long number) {
		this.cost = cost;
		this.number = number;
	}
	/**
	 * @param n The number of stations
	 * @param m The number of edges
	 * @param b the budget
	 * @param edgesIdiot the edges in the network, you should ignore edges[0] and only use edges[1] to edges[m].
	 */
	public static WirelessAntenna setUpTheNetwork(int n, int m, int b, Edge[] edgesIdiot) {
		// TODO
		Edge[] edges = new Edge[m];
		for (int i = 0; i < m; i ++) edges[i] = edgesIdiot[i + 1];
		Arrays.sort(edges, Comparator.comparingInt(x -> x.l));
		Edge[] smallestEdges = new Edge[n - 1];
		UnionFind unionFind = new UnionFind(n);

		int num = 0;
		for (Edge edge: edges) {
			if (unionFind.union(edge.x, edge.y)) smallestEdges[num++] = edge;
			if (num == n - 1) break;
		}

		long sum = 0, amount = 0;
		for (Edge edge: smallestEdges) {
			sum += edge.l;
			if (sum <= b) amount ++;
		}

		return new WirelessAntenna(sum, amount);
	}
}

class Edge {

	// from, to and length
	int x, y, l;

	public Edge(int from, int to, int length) {
		x = from;
		y = to;
		l = length;
	}
}

