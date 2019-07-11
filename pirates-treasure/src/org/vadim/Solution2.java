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
public class Solution2 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int W = in.nextInt();
		int H = in.nextInt();
		final BitSet desk = new BitSet(W * H);
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				if (in.nextInt() == 1) desk.set(W * y + x);
			}
		}

		printDesk(desk, H, W);
		boolean flag = checkCorners(desk, H, W) || topEdge(desk, H, W) || bottomEdge(desk, H, W) || leftEdge(desk, H, W)
				|| rightEdge(desk, H, W) || fullCheck(desk, H, W);
		printDesk(desk, H, W);
	}

	private static boolean checkCorners(final BitSet desk, int H, int W) {
		// top left
		if (!desk.get(0)) {
			if (desk.get(1) && desk.get(W) && desk.get(W + 1)) {
				System.out.println("0 0");
				System.err.println("checkCorners: top-left");
				return true;
			}
		}

		// top right
		if (!desk.get(W - 1)) {
			if (desk.get(W - 2) && desk.get(W + W - 2) && desk.get(W + W - 1)) {
				System.out.println((W - 1) + " 0");
				System.err.println("checkCorners: top-right");
				return true;
			}
		}

		// bottom left
		if (!desk.get(W * (H - 1))) {
			if (desk.get(W * (H - 2)) && desk.get(W * (H - 1) + 1) && desk.get(W * (H - 2) + 1)) {
				System.out.println("0 " + (H - 1));
				System.err.println("checkCorners: bottom-left");
				return true;
			}
		}

		// bottom right
		if (!desk.get(W * (H - 1) + W - 1)) {
			if (desk.get(W * (H - 1) + W - 2) && desk.get(W * (H - 2) + W - 1) && desk.get(W * (H - 2) + W - 2)) {
				System.out.println("" + (W - 1) + " " + (H - 1));
				System.err.println("checkCorners: bottom-right");
				return true;
			}
		}

		return false;
	}

	private static boolean topEdge(final BitSet desk, int H, int W) {
		int line = 0;
		int pos = 2;
		for (int x = 0; x < pos; x++) {
			if (desk.get(W + x)) ++line;
		}

		while (pos < W) {
			if (desk.get(W + pos)) ++line;
			if (line == 3 && desk.get(pos) && desk.get(pos - 2)) {
				System.out.println("" + (pos - 1) + " 0");
				System.err.println("topEdge");
				return true;
			}

			if (desk.get(W + pos - 2)) --line;
			++pos;
		}

		return false;
	}

	private static boolean bottomEdge(final BitSet desk, int H, int W) {
		int line = 0;
		int pos = 2;
		for (int x = 0; x < pos; x++) {
			if (desk.get(W * (H - 2) + x)) ++line;
		}

		while (pos < W) {
			if (desk.get(W * (H - 2) + pos)) ++line;
			if (line == 3 && desk.get(W * (H - 1) + pos) && desk.get(W * (H - 1) + pos - 2)) {
				System.out.println("" + (pos - 1) + " " + (H - 1));
				System.err.println("bottomEdge");
				return true;
			}

			if (desk.get(W * (H - 2) + pos - 2)) --line;
			++pos;
		}

		return false;
	}

	private static boolean leftEdge(final BitSet desk, int H, int W) {
		int line = 0;
		int pos = 2;
		for (int y = 0; y < pos; y++) {
			if (desk.get(W * y + 1)) ++line;
		}

		while (pos < W) {
			if (desk.get(W * pos + 1)) ++line;
			if (line == 3 && desk.get(W * pos) && desk.get(W * (pos - 2))) {
				System.out.println("0 " + (pos - 1));
				System.err.println("leftEdge");
				return true;
			}

			if (desk.get(W * (pos - 2) + 1)) --line;
			++pos;
		}

		return false;
	}

	private static boolean rightEdge(final BitSet desk, int H, int W) {
		int line = 0;
		int pos = 2;
		for (int y = 0; y < pos; y++) {
			if (desk.get(W * y + W - 2)) ++line;
		}

		while (pos < W) {
			if (desk.get(W * pos + W - 2)) ++line;
			if (line == 3 && desk.get(W * pos + W - 1) && desk.get(W * (pos - 2) + W - 1)) {
				System.out.println("" + (W - 1) + " " + (pos - 1));
				System.err.println("rightEdge");
				return true;
			}

			if (desk.get(W * (pos - 2) + W - 2)) --line;
			++pos;
		}

		return false;
	}

	private static boolean fullCheck(final BitSet desk, int H, int W) {
		for (int y = 1; y < H - 1; y++) {
			for (int x = 1; x < W - 1; x++) {
				if (desk.get(W * y + x)) continue;
				if (desk.get(W * y + x - 1) && desk.get(W * y + x + 1) && desk.get(W * (y - 1) + x) && desk.get(W * (y + 1) + x)
						&& desk.get(W * (y - 1) + x - 1) && desk.get(W * (y + 1) + x - 1) && desk.get(W * (y - 1) + x + 1)
						&& desk.get(W * (y + 1) + x + 1)) {
					System.out.println("" + x + " " + y);
					System.err.println("full check");
					return true;
				}

			}
		}

		return false;
	}

	private static void printDesk(final BitSet desk, int H, int W) {
		System.err.println("--- DESK ---");
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				System.err.print(desk.get(W * y + x) ? 'X' : ' ');
			}
			System.err.println();
		}
		System.err.println("--- DESK ---");
	}
}
