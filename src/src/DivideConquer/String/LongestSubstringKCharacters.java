package DivideConquer.String;

import java.net.PortUnreachableException;
import java.util.Arrays;

public class LongestSubstringKCharacters {

	public static int longestSubstring(String s, int k) {
		char[] chars = s.toCharArray();
		return largestSubstring(chars, 0, chars.length - 1, k);
	}

	public static int largestSubstring(char[] chars, int left, int right, int k) {
		if (left > right) return 0;

		int[] frequency = new int[26];
		for (int i = left; i <= right; i ++) frequency[chars[i] - 'a'] ++;

		int mid = -1;
		for (int i = left; i <= right; i ++) {
			if (frequency[chars[i] - 'a'] < k) {
				mid = i;
				break;
			}
		}

		if (mid == -1) return right - left + 1;

		int nextMid = mid;
		for (int i = mid + 1; i <= right; i ++) {
			if (frequency[chars[i] - 'a'] >= k) break;
			nextMid = i;
		}

		return Math.max(largestSubstring(chars, left, mid - 1, k), largestSubstring(chars, nextMid + 1, right, k));
	}
}
