package DivideConquer.Weblab;

import org.junit.Test;

import java.security.interfaces.DSAParams;
import java.util.*;

import static org.junit.Assert.*;

public class PowerSetTest {

	@Test
	public void partialSums() {
		Integer[] a = {1, 2};
		Set<Integer> answer = new HashSet<>(Arrays.asList(0, 1, 2, 3));
		assertEquals(answer, PowerSet.partialSums(a));
	}

	@Test
	public void partialSums1() {
		Integer[] a = {1, 2, 3, 4};
		Set<Integer> answer = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		assertEquals(answer, PowerSet.partialSums(a));
	}
}
