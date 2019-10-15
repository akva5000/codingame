package org.vadim;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <pre>
 * Input:
 * The moves in one single line, for example B' possibly with dots (for human readability, you can ignore them).
 * R2L2UD'.F2B2UD'
 * Possible commands:
 * X
 * XN
 * X'
 * X'N
 * 
 * 
 * Output:
 * The colours in two lines, for example:
 * FF
 * RR
 * 
 * Constraints
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	private static final int[][] FRONT = new int[][] { new int[] { 3, 14, 16, 5 }, new int[] { 2, 12, 17, 7 }, new int[] { 8, 9, 11, 10 } };
	private static final int[][] LEFT = new int[][] { new int[] { 0, 8, 16, 20 }, new int[] { 2, 10, 18, 22 }, new int[] { 4, 5, 7, 6 } };
	private static final int[][] RIGHT = new int[][] { new int[] { 9, 1, 21, 17 }, new int[] { 11, 3, 23, 19 }, new int[] { 12, 13, 15, 14 } };
	private static final int[][] UP = new int[][] { new int[] { 8, 4, 23, 12 }, new int[] { 9, 5, 22, 13 }, new int[] { 0, 1, 3, 2 } };
	private static final int[][] DOWN = new int[][] { new int[] { 7, 11, 15, 20 }, new int[] { 6, 10, 14, 21 }, new int[] { 16, 17, 19, 18 } };
	private static final int[][] BACK = new int[][] { new int[] { 0, 6, 19, 13 }, new int[] { 1, 4, 18, 15 }, new int[] { 20, 21, 23, 22 } };

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		final char[] cube = new char[24];
		Arrays.fill(cube, 0, 4, 'U');
		Arrays.fill(cube, 4, 8, 'L');
		Arrays.fill(cube, 8, 12, 'F');
		Arrays.fill(cube, 12, 16, 'R');
		Arrays.fill(cube, 16, 20, 'D');
		Arrays.fill(cube, 20, 24, 'B');

		String move = in.next();
		System.err.println("COMMANDS: " + move);
		for (final StringTokenizer tok = new StringTokenizer(move, "."); tok.hasMoreTokens();) {
			String block = tok.nextToken().trim();
			int pos = 0;
			while (pos < block.length()) {
				int pos2 = nextLetter(block, pos + 1);
				handleCommand(block.substring(pos, pos2), cube);
				pos = pos2;
			}
		}

		printFront(cube);
	}

	private static int nextLetter(final String block, int from) {
		for (int i = from; i < block.length(); i++) {
			switch (block.charAt(i)) {
				case 'U':
				case 'L':
				case 'R':
				case 'F':
				case 'B':
				case 'D':
					return i;

				default:
					break;
			}
		}

		return block.length();
	}

	private static void handleCommand(String cmd, final char[] cube) {
		boolean isClockwise = true;
		int rounds = 1;
		char side = cmd.charAt(0);

		if (cmd.length() == 2) {
			if (cmd.charAt(1) == '\'') {
				isClockwise = false;
			} else {
				rounds = Integer.parseInt(cmd.substring(1));
			}
		} else if(cmd.length() == 3) { // 3 parts
			isClockwise = false;
			rounds = Integer.parseInt(cmd.substring(2));
		}

		for (int i = 0; i < rounds; i++) {
			final int[][] rules;
			switch (side) {
				case 'U':
					rules = UP;
					break;
					
				case 'L':
					rules = LEFT;
					break;
					
				case 'R':
					rules = RIGHT;
					break;
					
				case 'F':
					rules = FRONT;
					break;
					
				case 'B':
					rules = BACK;
					break;
					
				case 'D':
					rules = DOWN;
					break;
					
				default:
					throw new IllegalStateException("Unknown side: " + side);
			}
			
			if(isClockwise) {
				makeTurnClockwise(rules, cube);
			} else {
				makeTurnCounterClockwise(rules, cube);
			}
		}
	}

	private static void makeTurnClockwise(final int[][] rules, final char[] cube) {
		for (int i = 0; i < 3; i++) {
			final int[] path = rules[i];
			final char t = cube[path[3]];
			for (int j = 3, k = 2; j > 0; j--, k--) cube[path[j]] = cube[path[k]];
			cube[path[0]] = t;
		}
	}

	private static void makeTurnCounterClockwise(final int[][] rules, final char[] cube) {
		for (int i = 0; i < 3; i++) {
			final int[] path = rules[i];
			final char t = cube[path[0]];
			for (int j = 0, k = 1; j < 3; j++, k++) cube[path[j]] = cube[path[k]];
			cube[path[3]] = t;
		}
	}

	private static void printFront(final char[] cube) {
		System.out.print(cube[8]);
		System.out.println(cube[9]);
		System.out.print(cube[10]);
		System.out.println(cube[11]);
	}
}
