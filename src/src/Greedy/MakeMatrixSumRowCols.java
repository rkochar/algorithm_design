package Greedy;

/**
 * https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/
 * Place smallest and subtract from sums. 0 goes everywhere else.
 */
public class MakeMatrixSumRowCols {

	public static int[][] restoreMatrix(int[] rowSum, int[] colSum) {
		int[][] result = new int[rowSum.length][colSum.length];

		for (int i = 0; i < rowSum.length; i++) {
			for (int j = 0; j < colSum.length; j++) {
				result[i][j] = Math.min(rowSum[i], colSum[j]);
				rowSum[i] -= result[i][j];
				colSum[j] -= result[i][j];
			}
		}
		return result;
	}
}
