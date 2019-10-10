package org.vadim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: Integer N, the amount of cages.
 * Line 2: Integer Y, the number of years.
 * Next N lines: Three space-separated integers S, H and A, the amounts of sick, healthy and alive porcupines in the cage respectively.
 * 
 * Output:
 * Y or fewer lines of integers of porcupines alive.
 * Line 1 is year 1, not year 0. Any sick porcupines die first.
 * 
 * Constraints
 * 0 ≤ N < 500
 * 0 < Y < 100
 * 0 ≤ S, H, A < 10,000,000
 * S + H is A
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	private static List<Cage> cages;
	private static int yearsPeriod;

	public static void main(String args[]) {
		final Scanner in = new Scanner(System.in);
		int constantAnimals = buildModel(in);

		for (int i = 0; i < yearsPeriod; i++) {
			int total = cages.parallelStream()
				.filter(cage -> cage.getTotal() > 0)
				.mapToInt(cage -> cage.evaluate())
				.sum();
			System.out.println(String.valueOf(total + constantAnimals));
			
			if (total == 0 && constantAnimals == 0) break;
			cages.removeIf(cage -> cage.getTotal() == 0);
		}
	}

	private static int buildModel(final Scanner in) {
		int cagesN = in.nextInt();
		cages = new ArrayList<>(cagesN);
		yearsPeriod = in.nextInt();
		int constantAnimals = 0;
		for (int i = 0; i < cagesN; i++) {
			int sick = in.nextInt();
			int health = in.nextInt();
			int A = in.nextInt();
			if (sick == 0) {
				constantAnimals += health;
			} else {
				cages.add(new Cage(sick, health));
			}
		}

		return constantAnimals;
	}

	private static class Cage {
		private int sick;
		private int health;

		public Cage(int sick, int health) {
			this.sick = sick;
			this.health = health;
		}

		public int getTotal() {
			return sick + health;
		}

		public int evaluate() {
			int s = health <= sick * 2 ? health : sick * 2;
			sick = s;
			health -= s;
			return sick + health;
		}
	}
}
