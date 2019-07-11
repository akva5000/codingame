package org.vadim;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: the number N of participants
 * Line 2: the price C of the gift
 * N following lines: the list of budgets B of participants.
 * 
 * Output:
 * If it is possible to buy the present : N lines, each containing the contribution of a participant. The list is sorted in ascending order.
 * Otherwise the word IMPOSSIBLE.
 * 
 * Constraints
 * 0 < N ≤ 2000
 * 0 ≤ C ≤ 1000000000
 * 0 ≤ B ≤ 1000000000
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int giftCost = in.nextInt();
		int[] budgets = new int[N];
		for (int i = 0; i < N; i++) {
			budgets[i] = in.nextInt();
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += budgets[i];
		}

		if (sum < giftCost) {
			System.out.println("IMPOSSIBLE");
			return;
		}

		Arrays.sort(budgets);

		// game...
		float[] pers = new float[N];
		for (int i = 0; i < N; i++) {
			pers[i] = budgets[i] * 100.0f / (float) sum;
			if (pers[i] < 1) pers[i] = 1;
		}

		int[] priceParts = new int[N];
		for (int i = 0; i < N; i++) {
			priceParts[i] = (int) (giftCost * pers[i] / 100.0f);
		}

		int sum2 = 0;
		for (int i = 0; i < N; i++) {
			sum2 += priceParts[i];
		}

		if (sum2 < giftCost) {
			priceParts[N - 1] += (giftCost - sum2);
		} else if (sum2 > giftCost) {
			priceParts[N - 1] -= (sum2 - giftCost);
		}

		optimization(budgets, priceParts);

		for (int i = 0; i < N; i++) {
			System.out.println(priceParts[i]);
		}
	}

	private static void optimization(int[] budgets, int[] priceParts) {
		for (int i = budgets.length - 1; i > 0; i--) {
			while (priceParts[i] > 1 && priceParts[i] > priceParts[i - 1] + 1 && priceParts[i - 1] + 1 <= budgets[i - 1]) {
				--priceParts[i];
				++priceParts[i - 1];
			}
		}
	}
}
