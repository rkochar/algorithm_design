package Exam.TwentyOne.Endterm;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;
import org.junit.rules.*;

public class ExamDatabaseTest {

	@Test(timeout = 100)
	public void example_one_exam() {
		int n = 1;
		int z = 30;
		int r = 20;
		int L = 15;
		int[] q = { 0, 4 };
		/**
		 * Only one exam, so we just sit down and create it on the day with a 30 minute overhead.
		 */
		assertEquals(30, ExamDatabase.minimiseOverhead(n, q, z, r, L));
	}

	@Test(timeout = 100)
	public void example_two_exams_no_reserves() {
		int n = 2;
		int z = 10;
		int r = 5;
		int L = 20;
		int[] q = { 0, 4, 5 };
		/**
		 * Only two exams, here are our options:
		 * - Sit down and create 4 for exam 1, and sit down and create 5 for  day2, for a total of 2*z = 20.
		 * - Sit down and create all 9 for exam 1, and store 5 for day 2, for a total of z + 4*5 = 30.
		 */
		assertEquals(20, ExamDatabase.minimiseOverhead(n, q, z, r, L));
	}

	@Test(timeout = 100)
	public void example_two_exams_using_reserves() {
		int n = 2;
		int z = 10;
		int r = 1;
		int L = 20;
		int[] q = { 0, 4, 5 };
		/**
		 * Only two exams, here are our options:
		 * - Sit down and create 4 for exam 1, and sit down and create 5 for  day2, for a total of 2*z = 20.
		 * - Sit down and create all 9 for exam 1, and store 5 for day 2, for a total of z + 5*1 = 15.
		 */
		assertEquals(15, ExamDatabase.minimiseOverhead(n, q, z, r, L));
	}

	@Test(timeout = 100)
	public void example_two_days_can_store() {
		int n = 2;
		int z = 10;
		int r = 1;
		int L = 7;
		int[] q = { 0, 4, 5 };
		/**
		 * Only two exams, here are our options:
		 * - Sit down and create 4 for exam 1, and sit down and create 5 for  day2, for a total of 2*z = 20.
		 * - Sit down and create all 9 for exam 1, and store 5 for day 2, for a total of z + 5*1 = 15.
		 *
		 * We can store just fine still, because we use our 4 questions on day 1 having 5 left which fits in our space of 7.
		 */
		assertEquals(15, ExamDatabase.minimiseOverhead(n, q, z, r, L));
	}

	@Test(timeout = 100)
	public void example_two_days_cannot_store() {
		int n = 2;
		int z = 10;
		int r = 1;
		int L = 2;
		int[] q = { 0, 4, 5 };
		/**
		 * Only two exams, here are our options:
		 * - Sit down and create 4 for exam 1, and sit down and create 5 for  day2, for a total of 2*z = 20.
		 * - Sit down and create all 9 for exam 1, and store 5 for day 2, for a total of z + 5*1 = 15.
		 *
		 * Ideally then we would create all and then recover them, but we cannot store sufficiently many, as we can only store 2! So alternative 2 becomes:
		 *
		 * - Sit down and create 6 for exam 1, storing 2 for day 2, for a total of z + 2*r = 12 and sit down and create another 3 for day2, so + z = 22. This is worse than just sitting down twice!
		 */
		assertEquals(20, ExamDatabase.minimiseOverhead(n, q, z, r, L));
	}
}