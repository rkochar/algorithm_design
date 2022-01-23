package DynamicProgramming;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromePartitionTest {

	@Test
	public void palindromePartition() {
		assertEquals(1, new PalindromePartition().palindromePartition("abcbaa"));
	}
}