package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumInitialEnergy {

	/**
	 * A task consumes some energy and requires some energy to be started (requires > consume).
	 * Sort by difference and max(ans, consumed + required[i]).
	 *
	 * https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/
	 *
	 * @param tasks - [consume][require]
	 * @return initial energy to complete all tasks
	 */
	public static int minimumEffort(int[][] tasks) {
		Arrays.sort(tasks, new Comparator<int[]>() {
			@Override
			public int compare(int[] ints, int[] t1) {
				return Integer.compare(t1[1] - t1[0], ints[1] - ints[0]);
			}
		});
		int answer = 0, energy = 0;
		System.out.println(Arrays.deepToString(tasks));
		for (int[] ints: tasks) {
			answer = Math.max(answer, energy + ints[1]);
			energy += ints[0];
		}
		return answer;
	}
}
