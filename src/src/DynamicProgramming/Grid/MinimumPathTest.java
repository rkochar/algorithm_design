package DynamicProgramming.Grid;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MinimumPathTest {

	@Test
	public void minimumTotal() {
		List<List<Integer>> list = new ArrayList<>();
		list.add(Arrays.asList(2));
		list.add(Arrays.asList(3, 4));
		list.add(Arrays.asList(6, 5, 7));
		list.add(Arrays.asList(4, 1, 8, 3));
		assertEquals(11, new MinimumPath().minimumTotal(list));
	}
}