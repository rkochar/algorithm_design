package DynamicProgramming;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeightedJobSchedulingTest {

	@Test
	public void jobScheduling() {
		int[] a = {1, 1, 1}, b = {2, 3, 4}, c = {5, 6, 4};
		assertEquals(6, new WeightedJobScheduling().jobScheduling(a, b, c));
	}

	@Test
	public void jobScheduling1() {
		int[] a = {1, 2, 3, 3}, b = {3, 4, 5, 6}, c = {50, 10, 40, 70};
		assertEquals(120, new WeightedJobScheduling().jobScheduling(a, b, c));
	}
}