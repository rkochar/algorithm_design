package DynamicProgramming;

import org.junit.Test;

import javax.swing.table.TableRowSorter;

import static org.junit.Assert.*;

public class TrainTicketsTest {

	@Test
	public void mincostTickets() {
		int[] costs = {2, 7, 15};
		int[] days = {1, 4, 6, 7, 8, 20};
		assertEquals(11, new TrainTickets().mincostTickets(days, costs));
	}
}