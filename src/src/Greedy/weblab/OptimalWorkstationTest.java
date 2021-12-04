package Greedy.weblab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptimalWorkstationTest {

	@Test
	void solve() {
		int n = 5;
		int m = 10;
		int[] start = { 0, 2, 1, 17, 3, 15 };
		int[] end = { 0, 6, 2, 7, 9, 6 };
		assertEquals(3, OptimalWorkstation.solve(n, m, start, end));
	}
}