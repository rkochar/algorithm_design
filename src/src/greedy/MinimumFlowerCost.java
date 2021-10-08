package greedy;

import java.util.*;

/**
 * Price of each flower increases as customer buys more flowers.
 */

public class MinimumFlowerCost {

	public static long minimumFlowerCost(List<Integer> prices, int n, int numberOfCustomers) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(n);
		for (int i = 0; i < numberOfCustomers; i ++) queue.add(0);
		prices.sort(Comparator.comparingInt(x -> -x));

		long answer = 0;
		for (int price: prices) {
			if (queue.peek() != null) {
				int previous = queue.poll();
				answer += (long) (previous + 1) * price;
				queue.add(previous + 1);
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), numberOfCustomers = sc.nextInt();
		List<Integer> prices = new ArrayList<>(n);
		for (int i = 0; i < n; i ++) prices.add(sc.nextInt());
		System.out.println(minimumFlowerCost(prices, n, numberOfCustomers));
	}
}
