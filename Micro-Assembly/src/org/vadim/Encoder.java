package org.vadim;

public class Encoder {

	/**
	 * MOV regDst regSrc
	 * [00 00 regSrc regDst]
	 * 
	 * MOV regDst number
	 * [00 01 00 regDst] [number]
	 * 
	 * ADD regDst regSrc1 regSrc2
	 * [01 00 regSrc1 regDst] [regSrc2]
	 * 
	 * ADD regDst regSrc number
	 * [01 01 regSrc regDst] [number]
	 * 
	 * ADD regDst number regSrc
	 * [01 10 regSrc regDst] [number]
	 * 
	 * ADD regDst number1 number2
	 * [01 11 00 regDst] [number1] [number2]
	 * 
	 * SUB regDst regSrc1 regSrc2
	 * [10 00 regSrc1 regDst] [regSrc2]
	 * 
	 * SUB regDst regSrc number 
	 * [10 01 regSrc regDst] [number]
	 * 
	 * SUB regDst number regSrc
	 * [10 10 regSrc regDst] [number]
	 * 
	 * SUB regDst number1 number2
	 * [10 11 00 regDst] [number1] [number2]
	 * 
	 * JNE number regSrc1 regSrc2
	 * [11 00 regSrc1 regSrc2] [number]
	 * 
	 * JNE number1 regSrc number2
	 * [11 11 00 regSrc] [number2] [number1]
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

}
