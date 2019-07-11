package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input:
 * A line of text.
 * 
 * Output:
 * A line containing the Formatted text.
 * 
 * Constraints
 * The length of the input string is less than 1000.
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		char[] text = in.nextLine().toCharArray();

		boolean isDot = false;
		int pos = 0;
		while (pos < text.length) {
			char ch = text[pos];
			if (ch == ' ') {
				++pos;
				if (pos >= text.length || text[pos] == ' ') { // skip the last space or if the next is space
					continue;
				}

				// No spaces before punctuation marks
				if ((text[pos] >= 'a' && text[pos] <= 'z') || (text[pos] >= 'A' && text[pos] <= 'Z') || (text[pos] >= '0' && text[pos] <= '9')) {
					System.out.print(ch);
					continue;
				}

			} else if (ch >= 'a' && ch <= 'z') {
				if (pos == 0 || isDot) ch -= 32;
				System.out.print(ch);
				if (isDot) isDot = false;
				++pos;

			} else if (ch >= 'A' && ch <= 'Z') {
				if (!isDot && pos != 0) ch += 32;
				System.out.print(ch);
				if (isDot) isDot = false;
				++pos;

			} else if (ch >= '0' && ch <= '9') {
				if (isDot) isDot = false;
				System.out.print(ch);
				++pos;

			} else { // punctuation
				if (isDot) isDot = false;
				++pos;

				if (pos < text.length && text[pos] == ch) { // Remove repeated punctuation marks.
					continue;
				}

				System.out.print(ch);
				if (ch == '.') isDot = true;

				while (pos < text.length && text[pos] == ' ') {
					++pos;
					if (text[pos] == ch) ++pos;
				}

				if (pos < text.length) { // space after punctuation
					System.out.print(' ');
				}
			}

		} // while

		System.out.println();
	}

}
