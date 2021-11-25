package greedy;

import java.util.*;

public class TaskScheduleWithCooldown {

	/**
	 * Schedule tasks such that two tasks have same type have a cooldown of n.
	 * https://leetcode.com/problems/task-scheduler/
	 * Solve with greedy instead of pq.
	 *
	 * @param tasks name of task
	 * @param n - cooldown
	 * @return minimum time
	 */
	public static int leastInterval(char[] tasks, int n) {
		if (n == 0) return tasks.length;
		HashMap<Character, Integer> map = new HashMap<>();
		for (char c : tasks) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		int maxTask = map.values().stream().max(new Comparator<>() {
			@Override
			public int compare(Integer integer, Integer t1) {
				return integer - t1;
			}
		}).get();
		int maxCount = (int) map.values().stream().filter(x -> x == maxTask).count();
		return Math.max(tasks.length, (maxTask - 1) * (n + 1) + maxCount);

		/**
		 * 		char[] ch1 = {'A', 'A', 'A', 'B', 'B', 'B'};
		 * 		char[] ch2 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		 *
		 * 		assertEquals(6, BeautifulPair.leastInterval(ch1, 0));
		 * 		assertEquals(8, BeautifulPair.leastInterval(ch1, 2));
		 * 		assertEquals(16, BeautifulPair.leastInterval(ch2, 2));
		 */

//		PriorityQueue<Task> pq = new PriorityQueue<>(new Comparator<Task>() {
//			@Override
//			public int compare(Task task, Task t1) {
//				if (task.cooldown != 0 && t1.cooldown != 0) return t1.number - task.number;
//				else return task.cooldown - t1.cooldown;			}
//		});
//		for (Character character: map.keySet()) {
//			pq.add(new Task(character, map.get(character)));
//		}
//
//		int answer = 0;
//		while (!pq.isEmpty()) {
//			Task task = pq.poll();
//			cooldown(pq);
//			while (task.cooldown != 0) {
//				task.cooldown --;
//				cooldown(pq);
//				answer ++;
//			}
//
//			answer ++;
//			task.number --;
//			task.cooldown = n;
//			if (task.number != 0) pq.add(task);
//		}
//
//		return answer;
	}

	private static void cooldown(PriorityQueue<Task> pq) {
		List<Task> tasks = new ArrayList<>();
		while (!pq.isEmpty()) {
			tasks.add(pq.poll());
		}
		for (Task task: tasks) {
			if (task.cooldown != 0) task.cooldown --;
			pq.add(task);
		}
	}
}

class Task {
	Character character;
	int number;
	int cooldown;

	public Task(Character string, int number) {
		this.character = string;
		this.number = number;
	}
}
