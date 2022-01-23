package DynamicProgramming.Weblab;

public class FishSalesman {

	/**
	 * @param n the number of days
	 * @param P the profits that can be made on day 1 through n on location P are stored in P[1] through P[n].
	 * @param Q the profits that can be made on day 1 through n on location Q are stored in Q[1] through Q[n].
	 * @return the maximum obtainable profit.
	 */
	public static int fishSalesman(int n, int[] P, int[] Q) {
		int[][] dp = new int[n + 1][2];
		dp[1][0] = P[1];
		dp[1][1] = Q[1];

		for (int i = 2; i <= n; i ++) {
			dp[i][0] = Integer.max(dp[i - 1][0] + P[i], dp[i - 1][1]);
			dp[i][1] = Integer.max(dp[i - 1][1] + Q[i], dp[i - 1][0]);
		}

		return Integer.max(dp[n][0], dp[n][1]);
	}
}
