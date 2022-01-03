package DynamicProgramming;

public class WaysToMakeFairArray {

	/**
	 * Fair array has equal sum of odd and even indexed elements. Find element to delete to make array fair.
	 * Find prefix sum so that each element can be checked in constant time.
	 * @param nums
	 * @return
	 */
	public int waysToMakeFair(int[] nums) {
		int oddLength = nums.length / 2;
		int evenLength = oddLength + nums.length % 2;
		int[] prefixOdd = new int[oddLength], prefixEven = new int[evenLength];
		prefixOdd[0] = nums[1];
		prefixEven[0] = nums[0];

		for (int i = 2, o = 1, e = 1; i < nums.length; i ++) {
			if ((i & 1) == 0) {
				prefixEven[e] = prefixEven[e - 1] + nums[i];
				e ++;
			} else {
				prefixOdd[o] = prefixOdd[o - 1] + nums[i];
				o ++;
			}
		}

		for (int i = 0, o = 0, e = 0; i < nums.length; i ++) {
			// TODO: Fix calculations
			if ((i & 1) == 0) {
				if (prefixEven[e] - nums[i] + prefixOdd[prefixOdd.length - 1] - prefixOdd[o] == prefixEven[prefixEven.length - 1] - prefixEven[e] + prefixOdd[o]) return i;
//				e ++;
			} else {
				if (prefixOdd[o] - nums[i] + prefixEven[prefixEven.length - 1] - prefixEven[e] == prefixOdd[prefixOdd.length - 1] - prefixOdd[o] + prefixOdd[o]) return i;
				o ++;
				e ++;
			}
		}

		return -1;
	}
}
