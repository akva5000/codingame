package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: An integer N for the number of cards to verify.
 * Next N lines: One line for each card to verify.
 *               Each test is consisting of four space-separated 4-digit strings.
 * 
 * Output
 * N lines: For each test case, output a line containing YES or NO depending on 
 *          whether the card number is valid or not, respectively.
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	private static final int[] step2Mapping = new int[] { 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 };

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}

		int[] number = new int[16];
		for (int i = 0; i < n; i++) {
			String card = in.nextLine();
			int pos = 0;
			for (int j = 0; j < card.length(); j++) {
				if (card.charAt(j) == ' ') continue;
				number[pos++] = card.charAt(j) - '0';
			}
			System.out.println(check(number) ? "YES" : "NO");
		}
	}

	private static boolean check(int[] number) {
		int sum1 = 0;
		for (int i = 0; i < 16; i += 2) {
			sum1 += step2Mapping[number[i]];
			sum1 += number[i + 1];
		}
		return sum1 % 10 == 0;
	}
}
