package org.vadim;

import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: An integer X for the number of bricks in a row.
 * Line 2: An integer N for the number of bricks.
 * Line 3: The integer weights of the bricks separated by space. Weights are given in kg.
 * 
 * Output
 * Line 1 : The minimum work. Printed exactly with 3 decimal places.
 * 
 * Constraints
 * 2 ≤ X ≤ 100
 * 2 ≤ N ≤ 1000
 * 0 < m < 1000
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	private static final double coeff = 6.5d / 10.0d;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int X = in.nextInt();
		int N = in.nextInt();

		int[] bricks = new int[N];
		for (int i = 0; i < N; i++) {
			bricks[i] = in.nextInt();
		}
		Arrays.parallelSort(bricks);

		int pos = bricks.length - 1;
		int level = 0;
		double work = 0.0d;

		while (pos >= 0) {
			if (pos - X >= 0) { // full row
				int weight = 0;
				for (int i = pos; i > pos - X; i--) {
					weight += bricks[i];
				}
				pos -= X;
				work += level * coeff * weight;
			} else { // less than X in the last row
				int weight = 0;
				for (int i = 0; i <= pos; i++) {
					weight += bricks[i];
				}
				work += level * coeff * weight;
				break;
			}

			++level;
		}

		System.out.println(new Formatter().format(Locale.UK, "%1.3f", work));
	}
}
