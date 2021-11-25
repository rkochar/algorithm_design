package Exam.Resit;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
public class ChargerManDPTest {

	@Test(timeout = 100)
	public void example_one_switch() {
		int n = 3;
		double[] a = { 0.01, 0.01 };
		double[][][] mem = { // t == 0
				{ { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }, // t == 1
				{ { 50, 50 }, { 0.5, 0.5 }, { 0.005, 0.005 }, { 5e-5, 5e-5 }, { 0, 0 } }, // t == 2
				{ { 50.5, 50.5 }, { 50, 50 }, { 50, 50 }, { 50, 50 }, { 0, 0 } }, // t == n == 3
				{ { 100, 100 }, { 99.5, 99.5 }, { 99.5, 99.5 }, { 99.5, 99.5 }, { 0, 0 } } };
		/*
		 * Only switch on day 2.
		 */
		Set<Integer> ans = ChargerManDP.solve(n, a, mem);
		assertEquals(1, ans.size());
		assertTrue(ans.contains(2));
	}

	@Test(timeout = 100)
	public void example_no_switches() {
		int n = 3;
		double[] a = { 0.01, 0.99 };
		double[][][] mem = { // t == 0
				{ { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }, // t == 1
				{ { 50, 50 }, { 0.5, 49.5 }, { 0.005, 49.005 }, { 5e-5, 48.51495 }, { 0, 0 } }, // t == 2
				{ { 50.5, 99.5 }, { 50, 98.505 }, { 50, 97.51995 }, { 50, 50 }, { 0, 0 } }, // t == n == 3
				{ { 100, 148.505 }, { 99.5, 147.01995 }, { 99.5, 99.005 }, { 99.5, 50.5 }, { 0, 0 } } };
		/*
		 * Just stay on beach 2 forever.
		 */
		Set<Integer> ans = ChargerManDP.solve(n, a, mem);
		assertEquals(0, ans.size());
	}

}