package Greedy.weblab;

import org.junit.Test;

import static org.junit.Assert.*;

public class WirelessAntennaTest {

	@Test
	public void setUpTheNetwork() {
		int n = 7;
		int m = 6;
		int b = 3;
		Edge[] edges = new Edge[m + 1];
		edges[1] = new Edge(0, 1, 1);
		edges[2] = new Edge(1, 2, 2);
		edges[3] = new Edge(2, 3, 3);
		edges[4] = new Edge(3, 4, 1);
		edges[5] = new Edge(4, 5, 2);
		edges[6] = new Edge(5, 6, 1);
		WirelessAntenna sol = WirelessAntenna.setUpTheNetwork(n, m, b, edges);
		assertEquals(10, sol.cost);
		assertEquals(3, sol.number);
	}

	@Test
	public void setUpTheNetwork1() {
		int n = 4;
		int m = 5;
		int b = 8;
		Edge[] edges = new Edge[m + 1];
		edges[1] = new Edge(0, 1, 6);
		edges[2] = new Edge(1, 2, 9);
		edges[3] = new Edge(0, 2, 7);
		edges[4] = new Edge(1, 3, 2);
		edges[5] = new Edge(0, 3, 8);
		WirelessAntenna sol = WirelessAntenna.setUpTheNetwork(n, m, b, edges);
		assertEquals(15, sol.cost);
		assertEquals(2, sol.number);
	}
}