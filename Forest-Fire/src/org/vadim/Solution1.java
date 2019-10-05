package org.vadim;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
public class Solution1 {
	private static final Deque<StackItem> stack = new ArrayDeque<>();
	private static int water;

	public static void main(String args[]) throws Exception {
		Scanner in = new Scanner(System.in);

		int L = in.nextInt(); // Size of forest map
		water = in.nextInt(); // Total amount of water available
		System.err.println("L=" + L + ", W=" + water);

		final List<Area> areas = new ArrayList<>();

		final List<Area> areas2 = new ArrayList<>();
		int N = in.nextInt(); // Amount of fires
		System.err.println("N=" + N);
		for (int i = 0; i < N; i++) {
			final Item item = new Item(in.nextInt(), in.nextInt());
			System.err.println("" + item.p.x + ',' + item.p.y);

			areas2.clear();
			for (final ListIterator<Area> it = areas.listIterator(); it.hasNext();) {
				Area ar = it.next();
				if (!ar.isNeighbor(item)) continue;
				it.remove();
				areas2.add(ar);
			}

			if (areas2.isEmpty()) {
				areas.add(new Area(item));
			} else if (areas2.size() == 1) {
				areas2.get(0).addItem(item);
				areas.add(areas2.get(0));
				areas2.clear();
			} else {
				Area ar = areas2.get(0);
				for (int k = 1; k < areas2.size(); k++) {
					for (Item t : areas2.get(k).items) {
						ar.addItem(t);
					}
				}
				ar.addItem(item);
				areas.add(ar);
				areas2.clear();
			}
		}

		areas.parallelStream().forEach(area -> area.recalculateSize());
		findLineArea(areas);
		findBigSquareArea(areas);
		findSingleArea(areas);

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
		final StackItem item = stack.pop();
		switch (item.number) {
			case 1:
				System.out.println("J " + item.p.x + ' ' + item.p.y);
				water -= 600;
				break;

			case 4: // 2x2
				System.out.println("H " + item.p.x + ' ' + item.p.y);
				water -= 1200;
				break;

			default: // 3x3
				System.out.println("C " + item.p.x + ' ' + item.p.y);
				water -= 2100;
				break;
		}
	}

	private static void findSingleArea(final List<Area> areas) {
		areas.stream()
			.flatMap((Area area) -> area.items.stream())
			.filter((Item item) -> !item.isHandled)
			.forEach((Item item) -> stack.push(new StackItem(item.p, 1)));
		areas.clear();
	}

	private static void findLineArea(final List<Area> areas) {
		Optional<Area> lineArea = areas.stream().filter((Area area) -> area.s > 1).filter((Area area) -> area.x1 == area.x2 || area.y1 == area.y2)
				.findFirst();
		if (!lineArea.isPresent()) return;
		areas.remove(lineArea.get());

		for (Item item : lineArea.get().items) stack.push(new StackItem(item.p, 1));
	}

	private static void findBigSquareArea(final List<Area> areas) {
		for (final ListIterator<Area> it = areas.listIterator(); it.hasNext();) {
			final Area area = it.next();
			if (area.s < 4) continue;

			for (int y = area.y1; y <= area.y2; y++) {
				Map<Integer, Item> mapY = area.map.get(y);
				for (int x = area.x1; x <= area.x2; x++) {
					Item t = mapY.get(x);
					if (t == null || t.isHandled) continue;
					if (checkMidBoxPoint(area, y, x)) {
						Item item = area.map.get(y).get(x);
						item.isHandled = true;
						mapY.get(x + 1).isHandled = true;
						Map<Integer, Item> map = area.map.get(y + 1);
						map.get(x).isHandled = true;
						map.get(x + 1).isHandled = true;

						if (checkBigBoxPoint(area, y, x)) {
							stack.push(new StackItem(item.p, 9));
							mapY.get(x + 2).isHandled = true;
							map.get(x + 2).isHandled = true;

							map = area.map.get(y + 2);
							map.get(x).isHandled = true;
							map.get(x + 1).isHandled = true;
							map.get(x + 2).isHandled = true;
						} else {
							stack.push(new StackItem(item.p, 4));
						}
					} else {
						Item item = area.map.get(y).get(x);
						item.isHandled = true;
						stack.push(new StackItem(item.p, 1));
					}
				}
			}
		}
	}

	private static boolean checkMidBoxPoint(final Area area, int y, int x) {
		Map<Integer, Item> map = area.map.get(y);
		// line 1
		Item t = map.get(x + 1);
		if (t == null || t.isHandled) return false;

		// line 2
		map = area.map.get(y + 1);
		t = map.get(x);
		if (t == null || t.isHandled) return false;

		t = map.get(x + 1);
		if (t == null || t.isHandled) return false;
		return true;
	}

	private static boolean checkBigBoxPoint(final Area area, int y, int x) {
		Map<Integer, Item> map = area.map.get(y);
		// line 1
		Item t = map.get(x + 2);
		if (t == null || t.isHandled) return false;

		// line 2
		map = area.map.get(y + 1);
		t = map.get(x + 2);
		if (t == null || t.isHandled) return false;

		// line 3
		map = area.map.get(y + 2);
		t = map.get(x);
		if (t == null || t.isHandled) return false;

		t = map.get(x + 1);
		if (t == null || t.isHandled) return false;

		t = map.get(x + 2);
		if (t == null || t.isHandled) return false;

		return true;
	}

	private static class Item {
		Point p;
		boolean isHandled = false;

		public Item(int x, int y) {
			p = new Point(x, y);
		}

		public int hashCode() {
			return Objects.hash(p);
		}

		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Item other = (Item) obj;
			return Objects.equals(p, other.p);
		}

		@Override
		public String toString() {
			return "(" + p.x + ',' + p.y + ')';
		}
	}

	private static class Area {
		final List<Item> items = new ArrayList<>();
		final Map<Integer, Map<Integer, Item>> map = new HashMap<>();
		int y1, y2, x1, x2;
		int s;

		public Area(Item item) {
			items.add(item);
		}

		boolean isNeighbor(Item item) {
			for (Item t : items) {
				if (item.p.y == t.p.y && (item.p.x == t.p.x - 1 || item.p.x == t.p.x + 1)) return true;
				if (item.p.x == t.p.x && (item.p.y == t.p.y - 1 || item.p.y == t.p.y + 1)) return true;
			}
			return false;
		}

		void addItem(Item item) {
			if (items.contains(item)) throw new IllegalStateException("dublicate item");
			items.add(item);
		}

		void recalculateSize() {
			y1 = Integer.MAX_VALUE;
			y2 = -1;
			x1 = Integer.MAX_VALUE;
			x2 = -1;
			map.clear();
			for (Item item : items) {
				if (item.p.y < y1) y1 = item.p.y;
				if (item.p.y > y2) y2 = item.p.y;
				if (item.p.x < x1) x1 = item.p.x;
				if (item.p.x > x2) x2 = item.p.x;
				final Map<Integer, Item> map2 = map.computeIfAbsent(Integer.valueOf(item.p.y), (key) -> new HashMap<>());
				map2.put(item.p.x, item);
			}
			s = (x2 - x1 + 1) * (y2 - y1 + 1);
		}
	}

	private static class StackItem {
		Point p;
		int number;

		public StackItem(Point p, int number) {
			this.p = p;
			this.number = number;
		}
	}
}
