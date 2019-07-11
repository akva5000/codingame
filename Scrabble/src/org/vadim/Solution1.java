package org.vadim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: the number N of words in the dictionary
 * N following lines: the words in the dictionary. One word per line.
 * Last line: the 7 letters available.
 * 
 * Output:
 * The word that scores the most points using the available letters (1 to 7 letters).
 * The word must belong to the dictionary. Each letter must be used at most once in the solution.
 * There is always a solution.
 * 
 * Constraints
 * 0 < N < 100000
 * Words in the dictionary have a maximum length of 30 characters.
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	private static BitSet set = new BitSet(7);

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		// handle dictionary
		final List<String> words = new ArrayList<>(N);
		final Map<String, Integer> dic = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String W = in.nextLine();
			System.err.println("W= " + W);
			if (W.length() > 7) continue;
			words.add(W);
			dic.put(W, calcScore(W));
		}

		// handle letters
		char[] list = in.nextLine().toCharArray();
		Arrays.sort(list);

		int maxScore = 0;
		String value = null;
		for (String word : words) {
			if (!isMatch(word, list)) continue;
			if (dic.get(word) <= maxScore) continue;
			maxScore = dic.get(word);
			value = word;
		}

		System.out.println(value);
	}

	private static boolean isMatch(String word, char[] list) {
		set.clear();
		char[] wordArr = word.toCharArray();
		Arrays.sort(wordArr);
		int pos = 0;
		for (char ch : wordArr) {
			boolean isFound = false;
			while (pos < list.length) {
				if (ch < list[pos]) break;
				if (list[pos] == ch && !set.get(pos)) {
					set.set(pos++);
					isFound = true;
					break;
				}
				++pos;
			} // while

			if (!isFound) return false;
		}

		return true;
	}

	private static int calcScore(String word) {
		int score = word.length();
		for (char ch : word.toCharArray()) {
			switch (ch) {
				case 'f':
				case 'h':
				case 'v':
				case 'w':
				case 'y':
					score += 3;
					break;

				case 'd':
				case 'g':
					++score;
					break;

				case 'b':
				case 'c':
				case 'm':
				case 'p':
					score += 2;
					break;

				case 'q':
				case 'z':
					score += 9;
					break;

				case 'k':
					score += 4;
					break;

				case 'j':
				case 'x':
					score += 7;
					break;

				default:
					break;
			}
		}
		return score;
	}
}
