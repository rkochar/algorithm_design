package Greedy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BusiestServerTest {

	@Test
	void busiestServers() {
		int[] arrival = {1, 2, 3, 4, 5};
		int[] load = {5, 2, 3, 3, 3};
		assertEquals(Arrays.asList(1), BusiestServer.busiestServers(3, arrival, load));
	}

	@Test
	void busiestServers1() {
		int[] arrival = {1, 2, 3, 4};
		int[] load = {1, 2, 1 ,2};
		assertEquals(Arrays.asList(0), BusiestServer.busiestServers(3, arrival, load));
	}

	@Test
	void busiestServers2() {
		int[] arrival = {1, 2, 3};
		int[] load = {10, 12, 11};
		assertEquals(Arrays.asList(0, 1, 2), BusiestServer.busiestServers(3, arrival, load));
	}

	@Test
	void busiestServers3() {
		int[] arrival = {1, 2, 3, 4, 8, 9, 10};
		int[] load = {5, 2, 10, 3, 1, 2, 2};
		assertEquals(Arrays.asList(1), BusiestServer.busiestServers(3, arrival, load));
	}

	@Test
	void busiestServers4() {
		int[] arrival = {1};
		int[] load = {1};
		assertEquals(Arrays.asList(0), BusiestServer.busiestServers(1, arrival, load));
	}
}