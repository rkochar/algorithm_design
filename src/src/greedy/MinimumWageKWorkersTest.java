package greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumWageKWorkersTest {

	@Test
	void minCostToHireWorkers() {
		int[] a = {10, 20, 5};
		int[] b = {70, 50, 30};
		assertEquals(105.0, MinimumWageKWorkers.minCostToHireWorkers(a, b, 2));
	}

	@Test
	void minCostToHireWorkers1() {
		int[] a = {3, 1, 10, 10, 1};
		int[] b = {4, 8, 2, 2, 7};
		assertEquals(30.666666666666664, MinimumWageKWorkers.minCostToHireWorkers(a, b, 3));
	}
}