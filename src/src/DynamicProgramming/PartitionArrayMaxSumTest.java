package DynamicProgramming;

import org.junit.Test;

import static org.junit.Assert.*;

public class PartitionArrayMaxSumTest {

	@Test
	public void maxSumAfterPartitioning() {
		int[] a = {1,15,7,9,2,5,10};
		assertEquals(84, new PartitionArrayMaxSum().maxSumAfterPartitioning(a, 3));
	}

	@Test
	public void maxSumAfterPartitioning1() {
		int[] a = {1, 15, 7, 9, 9, 9, 10, 2, 2, 2};
		assertEquals(118, new PartitionArrayMaxSum().maxSumAfterPartitioning(a, 4));
	}

	@Test
	public void maxSumAfterPartitioning2() {
		int[] a = {10, 2, 2, 2};
		assertEquals(40, new PartitionArrayMaxSum().maxSumAfterPartitioning(a, 4));
	}

	@Test
	public void maxSumAfterPartitioning3() {
		int[] a = {2, 3, 10};
		assertEquals(30, new PartitionArrayMaxSum().maxSumAfterPartitioning(a, 3));
	}

	@Test
	public void maxSumAfterPartitioning4() {
		int[] a = {2, 3, 10, 1, 1};
		assertEquals(36, new PartitionArrayMaxSum().maxSumAfterPartitioning(a, 3));
	}
}