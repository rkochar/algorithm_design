package DynamicProgramming.TwoString;

public class DeleteOperationsToEqualize {

	/**
	 * Given two strings s1 and s2 find minimum number of steps to make them equal by removing characters.
	 * l1 + l2 - 2 * longest common subsequence. Alternatively, this the opposite of longest common subsequence.
	 *
	 * https://leetcode.com/problems/delete-operation-for-two-strings/
	 *
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int minDistanceLCS(String text1, String text2) {
		int l1 = text1.length(), l2 = text2.length();
		int[][] dp = new int[l1 + 1][l2 + 1];

		for (int i = 0; i <= l2; i ++) dp[0][i] = 0;
		for (int i = 0; i <= l1; i ++) dp[i][0] = 0;

		for (int i = 1; i <= l1; i ++)
			for (int j = 1; j <= l2; j ++)
				dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? 1 + dp[i - 1][j - 1] : Integer.max(dp[i - 1][j], dp[i][j - 1]);

		return l1 + l2 - 2 * dp[l1][l2];
	}

	public int minDistance(String text1, String text2) {
		int l1 = text1.length(), l2 = text2.length();
		int[][] dp = new int[l1 + 1][l2 + 1];

		for (int i = 0; i <= l1; i ++) dp[i][0] = i;
		for (int i = 0; i <= l2; i ++) dp[0][i] = i;

		for (int i = 1; i <= l1; i ++)
			for (int j = 1; j <= l2; j ++)
				dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? dp[i - 1][j - 1] : 1 + Integer.min(dp[i - 1][j], dp[i][j - 1]);

		return dp[l1][l2];
	}

	/**
	 * Attempted 1d memoization but there is a copy paste back in, very weird.
	 *
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int minDistance1d(String text1, String text2) {
		int l1 = text1.length(), l2 = text2.length();
		int[] dp = new int[l2 + 1];

		for (int i = 0; i <= l1; i ++) {
			int[] tmp = new int[l2 + 1];
			for (int j = 0; j <= l2; j++) {
				if (i == 0) tmp[j] = j;
				else if (j == 0) tmp[j] = i;
				else if (text1.charAt(i - 1) == text2.charAt(j - 1)) tmp[j] = tmp[j - 1];
				else tmp[j] = 1 + Integer.min(tmp[j], tmp[j - 1]);
			}
			dp = tmp;
		}

		return dp[l2];
	}
}
