package DivideConquer.Weblab;

import org.junit.Test;

import static org.junit.Assert.*;

public class ithSmallestTest {

	@Test
	public void findIth() {
		int[] a = {5, 3, 4, 1, 2, 7, 4 ,8, 9, 0, 10, 4, 5, 8, 7, 2, 3};
		assertEquals(3, ithSmallest.findIth(a, 4));
	}

	@Test
	public void findIth1() {
		int[] a = {5, 3, 5, 1, 2, 7, 5 ,8, 9, 0, 10, 5, 5, 8, 7, 2, 3, 6, 4, 2, 8, 9, 1, 0};
		assertEquals(3, ithSmallest.findIth(a, 4));
	}
}