package DynamicProgramming.Grid;

import java.util.Arrays;
import java.util.List;

public class MinimumPath {

	/**
	 * All costs must be incurred so just add them straight away.
	 *
	 * Find a path A to B with minimum cost path.
	 * Can move in any direction. Assume B is never left or above A.
	 * Make spawn 0, 0 with offset.
	 *
	 * https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/
	 *
	 * @param startPos - spawn [i, j]
	 * @param homePos - destination [i, j]
	 * @param rowCosts - cost of moving in that row
	 * @param colCosts - cost of moving in that column
	 * @return
	 */
	public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
		int rows = homePos[0] - startPos[0] + 1, cols = homePos[1] - startPos[1] + 1;
		int[][] dp = new int[rows][cols];
		dp[0][0] = 0;
		for (int i = 1; i < rows; i ++) dp[i][0] = dp[i - 1][0] + rowCosts[i + startPos[0]];
		for (int i = 1; i < cols; i ++) dp[0][i] = dp[0][i - 1] + colCosts[i + startPos[1]];

		for (int i = startPos[0] + 1; i <= homePos[0]; i ++) {
			for (int j = startPos[1] + 1; j <= homePos[1]; j ++) {
				dp[i - startPos[0]][j - startPos[1]] = Integer.min(rowCosts[i] + dp[i - 1 - startPos[0]][j - startPos[1]], colCosts[j] + dp[i - startPos[0]][j - 1 - startPos[1]]);
			}
		}

		return dp[rows - 1][cols - 1];
	}

	/**
	 * Go from top row to bottom row with minimum sum path. In place memoization.
	 * Can go diagonal or straight down. Start at row 1 and add min of 3 possible rows at 0. Recurse for i on i - 1th row.
	 *
	 * https://leetcode.com/problems/minimum-falling-path-sum/submissions/
	 *
	 * @param matrix
	 * @return
	 */
	public int minFallingPathSum(int[][] matrix) {
		for (int i = 1; i < matrix.length; i ++)
			for (int j = 0; j < matrix[0].length; j ++)
				matrix[i][j] += Math.min(matrix[i - 1][j], Math.min(matrix[i - 1][Math.max(0, j - 1)], matrix[i - 1][Math.min(matrix[0].length - 1, j + 1)]));

		return Arrays.stream(matrix[matrix[0].length - 1]).min().getAsInt();
	}

	/**
	 * Given a triangle of numbers, find the minimum path sum from top to bottom.
	 *
	 * https://leetcode.com/problems/triangle/
	 *
	 * Java is not allowing deletion and addition of elements in triangle for some reason. This inplace algo is similar to one above.
	 *
	 * @param triangle 2d matrix of elements arranged in triangle
	 * @return minimum sum to travel from top to bottom row.
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
		int l = triangle.size();

		for (int i = l - 2; i > 0; i --) {
			List<Integer> tri = triangle.get(i), next = triangle.get(i + 1);
			for (int j = 0; j < i; j ++) {
				tri.forEach(System.out::println);
				int t = tri.remove(j);
				tri.add(t + Integer.min(next.get(j), next.get(j + 1)), j);
			}
		}

		return triangle.get(0).get(0);
	}
}
