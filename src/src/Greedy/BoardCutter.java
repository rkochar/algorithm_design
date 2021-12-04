package Greedy;

import java.util.Comparator;
import java.util.List;

/**
 * Cut a board along horizontal and vertical lines which have costs. Find minimum cost to break board into 1x1 pieces. Cost of cutting a line is multiplied by pieces it is already broken into.
 */
public class BoardCutter {

	public static int boardCuttingSimpler(List<Integer> cost_y, List<Integer> cost_x, int m, int n) {
		cost_x.sort(Comparator.comparingInt(x -> -x));
		cost_y.sort(Comparator.comparingInt(x -> -x));
		int answer = 0;

		int i = 0, j = 0;
		while (i < m - 1 && j < n - 1) {
			if (cost_x.get(i) > cost_y.get(j)) {
				// Cut x
				answer += (cost_x.get(i) * (1 + j) % 1000000007);
				i += 1;
			} else if (cost_x.get(i) < cost_y.get(j)) {
				// Cut y
				answer += (cost_y.get(j) * (1 + i)) % 1000000007;
				j += 1;
			} else {
				if (i > j) {
					// Cut x
					answer += (cost_x.get(i) * (1 + j) % 1000000007);
					i += 1;
				} else if (i < j) {
					// Cut y
					answer += (cost_y.get(j) * (1 + i)) % 1000000007;
					j += 1;
				} else {
					// Cut x
					answer += (cost_x.get(i) * (1 + j) % 1000000007);
					i += 1;
					// Cut y
					answer += (cost_y.get(j) * (1 + i)) % 1000000007;
					j += 1;
				}
			}
		}
		while (i < m - 1) {
			// Cut x
			answer += (cost_x.get(i) * (1 + j) % 1000000007);
			i += 1;
		}
		while (j < n - 1) {
			// Cut y
			answer += (cost_y.get(j) * (1 + i)) % 1000000007;
			j += 1;
		}

		return answer;
	}

	public static int boardCutting(List<Integer> cost_y, List<Integer> cost_x, int m, int n) {
		cost_x.sort(Comparator.comparingInt(x -> -x));
		cost_y.sort(Comparator.comparingInt(x -> -x));
		int answer = 0;

		int i = 0, j = 0;
		while (i < m - 1 && j < n - 1) {
			if (cost_x.get(i)*Math.max(1, j) > cost_y.get(j)*Math.max(1, i)) {
				// Cut x
				answer += (cost_x.get(i) * (1 + j) % 1000000007);
				i += 1;
			} else if (cost_x.get(i)*Math.max(1, j) < cost_y.get(j)*Math.max(1, i)) {
				// Cut y
				answer += (cost_y.get(j) * (1 + i)) % 1000000007;
				j += 1;
			} else if (cost_x.get(i) > cost_y.get(j)) {
				// Cut x
				answer += (cost_x.get(i) * (1 + j)) % 1000000007;
				i += 1;
			} else {
				// Cut y
				answer += (cost_y.get(j) * (1 + i)) % 1000000007;
				j += 1;
			}
		}
		while (i < m - 1) {
			// Cut x
			answer += (cost_x.get(i) * (1 + j)) % 1000000007;
			i += 1;
		}
		while (j < n - 1) {
			// Cut y
			answer += (cost_y.get(j) * (1 + i)) & 1000000007;
			j += 1;
		}
		return answer;
	}
}
