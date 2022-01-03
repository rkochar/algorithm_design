package DynamicProgramming;

public class FibonacciStaircase {

	/**
	 * At each stair take 1 or 2 steps. Return max number of steps.
	 * mem[n] = mem[n - 1] + mem[n - 2]
	 * From n - 1, there is one way and from n - 2, there are two ways. Recurse base case.
	 * https://leetcode.com/problems/climbing-stairs/submissions/
	 *
	 * @param n - number of steps to climb
	 * @return number of possibilities
	 */
	public int climbStairs(int n) {
		if (n <= 2) return n;

		int first = 1;
		int second = 2;
		int third = 3;

		for (int i = 3; i <= n; i ++) {
			third = first + second;
			first = second;
			second = third;
		}

		return third;
	}
}
