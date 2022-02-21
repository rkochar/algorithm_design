package NetworkFlow;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Baseball {
	/**
	 * Returns true if team x can still win the Cup.
	 */
	public static boolean solve(InputStream in) {
		Scanner sc = new Scanner(in);
		int m = sc.nextInt();
		// Process team x
		int teamX = sc.nextInt();
		int winsX = sc.nextInt();
		// To maximize the chance of winning, assume team x wins all remaining games
		winsX += sc.nextInt();
		// The other teams for these games do not matter, skip to the other teams
		sc.nextLine();
		sc.nextLine();
		ArrayList<Node> nodes = new ArrayList<>();
		Node source = new Node(0, 0);
		nodes.add(source);
		// Add a node for each team
		for (int x = 0; x < m; x++) {
			nodes.add(new Node(x + 1, 0));
		}
		Node sink = new Node(m + 1, 0);
		nodes.add(sink);
		// Process the remaining m-1 teams
		for (int x = 0; x < m - 1; x++) {
			int i = sc.nextInt();
			int w = sc.nextInt();
			int g = sc.nextInt();
			if (winsX < w)
				// We can be sure team x cannot win if another team has more wins than x can get
				return false;
			// Make sure this team does not have more wins than team x
			nodes.get(i).addEdge(sink, 0, winsX - w);
			// Find the number of games to play against each opponent
			HashMap<Integer, Integer> games = new HashMap<>();
			for (int y = 0; y < g; y++) {
				int t = sc.nextInt();
				// Avoid duplicates and remove matches against team x
				if (t < i && t != teamX) {
					int n = games.getOrDefault(t, 0);
					games.put(t, n + 1);
				}
			}
			// Adds a game node, edge from source and edges to teams
			for (Integer t : games.keySet()) {
				Node game = new Node(nodes.size(), 0);
				nodes.add(game);
				source.addEdge(game, 0, games.get(t));
				game.addEdge(nodes.get(i), 0, games.get(t));
				game.addEdge(nodes.get(t), 0, games.get(t));
			}
		}
		Graph g = new Graph(nodes, source, sink);
		MaxFlow.maximizeFlow(g);
		// One of these teams will beat team x when the game is played, therefore team x cannot win.
		for (Edge e : source.getEdges()) {
			if (e.getFlow() != e.getCapacity()) {
				return false;
			}
		}
		return true;
	}
}
