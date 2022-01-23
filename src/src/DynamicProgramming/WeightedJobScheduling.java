package DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

public class WeightedJobScheduling {

	/**
	 * Given a job schedule of starting, ending times and profits, return max attainable profit.
	 *
	 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
	 *
	 * @param startTime starting times
	 * @param endTime ending times
	 * @param profit profits
	 * @return max profit
	 */
	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int[][] jobs = new int[profit.length][3];
		for (int i = 0; i < startTime.length; i ++) jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
		Arrays.sort(jobs, Comparator.comparingInt(x -> x[1]));

		int[] dp = new int[startTime.length + 1];
		int max = 0;

		for (int i = 1; i <= jobs.length; i++) {
			dp[i] = Integer.max(jobs[i - 1][2], dp[i - 1]);
			for (int j = 1; j < i; j ++)
				if (jobs[j - 1][1] <= jobs[i - 1][0]) dp[i] = Integer.max(dp[i], dp[j] + jobs[i - 1][2]);
			max = Integer.max(max, dp[i]);
		}

		return max;
	}
}
