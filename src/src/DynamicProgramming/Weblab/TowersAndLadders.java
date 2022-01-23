package DynamicProgramming.Weblab;

import java.util.Arrays;

public class TowersAndLadders {

	public static int towersAndLadders(int n, int m, int[][] graph) {
		int[][] dp = new int[n][m];
		{
			for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
			// Base case
			dp[0][0] = 0;
			// Iterate over the entire grid n*m times, since it can take that long for the result to propagate to the end.
			for (int k = 0; k < n * m; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						if (i == 0 && j == 0)
							continue;
						// Try all 4 cases: left, right, up and down.
						// Do an out-of-bounds check before indexing the array.
						// Check whether we found a shorter path to our current node, by taking the min of the current value
						// And the neighboring nodes. Keep in mind we might have to jump up from this node
						// and therefore we compare the graph values.
						dp[i][j] = j - 1 < 0 ? dp[i][j] : Integer.max(dp[i][j - 1], graph[i][j] - graph[i][j - 1]);
						dp[i][j] = i - 1 < 0 ? dp[i][j] : Integer.min(dp[i][j], Integer.max(dp[i - 1][j], graph[i][j] - graph[i - 1][j]));
						dp[i][j] = j + 1 >= m ? dp[i][j] : Integer.min(dp[i][j], Integer.max(dp[i][j + 1], graph[i][j] - graph[i][j + 1]));
						dp[i][j] = i + 1 >= n ? dp[i][j] : Integer.min(dp[i][j], Integer.max(dp[i + 1][j], graph[i][j] - graph[i + 1][j]));
					}
				}
			}
			return dp[n - 1][m - 1];
		}
	}
}
