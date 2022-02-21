package NetworkFlow;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImageSegmentation {

	public int solve(InputStream in) {
		Scanner sc = new Scanner(in);
		int numPixels = sc.nextInt();
		int numEdges = sc.nextInt();
		Node source = new Node(-1, 0);
		Node sink = new Node(-2, 0);
		List<Node> nodes = new ArrayList<>();
		nodes.add(source);
		nodes.add(sink);
		for (int i = 0; i < numPixels; i ++) nodes.add(new Node(i + 1, 0));
		for (int i = 1; i <= numPixels; i ++) {
			int id = sc.nextInt();
			int forward = sc.nextInt();
			int back = sc.nextInt();
			nodes.add(new Node(id, 0));
			source.addEdge(nodes.get(id), 0, forward);
			nodes.get(id).addEdge(sink, 0, back);
		}

		for (int i = 0; i < numEdges; i ++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			nodes.get(from).addEdge(nodes.get(to), 0, 10);
			nodes.get(to).addEdge(nodes.get(from), 0, 10);
		}

		Graph graph = new Graph(nodes, source, sink);
		MaxFlow.maximizeFlow(graph);
		int sum = 0;
		for (Edge edge: source.getEdges()) {
			sum += edge.getFlow();
		}
		return sum;
	}
}
