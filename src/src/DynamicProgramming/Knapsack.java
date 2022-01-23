package DynamicProgramming;

public class Knapsack {

	/**
	 * Find size of largest subset that contains atmost m 1s and n 0s.
	 * Brute force and memoize results. Check for each new word, can it be taken and what can be removed.
	 *
	 * https://leetcode.com/problems/ones-and-zeroes/
	 *
	 * @param strings strings
	 * @param m number of 1s
	 * @param n number of 0s
	 * @return size of largest subset
	 */
	public int findMaxForm(String[] strings, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];

		for (String string: strings) {
			int one = 0, zero = 0;
			for (char c: string.toCharArray())
				if (c == '1') one ++;
				else zero ++;

			for (int i = m; i >= zero; i --)
				for (int j = n; j >= 0; j --)
					if (zero <= i && one <= j)
						dp[i][j] = Integer.max(dp[i][j], dp[i - zero][j - one] + 1);
		}

		return dp[m][n];
	}

	/**
	 * Find minimum number of coins needed to achieve target using infinite number of coins.
	 *
	 * https://leetcode.com/problems/coin-change/
	 *
	 * @param coins denominations of coins
	 * @param target to achieve
	 * @return minimum number of coins
	 */
	public int coinChange(int[] coins, int target) {
		if (target <= 0) return 0;
		int[] dp = new int[target + 1];

		for (int i = 1; i <= target; i ++) {
			dp[i] = Integer.MAX_VALUE;
			for (int coin: coins) if (i >= coin && dp[i - coin] != -1) dp[i] = Integer.min(dp[i], dp[i - coin]);
			if (dp[i] == Integer.MAX_VALUE) dp[i] = -1;
			else dp[i] = dp[i] + 1;
		}

		return dp[target];
	}

	/**
	 * Given a rod of length, cut it into pieces of different lengths to maximize profit.
	 *
	 * @param prices price of each length
	 * @param length of rod
	 * @return maximum price of rod
	 */
	public int cutRod(int[] prices, int length) {
		int[] dp = new int[length + 1];

		for (int i = 1; i <= length; i ++) {
			dp[i] = Integer.MIN_VALUE;
			for (int j = 1; j < prices.length; j++)
				if (i >= j) dp[i] = Integer.max(dp[i - j] + prices[j - 1], dp[i - 1]);
		}
		return dp[length];
	}
}
