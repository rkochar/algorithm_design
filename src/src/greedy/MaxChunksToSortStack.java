package greedy;

import java.util.Stack;

public class MaxChunksToSortStack {

	/**
	 * Give the highest number of already chunks in array.
	 * https://leetcode.com/problems/max-chunks-to-make-sorted/
	 *
	 * @param arr - array to be sorted by chunks
	 * @return number of chunks to make
	 */
	public static int maxChunksToSorted(int[] arr) {
		int[] max = new int[arr.length];
		int curMax = arr[0];
		// Store max element uptil then.
		for(int i = 0; i < arr.length; ++i){
			curMax = Math.max(curMax, arr[i]);
			max[i] = curMax;
		}

		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < arr.length; i++){
			// peek gives index of that max array and look at max element till to current element.
			// If it is part of the previous chunk, remove it.
			while(!stack.isEmpty() && arr[i] < max[stack.peek()]){
				stack.pop();
			}
			// Add i of for loop to stack
			stack.push(i);
		}
		return stack.size();
	}
}
