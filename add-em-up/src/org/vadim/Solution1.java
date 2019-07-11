package org.vadim;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: An integer N for the number of integers to add up
 * Line 2: N positive integers separated by space
 * 
 * Output
 * Line 1 : The lowest total cost of finishing the additions
 * 
 * Constraints
 * 2 ≤ N ≤ 5000
 * each integer x will be 0 < x ≤ 100000
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		final PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			queue.add(in.nextInt());
		}

		long sum = 0;
		while (queue.size() != 1) {
			int tmp = queue.poll() + queue.poll();
			queue.add(tmp);
			sum += tmp;
		}

		System.out.println(sum);
	}
}
