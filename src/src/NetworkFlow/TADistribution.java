package NetworkFlow;

import java.util.ArrayList;

/**
 * WARNING: The spec tests are not necessarily equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined by a manual inspection of your implementation.
 */
public class TADistribution {

	/**
	 * You should implement this method
	 *
	 * @param n the number of TAs
	 * @param a the number of hours a TA is available from index _1_ to _n_. You should ignore a[0].
	 * @param m the number of courses
	 * @param b the number of TA hours a course requires from index _1_ to _m_. You should ignore b[0].
	 * @param c a matrix indicating whether a TA is available to assist in a course. The value c[i][j] is true iff TA i can assist course j. You should ignore c[0][j] and c[i][0].
	 * @return the number of hours we are short when optimally using the available TAs.
	 */
	public static int shortageOfTAs(int n, int[] a, int m, int[] b, boolean[][] c) {
		Node source = new Node(-1, 0);
		Node sink = new Node(-2, 0);
		ArrayList<Node> nodes = new ArrayList<>();
		nodes.add(source);
		nodes.add(sink);
		Node[] tas = new Node[n + 1];
		for (int i = 1; i <= n; i ++) {
			Node ta = new Node(i, 0);
			nodes.add(ta);
			tas[i] = ta;
			source.addEdge(ta, 0, a[i]);
		}

		Node[] courses = new Node[m + 1];
		int req = 0;
		for (int i = 1; i <= m; i ++) {
			Node course = new Node(n + 1 + i, 0);
			courses[i] = course;
			nodes.add(course);
			course.addEdge(sink, 0, b[i]);
			req += b[i];
		}

		for (int i = 1; i < n; i ++) {
			for (int j = 1; j < m; j ++) {
				if (c[i][j]) tas[i].addEdge(courses[j], 0,Integer.MAX_VALUE / 2);
			}
		}

		Graph graph = new Graph(nodes, source, sink);
		return req - MaxFlow.maximizeFlow(graph);
	}
}