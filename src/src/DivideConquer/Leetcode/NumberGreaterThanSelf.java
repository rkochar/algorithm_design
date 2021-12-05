package DivideConquer.Leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class NumberGreaterThanSelf {

	public static int[] countInversions(int[] arr) {
		Number[] numbers = new Number[arr.length];
		for (int i = 0; i < arr.length; i++) numbers[i] = new Number(arr[i], 0, i);

		countInversions(numbers, 0, arr.length - 1);

		Arrays.sort(numbers, Comparator.comparingInt(x -> x.index));
		int[] answer = new int[arr.length];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = numbers[i].inversions;
		}
		System.out.println(Arrays.toString(answer));
		return answer;
	}

	private static void countInversions(Number[] arr, int left, int right) {
		if (right <= left) return;

		int mid = (right + left) / 2;
		countInversions(arr, left, mid);
		countInversions(arr, mid + 1, right);
		mergeInversions(arr, left, mid, right);
	}

	private static void mergeInversions(Number[] arr, int left, int mid, int right) {
		int i1 = left, i2 = mid + 1, i = 0;
		Number[] numbers = new Number[right - left + 1];

		while (i1 <= mid && i2 <= right) {
			if (arr[i1].number < arr[i2].number) numbers[i ++] = arr[i1 ++];
			else {
				arr[i1].inversions += mid - i1 + 1;
				numbers[i ++] = arr[i2 ++];
			}
		}

		while (i1 <= mid) {
			numbers[i ++] = arr[i1 ++];
		}
		while (i2 <= right) numbers[i ++] = arr[i2 ++];

		for (i = 0; i < numbers.length; i ++) arr[i + left] = numbers[i];
	}
}

class Number {
	int number;
	int inversions;
	int index;

	public Number(int number, int inversions, int index) {
		this.number = number;
		this.inversions = inversions;
		this.index = index;
	}
}