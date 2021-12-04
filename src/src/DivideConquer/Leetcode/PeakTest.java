package DivideConquer.Leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PeakTest {

	@Test
	public void findPeakGrid() {
		int[][] grid = {{1, 4}, {3, 2}};
		int[] answer = {1, 0};
		assertArrayEquals(answer, Peak.findPeakGrid(grid));
	}

	@Test
	public void findPeakGrid1() {
		int[][] grid = {{41,8,2,48,18},{16,15,9,7,44},{48,35,6,38,28},{3,2,14,15,33},{39,36,13,46,42}};
		int[] answer = {3, 4};
		int[] r = Peak.findPeakGrid(grid);
		System.out.println(Arrays.toString(r));
		assertArrayEquals(answer, r);
	}


	@Test
	public void findPeakGrid2() {
		int[][] grid = 	{{78,96,64},{37,100,30},{78,46,29},{82,25,80},{33,87,97},{93,99,85},{88,18,81},{13,81,83},{6,40,57},{5,75,47},{94,17,12},{38,42,96},{54,23,26},{17,70,47},{68,65,35},{22,33,62},{38,96,44},{15,60,10},{19,97,29},{87,93,87},{51,72,47},{12,51,2},{34,69,16},{59,48,87},{96,87,34}};
		int[] answer = {1, 1};
		int[] r = Peak.findPeakGrid(grid);
		System.out.println(Arrays.toString(r));
		assertArrayEquals(answer, r);
	}
}