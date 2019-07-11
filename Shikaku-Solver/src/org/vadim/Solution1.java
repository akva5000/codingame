package org.vadim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * <pre>
 * Input:
 * Line 1: The Width and Height of the puzzle, space separated.
 * Next H lines: W space separated numbers to fill out the grid (0 for blank cells, otherwise the area value).
 * 
 * Output:
 * Line 1: The number of solutions to the puzzle. There will always be at least one solution.
 * Next H lines: W characters (A-Z,a-z) for each line of the solution.
 *      If there is more than one solution, return the solution that comes first if you concatenate all rows and sort lexicographically.
 * 
 * Constraints
 * 10 ≤ W,H ≤ 30
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	private static final Map<Integer, List<int[]>> cache = new HashMap<>();
	private static final List<Point> numbers = new ArrayList<>(); // y, x, number
	
	private static int W;
	private static int H;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		W = in.nextInt();
		H = in.nextInt();
		in.nextLine();

		numbers.clear();
		for (int i = 0; i < H; i++) {
			String line = in.nextLine();
			int x = 0;
			for (StringTokenizer tok = new StringTokenizer(line, " "); tok.hasMoreTokens();) {
				String sv = tok.nextToken();
				if (sv.length() != 1 || sv.charAt(0) != '0') {
					int value = Integer.parseInt(sv);
					numbers.add(new Point(x, i, value));
					cache.computeIfAbsent(value, key -> findBoxSizes(key));
				}
				++x;
			}
		}

		// optimization - big numbers first
		numbers.sort((o1, o2) -> Integer.compare(o2.v, o1.v));

		List<List<Rect>> variation = new ArrayList<>(numbers.size());
		for (int i = 0; i < numbers.size(); i++) {
			variation.add(generateCombinations(numbers, i, variation));
		}

		// remove intersection areas
		for (int i = 0; i < variation.size(); i++) {
			if (variation.get(i).size() != 1) continue;
			Rect r1 = variation.get(i).get(0);

			for (int j = 0; j < variation.size(); j++) {
				if (i == j || variation.get(j).size() == 1) continue;
				for (ListIterator<Rect> it = variation.get(j).listIterator(); it.hasNext();) {
					if (isIntersect(r1, it.next())) it.remove();
				}
			}
		}

		// evaluate combinations
		String[] solutions;
		{
			List<List<Rect>> tmp = new ArrayList<>(5);
			List<Rect> variant = new ArrayList<>(numbers.size());
			for (int i = 0; i < numbers.size(); i++) variant.add(new Rect(0, 0, 0, 0));

			for (int i = 0; i < variation.get(0).size(); i++) {
				variant.set(0, variation.get(0).get(i));
				evaluateVariant(variant, 1, variation, tmp);
			}

			// converting and sorting solutions
			final char[][] desk = new char[H][W];
			final StringBuilder buf = new StringBuilder(H * W);
			solutions = new String[tmp.size()];
			for (int i = 0; i < tmp.size(); i++) {
				convVariant(tmp.get(i), desk, buf);
				solutions[i] = buf.toString();
			}
			System.err.println("xx");
		}

		// result
		if (solutions.length > 1) Arrays.sort(solutions);
		System.out.println(solutions.length);
		printSolution(solutions[0]);
	}

	private static List<int[]> findBoxSizes(int value) {
		if (value == 1) return Arrays.asList(new int[] { 1, 1 });

		List<int[]> values = new ArrayList<>(10);
		if (value <= 20) values.add(new int[] { 1, value });

		int lim = value > 30 ? 30 : value;
		for (int i = 2; i < lim; i++) {
			for (int j = i; j < lim; j++) {
				int t = i*j;
				if(t == value) values.add(new int[] { i, j});
				else if(t > value) break;
			}
		}
		return values;
	}

	private static void evaluateVariant(List<Rect> variant, int level, List<List<Rect>> variation, List<List<Rect>> solutions) {
		if (level == variant.size()) {
			solutions.add(new ArrayList<>(variant));
			return;
		}

		for (int i = 0; i < variation.get(level).size(); i++) {
			variant.set(level, variation.get(level).get(i));

			// check
			boolean flag = false;
			for (int j = 0; j < level; j++) {
				if (isIntersect(variant.get(level), variant.get(j))) {
					flag = true;
					break;
				}
			}

			if (!flag) evaluateVariant(variant, level + 1, variation, solutions);
		}
	}

	private static void convVariant(List<Rect> variant, final char[][] desk, final StringBuilder buf) {
		variant.sort((o1, o2) -> {
			int t = Integer.compare(o1.y1, o2.y1);
			return t != 0 ? t : Integer.compare(o1.x1, o2.x1);
		});

		for (int y = 0; y < desk.length; y++) Arrays.fill(desk[y], ' ');
		char P = 'A';
		for (Rect v : variant) {
			for (int y = v.y1; y <= v.y2; y++) {
				for (int x = v.x1; x <= v.x2; x++) {
					desk[y][x] = P;
				}
			}

			P = P == 'Z' ? 'a' : ++P;
		}

		buf.setLength(0);
		for (int y = 0; y < desk.length; y++) {
			for (int x = 0; x < desk[0].length; x++) buf.append(desk[y][x]);
		}
	}

	private static void printSolution(String solution) {
		int pos = 0;
		for (int y = 0; y < H; y++) {
			System.out.println(solution.substring(pos, pos + W));
			pos += W;
		}
//		System.err.println("+++++++++++++++++++++++++++++++++");
	}

	private static List<Rect> generateCombinations(final List<Point> numbers, int idx, List<List<Rect>> variation) {
		Set<Rect> combinations = new HashSet<>();
		Point pos = numbers.get(idx);
		BitSet checkSet = new BitSet(numbers.size());
		for (int[] combo : cache.get(pos.v)) { // H x W
			// horizontal
			if (combo[1] <= W && combo[0] <= H) {
				int x1 = combo[1] - 1 >= pos.x ? 0 : pos.x - combo[1] + 1;
				int x2 = W - combo[1] <= pos.x ? W - combo[1] : pos.x;

				int y1 = combo[0] - 1 >= pos.y ? 0 : pos.y - combo[0] + 1;
				int y2 = H - combo[0] <= pos.y ? H - combo[0] : pos.y;

				for (int y = y1; y <= y2; y++) {
					int y22 = y + combo[0] - 1;
					for (int x = x1; x <= x2; x++) {
						int x22 = x + combo[1] - 1;
						boolean flag = false;

						int pos2 = 0;
						int pos3;
						while ((pos3 = checkSet.nextSetBit(pos2)) != -1) {
							Point p = numbers.get(pos3);
							if (p.y < y || p.y > y22 || p.x < x || p.x > x22) {
								pos2 = pos3 + 1;
								continue;
							}
							flag = true;
							break;
						}

						if (!flag) for (int i = 0; i < numbers.size(); i++) {
							if (checkSet.get(i)) continue;
							Point p = numbers.get(i);
							if (p == pos) continue;

							// check inside
							if (p.y < y || p.y > y22 || p.x < x || p.x > x22) continue;
							flag = true;
							checkSet.set(i);
							break;
						}
						if (!flag) combinations.add(new Rect(x, y, x22, y22));
					}
				}
			}

			// vertical
			if (combo[0] <= W && combo[1] <= H) {
				int x1 = combo[0] - 1 >= pos.x ? 0 : pos.x - combo[0] + 1;
				int x2 = W - combo[0] <= pos.x ? W - combo[0] : pos.x;

				int y1 = combo[1] - 1 >= pos.y ? 0 : pos.y - combo[1] + 1;
				int y2 = H - combo[1] <= pos.y ? H - combo[1] : pos.y;

				for (int y = y1; y <= y2; y++) {
					int y22 = y + combo[1] - 1;
					for (int x = x1; x <= x2; x++) {
						int x22 = x + combo[0] - 1;
						boolean flag = false;

						int pos2 = 0;
						int pos3;
						while ((pos3 = checkSet.nextSetBit(pos2)) != -1) {
							Point p = numbers.get(pos3);
							if (p.y < y || p.y > y22 || p.x < x || p.x > x22) {
								pos2 = pos3 + 1;
								continue;
							}
							flag = true;
							break;
						}

						if (!flag) for (int i = 0; i < numbers.size(); i++) {
							if (checkSet.get(i)) continue;
							Point p = numbers.get(i);
							if (p == pos) continue;

							// check inside
							if (p.y < y || p.y > y22 || p.x < x || p.x > x22) continue;
							flag = true;
							checkSet.set(i);
							break;
						}
						if (!flag) combinations.add(new Rect(x, y, x22, y22));
					}
				}
			}
		}

		List<Rect> rc = new ArrayList<>(combinations.size());
		for (final Iterator<Rect> it = combinations.iterator(); it.hasNext();) {
			Rect r = it.next();
			boolean flag = false;
			if (idx > 0) for (int j = 0; j < idx; j++) {
				List<Rect> list = variation.get(j);
				if (list.size() != 1) continue;
				if (isIntersect(list.get(0), r)) {
					flag = true;
					break;
				}
			}
			if (!flag) rc.add(r);
		}
		return rc;
	}

	private static boolean isIntersect(Rect r1, Rect r2) {
		int tw = r1.x2 - r1.x1 + 1; // width
		int th = r1.y2 - r1.y1 + 1; // height

		int rw = r2.x2 - r2.x1 + 1; // #2 width
		int rh = r2.y2 - r2.y1 + 1; // #2 height

		rw += r2.x1;
		rh += r2.y1;
		tw += r1.x1;
		th += r1.y1;
		// overflow || intersect
		return ((rw < r2.x1 || rw > r1.x1) && (rh < r2.y1 || rh > r1.y1) && (tw < r1.x1 || tw > r2.x1) && (th < r1.y1 || th > r2.y1));
	}

	private static final class Point {
		int x, y, v;

		public Point(int x, int y, int v) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
		}

		@Override
		public String toString() {
			return Integer.valueOf(x) + ',' + y + " - " + v;
		}
	}

	private static final class Rect {
		int x1, y1, x2, y2;

		public Rect(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x1, y1, x2, y2);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			Rect other = (Rect) obj;
			if (x1 != other.x1 || x2 != other.x2 || y1 != other.y1 || y2 != other.y2) return false;
			return true;
		}

		@Override
		public String toString() {
			return Integer.toString(x1) + ',' + y1 + '-' + x2 + ',' + y2;
		}
	}
}
