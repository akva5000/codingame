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
public class Player2 {
	private static final int ID_X = 0;
	private static final int ID_Y = 1;
	private static final int ID_FROM = 2;
	private static final int ID_ANGLE = 3;
	
	private static final int STOP = -1;
	private static final int LEFT = 0;
	private static final int TOP = 1;
	private static final int RIGHT = 2;
	private static final int DOWN = 3;
	
	private static final int ANGLE_0 = 0;
	private static final int ANGLE_90 = 90;
	private static final int ANGLE_180 = 180;
	private static final int ANGLE_270 = 270;
	
	private static final int ANGLE_0_MASK =   0B0001;
	private static final int ANGLE_90_MASK =  0B0010;
	private static final int ANGLE_180_MASK = 0B0100;
	private static final int ANGLE_270_MASK = 0B1000;

	// idx-1 is room type (0-13)
	// idx-2 is in-direction: 0-left, 1-top, 2-right, 3-down
	// value is out-direction: 0-left, 1-top, 2-right, 3-down
	private static final byte[][] ROOM_TYPES_0 = new byte[][] {
		  { STOP,  STOP,  STOP, STOP },   // type 0
			{ DOWN,  DOWN,  DOWN, STOP },   // type 1
			{ RIGHT, STOP,  LEFT, STOP },   // type 2
			{ STOP,  DOWN,  STOP, STOP },   // type 3
			{ STOP,  LEFT,  DOWN, STOP },   // type 4
			{ DOWN,  RIGHT, STOP, STOP },   // type 5
			{ RIGHT, STOP,  LEFT, STOP },   // type 6
			{ STOP,  DOWN,  DOWN, STOP },   // type 7
			{ DOWN,  STOP,  DOWN, STOP },   // type 8
			{ DOWN,  DOWN,  STOP, STOP },   // type 9
			{ STOP,  LEFT,  STOP, STOP },   // type 10
			{ STOP,  RIGHT, STOP, STOP },   // type 11
			{ STOP,  STOP,  DOWN, STOP },   // type 12
			{ DOWN,  STOP,  STOP, STOP } }; // type 13

	private static final byte[][] ROOM_TYPES_90 = new byte[][] { 
		  { STOP, STOP, STOP, STOP },   // type 0
			{ STOP, LEFT, LEFT, LEFT },   // type 1
			{ STOP, DOWN, STOP, STOP },   // type 2
			{ STOP, STOP, LEFT, STOP },   // type 3
			{ STOP, STOP, STOP, LEFT },   // type 4
			{ STOP, LEFT, DOWN, STOP },   // type 5
			{ STOP, DOWN, STOP, STOP },   // type 6
			{ STOP, STOP, LEFT, LEFT },   // type 7
			{ STOP, LEFT, STOP, LEFT },   // type 8
			{ STOP, LEFT, LEFT, STOP },   // type 9
			{ STOP, STOP, STOP, STOP },   // type 10
			{ STOP, STOP, DOWN, STOP },   // type 11
			{ DOWN, STOP, STOP, LEFT },   // type 12
			{ STOP, LEFT, STOP, STOP } }; // type 13

	private static final byte[][] ROOM_TYPES_180 = new byte[][] { 
		  { STOP, STOP, STOP, STOP },   // type 0
			{ STOP, STOP, STOP, STOP },   // type 1
			{ STOP, STOP, STOP, STOP },   // type 2 repeat 0
			{ STOP, STOP, STOP, STOP },   // type 3
			{ STOP, STOP, STOP, RIGHT },   // type 4
			{ STOP, STOP, STOP, LEFT },   // type 5
			{ STOP, STOP, STOP, STOP },   // type 6 repeat 0
			{ STOP, STOP, STOP, STOP },   // type 7
			{ STOP, STOP, STOP, STOP },   // type 8
			{ STOP, STOP, STOP, STOP },   // type 9
			{ STOP, STOP, STOP, RIGHT },   // type 10
			{ STOP, STOP, STOP, LEFT },   // type 11
			{ STOP, STOP, STOP, STOP },   // type 12
			{ STOP, STOP, STOP, STOP } }; // type 13

	private static final byte[][] ROOM_TYPES_270 = new byte[][] { 
		  { STOP,  STOP,  STOP, STOP },   // type 0
			{ RIGHT, RIGHT, STOP, RIGHT },   // type 1
			{ STOP,  STOP,  STOP, STOP },   // type 2 repeat 90
			{ RIGHT, STOP,  STOP, STOP },   // type 3
			{ DOWN,  RIGHT, STOP, STOP },   // type 4
			{ STOP,  STOP,  STOP, RIGHT },   // type 5
			{ STOP,  STOP,  STOP, STOP },   // type 6 repeat 90
			{ RIGHT, RIGHT, STOP, STOP },   // type 7
			{ STOP,  RIGHT, STOP, RIGHT },   // type 8
			{ RIGHT, STOP,  STOP, RIGHT },   // type 9
			{ DOWN,  STOP,  STOP, STOP },   // type 10
			{ STOP,  STOP,  STOP, STOP },   // type 11
			{ STOP,  RIGHT, STOP, STOP },   // type 12
			{ STOP,  STOP,  STOP, RIGHT } }; // type 13
			
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
				if(roomType > 0) isRoomRotate.set(i * W + idx); // mark rotating room
				grid[i][idx++] = roomType < 0 ? -roomType : roomType;
				pos = pos2 + 1;
			}
		}

		int EX = in.nextInt(); // the coordinate along the X axis of the exit.
		System.err.println("EXIT=" + EX);
		isRoomRotate.clear((H - 1) * W + EX);

		// -------------- the first turn ------------
		int x = in.nextInt();
		int y = in.nextInt();
		char ch = in.next().charAt(0);
		System.err.println("Turn: " + x + ',' + y + " / " + ch);
		final LinkedList<int[]> path = calculateStrategy(x,
				ch == 'L' ? LEFT : ch == 'T' ? TOP : RIGHT,
				EX, grid, isRoomRotate);
		
		int R = in.nextInt(); // the number of rocks currently in the grid.
		for (int i = 0; i < R; i++) {
			in.nextInt();
			in.nextInt();
			in.next();
		}
		// -------------- the first turn ------------

		final LinkedList<int[]> commands = new LinkedList<>(); // x, y, rotate dir (1-RIGHT, 0-LEFT)
		for(int[] node : path) {
			switch (node[ID_ANGLE]) {
				case ANGLE_90:
					commands.add(new int[] {node[ID_X], node[ID_Y], 1});
					break;

				case ANGLE_180:
					commands.add(new int[] {node[ID_X], node[ID_Y], 1});
					commands.add(new int[] {node[ID_X], node[ID_Y], 1});
					break;
					
				case ANGLE_270:
					commands.add(new int[] {node[ID_X], node[ID_Y], 0});
					break;
					
				default: // 0
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
		System.out.println( cmd[2] == 1 ? " RIGHT" : " LEFT");
	}

	static LinkedList<int[]> calculateStrategy(int x, int from, int EX, final int[][] grid,
			final BitSet isRoomRotate) {
		System.err.println(">>calc: x=" + x + ", from=" + from + ". EX=" + EX);
		int H = grid.length;
		int W = grid[0].length;
		System.err.println("\n\n");
		
		final byte[][][] angleTried = new byte[H][W][4]; // bits x3x2x1x0: 0-0,1-90,2-180,3-270
		final LinkedList<int[]> path = new LinkedList<>();

		final int[] pos = new int[] { x, 0, from, ANGLE_0 }; // x, y, from direction, angle
		path.addLast(pos);
		angleTried[pos[ID_Y]][pos[ID_X]][from] = ANGLE_0_MASK; // fix angle 0
		System.err.println("+ node: " + nodePath(grid, pos));

		while ( path.peekLast()[ID_Y] != H - 1 || path.peekLast()[ID_X] != EX) {
			// calculate next room
			int[] node = path.peekLast();
			int roomType = grid[node[ID_Y]][node[ID_X]];

			byte[][] roomRotate_table = 
					node[ID_ANGLE] == ANGLE_0 ?
							ROOM_TYPES_0
							: node[ID_ANGLE] == ANGLE_90 ?
									ROOM_TYPES_90
									: node[ID_ANGLE] == ANGLE_180 ?
											ROOM_TYPES_180
											: ROOM_TYPES_270;

			int[] nextNode;
			switch (roomRotate_table[roomType][node[ID_FROM]]) {
				case LEFT: // to LEFT -> from RIGHT
					nextNode = new int[] { node[ID_X] - 1, node[ID_Y], RIGHT, ANGLE_0 };
					break;

				case RIGHT: // to RIGHT -> from LEFT
					nextNode = new int[] { node[ID_X] + 1, node[ID_Y], LEFT, ANGLE_0 };
					break;

				case DOWN: // to Bottom -> from TOP
					nextNode = new int[] { node[ID_X], node[ID_Y] + 1, TOP, ANGLE_0 };
					break;

				default: // to TOP -> from BOTTOM
					nextNode = null;
					break;
			}
			
			if(nextNode == null) {
				System.err.println("[BLOCK] node: " + nodePath(grid, nextNode) + " next node goes TOP");
				nextNode = path.removeLast();
				System.err.println("- node: " + nodePath(grid, nextNode));
				continue;
			}	
			
			if (nextNode[ID_X] < 0 || nextNode[ID_X] >= W || nextNode[ID_Y] < 0 || nextNode[ID_Y] >= H) {
				System.err.println("[BLOCK] node: " + outNodePath(grid, nextNode) + " next node out of grid");
				nextNode = path.removeLast();
				System.err.println("- node: " + nodePath(grid, nextNode));
				continue;
			} 
			
			if (grid[nextNode[ID_Y]][nextNode[ID_X]] == 0) {
				// loop detected
				System.err.println("[BLOCK] node: " + nodePath(grid, nextNode) + " next cell is monolith");
				nextNode = path.removeLast();
				System.err.println("- node: " + nodePath(grid, nextNode));
				continue;
			} 
			
			if (checkIfNodePresent(nextNode[ID_X], nextNode[ID_Y], path)) {
				// loop detected
				System.err.println("[BLOCK] node: " + nodePath(grid, nextNode) + " loop detected");
				nextNode = path.removeLast();
				System.err.println("- node: " + nodePath(grid, nextNode));
				continue;
			} 
			
			// no loop
			// calculate next node angle
			if(angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] == 0B01111) {
				System.err.println("[BLOCK] node: " + nodePath(grid, nextNode) + " terminal node, no other angles possible");
				nextNode = path.removeLast();
				System.err.println("- node: " + nodePath(grid, nextNode));
				continue;
			} 
			
			if (isRoomRotate.get(nextNode[ID_Y] * W + nextNode[ID_X])) { // rotate room
				int nextType = grid[nextNode[ID_Y]][nextNode[ID_X]];

				// pre-check
				if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] & ANGLE_0_MASK) == 0
						&& ROOM_TYPES_0[nextType][nextNode[ID_FROM]] == -1)
					angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] |= ANGLE_0_MASK;

				if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] & ANGLE_90_MASK) == 0
						&& ROOM_TYPES_90[nextType][nextNode[ID_FROM]] == -1)
					angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] |= ANGLE_90_MASK;

				if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] & ANGLE_180_MASK) == 0
						&& ROOM_TYPES_180[nextType][nextNode[ID_FROM]] == -1)
					angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] |= ANGLE_180_MASK;

				if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] & ANGLE_270_MASK) == 0
						&& ROOM_TYPES_270[nextType][nextNode[ID_FROM]] == -1)
					angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] |= ANGLE_270_MASK;

				// main check
				if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] & ANGLE_0_MASK) == 0) {
					// angle 0
					nextNode[ID_ANGLE] = ANGLE_0;
					angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] |= ANGLE_0_MASK;
					path.addLast(nextNode);
					System.err.println("+ node: " + nodePath(grid, nextNode));
					continue;
				} 
				
				if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] & ANGLE_90_MASK) == 0) {
					// angle 90
					nextNode[ID_ANGLE] = ANGLE_90;
					angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] |= ANGLE_90_MASK;
					path.addLast(nextNode);
					System.err.println("+ node: " + nodePath(grid, nextNode));
					continue;
				} 
				
				if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] & ANGLE_180_MASK) == 0) {
					// angle 180
					nextNode[ID_ANGLE] = ANGLE_180;
					angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] |= ANGLE_180_MASK;
					path.addLast(nextNode);
					System.err.println("+ node: " + nodePath(grid, nextNode));
					continue;
				} 
				
				if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] & ANGLE_270_MASK) == 0) {
					// angle 270
					nextNode[ID_ANGLE] = ANGLE_270;
					angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] |= ANGLE_270_MASK;
					path.addLast(nextNode);
					System.err.println("+ node: " + nodePath(grid, nextNode));
					continue;
				} 

				// no angles possible
				System.err.println("[BLOCK] node: " + nodePath(grid, nextNode) + " no other angles possible");
				nextNode = path.removeLast();
				System.err.println("- node: " + nodePath(grid, nextNode));

			} else { // not rotate room
				if ((angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] & ANGLE_0_MASK) != 0) {
					System.err.println("[BLOCK] node: " + nodePath(grid, nextNode) + " static node goes to block");
					nextNode = path.removeLast();
					System.err.println("- node: " + nodePath(grid, nextNode));
				} else {
					angleTried[nextNode[ID_Y]][nextNode[ID_X]][nextNode[ID_FROM]] |= ANGLE_0_MASK; // 0 degrees
					path.addLast(nextNode);
					System.err.println("+ node: " + nodePath(grid, nextNode));
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

	private static String nodePath(final int[][] grid, final int[] node) {
		byte[][] roomRotate_table = 
				node[ID_ANGLE] == ANGLE_0 ?
						ROOM_TYPES_0
						: node[ID_ANGLE] == ANGLE_90 ? ROOM_TYPES_90 : ROOM_TYPES_270;
		
		return node[ID_X] + "," + node[ID_Y] + " (" + grid[node[ID_Y]][node[ID_X]] + ") " 
				+ dirConv(node[ID_FROM]) + "->" + dirConv(roomRotate_table[grid[node[ID_Y]][node[ID_X]]][node[ID_FROM]])  + ", AN=" + node[ID_ANGLE];
	}
	
	private static String outNodePath(final int[][] grid, final int[] node) {
		return node[ID_X] + "," + node[ID_Y] + " (OUT)";
	}
	
	private static String dirConv(int dir) {
		return dir == LEFT ? "LEFT"
				: dir == TOP ? "TOP"
						: dir == RIGHT ? "RIGHT" : "DOWN";
	}

}
