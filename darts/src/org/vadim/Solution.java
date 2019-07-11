package org.vadim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Input<br>
 * <ul>
 * <li>Line 1: SIZE of square as integer.</li>
 * <li>Line 2: The number of competitors in name list,</li>
 * <li>N Next N lines: name of a single competitor in name list.</li>
 * <li>Line N+1: Number of throws,</li>
 * <li>T Next T: Lines: Name of competitor, integer x coordinate, integer y
 * coordinate of throw each separated by a space name X Y.</li>
 * </ul>
 *
 * Output<br>
 * A line with competitor's name, a space, and that competitor's score, in
 * descending order by score.
 * 
 * @author akva
 */
public class Solution {
	private static int SIZE;
	private static int D;
	private static int DD;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		SIZE = in.nextInt();
		D = SIZE / 2;
		DD = D * D;
		int N = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}

		final List<String> names = new ArrayList<>(N);
		final Map<Integer, Integer> scoreBoard = new HashMap<>(N);
		for (int i = 0; i < N; i++) {
			names.add(in.nextLine());
			scoreBoard.put(i, 0);
		}

		// handle throws
		int T = in.nextInt();
		for (int i = 0; i < T; i++) {
			scoreBoard.compute(names.indexOf(in.next()), (k, v) -> v + processThrow(in.nextInt(), in.nextInt()));
		}

		// print score board
		scoreBoard.entrySet().stream().sorted((e1, e2) -> {
			int rc = Integer.compare(e2.getValue(), e1.getValue());
			return rc != 0 ? rc : Integer.compare(e1.getKey(), e2.getKey());
		}).forEach(e -> System.out.println(names.get(e.getKey()) + " " + e.getValue()));
	}

	private static int processThrow(int x, int y) {
		// check is out the square
		if (x < -D || x > D || y < -D || y > D) return 0;

		// check is out the circle
		if (x * x + y * y > DD) return 5;

		// check is out the diamond
		if (Math.abs(x) + Math.abs(y) > D) return 10;

		return 15;
	}

}
