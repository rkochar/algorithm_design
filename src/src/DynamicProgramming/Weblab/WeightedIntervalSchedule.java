package DynamicProgramming.Weblab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class WeightedIntervalSchedule {

	public static int findMaxCost(int n, int[] s, int[] f, int[] v, int[] p) {
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i ++)
			dp[i] = Integer.max((p[i] == -1 ? 0 : dp[p[i]]) + v[i], dp[i - 1]);
		return dp[n];
	}

	public static int[] findSchedule(int n, int[] s, int[] f, int[] v) {
		WeightedIntervalSchedulePair[] jobs = new WeightedIntervalSchedulePair[n + 1];
		for (int i = 1; i <= n; i ++) jobs[i] = new WeightedIntervalSchedulePair(s[i], f[i], v[i]);
		Arrays.sort(jobs, Comparator.comparingInt(x -> x.end));

		int t = 0;
		for (int i = 1; i <= n; i ++) {
			if (jobs[t].end > jobs[i].start) v[i] = -1;
			else {
				for (; t < n; t ++) {
					if (jobs[t].start > jobs[i].end) break;
					v[i] = t;
				}
			}
		}

		return v;
	}
}

class WeightedIntervalSchedulePair {
	int start;
	int end;
	int cost;

	public WeightedIntervalSchedulePair(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
}
