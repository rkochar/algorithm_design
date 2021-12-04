package Greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChefSatisfactionTest {

	@Test
	void maxSatisfaction() {
		int[] a = {-1, -8, 0, 5, -9};
		assertEquals(14, ChefSatisfaction.maxSatisfaction(a));
	}
}