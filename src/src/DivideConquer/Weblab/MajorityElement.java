package DivideConquer.Weblab;

public class MajorityElement {

	public int majorityElement(int[] nums) {
		return majorityElement(nums, 0, nums.length - 1);
	}

	private int majorityElement(int[] nums, int left, int right) {
		if (left == right) return nums[left];

		int mid = (right + left) / 2;
		int leftElement = majorityElement(nums, left, mid);
		int rightElement = majorityElement(nums, mid + 1, right);

		if (leftElement == rightElement) return leftElement;
		else {
			int leftCount = countInRange(nums, leftElement, left, mid);
			int rightCount = countInRange(nums, rightElement, mid + 1, right);
			return leftCount > rightCount ? leftElement : rightElement;
		}
	}

	private int countInRange(int[] nums, int num, int lo, int hi) {
		int count = 0;
		for (int i = lo; i <= hi; i++) {
			if (nums[i] == num) {
				count++;
			}
		}
		return count;
	}
}
