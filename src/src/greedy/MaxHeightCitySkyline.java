package greedy;

/**
 *  https://leetcode.com/problems/max-increase-to-keep-city-skyline/
 *  min(max(row_r), max(col_c)) - grid[i][j]
 */
public class MaxHeightCitySkyline {

	public int maxIncreaseKeepingSkyline(int[][] grid) {
		int answer = 0;
		int[] rowMax = new int[grid.length];
		int[] colMax = new int[grid.length];

		for (int i = 0; i < grid.length; i ++) {
			for (int j = 0; j < grid.length; j ++) {
				rowMax[i] = Math.max(grid[i][j], rowMax[i]);
				colMax[j] = Math.max(grid[i][j], colMax[j]);
			}
		}

		for (int i = 0; i < grid.length; i ++) {
			for (int j = 0; j < grid.length; j ++) {
				answer += Math.min(colMax[j], rowMax[i]) - grid[i][j];
			}
		}

		return answer;
	}
}
