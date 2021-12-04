package Greedy;

import java.util.Arrays;

public class ChefSatisfaction {

	/**
	 * Given array of numbers (+, -), multiply with it's index to return max sum.
	 * Some numbers can be dropped.
	 *
	 * Cumulative sum from right side, then do it again. Take max the second time.
	 *
	 * https://leetcode.com/problems/reducing-dishes/
	 *
	 * @param satisfaction
	 * @return
	 */
	public static int maxSatisfaction(int[] satisfaction) {
		Arrays.sort(satisfaction);
		int [] cumulativeSum = new int [satisfaction.length];
		cumulativeSum[satisfaction.length - 1] = satisfaction[satisfaction.length - 1];
		int [] likeCoefficient = new int [satisfaction.length];
		likeCoefficient[satisfaction.length - 1] = satisfaction[satisfaction.length - 1]; // copy last element
		int max = Integer.MIN_VALUE;
		for(int i = satisfaction.length - 2; i >= 0; i --){
			cumulativeSum[i] = cumulativeSum[i+1] + satisfaction[i];
			likeCoefficient[i] = likeCoefficient[i + 1] + cumulativeSum[i];
			max = Math.max(max, likeCoefficient[i]);
		}

		return Math.max(0, max);
	}
}
