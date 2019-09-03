package org.vadim;

import java.util.BitSet;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1 : Two integers: The map width and height.
 * height next lines : width characters: (. or #).
 * 
 * Output:
 * height lines : width characters where the # are at the bottom of the grid.
 * 
 * Constraints
 * 0 < width < 100
 * 0 < height < 10
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int width = in.nextInt();
		int height = in.nextInt();

		final BitSet desk = new BitSet(height * width);
		for (int i = 0; i < height; i++) {
			String line = in.next();
			for (int x = 0; x < width; x++) {
				if (line.charAt(x) != '#') continue;
				desk.set(width * i + x);
			}
		}

		if (height > 1 && desk.cardinality() != 0) process(width, height, desk);
		printDesk(desk, width, height);
	}

	private static void process(int width, int height, final BitSet desk) {
		int pos = (height - 1) * width;
		for (int y = height - 1; y >= 0; y--) {
			for (int x = 0, idx = pos + x; x < width; x++, idx++) {
				if (!desk.get(idx)) continue;
				int y2 = y + 1;
				int pos2 = pos + width + x;
				while (y2 < height && !desk.get(pos2)) {
					desk.clear(pos2 - width);
					desk.set(pos2);
					pos2 += width;
					++y2;
				}
			}
			pos -= width;
		}
	}

	private static void printDesk(final BitSet desk, int width, int height) {
		int counter = 1;
		for (int i = 0, size = width * height; i < size; i++) {
			System.out.print(desk.get(i) ? '#' : '.');
			if ((counter++) == width) {
				System.out.println();
				counter = 1;
			}
		}
	}
}
