package Exam.TwentyOne.Endterm;

/**
 * WARNING: The spec tests are not necessarily equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined by a manual inspection of your implementation.
 */
public class ExamDatabase {

	/**
	 * You should implement this method.
	 *
	 * @param n the number of exams we need to create.
	 * @param q the number of questions we need per exam from index _1_ to _n_, you should ignore q[0].
	 * @param z the overhead of getting into the zone in minutes, regardless of how many questions need to be created.
	 * @param r the overhead to recall a question in minutes, measured _per_ question.
	 * @param L the maximum number of questions we can keep in reserves.
	 * @return the minimal overhead in minutes.
	 */
	public static int minimiseOverhead(int n, int[] q, int z, int r, int L) {
		// Edge cases at first row and last column.
		int[][] mem = new int[n + 1][L + 1];
		for (int i = 0; i <= n; i ++) {
			mem[i][0] = 0;
		}
		for (int i = 0; i <= L; i ++) {
			//mem[0][i] = 0;
			mem[L][i] = Integer.MAX_VALUE/2;
		}

		// Iterate from behind.
		for (int i = n; i > 0; i --) {
			for (int j = L; j > 0; j --) {
				mem[i][j] = z + Integer.max(mem[i + 1][j - q[i]], mem[i][j] * r);
			}
		}
		return mem[1][0]; // Top left non-trivial solution.
	}

	public static int solveProper(int n, int[] q, int z, int r, int L) {
		int[][] mem = new int[n + 2][L + 1];
		for (int i = n + 1; i >= 0; i--) {
			for (int j = 0; j <= L; j++) {
				if (i == n + 1 && j == 0) {
					mem[i][j] = 0;
				} else if (i == n + 1 && j > 0) {
					mem[i][j] = Integer.MAX_VALUE / 2;
				} else if (j >= q[i]) {
					mem[i][j] = mem[i + 1][j - q[i]] + r * (j - q[i]);
				} else {
					mem[i][j] = Integer.MAX_VALUE / 2;
					for (int new_total = q[i]; new_total <= L + q[i]; new_total++) {
						mem[i][j] = Math.min(mem[i][j], mem[i + 1][new_total - q[i]] + z + r * (new_total - q[i]));
					}
				}
			}
		}
		return mem[0][0];
	}
}