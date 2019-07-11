package org.vadim;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: the number N of horses
 * N following lines: the speed Vi and elegance Ei of each horse, space-separated
 * 
 * Output
 * Line 1: the distance D between the two closest strengths
 * 
 * Constraints
 * 10 ≤ N ≤ 600
 * 0 ≤ Vi,Ei ≤ 10000000
 * D ≥ 0
 * All values are integral.
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int vs[] = new int[N];
		int es[] = new int[N];
		for (int i = 0; i < N; i++) {
			vs[i] = in.nextInt();
			es[i] = in.nextInt();
		}

		int min = Integer.MAX_VALUE;
		for (int y = 1; y < N; y++) {
			for (int x = 0; x < y - 1; x++) {
				int v = Math.abs(vs[y] - vs[x]) + Math.abs(es[y] - es[x]);
				if (v < min) min = v;
			}
		}

		System.out.println(min);
	}
}
