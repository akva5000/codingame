package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input:
 * The first and the only line contains string S consisting of digits only.
 * 
 * Output:
 * Output two numbers A and B separated by space.
 * If such numbers can't be formed then output -1 -1.
 * 
 * Constraints
 * 1 ≤ length of S ≤ 50
 * 0 ≤ A, B ≤ 10^18
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String S = in.nextLine();
		int[] amount = new int[10];
		for (int i = 0; i < S.length(); i++) {
			++amount[(S.charAt(i) - '0')];
		}

		// number #1
		StringBuilder buf1 = new StringBuilder();
		if (amount[0] > 0) {
			buf1.append('0');
			--amount[0];
		} else {
			for (int i = 1; i < 10; i++) {
				if (amount[i] > 0) {
					buf1.append((char) ('0' + i));
					--amount[i];
					break;
				}
			}
		}

		// number #2
		StringBuilder buf2 = new StringBuilder();
		for (int i = 1; i < 10; i++) {
			if (amount[i] > 0) {
				buf2.append((char) ('0' + i));
				--amount[i];
				break;
			}
		}
		for (int i = 0; i < 10; i++) {
			while(amount[i] > 0) {
				buf2.append((char) ('0' + i));
				--amount[i];
			}
		}

		long n1 = Long.parseLong(buf1.toString());
		long n2 = Long.parseLong(buf2.toString());
		if (n1 > n2) {
			long t = n2;
			n2 = n1;
			n1 = t;
			StringBuilder tmp = buf1;
			buf1 = buf2;
			buf2 = tmp;
		}

		if (n2 > 1000000000000000000L) {
			optimization(buf1, buf2);
		}

		System.err.println("A= " + buf1);
		System.err.println("B= " + buf2);

		System.out.print(buf1.toString());
		System.out.print(' ');
		System.out.println(buf2.toString());
	}

	private static void optimization(StringBuilder buf1, StringBuilder buf2) {

	}
}
