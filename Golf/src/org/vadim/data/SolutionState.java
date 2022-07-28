package org.vadim.data;

import java.awt.Point;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public abstract class SolutionState {
	public static int width;
	public static int height;
	
	public static BitSet water;
	public static BitSet holes;
	public static BitSet balls;
	public static List<Point> holePoints = new ArrayList<>();
	public static List<Ball> ballPoints = new ArrayList<>();
	public static BitSet field;
	public static PrintStream screen = System.out;
	
	private SolutionState() {
	}
}
