package org.vadim;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <pre>
 * Input:
 * Line 1: W H for the width and height of the whole rectangle
 * Next H lines: space separated integers. Each line contains one row of data for the rectangle
 * 
 * Output:
 * The sum of the "maximum" sub-rectangle
 * 
 * Constraints
 * 1 ≤ W, H ≤ 100
 * -99 ≤ n ≤ 99, where n is any integer within the rectangle
 * No data line is longer than 500 characters
 * </pre>
 * 
 * @author akva
 */
public class Solution3 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int W = in.nextInt();
		int H = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		int[][] desk = new int[H][W];
		int maxValue = Integer.MIN_VALUE;
		for (int y = 0; y < H; y++) {
			int x = 0;
			for (final StringTokenizer tok = new StringTokenizer(in.nextLine(), " "); tok.hasMoreTokens();) {
				int v = Integer.parseInt(tok.nextToken());
				desk[y][x++] = v;
				if (v > maxValue) maxValue = v;
			}
		}

		int v = maxRect(desk);
		if (v > maxValue) maxValue = v;
		System.out.println(maxValue);
	}

	private static int maxRect(int[][] matrix) {
		int rowCount = matrix.length;
		int colCount = matrix[0].length;

		int[] partialSum = new int[colCount];
		int maxSum = Integer.MIN_VALUE;

		for (int rowStart = 0; rowStart < rowCount; rowStart++) {
			Arrays.fill(partialSum, 0);

			for (int rowEnd = rowStart; rowEnd < rowCount; rowEnd++) {
				for (int i = 0; i < colCount; i++) {
					partialSum[i] += matrix[rowEnd][i];
				}

				int tempMaxSum = handleLine(partialSum, colCount);
				if (tempMaxSum > maxSum) maxSum = tempMaxSum;
			}
		}
		return maxSum;
	}

	private static int handleLine(int array[], int N) {
		int maxSum = Integer.MIN_VALUE;
		int runningSum = 0;

		for (int i = 0; i < N; i++) {
			runningSum += array[i];
			if (runningSum > maxSum) maxSum = runningSum;
			if (runningSum < 0) runningSum = 0;
		}
		return maxSum;
	}
}
