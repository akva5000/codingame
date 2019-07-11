package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Game Input
 * The program must first read the initialization data from standard input.
 * Then, within an infinite loop, read the data from the standard input related to 
 * the current position of Indy and provide to the standard output the expected data.
 * 
 * Initialization input
 * Line 1: 2 space separated integers W H specifying the width and height of the grid.
 * 
 * Next H lines: each line represents a line in the grid and contains 
 *      W space separated integers T.
 *      T specifies the type of the room.
 * 
 * Last line: 1 integer EX specifying the coordinate along the X axis of the exit 
 * (this data is not useful for this first mission, it will be useful for the second level of this puzzle).
 * 
 * Input for one game turn
 * Line 1: XI YI POS
 *  - (XI, YI) two integers to indicate Indy's current position on the grid.
 *  - POS a single word indicating Indy's entrance point into the current room: 
 *      TOP if Indy enters from above,
 *      LEFT if Indy enters from the left and 
 *      RIGHT if Indy enters from the right.
 * 
 * Output for one game turn
 * A single line with 2 integers: X Y representing the (X, Y) coordinates of the room in which you believe Indy will be on the next turn.
 * 
 * Constraints
 * 0 < W ≤ 20
 * 0 < H ≤ 20
 * 0 ≤ T ≤ 13
 * 0 ≤ EX < W
 * 0 ≤ XI, X < W
 * 0 ≤ YI, Y < H
 * 
 * Response time for one game ≤ 150ms
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	// idx-1 is room type (0-13)
	// idx-2 is in direction: 0-left, 1-top, 2-right
	// value is out direction: 0-left, 1-top, 2-right, 3-down
	private static final int[][] ROOM_TYPES = new int[][] {
			// type 0
			{ -1, -1, -1 },
			// type 1
			{ 3, 3, 3 },
			// type 2
			{ 2, -1, 0 },
			// type 3
			{ -1, 3, -1 },
			// type 4
			{ -1, 0, 3 },
			// type 5
			{ 3, 2, -1 },
			// type 6
			{ 2, -1, 0 },
			// type 7
			{ -1, 3, 3 },
			// type 8
			{ 3, -1, 3 },
			// type 9
			{ 3, 3, -1 },
			// type 10
			{ -1, 0, -1 },
			// type 11
			{ -1, 2, -1 },
			// type 12
			{ -1, -1, 3 },
			// type 13
			{ 3, -1, -1 } };

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int W = in.nextInt(); // number of columns.
		int H = in.nextInt(); // number of rows.
		if (in.hasNextLine()) {
			in.nextLine();
		}

		final int[][] grid = new int[H][W];
		for (int i = 0; i < H; i++) {
			String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer
																		// represents one room of a given type.
			int idx = 0;
			int pos = 0;
			while (pos < LINE.length()) {
				int pos2 = pos;
				while (pos2 < LINE.length() && LINE.charAt(pos2) != ' ') ++pos2;
				grid[i][idx++] = Integer.parseInt(LINE.substring(pos, pos2));
				pos = pos2 + 1;
			}
		}

		int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first
														// mission, but must be read).

		// game loop
		while (true) {
			int XI = in.nextInt();
			int YI = in.nextInt();
			String POS = in.next();

			// One line containing the X Y coordinates of the room in which you believe Indy
			// will be on the next turn.
			final int nextDir; // // 0-left, 2-right, 3-down
			switch (POS.charAt(0)) {
				case 'T':
					nextDir = ROOM_TYPES[grid[YI][XI]][1];
					break;

				case 'L':
					nextDir = ROOM_TYPES[grid[YI][XI]][0];
					break;

				default: // RIGHT
					nextDir = ROOM_TYPES[grid[YI][XI]][2];
					break;
			}

			switch (nextDir) {
				case 0: // left
					System.out.println("" + (XI - 1) + ' ' + YI);
					break;

				case 2: // right
					System.out.println("" + (XI + 1) + ' ' + YI);
					break;

				default: // 3 - down
					System.out.println("" + XI + ' ' + (YI + 1));
					break;
			}
		}
	}
}
