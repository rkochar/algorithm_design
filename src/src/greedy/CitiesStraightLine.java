package greedy;

import java.util.List;

public class CitiesStraightLine {

	public static int pylons(int k, List<Integer> arr) {
		int n = arr.size();
		if (k < 1 || n < 1 || n == 1 && arr.get(0) == 0) return -1;

		int answer = 0, i = 0;
		while (i < n - k) {
			int start = i;
			for (int j = i + k - 1; j > i - k && j >= 0; j --) {
				if (arr.get(j) == 1) {
					answer += 1;
					i = j + k;
				}
			}
			if (start == i) return -1;
		}
		while (i <= n - 1) {
			if (arr.get(i) == 1) {
				answer += 1;
				i += k;
			}
			i ++;
		}
		return answer;
	}
}
