package greedy.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LongestInterleave {

	/**
	 * Interleave a, b and c such that no character occurs thrice consecutively.
	 * https://leetcode.com/problems/longest-happy-string/submissions/
	 *
	 * @param a - a
	 * @param b - b
	 * @param c - c
	 * @return longest interleaved string
	 */
	public static String longestDiverseString(int a, int b, int c) {
		StringBuilder answer = new StringBuilder();
		if (a + b + c == 0) return answer.toString();
		Character previous = ' ';
		PriorityQueue<Letter> pq = new PriorityQueue<>(new Comparator<Letter>() {
			@Override
			public int compare(Letter letter, Letter t1) {
				return t1.number - letter.number;
			}
		});

		Letter aa = new Letter(a, 'a');
		Letter bb = new Letter(b, 'b');
		Letter cc = new Letter(c, 'c');
		if (a != 0) pq.add(aa);
		if (b != 0) pq.add(bb);
		if (c != 0) pq.add(cc);

		Letter skip = new Letter(0, ' ');
		boolean addSkiped = false;
		while (!pq.isEmpty()) {
			if (pq.peek().character == previous) {
				skip = pq.poll();
				addSkiped = true;
			}
			Letter letter = pq.poll();
			if (letter == null) break;
			previous = letter.character;

			int current = 0, others = 0, largest = 0;

			if (a > b && a > c) {
				current = aa.number;
				others = bb.number + cc.number;
				largest = a;
			} else if (b > a && b > c) {
				current = bb.number;
				others = aa.number + cc.number;
				largest = b;
			} else {
				current = cc.number;
				others = aa.number + bb.number;
				largest = c;
			}

			if (addSkiped) {
				if (skip.character == 'a') {
					largest = Math.max(b, c);
				} else if (skip.character == 'b') {
					largest = Math.max(a, c);
				} else {
					largest = Math.max(a, b);
				}
			}

			if (letter.number == 1 || (current >= others && current != largest)) {
				letter.number --;
				if (letter.character == 'a') a --;
				else if (letter.character == 'b') b --;
				else c --;
				answer.append(Character.toString(letter.character));
			} else {
				letter.number -= 2;
				answer.append(Character.toString(letter.character)).append(Character.toString(letter.character));

				if (letter.character == 'a') a -=2;
				else if (letter.character == 'b') b -=2;
				else c -=2;
			}

			if (letter.number != 0) pq.add(letter);
			if (addSkiped) {
				pq.add(skip);
				addSkiped = false;
				skip = new Letter(0, ' ');
			}
		}

		return answer.toString();
	}
}

class Letter {
	int number;
	Character character;

	public Letter(int number, Character character) {
		this.character = character;
		this.number = number;
	}
}
