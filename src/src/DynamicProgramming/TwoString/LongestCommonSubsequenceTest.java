package DynamicProgramming.TwoString;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongestCommonSubsequenceTest {

	@Test
	public void longestCommonSubsequence() {
		String s1 = "abcde", s2 = "ace";
		assertEquals(3, new LongestCommonSubsequence().longestCommonSubsequence(s1, s2));
	}
}