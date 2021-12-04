package DivideConquer.Weblab;

import javax.swing.*;

public class MergeSort {

	/**
	 * Takes an array and sorts it in an ascending order.
	 *
	 * @param arr - the array that needs to be sorted.
	 */
	public static void sort(int[] arr) {
		// TODO
		helper(arr, 0, arr.length - 1);
	}

	public static void merge(int[] arr, int left, int mid, int right) {
		int[] newArray = new int[right - left + 1];
		int i1 = left;
		int i2 = mid + 1;
		int i = 0;

		while (i1 <= mid && i2 <= right){
			if (arr[i1] <= arr[i2]) {
				newArray[i] = arr[i1];
				i1++;
			} else {
				newArray[i] = arr[i2];
				i2++;
			}
			i++;
		}

		while (i1 <= mid) {
			newArray[i] = arr[i1];
			i1 ++;
			i ++;
		}

		while (i2 <= right) {
			newArray[i] = arr[i2];
			i ++;
			i2 ++;
		}

		for (i = 0; i < newArray.length; i ++) {
			arr[i + left] = newArray[i];
		}
	}

	public static void helper(int[] arr, int left, int right) {
		if (left >= right) return;

		int mid = (right + left) / 2;
		helper(arr, left, mid);
		helper(arr, mid + 1, right);
		merge(arr, left, mid, right);
	}
}
