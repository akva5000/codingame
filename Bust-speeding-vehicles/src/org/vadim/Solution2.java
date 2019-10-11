package org.vadim;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: An integer L for the Speed Limit for the given road (in Kilometers per hour).
 * Line 2: An integer N which represents the total number of camera readings.
 * Next N Lines: 3 space separated values: The license plate recorded L, 
 *               the camera distance (in Kilometers from the begining of the road) C and 
 *               the timestamp of the camera capture (number of seconds since 01/01/1970) T.
 * 
 * Output:
 * For each speeding vehicles: print the license plate L with the detecting camera distance C, 
 * seperated by spaces, in the same order as input.
 * When no vehicle is speeding: print OK
 * 
 * Constraints
 * 10 ≤ L ≤ 100
 * 0 ≤ N ≤ 100
 * 0 ≤ C ≤ 1000
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int speedLimit = in.nextInt();
		int camerasNumber = in.nextInt();
		if (in.hasNextLine()) in.nextLine();
		System.err.println("limit=" + speedLimit + ", N=" + camerasNumber);

		final StringBuilder buf = new StringBuilder();

		final List<Integer> distances = new ArrayList<>();
		final List<Long> time = new ArrayList<>();

		String plate = null;
		for (int i = 0; i < camerasNumber; i++) {
			String line = in.nextLine();
			System.err.println("@ " + line);
			int pos = line.indexOf(' ');
			String plate2 = line.substring(0, pos);
			if (plate != null && plate.compareTo(plate2) != 0) {
				// calculate old plate
				detectOverSpeed(speedLimit, plate, distances, time, buf);
				distances.clear();
				time.clear();
				plate = plate2;
			} else {
				int pos2 = line.indexOf(' ', pos + 1);
				distances.add(Integer.valueOf(line.substring(pos + 1, pos2)));
				time.add(Long.valueOf(line.substring(pos2 + 1)));
				if (plate == null) plate = plate2;
			}
		}
		detectOverSpeed(speedLimit, plate, distances, time, buf);

		if (buf.length() == 0) buf.append("OK\n");
		System.out.print(buf.toString());
	}

	private static void detectOverSpeed(final int maxSpeed, String plate, final List<Integer> distances, final List<Long> time,
			final StringBuilder buf) {
		if (distances.size() == 1) return;

		BitSet set = new BitSet(distances.size());
		for (int i1 = 0; i1 < distances.size() - 1; i1++) {
			for (int i2 = i1 + 1; i2 < distances.size(); i2++) {
				if (set.get(i2)) continue;
				float speed = 3600 * (distances.get(i2).intValue() - distances.get(i1).intValue())
						/ (float) (time.get(i2).longValue() - time.get(i1).longValue());
				int s = (int) speed;
				if (s != speed) ++s;
				System.err.println("speed=" + s);
				if (s > maxSpeed) {
					set.set(i2);
					buf.append(plate).append(' ').append(distances.get(i2)).append('\n');
				}
			}
		}
	}

}
