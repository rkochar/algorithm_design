package Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class AttendMaxEvents {

	/**
	 * Attend as many events as possible, each needing 1 unit of time and 0 in travel.
	 * Sort by start time and check how many can be attended for each start time using pq to store end time.
	 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
	 *
	 * @param events - events[start][end]
	 * @return number of events that can be attended
	 */
	public static int maxEvents(int[][] events) {
		// edge, perf
		// greedy. for any day, pick the event that ends soon.
		// first, sort by start day.
		Arrays.sort(events, (a, b) -> a[0] - b[0]);
		// then, we use pq - this will store end dates.
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int end = 0, start = events[end][0], answer = 0;
		while(!pq.isEmpty() || end < events.length) {
			// put end days of events that start on sd.
			while (end < events.length && events[end][0] == start) {
				pq.offer(events[end][1]);
				end++;
			}

			// attend an event on day sd.
			if (!pq.isEmpty()) {
				pq.poll();
				answer++;
				// remove events that ends on day sd.
				// also if earliest ed is found, assign it to sd.
				while(!pq.isEmpty()) {
					if (pq.peek() == start)
						pq.poll();
					else
						break;
				}
			}
			start++;
		}
		return answer;
	}

//		PriorityQueue<Event> pq = new PriorityQueue<>(new Comparator<Event>() {
//			@Override
//			public int compare(Event event, Event t1) {
//				return event.end - t1.end;
//			}
//		});
//		for (int[] event: events) pq.add(new Event(event[0] - 1, event[1]));
//		int answer = 0, i = 0;
//
//		while (!pq.isEmpty()) {
//			Event event = pq.poll();
//			if (event.end <= i) continue;
//			else if (i < event.start) {
//				i ++;
//				pq.add(event);
//			}
//			else {
//				i ++;
//				answer ++;
//			}
//		}
//
//		return answer;

}
