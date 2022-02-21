package NetworkFlow;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ProjectSelection {

	public boolean solve(InputStream in) {
		Scanner sc = new Scanner(in);
		int n = sc.nextInt(); // Number of members
		int m = sc.nextInt(); // Number of jobs
		List<Node> nodes = new ArrayList<>();
		Node source = new Node(-1, 0);
		Node sink = new Node(-2, 0);
		nodes.add(source);
		nodes.add(sink);
		HashMap<String, Integer> skills = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			sc.next();
			Node node = new Node(i, 0);
			nodes.add(node);
			source.addEdge(node, 0, sc.nextInt());
			int requiredSkills = sc.nextInt();
			for (int j = 0; j < requiredSkills; j++) {
				String s = sc.next();
				int mapValue = skills.getOrDefault(s, -1);
				if (mapValue == -1) {
					mapValue = n + 1 + j;
					nodes.add(new Node(mapValue, 0));
					skills.put(s, n + 1 + j);
					nodes.get(n + 1 + j).addEdge(sink, 0,Integer.MAX_VALUE);
				}
				node.addEdge(nodes.get(mapValue), 0,Integer.MAX_VALUE);
			}
		}

		for (int i = 0; i < m; i++) {
			sc.next();
			int hours = sc.nextInt();
			int numSkills = sc.nextInt();
			// How the fuck do you add jobs data to the graoh??????????????????????????????????
		}

		return false;
	}
}
