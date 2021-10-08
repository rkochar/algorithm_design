package greedy;

import java.util.Arrays;
import java.util.List;

/**
 * For a row of positive numbers distribute minimum candies such that higher numbers get higher candies.
 * https://www.hackerrank.com/challenges/candies/problem
 *
 * If rating[i - 1] < rating[i] <= rating [i + 1], i is rising
 * If rating[i - 1] < rating[i] > rating [i + 1], i is peak
 * If rating[i - 1] >= rating[i] > rating [i + 1], i is falling
 * If rating[i - 1] >= rating[i] <= rating [i + 1], i is bottom
 * add inf to both sides to support first element of array
 * Note: rising is same as falling but other direction
 *
 *
 *
 * # populate 'valleys'
 * for i in xrange(1,n+1):
 *     if a[i-1] >= a[i] <= a[i+1]:
 *         candies[i] = 1
 *
 * # print the total number of candies
 * print sum(candies)
 */
public class MinimumCandies {

	public static int countMinCandies(int n, List<Integer> arr) {
		int[] candies = new int[n];
		Arrays.fill(candies, 1);

		for (int i = 0; i < candies.length;) {
			if (i > 0 && arr.get(i) > arr.get(i - 1) && candies[i] <= candies[i-1]) {
				candies[i] = candies[i-1] + 1;
				i--;
			}

			else

			if (i < candies.length - 1 && arr.get(i) > arr.get(i+1) && candies[i] <= candies[i+1]) {
				candies[i] = candies[i+1] + 1;
				if (i > 0) i--;
			}

			else i++;
		}

		int totalCandies = 0;
		for (int c: candies) {
			totalCandies += c;
		}

		return totalCandies;
	}

	public static long
	minimumCandies(int n, List<Integer> arr) {
		arr.add(Integer.MAX_VALUE);
		arr.add(0, Integer.MAX_VALUE);
		long[] candies = new long[n + 2];
		candies[0] = 0;
		candies[n + 1] = 0;

		for (int i = 1; i < n + 1; i ++) {
			// Rising
			if (arr.get(i - 1) < arr.get(i) && arr.get(i) <= arr.get(i + 1)) candies[i] = Long.max(1, candies[i - 1] + 1);
			// Falling
			else if(arr.get(i - 1) >= arr.get(i) && arr.get(i) > arr.get(i + 1)) candies[i] = Long.max(1, candies[i + 1] + 1);
			// Peak
			else if (arr.get(i - 1) < arr.get(i) && arr.get(i) > arr.get(i + 1)) candies[i] = 1;//Long.max(1, Long.max(candies[i - 1], candies[i + 1]) + 1);
			// Bottom
			else if (arr.get(i - 1) > arr.get(i) && arr.get(i) < arr.get(i + 1)) candies[i] = 1; // Long.max(1, Long.min(candies[i - 1], candies[i + 1]) - 1);
		}

		return Arrays.stream(candies).sum();
	}

}
