package greedy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumWageKWorkers {

	/**
	 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/solution/
	 *
	 * @param quality
	 * @param wage
	 * @param k
	 * @return
	 */
	public static double minCostToHireWorkers(int[] quality, int[] wage, int k) {
		Worker[] workers = new Worker[quality.length];
		for (int i = 0; i < quality.length; i ++) workers[i] = new Worker(quality[i], wage[i]);
		Arrays.sort(workers);

		double answer = Double.MAX_VALUE;
		int sum = 0;
		PriorityQueue<Integer> pq = new PriorityQueue();

		for (Worker worker: workers) {
			pq.offer(-worker.quality);
			sum += worker.quality;
			int size = pq.size();
			if (size > k) sum += pq.poll();
			if (size == k) answer = Math.min(answer, sum * worker.ratio());
		}

		return answer;
	}
}

class Worker implements Comparable<Worker> {
	int quality;
	int wage;

	public Worker(int quality, int wage) {
		this.quality = quality;
		this.wage = wage;
	}

	public double ratio() {
		return (double) this.wage / this.quality;
	}


	@Override
	public int compareTo(Worker worker) {
		return Double.compare(this.ratio(), worker.ratio());
	}
}