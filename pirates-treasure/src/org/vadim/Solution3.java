package org.vadim;

import java.util.BitSet;
import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: Width W of treasure map.
 * Line 2: Height H of treasure map.
 * Next H lines: W symbols (0 or 1) indicating free space (0) or obstacle (1).
 * 
 * Treasure is placed on free space surrounded by only obstacles.
 * 
 * There are three possibilities how can be the treasure surrounded:
 * By 3 obstacles when the treasure is in the corner of the map.
 * By 5 obstacles when the treasure is on the edge of the map.
 * By 8 obstacles when the treasure is inside the map.
 * 
 * Output
 * Coordinates of treasure in map represented by indexes separated by space. For example: "12 5"
 * 
 * Position "0 0" is in the top left corner, so maximum index x is W-1 and maximum index y is H-1.
 * 
 * Constraints:
 * 2 <= W <= 25
 * 2 <= H <= 25
 * Only 1 treasure in map.
 * </pre>
 * 
 * @author akva
 */
public class Solution3 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int W = in.nextInt();
		int H = in.nextInt();
		final BitSet desk = new BitSet(W * H);
		final BitSet forTest = new BitSet(W * H);

		int row = 0;
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				if (in.nextInt() != 1) continue;
				desk.set(row + x);
				if (forTest.get(row + x)) forTest.clear(row + x);

				// hor
				if (x > 0 && desk.get(row + x - 1) == false) {
					forTest.set(row + x - 1); // <-
					if (y > 0 && desk.get(row - W + x - 1) == false) forTest.set(row - W + x - 1);
					if (y < H - 1 && desk.get(row + W + x - 1) == false) forTest.set(row + W + x - 1);
				}
				if (x < W - 1 && desk.get(row + x + 1) == false) {
					forTest.set(row + x + 1); // ->
					if (y > 0 && desk.get(row - W + x + 1) == false) forTest.set(row - W + x + 1);
					if (y < H - 1 && desk.get(row + W + x + 1) == false) forTest.set(row + W + x + 1);
				}

				// vert
				if (y > 0 && desk.get(row - W + x) == false) forTest.set(row - W + x);
				if (y < H - 1 && desk.get(row + W + x) == false) forTest.set(row + W + x);
			}
			row += W; // next row
		}

		printDesk(forTest, H, W);
		check(forTest, desk, H, W);
		printDesk(desk, H, W);
//		printDesk(desk, H, W);
	}

	private static void check(final BitSet forTest, final BitSet desk, int H, int W) {
		int row = 0;
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				if (!forTest.get(row + x)) continue;

				if (x > 0) {
					if (!desk.get(row + x - 1)) continue; // left
					if (y > 0) {
						if (!desk.get(row - W + x - 1)) continue; // left-top
					}
					if (y < H - 1) {
						if (!desk.get(row + W + x - 1)) continue; // left-bottom
					}
				}

				if (x < W - 1) {
					if (!desk.get(row + x + 1)) continue; // right
					if (y > 0 && !desk.get(row - W + x + 1)) continue; // right-top
					if (y < H - 1 && !desk.get(row + W + x + 1)) continue; // right-bottom
				}

				if (y > 0 && !desk.get(row - W + x)) continue; // top
				if (y < H - 1 && !desk.get(row + W + x)) continue; // bottom

				System.out.println("" + x + " " + y);
				return;
			}
			row += W;
		}
	}

	private static void printDesk(final BitSet desk, int H, int W) {
		System.err.println("--- DESK ---");
		int t = 0;
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				System.err.print(desk.get(t + x) ? 'X' : '.');
			}
			System.err.println();
			t += W;
		}
		System.err.println("--- DESK ---");
	}
}
