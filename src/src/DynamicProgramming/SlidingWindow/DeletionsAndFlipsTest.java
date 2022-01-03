package DynamicProgramming.SlidingWindow;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeletionsAndFlipsTest {

	@Test
	public void longestSubarray() {
		int[] a = {1, 1, 0, 1};
		assertEquals(3, new DeletionsAndFlips().longestSubarrayOneDeletion(a));
	}

	@Test
	public void longestSubarray1() {
		int[] a = {0,1,1,1,0,1,1,0,1};
		assertEquals(5, new DeletionsAndFlips().longestSubarrayOneDeletion(a));
	}

	@Test
	public void longestSubarray2() {
		int[] a = {1, 1, 1};
		assertEquals(2, new DeletionsAndFlips().longestSubarrayOneDeletion(a));
	}

	@Test
	public void longestOnes() {
		int[] a = {1,1,1,0,0,0,1,1,1,1,0};
		assertEquals(6, new DeletionsAndFlips().longestOnesKflips(a, 2));
	}
}