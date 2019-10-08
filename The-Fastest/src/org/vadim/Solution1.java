package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: a integer number N.
 * Next N lines: 8 characters, a time result.
 * 
 * Output:
 * The best result.
 * 
 * Constraints
 * 0 < N ≤ 10
 * 0 ≤ hours < 24
 * 0 ≤ minutes < 60
 * 0 ≤ seconds < 60
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int bestTime = 257070;
		String best = "";

		int N = in.nextInt();
		int[] tmp = new int[3];
		for (int i = 0; i < N; i++) {
			String t = in.next();
			int time = Integer.parseInt(t.replaceAll(":", ""));
			if (Integer.compare(bestTime, time) > 0) {
				bestTime = time;
				best = t;
			}
		}

		System.out.println(best);
	}

}
