package DivideConquer.Leetcode;

import java.util.Arrays;

public class kClosestPointsOrigin {

	public static int[][] kClosest(int[][] points, int k) {
		if (k == 0 || points == null) return null;
		if (points.length == k) return points;
		return quickSelect(points, k);
	}

	public static int[][] quickSelect(int[][] points, int k) {
		int left = 0, right = points.length - 1;
		int pivot = 0;
		while (pivot != k) {
			pivot = partition(points, left, right);
			if (pivot < k) left = pivot;
			else right = pivot - 1;
		}

		return Arrays.copyOf(points, k);
	}

	private static int partition(int[][] points, int left, int right) {
		int mid = (left + right) / 2;
		int dist = distance(points[mid]);

		while (left < right) {
			if (distance(points[left]) >= dist) {
				swap(points, left, right);
				right --;
			} else left ++;
		}

		if (distance(points[left]) < dist) left ++;
		return left;
	}

	public static int distance(int[] p) {
		return (p[0] * p[0]) + (p[1] * p[1]);
	}

	public static void swap(int[][] points, int i, int j) {
		int[] tmp = points[i];
		points[i] = points[j];
		points[j] = tmp;
	}
}
