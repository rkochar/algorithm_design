package greedy;

public class DecentNumber {

	public static void decentNumber(int n) {
		int y = n, z = n;
		while (z % 3 != 0) z -= 5;
		if (z < 0) {
			System.out.println("-1");
			return;
		} else {
			String answer = "";
			int fives = z / 3;
			int threes = (y - z) / 5;
			for (int i = 0; i < fives; i++) answer += "555";
			for (int i = 0; i < threes; i++) answer += "33333";
			System.out.println(answer);
			return;
		}
	}
}