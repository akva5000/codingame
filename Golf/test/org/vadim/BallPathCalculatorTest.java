package org.vadim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Point;
import java.util.BitSet;
import java.util.List;

import org.junit.Test;
import org.vadim.data.Ball;
import org.vadim.data.SolutionState;
import org.vadim.turn.BallPathCalculator;
import org.vadim.turn.BallTurn;

public class BallPathCalculatorTest {

	@Test
	public void testSingleSegOkHor() {
		SolutionState.width = 10;
		SolutionState.height = 10;
		final Point hole = new Point(5, 10);
		final Point ball = new Point(5, 3);

		List<List<BallTurn>> rc = BallPathCalculator.getSolutions(new Ball(ball.x, ball.y, 1), hole);
		assertNotNull(rc);
		assertEquals(1, rc.size());

		List<BallTurn> turns = rc.get(0);
		assertNotNull(turns);
		assertEquals(1, turns.size());

		assertEquals(ball, turns.get(0).start);
		assertEquals(hole, turns.get(0).end);
	}

	@Test
	public void testSingleSegOkVer() {
		SolutionState.width = 20;
		SolutionState.height = 20;
		final Point hole = new Point(5, 10);
		final Point ball = new Point(7, 10);

		List<List<BallTurn>> rc = BallPathCalculator.getSolutions(new Ball(ball.x, ball.y, 1), hole);
		assertNotNull(rc);
		assertEquals(1, rc.size());

		List<BallTurn> turns = rc.get(0);
		assertNotNull(turns);
		assertEquals(1, turns.size());

		assertEquals(ball, turns.get(0).start);
		assertEquals(hole, turns.get(0).end);
	}

	@Test
	public void testSingleSegFail() {
		SolutionState.width = 20;
		SolutionState.height = 20;
		final Point hole = new Point(5, 10);
		final Point ball = new Point(7, 3);

		List<List<BallTurn>> rc = BallPathCalculator.getSolutions(new Ball(ball.x, ball.y, 1), hole);
		assertNotNull(rc);
		assertEquals(0, rc.size());
	}

	@Test
	public void testMultipleSegs1() {
		SolutionState.width = 3;
		SolutionState.height = 3;
		SolutionState.balls = new BitSet(SolutionState.width * SolutionState.height);
		SolutionState.holes = new BitSet(SolutionState.width * SolutionState.height);
		SolutionState.water = new BitSet(SolutionState.width * SolutionState.height);
		final Point hole = new Point(1, 2);
		SolutionState.holes.set(hole.y * SolutionState.height + hole.x);
		final Point ball = new Point(0, 0);
		SolutionState.balls.set(ball.y * SolutionState.height + ball.x);

		List<List<BallTurn>> rc = BallPathCalculator.getSolutions(new Ball(ball.x, ball.y, 2), hole);
		assertNotNull(rc);
		assertEquals(1, rc.size());

		List<BallTurn> turns = rc.get(0);
		assertNotNull(turns);
		assertEquals(2, turns.size());

		assertEquals(ball, turns.get(0).start);
		assertEquals(new Point(0, 2), turns.get(0).end);

		assertEquals(new Point(0, 2), turns.get(1).start);
		assertEquals(hole, turns.get(1).end);
	}

	@Test
	public void testMultipleSegs2() {
		SolutionState.width = 10;
		SolutionState.height = 10;
		SolutionState.balls = new BitSet(SolutionState.width * SolutionState.height);
		SolutionState.holes = new BitSet(SolutionState.width * SolutionState.height);
		SolutionState.water = new BitSet(SolutionState.width * SolutionState.height);
		final Point hole = new Point(5, 5);
		SolutionState.holes.set(hole.y * SolutionState.height + hole.x);
		final Point ball = new Point(0, 0);
		SolutionState.balls.set(ball.y * SolutionState.height + ball.x);

		List<List<BallTurn>> rc = BallPathCalculator.getSolutions(new Ball(ball.x, ball.y, 4), hole);
		assertNotNull(rc);
		assertEquals(2, rc.size());

		// Path #1
		List<BallTurn> turns = rc.get(0);
		assertNotNull(turns);
		assertEquals(4, turns.size());

		assertEquals(ball, turns.get(0).start);
		assertEquals(new Point(0, 4), turns.get(0).end);

		assertEquals(new Point(0, 4), turns.get(1).start);
		assertEquals(new Point(3, 4), turns.get(1).end);

		assertEquals(new Point(3, 4), turns.get(2).start);
		assertEquals(new Point(5, 4), turns.get(2).end);

		assertEquals(new Point(5, 4), turns.get(3).start);
		assertEquals(hole, turns.get(3).end);

		// Path #2
		turns = rc.get(1);
		assertNotNull(turns);
		assertEquals(4, turns.size());

		assertEquals(ball, turns.get(0).start);
		assertEquals(new Point(4, 0), turns.get(0).end);

		assertEquals(new Point(4, 0), turns.get(1).start);
		assertEquals(new Point(4, 3), turns.get(1).end);

		assertEquals(new Point(4, 3), turns.get(2).start);
		assertEquals(new Point(4, 5), turns.get(2).end);

		assertEquals(new Point(4, 5), turns.get(3).start);
		assertEquals(hole, turns.get(3).end);
	}
	
	@Test
	public void testMultipleSegs3() {
		SolutionState.width = 20;
		SolutionState.height = 20;
		SolutionState.balls = new BitSet(SolutionState.width * SolutionState.height);
		SolutionState.holes = new BitSet(SolutionState.width * SolutionState.height);
		SolutionState.water = new BitSet(SolutionState.width * SolutionState.height);
		final Point hole = new Point(2, 3);
		SolutionState.holes.set(hole.y * SolutionState.height + hole.x);
		final Point ball = new Point(5, 5);
		SolutionState.balls.set(ball.y * SolutionState.height + ball.x);

		List<List<BallTurn>> rc = BallPathCalculator.getSolutions(new Ball(ball.x, ball.y, 5), hole);
		assertNotNull(rc);
		assertEquals(1, rc.size());

		// Path #1
		List<BallTurn> turns = rc.get(0);
		assertNotNull(turns);
		assertEquals(5, turns.size());

		assertEquals(ball, turns.get(0).start);
		assertEquals(new Point(0, 5), turns.get(0).end);

		assertEquals(new Point(0, 5), turns.get(1).start);
		assertEquals(new Point(0, 1), turns.get(1).end);

		assertEquals(new Point(0, 1), turns.get(2).start);
		assertEquals(new Point(3, 1), turns.get(2).end);

		assertEquals(new Point(3, 1), turns.get(3).start);
		assertEquals(hole, turns.get(3).end);

		// Path #2
		turns = rc.get(1);
		assertNotNull(turns);
		assertEquals(4, turns.size());

		assertEquals(ball, turns.get(0).start);
		assertEquals(new Point(4, 0), turns.get(0).end);

		assertEquals(new Point(4, 0), turns.get(1).start);
		assertEquals(new Point(4, 3), turns.get(1).end);

		assertEquals(new Point(4, 3), turns.get(2).start);
		assertEquals(new Point(4, 5), turns.get(2).end);

		assertEquals(new Point(4, 5), turns.get(3).start);
		assertEquals(hole, turns.get(3).end);
	}
}
