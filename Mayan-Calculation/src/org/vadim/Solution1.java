package org.vadim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: the width L and height H of a mayan numeral.
 * H next lines: the ASCII representation of the 20 mayan numerals. Each line is (20 x L) characters long.
 * 
 * Next line: the amount of lines S1 of the first number.
 * S1 next lines: the first number, each line having L characters.
 * 
 * Next line: the amount of lines S2 of the second number.
 * S2 next lines: the second number, each line having L characters.
 * 
 * Last line: the operation to carry out: *, /, +, or -
 * 
 * Output:
 * The result of the operation in mayan numeration, H lines per section, each line having L characters.
 * 
 * Constraints
 * 0 < L, H < 100
 * 0 < S1, S2 < 1000
 * The remainder of a division is always 0.
 * The mayan numbers given as input will not exceed 2^63.
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int L = in.nextInt();
		int H = in.nextInt();
		in.nextLine();

		final String[] ABC = new String[20];
		for (int n = 0; n < 20; n++) ABC[n] = "";

		readAbc(in, H, ABC, L);

		long value1 = readNumber(in, ABC, L, H);
		long value2 = readNumber(in, ABC, L, H);
		
		System.err.println("Numbers: " + value1 + ", " + value2);

		switch (in.next().charAt(0)) {
			case '+':
				value1 += value2;
				break;

			case '-':
				value1 -= value2;
				break;

			case '*':
				value1 *= value2;
				break;

			default: // '/'
				value1 /= value2;
				break;
		}

		String[] result = convToMay(ABC, value1);
		for (int i = 0; i < result.length; i++) {
			int pos = 0;
			for (int line = 0; line < H; line++) {
				System.out.println(result[i].substring(pos, pos + L));
				pos += L;
			}
		}

	}

	private static void readAbc(Scanner in, int H, final String[] ABC, int L) {
		for (int i = 0; i < H; i++) {
			String numeral = in.nextLine();
			int pos = 0;
			for (int n = 0; n < 20; n++) {
				ABC[n] += numeral.substring(pos, pos + L);
				pos += L;
			}
		}
	}

	private static long readNumber(Scanner in, final String[] ABC, int L, int H) {
		int linesNumber = in.nextInt();
		in.nextLine();

		int digCount = linesNumber / H;
		String[] digits = new String[digCount];

		for (int digIdx = 0; digIdx < digCount; digIdx++) {
			digits[digIdx] = "";
			for (int n = 0; n < L; n++) {
				digits[digIdx] += in.nextLine();
			}
		}

		char[] mayaDigits = convStrToMaya(ABC, digits);
		System.err.println("NUMBER: " + String.valueOf(mayaDigits));
		return Long.parseLong(String.valueOf(mayaDigits), 20);
	}

	private static char[] convStrToMaya(final String[] ABC, String[] digits) {
		char[] mDigits = new char[digits.length];
		for (int i = 0; i < digits.length; i++) {
			for (int j = 0; j < ABC.length; j++) {
				if (ABC[j].compareTo(digits[i]) == 0) {
					if (j < 10) mDigits[i] = (char) (j + '0');
					else mDigits[i] = (char) (j - 10 + 'a');
					break;
				}
			}
		}

		return mDigits;
	}

	private static String[] convToMay(final String[] ABC, long value) {
		List<Integer> decNumber = convMayToDec(value);
		final String[] digits = new String[decNumber.size()];
		for (int i = 0; i < digits.length; i++) digits[i] = ABC[decNumber.get(i)];
		return digits;
	}

	static List<Integer> convMayToDec(long value) {
		List<Integer> list = new ArrayList<>();
		String t = Long.toUnsignedString(value, 20);
		for (int i = 0; i < t.length(); i++) {
			char ch = t.charAt(i);
			if (ch >= '0' && ch <= '9') list.add((int) (ch - '0'));
			else list.add((int) (ch - 'a' + 10));
		}
		return list;
	}

}
