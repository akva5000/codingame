package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: An integer N indicating the number of times the message was transformed.
 * If N is positive you have to decode i.e. retrieve the original message.
 * If N is negative you have to encode i.e. transform the message.
 * 
 * Line 2: A string message to be decoded or encoded.
 * 
 * Output
 * One line: The original message (if N is positive) or the transformed message (if N is negative).
 * 
 * Constraints
 * -10 ≤ N ≤ 10
 * 0 < message length < 1024
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
//		System.err.println("" + N);
		if (in.hasNextLine()) {
			in.nextLine();
		}
		String MESSAGE = in.nextLine();
//		System.err.println(MESSAGE);
		if (N == 0) {
			System.out.println(MESSAGE);
			return;
		}

		final StringBuilder buf = new StringBuilder(MESSAGE);
		for (int i = 0, len = (N < 0 ? -N : N); i < len; i++) {
			if (N < 0) encode(buf);
			else decode(buf);
		}
		System.out.println(buf);
	}

	private static void encode(final StringBuilder buf) {
		final char[] tmpBuf = new char[buf.length()];
		int size1 = 1;
		int size2 = buf.length() - size1;

		int counter = 1;
		int sum = size1;
		while (size2 > 2 && size2 + sum == buf.length()) {
//			System.err.println("size-1=" + size1 + ", size-2=" + size2);
			buf.getChars(0, size1, tmpBuf, 0);
			buf.delete(0, size1);
			if (counter % 2 == 0) {
				buf.insert(size2, tmpBuf, 0, size1);
			} else {
				buf.append(tmpBuf, 0, size1);
			}

			++size1;
			size2 -= size1;
			sum += size1;
			++counter;

//			System.err.println("enc-round: " + buf.toString());
//			System.err.println();
		}

		if (size2 > 0 && size2 < 3) {
//			System.err.println("post; size-1=" + size1 + ", size-2=" + size2);
			buf.getChars(size1, size1 + size2, tmpBuf, 0);
			buf.delete(size1, size1 + size2);
			buf.append(tmpBuf, 0, size2);
		}
	}

	private static void decode(StringBuilder buf) {
		int sizeA = 0;
		int sizeB = buf.length();
		for (int i = 1; i < buf.length(); i++) {
			sizeB -= i;
//			System.err.println("step: size-a:" + sizeA + ", size-b:" + i);
			if (sizeB < 3) {
				sizeA = i;
				break;
			}
		}

//		System.err.println("final: size-a=" + sizeA + ", size-b=" + sizeB);
//		System.err.println();

		final char[] tmpBuf = new char[buf.length()];
		if (sizeB < 3) {
			if (sizeB > 0) {
				buf.getChars(buf.length() - sizeB, buf.length(), tmpBuf, 0);
				buf.delete(buf.length() - sizeB, buf.length());
				buf.insert(sizeA, tmpBuf, 0, sizeB);
//			System.err.println("pre-step: " + buf.toString());
			}

			sizeB += sizeA;
			--sizeA;
//			System.err.println("pre-step: size-a=" + sizeA + ", size-b=" + sizeB);
		}

		int counter = sizeA;
		for (int i = sizeA; i > 0; i--) {
//			System.err.println("size-a=" + i + ", size-b=" + sizeB);
			if (counter % 2 != 0) { // from the end
//				System.err.print("end - ");
				buf.getChars(buf.length() - i, buf.length(), tmpBuf, 0);
				buf.delete(buf.length() - i, buf.length());
				buf.insert(0, tmpBuf, 0, i);
			} else {
//				System.err.print("mid - ");
				buf.getChars(sizeB, sizeB + i, tmpBuf, 0);
				buf.delete(sizeB, sizeB + i);
				buf.insert(0, tmpBuf, 0, i);
			}
//			System.err.println("dec-step: " + buf.toString());
//			System.err.println();

			++counter;
			sizeB += i;
		}

	}
}
