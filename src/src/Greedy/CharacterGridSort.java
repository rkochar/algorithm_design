package Greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CharacterGridSort {

	/**
	 * Complete the 'gridChallenge' function below.
	 *
	 * The function is expected to return a STRING.
	 * The function accepts STRING_ARRAY grid as parameter.
	 */
	public String characterGridSort(List<String> grid) {
		grid.add("eabcd");
		grid.add("fghij");
		grid.add("olkmn");
		grid.add("trpqs");
		grid.add("xywuv");

		for (int i = 0; i < grid.size(); i ++) {

			char[] chars = grid.get(i).toCharArray();
			Arrays.sort(chars);
			grid.set(i, new String(chars));
		}


		for (int i = 0; i < grid.get(0).length(); i ++) {
			for (int j = 1; j < grid.size(); j ++) {
				int a = grid.get(j - 1).charAt(i);
				int b = grid.get(j).charAt(i);
				if (a > b) return "NO";
			}
		}
		return "YES";
	}

	public static void main(String[] args) {
		CharacterGridSort characterGridSort = new CharacterGridSort();
		System.out.println(characterGridSort.characterGridSort(new LinkedList<>()));
	}

//
//	    for (int i = 0; i < grid.length; i++) {
//			char[] chars = grid[i].toCharArray();
//			Arrays.sort(chars);
//			grid[i] = new String(chars);
//
//
//			if ( i != 0 && grid[i].compareTo(grid[i-1]) < 0 ) return "NO";
//		}
//    return "YES";
//	}
}
