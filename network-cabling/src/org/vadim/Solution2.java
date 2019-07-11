package org.vadim;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;

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
public class Solution2 {
	private static int[][] points;
	private static int xMin;
	private static int xMax;

	private static long trunkLen;

	public static void main(String args[]) {
		int yMin = Integer.MAX_VALUE;
		int yMax = -1;
		trunkLen = 0;

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		points = new int[N][2];
		for (int i = 0; i < N; i++) {
			points[i][0] = in.nextInt();
			points[i][1] = in.nextInt();

			if (points[i][1] < yMin) yMin = points[i][1];
			else if (points[i][1] > yMax) yMax = points[i][1];
		}

		if (N == 1) {
			System.out.println("0");
			return;
		}

		Arrays.parallelSort(points, (o1, o2) -> Integer.compare(o1[0], o2[0]));
		xMin = points[0][0];
		xMax = points[points.length - 1][0];
		trunkLen = xMax - xMin;

		System.err.println("## trunk=" + trunkLen);
		long minSum = medianaTask() + trunkLen;
		System.out.println(minSum);
	}

	private static long medianaTask() {
		int[] yOrdered = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			yOrdered[i] = points[i][1];
		}
		Arrays.parallelSort(yOrdered);

		long sum = 0;
		if (yOrdered.length % 2 != 0) {
			sum = calcDist(yOrdered[yOrdered.length / 2]);

		} else { // symmetric
			int midIdx = yOrdered.length / 2 - 1;
			int midY = yOrdered[midIdx];
			long sum1 = calcDist(midY);

			++midIdx;
			sum = calcDist(yOrdered[midIdx]);
			sum = sum1 < sum ? sum1 : sum;
		}

		System.err.println("mediana=" + sum);
		return sum;
	}

	private static long calcDist(int yMid) {
		long sum = 0;
		for (int i = 0; i < points.length; i++) {
			sum += Math.abs(points[i][1] - yMid);
		}
		return sum;
	}
}
