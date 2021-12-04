package Exam.TwentyOne.Resit;

public class DelftLibrary {
	/**
	 * You should implement this method.
	 * @param n the number of books
	 * @param books the books at indices 1 through n, note that you should ignore index 0!
	 * @return the number of mistakes that occurred (with book i appearing before book j, despite books[i].isbnNumber > books[j].isbnNumber)
	 */
	public static int numberOfMistakes(int n, Book[] books) {
		// Base case for 1 book (no inversions possible).
		if (n == 1) return 0;
		return helper(books, 1, n);
	}

	public static int helper(Book[] books, int low, int high) {
		int answer = 0, mid;
		if (high > low) {
			mid = (high + low) / 2;
			// Break array into subarrays (divide)
			answer = helper(books, low, mid);
			answer += helper(books, mid + 1, high);
			answer += counter(books, low, mid + 1, high);
		}
		return answer;
	}

	public static int counter(Book[] books, int low, int mid, int high) {
		// Method is to count inversions (conquer).
		// make pointers to and counter.
		int count = 0, x = low, y = mid, z = low;
		Book[] swap = new Book[books.length];
		while ((x <= mid - 1) && (y <= high)) {
			// No inversion
			if (books[x].isbnNumber <= books[y].isbnNumber)	swap[z++] = books[x++];
			else {
				// Inversion
				swap[z++] = books[y++];
				count = count + (mid - x);
			}
		}
		// Replace original elements because they have been counted. Don't want to count again later.
		while (x <= mid - 1) swap[z++] = books[x++];
		while (y <= high) swap[z++] = books[y++];
		for (x = low; x <= high; x++) books[x] = swap[x];
		return count;
	}

	static class Book {

		int isbnNumber;

		public Book(int isbnNumber) {
			this.isbnNumber = isbnNumber;
		}
	}
}
