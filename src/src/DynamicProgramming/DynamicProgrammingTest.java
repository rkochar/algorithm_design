package DynamicProgramming;

import org.junit.Test;

import static org.junit.Assert.*;

public class DynamicProgrammingTest {

	@Test
	public void minSubArrayLen() {
		int[] a = {2,3,1,2,4,3};
		assertEquals(2, new DynamicProgramming().minSubArrayLen(7, a));
	}

//	@Test
//	public void tribonacci1() {
//		int[] b = {1,100,1,1,1,100,1,1,100,};
//		assertEquals(6, new DynamicProgramming().getMaximumGenerated(b));
//	}
}