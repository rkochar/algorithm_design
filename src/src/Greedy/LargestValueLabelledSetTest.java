package Greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LargestValueLabelledSetTest {

	@Test
	void largestValsFromLabels() {
		int[] a = {9, 8, 8, 7, 6};
		int[] b = {0, 0, 0, 1, 1};
		assertEquals(16, LargestValueLabelledSet.largestValsFromLabels(a, b, 3, 1));
	}
}