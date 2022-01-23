package DynamicProgramming.Weblab;

import java.util.LinkedList;
import java.util.List;

public class Knapsack {

	public static int knapsack(int n, int maxWeight, int[] weights, int[] values) {
		int[][] dp = new int[n + 1][maxWeight + 1];
		for (int i = 0; i <= n; i ++) dp[i][0] = weights[i] <= i ? values[i] : 0;

		for (int i = 1; i <= n; i ++)
			for (int j = 1; j <= maxWeight; j ++)
				if (weights[i] > j) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Integer.max(dp[i][j - 1], dp[i - 1][j - weights[i]] + values[i]);

		return dp[n][maxWeight];
	}

	public static List<Integer> recoverBackpack(int maxWeight, int n, int[] weights, int[] values, int[][] dp) {
		LinkedList<Integer> answer = new LinkedList<>();

		for (int i = n, j = maxWeight, value = dp[n][maxWeight]; i > 0; i --)
			if (weights[i] <= j && dp[i][j] == values[i] + dp[i - 1][j - weights[i]]) {
				answer.addFirst(i);
				j -= weights[i];
			}

		return answer;
	}
}
