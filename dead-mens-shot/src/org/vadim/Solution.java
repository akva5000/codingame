package org.vadim;

import java.util.Scanner;

/**
 * Input Line 1: An integer N for the number of corners. Next N lines: Two
 * space-separated integers x and y for the coordinates of a corner. - The
 * corners are listed in a counterclockwise manner. - The target is formed by
 * connecting the corners together with lines and connecting the last corner
 * with the first one. Line N+1: An integer M for the number of shots. Next M
 * lines: Two space-separated integers x and y for the coordinates of each shot.
 * 
 * Output M lines with either "hit" or "miss" depending on whether the shot hit
 * the target or not.
 * 
 * @author akva
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); // N corners
		final int[] xs = new int[N];
		final int[] ys = new int[N];

		for (int i = 0; i < N; i++) {
			xs[i] = in.nextInt();
			ys[i] = in.nextInt();
		}

		int M = in.nextInt();
		for (int i = 0; i < M; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			if (checkHit(xs, ys, x, y) ) {
				System.out.println("hit");
			} else {
				System.out.println("miss");
			}
		}
	}
	
	private static boolean checkHit(final int[] xs, final int[] ys, int x, int y) {
		int N = xs.length;
		int flag=0;
		for (int n = 0; n < N; n++) {
			flag = 0;
			int i1 = n < N - 1 ? n + 1 : 0;
			while (flag == 0) {
				int i2 = i1 + 1;
				if (i2 >= N) i2 = 0;
				if (i2 == (n < N - 1 ? n + 1 : 0)) return false;
				
				if (Math.abs(xs[i1] * (ys[i2] - ys[n]) + xs[i2] * (ys[n] - ys[i1]) + xs[n] * (ys[i1] - ys[i2])) == Math
						.abs(xs[i1] * (ys[i2] - y) + xs[i2] * (y - ys[i1]) + x * (ys[i1] - ys[i2]))
						+ Math.abs(xs[n] * (ys[i2] - y) + xs[i2] * (y - ys[n]) + x * (ys[n] - ys[i2]))
						+ Math.abs(xs[i1] * (ys[n] - y) + xs[n] * (y - ys[i1]) + x * (ys[i1] - ys[n]))) {
					flag = 1;
					break;
				}
				i1 = i1 + 1;
				if (i1 >= N) i1 = 0;
			} // while
			if (flag == 0) return false;
		} // for
		
		return true;
	}

	private static int checkHit1(final int[] xs, final int[] ys, int x, int y) {
		int N = xs.length;
		int flag = 0;
		for (int n = 0; n < N; n++) {
			flag = 0;
			int i1 = n < N - 1 ? n + 1 : 0;
			while (flag == 0) {
				int i2 = i1 + 1;
				if (i2 >= N) i2 = 0;
				if (i2 == (n < N - 1 ? n + 1 : 0)) break;
				
				if (Math.abs(xs[i1] * (ys[i2] - ys[n]) + xs[i2] * (ys[n] - ys[i1]) + xs[n] * (ys[i1] - ys[i2])) == Math
						.abs(xs[i1] * (ys[i2] - y) + xs[i2] * (y - ys[i1]) + x * (ys[i1] - ys[i2]))
						+ Math.abs(xs[n] * (ys[i2] - y) + xs[i2] * (y - ys[n]) + x * (ys[n] - ys[i2]))
						+ Math.abs(xs[i1] * (ys[n] - y) + xs[n] * (y - ys[i1]) + x * (ys[i1] - ys[n]))) {
					flag = 1;
					break;
				}
				i1 = i1 + 1;
				if (i1 >= N) i1 = 0;
			}
			if (flag == 0) break;
		}
		return flag;
	}
}
