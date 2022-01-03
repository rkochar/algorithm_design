package DynamicProgramming;

public class PartitionArrayMaxSum {

	/**
	 * Make subarrays of length k and replace every element in subarray with largest element in that subarray.
	 * Return max sum of array.
	 *
	 * Check for each element in k, the max * k + dp[i - k].
	 *
	 * https://leetcode.com/problems/partition-array-for-maximum-sum/
	 *
	 * @param arr - array
	 * @param k - size of subarray
	 * @return max sum of array
	 */
	public int maxSumAfterPartitioning(int[] arr, int k) {
		int N = arr.length;
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; ++i) {
			int curMax = 0, best = 0;
			for (int j = 1; j <= k && i - j >= 0; ++j) {
				curMax = Math.max(curMax, arr[i - j]);
				best = Math.max(best, dp[i - j] + curMax * j);
			}
			dp[i] = best;
		}
		return dp[N];
	}
}
