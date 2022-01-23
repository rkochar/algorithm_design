package DynamicProgramming;

import java.util.List;

public class WordBreak {

	/**
	 * For a string, check if it can be divided such at that there is at least one division where every substring in in the dict.
	 * Check if substring(0, j) and substring(j, i) is true for all combinations.
	 *
	 * https://leetcode.com/problems/word-break/
	 *
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public boolean wordBreak(String s, List<String> wordDict) {
		int l = s.length();
		boolean[] dp = new boolean[l + 1];
		dp[0] = true;

		for (int i = 1; i <= l; i ++) {
			for (int j = 0; j < i; j ++) {
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}

		return dp[l];
	}
}
