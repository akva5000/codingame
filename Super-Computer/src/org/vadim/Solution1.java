package org.vadim;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: The number N of calculations
 * The N following lines: on each line, the starting day J and the duration D of reservation, separated by a blank space.
 * 
 * Output:
 * The maximum number of calculations that can be carried out.
 * 
 * Constraints
 * 0 < N < 100000
 * 0 < J < 1000000
 * 0 < D < 1000
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		final Task[] tasks = new Task[N];
		for (int i = 0; i < N; i++) {
			int J = in.nextInt();
			int D = in.nextInt();
			tasks[i] = new Task(J, J + D - 1);
		}

		// sort intervals
		Arrays.parallelSort(tasks, (Task t1, Task t2) -> {
			return Integer.compare(t1.end, t2.end);
		});

		System.out.println("" + optimizeList(tasks));
	}

	private static int optimizeList(final Task[] tasks) {
		int leftPos = tasks[0].end;
		int counter = 1;

		for (int i = 1; i < tasks.length; i++) {
			if (tasks[i].start > leftPos) {
				++counter;
				if (tasks[i].end > leftPos) leftPos = tasks[i].end;
			}
		}

		return counter;
	}

	private static class Task {
		public int start, end;

		public Task(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public boolean isOverlap(final Task task) {
			return task.start >= this.start && task.start <= this.end || this.start >= task.start && this.start <= task.end;
		}
	}
}
