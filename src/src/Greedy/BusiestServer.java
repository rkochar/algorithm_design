package Greedy;

import java.util.*;

public class BusiestServer {



	/**
	 * Solved with brute force.
	 * https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/
	 *
	 * Correct solution: https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/discuss/1432118/Simple-Java-Solution-using-Array%3A-Runtime%3A-O(n)-and-Space-O(k)
	 * @param k
	 * @param arrival
	 * @param load
	 * @return
	 */
	public static List<Integer> busiestServers(int k, int[] arrival, int[] load) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> -x));
		HashMap<Integer, Server> servers = new HashMap<>();
		for (int i = 0; i < k; i ++) servers.put(i, new Server(i, 0, 0));

		int max = 0;
		for (int i = 0; i < arrival.length; i ++) {
			int replacei = arrival[i];
			for (int j = 0; j < k; j ++) {
				Server server = servers.get((i + j) % k);
				if (server.busyTill <= replacei) {
					server.busyTill = replacei + load[i];
					server.jobs++;
					max = Math.max(max, server.jobs);
					break;
				}
			}
		}

		List<Integer> answer = new ArrayList<>();
		for (Server server: servers.values()) {
			if (server.jobs == max) answer.add(server.id);
		}
		return answer;
	}
}

class Server implements Comparable<Server> {
	int id;
	int jobs;
	int busyTill;

	public Server(int id, int jobs, int busyTill) {
		this.jobs = jobs;
		this.busyTill = busyTill;
		this.id = id;
	}

	@Override
	public int compareTo(Server server) {
		return Integer.compare(this.busyTill, server.busyTill);
	}
}