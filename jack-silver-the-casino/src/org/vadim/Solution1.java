package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: An integer, ROUNDS, for the number of rounds the target is playing
 * Line 2: An integer, CASH, for the amount of cash the target starts with
 * Next ROUNDS lines: The target's PLAY for that round, consisting of either 2 or 3 space separated variables:
 *   1) an integer, BALL, which represents the roulette table result
 *   2) a string, CALL, which represents the call the target made
 *   3) (optional) an optional integer, NUMBER, which represents the selected number when the target's call is PLAIN
 * 
 * Output:
 * The amount of money, MONEY, the target has after ROUNDS of playing
 * 
 * Constraints
 * 1 ≤ ROUNDS ≤ 100
 * 50000 ≤ CASH ≤ 100000
 * CALL can be the text EVEN, ODD or PLAIN with an integer NUMBER
 * If NUMBER is set 0 ≤ NUMBER ≤ 36
 * 
 * 1 ≤ MONEY ≤ 1 000 000
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int ROUNDS = in.nextInt();
		int CASH = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		for (int i = 0; i < ROUNDS; i++) {
			int conMoney = (CASH / 4);
			if(CASH % 4 != 0) ++conMoney;

			String PLAY = in.nextLine();
			int p1 = PLAY.indexOf(' ');
			String realNumber = PLAY.substring(0, p1);

			switch (PLAY.charAt(p1 + 1)) {
				case 'E':
				case 'O':
					if(realNumber.length() == 1 && realNumber.charAt(0) == '0') {
						CASH -= conMoney;
						break;
					}
					
					char type = PLAY.charAt(p1 + 1);
					int lastDigitPos = realNumber.length() - 1;
					boolean isEven = realNumber.charAt(lastDigitPos) == '2'
							|| realNumber.charAt(lastDigitPos) == '4'
							|| realNumber.charAt(lastDigitPos) == '6'
							|| realNumber.charAt(lastDigitPos) == '8'
							|| (lastDigitPos > 0 && realNumber.charAt(lastDigitPos) == '0');
						CASH += ((isEven && type == 'E') || (!isEven && type == 'O'))
								? conMoney
								: -conMoney;
					break;

				default: // PLANT
					String wantNumber = PLAY.substring(p1 + 7);
					CASH += (wantNumber.compareTo(realNumber) == 0)
							? conMoney * 35
							: -conMoney;
					break;
			}
		}

		System.out.println(CASH);
	}
}
