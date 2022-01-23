package DynamicProgramming.TwoString;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringInterleaving {

	/**
	 * Check if the third string is an interleave of the first two.
	 * Kind off like wildcard matching. It seems that swapping l1 and l2 in declaration helps.
	 *
	 * https://leetcode.com/problems/interleaving-string/
	 *
	 * @param s1 string to interleave
	 * @param s2 string to interleave
	 * @param s3 string to check
	 * @return
	 */
	public boolean isInterleave(String s1, String s2, String s3) {
		int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
		if (l1 + l2 != l3) return false;
		else if (s1.equals("")) return s3.equals(s2);
		else if (s2.equals("")) return s3.equals(s1);

		boolean[][] dp = new boolean[l1 + 1][l2 + 1];
		dp[0][0] = true;
		for (int i = 1; i <= l1; i ++) dp[i][0] = s3.charAt(i - 1) == s1.charAt(i - 1) && dp[0][i - 1];
		for (int i = 1; i <= l2; i ++) dp[0][i] = s3.charAt(i - 1) == s2.charAt(i - 1) && dp[i - 1][0];

		for (int i = 1; i <= l1; i ++)
			for (int j = 1; j <= l2; j ++)
				dp[i][j] = (dp[i-1][j] && s2.charAt(i-1)==s3.charAt(i+j-1)) || (dp[i][j-1] &&(s1.charAt(j-1) == s3.charAt(i + j - 1)));
			
		return dp[l1][l2];
	}
}
