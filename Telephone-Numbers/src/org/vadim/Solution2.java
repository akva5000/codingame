package org.vadim;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * Input:
 * Line 1: The number N of telephone numbers.
 * N following lines: Each line contains a phone number, with a maximum length L.
 * Telephone numbers consist of only the digits 0 to 9 included, without any spaces.
 * 
 * Output:
 * The number of elements (referencing a number) stored in the structure.
 * 
 * Constraints
 * 0 ≤ N ≤ 10000
 * 2 ≤ L ≤ 20
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		final Map<Integer, Map> root = new HashMap<>();

		final AtomicInteger maxLen = new AtomicInteger(0);
		for (int i = 0; i < N; i++) {
			char[] phone = in.next().toCharArray();
			Map<Integer, Map> map = root;
			for (int p = 0; p < phone.length; p++) {
				int key = phone[p] - '0';
				map = map.computeIfAbsent(key, k -> {
					maxLen.incrementAndGet();
					return new HashMap<>();
				});
			}
		}

		System.out.println(maxLen.get());
	}
}
