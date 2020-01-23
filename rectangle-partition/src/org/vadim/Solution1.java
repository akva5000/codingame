package org.vadim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

/**
 * <pre>
 * Input:
 * Line 1: Integers w h countX countY, separated by space
 * Line 2: list of measurements on the width side, countX integers separated by space, sorted in asc order
 * Line 3: list of measurements on the height side, countY integers separated by space, sorted in asc order
 * 
 * Output:
 * Line 1: the number of squares in sub-rectangles created by the added lines
 * 
 * Constraints
 * 1 ≤ w, h ≤ 20,000
 * 1 ≤ number of measurements on each axis ≤ 500
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		final Scanner in = new Scanner(System.in);

		int w = in.nextInt();
		int h = in.nextInt();
		int countX = in.nextInt();
		int countY = in.nextInt();

		int[] xSizes = new int[countX + 1];
		int[] ySizes = new int[countY + 1];

		readDividers(in, countX, w, xSizes);
		readDividers(in, countY, h, ySizes);

		final List<Integer> xVariants = countX > 0 ? calcSizes(xSizes) : Arrays.asList(w);
		final List<Integer> yVariants = countY > 0 ? calcSizes(ySizes) : Arrays.asList(h);

		int counter = 0;
		for (int xs : xVariants) {
			for (int ys : yVariants) {
				if (xs == ys) ++counter;
			}
		}

		System.out.println(counter);
	}

	private static void readDividers(final Scanner in, int divCount, int maxSize, int[] array) {
		int pos = 0;
		int t = 0;
		for (int i = 0; i < divCount; i++) {
			int x = in.nextInt();
			array[pos++] = x - t;
			t = x;
		}

		if (divCount > 0) {
			array[pos++] = maxSize - t;
		}
	}

	private static List<Integer> calcSizes(int[] sizes) {
		final List<Integer> list = new ArrayList<>();
		for (int i1 = 0; i1 < sizes.length; i1++) {
			int size = sizes[i1];
			list.add(size);

			if (i1 != sizes.length - 1) {
				int size2 = size;
				for (int i2 = i1 + 1; i2 < sizes.length; i2++) {
					size2 += sizes[i2];
					list.add(size2);
				}
			}
		}

		return list;
	}
}
