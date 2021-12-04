package Greedy;

public class MaxSubarrayCircular {

	public int maxSubarraySumCircular(int[] nums) {
		int max = Integer.MIN_VALUE, maxTmp = 0, min = Integer.MAX_VALUE, minTmp = 0, sum = 0;
		int maxElement = Integer.MIN_VALUE;
		for (int num : nums) {
			maxTmp = Integer.max(maxTmp + num, num);
			max = Integer.max(maxTmp, max);
			minTmp = Integer.min(minTmp + num, num);
			min = Integer.min(min, minTmp);
			sum += num;
			maxElement = Integer.max(maxElement, num);
		}

		return max > 0 ? Integer.max(max, sum - min) : maxElement;
	}
}
