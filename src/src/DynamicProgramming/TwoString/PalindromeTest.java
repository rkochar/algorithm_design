package DynamicProgramming.TwoString;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeTest {

	@Test
	public void longestPalindrome() {
		assertEquals("racecar", new Palindrome().longestPalindrome("racecar"));
	}

	@Test
	public void longestPalindrome1() {
		assertEquals("bb", new Palindrome().longestPalindrome("cbbd"));
	}

	@Test
	public void longestPalindrome2() {
		assertEquals("a", new Palindrome().longestPalindrome("a"));
	}

	@Test
	public void countPalindrome() {
		assertEquals(6, new Palindrome().countSubstrings("fdsklf"));
	}

	@Test
	public void countPalindrome1() {
		assertEquals(6, new Palindrome().countSubstrings("aaa"));
	}
}