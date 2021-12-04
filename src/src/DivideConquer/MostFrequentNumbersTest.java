package DivideConquer;

import org.junit.Test;

import static org.junit.Assert.*;

public class MostFrequentNumbersTest {

	@Test
	public void topKFrequent() {
		int[] a = {1,1,1,2,2,3};
		int[] answer = {1, 2};
		assertArrayEquals(answer, MostFrequentNumbers.topKFrequentA(a, 2));
	}
}