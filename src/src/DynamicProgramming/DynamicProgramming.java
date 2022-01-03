package DynamicProgramming;

import java.util.*;

public class DynamicProgramming {

	public int minSubArrayLen(int target, int[] nums) {
		int slow = 0, fast = 0, answer = Integer.MAX_VALUE, tmp = 0;
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
