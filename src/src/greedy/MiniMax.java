package greedy;

import java.util.Comparator;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-minimax/problem
 *
 * Find the smallest difference (quadratic complexity) between two arrays and smallest number
 * resulting in largest of these difference in other array.
 */

public class MiniMax {

	public static int sherlockAndMinimax(List<Integer> arr, int p, int q) {
		int n = arr.size();
		int maxDifference = Integer.MIN_VALUE;
		int answer = 0;
		int min_ind = 0, maxAns = -1, min = Integer.MAX_VALUE;
		arr.sort(Comparator.comparingInt(x -> x));


		// Find smallest distance to p
		for (int i = 0; i < n; i ++) {
			if (Math.abs(arr.get(i) - p) < min) {
				min = Math.abs(arr.get(i) - p);
				min_ind = p;
			}
		}

		if (maxDifference < min) {
			maxAns = min;
			maxDifference = min;
		}
		min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i ++) {
			if (Math.abs(arr.get(i) - q) < min) {
				min = Math.abs(arr.get(i) - q);
			}
		}

		if (maxDifference < min) {
			maxAns = min;
			maxDifference = min;
		}

		for (int i = 1; i < n; i ++) {
			int mid = (arr.get(i) + arr.get(i - 1)) / 2;
			min = Integer.MAX_VALUE;
			if (mid > p && mid < q) {
				if (Math.abs(arr.get(i) - mid) < min) {
					min = Math.abs(arr.get(i) - mid);
					min_ind = mid;
				}
				if (Math.abs(arr.get(i - 1) - mid) < min) {
					min = Math.abs(arr.get(i - 1) - mid);
					min_ind = mid;
				}
				if (maxAns < min) {
					maxAns = min;
					maxDifference = min_ind;
				}
			}
		}
		return maxDifference;
	}
}

		/**
		 *         int min = Integer.MAX_VALUE;
		 *         int min_ind = 0;
		 *         int max_ans = -1;
		 *         int max_ind = -1;
		 *         // start
		 *         for (int i = 0; i < arr.length; i++) {
		 *             if (Math.abs(arr[i] - p) < min) {
		 *                 min = Math.abs(arr[i] - p);
		 *                 min_ind = p;
		 *             }
		 *         }
		 *         if (max_ans < min) {
		 *             max_ans = min;
		 *             max_ind = min_ind;
		 *         }
		 *         min = Integer.MAX_VALUE;
		 *         // end
		 *         for (int i = 0; i < arr.length; i++) {
		 *             if (Math.abs(arr[i] - q) < min) {
		 *                 min = Math.abs(arr[i] - q);
		 *                 min_ind = q;
		 *             }
		 *         }
		 *         if (max_ans < min) {
		 *             max_ans = min;
		 *             max_ind = min_ind;
		 *         }
		 *
		 *         // mid - optimal M
		 *         for (int i = 1; i < arr.length; i++) {
		 *             int mid = (arr[i] + arr[i - 1]) / 2;
		 *             min = Integer.MAX_VALUE;
		 *             if (mid > p && mid < q) {
		 *                 if (Math.abs(arr[i] - mid) < min) {
		 *                     min = Math.abs(arr[i] - mid);
		 *                     min_ind = mid;
		 *                 }
		 *                 if (Math.abs(arr[i - 1] - mid) < min) {
		 *                     min = Math.abs(arr[i - 1] - mid);
		 *                     min_ind = mid;
		 *                 }
		 *                 if (max_ans < min) {
		 *                     max_ans = min;
		 *                     max_ind = min_ind;
		 *                 }
		 *             }
		 *         }
		 *         return max_ind;
		 */

//		int a = Math.abs(min - p);
//		int b = Math.abs(min - q);
//		int c = Math.abs(max - p);
//		int d = Math.abs(max - q);
//
//		int f = Math.min(a, b);
//		int g = Math.min(c, d);
//		return f >= g ? q : p;

//		if (max <= p || ()) return q;
//		if (min >= q || ()) return p;


//		for (int i = p; i <= q; i ++) {
//			for (int a: arr) {
//				if (Math.abs(a - i) > maxDifference) {
//					maxDifference = Math.abs(a - i);
//					answer = i;
//				}
//			}
//		}
//		return answer;

