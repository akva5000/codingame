package org.vadim;

import java.util.Scanner;

public class Solution {
	private static int[][] rotors = new int[3][];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String operation = in.nextLine();
		int pseudoRandomNumber = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}
		for (int i = 0; i < 3; i++) {
			String rotor = in.nextLine();
			rotors[i] = new int[rotor.length()];
			for (int pos = 0; pos < rotor.length(); pos++) {
				rotors[i][pos] = rotor.charAt(pos) - 'A';
			}
		}

		String message = in.nextLine();
		int[] data = new int[message.length()];
		for (int pos = 0; pos < data.length; pos++) {
			data[pos] = message.charAt(pos) - 'A';
		}

		if ("ENCODE".compareTo(operation) == 0) {
			encode(data, pseudoRandomNumber);
		} else {
			prepareForDecoding();
			decode(data, pseudoRandomNumber);
		}

		char[] out = new char[data.length];
		for (int pos = 0; pos < data.length; pos++) {
			out[pos] = (char) ('A' + data[pos]);
		}
		System.out.println(String.valueOf(out));
	}

	private static void prepareForDecoding() {
		int[] tmp = new int[26];
		for (int i = 0; i < 3; i++) {
			for (int p = 0; p < 26; p++) {
				tmp[rotors[i][p]] = p;
			}
			System.arraycopy(tmp, 0, rotors[i], 0, 26);
		}
	}

	private static void encode(int[] text, int shift) {
		int s = shift;
		for (int i = 0; i < text.length; i++) {
			text[i] += s;
			while (text[i] > 25) {
				text[i] = text[i] - 26;
			}
			++s;
		}

		for (int rotor = 0; rotor < 3; rotor++) {
			for (int i = 0; i < text.length; i++) {
				text[i] = rotors[rotor][text[i]];
			}
		}
	}

	private static void decode(int[] text, int shift) {
		for (int rotor = 2; rotor > -1; rotor--) {
			for (int i = 0; i < text.length; i++) {
				text[i] = rotors[rotor][text[i]];
			}
		}

		int s = shift;
		for (int i = 0; i < text.length; i++) {
			text[i] -= s;
			while (text[i] < 0) {
				text[i] = text[i] + 26;
			}
			++s;
		}
	}
}
