package Greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterTheGardenTest {

	@Test
	void minTaps() {
		int[] a = {3, 4, 1, 1, 0, 0};
		assertEquals(1, WaterTheGarden.minTaps(5, a));
	}

	@Test
	void minTaps1() {
		int[] a = {0, 0, 0, 0};
		assertEquals(-1, WaterTheGarden.minTaps(3, a));
	}

	@Test
	void minTaps2() {
		int[] a = {1, 2, 1, 0, 2, 1, 0, 1};
		assertEquals(3, WaterTheGarden.minTaps(7, a));
	}

	@Test
	void minTaps3() {
		int[] a = {4, 0, 0, 0, 0, 0, 0, 0, 4};
		assertEquals(2, WaterTheGarden.minTaps(8, a));
	}

	@Test
	void minTaps4() {
		int[] a = {4, 0, 0, 0, 4, 0, 0, 0, 4};
		assertEquals(1, WaterTheGarden.minTaps(8, a));
	}
}