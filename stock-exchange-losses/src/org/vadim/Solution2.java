package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: the number n of stock values available.
 * Line 2: the stock values arranged in order, from the date of their introduction on the stock market v1 until the last known value vn. The values are integers.
 * 
 * Output
 * The maximal loss p, expressed negatively if there is a loss, otherwise 0.
 * </pre>
 */
public class Solution2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] data = new int[n];

		int size = 0;
		int prevV = -1;
		for (int i = 0; i < n; i++) {
			int v = in.nextInt();
			if (v == prevV) continue;
			data[size++] = v;
			prevV = v;
		}

		while (true) {
			if (handle1(data, size)) {
				data = compact(data, size);
				size = data.length;
			} else break;
		}

		int maxGap = detect(data);
		System.out.println(maxGap);
	}

	private static int[] compact(int[] data, int size) {
		int counter = 0;
		for (int i = 0; i < size; i++) {
			if (data[i] != -1) ++counter;
		}

		int[] list = new int[counter];
		int pos = 0;
		for (int i = 0; i < size; i++) {
			if (data[i] != -1) list[pos++] = data[i];
		}
		return list;
	}

	private static int detect(int[] data) {
		int[] peaks = new int[data.length];

		for (int x = 0; x < data.length - 1; x++) {
			int peak = 0;
			for (int y = x + 1; y < data.length; y++) {
				if (data[y] >= data[x]) break;
				if (data[y] < data[x] && data[x] - data[y] > peak) peak = data[x] - data[y];
			}
			peaks[x] = peak;
		}

		int max = 0;
		for (int i = 0; i < peaks.length; i++) {
			if (peaks[i] > max) max = peaks[i];
		}
		return -max;
	}

	private static boolean handle1(int[] data, int size) {
		if (pattern1(data, size)) return true;
		if (pattern2(data, size)) return true;
		if (pattern3(data, size)) return true;
		return false;
	}

	// equal points
	private static boolean pattern1(int[] data, int size) {
		int flag = 0;
		int pos = 0;

		int prevV = -1;
		while (pos < size) {
			if (data[pos] != -1) {
				if (prevV == data[pos]) {
					data[pos] = -1;
					++flag;
				} else {
					prevV = data[pos];
				}
			}
			++pos;
		}

		if (flag > 0) print(data, size);
		return flag != 0;
	}

	// gradient simplify
	private static boolean pattern2(int[] data, int size) {
		int flag = 0;
		int pos = 0;

		int prevV = data[pos++];
		int segmentStartPos = 0;
		int dir = 0; // -1 decrease, 1 increase
		while (pos < size) {
			switch (dir) {
				case 0:
					dir = data[pos] < prevV ? -1 : 1;
					prevV = data[pos];
					break;

				case -1:
					if (data[pos] >= prevV) { // segment end
						if (pos - segmentStartPos > 2) { // simplify
							++flag;
							for (int i = segmentStartPos + 1; i < pos - 1; i++) {
								data[i] = -1;
							}
						}

						dir = 0;
						prevV = data[pos];
						segmentStartPos = pos;
					} else {
						prevV = data[pos];
					}
					break;

				case 1:
					if (data[pos] < prevV) { // segment end
						if (pos - segmentStartPos > 2) { // simplify
							++flag;
							for (int i = segmentStartPos + 1; i < pos - 1; i++) {
								data[i] = -1;
							}
						}

						dir = 0;
						prevV = data[pos];
						segmentStartPos = pos;
					} else {
						prevV = data[pos];
					}
					break;

				default:
					break;
			}
			++pos;
		}

		if (flag > 0) print(data, size);
		return flag != 0;
	}

	// remove the tail up hook
	private static boolean pattern3(int[] data, int size) {
		int pos1 = -1;
		int pos2 = -1;

		for (int i = size - 1; i >= 0; i--) {
			if (data[i] != -1) {
				pos2 = i;
				break;
			}
		}

		if (pos2 <= 0) return false;
		for (int i = pos2 - 1; i >= 0; i--) {
			if (data[i] != -1) {
				pos1 = i;
				break;
			}
		}

		if (pos1 <= 0) return false;
		if (data[pos2] > data[pos1]) {
			data[pos2] = -1;
			print(data, size);
			return true;
		}
		return false;
	}

	private static void print(int[] data, int size) {
		for (int i = 0; i < size; i++) {
			if (data[i] == -1) continue;
			System.err.print(data[i] + ", ");
		}
		System.err.println();
	}
}
