package Exam.TwentyOne.Resit;

import java.util.HashSet;
import java.util.Set;

public class ChargerManDP {

	/**
	 *  You should implement this method.
	 *
	 *  @param n   the number of days.
	 *  @param a   the alpha values of the different beaches a[0] and a[1].
	 *  @param mem the memory filled by the dynamic programming algorithm using the provided recursive formulation (dimensions [n+1][n+2][2])
	 *  @return the set of days on which Max should switch beach
	 */
	public static Set<Integer> solve(int n, double[] a, double[][][] mem) {
		return solveProper(n, a, mem);
	}

	public static Set<Integer> solveProper(int n, double[] a, double[][][] mem) {
		HashSet<Integer> res = new HashSet<>();
		int t = n;
		int d = 0;
		int b = 0;
		if (mem[n][d][1] > mem[n][d][0]) {
			b = 1;
		}
		while (t > 0) {
			if (mem[t][d][b] == mem[t - 1][0][1 - b]) {
				d = 0;
				b = 1 - b;
				res.add(t);
			} else {
				d += 1;
			}
			t--;
		}
		return res;
	}
}
