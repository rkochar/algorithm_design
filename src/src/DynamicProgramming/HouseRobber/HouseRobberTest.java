package DynamicProgramming.HouseRobber;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HouseRobberTest {

	@Test
	public void deleteAndEarn() {
		int[] a = {3, 4, 2};
		assertEquals(6, new HouseRobber().deleteAndEarn(a));
	}
}