package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1 An integer R for the number of rounds in the transcript
 * Next R lines: Each line is a conversation between Bob and Alice, to form one round in the game.
 * 
 * The conversation lines format is given by this example:
 * 50 too low => Bob says "50", followed by Alice saying "too low".
 * 
 * Output:
 * If there is no evidence showing Alice was dishonest, write No evidence of cheating.
 * 
 * As soon as you can prove Alice was cheating, write Alice cheated in round X where X is the first round number indicating Alice did cheat.
 * 
 * Round number counts from 1, to 2, to 3, and so on.
 * 
 * Constraints
 * R ≤ 50
 * 1 <= X <= 100
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int R = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		int max = 100;
		int min = 1;
		Integer right = null;
		boolean isCheeting = false;
		for (int i = 0; i < R; i++) {
			String line = in.nextLine();
			if (isCheeting) continue;
			int pos = line.indexOf(" ");
			int x = Integer.parseInt(line.substring(0, pos));

			++pos;
			if (line.charAt(pos) == 't') { // too high/low
				pos += 4;
				if (line.charAt(pos) == 'l') { // too low
					if (x < 1 || x + 1 > 100 || x + 1 > max || (right != null && x >= right.intValue())) {
						System.out.println("Alice cheated in round " + (i + 1));
						isCheeting = true;
					} else {
						if (x + 1 > min) min = x + 1;
					}
				} else { // too high
					if (x - 1 < 1 || x > 100 || x - 1 < min || (right != null && x <= right.intValue())) {
						System.out.println("Alice cheated in round " + (i + 1));
						isCheeting = true;
					} else {
						if(x -1 < max) max = x - 1;
					}
				}
			} else { // right on
				if ((right != null && right.intValue() != x) || x < min || x > max) {
					System.out.println("Alice cheated in round " + (i + 1));
					isCheeting = true;
				}
			}
		}

		if (!isCheeting) System.out.println("No evidence of cheating");
	}
}
