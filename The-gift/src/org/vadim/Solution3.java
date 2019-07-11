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
public class Solution3 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int giftCost = in.nextInt();
		int[] budgets = new int[N];

		int sum = 0;
		for (int i = 0; i < N; i++) sum += (budgets[i] = in.nextInt());
		if (sum < giftCost) {
			System.out.println("IMPOSSIBLE");
			return;
		}

		Arrays.sort(budgets);
		for (int i = 0; i < N; i++) {
			int restPayment = (int) (giftCost / (N - i));
			int itemPayment = budgets[i] < restPayment ? budgets[i] : restPayment;
			System.out.println(itemPayment);
			giftCost -= itemPayment;
		}
	}

}
