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
public class Solution2 {
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
		int[] work1 = Arrays.copyOf(priceParts, priceParts.length);
		int[] work2 = new int[work1.length];

		for (int i = work1.length - 1; i > 0; i--) { // last element
			int amount = 0;
			do {
				amount = 1;
				if (work1[i] == 1 || work1[i] == budgets[i]) break; // next position

				for (int p = 0; p < work1.length; p++) work2[p] = work1[p]; // copy

				--work2[i];
				for (int j = i - 1; j >= 0; j--) { // increase position
					if (work2[j] == budgets[j]) continue; // cannot be increased - go next position
					
					if(work2[j] > work2[i]) {
						amount += work2[j] - work2[i];
						work2[j] = work2[i];
					}
					
					if(work2[j] == work2[i]) {
						if(budgets[j] != budgets[i]) {
							++amount;
							--work2[j];
						}
						continue;
					}


					int amount1;
					// limit from budget
					if (amount + work2[j] > budgets[j]) {
						amount1 = budgets[j];
						amount -= amount1;
					} else {
						amount1 = amount;
						amount = 0;
					}

					if (amount1 <= 0) continue;
					
					// limit from the higher position
					if (work2[j] + amount1 > work2[i]) {
						amount += amount1 - Math.abs(work2[i] - work2[j]);
						amount1 = Math.abs(work2[i] - work2[j]);
					}

					// check for equal budget positions
					if (work2[j] + amount1 == work2[i] && budgets[j] != budgets[i]) {
						--amount1;
						++amount;
					}

					if (amount1 > 0) {
						work2[j] += amount1;
					}

					if (amount == 0) break;
				} // for j

				if (amount == 0) {
					int[] tmp = work1;
					work1 = work2;
					work2 = tmp;
				}
			} while (amount == 0);
		} // for i

		for (int p = 0; p < work1.length; p++) priceParts[p] = work1[p]; // copy
	}
}
