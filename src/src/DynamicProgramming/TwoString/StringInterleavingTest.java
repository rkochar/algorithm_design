package DynamicProgramming.TwoString;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringInterleavingTest {

	@Test
	public void isInterleave() {
		assertTrue(new StringInterleaving().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
	}

	@Test
	public void isInterleave1() {
		assertTrue(new StringInterleaving().isInterleave("aab", "axy", "aaxaby"));
	}

	@Test
	public void isInterleave2() {
		assertTrue(new StringInterleaving().isInterleave("", "", ""));
	}

	@Test
	public void isInterleave3() {
		assertFalse(new StringInterleaving().isInterleave("db", "b", "cbb"));
	}

	@Test
	public void isInterleave4() {
		assertFalse(new StringInterleaving().isInterleave("aabc", "abad", "aabadabc"));
	}
}