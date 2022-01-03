package DynamicProgramming;

import org.junit.Test;

import static org.junit.Assert.*;

public class WaysToMakeFairArrayTest {

	@Test
	public void waysToMakeFair() {
		int[] a = {2, 1, 6, 4};
		assertEquals(1, new WaysToMakeFairArray().waysToMakeFair(a));
	}
}