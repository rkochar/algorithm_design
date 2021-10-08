package greedy;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinimumCandiesTest {

	@org.junit.jupiter.api.Test
	void test1() {
		List<Integer> input = new LinkedList<>();
		input.add(1);
		input.add(2);
		input.add(2);
		assertEquals(4, MinimumCandies.countMinCandies(3, input));
	}

}