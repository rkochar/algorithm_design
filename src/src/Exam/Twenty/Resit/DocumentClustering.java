package Exam.Twenty.Resit;

public class DocumentClustering {

	/**
	 * https://weblab.tudelft.nl/cse2310/2020-2021/assignment/55775/submission/27232/
	 *
	 * You should implement this method.
	 * @param n the number of documents to sort.
	 * @param k the number of groups that they need to be sorted in.
	 * @param d 2D-array with all the dissimilarity scores, d[i][j] represents the dissimilarity score of document \\(1 \leq i \leq n\\) and
	 * document \\(1 \leq j \leq n\\). You should ignore d[0][j] and d[i][0].
	 * @param e 2D-array indicating which documents Phoenix has judged similar, e[i][j] is true iff Phoenix has determined that a document
	 * \\(1 \leq i \leq n\\) and document \\(1 \leq j \leq n\\) must be in the same group. You should ignore e[0][j] and e[i][0].
	 * @return the smallest dissimilarity score between different groups in the optimal grouping.
	 */
	public static int legalisingDocuments(int n, int k, int[][] d, boolean[][] e) {
		DocumentUnionFind uf = new DocumentUnionFind(n, e);
		int[][] selected = new int[n][n];

		int count = 0;
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < i; j++) {
				if (e[i][j]) {
					selected[i][j] = 100;
					selected[j][i] = 100;
					count ++;
				} else if (uf.union(i, j)){
					count ++;
					selected[i][j] = d[i][j];
					selected[j][i] = d[i][j];
				}
				if (count == n - 1) break;
			}
			if (count == n - 1) break;
		}

		int answer = Integer.MIN_VALUE;
		for (int i = 1; i < n; i ++) {
			for (int j = 1; j < n; j ++) {
				answer = Integer.max(answer, selected[i][j]);
			}
		}
		return answer;
	}
}

class DocumentUnionFind {

	int[] rank;
	int[] parent;

	public DocumentUnionFind(int size, boolean[][] e) {
		rank = new int[size];
		parent = new int[size];

		for (int i = 1; i < size; i ++) parent[i] = i;
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if (i != j && e[i][j]) {
					parent[j] = i;
					rank[i] ++;
				}
			}
		}
	}

	public int find(int i) {
		if (parent[i] != i) parent[i] = find(parent[i]);
		return parent[i];
	}

	public boolean union(int i, int j) {
		int x = find(i);
		int y = find(j);
		if (x == y) return false;

		if (parent[x] < parent[y]) parent[x] = y;
		else {
			parent[y] = x;
			rank[x] ++;
		}
		return true;
	}
}
