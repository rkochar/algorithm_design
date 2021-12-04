package DivideConquer.Weblab;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PowerSet {

	/**
	 * Computes all possible partial sums given an array of integers.
	 *
	 * @param arr - all values in the input set
	 * @return set of sums
	 */
	public static Set<Integer> partialSums(Integer[] arr) {
		Set<Integer> answer = new HashSet<>();
		answer.add(0);
		if (arr.length != 0) answer.addAll(partialSums(arr, 0, arr.length - 1));
		return answer;
	}

	private static Set<Integer> partialSums(Integer[] arr, int left, int right) {
		if (left == right) return new HashSet<>(Arrays.asList(arr[left]));

		int mid = (left + right) / 2;
		Set<Integer> leftSet = partialSums(arr, left, mid);
		Set<Integer> rightSet = partialSums(arr, mid + 1, right);

		Set<Integer> combine = new HashSet<>();
		combine.addAll(leftSet);
		combine.addAll(rightSet);

		for (Integer i: leftSet)
			for (Integer j: rightSet)
				combine.add(i + j);

		return combine;
	}
}
