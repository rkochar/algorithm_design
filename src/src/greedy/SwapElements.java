package greedy;

import java.util.List;

/**
 * Swap elements of list so that k largest elements come to left side and others preserve order.
 * Not max efficiency for some reason.
 */
public class SwapElements {

	public static List<Integer> largestPermutation(int k, List<Integer> arr) {
		int n = arr.size(), swap = 0;
		for (int i = 0; i < n && swap < k; i ++) {
			if (arr.get(i) < n - i) {
				int max = Integer.MIN_VALUE, index = -1;
				for (int j = i; j < n; j++) {
					if (max < arr.get(j)) {
						max = arr.get(j);
						index = j;
					}
				}
				if (index != -1 && arr.get(i) < max) {
					int tmp = arr.get(i);
					arr.add(i, arr.get(index));
					arr.add(index + 1, tmp);
					arr.remove(i + 1);
					arr.remove(index + 1);
					swap += 1;
				}
			}
		}
		return arr;
	}
}

/**
 * def largestPermutation(k, arr, n):
 *     d = {}
 *     i = 0
 *     while i<n:
 *         d[arr[i]] = i
 *         i+=1
 *     i=0
 *     print(d)
 *     while n>0 and k>0:
 *         if arr[i]<n:
 *             tmp = d[n]
 *             arr[d[n]] = arr[i]
 *             arr[i] = n
 *             tmp = d[arr[d[n]]]
 *             d[arr[d[n]]] = d[n]
 *             d[n] = tmp
 *             k-=1
 *         n-=1
 *         i+=1
 *     return arr
 */