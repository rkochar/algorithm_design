package NetworkFlow;

import org.junit.Assert;
import org.junit.Test;


public class TADistributionTest {
	@Test(timeout = 100)
	public void exampleTwoOfEach() {
		int n = 2;
		int m = 2;
		int[] available = { 0, 75, 75 };
		int[] need = { 0, 100, 50 };
		boolean[][] match = new boolean[n + 1][m + 1];
		match[1][1] = true;
		match[1][2] = true;
		match[2][1] = true;
		match[2][2] = true;
		/*
		 * This test models the situation where:
		 * Course 1 requires 100 TA hours and course 2 requires 50
		 * Both TAs have 75 hours available.
		 * Both TAs can work both courses.
		 * So we are not short any hours.
		 */
		Assert.assertEquals(0, TADistribution.shortageOfTAs(n, available, m, need, match));
	}

	@Test(timeout = 100)
	public void exampleShortage() {
		int n = 2;
		int m = 2;
		int[] available = { 0, 250, 25 };
		int[] need = { 0, 100, 50 };
		boolean[][] match = new boolean[n + 1][m + 1];
		match[1][1] = true;
		match[2][1] = true;
		match[2][2] = true;
		/*
		 * This test models the situation where:
		 * Course 1 requires 100 TA hours and course 2 requires 50
		 * TA 1 can work on course 1 for 250 hours.
		 * TA 2 can work on both courses for 25 hours.
		 * So we are short a total of 25 hours for course 2 by 25 hours when optimally assigning our current TAs.
		 */
		Assert.assertEquals(25, TADistribution.shortageOfTAs(n, available, m, need, match));
	}
}