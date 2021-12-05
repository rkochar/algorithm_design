package DivideConquer.Weblab;

public class    SortPractice {

	public static int[] quickSort(int[] arr) {
		return quickSort(arr, 0, arr.length - 1);
	}

	private static int[] quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int p = partition(arr, left, right);
			quickSort(arr, left, p - 1);
			quickSort(arr, p + 1, right);
		}
		return arr;
	}

	private static int partition(int[] arr, int left, int right) {
		int i = left, j = right, pivot = arr[left];

		while (i < j) {
			while (arr[i] < pivot && i <= right) i ++;
			while (arr[j] > pivot && j >= left) j --;
			if (i < j) swap(arr, i, j);
		}

		if (arr[left] > arr[j]) swap(arr, left, j);
		return j;
	}

	public static int[] mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
		return arr;
	}

	private static void mergeSort(int[] arr, int left, int right) {
		if (right <= left) return;

		int mid = (right + left) / 2;
		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);
		merge(arr, left, mid, right);
	}

	private static void merge(int[] arr, int left, int mid, int right) {
		int i1 = left, i2 = mid + 1, i = 0;
		int[] tmp = new int[right - left + 1];

		while (i1 <= mid && i2 <= right) {
			if (arr[i1] < arr[i2]) tmp[i ++] = arr[i1 ++];
			else tmp[i ++] = arr[i2 ++];
		}

		while (i1 <= mid) tmp[i ++] = arr[i1 ++];
		while (i2 <= right) tmp[i ++] = arr[i2 ++];
		for (i = 0; i < tmp.length; i ++) arr[i + left] = tmp[i];
	}

	public static void swap(int[] arr, int i, int j) {
		arr[i] += arr[j];
		arr[j] = arr[i] - arr[j];
		arr[i] -= arr[j];
	}
}
