package Greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SendingToTwoCities {

	/**
	 * Send 2n people to two cities with minimum costs, n to each city.
	 * https://leetcode.com/problems/two-city-scheduling/submissions/
	 *
	 * @param costs - cost to city a and b
	 * @return minimum cost
	 */
	public static int twoCitySchedCost(int[][] costs) {
		List<Person> persons = new ArrayList<>(costs.length);
		for (int i = 0; i < costs.length; i ++) {
			persons.add(new Person(costs[i][0], costs[i][1]));
		}
		persons.sort(new Comparator<Person>() {
			@Override
			public int compare(Person person, Person t1) {
				return (person.a - person.b) - (t1.a - t1.b);
			}
		});

		int answer = 0;
		for (int i = 0; i < costs.length / 2; i ++) {
			answer += persons.get(i).a + persons.get(i + (costs.length/2)).b;
		}

		return answer;
	}
}

class Person {

	int a;
	int b;

	public Person(int a, int b) {
		this.a = a;
		this.b = b;
	}
}