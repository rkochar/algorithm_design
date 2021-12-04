package Greedy;

import java.util.*;

public class LargestValueLabelledSet {

	public static int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
		int answer = 0;
		if (numWanted == 0 || useLimit == 0) return 0;
		List<Item> items = new ArrayList<>(values.length);
		HashMap<Integer, List<Integer>> labelsUsed = new HashMap<>();
		for (int i = 0; i < values.length; i ++) items.add(new Item(labels[i], values[i]));

		items.sort(Comparator.comparingInt(x -> -x.value));

		for (Item item: items) {
			if (numWanted == 0) break;
			else if (labelsUsed.getOrDefault(item.label, new ArrayList<>()).size() >= useLimit) continue;
			else {
				numWanted --;
				answer += item.value;
				if (!labelsUsed.containsKey(item.label)) labelsUsed.put(item.label, new ArrayList<>());
				labelsUsed.getOrDefault(item.label, new ArrayList<>()).add(item.value);
			}
		}

		return answer;
	}
}

class Item {
	int label;
	int value;

	public Item(int label, int value) {
		this.label = label;
		this.value = value;
	}
}