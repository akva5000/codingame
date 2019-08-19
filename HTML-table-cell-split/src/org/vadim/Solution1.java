package org.vadim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <pre>
 * Input:
 * Line 1: An integer NR for the number of rows info that will follow
 * Next NR lines: Row info, as a space separated couple of values CS,RS for the colspan and rowspan attribute of each cell in this row
 * Last line: The zero based index IS of the cell to split, in the order as appeared in the input,
 *      and a character DS that indicates if the cell's split is requested in two columns ("C") or in two rows ("R")
 * 
 * Output:
 * NR or NR+1 lines: Row info, as a space separated couple of values CS,RS for the colspan and rowspan of each cell in this row
 * (hint: if DS is "R" then it will be one more row in the output with respect to the input)
 * 
 * Constraints
 * 1≤ NR ≤ 100
 * The cell to be split has a span value of 1 in the direction asked (but can have any span value in the other direction).
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int NR = in.nextInt();
		if (in.hasNextLine()) in.nextLine();
		System.err.println("N=" + NR);

		final ArrayList<List<ICell>> rows = new ArrayList<>();
		for (int i = 0; i < NR; i++) rows.add(new ArrayList<>());

		parseFirstLine(rows, in.nextLine());
		fillRefs(rows, 0);
		for (int i = 1; i < NR; i++) {
			parseLine(rows, in.nextLine(), i);
			fillRefs(rows, i);
		}

		// parse destination
		int IS = in.nextInt();
		int type = in.next().charAt(0) == 'C' ? 0 : 1;
		System.err.println("split: " + IS + ' ' + type);
		findSplit(rows, IS, type);

		for (int rowIdx = 0; rowIdx < rows.size(); rowIdx++) {
			List<ICell> row = rows.get(rowIdx);
			int counter = 0;
			for (int colIdx = 0; colIdx < row.size(); colIdx++) {
				if (row.get(colIdx) instanceof RefCell) continue;
				Cell c = (Cell) row.get(colIdx);
				if (counter != 0) System.out.print(' ');
				System.out.print(String.format("%d,%d", c.cspan, c.rspan));
				++counter;
			}
			System.out.println();
		}

	}

	private static void findSplit(final ArrayList<List<ICell>> rows, int IS, int type) {
		int counter = -1;
		for (int rowIdx = 0; rowIdx < rows.size(); rowIdx++) {
			List<ICell> row = rows.get(rowIdx);
			for (int colIdx = 0; colIdx < rows.get(0).size(); colIdx++) {
				if (row.get(colIdx) instanceof Cell) ++counter;

				if (counter == IS) {
					System.err.println("split, x=" + colIdx + ", y=" + rowIdx);
					if (type == 0) makeSplitColumns(rows, rowIdx, colIdx);
					else makeSplitRows(rows, rowIdx, colIdx);
					return;
				}
			}
		}
	}

	private static void makeSplitColumns(final ArrayList<List<ICell>> rows, int rowIdx, int colIdx) {
		for (int y = 0; y < rows.size(); y++) {
			rows.get(y).add(colIdx + 1, null);
		}

		for (int y = 0; y < rows.size(); y++) {
			if (y == rowIdx) continue;
			ICell c = rows.get(y).get(colIdx);
			if (c instanceof RefCell) {
				c = ((RefCell) c).cell;
				if (((Cell) c).y == y) {
					((Cell) c).cspan += 1;
				}

			} else {
				((Cell) c).cspan += 1;
			}

			rows.get(y).set(colIdx + 1, new RefCell((Cell) c));
		}

		rows.get(rowIdx).set(colIdx + 1, new Cell(colIdx + 1, rowIdx, 1, 1));
	}

	private static void makeSplitRows(final ArrayList<List<ICell>> rows, int rowIdx, int colIdx) {
		System.err.println("rows-split");

		List<ICell> newRow = new ArrayList<>();
		for (int i = 0; i < rows.get(0).size(); i++) newRow.add(null);
		rows.add(rowIdx + 1, newRow);

		for (int x = 0; x < rows.get(0).size(); x++) {
			if (x == colIdx) continue;

			ICell c = rows.get(rowIdx).get(x);
			if (c instanceof RefCell) {
				c = ((RefCell) c).cell;
				if (((Cell) c).x == x) {
					((Cell) c).rspan += 1;
				}

			} else {
				((Cell) c).rspan += 1;
			}

			rows.get(rowIdx + 1).set(x, new RefCell((Cell) c));
		}

		rows.get(rowIdx + 1).set(colIdx, new Cell(colIdx, rowIdx + 1, 1, 1));
	}

	private static void parseFirstLine(final ArrayList<List<ICell>> rows, String line) {
		System.err.println(line);

		int x = 0;
		for (StringTokenizer tok = new StringTokenizer(line, " "); tok.hasMoreTokens();) {
			String cellStr = tok.nextToken();
			int pos = cellStr.indexOf(',');
			int cs = Integer.parseInt(cellStr.substring(0, pos));
			int rs = Integer.parseInt(cellStr.substring(pos + 1));

			final Cell cell = new Cell(x, 0, cs, rs);
			rows.get(0).add(cell);
			if (cs != 1) {
				for (int x2 = 1; x2 < cs; x2++) rows.get(0).add(new RefCell(cell));
			}
			
			for (int y = 1; y < rows.size(); y++) {
				for (int x2 = 0; x2 < cs; x2++) {
					rows.get(y).add(null);
				}
			}
		}
	}

	private static void parseLine(final ArrayList<List<ICell>> rows, String line, int rowIdx) {
		System.err.println(line);
		List<ICell> row = rows.get(rowIdx);

		int colIdx = 0;
		for (StringTokenizer tok = new StringTokenizer(line, " "); tok.hasMoreTokens();) {
			while (colIdx < row.size() - 1 && row.get(colIdx) instanceof RefCell ) ++colIdx;
			String cellStr = tok.nextToken();
			int pos = cellStr.indexOf(',');
			int cs = Integer.parseInt(cellStr.substring(0, pos));
			int rs = Integer.parseInt(cellStr.substring(pos + 1));
			
			final Cell cell = new Cell(colIdx, rowIdx, cs, rs);
			row.set(colIdx, cell);
			
			if (cs != 1) {
				for (int x2 = colIdx + 1; x2 < colIdx + cs; x2++) rows.get(rowIdx).set(x2, new RefCell(cell));
			}
			
			colIdx += cs;
		}
	}
	
	private static void fillRefs(final ArrayList<List<ICell>> rows, int rowIdx) {
		for (int x = 0; x < rows.get(rowIdx).size(); x++) {
			ICell cell = rows.get(rowIdx).get(x);
			if (cell == null || !Cell.class.isInstance(cell) || ((Cell) cell).rspan == 1) continue;
			
			final Cell cell2 = (Cell) cell;
			for (int y = rowIdx + 1; y < rowIdx + cell2.rspan; y++) {
				for (int x2 = x; x2 < x + cell2.cspan; x2++) {
					rows.get(y).set(x2, new RefCell(cell2));
				}
			}
		}
	}

	public static interface ICell {}
	public static class RefCell implements ICell {
		public Cell cell;
		public RefCell(Cell cell) {this.cell = cell;}
		public String toString() {return String.format("REF(%d,%d,xspan=%d,yspan=%d)", cell.x, cell.y, cell.cspan, cell.rspan);}
	}
	public static class Cell implements ICell {
		public int x, y, cspan, rspan;
		public Cell(int x, int y, int cspan, int rspan) { this.x=x; this.y=y; this.cspan = cspan; this.rspan = rspan; }
		public String toString() {return String.format("(%d,%d,xspan=%d,yspan=%d)", x, y, cspan, rspan);}
	}

}
