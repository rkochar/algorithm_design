package greedy;

import java.util.Arrays;

public class GreedyStocks {

	/**
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
	 *
	 * @param prices - prices of stock
	 * @param fee - of transaction
	 * @return max profit
	 */
	public static int maxProfit(int[] prices, int fee) {
		int cash = 0, hold = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			cash = Math.max(cash, hold + prices[i] - fee);
			hold = Math.max(hold, cash - prices[i]);
		}
		return cash;
	}

	/**
	 * Same as above but without transaction cost.
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
	 *
	 * @param prices -
	 * @return
	 */
	public int maxProfit(int[] prices) {
		int cash = 0, hold = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			cash = Math.max(cash, hold + prices[i]);
			hold = Math.max(hold, cash - prices[i]);
		}
		return cash;
	}

	/**
	 * Return max profit with at most two trades.
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/submissions/
	 *
	 * @param prices - of stock
	 * @return max profit
	 */
	public int atmostTwoTransactions(int[] prices) {
		int buy1 = -prices[0];
		int buy2 = -prices[0];

		int profit = 0;
		int total_profit = 0 ;

		for(int price : prices)
		{
			buy1=Math.max(buy1,-price);
			profit = Math.max(profit ,  price + buy1);

			buy2=Math.max(buy2,profit-price);
			total_profit=Math.max(total_profit,price+buy2);
		}
		return total_profit;
	}

	/**
	 * Atmost k transactions (dp)
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/submissions/
	 *
	 * @param k
	 * @param prices
	 * @return
	 */
	public int maxProfit(int k, int[] prices) {
		if (k == 0) return 0;
		int[] buy = new int[k]; // buy[i]: min cost at (i + 1)-th time stock purchased
		int[] sell = new int[k]; // sell[i]: max revenue at (i + 1)-th time stock sold
		Arrays.fill(buy, Integer.MIN_VALUE);
		Arrays.fill(sell, 0);

		for (int i : prices) {
			buy[0] = Math.max(buy[0], i * -1);
			sell[0] = Math.max(sell[0], buy[0] + i);
			for (int j = 1; j < k; j++) { // The i-th time buy & sell depens on the (i - 1)-th time buy & sell
				buy[j] = Math.max(buy[j], sell[j - 1] - i);
				sell[j] = Math.max(sell[j], buy[j] + i);
			}
		}
		return sell[k - 1];
	}
}
