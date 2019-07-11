package org.vadim;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <pre>
 * Input
 * Ligne1 : A number N of player
 * Next N lines : name of player
 * Next N lines : shoots of a player separated with space (X when a player missed the target).
 * 
 * Output
 * Line 1 : The name of the winner
 * 
 * Constraints
 * 1 ≤ N ≤ 8
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		final String[] playerNames = new String[in.nextInt()];
		if (in.hasNextLine()) in.nextLine();
		
		for (int i = 0; i < playerNames.length; i++) playerNames[i] = in.nextLine();
		System.out.println(playerNames[emulator(readPlayerShoots(in, playerNames.length))]);
	}

	private static int[][] readPlayerShoots(final Scanner in, int N) {
		final int[][] shoots = new int[N][];
		final ArrayList<Integer> shootsList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String SHOOTS = in.nextLine();
			for (final StringTokenizer tok = new StringTokenizer(SHOOTS, " "); tok.hasMoreTokens();) {
				String value = tok.nextToken();
				if (value.length() == 1 && value.charAt(0) == 'X') {
					shootsList.add(-20);
				} else if (value.indexOf('*') == -1) {
					shootsList.add(Integer.parseInt(value));
				} else {
					int pos = value.indexOf('*');
					shootsList.add(Integer.parseInt(value.substring(0, pos)) * Integer.parseInt(value.substring(pos + 1)));
				}
			}

			shoots[i] = shootsList.stream().mapToInt(Integer::intValue).toArray();
			shootsList.clear();
		}

		return shoots;
	}

	private static int emulator(final int[][] shoots) {
		final int[] currentScore = new int[shoots.length];
		final int[] playerShootPos = new int[shoots.length];

		// game rounds
		while (true) {
			for (int i = 0; i < currentScore.length; i++) {
				if (playerShootPos[i] >= shoots[i].length) continue;
				int scoreBeforeRound = currentScore[i]; // score for previous round
				int missed = 0; // number consecutively missing
				for (int j = 0; j < 3; j++) { // 3 shoots per player
					if (playerShootPos[i] >= shoots[i].length) break; // no turns

					// missing case
					if (shoots[i][playerShootPos[i]] == -20) {
						currentScore[i] -= 20;
						++missed;
						++playerShootPos[i];
						continue;
					}

					// over score case
					if (currentScore[i] + shoots[i][playerShootPos[i]] > 101) {
						++playerShootPos[i];
						currentScore[i] = scoreBeforeRound;
						break;
					}

					// normal case
					currentScore[i] += shoots[i][playerShootPos[i]];
					if (missed == 2) currentScore[i] -= 10; // before 2 consecutively missing
					missed = 0;
					if (currentScore[i] == 101) return i;
					++playerShootPos[i];
				} // while

				// several consecutively missing case
				if (missed > 1) {
					currentScore[i] = missed == 3 ? 0 : currentScore[i] - 10;
				}
			}
		}
	}
}
