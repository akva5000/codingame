package org.vadim.data;

import java.awt.Point;

public class Ball {
	public final Point start;
	public final int turns;

	public Ball(int x, int y, int turns) {
		this.start = new Point(x, y);
		this.turns = turns;
	}
}