package DynamicProgramming.SlidingWindow;

public class MinimumSumSubarrayGreaterThanK {

	public int minSubArrayLen(int target, int[] nums) {
		int slow = 0, fast = 0, answer = 0, tmp = Integer.MAX_VALUE;
		for (; fast < nums.length; fast ++) {
			tmp += nums[fast];
			while (tmp >= target) {
				answer = Integer.min(answer, fast - slow + 1);
				tmp -= nums[slow ++];
			}
		}

		return answer;
	}
}
