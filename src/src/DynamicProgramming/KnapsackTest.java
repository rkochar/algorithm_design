package DynamicProgramming;

import org.junit.Test;

import static org.junit.Assert.*;

public class KnapsackTest {

	@Test
	public void coinChange() {
		int[] a = {1, 2, 5};
		assertEquals(3, new Knapsack().coinChange(a, 11));
	}

	@Test
	public void cutRud() {
		int[] a = {1, 2, 3, 4};
		int[] b = {2, 5, 7, 8};
		assertEquals(12, new Knapsack().cutRod(b, 5));
	}
}