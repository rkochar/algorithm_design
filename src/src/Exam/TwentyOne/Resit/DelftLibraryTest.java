package Exam.TwentyOne.Resit;

import java.util.*;
import org.junit.*;

public class DelftLibraryTest {

	@Test
	public void onlyTwoBooks() {
		int n = 2;
		DelftLibrary.Book[] books = new DelftLibrary.Book[n + 1];
		books[1] = new DelftLibrary.Book(0);
		books[2] = new DelftLibrary.Book(1);
		Assert.assertEquals(0, DelftLibrary.numberOfMistakes(n, books));
		books[1] = new DelftLibrary.Book(1);
		books[2] = new DelftLibrary.Book(0);
		Assert.assertEquals(1, DelftLibrary.numberOfMistakes(n, books));
	}

	@Test
	public void example() {
		int n = 20;
		DelftLibrary.Book[] books = new DelftLibrary.Book[n + 1];
		for (int i = 1; i <= n; i++) {
			books[i] = new DelftLibrary.Book(i);
		}
		// They are all in the correct place.
		Assert.assertEquals(0, DelftLibrary.numberOfMistakes(n, books));
		books[8] = new DelftLibrary.Book(20);
		// 9 through 19 all count one switch
		Assert.assertEquals(11, DelftLibrary.numberOfMistakes(n, books));
	}
}