package org.vadim;

import java.util.Scanner;

/**
 * 
 * Input Line 1: An integer N for the length of one side of the room. Line 2: An
 * integer L for the base light of the candles. Next N lines: N number of
 * characters (as c), separated by one space.
 * 
 * Output Line 1 : The number of places with zero light.
 * 
 * @author akva
 *
 */
public class Solution {
	private static int[][] room;
	private static int L;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		L = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}

		room = new int[N][N];

		for (int y = 0; y < N; y++) {
			String LINE = in.nextLine();
			int pos = 0;
			for (int x = 0; x < N; x++) {
				if (LINE.charAt(pos) == 'C') {
					handleCandle(x, y);
				}
				pos += 2;
			}
		}

		int count = 0;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (room[y][x] == 0) ++count;
			}
		}

//		for (int y = 0; y < N; y++) {
//			for (int x = 0; x < N; x++) {
//				System.out.print(room[y][x]);
//			}
//			System.out.println();
//		}

		System.out.println(count);
	}

	private static void handleCandle(int x, int y) {
		int x1 = x - L + 1;
		int x2 = x + L - 1;
		int y1 = y - L + 1;
		int y2 = y + L - 1;
		
		for (int yp = y1; yp <= y2; yp++) {
			if(yp < 0 || yp >= room.length) continue;
			for (int xp = x1; xp <= x2; xp++) {
				if(xp < 0 || xp >= room.length) continue;
				room[yp][xp] = 1;
			}
		}
	}
}
