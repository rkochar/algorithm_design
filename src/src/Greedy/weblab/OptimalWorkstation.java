package Greedy.weblab;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class OptimalWorkstation {

	/**
	 * Since number of machines is infinite, they must be added while looping - when an
	 * unlocked machine is unavailable.
	 * IGNORE [0].
	 *
	 * @param n - number of machines needed
	 * @param m - unlock period after machine is used
	 * @param start - starting time of task
	 * @param duration - time of task
	 * @return unlocks saved
	 */
	public static int solve(int n, int m, int[] start, int[] duration) {
		PriorityQueue<Machine> machines = new PriorityQueue<>(Comparator.comparingInt(x -> x.lockedAfter));
		Session[] sessions = new Session[n];
		for (int i = 1; i < start.length; i++) sessions[i - 1] = new Session(start[i], duration[i]);
		Arrays.sort(sessions, Comparator.comparingInt(x -> x.start));

		int answer = 0;
		for (int i = 0; i < n; i ++) {
			while (!machines.isEmpty()) {
				if (machines.peek().availableFrom > sessions[i].start) break; // Machine unavailable
				Machine machine = machines.poll();
				if (machine.lockedAfter >= sessions[i].start) {
					answer ++;
					break;
				}
			}
			machines.add(new Machine(sessions[i].start + sessions[i].duration, sessions[i].start + sessions[i].duration + m));
		}

		return answer;
	}
}

	class Session {
		int start;
		int duration;

		public Session(int start, int duration) {
			this.start = start;
			this.duration = duration;
		}
	}


	class Machine {
		int availableFrom;
		int lockedAfter;

		public Machine(int availableFrom, int lockedAfter) {
			this.availableFrom = availableFrom;
			this.lockedAfter = lockedAfter;
		}
	}


