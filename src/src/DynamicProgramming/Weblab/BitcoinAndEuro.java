package DynamicProgramming.Weblab;

public class BitcoinAndEuro {

	public static double optimalTrade(int t, double[] r) {
		double[][] dp = new double[r.length + 1][2];
		dp[0][0] = 0;
		dp[0][1] = 0.1;

		for (int i = 1; i <= t; i ++) {
			dp[i][0] = Double.max(dp[i - 1][1] * 0.95 * r[i], dp[i - 1][0]);
			dp[i][1] = Double.max((dp[i - 1][0] - 5) / r[i], dp[i - 1][1]);
		}

		return dp[t][0];
	}
}
