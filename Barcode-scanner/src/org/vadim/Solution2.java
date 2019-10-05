package org.vadim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * <pre>
 * Input:
 * A string scanline of 95 bits representing a scanned EAN-13 barcode line of pixels (0-white, 1-black)
 * 
 * Output:
 * A single line of 13 decimal numbers without spaces representing decoded EAN-13 barcode or INVALID SCAN
 * in case any corruption of the input sequence detected.
 * 
 * Constraints
 * XXX
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {
	private static final Map<Integer, Integer> RCodes = new HashMap<>(10);
	private static final Map<Integer, Integer> LCodes = new HashMap<>(10);
	private static final Map<Integer, Integer> GCodes = new HashMap<>(10);
	private static final Map<String, Integer> PATTERN = new HashMap<>(10);

	static {
		RCodes.put(0b1110010, 0);
		RCodes.put(0b1100110, 1);
		RCodes.put(0b1101100, 2);
		RCodes.put(0b1000010, 3);
		RCodes.put(0b1011100, 4);
		RCodes.put(0b1001110, 5);
		RCodes.put(0b1010000, 6);
		RCodes.put(0b1000100, 7);
		RCodes.put(0b1001000, 8);
		RCodes.put(0b1110100, 9);

		LCodes.put(0b0001101, 0);
		LCodes.put(0b0011001, 1);
		LCodes.put(0b0010011, 2);
		LCodes.put(0b0111101, 3);
		LCodes.put(0b0100011, 4);
		LCodes.put(0b0110001, 5);
		LCodes.put(0b0101111, 6);
		LCodes.put(0b0111011, 7);
		LCodes.put(0b0110111, 8);
		LCodes.put(0b0001011, 9);

		GCodes.put(0b0100111, 0);
		GCodes.put(0b0110011, 1);
		GCodes.put(0b0011011, 2);
		GCodes.put(0b0100001, 3);
		GCodes.put(0b0011101, 4);
		GCodes.put(0b0111001, 5);
		GCodes.put(0b0000101, 6);
		GCodes.put(0b0010001, 7);
		GCodes.put(0b0001001, 8);
		GCodes.put(0b0010111, 9);

		PATTERN.put("LLLLLL", 0);
		PATTERN.put("LLGLGG", 1);
		PATTERN.put("LLGGLG", 2);
		PATTERN.put("LLGGGL", 3);
		PATTERN.put("LGLLGG", 4);
		PATTERN.put("LGGLLG", 5);
		PATTERN.put("LGGGLL", 6);
		PATTERN.put("LGLGLG", 7);
		PATTERN.put("LGLGGL", 8);
		PATTERN.put("LGGLGL", 9);
	}

	public static void main(String args[]) throws Exception {
		Scanner in = new Scanner(System.in);
		String scanline = in.nextLine();

		if (scanline.length() != 95 || scanline.substring(0, 3).compareTo("101") != 0
			|| scanline.substring(92).compareTo("101") != 0
			|| scanline.substring(45, 50).compareTo("01010") != 0) {
			System.out.println("INVALID SCAN");
			return;
		}

		final Integer[] code = new Integer[12];
		final char[] prefix = new char[6];
		Integer prefixDigit = parse(scanline, code, prefix);
		if (prefixDigit == null || !checkSum(prefixDigit, code)) {
			char[] array = new char[scanline.length()];
			int pos = 0;
			for (int i = scanline.length() - 1; i >= 0; i--) {
				array[pos++] = scanline.charAt(i);
			}
			scanline = String.valueOf(array);
			prefixDigit = parse(scanline, code, prefix);
			if (prefixDigit == null || !checkSum(prefixDigit, code)) {
				System.out.println("INVALID SCAN");
				return;
			}
		}

		System.out.print(prefixDigit.toString());
		for (Integer d : code) {
			System.out.print(d.toString());
		}
		System.out.println();
	}

	private static Integer parse(String scanline, final Integer[] code, final char[] prefix) throws Exception {
		final List<Callable<Boolean>> tasks = new ArrayList<>(12);
		int codePos = 0;
		for (int pos = 3; pos < 45; pos += 7) {
			tasks.add(new ZoneABHandler(scanline, pos, code, codePos++, prefix));
		}

		for (int pos = 50; pos < 92; pos += 7) {
			tasks.add(new ZoneCHandler(scanline, pos, code, codePos++));
		}

		final List<Future<Boolean>> list = ForkJoinPool.commonPool().invokeAll(tasks);
		for (Future<Boolean> f : list) {
			if (!f.get()) return null;
		}

		return PATTERN.get(String.valueOf(prefix));
	}

	private static int get7BitsDigit(String bytes, int pos) {
		int digit = 0;
		for (int i = pos; i < pos + 7; i++) {
			digit <<= 1;
			if (bytes.charAt(i) != '0') digit |= 1;
		} // for i
		return digit;
	}

	private static boolean checkSum(int prefix, final Integer[] code) {
		int sum = prefix;
		for (int i = 0; i <code.length; i++) {
			sum += (i & 0x01) == 1 ? code[i] : code[i] * 3;
		}
		return sum % 10 == 0;
	}

	private static class ZoneCHandler implements Callable<Boolean> {
		private final String line;
		private final int pos;
		private final Integer[] code;
		private final int codePos;

		public ZoneCHandler(String line, int pos, final Integer[] code, int codePos) {
			this.line = line;
			this.pos = pos;
			this.code = code;
			this.codePos = codePos;
		}

		@Override
		public Boolean call() throws Exception {
			Integer digit = RCodes.get(get7BitsDigit(line, pos));
			if (digit == null) return Boolean.FALSE;
			code[codePos] = digit;
			return Boolean.TRUE;
		}
	}

	private static class ZoneABHandler implements Callable<Boolean> {
		private final String line;
		private final int pos;
		private final Integer[] code;
		private final int codePos;
		private final char[] prefix;

		public ZoneABHandler(String line, int pos, final Integer[] code, final int codePos, final char[] prefix) {
			this.line = line;
			this.pos = pos;
			this.code = code;
			this.codePos = codePos;
			this.prefix = prefix;
		}

		@Override
		public Boolean call() throws Exception {
			Integer d = get7BitsDigit(line, pos);

			Integer t = LCodes.get(d);
			if (t == null) {
				t = GCodes.get(d);
				prefix[codePos] = 'G';
			} else {
				prefix[codePos] = 'L';
			}

			code[codePos] = t;
			return Boolean.valueOf(t != null);
		}
	}
}
