package org.vadim;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Game Input
 * The program must first read the initialization data from standard input.
 * Then, within an infinite loop, read the data from the standard input related 
 * to the current position of Indy and provide to the standard output the expected data.
 * 
 * Initialization input
 * Line 1 : 2 space separated integers W H specifying the width and height of the grid.
 * Next H lines: Each line represents a line in the grid and contains W space separated integers T.
 * 	T specifies the type of the room.
 * 	If T is negative, the room cannot be rotated.
 * Last line: 1 integer EX specifying the coordinate along the X axis of the exit (always at the very bottom of the grid).
 * 
 * Input for one game turn
 * Line 1 : XI YI POSI
 *  - (XI, YI) two integers to indicate Indy's current position on the grid.
 *  - POSI a single word indicating Indy's entrance point into the current room:
 *    - TOP if Indy enters from above,
 *    - LEFT if Indy enters from the left and
 *    - RIGHT if Indy enters from the right.
 * 
 * Next line: R an integer representing the number of rocks currently in the grid.
 * 
 * Next R lines: each line represents the position of a rock on the grid: XR YR POSR (same rules as Indy's XI YI POSI).
 * 
 * Output for one game turn
 * One line containing on of three commands:
 * 
 *     X Y LEFT : rotate the room at (X Y) by a quarter turn counterclockwise
 *     X Y RIGHT : rotate the room at (X Y) by a quarter turn clockwise
 *     WAIT : don't rotate a room and wait for next turn
 * 
 * Constraints
 * 0 < W ≤ 20
 * 0 < H ≤ 20
 * 0 ≤ T ≤ 13
 * 0 ≤ R ≤ 10
 * 0 ≤ EX < W
 * 0 ≤ XI, XR, X < W
 * 0 ≤ YI, YR, Y < H
 * Response time for one game ≤ 150ms
 * </pre>
 * 
 * @author akva
 */
public class Player1 {
	// idx-1 is room type (0-13)
	// idx-2 is in-direction: 0-left, 1-top, 2-right, 3-down
	// value is out-direction: 0-left, 1-top, 2-right, 3-down
	private static final byte[][] ROOM_TYPES_0 = new byte[][] {
		  { -1, -1, -1, -1 },   // type 0
			{  3,  3,  3, -1 },   // type 1
			{  2, -1,  0, -1 },   // type 2
			{ -1,  3, -1, -1 },   // type 3
			{ -1,  0,  3, -1 },   // type 4
			{  3,  2, -1, -1 },   // type 5
			{  2, -1,  0, -1 },   // type 6
			{ -1,  3,  3, -1 },   // type 7
			{  3, -1,  3, -1 },   // type 8
			{  3,  3, -1, -1 },   // type 9
			{ -1,  0, -1, -1 },   // type 10
			{ -1,  2, -1, -1 },   // type 11
			{ -1, -1,  3, -1 },   // type 12
			{  3, -1, -1, -1 } }; // type 13

	private static final byte[][] ROOM_TYPES_90 = new byte[][] { 
		  { -1, -1, -1, -1 },   // type 0
			{  1,  0,  0,  0 },   // type 1
			{ -1,  3, -1,  1 },   // type 2
			{ -1, -1,  0, -1 },   // type 3
			{ -1, -1,  1,  0 },   // type 4
			{ -1,  0,  3, -1 },   // type 5
			{ -1,  3, -1,  1 },   // type 6
			{ -1, -1,  0,  0 },   // type 7
			{ -1,  0, -1,  0 },   // type 8
			{ -1,  0,  0, -1 },   // type 9
			{ -1, -1,  1, -1 },   // type 10
			{ -1, -1,  3, -1 },   // type 11
			{  3, -1, -1,  0 },   // type 12
			{ -1,  0, -1, -1 } }; // type 13

	private static final byte[][] ROOM_TYPES_180 = new byte[][] { 
		  { -1, -1, -1, -1 },   // type 0
			{  1, -1,  1,  1 },   // type 1
			{  2, -1,  0, -1 },   // type 2
			{ -1, -1, -1,  1 },   // type 3
			{  1, -1, -1,  2 },   // type 4
			{ -1, -1,  1,  0 },   // type 5
			{  2, -1,  0, -1 },   // type 6
			{  1, -1, -1,  1 },   // type 7
			{  1, -1,  1, -1 },   // type 8
			{ -1, -1,  1,  1 },   // type 9
			{ -1, -1, -1,  2 },   // type 10
			{ -1, -1, -1,  0 },   // type 11
			{  1, -1, -1, -1 },   // type 12
			{ -1, -1,  1, -1 } }; // type 13

	private static final byte[][] ROOM_TYPES_270 = new byte[][] { 
		  { -1, -1, -1, -1 },   // type 0
			{  2,  2, -1,  2 },   // type 1
			{ -1,  3, -1,  1 },   // type 2
			{  2, -1, -1, -1 },   // type 3
			{  3,  2, -1, -1 },   // type 4
			{  1, -1, -1,  2 },   // type 5
			{ -1, -1, -1, -1 },   // type 6
			{ -1,  3, -1,  1 },   // type 7
			{ -1,  2, -1,  2 },   // type 8
			{  2, -1, -1,  2 },   // type 9
			{  3, -1, -1, -1 },   // type 10
			{  1, -1, -1, -1 },   // type 11
			{ -1,  2, -1, -1 },   // type 12
			{ -1, -1, -1,  2 } }; // type 13
			
	private static final int ID_X = 0;
	private static final int ID_Y = 1;
	private static final int ID_FROM = 2;
	private static final int ID_ANGLE = 3;

	public static void main(String args[]) {
		final Scanner in = new Scanner(System.in);
		int W = in.nextInt(); // number of columns.
		int H = in.nextInt(); // number of rows.
		if (in.hasNextLine()) {
			in.nextLine();
		}
		System.err.println("SIZE " + W + " x " + H);

		final int[][] grid = new int[H][W];
		final BitSet isRoomRotate = new BitSet(W * H);

		for (int i = 0; i < H; i++) {
			String LINE = in.nextLine(); // each line represents a line in the grid and contains W integers T. The
																		// absolute value of T specifies the type of the room. If T is negative, the
																		// room cannot be rotated.
			System.err.println("  " + LINE);
			int idx = 0;
			int pos = 0;
			while (pos < LINE.length()) {
				int pos2 = pos;
				while (pos2 < LINE.length() && LINE.charAt(pos2) != ' ') ++pos2;
				int roomType = Integer.parseInt(LINE.substring(pos, pos2));
				grid[i][idx++] = Math.abs(roomType);
				if (roomType > 0) isRoomRotate.set(i * W + idx); // mark rotating room
				pos = pos2 + 1;
			}
		}

		int EX = in.nextInt(); // the coordinate along the X axis of the exit.
		System.err.println("EXIT=" + EX);

		// -------------- the first turn ------------
		int x = in.nextInt();
		int y = in.nextInt();
		char ch = in.next().charAt(0);
		System.err.println("Turn: " + x + ',' + y + " / " + ch);
		final LinkedList<int[]> path = calculateStrategy(x, y,
				ch == 'L' ? 0 : ch == 'T' ? 1 : 2,
				H, W, EX, grid, isRoomRotate);
		
		int R = in.nextInt(); // the number of rocks currently in the grid.
		for (int i = 0; i < R; i++) {
			in.nextInt();
			in.nextInt();
			in.next();
		}
		// -------------- the first turn ------------

		final LinkedList<int[]> commands = new LinkedList<>(); // x, y, rotate dir (1-RIGHT, 0-LEFT)
		for(int[] node : path) {
			if (!isRoomRotate.get(node[ID_Y] * W + node[ID_X]) || node[ID_ANGLE] == 0) continue;
			
			switch (node[ID_ANGLE]) {
				case 90:
					commands.add(new int[] {node[ID_X], node[ID_Y], 1});
					break;

				case 180:
					commands.add(new int[] {node[ID_X], node[ID_Y], 1});
					commands.add(new int[] {node[ID_X], node[ID_Y], 1});
					break;
					
				default: // 270
					commands.add(new int[] {node[ID_X], node[ID_Y], 0});
					break;
			}
		}
		
		if(commands.isEmpty()) {
			System.out.println("WAIT");
		} else {
			writeCommand(commands);
		}
		
		// -------------- the first turn ------------
		
		// game loop
		final List<Integer> xRocks = new ArrayList<>(10);
		final List<Integer> yRocks = new ArrayList<>(10);
		while (true) {
			in.nextInt();
			in.nextInt();
			in.next();

			xRocks.clear();
			yRocks.clear();
			R = in.nextInt(); // the number of rocks currently in the grid.
			for (int i = 0; i < R; i++) {
				xRocks.add(in.nextInt());
				yRocks.add(in.nextInt());
				String POSR = in.next();
			}

			if(commands.isEmpty()) {
				System.out.println("WAIT");
			} else {
				writeCommand(commands);
			}
		} // while
	}

	private static void writeCommand(final LinkedList<int[]> commands) {
		int[] cmd = commands.pop();
		System.out.print("" + cmd[ID_X] + ' ' + cmd[ID_Y] );
		if(cmd[2] == 1) {
			System.out.println(" RIGHT");
		} else {
			System.out.println(" LEFT");
		}
	}

	static LinkedList<int[]> calculateStrategy(int x, int y, int from, int H, int W, int EX, final int[][] grid,
			final BitSet isRoomRotate) {
		final byte[][] angleTried = new byte[H][W]; // bits x3x2x1x0: 0-0,1-90,2-180,3-270
		final LinkedList<int[]> path = new LinkedList<>();

		final int[] pos = new int[] { x, y, from, 0 }; // x, y, from direction, angle
		path.addLast(pos);
		angleTried[pos[ID_Y]][pos[ID_X]] = 0B01; // fix angle 0
		System.err.println("+ node: " + pos[ID_X] + "," + pos[ID_Y] + " / " + from + " | 0");

		while (path.peekLast()[ID_Y] != H - 1 || path.peekLast()[ID_X] != EX) {
			// calculate next room
			int[] node = path.peekLast();
			int roomType = grid[node[ID_Y]][node[ID_X]];

			byte[][] roomRotate_table = 
					node[ID_ANGLE] == 0 ?
							ROOM_TYPES_0
							: node[ID_ANGLE] == 90 ?
									ROOM_TYPES_90
									: node[ID_ANGLE] == 180 ?
											ROOM_TYPES_180
											: ROOM_TYPES_270;

			int[] nextNode;
			switch (roomRotate_table[roomType][node[ID_FROM]]) {
				case 0: // to LEFT -> from RIGHT
					nextNode = new int[] { node[ID_X] - 1, node[ID_Y], 2, 0 };
					break;

				case 2: // to RIGHT -> from LEFT
					nextNode = new int[] { node[ID_X] + 1, node[ID_Y], 0, 0 };
					break;

				case 3: // to Bottom -> from TOP
					nextNode = new int[] { node[ID_X], node[ID_Y] + 1, 1, 0 };
					break;

				default: // to TOP - error
					System.err.println("ERROR: turn to TOP, coord=" + node[ID_X] +"," + node[ID_Y]);
					nextNode = null;
					break;
			}
			
			if(nextNode == null) {
				nextNode = path.removeLast();
				System.err.println("- node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);
			} else if (nextNode[ID_X] < 0 || nextNode[ID_X] >= W || nextNode[ID_Y] < 0 || nextNode[ID_Y] >= H) {
				System.err.println("ERROR: next node out of grid, coord=" + nextNode[ID_X] + "," + nextNode[ID_Y]);
				nextNode = path.removeLast();
				System.err.println("- node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);
			} else if (grid[nextNode[ID_Y]][nextNode[ID_X]] == 0) {
				// loop detected
				System.err.println("ERROR: next cell is monolith, coord=" + nextNode[ID_X] + "," + nextNode[ID_Y] );
				nextNode = path.removeLast();
				System.err.println("- node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);

			} else if (checkIfNodePresent(nextNode[ID_X], nextNode[ID_Y], path)) {
				// loop detected
				System.err.println("ERROR: loop detected, coord=" + nextNode[ID_X] + "," + nextNode[ID_Y]);
				nextNode = path.removeLast();
				System.err.println("- node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);

			} else { // no loop
				// calculate next node angle
				if(angleTried[nextNode[ID_Y]][nextNode[ID_X]] == 0B01111) {
					System.err.println("ERROR: terminal node, no other angles possible, coord=" + nextNode[ID_X] + "," + nextNode[ID_Y]);
					nextNode = path.removeLast();
					System.err.println("- node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);
					
				} else if (isRoomRotate.get(nextNode[ID_Y] * W + nextNode[ID_X])) { // rotate room
					int nextType = grid[nextNode[ID_Y]][nextNode[ID_X]];
					
					// pre-check
					if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]] & 0B01) == 0B0
							&& ROOM_TYPES_0[nextType][nextNode[ID_FROM]] == -1)
						angleTried[nextNode[ID_Y]][nextNode[ID_X]] |= 0B01;
					
					if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]] & 0B010) == 0B0
							&& ROOM_TYPES_90[nextType][nextNode[ID_FROM]] == -1)
						angleTried[nextNode[ID_Y]][nextNode[ID_X]] |= 0B010;
					
					if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]] & 0B0100) == 0B0
							&& ROOM_TYPES_180[nextType][nextNode[ID_FROM]] == -1)
						angleTried[nextNode[ID_Y]][nextNode[ID_X]] |= 0B0100;
					
					if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]] & 0B01000) == 0B0
							&& ROOM_TYPES_270[nextType][nextNode[ID_FROM]] == -1)
						angleTried[nextNode[ID_Y]][nextNode[ID_X]] |= 0B01000;

					// main check
					if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]] & 0B01) == 0B0) {
						// angle 0
						nextNode[ID_ANGLE] = 0;
						angleTried[nextNode[ID_Y]][nextNode[ID_X]] |= 0B01;
						path.addLast(nextNode);
						System.err.println("+ node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);
					} else if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]] & 0B010) == 0B0) {
						// angle 90
						nextNode[ID_ANGLE] = 90;
						angleTried[nextNode[ID_Y]][nextNode[ID_X]] |= 0B010;
						path.addLast(nextNode);
						System.err.println("+ node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);
					} else if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]] & 0B0100) == 0B0) {
						// angle 180
						nextNode[ID_ANGLE] = 180;
						angleTried[nextNode[ID_Y]][nextNode[ID_X]] |= 0B0100;
						path.addLast(nextNode);
						System.err.println("+ node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);
					} else if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]] & 0B01000) == 0B0) {
						// angle 270
						nextNode[ID_ANGLE] = 270;
						angleTried[nextNode[ID_Y]][nextNode[ID_X]] |= 0B01000;
						path.addLast(nextNode);
						System.err.println("+ node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);
					} else {
						// no angles possible
						System.err.println("ERROR: no other angles possible, coord=" + nextNode[ID_X] + "," + nextNode[ID_Y]);
						nextNode = path.removeLast();
						System.err.println("- node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);
					}

				} else { // not rotate room
					angleTried[nextNode[ID_Y]][nextNode[ID_X]] |= 0B01; // 0 degrees
					path.addLast(nextNode);
					System.err.println("+ node: " + nextNode[ID_X] + "," + nextNode[ID_Y] + " / " + nextNode[ID_FROM] + " | " + nextNode[ID_ANGLE]);
				}
			}
		} // while
		
		return path;
	}

	private static boolean checkIfNodePresent(int x, int y, final LinkedList<int[]> path) {
		for (int[] node : path) {
			if (node[ID_X] == x && node[ID_Y] == y) return true;
		}
		return false;
	}

}
