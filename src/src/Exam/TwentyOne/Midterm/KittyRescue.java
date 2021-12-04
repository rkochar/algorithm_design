package Exam.TwentyOne.Midterm;

import java.util.*;

/**
 * WARNING: The spec tests are not necessarily equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined by a manual inspection of your implementation.
 */
class KittyRescue {

	/**
	 * Jump evaluation as given in the assignment description.
	 * There should be no need to modify this function!
	 */
	public static int evaluateClimb(int height1, int height2) {
		if (height2 <= height1) {
			return height1 - height2 + 1;
		} else {
			int diff = height2 - height1;
			return -(diff * diff);
		}
	}

	/**
	 * You should implement this method.
	 *
	 * @param n the number of cats that are lost in the woods
	 * @param h the height of the trees h_1 through h_n that the cats are in. Note you should only use entries h[1] up to and including h[n].
	 * @return the score of the best possible rescue mission.
	 */
	public static int findBestRescueMissionBruteForce(int n, int[] h) {
		int ans = Integer.MIN_VALUE, pointer = 0;
		// Check every climb with other climbs
		for (int i = 1; i <= n; i++) {
			for (int j = i; j < n; j++) {
				//find max
				int current = pointer + evaluateClimb(h[j], h[j + 1]);
				if (current > ans) ans = current;
				pointer = current;
			}
			pointer = 0;
		}
		return ans;
	}

	/**
	 * You should implement this method.
	 *
	 * @param n the number of cats that are lost in the woods
	 * @param h the height of the trees h_1 through h_n that the cats are in. Note you should only use entries h[1] up to and including h[n].
	 * @return the score of the best possible rescue mission.
	 */
	public static int findBestRescueMissionDivideAndConquer(int n, int[] h) {
		return FindBestTrainingDivideAndConquer(h, 1, n + 1);
	}

	public static int FindBestTrainingDivideAndConquer(int[] heights, int index_start, int index_end) {
		// base case of size two
		if (index_end - index_start == 2) {
			return Math.max(0, evaluateClimb(heights[index_start], heights[index_start + 1]));
		}
		// cases where the array is of length 1 or 0
		if (index_start + 1 >= index_end) {
			return 0;
		}
		// divide into two parts and solve independently
		int index_middle = (index_start + index_end) / 2;
		int best_left_half = FindBestTrainingDivideAndConquer(heights, index_start, index_middle);
		int best_right_half = FindBestTrainingDivideAndConquer(heights, index_middle, index_end);
		// now compute the best training option that intersects the left and right part
		// compute the best starting point from the left, assuming that index_middle is the end point
		int left_start_value = evaluateClimb(heights[index_middle - 1], heights[index_middle]);
		int best_left_start_value = left_start_value;
		for (int i = index_middle - 2; i >= index_start; i--) {
			left_start_value = left_start_value + evaluateClimb(heights[i], heights[i + 1]);
			if (left_start_value > best_left_start_value) {
				best_left_start_value = left_start_value;
			}
		}
		// compute the best end point from the right, assuming that index_middle-1 is the starting point
		int right_start_value = 0;
		int best_right_start_value = 0;
		for (int i = index_middle + 1; i < index_end; i++) {
			right_start_value = right_start_value + evaluateClimb(heights[i - 1], heights[i]);
			if (right_start_value > best_right_start_value) {
				best_right_start_value = right_start_value;
			}
		}
		int global_best = Math.max(best_left_half, best_right_half);
		global_best = Math.max(global_best, best_left_start_value + best_right_start_value);
		return global_best;
	}
}
