package org.vadim;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: three integers N, C and P.
 * Next N lines: two integers, the budget and joy of each friend.
 * 
 * Output:
 * Line 1: the maximum happiness that you can get from this trip.
 * 
 * Constraints
 * 1 ≤ N ≤ 900
 * 0 ≤ P ≤ 1000
 * 0 ≤ C ≤ 10^5
 * 0 ≤ budget ≤ 10^5
 * -10^5 ≤ joy ≤ 10^5
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt(); // N persons
		int C = in.nextInt(); // constant price
		int P = in.nextInt(); // price per person
		in.nextLine();

		final int[][] people = new int[N][2]; // budget, joy

		for (int i = 0; i < N; i++) {
			people[i][0] = in.nextInt();
			people[i][1] = in.nextInt();
			in.nextLine();
		}

		Arrays.parallelSort(people, (o1, o2) -> {
			int v = Integer.compare(o2[0], o1[0]);
			return v != 0 ? v : Integer.compare(o2[1], o1[1]);
		});

		int joy = 0;
		float CC = C;
		for (int M = N + 1, p = N - 1; M > 1; M--, p--) {
			float cost = CC / M + P;
			if (people[p][0] >= cost) {
				int pos = M - 2;
				while (pos < N && people[pos][0] >= cost) ++pos;
				int j = calcJoy(people, M - 1, pos);
				if (j > joy) joy = j;
			}
		}

		System.out.println(String.valueOf(joy));
	}

	private static int calcJoy(final int[][] people, int num, int maxNum) {
		return Arrays.asList(people)
			.subList(0, maxNum)
			.stream()
			.sorted((o1, o2) -> Integer.compare(o2[1], o1[1]))
			.limit(num)
			.mapToInt(o -> o[1])
			.sum();
	}
}
