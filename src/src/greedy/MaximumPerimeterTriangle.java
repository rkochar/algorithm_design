package greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaximumPerimeterTriangle {

	public static List<Integer> maximumPerimeterTriangle(List<Integer> sticks) {
		sticks.sort(Comparator.comparingInt(x -> x));
		int n = sticks.size();
		int i = n - 3;
		while (i >= 0 && sticks.get(i) + sticks.get(i + 1) <= sticks.get(i + 2)) {
			i -= 1;
		}
		if (i >= 0) {
			List<Integer> ans = new ArrayList<>(3);
			ans.add(sticks.get(i));
			ans.add(sticks.get(i + 1));
			ans.add(sticks.get(i + 2));
			return ans;
		} else {
			List<Integer> ans = new ArrayList<>(1);
			ans.add(-1);
			return ans;
		}
	}
}
