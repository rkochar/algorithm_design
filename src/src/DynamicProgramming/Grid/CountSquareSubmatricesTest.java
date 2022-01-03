package DynamicProgramming.Grid;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountSquareSubmatricesTest {

	@Test
	public void countSquares() {
		int[][] aa = {{0, 0, 0, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 1, 1, 1}, {0, 0, 1, 1, 1}};
		assertEquals(9, new CountSquareSubmatrices().countSquares(aa));
	}
}