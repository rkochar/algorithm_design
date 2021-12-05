package Exam.TwentyOne.Midterm;

import java.util.Arrays;
import java.util.Comparator;

public class ExamTime {

	/**
	 *  You should implement this method.
	 *  @param n the number of exercises
	 *  @param t the time required to create an exercise t_1 through t_n. Note you should only use entries t[1] up to and including t[n].
	 *  @param p the penalty per hour for an exercise p_1 through p_n. Note you should only use entries p[1] up to and including p[n].
	 *  @return the smallest penalty for creating all n exercises.
	 */
	public static int prioritisingExercises(int n, int[] t, int[] p) {
		return practice(n, t, p);
//		return solveProper(n, t, p, 0);
	}

	public static int practice(int n, int[] t, int[] p) {
		MidtermExercise[] exercises = new MidtermExercise[n];
		for (int i = 0; i < exercises.length; i++) {
			exercises[i] = new MidtermExercise(t[i + 1], p[i + 1]);
		}
		Arrays.sort(exercises, Comparator.comparingDouble(x -> -(x.penalty * 1.0 / x.time)));
		int answer = 0, time = 0;

		for (MidtermExercise exercise : exercises) {
			time += exercise.time;
			answer += time * exercise.penalty;
		}

		return answer;
	}
	public static int solveProper(int n, int[] t, int[] p, int sortingMethod) {
		Exercise[] exercises = new Exercise[n];
		for (int i = 1; i <= n; i++) {
			exercises[i - 1] = new Exercise(t[i], p[i]);
		}
		switch(sortingMethod) {
			case 0:
				Arrays.sort(exercises, (o1, o2) -> Double.compare(o2.p * 1.0 / o2.t, o1.p * 1.0 / o1.t));
				break;
			case 1:
				Arrays.sort(exercises, (o1, o2) -> Double.compare(o2.p, o1.p));
				break;
			case 2:
				Arrays.sort(exercises, Comparator.comparingDouble(o -> o.t));
				break;
		}
		int result = 0;
		int time = 0;
		for (int i = 0; i < n; i++) {
			time += exercises[i].t;
			result += exercises[i].p * time;
			if (result < 0) {
				throw new Error("Too large number");
			}
		}
		return result;
	}

	public static class Exercise {

		int t;

		int p;

		public Exercise(int t, int p) {
			this.t = t;
			this.p = p;
		}
	}
}

class MidtermExercise {
	int time;
	int penalty;

	public MidtermExercise(int time, int penalty) {
		this.time = time;
		this.penalty = penalty;
	}
}