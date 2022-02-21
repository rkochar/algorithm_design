package Exam.TwentyOne.Endterm;

import java.util.Scanner;

import org.junit.*;
import org.junit.rules.*;

public class BookSaleTest {

	@Test()
	public void exampleOneDay() {
		int n = 2;
		int m = 3;
		int k = 1;
		int[] p = { 0, 1, 2 };
		int[] d = { 0, 1, 1, 1 };
		boolean[][] a = new boolean[n + 1][m + 1];
		a[1][1] = true;
		a[1][2] = true;
		a[2][2] = true;
		a[2][3] = true;
		/**
		 * This test models the situation where:
		 * Volunteer 1 can deliver books 1 and 2, and do at most 1.
		 * Volunteer 2 can deliver books 2 and 3, and do at most 2.
		 * All books need to be delivered on day 1.
		 *
		 * This is doable, for example by having Volunteer 1 do book 1 and Volunteer 2 do book 2 and 3.
		 */
		Assert.assertTrue(BookSale.gettingThoseBooksOut(n, m, k, p, d, a));
	}

	@Test()
	public void exampleOneDayLimit() {
		int n = 2;
		int m = 7;
		int k = 1;
		int[] a = { 0, 7, 7 };
		int[] t = { 0, 1, 1, 1, 1, 1, 1, 1 };
		boolean[][] c = new boolean[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				c[i][j] = true;
			}
		}
		/**
		 * This test models the situation where:
		 * Volunteer 1 can do all books.
		 * Volunteer 2 can do all books.
		 * All books need to be delivered on day 1.
		 *
		 * This is _not_ doable, as each Volunteer can do at most 3 books per day!
		 */
		Assert.assertFalse(BookSale.gettingThoseBooksOut(n, m, k, a, t, c));
	}

	@Test()
	public void exampleTwoDays() {
		int n = 2;
		int m = 7;
		int k = 2;
		int[] a = { 0, 7, 7 };
		int[] t = { 0, 1, 1, 1, 2, 2, 2, 2 };
		boolean[][] c = new boolean[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				c[i][j] = true;
			}
		}
		/**
		 * This test models the situation where:
		 * Volunteer 1 can do all books.
		 * Volunteer 2 can do all books.
		 * The first three books need to be done on day 1, the last four on day 2.
		 *
		 * This is doable, in a variety of ways.
		 */
		Assert.assertTrue(BookSale.gettingThoseBooksOut(n, m, k, a, t, c));
	}

	@Test()
	public void exampleTwoDaysImpossible() {
		int n = 2;
		int m = 6;
		int k = 2;
		int[] a = { 0, 3, 2 };
		int[] t = { 0, 1, 1, 1, 1, 2, 2 };
		boolean[][] c = new boolean[n + 1][m + 1];
		c[1][1] = true;
		c[1][2] = true;
		c[1][3] = true;
		c[1][4] = true;
		c[1][2] = true;
		c[1][5] = true;
		c[1][6] = true;
		/**
		 * This test models the situation where:
		 * Volunteer 1 can do all books on day 1.
		 * Volunteer 2 can do all books of day 2, and one of day 1, but only 2 all together!
		 * The first four books need to delivered on day 1, the last two on day 2.
		 *
		 * This is _not_ doable, as Volunteer one cannot do all the ones of day 1. Which means Volunteer two needs to do three, which they cannot do.
		 */
		Assert.assertFalse(BookSale.gettingThoseBooksOut(n, m, k, a, t, c));
	}
}