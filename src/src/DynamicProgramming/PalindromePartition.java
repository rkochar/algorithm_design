package DynamicProgramming;

public class PalindromePartition {

	/**
	 * Return minimum number of partitions needed to make all substrings palindrome.
	 * Iterate i from 1 and j from 0 to i. Update min when a palindrome is found by reducing it.
	 *
	 * https://leetcode.com/problems/palindrome-partitioning-ii/
	 *
	 * @param string
	 * @return
	 */
	public int palindromePartition(String string) {
		int l = string.length();
		int[] cut = new int[string.length()];
		boolean[][] isPalindrome = new boolean[l][l];

		for (int i = 1; i < string.length(); i++) {
			int min = i;
			for (int j = 0; j <= i; j++) {
				if (string.charAt(i) == string.charAt(j) && (i <= j + 1 || isPalindrome[i - 1][j + 1])) {
					isPalindrome[i][j] = true;
					min = Math.min(min, j == 0 ? 0 : 1 + cut[j - 1]);
				}
			}
			cut[i] = min;
		}

		return cut[l - 1];
	}
}
