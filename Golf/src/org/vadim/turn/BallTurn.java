package org.vadim.turn;

import java.awt.Point;

import org.vadim.data.Ball;

public class BallTurn {
	public final Ball ball;
	public final Point start;
	public final Point end;

	public BallTurn(Ball ball, Point start, Point end) {
		this.ball = ball;
		this.start = start;
		this.end = end;
	}
}
