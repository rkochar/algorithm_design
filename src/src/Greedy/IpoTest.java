package Greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IpoTest {

	@Test
	void findMaximizedCapital() {
		int[] profits = {1, 2, 3};
		int[] capital = {0, 1, 1};
		assertEquals(4, Ipo.findMaximizedCapital(2, 0, profits, capital));
	}

	@Test
	void findMaximizedCapital1() {
		int[] profits = {1, 2, 3};
		int[] capital = {0, 1, 2};
		assertEquals(6, Ipo.findMaximizedCapital(3, 0, profits, capital));
	}
}