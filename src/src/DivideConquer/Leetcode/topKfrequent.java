package DivideConquer.Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class topKfrequent {

	public static int[] topKFrequent(int[] nums, int k) {
		if (k == 0 || nums == null) return null;
		if (k == nums.length) return nums;

		HashMap<Integer, Integer> frequency = new HashMap<>();
		for (int num : nums) frequency.put(num, frequency.getOrDefault(num, 0) + 1);

		int l = frequency.size();
		FrequencyPair[] list = new FrequencyPair[l];
		Iterator<Integer> iter = frequency.keySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			int it = iter.next();
			list[i ++] = new FrequencyPair(it, frequency.get(it));
		}

		return topKfrequent(list, k);
	}

	private static int[] topKfrequent(FrequencyPair[] nums, int k) {
		int left = 0, right = nums.length - 1, pivot = 0;
		int counter = 0;

		while (pivot != k - 1 && counter < nums.length) {
			pivot = partition(nums, left, right);
			if (pivot < k) left = pivot;
			else right = pivot - 1;
			counter ++;
		}

		for (FrequencyPair num : nums) {
			System.out.println(num.toString());
		}
		int[] answer = new int[k];
		for (int i = 0; i < k; i ++) {
			answer[i] = nums[i].number;
		}
		return answer;
	}

	private static void quickSelect(FrequencyPair[] nums, int k, int left, int right) {
		int pivot = partition(nums, left, right);
		if (pivot == k) return;
		if (pivot < k) quickSelect(nums, k, pivot + 1, right);
		else quickSelect(nums, k, left, pivot - 1);
	}

	private static int partition(FrequencyPair[] nums, int left, int right) {
		int mid = (left + right) / 2;
		int p = nums[mid].frequency;

		while (left < right) {
			if (nums[left].frequency < p) {
				swap(nums, left, right);
				right --;
			} else left ++;
		}

		if (nums[left].frequency < p) left ++;
		return left;
	}

	public static void swap(FrequencyPair[] points, int i, int j) {
		FrequencyPair tmp = points[i];
		points[i] = points[j];
		points[j] = tmp;
	}
}

class FrequencyPair {
	int frequency;
	int number;

	public FrequencyPair(int number, int frequency) {
		this.number = number;
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return this.number + " : " + this.frequency;
	}
}