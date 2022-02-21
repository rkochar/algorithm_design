package DynamicProgramming;

import org.junit.Test;

import static org.junit.Assert.*;

public class GreedyStocksTest {

	@Test
	public void maxProfitKtransactions() {
		int[] prices = {3, 2, 6, 5, 0, 3};
		assertEquals(7, new GreedyStocks().maxProfitKtransactions(2, prices));
	}

	@Test
	public void maxProfitKtransactions1() {
		int[] prices = {2, 4, 1};
		assertEquals(2, new GreedyStocks().maxProfitKtransactions(2, prices));
	}

	@Test
	public void maxProfitKtransactions2() {
		int[] prices = {1, 2};
		assertEquals(1, new GreedyStocks().maxProfitKtransactions(1, prices));
	}

	@Test
	public void maxProfitKtransactionsBest() {
		int[] prices = {3, 2, 6, 5, 0, 3};
		assertEquals(7, new GreedyStocks().maxProfit(2, prices));
	}

	@Test
	public void maxProfitKtransactions1Best() {
		int[] prices = {2, 4, 1};
		assertEquals(2, new GreedyStocks().maxProfit(2, prices));
	}

	@Test
	public void maxProfitKtransactions2Best() {
		int[] prices = {1, 2};
		assertEquals(1, new GreedyStocks().maxProfit(1, prices));
	}
}