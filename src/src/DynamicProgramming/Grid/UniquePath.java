package DynamicProgramming.Grid;

public class UniquePath {

	/**
	 * Find number of unique paths to go from top left to bottom right cell of grid.
	 *
	 * https://leetcode.com/problems/unique-paths/submissions/
	 *
	 * @param m - number of rows
	 * @param n - number of columns
	 * @return number of unique paths
	 */
	public int uniquePathSimpleInplace(int m, int n) {
		// See uniquePathLinearMem for best solution.
		int[][] mem = new int[m][n];

		for (int i = 0; i < m; i ++) {
			for (int j = 0; j < n; j ++) {
				if (i == 0 || j == 0) mem[i][j] = 1;
				else {
					mem[i][j] = mem[i - 1][j] + mem[i][j - 1];
				}
			}
		}

		return mem[m - 1][n - 1];
	}

	/**
	 * See uniquePathSimpleInplace above.
	 *
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePathLinearMem(int m, int n) {
		int[] dp = new int[n];
		dp[0] = 1;

		for (int i = 0; i < m; i ++ ) {
			for (int j = 0; j < n; j ++)
				if (j > 0) dp[j] += dp[j - 1];
		}
		return dp[n - 1];
	}

	/**
	 * Find unique paths from top left to bottom right cell of grid where 1 represents obstacles and 0 free space.
	 *
	 * https://leetcode.com/problems/unique-paths-ii/solution/
	 *
	 * @param obstacleGrid - grid to travel. 1 is obstacle and 0 free space.
	 * @return number of unique paths
	 */
	public int uniquePathsWithObstaclesInplace(int[][] obstacleGrid) {
		// See uniquePathsWithObstaclesLinear for second best solution
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) return 0;

		obstacleGrid[0][0] = 1;
		for (int i = 1; i < m; i ++)
			if (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) obstacleGrid[i][0] = 1;
			else obstacleGrid[i][0] = 0;
		for (int j = 1; j < n; j ++)
			if (obstacleGrid[0][j] == 0 && obstacleGrid[0][j - 1] == 1) obstacleGrid[0][j] = 1;
			else obstacleGrid[0][j] = 0;

		for (int i = 1; i < m; i ++) {
			for (int j = 1; j < n; j ++) {
				if (obstacleGrid[i][j] == 1) obstacleGrid[i][j] = 0;
				else obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
			}
		}

		for (int[] i : obstacleGrid){
			for (int j: i) System.out.print(j + " ");
			System.out.println();
		}

		return obstacleGrid[m - 1][n - 1];
	}

	public int uniquePathsWithObstaclesLinear(int[][] obstacleGrid) {
		int n = obstacleGrid[0].length;
		int[] dp = new int[n];
		dp[0] = 1;

		for (int[] row: obstacleGrid) {
			for (int j = 0; j < n; j ++) // Starting j at 1 fails on [[1]]
				if (row[j] == 1) dp[j] = 0;
				else if (j > 0) dp[j] += dp[j - 1];
		}

		return dp[n - 1];
	}
}
