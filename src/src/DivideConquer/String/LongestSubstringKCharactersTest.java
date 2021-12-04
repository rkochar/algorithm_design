package DivideConquer.String;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongestSubstringKCharactersTest {

	@Test
	public void largestSubstring() {
		assertEquals(3, LongestSubstringKCharacters.longestSubstring("aaabb", 3));
	}

	@Test
	public void largestSubstring1() {
		assertEquals(3, LongestSubstringKCharacters.longestSubstring("baaabcb", 3));
	}
}