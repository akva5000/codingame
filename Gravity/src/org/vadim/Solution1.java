package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1 : Two integers: The map width and height.
 * height next lines : width characters: (. or #).
 * 
 * Output:
 * height lines : width characters where the # are at the bottom of the grid.
 * 
 * Constraints
 * 0 < width < 100
 * 0 < height < 10
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int width = in.nextInt();
		int height = in.nextInt();
		char[][] desk = new char[height][];
		for (int i = 0; i < height; i++) {
			desk[i] = in.next().toCharArray();
		}

		for (int i = height - 1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				if (desk[i][j] != '#') continue;
				int p = i + 1;
				while (p < height && desk[p][j] == '.') {
					desk[p - 1][j] = '.';
					desk[p++][j] = '#';
				}
			}
		}

		for (int y = 0; y < height; y++) {
			System.out.println(desk[y]);
		}
	}
}
