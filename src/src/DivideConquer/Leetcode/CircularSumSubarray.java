package DivideConquer.Leetcode;

public class CircularSumSubarray {

	public static int circularArraySum(int[] arr) {
		if (arr == null || arr.length == 0) return 0;
		return circularArraySum(arr, 0, arr.length - 1);
	}

	private static int circularArraySum(int[] arr, int left, int right) {
		if (right == left) return arr[left];

		int mid = (right + left) / 2;
		int leftSum = circularArraySum(arr, left, mid);
		int rightSum = circularArraySum(arr, mid + 1, right);

		int tmp = 0, leftrightSum = Integer.MIN_VALUE;
		for (int i = mid; i >= left; i --) {
			tmp += arr[i];
			leftrightSum = Integer.max(tmp, leftrightSum);
		}
		tmp = 0;
		int rightLeftSum = Integer.MIN_VALUE;
		for (int i = mid + 1; i <= right; i ++) {
			tmp += arr[i];
			rightLeftSum = Integer.max(tmp, rightLeftSum);
		}

		return Integer.max(leftrightSum + rightLeftSum, Integer.max(leftSum, rightSum));
	}
}
