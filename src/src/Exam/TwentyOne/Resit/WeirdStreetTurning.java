package Exam.TwentyOne.Resit;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class WeirdStreetTurning {

	/**
	 * You should implement this method.
	 *
	 * @param n the number of unique endpoints.
	 * @param m the number of edges.
	 * @param E a set of directed roads, you may assume the endpoints are labelled 1 <= label <= n.
	 * @param s the start point (1 <= s <= n).
	 * @param t the end point (1 <= t <= n).
	 * @return the number of shortest paths from s to t.
	 */
	public static int solve(int n, int m, Set<Streets> E, int s, int t) {
		return solveProper(n, E, s, t);
	}

	public static int solveProper(int n, Set<Streets> E, int s, int t) {
		int[][] mem = new int[n + 1][n + 1];
		int[][] numroutes = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				mem[i][j] = Integer.MAX_VALUE / 2;
			}
			mem[i][t] = 0;
			numroutes[i][t] = 1;
		}
		for (int i = 1; i <= n; i++) {
			for (Streets e : E) {
				int cost = e.weight + mem[i - 1][e.to];
				if (cost < mem[i][e.from]) {
					mem[i][e.from] = cost;
				}
			}
			for (Streets e : E) {
				int cost = e.weight + mem[i - 1][e.to];
				if (cost == mem[i][e.from]) {
					numroutes[i][e.from] += numroutes[i - 1][e.to];
				}
			}
		}
		return numroutes[n][s];
	}

	public static void main(String[] args) throws FileNotFoundException {
		String dirName = "src/main/java/adweblab/exams/resit_2020_2021/eee_dp/implementation/boardgame/data/secret";
		File dir = new File(dirName);
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
		int m = sc.nextInt();
		int s = sc.nextInt();
		int t = sc.nextInt();
		Set<Streets> E = new HashSet<>();
		for (int i = 0; i < m; i++) {
			E.add(new Streets(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		sc.close();
		return Integer.toString(WeirdStreetTurning.solve(n, m, E, s, t));
	}
}

class Streets {

	protected int weight;

	protected int from;

	protected int to;

	protected Streets(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Streets edge = (Streets) o;
		return weight == edge.weight && from == edge.from && to == edge.to;
	}

	@Override
	public int hashCode() {
		return Objects.hash(weight, from, to);
	}
}
