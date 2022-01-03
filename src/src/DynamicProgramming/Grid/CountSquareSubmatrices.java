package DynamicProgramming.Grid;

import DynamicProgramming.DynamicProgramming;

import java.util.Collections;

public class CountSquareSubmatrices {

	/**
	 * Find area of largest square of matrix of 0 and 1.
	 * dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
	 * Square can be extended by size 1 only if the three directions, top-left, top and left are also part of a square.
	 *
	 */
	public int countSquares(int[][] matrix) {
		int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
		int answer = 0;

		for (int i = 0; i <= matrix.length; i ++) {
			for (int j = 0; j <= matrix[0].length; j ++) {
				if (matrix[i][j] == 0 || i == 0 || j == 0) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = 1 + Integer.min(dp[i - 1][j - 1], Integer.min(dp[i][j - 1], dp[i - 1][j]));
					answer = Integer.max(answer, dp[i][j]);
				}
			}
		}

		return answer * answer;
	}

	/**
	 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/submissions/
	 *
	 * @param matrix
	 * @return
	 */
	public int countNumberOfSquares(int[][] matrix) {
		int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
		int answer = 0;

		for (int i = 0; i <= matrix.length; i ++) {
			for (int j = 0; j <= matrix[0].length; j ++) {
				if (matrix[i][j] == 0 || i == 0 || j == 0) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = 1 + Integer.min(dp[i - 1][j - 1], Integer.min(dp[i][j - 1], dp[i - 1][j]));
				}
			}
		}

		for (int[] ints : dp) {
			for (int anInt : ints) {
				answer += anInt;
			}
		}

		return answer;
	}
}
