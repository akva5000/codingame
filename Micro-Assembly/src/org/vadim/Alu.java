package org.vadim;

public class Alu {
	private Processor processor;

	public Alu(Processor processor) {
		this.processor = processor;
	}

	public void execCmd(int[] cmd) {
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

	public static class Processor {
		int[] register = new int[4]; // a b c d
		Alu alu;
		int[][] commands;
		private int pc = 0;

		void setPc(int pc) {this.pc = pc;}
		int getPc() { return pc; }
	}
}
