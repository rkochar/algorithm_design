package Exam.TwentyOne.Endterm;


import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;
import org.junit.rules.*;

public class RecoverYourItemsTest {

	@Test(timeout = 100)
	public void example_one_item() {
		int n = 2;
		int m = 2;
		int[] v = { 0, 2, 1 };
		int[][] mem = { { 1, 1, 1 }, { 2, 1, 1 }, { 2, 1, 1 } };
		/*
		 * Only item 1 should be included.
		 */
		Set<Integer> ans = RecoverYourItems.solve(n, m, v, mem);
		assertEquals(1, ans.size());
		assertTrue(ans.contains(1));
	}

	@Test(timeout = 100)
	public void example_multiple_items() {
		int n = 5;
		int m = 10;
		// The best answer is to take 5, 3 and 2 to get 30, so indices 1, 2, and 5
		int[] v = { 0, 5, 3, 1, 8, 2 };
		int[][] mem = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 1 }, { 15, 15, 15, 5, 5, 5, 3, 3, 1, 1, 1 }, { 15, 15, 15, 5, 5, 5, 3, 3, 1, 1, 1 }, { 15, 15, 15, 5, 5, 5, 3, 3, 1, 1, 1 }, { 30, 15, 15, 10, 6, 6, 3, 3, 2, 1, 1 } };
		Set<Integer> ans = RecoverYourItems.solve(n, m, v, mem);
		assertEquals(3, ans.size());
		assertTrue(ans.contains(1));
		assertTrue(ans.contains(2));
		assertTrue(ans.contains(5));
	}

}