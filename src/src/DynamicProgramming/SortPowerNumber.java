package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortPowerNumber {

	Map<Integer, Integer> map = new HashMap<>();

	/**
	 * Power of a number is number of steps to reduce to 1 by /2 or (3x + 1).
	 * Store intermediary powers in global hashmap. Return kth smallest number of this arrau.
	 * Can do binary search for improved sorting but not part of question.
	 *
	 * @param low - lower bound of range to search
	 * @param high = highest bound of range to search
	 * @param k - kth number to return
	 * @return kth number of sorted array of powers of numbers in the range.
	 */
	public int getKth(int low, int high, int k) {
		map.put(1, 0);
		map.put(2, 1);
		PowerNumber[] arr = new PowerNumber[high - low + 1];
		for (int i = low; i <= high; i ++) {
			arr[i - low] = new PowerNumber(i, count(i));
		}

		Arrays.sort(arr);
		return arr[k - 1].number;
	}

	public int count(int i) {
		if (map.containsKey(i)) return map.get(i);
		else if ((i & 1) == 0) {
			int x = 1 + count(i >> 1);
			map.put(i, x);
			return x;
		} else {
			int x = 1 + count(3 * i + 1);
			map.put(i, x);
			return x;
		}
	}
}

class PowerNumber implements Comparable<PowerNumber> {
	int number;
	int power;

	public PowerNumber(int number, int power) {
		this.number = number;
		this.power = power;
	}

	@Override
	public int compareTo(PowerNumber powerNumber) {
		if (this.power == powerNumber.power) return Integer.compare(this.number, powerNumber.number);
		else return Integer.compare(this.power, powerNumber.power);
	}
}
