package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class WeakCharacters {

	/**
	 * Characters have attack and defense. Weak characters are strictly lower on
	 * both attack and defense. Find number of weak characters.
	 *
	 * https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/
	 * @param properties - [attack][defense]
	 * @return number of weak characters
	 */
	public static int numberOfWeakCharacters(int[][] properties) {
		Arrays.sort(properties, new Comparator<int[]>() {
			@Override
			public int compare(int[] ints, int[] t1) {
				if (ints[0] == t1[0]) return t1[1] - ints[1];
				else return ints[0] - t1[0];
			}
		});

		int max = Integer.MIN_VALUE, count = 0;
		for (int i = properties.length - 1; i >= 0; i --) {
			int[] property = properties[i];

			if (property[1] < max) count ++;
			max = Math.max(property[1], max);
		}

		return count;
	}
}
