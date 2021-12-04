package Greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MakeMatrixSumRowColsTest {

	@Test
	void restoreMatrix() {
		int[] rows = {5, 7, 10};
		int[] cols = {8, 6, 8};
		int[][] ans = {{0, 5, 0}, {6, 1, 0}, {2, 0, 8}};
		assertEquals(ans, MakeMatrixSumRowCols.restoreMatrix(rows, cols));
	}
}