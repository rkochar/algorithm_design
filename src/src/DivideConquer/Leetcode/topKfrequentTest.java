package DivideConquer.Leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class topKfrequentTest {

	@Test
	public void topKFrequent() {
		int[] a = {1,1,1,2,2,3};
		int[] e = {1, 2};
		assertArrayEquals(e, topKfrequent.topKFrequent(a, 2));
	}

	@Test
	public void topKFrequent1() {
		int[] a = {1, 1};
		int[] e = {1};
		assertArrayEquals(e, topKfrequent.topKFrequent(a, 1));
	}

	@Test
	public void topKFrequent2() {
		int[] a = {1};
		int[] e = {1};
		assertArrayEquals(e, topKfrequent.topKFrequent(a, 1));
	}

	@Test
	public void topKFrequent3() {
		int[] a = {4,1,-1,2,-1,2,3};
		int[] e = {-1, 2};
		assertArrayEquals(e, topKfrequent.topKFrequent(a, 2));
	}

	@Test
	public void topKFrequent4() {
		int[] a = {5,2,5,3,5,3,1,1,3};
		int[] e = {3, 5};
		assertArrayEquals(e, topKfrequent.topKFrequent(a, 2));
	}


}