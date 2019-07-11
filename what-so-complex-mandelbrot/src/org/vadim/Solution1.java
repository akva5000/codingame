package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: The complex number to evaluate c represented as (x+yi) where x and y are floating point values.
 *         If y is negative, the + will become a -.
 * Line 2: An integer m indicating the maximum number of iterations to evaluate.
 * 
 * Output:
 * Line 1 : An integer i indicating how many times you need to iterate to determine
 *          if the complex number c is in the Mandelbrot set or not.
 * 
 * Constraints
 * length(c) < 30
 * -5 < x, y < 5
 * 0 < m < 1000
 * 0 < i <= m
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		double p;
		double q;
		{
			String c = in.nextLine();
			boolean sig1 = true;
			boolean sig2 = true;
			int pos = c.lastIndexOf('-');
			int pos2;
			if (pos == 0) { // -x+yi
				sig1 = false;
				pos = 1;
				pos2 = c.indexOf('+', pos) + 1;
			} else if (pos == -1) { // x+yi
				pos = 0;
				pos2 = c.indexOf('+', pos) + 1;
			} else { // ?x-yi
				sig2 = false;
				pos2 = pos + 1;
				if (c.charAt(0) == '-') { // -x-yi
					sig1 = false;
					pos = 1;
				} else { // x-yi
					pos = 0;
				}
			}

			p = Double.parseDouble(c.substring(pos, pos2 - 1));
			if (!sig1) p = -p;
			q = Double.parseDouble(c.substring(pos2, c.length() - 1));
			if (!sig2) q = -q;
		}

		System.err.println("IN: " + p + " " + q + "i");

		int m = in.nextInt();
		double xPrev = p;
		double yPrev = q;
		int round = 1;
		while (round <= m) {
			if (xPrev * xPrev + yPrev * yPrev > 4.0d) {
				System.err.println("Found: " + (xPrev * xPrev + yPrev * yPrev) + " round " + round);
				++round;
				break;
			}
			double x = xPrev * xPrev - yPrev * yPrev + p;
			double y = xPrev * yPrev + xPrev * yPrev + q;
			xPrev = x;
			yPrev = y;
			++round;
		}

		--round;
		System.out.println(round);
	}
}
