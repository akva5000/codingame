package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input
 * - First line : the position of the white rook
 * - Second line : the amount P of pieces to be placed on the board
 * - Next P lines : P space-separated lines containing attributes for each piece : color position
 * 
 * color is either 0 (WHITE) or 1 (BLACK)
 * position is the chessboard-notation of the position of the given piece
 * 
 * Output
 * - A list of space-separated combination where combination has the following shape :
 * 
 * Rfrom_positionactionto_position with from_position being the actual position of the white rook and to_position being a possible destination for the white rook, both in the chessboard-position format. action should be x if the movement involves taking an opponent pieces, - otherwise.
 * 
 * For instance, if the white rook is in d5 and can move to d6, then the output must be Rd5-d6.
 * If the white rook is in e4 and can capture an opponent piece in e3, then the output must be Re4xe3
 * 
 * 
 * Moves must be sorted in ascending lexicographical ASCII order
 * </pre>
 * 
 * @author akva
 *
 */
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int[][] desk = new int[8][8]; // a-f;1-7

		String rookPosition = in.next();
		int x = rookPosition.charAt(0) - 'a';
		int y = rookPosition.charAt(1) - '0';
		--y;
		desk[y][x] = 9;

		int nbPieces = in.nextInt();
		for (int i = 0; i < nbPieces; i++) {
			int colour = in.nextInt();
			String onePiece = in.next();
			int px = onePiece.charAt(0) - 'a';
			int py = onePiece.charAt(1) - '0';
			--py;
			desk[py][px] = (colour == 0 ? 1 : 2);
		}

		calculateHorizontalMovements(desk, x, y);
		calculateVerticalMovements(desk, x, y);
		printMovements(desk, rookPosition);
		printDesk(desk);
	}

	private static void calculateHorizontalMovements(int[][] desk, int x, int y) {
		boolean flag = false;
		for (int dx = x + 1; dx < 8; dx++) {
			flag = false;
			switch (desk[y][dx]) {
				case 0:
					desk[y][dx] = 3;
					break;

				case 1:
					flag = true;
					break;

				case 2: // black peace
					desk[y][dx] = 4;
					flag = true;
					break;

				default:
					break;
			}

			if (flag) break;
		}

		for (int dx = x - 1; dx >= 0; dx--) {
			flag = false;
			switch (desk[y][dx]) {
				case 0:
					desk[y][dx] = 3;
					break;

				case 1:
					flag = true;
					break;

				case 2: // black peace
					desk[y][dx] = 4;
					flag = true;
					break;

				default:
					break;
			}

			if (flag) break;
		}
	}

	private static void calculateVerticalMovements(int[][] desk, int x, int y) {
		boolean flag = false;
		for (int dy = y + 1; dy < 8; dy++) {
			flag = false;
			switch (desk[dy][x]) {
				case 0:
					desk[dy][x] = 3;
					break;

				case 1:
					flag = true;
					break;

				case 2: // black peace
					desk[dy][x] = 4;
					flag = true;
					;
					break;

				default:
					break;
			}

			if (flag) break;
		}

		for (int dy = y - 1; dy >= 0; dy--) {
			flag = false;
			switch (desk[dy][x]) {
				case 0:
					desk[dy][x] = 3;
					break;

				case 1:
					flag = true;
					break;

				case 2: // black peace
					desk[dy][x] = 4;
					flag = true;
					break;

				default:
					break;
			}

			if (flag) break;
		}
	}

	private static void printMovements(int[][] desk, String initCoords) {
		final StringBuilder buf = new StringBuilder(2);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (desk[y][x] != 3) continue;
				System.out.print('R');
				System.out.print(initCoords);
				System.out.print('-');
				convertCoords(x, y, buf);
				System.out.println(buf.toString());
			}
		}
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (desk[y][x] != 4) continue;
				System.out.print('R');
				System.out.print(initCoords);
				System.out.print('x');
				convertCoords(x, y, buf);
				System.out.println(buf.toString());
			}
		}
	}

	private static void convertCoords(int x, int y, final StringBuilder buf) {
		buf.setLength(0);
		buf.append((char) ('a' + x)).append((char) ('0' + (y + 1)));
	}

	private static void printDesk(int[][] desk) {
		System.err.println("--- DESK ---");
		System.err.println("   abcdefgh");
		for (int y = 7; y >= 0; y--) {
			System.err.print("" + (y + 1) + "  ");
			int[] row = desk[y];
			for (int x = 0; x < 8; x++) {
				System.err.print((row[x] == 0 ? "." : String.valueOf(row[x])));
			}
			System.err.println();
		}
		System.err.println("--- DESK ---");
	}
}
