package DivideConquer.Weblab;

import java.util.Arrays;

public class CountInversions {

	public static int countInversions(int[] arr) {
		return sortAndCount(arr, 0, arr.length - 1);
	}

	public static int sortAndCount(int[] arr,  int left, int right) {
		if (right <= left) return 0;
		else {
			int mid = (left + right) / 2;
			int countLeft = sortAndCount(arr, left, mid);
			int countRight = sortAndCount(arr, mid + 1, right);
			int count = mergeAndCount(arr, left, mid, right);
			return count + countLeft + countRight;
		}
	}

	public static int mergeAndCount(int[]arr, int left, int mid, int right) {
		int i1 = left;
		int i2 = mid + 1;
		int count = 0, i = 0;
		int[] tmp = new int[right - left + 1];

		while (i1 <= mid && i2 <= right) {
			if (arr[i1] <= arr[i2]) {
				tmp[i ++] = arr[i1 ++];
			} else {
				count += mid - i1 + 1;
//				count ++;
				tmp[i ++] = arr[i2 ++];
			}
		}

		while (i1 <= mid) tmp[i ++] = arr[i1 ++];
		while (i2 <= right) tmp[i ++] = arr[i2 ++];
		for (i = 0; i < tmp.length; i ++) arr[i + left] = tmp[i];
		return count;
	}

	// ######################################################################################################
	public static int countInversions(int[] arr1, int[] arr2) {
		int[] arr = new int[arr1.length + arr2.length];
		return sortAndCount(arr, arr1, arr2, 0, arr1.length - 1, 0, arr2.length - 1);
	}

	private static int sortAndCount(int[] arr, int[] arr1, int[] arr2, int left1, int right1, int left2, int right2) {
		int oneSum = sortAndCount(arr1, left1, right1);
		int twoSum = sortAndCount(arr2, left2, right2);
		int countMerge = mergeAndCount(arr, arr1, arr2);

		return oneSum + twoSum + countMerge;
	}

	private static int mergeAndCount(int[] arr, int[] arr1, int[] arr2) {
		int i1 = 0, i2 = 0;
		int count = 0, i = 0;

		while (i1 < arr1.length - 1 && i2 < arr2.length - 1) {
			if (arr1[i1] <= arr2[i2]) arr[i ++] = arr1[i1 ++];
			else {
				count ++;
				arr[i ++] = arr2[i2 ++];
			}
		}

		while (i1 <= arr1.length - 1) arr[i ++] = arr1[i1 ++];
		while (i2 <= arr2.length - 1) arr[i ++] = arr2[i2 ++];
		return count;
	}

	public static int countInversionsGreedy(int[] arr1, int[] arr2) {
		return Integer.max(greedyCount(arr1, arr2), greedyCount(arr2, arr1));
	}

	public static int greedyCount(int[] arr1, int[] arr2) {
		int count = 0;
		int i1 = 0, i2 = 0;

		while (i1 < arr1.length && i2 < arr2.length) {
			if (arr1[i1] < arr2[i2]) {
				i1 ++;
				i2 ++;
			} else {
				i2 ++;
				count ++;
			}
		}

		if (i2 < arr2.length) {
			count += arr1.length - i2;
		}

		return count;
	}
}
