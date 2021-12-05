package DivideConquer.Weblab;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CountInversionsTest {

	@Test
	public void countInversionsMergedArray() {
		int[] c = {2, 4, 1, 3, 5, 1, 2, 3, 4, 5};
		assertEquals(13, CountInversions.countInversions(c));
	}

	@Test
	public void countInversionsWeblab() {
		int[] c = {2, 1, 0, 8};
		assertEquals(3, CountInversions.countInversions(c));
	}

	@Test
	public void countInversions1() {
		int[] c = {4, 1, 2, 3};
		assertEquals(3, CountInversions.countInversions(c));
	}

	@Test
	public void countInversions2() {
		int[] c = {5, 3, 2, 4, 1};
		assertEquals(8, CountInversions.countInversions(c));
	}
}
