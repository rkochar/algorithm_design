package DynamicProgramming;

public class CountTeamsWithRatings {

	/**
	 * Find triplets such as rating[i] < rating[j] < rating[k] and other way around for i < j < k.
	 * answer += (leftLesser * rightGreater) + (leftGreater * rightLesser)
	 * Find number of elements lesser and greater for every element i of rating from 1 to l - 1. Start j at i - 1 and i + 1.
	 *
	 * https://leetcode.com/problems/count-number-of-teams/
	 *
	 * @param rating - array of ratings
	 * @return number of teams that can be made with strictly increasing or decreasing ratings
	 */
	public int numTeams(int[] rating) {
		int answer = 0;

		for (int i = 1; i < rating.length - 1; i ++) {
			int leftLesser = 0, leftGreater = 0, rightLesser = 0, rightGreater = 0;

			for (int j = i - 1; j >= 0; j --) {
				if (rating[i] < rating[j]) leftGreater ++;
				else leftLesser ++;
			}

			for (int j = i + 1; j < rating.length; j ++) {
				if (rating[i] > rating[j]) rightLesser ++;
				else rightGreater ++;
			}

			answer += (leftLesser * rightGreater) + (leftGreater * rightLesser);
		}

		return answer;
	}
}
