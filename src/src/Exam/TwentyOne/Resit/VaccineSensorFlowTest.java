package Exam.TwentyOne.Resit;

import org.junit.*;

public class VaccineSensorFlowTest {

	@Test()
	public void exampleOneEvent() {
		int n = 1;
		int m = 3;
		int z = 3;
		int k = 2;
		int[] t = { 0, 1, 2, 3 };
		boolean[][] c = new boolean[n + 1][z + 1];
		c[1][1] = true;
		c[1][2] = true;
		c[1][3] = true;
		/**
		 * This test models the situation where:
		 * Event 1 can be done by all sensors, which all belong to different companies.
		 * This is doable, for example by having sensor 1 and 2 measure at event 1.
		 */
		Assert.assertTrue(VaccineSensorFlow.organisingEvents(n, m, z, k, t, c));
	}

	@Test()
	public void exampleOneEventLimit() {
		int n = 1;
		int m = 1;
		int z = 3;
		int k = 3;
		int[] t = { 0, 1, 1, 1 };
		boolean[][] c = new boolean[n + 1][z + 1];
		c[1][1] = true;
		c[1][2] = true;
		c[1][3] = true;
		/**
		 * This test models the situation where:
		 * Event 1 can be done by all sensors, which all belong to _the same_ company.
		 * This is not doable, as we need at least two different companies to monitor events.
		 */
		Assert.assertFalse(VaccineSensorFlow.organisingEvents(n, m, z, k, t, c));
	}

	@Test()
	public void exampleTwoEventTooFewSensors() {
		int n = 2;
		int m = 2;
		int z = 3;
		int k = 2;
		int[] t = { 0, 1, 2, 2 };
		boolean[][] c = new boolean[n + 1][z + 1];
		c[1][1] = true;
		c[1][2] = true;
		c[1][3] = true;
		c[2][1] = true;
		c[2][2] = true;
		c[2][3] = true;
		/**
		 * This test models the situation where:
		 * Both events can be done by all sensors, but there are only 3 sensors and we need 2 per event.
		 * This is not doable.
		 */
		Assert.assertFalse(VaccineSensorFlow.organisingEvents(n, m, z, k, t, c));
	}

	@Test()
	public void exampleTwoEventTooFewCompanies() {
		int n = 2;
		int m = 2;
		int z = 4;
		int k = 2;
		int[] t = { 0, 1, 1, 2, 2 };
		boolean[][] c = new boolean[n + 1][z + 1];
		c[1][1] = true;
		c[1][2] = true;
		c[2][3] = true;
		c[2][4] = true;
		/**
		 * This test models the situation where:
		 * Event 1 can be done by sensors 1 and 2.
		 * Event 2 can be done by sensors 3 and 4.
		 * This is not doable, as each event is done by a single company!
		 */
		Assert.assertFalse(VaccineSensorFlow.organisingEvents(n, m, z, k, t, c));
	}

	@Test()
	public void exampleTwoEventDoable() {
		int n = 2;
		int m = 2;
		int z = 4;
		int k = 2;
		int[] t = { 0, 1, 2, 2, 1 };
		boolean[][] c = new boolean[n + 1][z + 1];
		c[1][1] = true;
		c[1][2] = true;
		c[2][3] = true;
		c[2][4] = true;
		/**
		 * This test models the situation where:
		 * Event 1 can be done by sensors 1 and 2.
		 * Event 2 can be done by sensors 3 and 4.
		 * This is doable, as each event can be covered by 2 sensors from two different companies.
		 */
		Assert.assertTrue(VaccineSensorFlow.organisingEvents(n, m, z, k, t, c));
	}
}