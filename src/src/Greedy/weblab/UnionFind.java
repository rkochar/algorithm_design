package Greedy.weblab;

class UnionFind {

	private int[] parent;

	private int[] rank;

	// Union Find structure implemented with two arrays for Union by Rank
	public UnionFind(int size) {
		parent = new int[size];
		rank = new int[size];
		for (int i = 0; i < size; i++) parent[i] = i;
	}

	/**
	 * Merge two subtrees if they have a different parent, input is array indices
	 * @param i a node in the first subtree
	 * @param j a node in the second subtree
	 * @return true iff i and j had different parents.
	 */
	boolean union(int i, int j) {
		// TODO
		int x = find(i);
		int y = find(j);

		if (x == y) return false;
		if (parent[x] > parent[y]) parent[y] = x;
		else if (parent[x] < parent[y]) parent[x] = y;
		else {
			parent[y] = x;
			rank[x] ++;
		}
		return true;
	}

	/**
	 * NB: this function should also do path compression
	 * @param i index of a node
	 * @return the root of the subtree containing i.
	 */
	int find(int i) {
		// TODO
		if (parent[i] != i) parent[i] = find(parent[i]);
		return parent[i];
	}

	// Return the rank of the trees
	public int[] getRank() {
		return rank;
	}

	// Return the parent of the trees
	public int[] getParent() {
		return parent;
	}
}
