package Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Practice {

	int[] parent;
	int[] rank;

	public Practice(int size) {
		this.parent = new int[size];
		this.rank = new int[size];
		for (int i = 0; i < size; i ++) parent[i] = i;
	}

	public boolean union(int i, int j) {
		int x = find(i);
		int y = find(j);
		if (x == y) return false;

		if (parent[x] < parent[y]) parent[x] = parent[y];
		else if (parent[x] > parent[y]) {
			parent[y] = x;
		}
		else {
			parent[y] = x;
			rank[x] ++;
		}
		return true;
	}

	public int find(int i) {
		if (parent[i] != i) parent[i] = find(parent[i]);
		return parent[i];
	}

}


