package org.vadim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: the number of lines L and columns C on the map, separated by a space.
 * The following L lines: a line of the length C representing a line on the map.
 *   A line can contain the characters #, X, @, $, S, E, N, W, B, I, T and space character.
 * 
 * Output:
 * - If Bender can reach $, then display the sequence of moves he has taken.
 *   One move per line: SOUTH for the South, EAST for the East, NORTH for the North and WEST for the west.
 * - If Bender cannot reach $, then only display LOOP.
 * 
 * Constraints
 * XXX
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	static int posX;
	static int posY;
	static byte head = 0; // 0-SOUTH, 1-EAST, 2-NORTH, 3-WEST
	static boolean isBreakerMode = false;
	static boolean isReversedPriorities = false;

	static char[][] field;
	static int[][] teleports = null;
	static final List<Integer> moves = new ArrayList<>();
	static final List<int[]> path = new ArrayList<>();

	public static void main(String args[]) {
		head = 0;
		isBreakerMode = false;
		isReversedPriorities = false;
		teleports = null;
		moves.clear();
		path.clear();

		Scanner in = new Scanner(System.in);

		int L = in.nextInt();
		int C = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		// skip first line
		in.nextLine();

		L -= 2;
		C -= 2;
		field = new char[L][C];

		for (int i = 0; i < L; i++) {
			String row = in.nextLine();
			for (int j = 0; j < C; j++) {
				field[i][j] = row.charAt(j + 1);
				if (field[i][j] == '@') {
					posY = i;
					posX = j;
				} else if (field[i][j] == 'T') {
					if (teleports == null) teleports = new int[][] { { -1, -1 }, { -1, -1 } }; // {y,x}
					int idx = teleports[0][0] == -1 ? 0 : 1;
					teleports[idx][0] = i;
					teleports[idx][1] = j;
				}
			}
		}

		// the first point
		path.add(new int[] { posY, posX, 1 });

		// skip last line
		if (in.hasNextLine()) in.nextLine();

		while (true) {
			// next step
			// check possibility of the next step
			if (!checkNextPossybility() && !selectAnotherDirection()) {
				System.out.println("LOOP");
				break;
			}

			if (!makeTurn()) {
				System.out.println("LOOP");
				break;
			}

			if (!handlePosition()) {
				for (Integer step : moves) {
					System.out.println(step == 0 ? "SOUTH" : step == 1 ? "EAST" : step == 2 ? "NORTH" : "WEST");
				}
				break;
			}
		}
	}

	private static boolean checkNextPossybility() {
		boolean rc;
		switch (head) {
			case 0: // SOUTH
				rc = (posY + 1 < field.length) && field[posY + 1][posX] != '#' && (isBreakerMode || field[posY + 1][posX] != 'X');
				break;

			case 1: // EAST
				rc = (posX + 1 < field[0].length) && field[posY][posX + 1] != '#' && (isBreakerMode || field[posY][posX + 1] != 'X');
				break;

			case 2: // NORTH
				rc = (posY - 1 > -1) && field[posY - 1][posX] != '#' && (isBreakerMode || field[posY - 1][posX] != 'X');
				break;

			default: // WEST
				rc = (posX - 1 > -1) && field[posY][posX - 1] != '#' && (isBreakerMode || field[posY][posX - 1] != 'X');
				break;
		}

		return rc;
	}

	private static boolean selectAnotherDirection() {
		if (isReversedPriorities) {
			for (byte i = (byte) (head == 3 ? 2 : 3); i >= 0; i--) {
				head = i;
				if (checkNextPossybility()) return true;
			}

		} else {
			for (byte i = (byte) (head == 0 ? 1 : 0); i <= 3; i++) {
				head = i;
				if (checkNextPossybility()) return true;
			}
		}

		return false;
	}

	// step into the next cell and handle the signal
	private static boolean makeTurn() {
		moves.add((int) head);

		// step
		switch (head) {
			case 0: // SOUTH
				++posY;
				break;

			case 1: // EAST
				++posX;
				break;

			case 2: // NORTH
				--posY;
				break;

			default: // WEST
				--posX;
				break;
		}

		// check for loop
		boolean isFound = false;
		for (int[] point : path) {
			if (posY == point[0] && posX == point[1]) {
				if (point[2] > 10) return false;
				++point[2];
				isFound = true;
				break;
			}
		}

		if (!isFound) {
			path.add(new int[] { posY, posX, 1 });
		}
		return true;
	}

	private static boolean handlePosition() {
		// signal
		switch (field[posY][posX]) {
			case 'I': // switch priorities order
				isReversedPriorities = !isReversedPriorities;
				break;

			case 'B': // switch Breaker mode
				isBreakerMode = !isBreakerMode;
				break;

			case 'S': // turn south
				head = 0;
				break;

			case 'E': // turn east
				head = 1;
				break;

			case 'N': // turn north
				head = 2;
				break;

			case 'W': // turn west
				head = 3;
				break;

			case 'T': // teleport
				int idx = posY == teleports[0][0] && posX == teleports[0][1] ? 1 : 0;
				posY = teleports[idx][0];
				posX = teleports[idx][1];
				break;

			case 'X': // obstacle
				if (isBreakerMode) field[posY][posX] = ' ';
				break;

			case '$': // END
				return false;

			default:
				break;
		}

		return true;
	}
}
