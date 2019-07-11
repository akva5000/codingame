package org.vadim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: The original number R of the sequence.
 * Line 2: The line L to display. The index of the first line is 1.
 * 
 * Output:
 * The line L of the sequence. Each element of the sequence is separated by a space.
 * 
 * Constraints
 * 0 < R < 100
 * 0 < L ≤ 25
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int R = in.nextInt();
		int L = in.nextInt();

		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		list1.add(R);

		for (int i = 2; i <= L; i++) {
			list2.clear();
			Iterator<Integer> it = list1.iterator();
			int num = it.next();
			int counter = 1;

			int pos = 1;
			while (it.hasNext()) {
				int value = it.next();
				if (value == num) {
					++counter;
				} else {
					list2.add(counter);
					list2.add(num);
					num = value;
					counter = 1;
				}
			} // while
			list2.add(counter);
			list2.add(num);

			// list swap
			List<Integer> tmp = list1;
			list1 = list2;
			list2 = tmp;
		}

		Iterator<Integer> it = list1.iterator();
		System.out.print(it.next());
		while (it.hasNext()) {
			System.out.print(' ');
			System.out.print(it.next());
		}
		System.out.println();
	}
}
