package Exam.TwentyOne.Resit;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class WeirdStreetTurningTest {

	@Test(timeout = 100)
	public void example_one_road() {
		int n = 2;
		int m = 1;
		int s = 1;
		int t = 2;
		Set<Streets> streets = new HashSet<>();
		streets.add(new Streets(1, 2, 10));
		/*
		 * Only one road so only one shortest path
		 */
		assertEquals(1, WeirdStreetTurning.solve(n, m, streets, s, t));
	}

	@Test(timeout = 100)
	public void example_two_routes() {
		int n = 4;
		int m = 3;
		int s = 1;
		int t = 4;
		Set<Streets> streets = new HashSet<>();
		streets.add(new Streets(1, 2, 10));
		streets.add(new Streets(1, 3, 1));
		streets.add(new Streets(2, 4, 1));
		streets.add(new Streets(3, 4, 10));
		/*
		 * There are two shortest path of weight 11.
		 */
		assertEquals(2, WeirdStreetTurning.solve(n, m, streets, s, t));
	}

	@Test(timeout = 200)
	public void example_with_a_negative_edge() {
		int n = 4;
		int m = 3;
		int s = 1;
		int t = 4;
		Set<Streets> streets = new HashSet<>();
		streets.add(new Streets(1, 2, 10));
		streets.add(new Streets(1, 3, -4));
		streets.add(new Streets(2, 4, -4));
		streets.add(new Streets(3, 4, 10));
		/*
		 * There are two shortest paths of weight 6.
		 */
		assertEquals(2, WeirdStreetTurning.solve(n, m, streets, s, t));
	}
}