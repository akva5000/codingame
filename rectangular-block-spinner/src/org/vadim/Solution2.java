package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: Size of the block, size.
 * Line 2: Angle angle.
 * size following lines: Content of the block. ASCII characters are separated with spaces.
 * 
 * Output
 * Rotated block with spaces on both ends.
 * 
 * Constraints
 * 2 =< size <= 100
 * 45 =< angle <= 2295
 * angle % 90 == 45 (Dimond shape)
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int angle = in.nextInt();

		angle = angle % 360;
		int rotate = angle / 45;
		System.err.println("angle=" + angle);
		System.err.println("rotate=" + rotate);

		if (in.hasNextLine()) {
			in.nextLine();
		}

		final char[][] data = new char[size][size];
		for (int y = 0; y < size; y++) {
			String line = in.nextLine();
			int pos = 0;
			for (int x = 0; x < size; x++) {
				data[y][x] = line.charAt(pos);
				pos += 2;
			}
		}

		switch (rotate) {
			case 0:
				System.err.println("Error angle = 0");
				break;

			case 1: // 1 time left
				angle1(data);
				break;

			case 3: // 2 times left
				angle2(data);
				break;

			case 5: // 2 times right or 1 time left and mirror
				angleR2(data);
				break;

			case 7: // 1 time right or 2 times left and mirror
				angleR1(data);
				break;

			default:
				System.err.println("Error angle");
				break;
		}

//		printData(data, size);
	}

	private static void angle1(final char[][] data) {
		int size = data.length;
		int sizeY = size + size - 1;

		// slicing from top right
		int xOut = sizeY / 2;

		// top half
		for (int xIn1 = size - 1; xIn1 >= 0; xIn1--) {
			if (xOut > 0) outNSpaces(xOut);
			int yIn2 = 0;
			int xIn2 = xIn1;
			boolean flag = false;
			while (xIn2 < size) {
				if (!flag) {
					flag = true;
				} else {
					System.out.print(' ');
				}
				char ch = data[yIn2][xIn2];
				System.out.print(ch);
				++yIn2;
				++xIn2;
			}

			if (xOut > 0) outNSpaces(xOut);
			System.out.println();
			--xOut;
		}

		// bottom half
		xOut = 1;
		for (int yIn1 = 1; yIn1 < size; yIn1++) {
			if (xOut > 0) outNSpaces(xOut);
			int yIn2 = yIn1;
			int xIn2 = 0;
			boolean flag = false;
			while (yIn2 < size) {
				if (!flag) {
					flag = true;
				} else {
					System.out.print(' ');
				}
				char ch = data[yIn2][xIn2];
				System.out.print(ch);
				++yIn2;
				++xIn2;
			}

			if (xOut > 0) outNSpaces(xOut);
			System.out.println();
			++xOut;
		}
	}

	private static void angle2(final char[][] data) {
		int size = data.length;
		int sizeY = size + size - 1;

		// slicing from bottom right
		int xOut = sizeY / 2;

		// top half
		for (int yIn1 = size - 1; yIn1 >= 0; yIn1--) {
			if (xOut > 0) outNSpaces(xOut);
			int yIn2 = yIn1;
			int xIn2 = size - 1;
			boolean flag = false;
			while (yIn2 < size) {
				if (!flag) {
					flag = true;
				} else {
					System.out.print(' ');
				}
				char ch = data[yIn2][xIn2];
				System.out.print(ch);
				++yIn2;
				--xIn2;
			}

			if (xOut > 0) outNSpaces(xOut);
			System.out.println();
			--xOut;
		}

		// bottom half
		xOut = 1;
		for (int xIn1 = size - 2; xIn1 >= 0; xIn1--) {
			if (xOut > 0) outNSpaces(xOut);
			int yIn2 = 0;
			int xIn2 = xIn1;
			boolean flag = false;
			while (xIn2 >= 0) {
				if (!flag) {
					flag = true;
				} else {
					System.out.print(' ');
				}
				char ch = data[yIn2][xIn2];
				System.out.print(ch);
				++yIn2;
				--xIn2;
			}

			outNSpaces(xOut);
			System.out.println();
			++xOut;
		}
	}

	private static void angleR1(final char[][] data) {
		int size = data.length;
		int sizeY = size + size - 1;

		// slicing from top right
		int xOut = sizeY / 2;

		// top half
		for (int yIn1 = 0; yIn1 < size; yIn1++) {
			if (xOut > 0) outNSpaces(xOut);
			int yIn2 = yIn1;
			int xIn2 = 0;
			boolean flag = false;
			while (yIn2 >= 0) {
				if (!flag) {
					flag = true;
				} else {
					System.out.print(' ');
				}
				char ch = data[yIn2][xIn2];
				System.out.print(ch);
				--yIn2;
				++xIn2;
			}

			if (xOut > 0) outNSpaces(xOut);
			System.out.println();
			--xOut;
		}

		// bottom half
		xOut = 1;
		for (int xIn1 = 1; xIn1 < size; xIn1++) {
			if (xOut > 0) outNSpaces(xOut);
			int yIn2 = size - 1;
			int xIn2 = xIn1;
			boolean flag = false;
			while (xIn2 < size) {
				if (!flag) {
					flag = true;
				} else {
					System.out.print(' ');
				}
				char ch = data[yIn2][xIn2];
				System.out.print(ch);
				--yIn2;
				++xIn2;
			}

			if (xOut > 0) outNSpaces(xOut);
			System.out.println();
			++xOut;
		}
	}

	private static void angleR2(final char[][] data) {
		int size = data.length;
		int sizeY = size + size - 1;

		// slicing from top right
		int xOut = sizeY / 2;

		// top half
		for (int xIn1 = 0; xIn1 < size; xIn1++) {
			if (xOut > 0) outNSpaces(xOut);
			int yIn2 = size - 1;
			int xIn2 = xIn1;
			boolean flag = false;
			while (xIn2 >= 0) {
				if (!flag) {
					flag = true;
				} else {
					System.out.print(' ');
				}
				char ch = data[yIn2][xIn2];
				System.out.print(ch);
				--yIn2;
				--xIn2;
			}

			if (xOut > 0) outNSpaces(xOut);
			System.out.println();
			--xOut;
		}

		// bottom half
		xOut = 1;
		for (int yIn1 = size - 2; yIn1 >= 0; yIn1--) {
			if (xOut > 0) outNSpaces(xOut);
			int yIn2 = yIn1;
			int xIn2 = size - 1;
			boolean flag = false;
			while (yIn2 >= 0) {
				if (!flag) {
					flag = true;
				} else {
					System.out.print(' ');
				}
				char ch = data[yIn2][xIn2];
				System.out.print(ch);
				--yIn2;
				--xIn2;
			}

			if (xOut > 0) outNSpaces(xOut);
			System.out.println();
			++xOut;
		}
	}

	private static void outNSpaces(int xOut) {
		for (int x = 0; x < xOut; x++) System.out.print(' ');
	}

	private static void printData(char[][] data, int size) {
		System.err.println("-- DATA --");
		for (int y = 0; y < data.length; y++) {
			for (char ch : data[y]) {
				System.err.print(ch);
			}
			System.err.println();
		}
		System.err.println("-- DATA --");
	}
}
