package org.vadim;

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
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int W = in.nextInt();
		int H = in.nextInt();
		int[][] desk = new int[H][W];
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				if (in.nextInt() == 1) desk[y][x] = 1;
			}
		}

		printDesk(desk, H, W);
		boolean flag = checkCorners(desk, H, W) || topEdge(desk, H, W) || bottomEdge(desk, H, W) || leftEdge(desk, H, W)
				|| rightEdge(desk, H, W) || fullCheck(desk, H, W);
		printDesk(desk, H, W);
	}

	private static boolean checkCorners(final int[][] desk, int H, int W) {
		// top left
		if (desk[0][0] != 1) {
			if (desk[0][1] == 1 && desk[1][0] == 1 && desk[1][1] == 1) {
				System.out.println("0 0");
				System.err.println("checkCorners: top-left");
				return true;
			}
		}

		// top right
		if (desk[0][W - 1] != 1) {
			if (desk[0][W - 2] == 1 && desk[1][W - 2] == 1 && desk[1][W - 1] == 1) {
				System.out.println((W - 1) + " 0");
				System.err.println("checkCorners: top-right");
				return true;
			}
		}

		// bottom left
		if (desk[H - 1][0] != 1) {
			if (desk[H - 2][0] == 1 && desk[H - 1][1] == 1 && desk[H - 2][1] == 1) {
				System.out.println("0 " + (H - 1));
				System.err.println("checkCorners: bottom-left");
				return true;
			}
		}

		// bottom right
		if (desk[H - 1][W - 1] != 1) {
			if (desk[H - 1][W - 2] == 1 && desk[H - 2][W - 1] == 1 && desk[H - 2][W - 2] == 1) {
				System.out.println("" + (W - 1) + " " + (H - 1));
				System.err.println("checkCorners: bottom-right");
				return true;
			}
		}

		return false;
	}

	private static boolean topEdge(final int[][] desk, int H, int W) {
		int line = 0;
		int pos = 2;
		for (int x = 0; x < pos; x++) {
			if (desk[1][x] == 1) ++line;
		}

		while (pos < W) {
			if (desk[1][pos] == 1) ++line;
			if (line == 3 && desk[0][pos] == 1 && desk[0][pos - 2] == 1) {
				System.out.println("" + (pos - 1) + " 0");
				System.err.println("topEdge");
				return true;
			}

			if (desk[1][pos - 2] == 1) --line;
			++pos;
		}

		return false;
	}

	private static boolean bottomEdge(final int[][] desk, int H, int W) {
		int line = 0;
		int pos = 2;
		for (int x = 0; x < pos; x++) {
			if (desk[H - 2][x] == 1) ++line;
		}

		while (pos < W) {
			if (desk[H - 2][pos] == 1) ++line;
			if (line == 3 && desk[H - 1][pos] == 1 && desk[H - 1][pos - 2] == 1) {
				System.out.println("" + (pos - 1) + " " + (H - 1));
				System.err.println("bottomEdge");
				return true;
			}

			if (desk[H - 2][pos - 2] == 1) --line;
			++pos;
		}

		return false;
	}

	private static boolean leftEdge(final int[][] desk, int H, int W) {
		int line = 0;
		int pos = 2;
		for (int y = 0; y < pos; y++) {
			if (desk[y][1] == 1) ++line;
		}

		while (pos < W) {
			if (desk[pos][1] == 1) ++line;
			if (line == 3 && desk[pos][0] == 1 && desk[pos - 2][0] == 1) {
				System.out.println("0 " + (pos - 1));
				System.err.println("leftEdge");
				return true;
			}

			if (desk[pos - 2][1] == 1) --line;
			++pos;
		}

		return false;
	}

	private static boolean rightEdge(final int[][] desk, int H, int W) {
		int line = 0;
		int pos = 2;
		for (int y = 0; y < pos; y++) {
			if (desk[y][W - 2] == 1) ++line;
		}

		while (pos < W) {
			if (desk[pos][W - 2] == 1) ++line;
			if (line == 3 && desk[pos][W - 1] == 1 && desk[pos - 2][W - 1] == 1) {
				System.out.println("" + (W - 1) + " " + (pos - 1));
				System.err.println("rightEdge");
				return true;
			}

			if (desk[pos - 2][W - 2] == 1) --line;
			++pos;
		}

		return false;
	}

	private static boolean fullCheck(final int[][] desk, int H, int W) {
		for (int y = 1; y < H - 1; y++) {
			for (int x = 1; x < W - 1; x++) {
				if(desk[y][x] == 1) continue; 
				if (desk[y][x - 1] == 1 && desk[y][x + 1] == 1 && desk[y - 1][x] == 1 && desk[y + 1][x] == 1
						&& desk[y - 1][x - 1] == 1 && desk[y + 1][x - 1] == 1 && desk[y - 1][x + 1] == 1
						&& desk[y + 1][x + 1] == 1) {
					System.out.println("" + x + " " + y);
					System.err.println("full check");
					return true;
				}

			}
		}

		return false;
	}

	private static void printDesk(final int[][] desk, int H, int W) {
		System.err.println("--- DESK ---");
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				System.err.print((desk[y][x] == 1) ? 'X' : ' ');
			}
			System.err.println();
		}
		System.err.println("--- DESK ---");
	}
}
