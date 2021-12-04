package DivideConquer.Weblab;

import org.junit.Test;

import static org.junit.Assert.*;

public class LargestConsecutiveSumTest {

	@Test
	public void largestSum() {
		int[] input = new int[] { 3, -3, -2, 42, -11, 20, 4, 4} ;
		assertEquals(59, LargestConsecutiveSum.largestSum(input));
	}

	@Test
	public void testExampleA() {
		int[] input = new int[] { 2, -3, 2, 1 };
		assertEquals(3, LargestConsecutiveSum.largestSum(input));
	}

	@Test
	public void testExampleB() {
		int[] input = new int[] { 3, -3, -2, 42, -11, 2, 4, 4 };
		assertEquals(42, LargestConsecutiveSum.largestSum(input));
	}
}