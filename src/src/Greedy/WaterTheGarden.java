package Greedy;

public class WaterTheGarden {

	/**
	 * Find minimum number of taps to water a garden where each tap can water a range (to left and right).
	 * Let all taps water into an array and store i + ranges[i] (idk why).
	 * Jump over the values and add 1 to answer every jump.
	 * Intuitively, i + range[i] can water all spots of that value.
	 *
	 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
	 *
	 * @param n - length of garden
	 * @param ranges - ranges of taps
	 * @return minimum number of taps needed to water garden
	 */
	public static int minTaps(int n, int[] ranges) {
		int answer = 0;
		int[] jumps = new int[n + 1];
		for (int i= 0; i < ranges.length; i ++) {
			for (int j = Math.max(0, i - ranges[i]); j < Math.min(n, i + ranges[i]); j ++) {
				jumps[j] = Math.max(jumps[j], i + ranges[i]);
			}
		}

		int p = 0;
		while (p < n) {
			if (jumps[p] == 0) return -1;
			p = jumps[p];
			answer ++;
		}

		return answer;
	}
}
