package DynamicProgramming.TwoString;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteOperationsToEqualizeTest {

	@Test
	public void minDistance() {
		assertEquals(2, new DeleteOperationsToEqualize().minDistance("a", "b"));
	}
}