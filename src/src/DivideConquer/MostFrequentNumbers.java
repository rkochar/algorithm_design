package DivideConquer;

import java.security.interfaces.DSAParams;
import java.util.*;

public class MostFrequentNumbers {

	static public int[] topKFrequentA(int[] nums, int k) {
		HashMap<Integer,Integer> map = new HashMap<>();
		for (int num : nums) {
			map.putIfAbsent(num, 0);
			map.put(num, map.get(num) + 1);
		}

		ArrayList<Pair> list = new ArrayList<>();
		for(int key : map.keySet()) list.add(new Pair(key, map.get(key)));

		quickSelect(list, 0, list.size()-1, k);
		System.out.println(list);

		int [] ans = new int[k];
		for(int i = 0; i < k; i++) ans[i] = list.get(i).index;

		return ans;
	}

	static void quickSelect(ArrayList<Pair> list, int low, int high, int k) {
		int pivot = partition(list, low, high);
		if(pivot == k) return;
		if(pivot < k) quickSelect(list,pivot+1,high, k);
		else quickSelect(list,low, pivot-1, k);
		}

	static int partition(ArrayList<Pair> list, int low, int high) {
		Pair pivot = list.get(high);
		int i = low;

		for (int j = low; j <= high; j++)
			if (list.get(i).count > pivot.count) {
				Collections.swap(list, i, j);
				i++;
			}

		Collections.swap(list, i, high);
		return i;
	}
}

class Pair {
	int index;
	int count;

	public Pair(int count, int index) {
		this.index = index;
		this.count = count;
	}

	@Override
	public String toString() {
		return this.index + " : " + this.count;
	}
}