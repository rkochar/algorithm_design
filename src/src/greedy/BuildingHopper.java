package greedy;

import java.util.List;
import java.util.ListIterator;

/**
 * Given a list of building heights, find a minimum energy needed by calculating heights in reverse.
 */
public class BuildingHopper {

	public static int chiefHopper(List<Integer> arr) {
		ListIterator<Integer> li = arr.listIterator(arr.size());
		int energy = 0;
		while (li.hasPrevious()) {
			energy = (energy + li.previous() + 1)/2;
		}
		return energy;
	}
}
