package org.vadim;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <pre>
 * Input:
 * There are multiple data structures and operation sequences in each test case.
 * 
 * Line 1: An integer N for the number of operation sequences for your analysis.
 * Next N lines: each line represents one data structure and a sequence of operations carried out on it.
 * » Operations are space separated.
 * » Each operation starts with a character i or o, followed by an integer
 * 
 * For example,
 * i2 means input 2 into the structure
 * o2 means asking the structure to output a data, and we got 2 as the output.
 * 
 * Important for your analysis - we got NO ERROR in all the operations.
 * 
 * Output:
 * Output N lines, corresponding to the inputs.
 * Each line shall be one of the options in the "answer" column below.
 * 
 * ┌────────────────┬───────────────────────────────────────────────────┐
 * │ answer         │ meaning                                           │
 * ├────────────────┼───────────────────────────────────────────────────┤
 * │ queue          │ it is a Queue                                     │
 * │ stack          │ it is a Stack                                     │
 * │ priority queue │ it is a Priority Queue (bigger data has priority) │
 * │ unsure         │ it can be two or more of the above options        │
 * │ mystery        │ it belongs to none of the above options           │
 * └────────────────┴───────────────────────────────────────────────────┘
 * 
 * Constraints
 * 1 ≤ N ≤ 100
 * 0 ≤ value of data in each operation < 100
 * 0 < number of operations in a sequence ≤ 100
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		final Queue<Integer> queue = new LinkedList<>();
		final Deque<Integer> stack = new ArrayDeque<>();
		final PriorityQueue<Integer> pqueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

		for (int i = 0; i < N; i++) {
			queue.clear();
			stack.clear();
			pqueue.clear();
			int mask = 0b0111;

			String line = in.nextLine();
			for (final StringTokenizer tok = new StringTokenizer(line, " "); tok.hasMoreTokens();) {
				String cmd = tok.nextToken();
				Integer value = Integer.valueOf(cmd.substring(1));

				if (cmd.charAt(0) == 'i') {
					queue.add(value);
					stack.push(value);
					pqueue.add(value);
				} else {
					if ((mask & 0b001) != 0) {
						Integer value2 = queue.isEmpty() ? value + 1 : queue.remove();
						if (value2.compareTo(value) != 0) mask &= 0b0110;
					}

					if ((mask & 0b010) != 0) {
						Integer value2 = stack.isEmpty() ? value + 1 : stack.pop();
						if (value2.compareTo(value) != 0) mask &= 0b0101;
					}

					if ((mask & 0b100) != 0) {
						Integer value2 = pqueue.isEmpty() ? value + 1 : pqueue.remove();
						if (value2.compareTo(value) != 0) mask &= 0b0011;
					}
				}
			}

			int t = Integer.bitCount(mask);
			switch (t) {
				case 0:
					System.out.println("mystery");
					break;

				case 2:
				case 3:
					System.out.println("unsure");
					break;

				default:
					if ((mask & 0b001) != 0) System.out.println("queue");
					else if ((mask & 0b010) != 0) System.out.println("stack");
					else if ((mask & 0b0100) != 0) System.out.println("priority queue");
					break;
			}
		} // for i
	}

}
