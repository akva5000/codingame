package org.vadim;

import static org.vadim.data.SolutionState.ballPoints;
import static org.vadim.data.SolutionState.balls;
import static org.vadim.data.SolutionState.height;
import static org.vadim.data.SolutionState.holePoints;
import static org.vadim.data.SolutionState.holes;
import static org.vadim.data.SolutionState.water;
import static org.vadim.data.SolutionState.width;

import java.awt.Point;
import java.util.BitSet;
import java.util.Scanner;

import org.vadim.data.Ball;
import org.vadim.data.SolutionState;

/**
 * <pre>
 * Input:
 * XXX
 * 
 * Output:
 * XXX
 * 
 * Constraints
 * XXX
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		width = in.nextInt();
		height = in.nextInt();

		water = new BitSet(width * height);
		holes = new BitSet(width * height);
		balls = new BitSet(width * height);
		SolutionState.field = new BitSet(width * height);

		System.err.println(width + " : " + height);
		fillInput(in);
		debug();

		final Solver solver = new Solver();
		if (ballPoints.size() > 1) {
			solver.multyBallsSolve();
		} else {
			solver.singleBallSolve();
		}
	}

	private static void debug() {
		System.err.println("water field:");
		int yidx = 0;
		StringBuilder buf = new StringBuilder(width);
		for (int y = 0; y < height; y++) {
			buf.setLength(0);
			for (int x = 0; x < width; x++) {
				if (water.get(yidx + x)) {
					buf.append('X');
				} else if (holes.get(yidx + x)) {
					buf.append('H');
				} else if (balls.get(yidx + x)) {
					for (Ball b : ballPoints) {
						if (b.start.x == x && b.start.y == y) {
							buf.append(b.turns);
							break;
						}
					}
				} else {
					buf.append('.');
				}
			}

			System.err.println(buf.toString());
			yidx += height;
		}
	}

	private static void fillInput(Scanner in) {
		int yidx = 0;
		for (int y = 0; y < height; y++) {
			String row = in.next();
			System.err.println(">" + row);
			for (int x = 0; x < row.length(); x++) {
				switch (row.charAt(x)) {
					case 'X':
						water.set(yidx + x);
						break;

					case 'H':
						holes.set(yidx + x);
						holePoints.add(new Point(x, y));
						break;

					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						balls.set(yidx + x);
						ballPoints.add(new Ball(x, y, row.charAt(x) - '1' + 1));
						break;

					default:
						break;
				}
			}

			yidx += height;
		}
	}
}
