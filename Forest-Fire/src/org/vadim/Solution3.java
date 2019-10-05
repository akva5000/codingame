package org.vadim;

import java.awt.Rectangle;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

import org.vadim.genetics.Algorithm;
import org.vadim.genetics.Chromosome;
import org.vadim.genetics.Population;

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
public class Solution3 {
	// [T,x,y]
	private static final Deque<int[]> stack = new ArrayDeque<>();
	private static final List<Drop> confilictingBoxes = new ArrayList<>();
	
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
		if (L < 3) return;

		final List<Drop> variants = new ArrayList<>();
		for (int y = 0; y < L - 2; y++) {
			for (int x = 0; x < L - 2; x++) {
				Drop d = calcBigPoint(y, x);
				if (d != null) variants.add(d);
			}
		}

		if (variants.isEmpty()) throw new IllegalStateException("There are no variants at all");

		if (variants.size() == 1) {
			Drop d = variants.get(0);
			stack.push(new int[] { 9, d.rec.x, d.rec.y });
			clearBigBox(d.rec.x, d.rec.y);
			System.err.println("Interm: fixed boxes=" + stack.size() + ", conflict boxes=0");
			System.err.println("Fixed boxes covers: " + d.cover);
			return;
		}

		final BitSet bits = new BitSet(variants.size());
		for (int i = 0; i < variants.size() - 1; i++) {
			final Rectangle r1 = variants.get(i).rec;
			for (int j = i + 1; j < variants.size(); j++) {
				if (r1.intersects(variants.get(j).rec)) {
					bits.set(i);
					bits.set(j);
				}
			}
		}

		int fixedBoxCover = 0;
		for (int i = 0; i < variants.size(); i++) {
			if (bits.get(i)) {
				confilictingBoxes.add(variants.get(i));
			} else {
				Drop drop = variants.get(i);
				stack.push(new int[] { 9, drop.rec.x, drop.rec.y });
				clearBigBox(drop.rec.x, drop.rec.y);
				fixedBoxCover += drop.cover;
			}
		}

		System.err.println("Interm: fixed boxes=" + stack.size() + ", conflict boxes=" + confilictingBoxes.size());
		System.err.println("Fixed boxes covers: " + fixedBoxCover);
		
		if(confilictingBoxes.isEmpty()) return;
		
		// resolve conflicting boxes
		confilictingBoxes.sort( (o1, o2) -> {
			int t = Integer.compare(o1.rec.y, o2.rec.y);
			return t != 0 ? t : Integer.compare(o1.rec.x, o2.rec.x);
		});

		Population population = new Population(50, confilictingBoxes.size(), true);
		Chromosome bestChromo = Algorithm.evaluate(population, Solution3::eval, 200);

		for (int i = 0; i < bestChromo.size(); i++) {
			if (!bestChromo.getGene(i).getValue()) continue;
			Drop d = confilictingBoxes.get(i);
			stack.push(new int[] { 9, d.rec.x, d.rec.y });
			clearBigBox(d.rec.x, d.rec.y);
		}
	}
	
	private static final ThreadLocal<List<Drop>> listRef = ThreadLocal.withInitial(() -> new ArrayList<>());
	private static int eval(final Chromosome chromosome) {
		final List<Drop> list = listRef.get();
		list.clear();
		
		for (int i = 0; i < chromosome.size(); i++) {
			if (!chromosome.getGene(i).getValue()) continue;
			list.add(confilictingBoxes.get(i));
		}
		
		final BitSet bits = new BitSet(list.size());
		for (int i = 0; i < list.size() - 1; i++) {
			Drop d1 = list.get(i);
			for (int j = i + 1; j < list.size(); j++) {
				if (d1.rec.intersects(list.get(j).rec)) {
					bits.set(i);
					bits.set(j);
				}
			} // for j
		} // for i

		int conflictNumber = 0;
		int covered = 0;
		for (int i = 0; i < list.size(); i++) {
			if(bits.get(i)) {
				++conflictNumber;
			} else {
				covered += list.get(i).cover;
			}
		}

		int fit = covered - conflictNumber * 1000;
		return fit;
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
	
	private static void clearBigBox(int x, int y) {
		int idx = y * L;
		for (int i = 0; i < 3; i++) {
			for (int x1 = x; x1 < x + 3; x1++) {
				field.clear(idx + x1);
			}
			idx += L;
		}
	}

	private static void handleMid() {
		if (L < 2) return;

		List<Drop> variants = new ArrayList<>();
		for (int y = 0; y < L - 1; y++) {
			for (int x = 0; x < L - 1; x++) {
				Drop d = calcMidPoint(y, x);
				if (d != null) variants.add(d);
			}
		}
		
		if(variants.isEmpty()) {
			System.err.println("no 2x2 variants");
			return;	
		}
		
		if(variants.size() == 1) {
			System.err.println("1 2x2 variant");
			final Drop d = variants.get(0);
			stack.push(new int[] { 4, d.rec.x, d.rec.y });
			return;
		}
		
		System.err.println("many 2x2 variants");

		final BitSet bits = new BitSet(variants.size());
		for (int i = 0; i < variants.size() - 1; i++) {
			final Rectangle r1 = variants.get(i).rec;
			for (int j = i + 1; j < variants.size(); j++) {
				if (r1.intersects(variants.get(j).rec)) {
					bits.set(i);
					bits.set(j);
				}
			}
		}
		
		for (int i = 0; i < variants.size(); i++) {
			if (bits.get(i)) {
				confilictingBoxes.add(variants.get(i));
			} else {
				final Drop drop = variants.get(i);
				stack.push(new int[] { 4, drop.rec.x, drop.rec.y });
				int idx = drop.rec.y * L;
				for (int j = 0; j < 2; j++) {
					for (int x = drop.rec.x; x < drop.rec.x + 2; x++) {
						field.clear(idx + x);
					}
					idx += L;
				}
			}
		}
		variants = null;

		if(confilictingBoxes.isEmpty()) return;

		// TODO resolve conflicting boxes
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
		final Rectangle rec;
		final int cover;

		public Drop(int x, int y, int cover) {
			rec = new Rectangle(x, y, 3, 3);
			this.cover = cover;
		}
	}

}
