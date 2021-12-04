package Greedy;

import java.util.PriorityQueue;

public class Ipo {

	/**
	 * Since capital needed is not asked, assume that there is always sufficient capital.
	 * Index of profits and capitals needed does not change so add index to minheap and check that
	 * capital at that index is sufficient at that point of time.
	 * Heaps are sorted in a really fancy way. That sort is key and probably why index can be used.
	 *
	 * https://leetcode.com/problems/ipo/
	 *
	 * @param k - number of projects to choose
	 * @param w - initial capital
	 * @param profits - of each project
	 * @param capital - of each project
	 * @return - maximize total capital
	 */
	public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		PriorityQueue<Integer> maxq = new PriorityQueue<>((a, b) -> profits[b] - profits[a]);
		PriorityQueue<Integer> minq = new PriorityQueue<>((a, b) -> capital[a] - capital[b]);

		for (int i = 0; i < capital.length; i++) minq.add(i);

		while(k-- > 0) {
			while (minq.size() > 0 && capital[minq.peek()] <= w) maxq.add(minq.poll());
			if(maxq.size() != 0) w += profits[maxq.poll()];
		}
		return w;

	}
}
