package org.vadim;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * <pre>
 * Input:
 * Line 1: An integer H representing the height of the snow line (including this value, and under).
 * Line 2: An integer N for the length of one side of the map.
 * Next N lines: N number of integers (as h), separated by one space.
 * 
 * Output:
 * Line 1 : The deepest point of the largest valley.
 * 
 * Constraints
 * 0 < H < 2000
 * 0 < N < 21
 * 0 < h < 2000
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int H = in.nextInt();
		int N = in.nextInt();

		final List<Field> fields = inputInformation(in, H, N);

		if (fields.isEmpty()) {
			System.out.println("0");
		} else {
			List<Field> list = fields.parallelStream()
			.sorted((o1, o2) -> Integer.compare(o2.getSquare(), o1.getSquare()))
			.collect(Collectors.toList());
			
			int size = list.get(0).getSquare();
			int idx = 0;
			int deepest = Integer.MAX_VALUE;
			while(idx < list.size() && list.get(idx).getSquare() == size) {
				int t = list.get(idx).getDeppestValue();
				if(t < deepest) deepest = t;
				++idx;
			}
			
			System.out.println(String.valueOf(deepest));
		}
	}

	private static List<Field> inputInformation(Scanner in, int H, int N) {
		final List<Field> fields = new ArrayList<>();
		final List<Field> tmpList = new ArrayList<>();
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				int h = in.nextInt();
				if (h > H) continue;

				tmpList.clear();
				for (Field fld : fields) {
					if(!fld.isNeighbor(x, y)) continue;
					tmpList.add(fld);
				}

				Field field;
				if (tmpList.isEmpty()) {
					field = new Field();
					fields.add(field);
				} else if (tmpList.size() == 1) {
					field = tmpList.get(0);
				} else {
					field = new Field();
					for (Field f : tmpList) {
						fields.remove(f);
						field.cells.addAll(f.cells);
					}

					fields.add(field);
				}

				field.addCell(new Cell(x, y, h));
			}
		}

		return fields;
	}
	
	
	

	private static class Cell {
		int x, y, h;

		public Cell(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Cell other = (Cell) obj;
			return x == other.x && y == other.y;
		}
	}

	private static class Field {
		final List<Cell> cells = new ArrayList<>();

		public void addCell(Cell cell) {
			if (!cells.contains(cell)) cells.add(cell);
		}

		public boolean isBelong(int x, int y) {
			return cells.parallelStream()
					.filter((Cell cell) -> cell.x == x && cell.y == y)
					.findAny()
					.isPresent();
		}

		public boolean isNeighbor(int x, int y) {
			return cells.parallelStream()
					.filter((Cell cell) -> (cell.y == y && (cell.x + 1 == x || cell.x - 1 == x)) || (cell.x == x & (cell.y + 1 == y || cell.y - 1 == y)))
					.findAny()
					.isPresent();
		}
		
		public int getSquare() {
			return cells.size();
		}
		
		public int getDeppestValue() {
			return cells.parallelStream()
					.mapToInt(o -> o.h)
					.min()
					.getAsInt();
		}
	}

}
