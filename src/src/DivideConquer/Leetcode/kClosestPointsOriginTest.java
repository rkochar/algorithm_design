package DivideConquer.Leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class kClosestPointsOriginTest {

//	@Test
//	public void kClosest() {
//		int[][] dd = {{1, 10}, {2, 2}, {2, 2}, {2, 2}, {2, 2}};
//		int[][] answer = {{2, 2}, {2, 2}, {2, 2}};
//		assertArrayEquals(answer, kClosestPointsOrigin.kClosest(dd, 3));
//	}

	@Test
	public void kClosest2() {
		int[][] ee = {{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}};
		assertArrayEquals(null, kClosestPointsOrigin.kClosest(ee, 4));
	}

	@Test
	public void kClosest3() {
		int[][] aa = {{7,16},{2,3},{3,12},{3,14},{10,19},{10,16},{6,8},{6,11},{3,13},{6,16}};
		assertArrayEquals(null, kClosestPointsOrigin.kClosest(aa, 3));
	}

	@Test
	public void kClosest4() {
		int[][] bb = {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
		assertArrayEquals(null, kClosestPointsOrigin.kClosest(bb, 5));
	}
}