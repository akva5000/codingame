package org.vadim.research;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Main {
	private static int L;
	private static BitSet field;

	public static void main(String[] args) {
		L = 6;
		field = new BitSet(L * L);
		field.set(L);
		field.set(L + 2);
		field.set(L + 3);
		
		field.set(2*L);
		field.set(2*L + 1);
		field.set(2*L + 2);
		field.set(2*L + 3);
		
		field.set(3*L + 2);
		field.set(3*L + 3);
		field.set(3*L + 4);
		field.set(3*L + 5);
		
		field.set(4*L + 4);
		field.set(4*L + 5);

		handleBig();
	}

	private static void handleBig() {
		final List<Drop> variants = new ArrayList<>();

		for (int y = 0; y < L - 2; y++) {
			for (int x = 0; x < L - 2; x++) {
				Drop d = calcBigPoint(y, x);
				if (d != null) variants.add(d);
			}
		}
		
		variants.sort((o1, o2) -> Integer.compare(o2.cover, o1.cover));
		
		List<Drop> delete = new ArrayList<>();
		for (int i = 0; i < variants.size() - 1; i++) {
			Rectangle r1 = variants.get(i).rec;
			for (int j = i + 1; j < variants.size(); j++) {
				if(r1.intersects(variants.get(j).rec)) {
					r1 = null;
					break;
				}
			}
			
			if(r1 != null) delete.add(variants.get(i));
		}
		
		for (Drop d : delete) {
			System.out.println("NC " + d);
			variants.remove(d);
		}

		for (Drop d : variants) {
			System.out.println("C " + d);
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

	private static class Drop {
		final Rectangle rec;
		final int x, y;
		final int cover;

		public Drop(int x, int y, int cover) {
			rec = new Rectangle(x, y, 3, 3);
			this.x = x;
			this.y = y;
			this.cover = cover;
		}

		@Override
		public String toString() {
			return "DROP: " + x + ',' + y + '/' + cover;
		}
	}
}
