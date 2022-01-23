package DynamicProgramming.Weblab;

public class SequenceAlignment {

	public static int sequenceAlignment(String firstString, String secondString) {
		int l1 = firstString.length(), l2 = secondString.length();
		int[][] dp = new int[l1 + 1][l2 + 1];

		for (int i = 1; i <= l2; i ++) dp[0][i] = i;

		for (int i = 1; i <= l1; i ++) {
			dp[i][0] = i;
			for (int j = 1; j <= l2; j ++) {
				if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] = 1 + Integer.min(dp[i - 1][j - 1], Integer.min(dp[i - 1][j], dp[i][j - 1]));
			}
		}

		return dp[l1][l2];
	}
}
