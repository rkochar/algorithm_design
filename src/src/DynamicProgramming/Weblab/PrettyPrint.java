package DynamicProgramming.Weblab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class PrettyPrint {


	public static List<String> solve(InputStream is) {
		// TODO

		ArrayList<String> list = new ArrayList<>();

		Scanner sc = new Scanner(is);

		int L = sc.nextInt();
		int n = sc.nextInt();


		for(int i = 0; i < n; i++){
			list.add(sc.next());
		}


		int[][] slack = slack(n, L, list);


		int[] dp = mem(slack,n);


		List<String> l = new ArrayList<>();
		recoverSolution(dp, slack, n, list, l);

		return l;

	}

	static void recoverSolution(int[]dp, int[][]slacks, int j, List<String>words, List<String> ans){

		if(j==0) return;
		int printVal = j;
		int curVal = Integer.MAX_VALUE;

		for (int i = 1; i <= j; i++) {
			if (slacks[i - 1][j - 1] == Integer.MAX_VALUE) {
				// skip max values due to overflows when squaring.
				continue;
			}
			int squared = slacks[i - 1][j - 1] * slacks[i - 1][j - 1];
			// Find the current best for this line
			if (squared + dp[i - 1] < curVal) {
				curVal = squared + dp[i - 1];
				printVal = i;
			}
		}

		recoverSolution(dp, slacks,printVal-1, words, ans);

		String res = String.join(" ", words.subList(printVal - 1, j));


		ans.add(res);
	}



	// static List<String> recoverSolution(int j, int[] opt, int[][] slacks, List<String> words){

	//     if (j == 0) {
	//         return new ArrayList<String>();
	//     }
	//     // Keep track of current best value
	//     int printVal = 1;
	//     int curVal = Integer.MAX_VALUE;
	//     for (int i = 1; i <= j; i++) {
	//         if (slacks[i - 1][j - 1] == Integer.MAX_VALUE) {
	//             // skip max values due to overflows when squaring.
	//             continue;
	//         }
	//         int squared = slacks[i - 1][j - 1] * slacks[i - 1][j - 1];
	//         // Find the current best for this line
	//         if (squared + opt[i - 1] < curVal) {
	//             curVal = squared + opt[i - 1];
	//             printVal = i;
	//         }
	//     }
	//     // Recursively traverse the words, start with the last word of the previous sentence
	//     List<String> result = recoverSolution(printVal - 1, opt, slacks, words);
	//     // Build this line
	//     String res = String.join(" ", words.subList(printVal - 1, j));
	//     result.add(res);
	//     return result;
	// }

	static int[] mem(int[][] slacks, int n) {
		int[] opt = new int[n + 1];
		opt[0] = 0;
		for (int j = 1; j <= n; j++) {


			opt[j] =  Integer.MAX_VALUE;


			for (int i = 1; i <= j; i++) {
				if (slacks[i - 1][j - 1] == Integer.MAX_VALUE) {
					// skip max values due to overflows when squaring.
					continue;
				}


				opt[j] = Math.min(opt[i - 1] + slacks[i - 1][j - 1] * slacks[i - 1][j - 1] , opt[j]);

			}
		}
		return opt;
	}


	static int[][] slack(int n, int L,List<String> list){

		int slackArr[][] = new int[n][n];
		for(int i = 0; i < n; i++) {
			int sum = -1;
			for (int j = i; j < n; j++) {
				sum += list.get(j).length() + 1;
				if(sum > L) {
					slackArr[i][j] = Integer.MAX_VALUE;
				}
				else{
					slackArr[i][j] = L - sum;
				}
			}
		}

		return slackArr;
	}


}