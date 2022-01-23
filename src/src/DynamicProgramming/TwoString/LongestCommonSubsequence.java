package DynamicProgramming.TwoString;

import DynamicProgramming.DynamicProgramming;

public class LongestCommonSubsequence {

	/**
	 * Given two strings, return length of largest common subsequence.
	 * Check if i and j characters are equal. If yes, add 1 to diagonal else max of [i - 1] and [j - 1].
	 *
	 * https://leetcode.com/problems/longest-common-subsequence/
	 *
	 * @param text1 first string
	 * @param text2 second string
	 * @return length of largest common subsequence.
	 */
	public int longestCommonSubsequence(String text1, String text2) {
		int l1 = text1.length(), l2 = text2.length();
		int[][] dp = new int[l1 + 1][l2 + 1];

		for (int i = 1; i <= l1; i ++)
			for (int j = 1; j <= l2; j ++)
				dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? 1 + dp[i - 1][j - 1] : Integer.max(dp[i - 1][j], dp[i][j - 1]);

		return dp[l1][l2];
	}
}
