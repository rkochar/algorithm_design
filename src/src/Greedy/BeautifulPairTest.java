package Greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeautifulPairTest {

//	@org.junit.jupiter.api.Test
//	void leetOne() {
//		int[] a = {10,9,8,7,6,5,4,3,2,1,1,0};
//		List<Integer> ints = Arrays.asList(9, 7, 8);
//		assertEquals(2, BeautifulPair.jump(a));
//	}

	@org.junit.jupiter.api.Test
	void leetTwo() {
		int[] a = {2, 5};
		int[] b = {1000000000};
		int[] c = {1, 2, 3, 4};
		int[] d = {1, 3, 2, 2, 2};
		int[] e = {0,3,3,0,3,1,3,0,2,2,0};
		int[] f = {1};

		int[][] aa = {{7,16},{2,3},{3,12},{3,14},{10,19},{10,16},{6,8},{6,11},{3,13},{6,16}};
		int[][] bb = {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
		int[][] cc = {{1, 2}, {2, 3}, {3, 4}};
		int[][] dd = {{1, 10}, {2, 2}, {2, 2}, {2, 2}, {2, 2}};
		int[][] ee = {{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}};

		char[] ch1 = {'A', 'A', 'A', 'B', 'B', 'B'};
		char[] ch2 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		List<Integer> ints = Arrays.asList(9, 7, 8);

//		assertEquals(21, BeautifulPair.maxProfit(b, 1000000000));
//		assertEquals(14, BeautifulPair.maxProfit(a, 4));
//		assertEquals("5", BeautifulPair.maxProfit(c));
//		assertEquals(4, BeautifulPair.findUnsortedSubarray(d));
//		assertEquals(2, BeautifulPair.minSideJumps(b));


//		assertEquals(16, BeautifulPair.leastInterval(ch2, 2));
	}

	/**
	 *
	 [[21,22,21,22,21,30]]
	 [21,22,21,22,21,22,21,30]

	 *
	 */

//	@org.junit.jupiter.api.Test
//	void leetThree() {
//		int[] a = {2, 7, 5};
//		int[] b = {1, 1, 2, 2, 3};
//		int[][] aa = {{2, 5, 3}, {1, 8, 4}, {1, 7, 5}};
//		int[][] bb = {{5,0},{7,0},{5,2},{6,1},{4,4},{7,1}};
//		List<Integer> ints = Arrays.asList(9, 7, 8);
//		assertEquals(true, BeautifulPair.mergeTriplets(aa, a));
//	}



	@org.junit.jupiter.api.Test
	void beautifulPairs() {
		List<Integer> a = new LinkedList<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		List<Integer> b = new LinkedList<Integer>();
		b.add(1);
		b.add(2);
		b.add(3);
		b.add(3);

		assertEquals(4, BeautifulPair.beautifulPairs(a, b));
	}

	@org.junit.jupiter.api.Test
	void beautifulPairsAnother() {
		List<Integer> a = new LinkedList<Integer>();
		// 3,5,5,7,8,   11
		//   5,5,7,8,10,11
		a.add(3);
		a.add(5);
		a.add(7);
		a.add(11);
		a.add(5);
		a.add(8);
		List<Integer> b = new LinkedList<Integer>();
		b.add(5);
		b.add(7);
		b.add(11);
		b.add(10);
		b.add(5);
		b.add(8);

		assertEquals(6, BeautifulPair.beautifulPairs(a, b));
	}
}