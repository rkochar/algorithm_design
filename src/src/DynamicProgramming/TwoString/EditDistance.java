package DynamicProgramming.TwoString;

public class EditDistance {

	/**
	 * Convert word1 to word2 by replacing, inserting or deleting characters.
	 * Take 1 + min of [i][j - 1], [i - 1][j] and [i - 1][j - 1]. If character at i ==j, take [i - 1][j - 1].
	 *
	 * https://leetcode.com/problems/edit-distance
	 *
	 * @param word1 word1
	 * @param word2 word2
	 * @return minimum number of operations
	 */
	public int minDistance(String word1, String word2) {
		int l1 = word1.length(), l2 = word2.length();
		int[][] dp = new int[l1 + 1][l2 + 1];

		for (int i = 0; i <= l1; i ++)
			for (int j = 0; j <= l2; j ++)
				if (i == 0) dp[0][j] = j;
				else if (j == 0) dp[i][0] = i;
				else if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] = 1 + Integer.min(dp[i][j - 1], Integer.min(dp[i - 1][j], dp[i - 1][j - 1]));

		return dp[l1][l2];
	}
}
