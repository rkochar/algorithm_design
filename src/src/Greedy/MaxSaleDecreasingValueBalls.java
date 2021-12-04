package Greedy;

import java.util.Arrays;

public class MaxSaleDecreasingValueBalls {

	/**
	 * Balls cost the amount at that time. So 3 balls is 3*4/2.
	 * Use math instead of pq.
	 * https://leetcode.com/problems/sell-diminishing-valued-colored-balls/
	 *
	 * @param inventory - number of balls of each type
	 * @param orders - number of balls wanted
	 * @return price
	 */
	public static int maxProfitCorrect(int[] inventory, int orders) {
		int M = 1000000007;
		if (orders == 0) return 0;
		Arrays.sort(inventory);
		int i = inventory.length-1, cnt = 1;
		long ans = 0;
		while (orders > 0) {
			int currPrice = inventory[i], nextPrice = --i < 0 ? 0 : inventory[i];
			int targetPrice = (currPrice - nextPrice) * cnt < orders ? nextPrice : currPrice - orders / cnt;
			// don have to calculate if the prices are the same, just skip to the next after increasing count
			if (targetPrice != currPrice) {
				ans += getSum(targetPrice, currPrice) * cnt;
				orders -= (currPrice - targetPrice) * cnt;
			}
			// if target price are higher than next price, just add left orders to result
			if (targetPrice != nextPrice) {
				ans += (long) orders * targetPrice % M;
				orders = 0;
			}
			if (ans >= M) ans %= M;
			cnt++;
		}
		return (int) ans;
	}

	public static int maxProfit(int[] inventory, int orders) {
		Arrays.sort(inventory);
		int answer = 0;
		for (int i = inventory.length - 1; i > 0; i --) {
			if (orders >= inventory[i] - inventory[0]) {
				answer += (inventory[i] * (inventory[i] + 1)) / 2 - (inventory[0] * (inventory[0] + 1)) / 2;
				orders = orders - inventory[i] + inventory[0];
				inventory[i] = inventory[0];
			} else {
				answer += inventory[i] * (inventory[i] + 1) / 2 - (inventory[i] - orders) * (inventory[i] - orders + 1) / 2 ;
				orders = 0;
			}
			if (orders == 0) return answer % (1000000007);
		}

		int inv = inventory[0];
		if (orders >= inventory.length) {
			int num = orders / inventory.length;
			orders = orders % inventory.length;
			int v = num * (inv * (inv + 1) / 2 - (inv - num) * (inv - num + 1) / 2);
			answer += v;
			inv -= num;
		}

		if (orders != 0) answer += orders * inv;

//		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
//			@Override
//			public int compare(Integer integer, Integer t1) {
//				return t1 - integer;
//			}
//		});
//		for (int i: inventory) pq.add(i);
//		while (orders > 0 && !pq.isEmpty()) {
//			int ball = pq.poll();
//			answer += ball;
//			orders --;
//			if (ball > 1) pq.offer(ball - 1);
//		}

		return answer % (1000000007);
	}


	private static long getSum(int from, int to) {      // total sum in range of (from, to] = [from+1 ~ to]
		int diff = to - from, cnt = diff/2;
		long total = (long) (from + to + 1) * cnt;
		return (diff & 1) == 0 ? total : total + from + cnt + 1;
	}
}
