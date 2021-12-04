package Greedy.string;

import java.util.Arrays;

public class SmallestStringForSum {

	/**
	 * https://leetcode.com/problems/smallest-string-with-a-given-numeric-value
	 *
	 * @param n - number of elements in string
	 * @param k - sum of characters in string
	 * @return smallest lexicographical string of given sum and integers
	 */
	public String getSmallestString(int n, int k) {
		char[] result = new char[n];
		Arrays.fill(result, 'a');
		k -= n;
		while(k > 0) {
			if(k > 25) {
				result[n - 1] = 'z';
				k -= 25;
			}
			else {
				result[n - 1] += k;
				break;
			}
			n--;
		}
		return new String(result);
	}
}
