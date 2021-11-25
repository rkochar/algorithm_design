package greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumInitialEnergyTest {

	@Test
	void minimumEffort() {
		int[][] aa = {{1, 2}, {2, 4}, {4, 8}};
		assertEquals(8, MinimumInitialEnergy.minimumEffort(aa));
	}

	@Test
	void minimumEffort1() {
		int[][] aa = {{1, 3}, {2, 4}, {10, 11}, {10,12}, {8, 9}};
		assertEquals(32, MinimumInitialEnergy.minimumEffort(aa));
	}

	@Test
	void minimumEffort2() {
		int[][] aa = {{1, 7}, {2, 8}, {3, 9}, {4, 10}, {5, 11}, {6, 12}};
		assertEquals(27, MinimumInitialEnergy.minimumEffort(aa));
	}
}