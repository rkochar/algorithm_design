package DynamicProgramming.SlidingWindow;

public class DeletionsAndFlips {

	/**
	 * Return max consecutive 1s after deleting ONE element (have to delete).
	 *
	 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
	 *
	 * @param nums - list of 0 and 1
	 * @return
	 */
	public int longestSubarrayOneDeletion(int[] nums) {
		int slow = 0, fast, count = 1;
		for (fast = 0; fast < nums.length; fast ++) {
			if (nums[fast] == 0) count--;
			if (count < 0 && nums[slow++] == 0) count++;
		}
		return fast - slow - 1; // Must delete at least one element
	}

	/**
	 * Return max consecutive 1s after flipping AT MOST k elements.
	 *
	 * https://leetcode.com/problems/max-consecutive-ones-iii/
	 *
	 * @param nums list of 0 and 1
	 * @param k numbers to delete
	 * @return largest consecutive 1s
	 */
	public int longestOnesKflips(int[] nums, int k) {
		int slow = 0, fast = 0, max = Integer.MIN_VALUE;
		for (; fast < nums.length; fast ++) {
			if (nums[fast] == 0) k --;
			if (k < 0) {
				max = Integer.max(max, fast - slow);
				while (k < 0 && slow < nums.length) {
					if (nums[slow ++] == 0) k ++;
				}
			}
		}

		return Integer.max(max, fast - slow);
	}

	/**
	 * Given a string containing upper case alphabets, replace any k of them and return largest subsequence of a single character.
	 * Maintain frequency map of window and for every window find most frequent element in O(26).
	 * This can be optimized with the fact that ans changes iff a new maxf has been found.
	 * Every window must be valid and TRICK: WINDOW of size ANSWER.
	 *
	 * @param s string
	 * @param k number of replacements
	 * @return largest sequence
	 */
	public int characterReplacementKflips(String s, int k) {
		int[] frequency = new int[26];
		int answer = 0, maxf = 0;

		for (int i = 0; i < s.length(); i ++) {
			maxf = Integer.max(maxf, ++frequency[s.charAt(i) - 'A']);
			if (answer - maxf < k) answer ++;
			else frequency[s.charAt(i - answer) - 'A'] --;
		}

		return answer;
	}

	/**
	 * Same as before, longest consecutive string of T or F. Try do to strict O(n) with Kadane.
	 * Don't think Kadane works when longest subsequence of a certain character is asked.
	 *
	 * https://leetcode.com/problems/maximize-the-confusion-of-an-exam/
	 *
	 * @param answerKey string of T and F
	 * @param k number of flips
	 * @return length of largest sequence of consecutive T or F.
	 */
	public int maxConsecutiveAnswers(String answerKey, int k) {
		int answer = 0, l = answerKey.length();
		int[] frequency = {0, 0};

		for (int i = 0; i < l; i ++) {
			if (answerKey.charAt(i) == 'T') frequency[0] ++;
			else frequency[1] ++;
			if (answer - Integer.max(frequency[0], frequency[1]) < k) answer ++;
			else {
				if (answerKey.charAt(i - answer) == 'T') frequency[0] --;
				else frequency[1] --;
			}
		}

		return answer;
	}

	/**
	 * Find longest subarray made by two characters.
	 *
	 * https://leetcode.com/problems/fruit-into-baskets/
	 *
	 * @param fruits
	 * @return
	 */
	public int totalFruit(int[] fruits) {
		int lastFruit = 0, secondLastFruit = 0, answer = Integer.MIN_VALUE, lastFruitCount = 0, tmp = 0;

		for (int fruit : fruits) {
			if (fruit == lastFruit || fruit == secondLastFruit) {
				tmp ++;
			} else {
				lastFruitCount
			}
		}
	}
}
