package org.vadim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * <pre>
 * Input
 * Line 1: xA yA xB yB
 * Line 2: n, the number of straight lines
 * Next n lines: a b c for each line
 * 
 * Output
 * If at least A or B is on one line, print ON A LINE
 * If not, print YES if A and B have the same colour, else NO
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int xA = in.nextInt();
		int yA = in.nextInt();
		int xB = in.nextInt();
		int yB = in.nextInt();
		System.err.println("points: (" + xA + ',' + yA + "), (" + xB + ',' + yB + ')');
		int n = in.nextInt();

		final List<float[]> lines = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();

			boolean isDup = false;
			float min = Math.abs((a == 0 ? (b == 0 ? c : b) : a));
			if (a != 0 && Math.abs(a) < min) min = Math.abs(a);
			if (b != 0 && Math.abs(b) < min) min = Math.abs(b);
			if (c != 0 && Math.abs(c) < min) min = Math.abs(c);

			float af = min == 1.0f ? a : a == 0 ? 0.0f : a / min;
			float bf = min == 1.0f ? b : b == 0 ? 0.0f : b / min;
			float cf = min == 1.0f ? c : c == 0 ? 0.0f : c / min;

			for (float[] line : lines) {
				if (line[0] == af && line[1] == bf && line[2] == cf) {
					isDup = true;
					break;
				}
			}

			if (isDup) continue;
			System.err.println("line: " + a + '(' + af + ")*x + " + b + '(' + bf + ")*y + " + c + '(' + cf + ") = 0");
			lines.add(new float[] { af, bf, cf });
		}

		// check online
		Optional<float[]> rc = lines.stream().parallel()
			.filter(line -> line[0] * xA + line[1] * yA + line[2] == 0.0f || line[0] * xB + line[1] * yB + line[2] == 0.0f) 
			.findAny();
			
		if (rc.isPresent()) {
			System.out.println("ON A LINE");
			return;
		}
		
		final List<Object[]> crossPoints = new ArrayList<>();
		for (int i = 1; i < lines.size(); i++) {
			for (int j = 0; j < i; j++) {
				float z = lines.get(i)[0] * lines.get(j)[1] - lines.get(j)[0] * lines.get(i)[1];
				if (z == 0.0f) continue;
				float cx = -(lines.get(i)[2] * lines.get(j)[1] - lines.get(j)[2] * lines.get(i)[1]) / z;
				float cy = -(lines.get(i)[0] * lines.get(j)[2] - lines.get(j)[0] * lines.get(i)[2]) / z;
				crossPoints.add(new Object[] { (float) cx, (float) cy, (int) i, (int) j });
			}
		}

		for (Object[] p : crossPoints) {
			System.err.println("cross-point: " + (float) p[0] + ',' + (float) p[1]);
		}

		// check online or intersections
		// y(x2-x1) - x(y2-y1) + x1*y2 - x2*y1 = 0
		// Ax + By + C = 0
		// A = y1-y2
		// B = x2-x1
		// C = x1*y2 - x2*y1
		float mA = yA - yB;
		float mB = xB - xA;
		float mC = xA * yB - xB * yA;
		List<float[]> cross = lines.stream().parallel()
				.map(points -> {
					float z = points[0] * mB - mA * points[1];
					return (z == 0.0f) ? null
							: new float[] { -(points[2] * mB - mC * points[1]) / z, -(points[0] * mC - mA * points[2]) / z };
					})
				.filter(item -> item != null)
				.filter(item -> xA < xB ? (item[0] > xA && item[0] < xB) : (item[0] > xB && item[0] < xA) )
				.filter(item -> yA < yB ? (item[1] > yA && item[1] < yB) : (item[1] > yB && item[1] < yA) )
				.sorted((o1, o2) -> Float.compare(o1[0], o2[0]))
				.collect(Collectors.toList());

		float[] prev = null;
		int counter = 0;
		for (float[] p : cross) {
			if(prev == null || prev[0] != p[0] || prev[1] != p[1]) {
				prev = p;
				++counter;
				System.err.println("AB cross: " + p[0] + ',' + p[1]);
			}
		}

		if (lines.size() == 1 || counter % 2 == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}
