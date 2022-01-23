package DynamicProgramming;

import java.util.*;

public class DynamicProgramming {

	public int minSubArrayLen(int target, int[] nums) {
		int slow = 0, fast = 0, answer = Integer.MAX_VALUE, tmp = 0;
		for (; fast < nums.length; fast ++) {
			tmp += nums[fast];
			while (tmp >= target) {
				answer = Integer.min(answer, fast - slow + 1);
				tmp -= nums[slow ++];
			}
		}

		return answer;
	}

	/**
	 * Pick a number from either side and return score of first player if both play optimally.
	 * [i][j]f = max([i+1][j]s + v[i], [i][j-1]s + v[j])
	 * [i][j]s =  max([i+1][j]f, [i][j-1]f)
	 * @param numbers
	 * @return
	 */
	public int pickEitherSide(int[] numbers) {
		return 0;
	}

	/**
	 * Recovers the matches from a 1-1 bipartite matching problem
	 *
	 * @param graph the graph on which maximum matching algorithm was applied
	 * @return a set of matches recovered
	 */
//	public static Set<Match> recoverMatches(Graph graph) {
//		Set<Match> matches = new HashSet<>();
//		for (Edge edge : graph.getSource().getEdges()) {
//			if (edge.flow == 1) {
//				Node x = edge.to;
//				for (Edge edgeFromX : x.getEdges()) if (edgeFromX.flow == 1 && edgeFromX.to != graph.getSource())
//					matches.add(new Match(x.id, edgeFromX.to.id));
//			}
//		}
//		return matches;
//	}
}
