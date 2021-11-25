package greedy.string;

public class MinimumSwapEqualString {

	/**
	 * https://leetcode.com/problems/minimum-swaps-to-make-strings-equal
	 *
	 * @param s1 - string1
	 * @param s2 - string2
	 * @return number of minimum swaps
	 */
	public static int minimumSwap(String s1, String s2) {
		int l = s1.length(), x = 0, y = 0, answer = 0;
		for (int i = 0; i < l; i ++) {
			if (s1.charAt(i) == s2.charAt(i)) continue;
			else if (s1.charAt(i) == 'x') {
				if (x == 0) {
					answer ++;
					x ++;
				} else x --;
			} else {
				if (y == 0) {
					answer ++;
					y ++;
				} else y --;
			}
		}
		return answer;
	}
}
