package Exam.TwentyOne.Endterm;

import java.util.HashSet;
import java.util.Set;

/**
 * WARNING: The spec tests are not necessarily equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined by a manual inspection of your implementation.
 */
public class RecoverYourItems {

	/**
	 *  You should implement this method.
	 *
	 *  @param n   the number of items n.
	 *  @param m   the maximum weight.
	 *  @param v   the array containing the weights of the items, in index 1 through n. Note this means you should ignore v[0] and use v[1] through v[n].
	 *  @param mem the memory filled by the dynamic programming algorithm using the provided recursive formulation.
	 *  @return the set of indices of items that together form the optimal solution.
	 */
	public static Set<Integer> solve(int n, int m, int[] v, int[][] mem) {
		int i = n - 1;
		int weight = m;
		HashSet<Integer> answer = new HashSet<>();

		while (i > 0) {
			if (weight >= v[i] && mem[i - 1][weight + v[i]] * v[i] >= (mem[i - 1][weight])) {
				answer.add(i);
				weight -= v[i];
			}
			i--;
		}
		return answer;
	}

	// Correct answer from Weblab.
	public static Set<Integer> solveProper(int n, int m, int[] v, int[][] mem) {
		int x = n;
		int y = 0;
		HashSet<Integer> res = new HashSet<>();
		while (x > 0) {
			if (m - y >= v[x] && mem[x][y] == v[x] * mem[x - 1][y + v[x]]) {
				res.add(x);
				y += v[x];
				x -= 1;
			} else {
				y -= 0;
				x -= 1;
			}
		}
		return res;
	}
}
