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
				tmp[i ++] = arr[i2 ++];
			}
		}

		while (i1 <= mid) tmp[i ++] = arr[i1 ++];
		while (i2 <= right) tmp[i ++] = arr[i2 ++];
		for (i = 0; i < tmp.length; i ++) arr[i + left] = tmp[i];
		return count;
	}
}
