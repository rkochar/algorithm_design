package greedy;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardCutterTest {

	@org.junit.jupiter.api.Test
	void testOne() {
		int m = 2, n = 2;
		List<Integer> cost_x = new LinkedList<>();
		List<Integer> cost_y = new LinkedList<>();

		cost_x.add(2);
		cost_y.add(1);
		assertEquals(4, BoardCutter.boardCuttingSimpler(cost_y, cost_x, m, n));
	}

	@org.junit.jupiter.api.Test
	void testTwo() {
		int m = 6, n = 4;
		List<Integer> cost_x = new LinkedList<>();
		List<Integer> cost_y = new LinkedList<>();

		int[] costs_x = {2, 1, 3, 1, 4};
		int[] costs_y = {4, 1, 2};
		for (int x: costs_x) cost_x.add(x);
		for (int y: costs_y) cost_y.add(y);

		assertEquals(42, BoardCutter.boardCuttingSimpler(cost_y, cost_x, m, n));
	}

}