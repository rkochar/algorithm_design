package DivideConquer.Weblab;

public class Practice {



//	public static int[] quickSort(int[] arr) {
//		return quickSort(arr, 0, arr.length - 1);
//	}

//	private static int[] quickSort(int[] arr, int left, int right) {
//
//	}

	private static void swap(int[] nums, int left, int right) {
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}
}
