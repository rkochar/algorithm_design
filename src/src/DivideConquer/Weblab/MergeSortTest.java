package DivideConquer.Weblab;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MergeSortTest {

	@Test
	public void sort() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = a.clone();
		MergeSort.sort(b);
		assertArrayEquals(a, b);
	}

	@Test
	public void sort1() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = {1, 9, 5, 4, 3, 6, 8, 7, 2, 10};
		MergeSort.sort(b);
		System.out.println(Arrays.toString(b));
		assertArrayEquals(a, b);
	}

	@Test
	public void sort2() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = new int[10];
		for (int i = 0; i < 10; i ++) b[9 - i] = a[i];
		MergeSort.sort(b);
		System.out.println(Arrays.toString(b));
		assertArrayEquals(a, b);
	}
}