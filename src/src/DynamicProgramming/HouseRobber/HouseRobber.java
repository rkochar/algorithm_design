package DynamicProgramming.HouseRobber;


public class HouseRobber {

	/**
	 * Find largest possible sum of non-adjacent numbers.
	 *
	 * https://leetcode.com/problems/house-robber/
	 *
	 * @param nums list of numbers
	 * @return largest possible sum of non-adjacent numbers.
	 */
	public int rob(int[] nums) {
		int[] dp = new int[nums.length];
		if (nums.length <= 1) return nums[0];
		else if (nums.length == 2) return Integer.max(nums[0], nums[1]);

		dp[0] = nums[0];
		dp[1] = Integer.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length; i ++) dp[i] = Integer.max(dp[i - 1], dp[i - 2] + nums[i]);

		return dp[nums.length - 1];
	}

	/**
	 * Houses are arranged in a circle.
	 *
	 * https://leetcode.com/problems/house-robber-ii/
	 *
	 * @param nums
	 * @return
	 */
	public int circularRob(int[] nums) {
		return Integer.max(new HouseRobber().rob(nums), new HouseRobber().rob(nums));
	}

	/**
	 * Above but with constant space.
	 *
	 * https://leetcode.com/problems/delete-and-earn/
	 *
	 * @param nums
	 * @return
	 */
	public int deleteAndEarn(int[] nums) {
		int[] frequencies = new int[10001];
		for (int num : nums) {
			frequencies[num] += num;
		}

		int one = 0, two = 0;
		for (int i = 0; i < 10001; i ++) {
			int tmp = Integer.max(one + frequencies[i], two);
			one = two;
			two = tmp;
		}

		return Integer.max(one, two);
	}
}
