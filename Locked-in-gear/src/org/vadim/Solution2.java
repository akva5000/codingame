package org.vadim;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: an integer N corresponding to the number of gears (at least 2)
 * Next N lines: the properties of the gears, given as
 * - the coordinates of center of the gear, given by two integers x and y
 * - the radius of the gear, given by an integer r
 * 
 * Output:
 * A single line, stating the direction of the last gear from the input. The direction can be one of the following:
 * - CW for a clockwise movement,
 * - CCW for a counterclockwise movement,
 * - NOT MOVING if the gear doesn't move at all.
 * 
 * Constraints
 * 2 ≤ N ≤ 1000
 * 0 ≤ x ≤ 1000
 * 0 ≤ y ≤ 1000
 * 1 ≤ r ≤ 1000
 * The gears do not overlap.
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		in.nextLine();

		final Gear[] gears = new Gear[N];
		for (int i = 0; i < N; i++) {
			gears[i] = new Gear(in.nextInt(), in.nextInt(), in.nextInt());
		}
		gears[0].direction = Direction.CW;

		buildLinks(gears);

		if (!gears[gears.length - 1].outGears.isEmpty()) {
			if (!spreadDirection(gears)) spreadBlock(gears);
		}

		printGearDirection(gears[gears.length - 1]);
	}

	private static void buildLinks(final Gear[] gears) {
		for (int i = 0; i < gears.length - 1; i++) {
			final Gear g1 = gears[i];
			for (int j = i + 1; j < gears.length; j++) {
				if (!g1.isConnected(gears[j]) && g1.checkTouchGears(gears[j])) g1.addGear(gears[j]);
			}
		}
	}

	private static boolean spreadDirection(final Gear[] gears) throws IllegalStateException {
		final Deque<Gear> stack = new ArrayDeque<>();
		stack.push(gears[0]);

		while (!stack.isEmpty()) {
			final Gear g1 = stack.pop();
			for (final Gear g2 : g1.outGears) {
				if (g2.direction == Direction.NOT_SET) {
					g2.direction = g1.direction == Direction.CW ? Direction.CCW : Direction.CW;
					stack.push(g2);
				} else {
					if (!g2.checkConnection(g1.direction)) return false;
				}
			}
		}

		return true;
	}

	private static void spreadBlock(final Gear[] gears) {
		final Deque<Gear> stack = new ArrayDeque<>();
		gears[0].direction = Direction.STOP;
		stack.push(gears[0]);

		while (!stack.isEmpty()) {
			final Gear g1 = stack.pop();
			for (final Gear g2 : g1.outGears) {
				if (g2.direction == Direction.STOP) continue;
				g2.direction = Direction.STOP;
				stack.push(g2);
			}
		}
	}

	private static void printGearDirection(Gear gear) {
		switch (gear.direction) {
			case CW:
				System.out.println("CW");
				break;

			case CCW:
				System.out.println("CCW");
				break;

			default: // 0
				System.out.println("NOT MOVING");
				break;
		}
	}

	private static enum Direction {
		NOT_SET, CW, CCW, STOP;
	}

	private static class Gear {
		final List<Gear> outGears = new ArrayList<>();
		int x, y, r;
		Direction direction = Direction.NOT_SET;

		public Gear(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}

		public boolean checkConnection(Direction direction) {
			return direction == Direction.NOT_SET || this.direction != direction;
		}

		public void addGear(Gear gear2) {
			if (outGears.contains(gear2)) return;
			outGears.add(gear2);
			gear2.addGear(this);
		}

		public boolean isConnected(Gear gear2) {
			return outGears.contains(gear2);
		}

		public boolean checkTouchGears(Gear gear2) {
			double len1 = Math.hypot((x - gear2.x), (y - gear2.y));
			return len1 == r + gear2.r;
		}
	}
}
