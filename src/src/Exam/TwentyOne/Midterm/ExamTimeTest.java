package Exam.TwentyOne.Midterm;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class ExamTimeTest {

	/* Doing job 1 first: 5*1 + 9*3 = 32
	  Doing job 2 first: 4*3 + 9*1 = 21 */
	@Test(timeout = 100)
	public void example() {
		int n = 2;
		int[] t = { 0, 5, 4 };
		int[] p = { 0, 1, 3 };
		assertEquals(21, ExamTime.prioritisingExercises(n, t, p));
	}

	// 4*3 + (4 + 5) * 1 + 0
	@Test(timeout = 100)
	public void example1() {
		int n = 3;
		int[] t = { 0, 5, 4, 15 };
		int[] p = { 0, 1, 3, 0 };
		assertEquals(21, ExamTime.prioritisingExercises(n, t, p));
	}

	// 2*100 + (2 + 4)*3 + (6 + 5) * 1 + 0
	@Test(timeout = 100)
	public void example2() {
		int n = 4;
		int[] t = { 0, 5, 4, 15, 2 };
		int[] p = { 0, 1, 3, 0, 100 };
		assertEquals(229, ExamTime.prioritisingExercises(n, t, p));
	}

	// 5*3 + (4 + 5)*3
	@Test(timeout = 100)
	public void example3() {
		int n = 2;
		int[] t = { 0, 5, 4 };
		int[] p = { 0, 3, 3 };
		assertEquals(39, ExamTime.prioritisingExercises(n, t, p));
	}

	@Test(timeout = 100)
	public void example4() {
		int n = 0;
		int[] t = { 0 };
		int[] p = { 0};
		assertEquals(0, ExamTime.prioritisingExercises(n, t, p));
	}

	// 5*5 + (5 + 5)*5
	@Test(timeout = 100)
	public void example5() {
		int n = 2;
		int[] t = { 0, 5, 5 };
		int[] p = { 0, 5, 5 };
		assertEquals(75, ExamTime.prioritisingExercises(n, t, p));
	}

	// 355
	@Test(timeout = 100)
	public void example6() {
		int n = 5;
		int[] t = { 0, 5, 4, 15, 2, 100 };
		int[] p = { 0, 1, 3, 0, 100, 1 };
		assertEquals(340, ExamTime.prioritisingExercises(n, t, p));
	}

	// 1*100 + 101*1 = 201
	// 100*1 + 101*100 = 10200
	@Test(timeout = 100)
	public void example7() {
		int n = 2;
		int[] t = { 0, 100, 1};
		int[] p = { 0, 1, 100};
		assertEquals(201, ExamTime.prioritisingExercises(n, t, p));
	}

}