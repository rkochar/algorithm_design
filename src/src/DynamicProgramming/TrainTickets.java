package DynamicProgramming;

public class TrainTickets {

	/**
	 * Tickets are of 3 lengths, 1, 7 and 30 with different costs. Return min cost to travel on all days.
	 * List has unique numbers from 1 to 365.
	 *
	 * Make dp of size last day + 1. Make a decision min(dp[i - size of ticket] + cost of ticket).
	 *
	 * https://leetcode.com/problems/minimum-cost-for-tickets/solution/
	 *
	 * @param days
	 * @param costs
	 * @return
	 */
	public int mincostTickets(int[] days, int[] costs) {
		int[] dp = new int[days[days.length - 1] + 1];
		dp[0] = 0;
		int j = 0;

		for (int i = 1; i < dp.length; i ++) {
			if (days[j] == i) {
				dp[i] = Integer.min(dp[i - 1] + costs[0], Integer.min(dp[Integer.max(0, i - 7)] + costs[1], dp[Integer.max(0, i - 30)] + costs[2]));
				j ++;
			} else dp[i] = dp[i - 1];
		}

		return dp[dp.length - 1];
	}
}
