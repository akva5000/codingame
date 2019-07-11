package org.vadim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Input Line 1: N the number of adjacency relations. N next lines: an
 * adjancency relation between two people, expressed as X (space) Y, meaning
 * that X is adjacent to Y.
 * 
 * Output The minimal amount of steps required to completely propagate the
 * advertisement.
 * 
 * @author akva
 */
public class Solution5 {
	private static int[][] tree;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		{
			int n = in.nextInt(); // the number of adjacency relations
			int[] x = new int[n];
			int[] y = new int[n];
			for (int i = 0; i < n; i++) {
				x[i] = in.nextInt();
				y[i] = in.nextInt();
			}

			int min = Integer.MAX_VALUE;
			int max = 0;
			for (int i = 0; i < n; i++) {
				if (x[i] > max) max = x[i];
				if (y[i] > max) max = y[i];
				if (x[i] < min) min = x[i];
				if (y[i] < min) min = y[i];
			}

			int size = max - min + 1;
			tree = new int[size][];

			for (int i = 0; i < n; i++) {
				int[] arr = tree[x[i] - min];
				arr = (arr == null) ? new int[1] : Arrays.copyOf(arr, arr.length + 1);
				arr[arr.length - 1] = y[i] - min;
				tree[x[i] - min] = arr;

				arr = tree[y[i] - min];
				arr = (arr == null) ? new int[1] : Arrays.copyOf(arr, arr.length + 1);
				arr[arr.length - 1] = x[i] - min;
				tree[y[i] - min] = arr;
			}

			x = null;
			y = null;
		}

		int rc = calculate();
		System.out.println(rc);
	}

	private static int calculate() {
		final List<Integer> stack = new ArrayList<>(10);
		int[] path = new int[tree.length];
		int handled = 0;

		int pos = 0;
		int counter = 0;
		int[] vector;
		while (true) {
			// fill stack
			stack.clear();
			int endPos = -1;
			for (int x = 0; x < tree.length; x++) {
				if (tree[x] == null) continue;

				counter = 0;
				for (int i = 0; i < tree[x].length; i++) {
					if (tree[x][i] != -1) ++counter;
				}

				switch (counter) {
					case 0:
						endPos = x;
						break;

					case 1:
						stack.add(x);
						break;

					default:
						break;
				}
			}

			if (stack.isEmpty()) return path[endPos];
			if (stack.size() == 2 && handled == tree.length - 2) { // check for last 2 nodes
				return 1 + (path[stack.get(0)] > path[stack.get(1)] ? path[stack.get(0)] : path[stack.get(1)]);
			}

			// handle stack with terminal nodes
			for (int x : stack) {
				for (int i = 0; i < tree[x].length; i++) {
					if (tree[x][i] != -1) {
						pos = tree[x][i];
						break;
					}
				}

				// new terminal node
				tree[x] = null;
				counter = path[pos];
				if (counter < path[x] + 1) path[pos] = path[x] + 1;

				vector = tree[pos];
				for (int i = 0; i < vector.length; i++) {
					if (vector[i] == x) {
						vector[i] = -1;
						break;
					}
				}

				++handled;
			}
		} // while
	}
}
