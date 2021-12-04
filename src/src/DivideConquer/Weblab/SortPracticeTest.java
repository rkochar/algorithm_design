package DivideConquer.Weblab;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SortPracticeTest {

	@Test
	public void quickSort0() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = a.clone();
		SortPractice.quickSort(b);
		assertArrayEquals(a, b);
	}

	@Test
	public void quickSort1() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = {1, 9, 5, 4, 3, 6, 8, 7, 2, 10};
		SortPractice.quickSort(b);
		assertArrayEquals(a, b);
	}

	@Test
	public void quickSort2() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = new int[10];
		for (int i = 0; i < 10; i ++) b[9 - i] = a[i];
		SortPractice.quickSort(b);
		assertArrayEquals(a, b);
	}

	@Test
	public void mergeSort0() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = a.clone();
		SortPractice.mergeSort(b);
		assertArrayEquals(a, b);
	}

	@Test
	public void mergeSort1() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = {1, 9, 5, 4, 3, 6, 8, 7, 2, 10};
		SortPractice.mergeSort(b);
		assertArrayEquals(a, b);
	}

	@Test
	public void mergeSort2() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] b = new int[10];
		for (int i = 0; i < 10; i ++) b[9 - i] = a[i];
		SortPractice.mergeSort(b);
		assertArrayEquals(a, b);
	}
}