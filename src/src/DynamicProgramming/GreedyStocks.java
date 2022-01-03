package DynamicProgramming;

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
	 * Max profit, at most one transaction.
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/submissions/   
	 *
	 * @param prices - array of prices
	 * @return max profit
	 */
	public int atmostOneTransactions(int[] prices) {
		int minPrice = Integer.MAX_VALUE, profit = 0;

		for (int i = 0; i < prices.length; i ++) {
			if (minPrice > prices[i]) {
				minPrice = prices[i];
			} else {
				profit = Integer.max(profit, prices[i] - minPrice);
			}

		}

		return profit;
	}

	/**
	 * Return max profit with at most two trades.
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/submissions/
	 *
	 * @param prices - of stock
	 * @return max profit
	 */
	public int atmostTwoTransactions(int[] prices) {
		int buy1 = -prices[0], buy2 = -prices[0];
		int profit = 0, totalProfit = 0 ;

		for(int price : prices) {
			buy1 = Math.max(buy1, -price);
			profit = Math.max(profit, price + buy1);

			buy2 = Math.max(buy2, profit - price);
			totalProfit = Math.max(totalProfit, price + buy2);
		}
		return totalProfit;
	}

	/**
	 * Atmost k transactions (dp)
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/submissions/
	 *
	 * @param k - number of transactions
	 * @param prices - array of prices
	 * @return max profit
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
			for (int j = 1; j < k; j++) { // The i-th time buy & sell depends on the (i - 1)-th time buy & sell
				buy[j] = Math.max(buy[j], sell[j - 1] - i);
				sell[j] = Math.max(sell[j], buy[j] + i);
			}
		}
		return sell[k - 1];
	}

	/**
	 * Pick a straight subsequence and add even index, subtract odd index. Maximize.
	 * First attempt, fisherman. Linear space. Either skip, or add/subtract element (put it at even or odd index).
	 *
	 * https://leetcode.com/problems/maximum-alternating-subsequence-sum/
	 *
	 * @param nums - number
	 * @return maximal alternating ...
	 */
	public long maxAlternatingSum(int[] nums) {
		long[][] dp = new long[nums.length + 1][2];
		dp[0][0] = 0; // even element so
		dp[0][1] = 0; // odd element so

		for (int i = 0; i < nums.length; i ++) {
			dp[i + 1][0] = Long.max(dp[i][0], dp[i][1] + nums[i]);
			dp[i + 1][1] = Long.max(dp[i][1], dp[i][0] - nums[i]);
		}

		return dp[nums.length][0];
	}

	/**
	 * Same problem as above, constant space.
	 *
	 * Instead of choosing to pick or not to pick an element twice at each moment, choose once.
	 * If odd + a is larger, odd = odd + a - a
	 * If even is larger, even = 
	 *
	 * @param nums - numbers
	 * @return maximal alternating ...
	 */
	public long maxAlternatingSumConstantSpace(int[] nums) {
		long odd = 0, even = 0;
		for (int a: nums) {
			even = Math.max(even, odd + a);
			odd = even - a;
		}
		return even;
	}
}
