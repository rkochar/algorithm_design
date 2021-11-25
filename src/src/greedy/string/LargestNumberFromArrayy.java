package greedy.string;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LargestNumberFromArrayy {

	/**
	 * Return largest number as string using all numbers in array.
	 * Sort by s1 + s2 in descending order.
	 *
	 * @param nums
	 * @return
	 */
	public static String largestNumber(int[] nums) {
		PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String s, String t1) {
				return (t1 + s).compareTo(s + t1);
			}
		});
		for (int n: nums) pq.add(Integer.toString(n));
		StringBuilder answer = new StringBuilder();
		while (!pq.isEmpty()) {
			answer.append(pq.poll());
		}
		return answer.indexOf("0") == 0 ? "0" : answer.toString();
	}
}
