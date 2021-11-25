package greedy;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CitiesStraightLineTest {

	@Test
	void pylons1() {
		List<Integer> input = new LinkedList<>();
		int k = 2;
		int[] in = {0, 1, 1, 1, 1, 0};
		for (int i: in) input.add(i);

		assertEquals(2, CitiesStraightLine.pylons(k, input));
	}

	@Test
	void pylons2() {
		List<Integer> input = new LinkedList<>();
		int k = 2;
		int[] in = {0, 1, 0, 0, 0, 1, 0};
		for (int i: in) input.add(i);

		assertEquals(-1, CitiesStraightLine.pylons(k, input));
	}

	@Test
	void pylons3() {
		List<Integer> input = new LinkedList<>();
		int k = 3;
		int[] in = {0, 1, 0, 0, 0, 1, 1, 1, 1, 1};
		for (int i: in) input.add(i);

		assertEquals(3, CitiesStraightLine.pylons(k, input));
	}

	@Test
	void pylons4() {
		List<Integer> input = new LinkedList<>();
		int k = 3;
		int[] in = {1};
		for (int i: in) input.add(i);

		assertEquals(1, CitiesStraightLine.pylons(k, input));
	}

	@Test
	void pylons5() {
		List<Integer> input = new LinkedList<>();
		int k = 3;
		int[] in = {0};
		for (int i: in) input.add(i);

		assertEquals(-1, CitiesStraightLine.pylons(k, input));
	}

	@Test
	void pylons6() {
		List<Integer> input = new LinkedList<>();
		int k = 4;
		int[] in = {1, 1};
		for (int i: in) input.add(i);

		assertEquals(1, CitiesStraightLine.pylons(k, input));
	}
}