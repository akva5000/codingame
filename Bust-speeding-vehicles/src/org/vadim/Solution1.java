package org.vadim;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		float speedLimit = in.nextInt();
		int camerasNumber = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		final Map<String, List<Meassure>> data = new LinkedHashMap<>();
		for (int i = 0; i < camerasNumber; i++) {
			String line = in.nextLine();
			int pos = line.indexOf(' ');
			String plate = line.substring(0, pos);
			final List<Meassure> list = data.computeIfAbsent(plate, key -> new ArrayList<>());
			int pos2 = line.indexOf(' ', pos + 1);
			int distance = Integer.parseInt(line.substring(pos + 1, pos2));
			long timestamp = Long.parseLong(line.substring(pos2 + 1));
			list.add(new Meassure(distance, timestamp));
		}
		
		System.err.println("speed-limit=" + speedLimit);

		final List<OverSpeedItem> overSpeedList = data.entrySet()
			.parallelStream()
				.map(entry -> {
					OverSpeedItem item = calculateMaxSpeed(speedLimit, entry.getValue());
					if(item != null) {
						item.plate = entry.getKey();
						System.err.println("# " + item.plate + ", " + item.distance + '/' + item.maxSpeed);
					}
					return item;
				})
			.filter(item -> item != null)
			.filter(item -> item.maxSpeed > speedLimit)
			.collect(Collectors.toList());

		if(overSpeedList.isEmpty()) {
			System.out.println("OK");
		} else {
			overSpeedList.stream()
				.forEach(item -> System.out.println(item.plate + ' ' + item.distance));
		}
	}

	private static OverSpeedItem calculateMaxSpeed(final float maxSpeed, final List<Meassure> list) {
		if(list.size() == 1) return null;
		
		for (int i1 = 0; i1 < list.size() - 1; i1++) {
			Meassure m1 = list.get(i1);
			for (int i2 = i1 + 1; i2 < list.size(); i2++) {
				Meassure m2 = list.get(i2);
				System.err.println("" + m1.distance + '/' + m1.timestamp + " - " + m2.distance + '/' + m2.timestamp);
				float speed = (m2.distance - m1.distance) / ((m2.timestamp - m1.timestamp) / 3600.0f);
				System.err.println("speed=" + speed);
				if(speed > maxSpeed) return new OverSpeedItem(m2.distance, speed);
			}
		}

		return null;
	}

	private static class OverSpeedItem {
		String plate;
		final int distance;
		final float maxSpeed;
		public OverSpeedItem(int distance, float maxSpeed) {
			this.distance = distance;
			this.maxSpeed = maxSpeed;
		}
	}
	
	private static class Meassure {
		final int distance;
		final long timestamp;

		public Meassure(int distance, long timestamp) {
			this.distance = distance;
			this.timestamp = timestamp;
		}

	}
}
