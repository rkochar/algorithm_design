package DivideConquer.Weblab;

public class QuickSort {

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
		int i = left;
		int j = right;
		int pivot = arr[left];

		while (i < j) {
			while (arr[i] < pivot && i < arr.length - 1) i ++;
			while (arr[j] > pivot && j > 0) j --;
			if (i < j) swap(arr, i, j);
		}
		if (arr[left] > arr[j]) swap(arr, left, j);
		return j;
	}

	public static void swap(int arr[], int left, int right) {
		if (left == right) return;
		arr[left] += arr[right];
		arr[right] = arr[left] - arr[right];
		arr[left] -= arr[right];
	}
}
