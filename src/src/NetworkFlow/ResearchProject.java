package NetworkFlow;

import java.util.ArrayList;

public class ResearchProject {

	/**
	 *   You should implement the method below. Note that you can use the graph structure below.
	 *   @param n The number of students.
	 *   @param m The number of supervisors.
	 *   @param student_availability_mon is an array of size (n+1) that is true iff student i is available on Monday. You should ignore student_availability_mon[0].
	 *   @param student_availability_tues is an array of size (n+1) that is true iff student i is available on Tuesday. You should ignore student_availability_tues[0].
	 *   @param supervisor_availability_mon is an array of size (m+1) that is true iff supervisor j is available on Monday. You should ignore supervisor_availability_mon[0].
	 *   @param supervisor_availability_tues is an array of size (m+1) that is true iff supervisor j is available on Tuesday. You should ignore supervisor_availability_tues[0].
	 *   @param selected is an array of size (n+1)x(m+1) that is true iff student i selected supervisor j. You should use entries selected[1][1] through selected[n][m].
	 *   @return true iff there is a valid allocation of students over supervisors.
	 */
	public static boolean areThereGroups(int n, int m, boolean[] student_availability_mon, boolean[] student_availability_tues, boolean[] supervisor_availability_mon, boolean[] supervisor_availability_tues, boolean[][] selected) {
		ArrayList<Node> nodes = new ArrayList<>();
		Node source = new Node(-1, -n);
		Node sink = new Node(-2, n);
		nodes.add(source);
		nodes.add(sink);

		Node[] students = new Node[n + 1];
		Node[] students_monday = new Node[n + 1];
		Node[] students_tuesday = new Node[n + 1];
		for (int i = 1; i <= n; i ++) {
			students[i] = new Node(i, 0);
			students_monday[i] = new Node(i, 0);
			students_tuesday[i] = new Node(i, 0);
			nodes.add(students[i]);
			nodes.add(students_monday[i]);
			nodes.add(students_tuesday[i]);
		}

		Node[] supervisors = new Node[n + 1];
		Node[] supervisors_monday1 = new Node[m + 1];
		Node[] supervisors_monday2 = new Node[m + 1];
		Node[] supervisors_tuesday1 = new Node[m + 1];
		Node[] supervisors_tuesday2 = new Node[m + 1];
		for (int i = 1; i <= m; i ++) {
			supervisors[i] = new Node(i, 0);
			supervisors_monday1[i] = new Node(i, 0);
			supervisors_monday2[i] = new Node(i, 0);
			supervisors_tuesday1[i] = new Node(i, 0);
			supervisors_tuesday2[i] = new Node(i, 0);
			nodes.add(supervisors[i]);
			nodes.add(supervisors_monday1[i]);
			nodes.add(supervisors_monday2[i]);
			nodes.add(supervisors_tuesday1[i]);
			nodes.add(supervisors_tuesday2[i]);
		}

		for (int i = 1; i <= n; i ++) {
			source.addEdge(students[i], 0, 1);
			if (student_availability_mon[i]) students[i].addEdge(students_monday[i], 0, 1);
			if (student_availability_tues[i]) students[i].addEdge(students_tuesday[i],0, 1 );
		}

		for (int i = 1; i <= m; i ++) {
			supervisors[i].addEdge(sink, 3, 12);
			if (supervisor_availability_mon[i]) {
				supervisors_monday1[i].addEdge(supervisors[i], 0, 5);
				supervisors_monday2[i].addEdge(supervisors[i], 0, 5);
			}
			if (supervisor_availability_tues[i]) {
				supervisors_tuesday1[i].addEdge(supervisors[i], 0, 5);
				supervisors_tuesday2[i].addEdge(supervisors[i], 0, 5);
			}
		}

		for (int i = 1; i <= n; i ++) {
			for (int j = 1; j <= m; j ++) {
				if (selected[i][j]) {
					students_monday[i].addEdge(supervisors_monday1[j], 0, 1);
					students_monday[i].addEdge(supervisors_monday2[j], 0, 1);
					students_tuesday[i].addEdge(supervisors_tuesday1[j], 0, 1);
					students_tuesday[i].addEdge(supervisors_tuesday2[j], 0, 1);
				}
			}
		}

		sink.addEdge(source, 0, Integer.MAX_VALUE/2);
		Graph graph = new Graph(nodes);//, source, sink);
		MaxFlow.maximizeFlow(graph);
		return graph.hasCirculation();
	}
}