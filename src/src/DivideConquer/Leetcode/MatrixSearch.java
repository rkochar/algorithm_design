package DivideConquer.Leetcode;

public class MatrixSearch {

	public static boolean searchMatrix(int[][] matrix, int target) {
		return searchMatrix(matrix, 0, matrix[0].length - 1, 0, matrix.length - 1, target);
	}

	private static boolean searchMatrix(int[][] matrix, int left, int right, int high, int low, int target) {
		if (right == left && high == low) return target == matrix[left][high];
		if (right - left == 1 && high - low == 1) {
			return matrix[left][high] == target || matrix[right][high] == target || matrix[left][low] == target || matrix[right][low] == target;
		}

		int midRow = (right + left) / 2;
		int midCol = (high + low) / 2;
		int check = matrix[midRow][midCol] - target;

		if (check == 0) return true;
		else if (check < 0) {
			// Not top left box
			return searchMatrix(matrix, midRow, right, high, midCol, target)
				|| searchMatrix(matrix, left, right, midCol, low, target);
		} else {
			// Not bottom right box
			return searchMatrix(matrix, left, right, high, midCol, target)
				|| searchMatrix(matrix, left, midRow, midCol, low, target);
		}

//		if (matrix[midRow][midCol] < target && matrix[right][midCol] > target) left = midRow;
//		else right = midRow;
//		if (matrix[midRow][midCol] < target && matrix[midRow][low] > target) high = midCol;
//		else low = midCol;
//
//		return searchMatrix(matrix, left, right, high, low, target);
	}
}
