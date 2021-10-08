package greedy;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeautifulPairTest {

	@org.junit.jupiter.api.Test
	void beautifulPairs() {
		List<Integer> a = new LinkedList<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		List<Integer> b = new LinkedList<Integer>();
		b.add(1);
		b.add(2);
		b.add(3);
		b.add(3);

		assertEquals(4, BeautifulPair.beautifulPairs(a, b));
	}

	@org.junit.jupiter.api.Test
	void beautifulPairsAnother() {
		List<Integer> a = new LinkedList<Integer>();
		// 3,5,5,7,8,   11
		//   5,5,7,8,10,11
		a.add(3);
		a.add(5);
		a.add(7);
		a.add(11);
		a.add(5);
		a.add(8);
		List<Integer> b = new LinkedList<Integer>();
		b.add(5);
		b.add(7);
		b.add(11);
		b.add(10);
		b.add(5);
		b.add(8);

		assertEquals(6, BeautifulPair.beautifulPairs(a, b));
	}
}