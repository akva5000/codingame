package org.vadim;

import static org.vadim.data.SolutionState.height;
import static org.vadim.data.SolutionState.screen;
import static org.vadim.data.SolutionState.width;

import java.awt.Point;
import java.util.List;

import org.vadim.turn.BallTurn;

public class SolutionPrinter {
	/**
	 * Print field 2D array to screen.
	 */
	public static void printSolution(char[][] pole) {
		StringBuilder buf = new StringBuilder(width);
		for (int i = 0; i < width; i++) {
			buf.append(' ');
		}

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				buf.setCharAt(x, pole[y][x]);
			}

			screen.println(buf.toString());
		}
		screen.flush();
	}

	/**
	 * Draw a path to 2D array field.
	 */
	public static void fillPath(char[][] pole, List<BallTurn> path) {
		path.stream()
				.forEach(turn -> drawSegment(pole, turn.start, turn.end));
	}

	private static void drawSegment(char[][] pole, Point start, Point end) {
		if (start.x == end.x) {
			drawVerticalSegment(pole, start, end);
		} else {
			drawHorizontalSegment(pole, start, end);
		}
	}

	private static void drawVerticalSegment(char[][] pole, Point start, Point end) {
		if (start.y < end.y) {
			drawStartPoint(pole, start, end, 'v');
			for (int y = start.y + 1; y < end.y; y++) {
				drawMidPoint(pole, start.x, y, 'v');
			}

			return;
		}

		drawStartPoint(pole, start, end, '^');
		for (int y = start.y - 1; y > end.y; y--) {
			drawMidPoint(pole, start.x, y, '^');
		}

	}

	private static void drawHorizontalSegment(char[][] pole, Point start, Point end) {
		if (start.x < end.x) {
			drawStartPoint(pole, start, end, '>');
			for (int x = start.x + 1; x < end.x; x++) {
				drawMidPoint(pole, x, start.y, '>');
			}

			return;
		}

		drawStartPoint(pole, start, end, '<');
		for (int x = start.x - 1; x > end.x; x--) {
			drawMidPoint(pole, x, start.y, '<');
		}

	}

	private static void drawStartPoint(char[][] pole, Point start, Point end, char dir) {
		pole[start.y][start.x] = dir;
	}

	private static void drawMidPoint(char[][] pole, int x, int y, char dir) {
		if (pole[y][x] != '.') {
			throw new IllegalStateException("cell is busy, " + x + "," + y);
		}
		pole[y][x] = dir;
	}
}
