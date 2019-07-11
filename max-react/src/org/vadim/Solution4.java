package org.vadim;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

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
public class Solution4 {

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
		int H = matrix.length;
		int W = matrix[0].length;

		int[] partialSum = new int[W];
		int maxSum = Integer.MIN_VALUE;

//		final ForkJoinPool pool = new ForkJoinPool(4); 
//		final BlockingQueue<Object> queue = new ArrayBlockingQueue<>(100, true);
		
		for (int yStart = 0; yStart < H; yStart++) {
			for (int yEnd = yStart; yEnd < H; yEnd++) {
				System.err.println("loop: y:" + yStart + "->" + yEnd);
//				for (int x = 0; x < W; x++) partialSum[x] += matrix[yEnd][x];
				final int y = yEnd;
				IntStream.range(0, W).parallel().forEach(x -> {partialSum[x] += matrix[y][x];});
				
				System.err.print("Col array: ");
				for (int x = 0; x < W; x++) System.err.print("" + partialSum[x] + ',');
				System.err.println();

				int tempMaxSum = maxColumn(partialSum);
				System.err.println("temMax=" + tempMaxSum);
				if (tempMaxSum > maxSum) maxSum = tempMaxSum;
				System.err.println("\n\n");
			}
			Arrays.fill(partialSum, 0);
		}
		
		return maxSum;
	}

	private static int maxColumn(final int array[]) {
		int maxSum = Integer.MIN_VALUE;
		int runningSum = 0;
		
		for (int i = 0; i < array.length; i++) {
			runningSum += array[i];
			if (runningSum > maxSum) maxSum = runningSum;
			if (runningSum < 0) runningSum = 0;
		}
		return maxSum;
	}
	
	
}
