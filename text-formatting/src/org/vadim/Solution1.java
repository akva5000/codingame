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
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String text = in.nextLine();
		System.out.println(handle(text));
	}

	/**
	 * <pre>
	 * Only a single space between words (remove excessive spaces) - aaa   bbb | aaa bbb
	 * No spaces before punctuation marks - aaa , bbb | aaa, bbb 
	 * One space after each punctuation mark in front of a letter;
	 * Use only lowercase letters, except for the beginning of the sentence (after a dot);
	 * Remove repeated punctuation marks.
	 * </pre>
	 */
	public static String handle(String text) {
		final StringBuilder buf = new StringBuilder(text);

		// remove double spaces
		int pos = 0;
		while (pos < buf.length()) {
			if (buf.charAt(pos) == ' ' && pos + 1 < buf.length() && buf.charAt(pos + 1) == ' ') {
				buf.deleteCharAt(pos);
			} else {
				++pos;
			}
		}

		pos = 0;
		while (pos < buf.length()) {
			if (buf.charAt(pos) == ',' || buf.charAt(pos) == '.') {
				if (pos > 0 && buf.charAt(pos - 1) == ' ') {
					buf.deleteCharAt(pos - 1);
					--pos;
				} else if (pos + 1 < buf.length() && buf.charAt(pos + 1) == buf.charAt(pos)) {
					buf.deleteCharAt(pos);
				} else {
					++pos;
				}
			} else {
				++pos;
			}
		}

		pos = 0;
		while (pos < buf.length()) {
			if ((buf.charAt(pos) == ',' || buf.charAt(pos) == '.') && pos + 1 < buf.length()
					&& ((buf.charAt(pos + 1) >= 'a' && buf.charAt(pos + 1) <= 'z')
							|| (buf.charAt(pos + 1) >= 'A' && buf.charAt(pos + 1) <= 'Z'))) {
				buf.insert(pos + 1, ' ');
				++pos;
			}
			++pos;
		}
		
		pos = 0;
		while (pos < buf.length()) {
			if ( ((buf.charAt(pos + 1) >= 'a' && buf.charAt(pos + 1) <= 'z')
							|| (buf.charAt(pos + 1) >= 'A' && buf.charAt(pos + 1) <= 'Z'))) {
				++pos;
			}
			++pos;
		}

		return buf.toString();
	}

}
