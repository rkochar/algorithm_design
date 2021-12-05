package Greedy.weblab;

import java.util.*;


public class MasonLatestEndtime {



	public static int masonLatestEndTime(int n, int[] startTimes, int[] computeTimes) {
		MasonJob[] masonJobs = new MasonJob[n];
		int answer = 0;
		for (int i = 1; i < startTimes.length; i++) {
			answer += startTimes[i];
			masonJobs[i - 1] = new MasonJob(startTimes[i], computeTimes[i]);
		}
		Arrays.sort(masonJobs);
		answer += masonJobs[n - 1].computeTime;

		int time = 0;
		for (int i = 0; i < masonJobs.length; i++) {
			int newTime = time + masonJobs[i].startTime + masonJobs[i].computeTime;
			answer = Integer.max(newTime, answer);
			time += masonJobs[i].startTime;
		}
		return answer;
	}
}

class MasonJob implements Comparable<MasonJob> {
	int startTime;
	int computeTime;

	public MasonJob(int start, int computeTime) {
		this.startTime = start;
		this.computeTime = computeTime;
	}

	@Override
	public int compareTo(MasonJob masonJob) {
		return Integer.compare(masonJob.computeTime, this.computeTime);
	}
}