package Greedy;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeakCharactersTest {

	@Test
	public void numberOfWeakCharacters() {
		int aa[][] = {{5, 1}, {5, 2}, {6, 3}};
		assertEquals(2, WeakCharacters.numberOfWeakCharacters(aa));
	}

	@Test
	public void numberOfWeakCharacters1() {
		int aa[][] = {{5, 1}, {5, 2}};
		assertEquals(0, WeakCharacters.numberOfWeakCharacters(aa));
	}
}