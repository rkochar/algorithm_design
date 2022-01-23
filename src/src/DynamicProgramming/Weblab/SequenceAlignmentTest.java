package DynamicProgramming.Weblab;

import org.junit.Test;

import static org.junit.Assert.*;

public class SequenceAlignmentTest {

	@Test
	public void sequenceAlignment() {
		assertEquals(3, SequenceAlignment.sequenceAlignment("kitten", "sitting"));
	}
}