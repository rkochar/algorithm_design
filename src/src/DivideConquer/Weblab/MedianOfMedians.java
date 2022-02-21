package DivideConquer.Weblab;

import java.util.Arrays;

public class MedianOfMedians {

	/**
	 * Takes an array and returns the ith smallest number using median of medians algorithm.
	 *
	 * @param arr   - the array to get the element from.
	 * @param index - the index of the return value if the array had been sorted.
	 */
	public static int findIth(int[] arr, int index) {
		int newsize = (arr.length - 1) / 5 + 1;
		int[] medians = new int[newsize];
		// Sort the sublists and find their medians.
		for (int i = 0; i < newsize; i++) {
			int pos = i * 5;
			Arrays.sort(arr, pos, Math.min(pos + 4, arr.length - 1));
			medians[i] = arr[pos + Math.min(2, (arr.length - pos) / 2)];
		}
		// calculate the pivot (median of medians)
		int pivot;
		if (newsize < 5) {
			Arrays.sort(medians);
			pivot = medians[newsize / 2];
		} else {
			pivot = findIth(medians, newsize / 2);
		}
		// First count how many will be in the 'low' array.
		// We could skip this step when using arrayLists, but there is no nice conversion
		// from ArrayList<Integer> to int[], so this is faster and does not affect the timecomplexity.
		int k = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= pivot)
				k++;
		}
		if (index < k) {
			// index before pivot
			int[] low = new int[k];
			int id = 0;
			boolean skipped = false;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] <= pivot) {
					if (arr[i] != pivot || skipped) {
						low[id++] = arr[i];
					} else {
						skipped = true;
					}
				}
			}
			return findIth(low, index);
		} else if (index > k) {
			// index after pivot
			int[] high = new int[arr.length - k - 1];
			int id = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > pivot) {
					high[id++] = arr[i];
				}
			}
			return findIth(high, index - k - 1);
		} else {
			// index at pivot
			return pivot;
		}
	}
}
