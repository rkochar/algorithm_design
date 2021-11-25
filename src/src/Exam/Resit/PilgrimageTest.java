package Exam.Resit;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import java.util.Set;

public class PilgrimageTest {

	@Test(timeout = 100)
	public void example() {
		int n = 3;
		int[] a = { 0, 1, 2, 3 };
		int[] b = { 0, 3, 4, 5 };
		/*
		 * If we setup just one post at distance 3, we got all fragments covered.
		 */
		Set<Integer> result = Pilgrimage.providingSupport(n, a, b);
		Assert.assertEquals("Just the one will do", 1, result.size());
		assertTrue("At time 3", result.contains(3));
	}

	@Test(timeout = 100)
	public void example_one_event() {
		int n = 1;
		int[] a = { 0, 10 };
		int[] b = { 0, 32 };
		// Just the one post is fine.
		assertEquals("Just the one", 1, Pilgrimage.providingSupport(n, a, b).size());
	}


}