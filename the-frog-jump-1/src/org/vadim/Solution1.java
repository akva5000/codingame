package org.vadim;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: The number of frogs f at the contest (without your frog).
 * Line 2: Frogs jump lengths in meters of each frog separated by spaces.
 * Line 3: Two integers x y separated by space representing the initial position of your frog.
 * Line 4: An integer m representing the mass of your frog in grams.
 * Line 5: An integer alpha which is the angle alpha of the speed vector at t = 0 in degree.
 * Line 6: A float speed representing the norm of the initial speed vector (at t = 0) in meters / seconds
 * Line 7: Two floats a b separated by space representing the vector of the gravity intensity on the planet
 *         where the frog is in meters / seconds².
 * 
 * Output
 * One line with the place of your frog in the contest.
 * 
 * Constraints
 * The x is horizontal and y is vertical.
 * 
 * The contest cannot be organized if there are less than 3 frogs or more than 100 frogs. 3 ≤ f ≤ 100
 * 
 * The ground is flat and is a sea level +0.
 * The mass of the frog is strictly positive. 0 < m ≤1500
 * The angle can be between 0° and 90°. 0 ≤ alpha ≤ 90
 * The speed can only be positive. 0 ≤ speed
 * The gravity intensity is strictly vertical. a = 0 and b < 0
 * 
 * The frog always comes back on the ground.
 * The frog jumps along the positive direction of the x axis.
 * 
 * For the precision of the distance, we round to two decimal places.
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int frogNumber = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		Float[] distances = new Float[frogNumber + 1];
		for (int i = 0; i < frogNumber; i++) {
			distances[i] = in.nextFloat();
		}
		if (in.hasNextLine()) in.nextLine();

		for (Float t : distances) {
			System.err.println("# dist: " + t);
		}

		int[] initialPosition = new int[] { in.nextInt(), in.nextInt() };
		if (in.hasNextLine()) in.nextLine();

		int mass = in.nextInt();
		int alpha = in.nextInt();
		float speed = in.nextFloat();
		if (in.hasNextLine()) in.nextLine();

		float[] gravity = new float[] { in.nextFloat(), in.nextFloat() };

		/**
		 * Calculations
		 */
		float[] speedVec = new float[] {
				(float) (Math.cos(alpha * Math.PI / 180.0d) * speed),
				(float) (Math.sin(alpha * Math.PI / 180.0d) * speed) };

		float delta = speedVec[1] * speedVec[1] - 2.0f * gravity[1] * initialPosition[1];
		float time = (float) ((-speedVec[1] - Math.sqrt(delta)) / gravity[1]);
		float xFinal = (gravity[0] * 0.5f * time * time) + (speedVec[0] * time) + initialPosition[0];
		System.err.println("# dist=" + xFinal);

		xFinal = Math.round(xFinal * 100.0f) / 100.0f;
		System.err.println("# dist round=" + xFinal);

		distances[distances.length-1] = xFinal;
		Arrays.parallelSort(distances, (o1, o2) -> Float.compare(o2, o1));
		for (int i = 0; i < distances.length; i++) {
			if(distances[i] == xFinal) {
				System.out.println(i + 1);
				return;
			}
		}
	}
}
