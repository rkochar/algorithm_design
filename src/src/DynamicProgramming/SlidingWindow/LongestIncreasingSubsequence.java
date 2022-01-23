package DynamicProgramming.SlidingWindow;

import DynamicProgramming.DynamicProgramming;

public class LongestIncreasingSubsequence {


	/**
	 * Problem asks for number of longest increasing subsequences but we give length of longest subsequence.
	 *
	 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
	 *
	 * @param nums
	 * @return
	 */
	public int findNumberOfLIS(int[] nums) {
		int[] dp = new int[nums.length];

		for (int i = 0; i < nums.length; i ++) {
			dp[i] = 1;
			for (int j = 0; j < i; j ++) {
				if (nums[j] < nums[i]) dp[i] = Integer.max(dp[j] + 1, dp[i]);
			}
		}

		return dp[nums.length - 1];
	}
}
