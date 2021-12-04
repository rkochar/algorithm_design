package Greedy;

import java.util.Arrays;

public class MinimumDifferenceWithThreeMoves {

	/**
	 * Sort array, now the 3 moves can be made in 4 combinations, 0, 1, 2 moves at the front.
	 * 3 moves at the front is same as 3 moves at the back so 4th case is same as 1st.
	 * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
	 *
	 * @param nums - numbers
	 * @return minimum difference of extremes.
	 */
	public int minDifference(int[] nums) {
		if (nums.length <= 4) return 0;
		Arrays.sort(nums);
		int min1 = Math.min(nums[nums.length-1] - nums[3], nums[nums.length-4] - nums[0]);
		int min2 = Math.min(nums[nums.length-2] - nums[2], nums[nums.length-3] - nums[1]);
		int min3 = Math.min(nums[nums.length-3] - nums[1], nums[nums.length-2] - nums[2]);
		return Math.min(Math.min(min1, min2), min3);
	}
}
