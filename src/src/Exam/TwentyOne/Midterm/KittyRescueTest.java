package Exam.TwentyOne.Midterm;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class KittyRescueTest {

	// see assignment text
	@Test(timeout = 100)
	public void text_example() {
		int n = 4;
		int[] h = { 0, 12, 8, 3, 10 };
		assertEquals(11, KittyRescue.findBestRescueMissionBruteForce(n, h));
	}

	@Test(timeout = 100)
	public void text_example_dc() {
		int n = 4;
		int[] h = { 0, 12, 8, 3, 10 };
		assertEquals(11, KittyRescue.findBestRescueMissionDivideAndConquer(n, h));
	}

	// So 2 is the best
	@Test(timeout = 100)
	public void small_example() {
		int n = 3;
		int[] h = { 0, 1, 4, 3 };
		assertEquals(2, KittyRescue.findBestRescueMissionBruteForce(n, h));
	}

	@Test(timeout = 100)
	public void small_example_dc() {
		int n = 3;
		int[] h = { 0, 1, 4, 3 };
		assertEquals(2, KittyRescue.findBestRescueMissionDivideAndConquer(n, h));
	}

	// The best one is [13, 10, 2]
	@Test(timeout = 100)
	public void large_example() {
		int n = 8;
		int[] h = { 0, 6, 5, 10, 12, 11, 13, 10, 2 };
		assertEquals(13, KittyRescue.findBestRescueMissionBruteForce(n, h));
	}

	@Test(timeout = 100)
	public void large_example_dc() {
		int n = 8;
		int[] h = { 0, 6, 5, 10, 12, 11, 13, 10, 2 };
		assertEquals(13, KittyRescue.findBestRescueMissionDivideAndConquer(n, h));
	}
}

