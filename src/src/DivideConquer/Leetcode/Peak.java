package DivideConquer.Leetcode;

public class Peak {

	public static int[] findPeakGrid(int[][] mat) {
		if (mat == null || mat.length == 1) return null;
		 return findPeakGrid(mat, (mat[0].length - 1)/2);
	}

	public static int[] findPeakGrid(int[][] mat, int column) {
		int max = Integer.MIN_VALUE, maxRow = 0;
		for (int i = 0; i < mat.length; i++) {
			if (mat[i][column] > max) {
				max = mat[i][column];
				maxRow = i;
			}
		}

		int check = check(mat, maxRow, column);
		if (check == 0) return new int[] {maxRow, column};
		else return findPeakGrid(mat, column + check);
	}


	public static int check(int[][] mat, int i, int j) {
		if (j == 0 || mat[i][j] > mat[i][j - 1]) {
			if (j == mat[0].length - 1 || mat[i][j] > mat[i][j + 1])
				return 0;
			else return 1;
		} else return -1;
	}
}
