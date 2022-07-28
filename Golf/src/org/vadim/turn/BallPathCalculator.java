package org.vadim.turn;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.vadim.data.Ball;
import org.vadim.data.SolutionState;

/**
 * Find all possible paths which satisfy the criteria.
 */
public final class BallPathCalculator {
	public static List<List<BallTurn>> getSolutions(Ball ball, Point hole) {
		return ball.turns == 1 ? evalSingleBallSolution(ball, hole) : evalMultiBallsSolutions(ball, hole);
	}

	private static List<List<BallTurn>> evalSingleBallSolution(Ball ball, Point hole) {
		return ball.start.x == hole.x || ball.start.y == hole.y
				? List.of((List.of(new BallTurn(ball, ball.start, hole))))
				: List.of();
	}

	private static List<List<BallTurn>> evalMultiBallsSolutions(Ball ball, Point hole) {
		final List<List<BallTurn>> solutions = new ArrayList<>();
		LinkedList<BallTurn> result = new LinkedList<>();
		buildNextSegment(ball, ball.start, hole, ball.turns, result, sol -> solutions.add(List.copyOf(sol)));
		return solutions;
	}

	private static void buildNextSegment(Ball ball, Point start, Point hole, int segNum, LinkedList<BallTurn> result,
			Consumer<List<BallTurn>> resultConsumer) {
		if (segNum == 1) {
			if ((start.y == hole.y && Math.abs(start.x - hole.x) == 1)
				|| (start.x == hole.x && Math.abs(start.y - hole.y) == 1)) {
				BallTurn seg = new BallTurn(ball, start, hole);
				result.add(seg);
				resultConsumer.accept(result);
				result.removeLast();
			}
			return;
		}

		// go up
		if (start.y - segNum >= 0) {
			if (result.isEmpty() || result.getLast().end.y <= result.getLast().start.y) {
				BallTurn seg = new BallTurn(ball, start, new Point(start.x, start.y - segNum));
				if (checkSegment(seg, result, false)) {
					result.add(seg);
					buildNextSegment(ball, seg.end, hole, segNum - 1, result, resultConsumer);
					result.removeLast();
				}
			}
		}

		// go down
		if (start.y + segNum < SolutionState.height) {
			if (result.isEmpty() || result.getLast().end.y >= result.getLast().start.y) {
				BallTurn seg = new BallTurn(ball, start, new Point(start.x, start.y + segNum));
				if (checkSegment(seg, result, false)) {
					result.add(seg);
					buildNextSegment(ball, seg.end, hole, segNum - 1, result, resultConsumer);
					result.removeLast();
				}
			}
		}

		// go left
		if (start.x - segNum >= 0) {
			if (result.isEmpty() || result.getLast().end.x <= result.getLast().start.x) {
				BallTurn seg = new BallTurn(ball, start, new Point(start.x - segNum, start.y));
				if (checkSegment(seg, result, false)) {
					result.add(seg);
					buildNextSegment(ball, seg.end, hole, segNum - 1, result, resultConsumer);
					result.removeLast();
				}
			}
		}

		// go right
		if (start.x + segNum < SolutionState.width) {
			if (result.isEmpty() || result.getLast().end.x >= result.getLast().start.x) {
				BallTurn seg = new BallTurn(ball, start, new Point(start.x + segNum, start.y));
				if (checkSegment(seg, result, false)) {
					result.add(seg);
					buildNextSegment(ball, seg.end, hole, segNum - 1, result, resultConsumer);
					result.removeLast();
				}
			}
		}

		return;
	}

	private static boolean checkSegment(BallTurn seg, LinkedList<BallTurn> result, boolean isLast) {
		// verify segment cross balls and holes
		// [0:start.x, 1:start.y, 2:end.x, 3:end.y]
		int[] testSeg = new int[] { seg.start.x, seg.start.y, seg.end.x, seg.end.y };

		// ignore the first point
		if (seg.start.x != seg.end.x) { // horizontal segment
			if (testSeg[0] < testSeg[2]) { // x1 < x2: right
				++testSeg[0]; // exclude first point (checked with prev segment or start ball

				if (!isLast) { // non last segment
					// first/mid segment: check last point for water/hole/ball
					int idx = testSeg[3] * SolutionState.height + testSeg[2];
					if (SolutionState.water.get(idx)
						|| SolutionState.holes.get(idx)
						|| SolutionState.balls.get(idx)) { // water/hole
						return false;
					}
				}
				--testSeg[2]; // exclude last point

				// check ball and hole without first and end points
				if (testSeg[2] >= testSeg[0]) { // at least 1 point
					int dy = testSeg[1] * SolutionState.height;
					for (int x = testSeg[0]; x <= testSeg[2]; x++) {
						if (SolutionState.holes.get(dy + x)
							|| SolutionState.balls.get(dy + x)) {
							return false;
						}
					}
				}

			} else { // x1 > x2: left
				--testSeg[0]; // the first segment
				if (!isLast) { // non last segment
					// first/mid segment: check end point in water/hole
					int idx = testSeg[1] * SolutionState.height + testSeg[2];
					if (SolutionState.holes.get(idx) || SolutionState.balls.get(idx)) { // hole/ball
						return false;
					}
				}
				++testSeg[2]; // exclude last point

				if (testSeg[2] <= testSeg[0]) { // first/mid segment
					int dy = testSeg[1] * SolutionState.height;
					for (int x = testSeg[0]; x >= testSeg[2]; x--) {
						if (SolutionState.holes.get(dy + x)
							|| SolutionState.balls.get(dy + x)) {
							return false;
						}
					}
				}
			}

		} else { // vertical segment
			if (seg.start.y < seg.end.y) { // y1 < y2: down
				++testSeg[1]; // the first segment

				if (!isLast) { // non last segment
					// first/mid segment: check end point in water/hole
					int idx = testSeg[1] * SolutionState.height + testSeg[2];
					if (SolutionState.holes.get(idx) || SolutionState.balls.get(idx)) { // hole/ball
						return false;
					}
				}
				--testSeg[3]; // exclude last point

				if (testSeg[3] >= testSeg[1]) { // at least 1 point
					for (int y = testSeg[1]; y <= testSeg[3]; y++) {
						int dy = y * SolutionState.height + testSeg[0];
						if (SolutionState.holes.get(dy)
							|| SolutionState.balls.get(dy)) {
							return false;
						}
					}
				}

			} else { // y1 > y2: up
				--testSeg[1]; // the first segment: exclude first point
				if (!isLast) { // non last segment
					// first/mid segment: check end point in water/hole
					int idx = testSeg[1] * SolutionState.height + testSeg[2];
					if (SolutionState.holes.get(idx) || SolutionState.balls.get(idx)) { // hole/ball
						return false;
					}
				}
				++testSeg[3]; // exclude last point

				if (testSeg[3] <= testSeg[1]) { // at least 1 point
					for (int y = testSeg[1]; y >= testSeg[3]; y--) {
						int dy = y * SolutionState.height + testSeg[0];
						if (SolutionState.holes.get(dy)
							|| SolutionState.balls.get(dy)) {
							return false;
						}
					}
				}
			}
		}

		// verify segments crossing
		if (!result.isEmpty()) {
			for (BallTurn s : result) {
				if (Line2D.linesIntersect(testSeg[0], testSeg[1], testSeg[2], testSeg[3], s.start.x, s.start.y, s.end.x, s.end.y)) {
					return false;
				}
			}
		}

		return true;
	}
}
