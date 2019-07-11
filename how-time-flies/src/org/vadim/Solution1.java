package org.vadim;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: A date BEGIN in dd.mm.yyyy format.
 * Line 2: A date END in dd.mm.yyyy format.
 * 
 * Output:
 * Line 1: Formatted string presenting date difference as "YY year[s], MM month[s], total NN days"
 * 
 * Constraints
 * BEGIN ≤ END
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) throws Exception {
		Scanner in = new Scanner(System.in);
		LocalDate ld1 = parsedate(in.next());
		LocalDate ld2 = parsedate(in.next());
		ld2.plusDays(1);

		Period period = Period.between(ld1, ld2);
		int years = period.getYears();
		if (years > 0) {
			System.out.print(years);
			System.out.print(" year");
			if (years > 1) System.out.print("s");
			System.out.print(", ");
		}

		long months = period.getMonths();
		if (months > 0) {
			System.out.print(months);
			System.out.print(" month");
			if (months > 1) System.out.print("s");
			System.out.print(", ");
		}

		long days = ChronoUnit.DAYS.between(ld1, ld2);
		System.out.print("total ");
		System.out.print(days);
		System.out.print(" days\n");
	}

	private static LocalDate parsedate(String BEGIN) {
		return LocalDate.of( 
				Integer.parseInt(BEGIN.substring(6)),
				Integer.parseInt(BEGIN.substring(3, 5)), 
				Integer.parseInt(BEGIN.substring(0, 2)));
	}
}
