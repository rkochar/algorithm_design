package DynamicProgramming.TwoString;

public class Palindrome {

	/**
	 * Find largest palindromic subsequence.
	 * Fill top triangle of dp. If i,j are same, take diagonal + 2 else max of [i + 1][j] and [i][j - 1].
	 *
	 * https://leetcode.com/problems/longest-palindromic-subsequence/
	 *
	 * @param s string s
	 * @return length of largest palindrome.
	 */
	public int longestPalindromeSubsequence(String s) {
		int l = s.length();
		int[][] dp = new int[l][l];

		for (int i = l - 1; i >= 0; i --) {
			dp[i][i] = 1;
			for (int j = i + 1; j < l; j++)
				if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1] + 2;
				else dp[i][j] = Integer.max(dp[i + 1][j], dp[i][j - 1]);
		}

		return dp[0][l - 1];
	}

	/**
	 * Find largest palindrome substring in a string.
	 *
	 * https://leetcode.com/problems/longest-palindromic-substring/
	 *
	 * @param s string
	 * @return largest palindrome substring.
	 */
	public String longestPalindrome(String s) {
		int l = s.length();
		String ans = Character.toString(s.charAt(0));

		boolean[][] dp = new boolean[l][l];
		for (int i = l - 1; i >= 0; i --)
			for (int j = i + 1; j < l; j ++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
				if (dp[i][j] && (ans == "" || j - i + 1 > ans.length())) ans = s.substring(i, j + 1);
			}

		return ans;
	}

	/**
	 * Find number of palindromes in a string. Make boolean array and place true. Adding same character side to a
	 * palindrome is also a palindrome.
	 *
	 * https://leetcode.com/problems/palindromic-substrings/
	 *
	 * @param s string
	 * @return number of palindromes in string
	 */
	public int countSubstrings(String s) {
		int l = s.length(), ans = 0;
		boolean[][] dp = new boolean[l][l];

		for (int i = l - 1; i >= 0; i --) {
			dp[i][i] = true;
			ans ++;
			for (int j = i + 1; j < l; j ++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] || j - i + 1 < 3);
				if (dp[i][j]) ans ++;
			}
		}

		return ans;
	}
}
