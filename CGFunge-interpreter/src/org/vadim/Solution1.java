package org.vadim;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1: An integer N indicating the number of lines in the program
 * Next N lines: One line per program input
 * 
 * Output:
 * The output of the properly interpreted program
 * 
 * Constraints
 * 1 ≤ N ≤ 10
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		final Cpu cpu = new Cpu();
		cpu.memory = new Memory();
		cpu.memory.grid = new char[N][];
		for (int i = 0; i < N; i++) cpu.memory.grid[i] = in.nextLine().toCharArray();
		cpu.run();
		System.out.println(cpu.out);
	}

	private static class Memory {
		char[][] grid;

		char get(int y, int x) {
			return x >= grid[y].length ? '\n' : grid[y][x];
		}
	}

	private static class Cpu {
		final ArrayDeque<Integer> stack = new ArrayDeque<>();
		final StringBuilder out = new StringBuilder();
		Memory memory;
		int nextY = 0;
		int nextX = 0;
		int direction = 1; // 0-up, 1-right, 2-down, 3-left
		boolean isStringMode = false;

		public void run() {
			while (!step());
		}

		@SuppressWarnings("boxing")
		private boolean step() {
			if (isStringMode) {
				switch (memory.get(nextY, nextX)) {
					case '"':
						isStringMode = !isStringMode;
						break;

					default:
						stack.push((int) memory.get(nextY, nextX));
						break;
				}

			} else {
				int v;
				switch (memory.get(nextY, nextX)) {
					case 'E':
						return true;

					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						stack.push((memory.get(nextY, nextX) - '0') & 0xFF);
						break;

					case '>':
						direction = 1;
						break;

					case '<':
						direction = 3;
						break;

					case '^':
						direction = 0;
						break;

					case 'v':
						direction = 2;
						break;

					case 'P': // Pop the top value stack
						stack.pop();
						break;

					case 'X': // Switch the order of the top two stack values stack
						Integer a = stack.pop();
						Integer b = stack.pop();
						stack.push(a);
						stack.push(b);
						break;

					case 'D': // Push a duplicate of the top value onto the stack
						stack.push(stack.peek());
						break;

					case '+':
						v = stack.pop();
						stack.push((v + stack.pop()) & 0xFF);
						break;

					case '-':
						v = stack.pop();
						stack.push((stack.pop() - v) & 0xFF);
						break;

					case '*':
						v = stack.pop();
						stack.push((v * stack.pop()) & 0xFF);
						break;

					case '"':
						isStringMode = !isStringMode;
						break;

					case '_': // Pop the top value from the stack. If it is 0, continue to the right. Otherwise, go left.
						direction = stack.pop() == 0 ? 1 : 3;
						break;

					case '|': // Pop the top value from the stack. If it is 0, continue down. Otherwise, go up.
						direction = stack.pop() == 0 ? 2 : 0;
						break;

					case 'I': // Pop the top integer from the stack and print it to stdout.
						out.append(stack.pop().toString());
						break;

					case 'C': // Pop the top integer from the stack and interpret it as an ASCII code, printing the corresponding
										// character to stdout.
						out.append((char) stack.pop().intValue());
						break;

					case 'S':
						skipMovePtr();
						break;

					default: // space or S
						break;
				}
			}

			movePtr();
			return false;
		}

		private void movePtr() {
			switch (direction) {
				case 0:
					--nextY;
					while (memory.get(nextY, nextX) == '\n') --nextY;
					break;

				case 1:
					++nextX;
					break;

				case 2:
					++nextY;
					while (memory.get(nextY, nextX) == '\n') ++nextY;
					break;

				default: // 3
					--nextX;
					break;
			}
		}
		
		private void skipMovePtr() {
			switch (direction) {
				case 0:
					--nextY;
					break;

				case 1:
					++nextX;
					break;

				case 2:
					++nextY;
					break;

				default: // 3
					--nextX;
					break;
			}
		}
	}
}
