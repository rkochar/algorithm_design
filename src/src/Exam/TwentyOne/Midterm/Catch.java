package Exam.TwentyOne.Midterm;

import java.util.*;

public class Catch {

	int friend1;

	int friend2;

	double catchRate;

	public Catch(int friend1, int friend2, double catchRate) {
		this.friend1 = friend1;
		this.friend2 = friend2;
		this.catchRate = catchRate;
	}
}

class CatchAndThrow {

	/**
	 *  You should implement this method.
	 *  @param n the number of friends
	 *  @param catches the throw catch combinations that are available.
	 *  @return the minimal largest chance of dropping any of the balls.
	 */
	public static double minimalLargestDropChance(int n, Set<Catch> catches) {
		return solveProper(n, catches);
	}

	public static double solveProper(int n, Set<Catch> catches) {
		double result = 1;
		Map<Integer, Map<Integer, Double>> map = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			map.put(i, new HashMap<>());
		}
		for (Catch c : catches) {
			map.get(c.friend1).put(c.friend2, c.catchRate);
			map.get(c.friend2).put(c.friend1, c.catchRate);
		}
		PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingDouble((Tuple o) -> o.cost).reversed().thenComparingInt((Tuple o) -> o.id));
		pq.add(new Tuple(1, 1.0));
		HashMap<Integer, Double> probs = new HashMap<>();
		probs.put(1, 1.0);
		HashSet<Integer> visited = new HashSet<>();
		while (!pq.isEmpty()) {
			Tuple curNode = pq.poll();
			if (visited.contains(curNode)) {
				continue;
			}
			if (curNode.cost < result) {
				result = curNode.cost;
			}
			visited.add(curNode.id);
			if (visited.size() == n) {
				break;
			}
			for (int neigh : map.get(curNode.id).keySet()) {
				double prob = map.get(curNode.id).get(neigh);
				if (!visited.contains(neigh) && prob * curNode.cost >= probs.getOrDefault(neigh, 0.0)) {
					probs.put(neigh, prob * curNode.cost);
					pq.add(new Tuple(neigh, prob * curNode.cost));
				}
			}
		}
		if (visited.size() != n) {
			return 0;
		}
		return result;
	}

	static class Tuple {

		int id;

		double cost;

		Tuple(int id, double cost) {
			this.id = id;
			this.cost = cost;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Tuple tuple = (Tuple) o;
			return id == tuple.id;
		}
	}
}
