package org.vadim;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: The number N of telephone numbers.
 * N following lines: Each line contains a phone number, with a maximum length L.
 * Telephone numbers consist of only the digits 0 to 9 included, without any spaces.
 * 
 * Output:
 * The number of elements (referencing a number) stored in the structure.
 * 
 * Constraints
 * 0 ≤ N ≤ 10000
 * 2 ≤ L ≤ 20
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int[][] plan = new int[N][];
		int maxLen = 0;
		for (int i = 0; i < N; i++) {
			String line = in.next();
			int[] phone = new int[line.length()];
			int pos = 0;
			for (char ch : line.toCharArray()) {
				phone[pos++] = ch - '0';
			}
			plan[i] = phone;
			if (phone.length > maxLen) maxLen = phone.length;
		}

		Arrays.sort(plan, (o1, o2) -> Arrays.compare(o1, o2));

		for (int[] phone : plan) {
			System.err.print('-');
			for (int i = 0; i < phone.length; i++) System.err.print(phone[i]);
			System.err.println();
		}

		int nodes = 0;
		int startRange = 0;
		for (int digit = 0; digit < 10; digit++) {
			if (plan[startRange][0] != digit) continue;
			int endRange = startRange + 1;

			while (endRange < N && plan[endRange][0] == digit) ++endRange;
			--endRange;
			if (startRange == endRange) {
				nodes += plan[startRange].length;
				++startRange;
			} else {
				++nodes;
				while (plan[startRange].length == 1) ++startRange;
				if (startRange == endRange) {
					nodes += plan[startRange].length - 1;
					++startRange;
				} else if (startRange < endRange) {
					nodes += deeper(plan, 1, startRange, endRange);
					startRange = endRange + 1;
				}
			}
			if (startRange >= N) break;
		}

		System.out.println(nodes);
	}

	private static int deeper(int[][] plan, int position, int startRange, int endRange) {
		int nodes = 0;
		int startRange2 = startRange;
		for (int digit = 0; digit < 10; digit++) {
			if ( position >= plan[startRange2].length || plan[startRange2][position] != digit) continue;
			if (startRange2 == plan.length - 1) {
				nodes += plan[startRange2].length - position;
				break;
			}

			int endRange2 = startRange2 + 1;
			while (endRange2 < plan.length && position < plan[endRange2].length && plan[endRange2][position] == digit) ++endRange2;
			--endRange2;
			if (startRange2 == endRange2) {
				nodes += plan[startRange2].length - position;
				++startRange2;
			} else {
				++nodes;
				while (plan[startRange2].length == position + 1) ++startRange2;
				if (startRange2 == endRange2) {
					nodes += plan[startRange2].length - position - 1;
					++startRange2;
				} else if (startRange2 < endRange2) {
					nodes += deeper(plan, position + 1, startRange2, endRange2);
					startRange2 = endRange2 + 1;
				}

			}
			if (startRange2 > endRange) break;
		}
		return nodes;
	}
}
