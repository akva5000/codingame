package org.vadim;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: The number N of buildings that need to be connected to the optical fiber network
 * On the N following lines: The coordinates x and y of the buildings
 * 
 * Output
 * The minimum length L of cable required to connect all of the buildings.
 * In other words, the length of the main cable plus the length of the cables dedicated to all the buildings.
 * 
 * Note: the buildings with the same position x should not in any case share the same dedicated cable.
 * 
 * Constraints
 * 0 < N ≤ 100000
 * 0 ≤ L ≤ 263
 * -230 ≤ x ≤ 230
 * -230 ≤ y ≤ 230
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] xs = new int[N];
		int[] ys = new int[N];
		int sum = 0;
		int xMin = Integer.MAX_VALUE;
		int xMax = -1;
		int yMin = Integer.MAX_VALUE;
		int yMax = -1;
		for (int i = 0; i < N; i++) {
			int X = in.nextInt();
			if (X < xMin) xMin = X;
			else if (X > xMax) xMax = X;
			xs[i] = X;

			int Y = in.nextInt();
			if (Y < yMin) yMin = Y;
			else if (Y > yMax) yMax = Y;
			ys[i] = Y;
			sum += Y;
		}

		if (N == 1) {
			System.out.println("0");
			return;
		}

		int sum1 = 0;

		// strategy 1
		{
			System.err.println("*** strategy 1");
			int[] uniqValues = Arrays.copyOf(ys, ys.length);
			Arrays.parallelSort(uniqValues);

			int prevValue = -1;
			int prevCounter = 0;
			{
				int value = -1;
				int counter = 0;
				for (int v : uniqValues) {
					if (v == value) {
						++counter;
						continue;
					}

					if (value != -1 && counter > prevCounter) {
						prevValue = value;
						prevCounter = counter;
					}
					value = v;
					counter = 1;
				}

				if (counter > prevCounter) {
					prevValue = value;
					prevCounter = counter;
				}

				System.err.println("often Y=" + prevValue + ", " + prevCounter);
			}

			sum1 = xMax - xMin;
			for (int i = 0; i < N; i++) {
				sum1 += Math.abs(ys[i] - prevValue);
			}

			System.err.println("sum1=" + sum1);
		}

		// strategy 2
		{
			System.err.println("*** strategy 2");
			System.err.println("x-min=" + xMin + ", x-max=" + xMax);
			int avgY = sum / N;
			System.err.println("avg-y=" + avgY);
			sum = xMax - xMin;
			System.err.println("main cable=" + sum);

			for (int i = 0; i < N; i++) {
				sum += Math.abs(ys[i] - avgY);
			}

			System.err.println("sum2=" + sum);
			sum1 = sum1 < sum ? sum1 : sum;
		}

		// strategy 3
		{
			System.err.println("*** strategy 3");
			System.err.println("x-min=" + xMin + ", x-max=" + xMax);
			float avg = (yMax - yMin) / 2.0f + yMin;
			System.err.println("avg-y=" + avg);
			
			int avgY = Math.round(avg);
			
			System.err.println("avg-y=" + avgY);
			sum = xMax - xMin;
			System.err.println("main cable=" + sum);

			for (int i = 0; i < N; i++) {
				sum += Math.abs(ys[i] - avgY);
			}

			System.err.println("sum3=" + sum);
			sum1 = sum1 < sum ? sum1 : sum;
		}

		System.out.println(String.valueOf(sum1));
	}
}
