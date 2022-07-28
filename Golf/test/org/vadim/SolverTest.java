package org.vadim;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.BitSet;
import java.util.List;

import org.junit.Test;
import org.vadim.data.Ball;
import org.vadim.data.SolutionState;

public class SolverTest {

	@Test
	public void testSingleBallSolveDown() {
		SolutionState.width = 10;
		SolutionState.height = 10;
		SolutionState.balls = new BitSet(10 * 10);
		SolutionState.balls.set(5 * 10 + 5);
		SolutionState.holes = new BitSet(10 * 10);
		SolutionState.holes.set(8 * 10 + 5);
		SolutionState.ballPoints = List.of(new Ball(5, 5, 1));
		SolutionState.holePoints = List.of(new Point(5, 8));
		ByteArrayOutputStream buf = new ByteArrayOutputStream(SolutionState.width * SolutionState.height);
		SolutionState.screen = new PrintStream(buf);

		Solver service = new Solver();
		service.singleBallSolve();

		assertEquals("..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n"
				+ ".....v....\n"
				+ ".....v....\n"
				+ ".....v....\n"
				+ "..........\n"
				+ "..........\n", buf.toString());
	}

	@Test
	public void testSingleBallSolveUp() {
		SolutionState.width = 10;
		SolutionState.height = 10;
		SolutionState.balls = new BitSet(10 * 10);
		SolutionState.balls.set(5 * 10 + 5);
		SolutionState.holes = new BitSet(10 * 10);
		SolutionState.holes.set(2 * 10 + 5);
		SolutionState.ballPoints = List.of(new Ball(5, 5, 1));
		SolutionState.holePoints = List.of(new Point(5, 2));
		ByteArrayOutputStream buf = new ByteArrayOutputStream(SolutionState.width * SolutionState.height);
		SolutionState.screen = new PrintStream(buf);

		Solver service = new Solver();
		service.singleBallSolve();

		assertEquals("..........\n"
				+ "..........\n"
				+ "..........\n"
				+ ".....^....\n"
				+ ".....^....\n"
				+ ".....^....\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n", buf.toString());
	}

	@Test
	public void testSingleBallSolveRight() {
		SolutionState.width = 10;
		SolutionState.height = 10;
		SolutionState.balls = new BitSet(10 * 10);
		SolutionState.balls.set(5 * 10 + 5);
		SolutionState.holes = new BitSet(10 * 10);
		SolutionState.holes.set(5 * 10 + 8);
		SolutionState.ballPoints = List.of(new Ball(5, 5, 1));
		SolutionState.holePoints = List.of(new Point(8, 5));
		ByteArrayOutputStream buf = new ByteArrayOutputStream(SolutionState.width * SolutionState.height);
		SolutionState.screen = new PrintStream(buf);

		Solver service = new Solver();
		service.singleBallSolve();

		assertEquals("..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n"
				+ ".....>>>..\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n", buf.toString());
	}

	@Test
	public void testSingleBallSolveLeft() {
		SolutionState.width = 10;
		SolutionState.height = 10;
		SolutionState.balls = new BitSet(10 * 10);
		SolutionState.balls.set(5 * 10 + 5);
		SolutionState.holes = new BitSet(10 * 10);
		SolutionState.holes.set(5 * 10 + 2);
		SolutionState.ballPoints = List.of(new Ball(5, 5, 1));
		SolutionState.holePoints = List.of(new Point(2, 5));
		ByteArrayOutputStream buf = new ByteArrayOutputStream(SolutionState.width * SolutionState.height);
		SolutionState.screen = new PrintStream(buf);

		Solver service = new Solver();
		service.singleBallSolve();

		assertEquals("..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "...<<<....\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n"
				+ "..........\n", buf.toString());
	}

	@Test
	public void testMultyBallsSolve() {
		SolutionState.width = 20;
		SolutionState.height = 20;
		SolutionState.field = new BitSet(SolutionState.width * SolutionState.height);
		SolutionState.balls = new BitSet(SolutionState.width * SolutionState.height);
		SolutionState.holes = new BitSet(SolutionState.width * SolutionState.height);
		SolutionState.water = new BitSet(SolutionState.width * SolutionState.height);

		final Point hole1 = new Point(2, 3);
		SolutionState.holes.set(hole1.y * SolutionState.height + hole1.x);
		final Point hole2 = new Point(8, 8);
		SolutionState.holes.set(hole2.y * SolutionState.height + hole2.x);

		final Point ball1 = new Point(5, 5);
		SolutionState.balls.set(ball1.y * SolutionState.height + ball1.x);
		final Point ball2 = new Point(9, 10);
		SolutionState.balls.set(ball2.y * SolutionState.height + ball2.x);

		SolutionState.ballPoints = List.of(new Ball(ball1.x, ball1.y, 5), new Ball(ball2.x, ball2.y, 5));
		SolutionState.holePoints = List.of(hole1, hole2);

		Solver service = new Solver();
		service.multyBallsSolve();
	}

}
