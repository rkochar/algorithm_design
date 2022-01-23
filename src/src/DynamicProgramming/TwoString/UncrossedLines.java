package DynamicProgramming.TwoString;

public class UncrossedLines {

	/**
	 * Given two arrays, draw lines between the same characters such that each number has atmost 1 line.
	 * No two lines intersect. LCS. Way faster than the two edits (in comments).
	 *
	 * https://leetcode.com/problems/uncrossed-lines/
	 *
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int maxUncrossedLines(int[] nums1, int[] nums2) {
		int[][] dp = new int[nums1.length + 1][nums2.length + 1];

		for (int i = 1; i <= nums1.length; i ++)
			for (int j = 1; j <= nums2.length; j++)
				if (nums1[i - 1] == nums2[j - 1]) dp[i][j] = 1 + dp[i - 1][j - 1];
				else dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
//				if (i == 0) dp[0][j] = 0;
//				else if (j == 0) dp[j][0] = 0;
//				else if (nums1[i - 1] == nums2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
//				else dp[i][j] = Integer.max(dp[i - 1][j], Integer.max(dp[i - 1][j - 1], dp[i][j - 1]));

		return dp[nums1.length][nums2.length];
	}
}
