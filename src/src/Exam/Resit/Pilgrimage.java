package Exam.Resit;

import java.io.*;
import java.util.*;

/**
 * There is a famous pilgrimage route from A to B, which many pilgrims want to walk.
 * However, the route is more than a thousand kilometers long, so many pilgrims only walk a fragment at a time.
 * These route fragments all start and end at a location that can be reached by public transport.
 * But note that the fragments overlap and not all end points are also starting points.
 *
 * We are given a set of n of these fragments, and for each fragment i the distance of the start ai and end bi location from A, in two arrays a and b,
 * respectively. Our goal is to support every pilgrim walking a fragment. For this we want to setup support posts at a minimum number of locations.
 *
 * Implement the function providingSupport which returns a set of locations (distances from A) at which to place a post so that 1)
 * there is a post along each route fragment and 2) the number of these posts is minimised.
 * Note that these locations are inclusive, meaning if a route fragment starts or ends at a location x
 * and the post is placed at x we assume that all pilgrims of this route fragment can make use of the post.
 */
class Pilgrimage {

	/**
	 *  You should implement this method.
	 *  @param n the number of route fragments.
	 *  @param a an array of size n+1, containing the start locations of the fragments, a_1 through a_n. You should ignore a[0].
	 *  @param b an array of size n+1, containing the end locations of the fragments, b_1 through b_n. You should ignore b[0].
	 *  @return A smallest set of locations at which to place the posts to ensure overlap with each fragment.
	 */
	public static Set<Integer> providingSupport(int n, int[] a, int[] b) {
		return solveProper(n, a, b);
		// return solveSortS(n, a, b);
		// return solveOnlySortF(n, a, b);
		// return solveProper(n, a, b);
	}

	public static Set<Integer> solveOnlySortF(int n, int[] s, int[] f) {
		Arrays.sort(f, 1, n + 1);
		Set<Integer> checks = new HashSet<>();
		int last_check = 0;
		for (int i = 0; i < n; i++) {
			if (last_check < s[i]) {
				last_check = f[i];
				checks.add(last_check);
			}
		}
		return checks;
	}

	public static Set<Integer> solveSortS(int n, int[] c, int[] t) {
		Job[] jobs = new Job[n];
		for (int i = 1; i <= n; i++) {
			jobs[i - 1] = new Job(c[i], t[i]);
		}
		Arrays.sort(jobs, new Comparator<Job>() {

			@Override
			public int compare(Job o1, Job o2) {
				return Integer.compare(o1.s, o2.s);
			}
		});
		Set<Integer> checks = new HashSet<>();
		int last_check = -1;
		for (int i = 0; i < n; i++) {
			if (last_check < jobs[i].s) {
				last_check = jobs[i].f;
				checks.add(last_check);
			}
		}
		return checks;
	}

	public static Set<Integer> solveProper(int n, int[] c, int[] t) {
		Job[] jobs = new Job[n];
		for (int i = 1; i <= n; i++) {
			jobs[i - 1] = new Job(c[i], t[i]);
		}
		Arrays.sort(jobs);
		Set<Integer> checks = new HashSet<>();
		int last_check = -1;
		for (int i = 0; i < n; i++) {
			if (last_check < jobs[i].s) {
				last_check = jobs[i].f;
				checks.add(last_check);
			}
		}
		return checks;
	}

	private static class Job implements Comparable<Job> {

		int s;

		int f;

		public Job(int s, int f) {
			this.s = s;
			this.f = f;
		}

		public int compareTo(Job job) {
			int x = Integer.compare(this.f, job.f);
			return x;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String dirName = "src/main/java/adweblab/exams/resit_2020_2021/bbb_greedy/implementation/intercom/data/secret/";
		File dir = new File(dirName);
		System.out.println(dir.exists());
		for (File f : dir.listFiles()) {
			if (f.getName().endsWith("in")) {
				FileInputStream in = new FileInputStream(f);
				System.out.println(f.getAbsolutePath());
				String ans = run(in);
				System.out.println(ans);
				PrintWriter out = new PrintWriter(f.getAbsolutePath().replace(".in", ".out"));
				out.println(ans);
				out.close();
				System.out.println();
			}
		}
	}

	public static String run(InputStream in) {
		Scanner sc = new Scanner(in);
		int n = sc.nextInt();
		int[] t = new int[n + 1];
		int[] p = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			t[i] = sc.nextInt();
			p[i] = sc.nextInt();
		}
		sc.close();
		StringBuilder sb = new StringBuilder();
		sb.append(Pilgrimage.solveProper(n, t, p).size());
		return sb.toString();
	}
}
