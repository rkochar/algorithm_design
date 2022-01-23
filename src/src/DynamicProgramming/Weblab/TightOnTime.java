package DynamicProgramming.Weblab;

public class TightOnTime {

	/**
	 * Given grade from hours spent on each assignment, find max grade.
	 *
	 * @param n - the number of assignments
	 * @param h - the number of hours you can spend
	 * @param f - the function in the form of a (n + 1) x (h + 1) matrix.
	 *          Index 0 of the number of assignments should be ignored.
	 *          Index 0 of the number of hours spend is the minimum grade for this assignment.
	 * @return the max grade achievable
	 */
	public static int tightOnTime(int n, int h, int[][] f) {
		int[][] dp = new int[n + 1][h + 1];

		for (int i = 1; i <= n; i ++) {
			for (int j = 0; j <= h; j ++) {
				if (j == 0) dp[i][0] = f[i][0] + dp[i - 1][0];
				else {
					int max = Integer.MIN_VALUE;
					for (int k = 0; k <= j; k ++)
						max = Integer.max(max,dp[i - 1][j - k] + f[i][k]);
					dp[i][j] = max;
				}
			}
		}

		return dp[n][h];
	}
}
