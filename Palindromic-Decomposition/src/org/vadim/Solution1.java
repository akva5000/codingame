package org.vadim;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.StreamSupport;

/**
 * <pre>
 * Input:
 * A string S.
 * 
 * Output:
 * The number of palindromic decompositions of S.
 * 
 * Constraints
 * 1 ≤ length of S ≤ 4000.
 * S is consisting of lowercase English letters.
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		char[] S = in.nextLine().toCharArray();
		if(S.length == 1) {
			System.out.println("3");
			return;
		}
		
		int[] d1 = palN2(S);
		pal2(S, d1);
		int sum = StreamSupport.intStream(Arrays.spliterator(d1), true)
				.filter(n -> n > 2)
				.map(n -> 3)
				.sum();

		System.out.println(String.valueOf(sum));
	}

	private static int[] palN2(char[] str) {
		int[] d1 = new int[str.length];
		int l = 0, r = -1;
		for (int i = 0; i < str.length; i++) {
			int k = (i > r ? 0 : Math.min(d1[l + r - i], r - i)) + 1;
			while (i + k < str.length && i - k >= 0 && str[i + k] == str[i - k]) ++k;
			d1[i] = k--;
			if (i + k > r) {
				l = i - k;
				r = i + k;
			}
		}

		return d1;
	}

	private static void pal2(char[] str, int[] d1) {
		int l = 0, r = -1;
		for (int i = 0; i < str.length; i++) {
			int k = (i > r ? 0 : Math.min(d1[l + r - i + 1], r - i + 1)) + 1;
			while (i + k - 1 < str.length && i - k >= 0 && str[i + k - 1] == str[i - k]) ++k;
			d1[i] += k--;
			if (i + k - 1 > r) {
				l = i - k;
				r = i + k - 1;
			}
		}
	}
}
