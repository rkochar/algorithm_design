package Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumWageKWorkers {

	/**
	 * We want k workers, not k best workers. Requirement is that everybody is well paid.
	 * Trick to find that one guy whose ratio can be used to guarantee other workers are well paid.
	 * If ratio X is min, anybody with ratio <= X can also be hired.
	 *
	 * So sort by wage / quality because everybody should be well paid by per quality unit.
	 * Make a pq and add -quality to it. Iterate and get rid of largest (smallest) qualities to retain
	 * k smallest of them (minimum wage).
	 * Minimum wage is just sum of retained workers * ratio of the lowest. This guarantees everybody is well paid.
	 *
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
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (Worker worker: workers) {
			pq.offer(-worker.quality);
			sum += worker.quality;
			if (pq.size() > k) sum += pq.poll();
			if (pq.size() == k) answer = Math.min(answer, sum * worker.ratio());
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