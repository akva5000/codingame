package org.vadim;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * At the beginning of the game:
 * Line 1: an int L, the size of one side of the map
 * Line 2: an int water, the total amount of water available
 * 
 * On each turn
 * Line 1: an int N, the amount of fires on the map
 * Next N lines: The coordinates of each fire in the format fireX fireY
 * 
 * Output:
 *  A single line containing the unit code that you want to send and the coordinates of the top left cell
 *  The unit codes are:
 *  C Canadair;
 *  H Fire Helicopter;
 *  J Smoke Jumpers Squad; 
 * 
 * Constraints
 * 2 ≤ L ≤ 20
 * 0 ≤ fireX < L
 * 0 ≤ fireY < L
 * N ≤ L*L
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {
	// [T,x,y]
	private static final Deque<int[]> stack = new ArrayDeque<>();
	private static int L;
	private static int water;
	private static BitSet field;

	public static void main(String args[]) throws Exception {
		Scanner in = new Scanner(System.in);

		L = in.nextInt(); // Size of forest map
		field = new BitSet(L * L);
		water = in.nextInt(); // Total amount of water available
		System.err.println("L=" + L + ", W=" + water);

		int N = in.nextInt(); // Amount of fires
		System.err.println("N=" + N);

		for (int i = 0; i < N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			System.err.println("" + x + ',' + y);
			field.set(y * L + x);
		}

		handleBig();
		handleMid();
		handleRest();
		handleStack();

		// game loop
		while (true) {
			N = in.nextInt(); // Amount of fires
			System.err.println("N=" + N);
			if (N == 0) break;
			for (int i = 0; i < N; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				System.err.println("" + x + ',' + y);
			}

			handleStack();
		} // while
	}

	private static void handleStack() {
		if (stack.isEmpty()) return;
		final int[] item = stack.pop();
		switch (item[0]) {
			case 1:
				System.out.println("J " + item[1] + ' ' + item[2]);
				water -= 600;
				break;

			case 4: // 2x2
				System.out.println("H " + item[1] + ' ' + item[2]);
				water -= 1200;
				break;

			default: // 3x3
				System.out.println("C " + item[1] + ' ' + item[2]);
				water -= 2100;
				break;
		}
	}

	private static void handleRest() {
		int idx = 0;
		for (int i = 0; i < L; i++) {
			for (int x = 0; x < L; x++) {
				if (field.get(idx + x)) {
					stack.push(new int[] { 1, x, i });
				}
			}
			idx += L;
		}
	}

	private static void handleBig() {
		int size = 0;
		final List<Drop> drops = new ArrayList<>();
		final List<Drop> drops2 = new ArrayList<>();

		int didy = 0;
		while (didy < 2) {
			int didx = 0;
			while (didx < 3) {
				int y = 0;
				while (y < L - 1) {
					int x = didx;
					while (x < L - 1) {
						Drop d = calcBigPoint(y, x);
						if (d != null) drops2.add(d);
						x += 3;
					}
					y += 3;
				}

				if (!drops2.isEmpty()) {
					int sum = drops2.stream().map(t -> t.cover).mapToInt(Integer::valueOf).sum();
					if (size < sum) {
						drops.clear();
						drops.addAll(drops2);
						size = sum;
					}
				}
				drops2.clear();

				++didx;
			} // didx

			++didy;
		} // didy

		for (Drop d : drops) {
			// stack d.x, d.y
			stack.push(new int[] { 9, d.x, d.y });
			int idx = d.y * L;
			for (int i = 0; i < 3; i++) {
				for (int x = d.x; x < d.x + 3; x++) {
					field.clear(idx + x);
				}
				idx += L;
			}
		}
	}

	private static Drop calcBigPoint(int y, int x) {
		int bidx = L * y;
		int counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int x1 = x; x1 < x + 3; x1++) {
				if (field.get(bidx + x1)) ++counter;
			}
			bidx += L;
		}

		if (counter < 6) return null;
		return new Drop(x, y, counter);
	}

	private static void handleMid() {
		int size = 0;
		final List<Drop> drops = new ArrayList<>();
		final List<Drop> drops2 = new ArrayList<>();

		int didy = 0;
		while (didy < 2) {
			int didx = 0;
			while (didx < 2) {
				int y = didy;
				while (y < L - 1) {
					int x = didx;
					while (x < L - 1) {
						Drop d = calcMidPoint(y, x);
						if (d != null) drops2.add(d);
						x += 2;
					}
					y += 2;
				}

				if (!drops2.isEmpty()) {
					int sum = drops2.stream().map(t -> t.cover).mapToInt(Integer::valueOf).sum();
					if (size < sum) {
						drops.clear();
						drops.addAll(drops2);
						size = sum;
					}
				}
				drops2.clear();

				++didx;
			} // didx

			++didy;
		} // didy

		for (Drop d : drops) {
			// stack d.x, d.y
			stack.push(new int[] { 4, d.x, d.y });
			int idx = d.y * L;
			for (int i = 0; i < 2; i++) {
				for (int x = d.x; x < d.x + 2; x++) {
					field.clear(idx + x);
				}
				idx += L;
			}
		}

	}

	private static Drop calcMidPoint(int y, int x) {
		int counter = 0;
		int bidx = L * y;

		if (field.get(bidx + x)) ++counter;
		if (field.get(bidx + x + 1)) ++counter;

		bidx += L;
		if (field.get(bidx + x)) ++counter;
		if (field.get(bidx + x + 1)) ++counter;

		if (counter < 3) return null;
		return new Drop(x, y, counter);
	}

	private static class Drop {
		int x, y;
		int cover;

		public Drop(int x, int y, int cover) {
			this.x = x;
			this.y = y;
			this.cover = cover;
		}
	}

}
