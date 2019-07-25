package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input:
 * Line 1 contains the blank-separated values for the registers a, b, c, d
 * Line 2 contains the number n of the following instruction lines
 * n lines containing assembly instructions
 * 
 * Output:
 * Line with the four blank-separated register values of a, b, c, d
 * 
 * Constraints
 * 0 < n < 16
 * -2^15 ≤ a, b, c, d < 2^15
 * Overflow and underflow behavior is unspecified (and not tested).
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		Processor processor = new Processor();
		processor.alu = new Alu();
		processor.alu.processor = processor;

		processor.register[0] = in.nextInt();
		processor.register[1] = in.nextInt();
		processor.register[2] = in.nextInt();
		processor.register[3] = in.nextInt();

		int n = in.nextInt();
		if (in.hasNextLine()) in.nextLine();
		processor.commands = new int[n][];

		for (int i = 0; i < n; i++) {
			processor.commands[i] = encode(in.nextLine());
		}

		processor.run();
		System.out.print("" + processor.register[0] + ' ');
		System.out.print("" + processor.register[1] + ' ');
		System.out.print("" + processor.register[2] + ' ');
		System.out.println("" + processor.register[3]);
	}

	/**
	 * <pre>
	 * MOV regDst regSrc [00 00 regSrc regDst]
	 * MOV regDst number [00 01 00 regDst] [number]
	 * ADD regDst regSrc1 regSrc2 [01 00 regSrc1 regDst] [regSrc2]
	 * ADD regDst regSrc number [01 01 regSrc regDst] [number]
	 * ADD regDst number regSrc [01 10 regSrc regDst] [number]
	 * ADD regDst number1 number2 [01 11 00 regDst] [number1] [number2]
	 * SUB regDst regSrc1 regSrc2 [10 00 regSrc1 regDst] [regSrc2]
	 * SUB regDst regSrc number [10 01 regSrc regDst] [number]
	 * SUB regDst number regSrc [10 10 regSrc regDst] [number]
	 * SUB regDst number1 number2 [10 11 00 regDst] [number1] [number2]
	 * JNE number regSrc1 regSrc2 [11 00 regSrc1 regSrc2] [number]
	 * JNE number1 regSrc number2 [11 11 00 regSrc] [number2] [number1]
	 * </pre>
	 */
	public static int[] encode(String line) {
		int[] cmd;
		char ch;

		int dstReg;
		int value1;
		int value2;
		boolean isReg1;
		boolean isReg2;

		switch (line.charAt(0)) {
			case 'M':
				dstReg = line.charAt(4) - 'a';
				ch = line.charAt(6);
				if (ch >= 'a' && ch <= 'd') { // MOV reg reg
					// [00 00 regSrc regDst]
					cmd = new int[] { (((ch - 'a') & 0B011) << 2) | (dstReg & 0B011) };
				} else { // MOV number reg
					// [00 01 00 regDst] [number]
					cmd = new int[] { 0B00010000 | (dstReg & 0B011), Integer.parseInt(line.substring(6)) };
				}
				break;

			case 'A':
			case 'S':
				dstReg = line.charAt(4) - 'a'; // op 1

				ch = line.charAt(6); // op 2
				if (ch >= 'a' && ch <= 'd') {
					value1 = ch - 'a';
					isReg1 = true;

					ch = line.charAt(8); // op 3
					if (ch >= 'a' && ch <= 'd') {
						value2 = ch - 'a';
						isReg2 = true;
					} else {
						value2 = Integer.parseInt(line.substring(8));
						isReg2 = false;
					}

				} else { // op 2 is number
					int pos = line.indexOf(' ', 7);
					value1 = Integer.parseInt(line.substring(6, pos));
					isReg1 = false;
					++pos;

					ch = line.charAt(pos); // op 3
					if (ch >= 'a' && ch <= 'd') {
						value2 = ch - 'a';
						isReg2 = true;
					} else {
						value2 = Integer.parseInt(line.substring(pos));
						isReg2 = false;
					}
				}

				if (isReg1 && isReg2) {
					cmd = new int[2];
					cmd[0] |= ((value1 & 0B011) << 2) | (dstReg & 0B011);
					cmd[1] = value2;

				} else if (!isReg1 && !isReg2) {
					cmd = new int[] { 0B00110000, value1, value2 };
					cmd[0] |= (dstReg & 0B011);

				} else { // reg and number
					cmd = new int[2];
					cmd[1] = isReg1 ? value2 : value1;
					int reg = isReg1 ? value1 : value2;
					cmd[0] |= ((reg & 0B011) << 2) | (dstReg & 0B011);
					cmd[0] |= (isReg1) ? 0B00010000 : 0B00100000; // operands
				}

				cmd[0] |= (line.charAt(0) == 'A') ? 0B01000000 : 0B10000000; // cmd
				break;

			case 'J':
				int pos = line.indexOf(' ', 4);
				int pc = Integer.parseInt(line.substring(4, pos));
				++pos;

				isReg1 = true;
				value1 = line.charAt(pos) - 'a';
				pos += 2;

				ch = line.charAt(pos);
				if (ch >= 'a' && ch <= 'd') {
					isReg2 = true;
					value2 = ch - 'a';
				} else {
					isReg2 = false;
					value2 = Integer.parseInt(line.substring(pos));
				}

				if (isReg2) {
					cmd = new int[] { 0B11000000, pc };
					cmd[0] |= ((value1 & 0B011) << 2) | (value2 & 0B011);
				} else {
					cmd = new int[] { 0B11110000, value2, pc };
					cmd[0] |= value1 & 0B011;
				}
				break;

			default:
				cmd = new int[0];
				break;
		}

		return cmd;
	}

	private static class Processor {
		int[] register = new int[4]; // a b c d
		Alu alu;
		int[][] commands;
		private int pc = 0;

		void setPc(int pc) {
			this.pc = pc;
		}

		void run() {
			while (pc < commands.length) {
				int oldPc = pc++;
				alu.execCmd(commands[oldPc]);
			}
		}
	}

	private static class Alu {
		Processor processor;

		void execCmd(int[] cmd) {
			int v = cmd[0];
			int dstReg = v & 0B00000011;

			v >>= 2;
			int srcReg1 = v & 0B00000011;

			v >>= 2;
			byte operans = (byte) (v & 0B00000011);

			v >>= 2;
			byte op = (byte) (v & 0B00000011);

			if (op == 0B00000000) { // MOV
				processor.register[dstReg] = (operans == 0) ? processor.register[srcReg1] : cmd[1];

			} else if (op == 0B00000011) { // JNE
				if (operans == 0) { // reg reg
					if (processor.register[dstReg] != processor.register[srcReg1]) {
						processor.setPc(cmd[1]);
					}
				} else if (processor.register[dstReg] != cmd[1]) { // reg number
					processor.setPc(cmd[2]);
				}

			} else { // ADD SUB
				int value1;
				int value2;

				switch (operans) {
					case 0: // reg reg
						value1 = processor.register[srcReg1];
						value2 = processor.register[cmd[1]];
						break;

					case 1: // reg number
						value1 = processor.register[srcReg1];
						value2 = cmd[1];
						break;

					case 2: // number reg
						value1 = cmd[1];
						value2 = processor.register[srcReg1];
						break;

					default:
						value1 = cmd[1];
						value2 = cmd[2];
						break;
				}

				if (op == 0B01) { // ADD
					value1 += value2;
				} else { // SUB
					value1 -= value2;
				}

				processor.register[dstReg] = value1;
			}
		}
	}

	private static abstract class ICmd {
		abstract void execute(Processor processor);
	}

	private static class MoveOp extends ICmd {
		private int dstReg;
		private int value;
		private boolean isReg;

		@Override
		void execute(Processor processor) {
			processor.register[dstReg] = isReg ? processor.register[value] : value;
		}

		static ICmd build(int value, boolean isReg, int dstReg) {
			MoveOp op = new MoveOp();
			op.dstReg = dstReg;
			op.value = value;
			op.isReg = isReg;
			return op;
		}
	}

	private static class AddOp extends ICmd {
		private int dstReg;
		private int value1;
		private int value2;
		private boolean isReg1;
		private boolean isReg2;

		@Override
		void execute(Processor processor) {
			int v1 = isReg1 ? processor.register[value1] : value1;
			int v2 = isReg2 ? processor.register[value2] : value2;
			processor.register[dstReg] = v1 + v2;
		}

		static ICmd build(int value1, boolean isReg1, int value2, boolean isReg2, int dstReg) {
			AddOp op = new AddOp();
			op.dstReg = dstReg;
			op.value1 = value1;
			op.value2 = value2;
			op.isReg1 = isReg1;
			op.isReg2 = isReg2;
			return op;
		}
	}

	private static class SubOp extends ICmd {
		private int dstReg;
		private int value1;
		private int value2;
		private boolean isReg1;
		private boolean isReg2;

		@Override
		void execute(Processor processor) {
			int v1 = isReg1 ? processor.register[value1] : value1;
			int v2 = isReg2 ? processor.register[value2] : value2;
			processor.register[dstReg] = v2 - v1;
		}

		static ICmd build(int value1, boolean isReg1, int value2, boolean isReg2, int dstReg) {
			SubOp op = new SubOp();
			op.dstReg = dstReg;
			op.value1 = value1;
			op.value2 = value2;
			op.isReg1 = isReg1;
			op.isReg2 = isReg2;
			return op;
		}
	}

	private static class JneOp extends ICmd {
		private int pc;
		private int reg;
		private int value;
		private boolean isReg;

		@Override
		void execute(Processor processor) {
			int v = isReg ? processor.register[value] : value;
			if (v != processor.register[reg]) processor.setPc(pc);
		}

		static ICmd build(int reg, int value, boolean isReg, int pc) {
			JneOp op = new JneOp();
			op.pc = pc;
			op.reg = reg;
			op.value = value;
			op.isReg = isReg;
			return op;
		}
	}
}
