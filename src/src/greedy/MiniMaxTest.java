package greedy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MiniMaxTest {

	@Test
	public void one() {
		List<Integer> arr = Arrays.asList(1,3,5,7,9);
		int p = 10, q = 12;
		assertEquals(12, MiniMax.sherlockAndMinimax(arr, p, q));
	}

	@Test
	public void two() {
		List<Integer> arr = Arrays.asList(5,6,9);
		int p = 1, q = 3;
		assertEquals(1, MiniMax.sherlockAndMinimax(arr, p, q));
	}

	@Test
	public void three() {
		List<Integer> arr = Arrays.asList(3, 7, 8);
		int p = 1, q = 4;
		assertEquals(4, MiniMax.sherlockAndMinimax(arr, p, q));
	}

}