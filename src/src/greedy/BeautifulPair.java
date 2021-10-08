package greedy;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/**
 * For two lists of numbers, change one number one second array to maximize common numbers (beautiful pairs).
 */

public class BeautifulPair {

	public static int beautifulPairs(List<Integer> A, List<Integer> B) {
		A.sort(Comparator.comparingInt(x -> x));
		B.sort(Comparator.comparingInt(x -> x));
		int n = A.size(), count = 0;
		ListIterator<Integer> a = A.listIterator();
		ListIterator<Integer> b = B.listIterator();
		int a1 = a.next();
		int b1 = b.next();

		while (a.hasNext() || b.hasNext()) {
			if (a1 == b1) {
				count ++;
				a1 = a.next();
				b1 = b.next();
			}
			else {
				while (a1 < b1) {
					if (a.hasNext()) {
						a1 = a.next();
					} else {
						break;
					}
				}
				while (b1 < a1) {
					if (b.hasNext()) {
						b1 = b.next();
					} else {
						break;
					}
				}
			}
		}
		if (a1 == b1) count ++;
		System.out.println("Count " + count);
		if (count < n) return count + 1;
		else return count - 1;
	}

	/**
	 *     int N=A.size(),count=0;
	 *     for(int i=0,j=0;i<N && j<N;i++,j++)
	 *     {
	 *         while(j<N && A[i]!=B[j]){
	 *             if(A[i]>B[j])
	 *             j++;
	 *             while(i<N && A[i]<B[j])
	 *             i++;
	 *         }
	 *         if(A[i]==B[j])
	 *             count++;
	 *     }
	 *     if(count<N)
	 *     return count+1;
	 *     else        //count == N i.e., no duplicates present
	 *     return count-1;
	 */
}
