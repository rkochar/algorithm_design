package DivideConquer.Weblab;

public class    SortPractice {

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int p = partition(arr, left, right);
			quickSort(arr, left, p - 1);
			quickSort(arr, p + 1, right);
		}
	}

	public static int partition(int[] arr, int left, int right) {
		int i1 = left;
		int i2 = right;
		int pivot = arr[left];

		while (i1 < i2) {
			while (i1 <= right && arr[i1] < pivot) i1 ++;
			while (i2 >= left && arr[i2] > pivot) i2 --;
			if (i1 < i2) swap(arr, i1, i2);
		}
		if (arr[left] > arr[i2]) swap(arr, left, i2);
		return i2;
	}

	public static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	public static void mergeSort(int[] arr, int left, int right) {
		if (left >= right) return;

		int mid = (right + left) / 2;
		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);
		merge(arr, left, mid, right);
	}

	public static void merge(int[] arr, int left, int mid, int right) {
		int i1 = left;
		int i2 = mid + 1;
		int i = 0;
		int[] tmpArray = new int[right - left + 1];

		while (i1 <= mid && i2 <= right)
			if (arr[i1] <= arr[i2]) tmpArray[i ++] = arr[i1 ++];
			else tmpArray[i ++] = arr[i2 ++];


		while (i1 <= mid) tmpArray[i ++] = arr[i1++];
		while (i2 <= right) tmpArray[i ++] = arr[i2++];

		for (i = 0; i < tmpArray.length; i ++) arr[i + left] = tmpArray[i];
	}

	public static void swap(int[] arr, int i, int j) {
		arr[i] += arr[j];
		arr[j] = arr[i] - arr[j];
		arr[i] -= arr[j];
	}
}
