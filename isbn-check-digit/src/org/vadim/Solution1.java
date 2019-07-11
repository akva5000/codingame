package org.vadim;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: An integer N for the number of ISBNs to verify.
 * Next N lines: One line for each ISBN to verify.
 * 
 * Output
 * Line 1: A string Y invalid: where Y is the number of invalid ISBNs.
 * Next Y lines: One line for each invalid ISBN, in the same order as given in the inputs.
 * 
 * Constraints
 * 1 ≤ N ≤ 500
 * 1 ≤ Y ≤ N (i.e. there will always be at least one invalid ISBN in each case)
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		final ArrayList<String> invalidCodes = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String ISBN = in.nextLine();
			if (!(ISBN.length() == 10 ? checkIsbn10(ISBN) : ISBN.length() == 13 ? checkIsbn13(ISBN) : false))
				invalidCodes.add(ISBN);
		}

		System.out.println("" + invalidCodes.size() + " invalid:");
		for (String code : invalidCodes) {
			System.out.println(code);
		}
	}

	private static boolean checkIsbn10(String code) {
		int[] number = new int[10];
		if (!convert(code, number)) return false;
		int sum = 0;
		for (int m = 10, i = 0; m > 1; m--, i++) sum += number[i] * m;
		int rem = sum % 11;
		return rem == 0 ? number[9] == 0 : rem + number[9] == 11;
	}

	private static boolean checkIsbn13(String code) {
		int[] number = new int[13];
		if (!convert(code, number)) return false;
		int sum = 0;
		for (int i = 0; i < number.length - 1; i++) {
			sum += number[i] * (i % 2 == 0 ? 1 : 3);
		}
		int rem = sum % 10;
		return rem == 0 ? number[12] == 0 : rem + number[12] == 10;
	}

	private static boolean convert(String code, int[] number) {
		for (int i = 0; i < code.length(); i++) {
			number[i] = code.charAt(i) - '0';
			if (number[i] < 0 || number[i] > 9) {
				if (i == code.length() - 1 && code.charAt(code.length() - 1) == 'X') number[code.length() - 1] = 10;
				else return false;
			}
		}

		return true;
	}
}
