package DynamicProgramming.Weblab;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KnapsackTest {

	@Test
	public void knapsack() {
		int n = 3;
		int maxWeight = 10;
		int[] w = { 0, 8, 3, 5 };
		int[] v = { 0, 8, 4, 9 };
		assertEquals(13, Knapsack.knapsack(n, maxWeight, w, v));
	}

	@Test
	public void recover() {
		int n = 3;
		int maxWeight = 10;
		int[] w = { 0, 8, 3, 5 };
		int[] v = { 0, 8, 4, 9 };
		int[][] mem = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8 },
				{ 0, 0, 0, 4, 4, 4, 4, 4, 8, 8, 8 },
				{ 0, 0, 0, 4, 4, 9, 9, 9, 13, 13, 13 } };
		assertEquals(List.of(2, 3), Knapsack.recoverBackpack(maxWeight, n,w, v, mem));
	}
}