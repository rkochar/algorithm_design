package Exam.TwentyOne.Midterm;


import static org.junit.Assert.assertEquals;
import java.util.*;
import org.junit.*;
public class CatchAndThrowTest {

	@Test(timeout = 100)
	public void exampleOneFriend() {
		int n = 2;
		Set<Catch> catches = new HashSet<>();
		catches.add(new Catch(1, 2, 0.8));
		// There is only one catch we can use, which has a catch rate of 0.8
		assertEquals(0.8, CatchAndThrow.minimalLargestDropChance(n, catches), 1E-6);
	}

	@Test(timeout = 100)
	public void exampleTwoFriends() {
		int n = 3;
		Set<Catch> catches = new HashSet<>();
		catches.add(new Catch(1, 2, 0.8));
		catches.add(new Catch(1, 3, 0.7));
		// There is only one catch we can use to each
		// For friend 2 a catch rate of 0.8
		// For friend 3 a catch rate of 0.7
		// So the hardest to reach is 3, with 0.7
		assertEquals(0.7, CatchAndThrow.minimalLargestDropChance(n, catches), 1E-6);
	}

	@Test(timeout = 100)
	public void exampleTwoFriendsIndirect() {
		int n = 4;
		Set<Catch> catches = new HashSet<>();
		catches.add(new Catch(1, 2, 0.6));
		catches.add(new Catch(1, 3, 0.4));
		catches.add(new Catch(2, 3, 0.7));
		catches.add(new Catch(1, 4, 0.8));
		// For Friend 2 a catch rate of 0.6
		// For Friend 3 a catch rate of 0.4 if we go directly, but we can go via friend 2 for a catch rate of 0.42
		// For Friend 4 a catch rate of 0.8
		// So the hardest to reach is 3 with 0.42
		assertEquals(0.42, CatchAndThrow.minimalLargestDropChance(n, catches), 1E-6);
	}

}