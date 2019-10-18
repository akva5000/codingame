package org.vadim;

import java.util.BitSet;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: Two space separated integers width and height, respectively the width and height of the board
 * Next height lines: width characters ('0' or '1') each representing an initial cell state.
 * 
 * Output:
 * height lines: width characters ('0' or '1') each representing an updated cell state.
 * 
 * Constraints
 * 1 ≤ width, height ≤ 100
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int width = in.nextInt();
		int height = in.nextInt();
		BitSet field = new BitSet(width * height);

		int idx = 0;
		for (int i = 0; i < height; i++) {
			String line = in.next();
			for (int x = 0; x < width; x++) {
				if (line.charAt(x) == '0') continue;
				field.set(idx + x);
			}
			idx += width;
		}

		BitSet field2 = round(field, width, height);
		printField(field2, width, height);
	}

	private static BitSet round(final BitSet field, int width, int height) {
		BitSet field2 = new BitSet(width + height);
		int idx = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (field.get(idx + x) ? checkAliveCell(field, width, height, y, x) : checkDeadCell(field, width, height, y, x)) {
					field2.set(idx + x);
				}
			}
			idx += width;
		}
		return field2;
	}

	private static boolean checkAliveCell(final BitSet field, int width, int height, int y, int x) {
		int sum = calcNumberofAlive(field, width, height, y, x);
		// Any live cell with fewer than two live neighbors dies, as if caused by under-population.
		// Any live cell with two or three live neighbors lives on to the next generation.
		// Any live cell with more than three live neighbors dies, as if by over-population..
		return sum == 2 || sum == 3;
	}
	
	private static boolean checkDeadCell(final BitSet field, int width, int height, int y, int x) {
		int sum = calcNumberofAlive(field, width, height, y, x);
		// Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
		return sum == 3;
	}
	
	private static int calcNumberofAlive(final BitSet field, int width, int height, int y, int x) {
		int idx = y * width;
		int sum = 0;

		if (x > 0) {
			if (field.get(idx + x - 1)) ++sum;
			if (y > 0 && field.get(idx + x - width - 1)) ++sum;
			if (y < height - 1 && field.get(idx + x + width - 1)) ++sum;
		}

		if (x < width - 1) {
			if (field.get(idx + x + 1)) ++sum;
			if (y > 0 && field.get(idx + x - width + 1)) ++sum;
			if (y < height - 1 && field.get(idx + x + width + 1)) ++sum;
		}

		if (y > 0 && field.get(idx + x - width)) ++sum;
		if (y < height - 1 && field.get(idx + x + width)) ++sum;
		
		return sum;
	}

	private static void printField(final BitSet field, int width, int height) {
		int idx = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				System.out.print(field.get(idx + x) ? '1' : '0');
			}
			System.out.println();
			idx += width;
		}
	}
}
