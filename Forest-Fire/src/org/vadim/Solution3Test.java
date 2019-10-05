package org.vadim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution3Test {
	@FunctionalInterface
	interface IService {
		void main(String[] arg);
	}

	private InputStream sysIn;
	private PrintStream sysOut;
	private IService service;

	@Before
	public void setUp() {
		sysIn = System.in;
		sysOut = System.out;
		service = arg -> {
			try {
				Solution3.main(arg);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new IllegalStateException("ERROR", ex);
			}
		};
	}

	@After
	public void tearDown() throws Exception {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}

	@Test
	public void testMain1() throws Exception {
		final AtomicInteger water = new AtomicInteger(2000);
		final int L = 3;
		final BitSet field = new BitSet(L * L);
		field.set(0);
		field.set(2 * L + 1);

		final PipedOutputStream pinOut = new PipedOutputStream();
		final PipedInputStream pinIn = new PipedInputStream(pinOut);

		final PipedOutputStream poutOut = new PipedOutputStream();
		final PipedInputStream poutIn = new PipedInputStream(poutOut);

		System.setIn(pinIn);
		System.setOut(new PrintStream(poutOut));

		final InStreamWrapper inWrapper = new InStreamWrapper(L, field, pinOut);
		final OutStreamWrapper outWrapper = new OutStreamWrapper(water, L, field, inWrapper, poutIn);
		inWrapper.header();
		inWrapper.writeField();
		outWrapper.start();

		service.main(new String[0]);
		
		outWrapper.join(10000L);
		if (outWrapper.isAlive()) {
			System.err.println("thread timeout");
			outWrapper.interrupt();
			outWrapper.join();
			throw new IllegalStateException("timeout");
		}
		System.err.println("thread over");

		assertTrue(water.get() >= 0);
		assertEquals(0, field.cardinality());
	}

	@Test
	public void testMain2() throws Exception {
		final AtomicInteger water = new AtomicInteger(2000);
		final int L = 5;
		final BitSet field = new BitSet(L * L);
		field.set(2 * L);
		field.set(3 * L);
		field.set(3 * L + 1);
		field.set(4 * L);
		field.set(4 * L + 1);

		final PipedOutputStream pinOut = new PipedOutputStream();
		final PipedInputStream pinIn = new PipedInputStream(pinOut);

		final PipedOutputStream poutOut = new PipedOutputStream();
		final PipedInputStream poutIn = new PipedInputStream(poutOut);

		System.setIn(pinIn);
		System.setOut(new PrintStream(poutOut));

		final InStreamWrapper inWrapper = new InStreamWrapper(L, field, pinOut);
		final OutStreamWrapper outWrapper = new OutStreamWrapper(water, L, field, inWrapper, poutIn);
		inWrapper.header();
		inWrapper.writeField();
		outWrapper.start();

		service.main(new String[0]);

		outWrapper.join(10000L);
		if (outWrapper.isAlive()) {
			System.err.println("thread timeout");
			outWrapper.interrupt();
			outWrapper.join();
			throw new IllegalStateException("timeout");
		}
		System.err.println("thread over");

		assertTrue(water.get() >= 0);
		assertEquals(0, field.cardinality());
	}

	@Test
	public void testMain3() throws Exception {
		final AtomicInteger water = new AtomicInteger(4000);
		final int L = 6;
		final BitSet field = new BitSet(L * L);
		field.set(5 * L);
		field.set(2 * L + 2);
		field.set(3 * L + 2);

		field.set(L + 3);
		field.set(2 * L + 3);
		field.set(3 * L + 3);

		field.set(L + 4);
		field.set(4 * L + 4);
		field.set(4 * L + 5);
		field.set(5 * L + 5);

		final PipedOutputStream pinOut = new PipedOutputStream();
		final PipedInputStream pinIn = new PipedInputStream(pinOut);

		final PipedOutputStream poutOut = new PipedOutputStream();
		final PipedInputStream poutIn = new PipedInputStream(poutOut);

		System.setIn(pinIn);
		System.setOut(new PrintStream(poutOut));

		final InStreamWrapper inWrapper = new InStreamWrapper(L, field, pinOut);
		final OutStreamWrapper outWrapper = new OutStreamWrapper(water, L, field, inWrapper, poutIn);
		inWrapper.header();
		inWrapper.writeField();

		outWrapper.start();
		service.main(new String[0]);

		outWrapper.join(10000L);
		if (outWrapper.isAlive()) {
			System.err.println("thread timeout");
			outWrapper.interrupt();
			outWrapper.join();
			throw new IllegalStateException("timeout");
		}
		System.err.println("thread over");

		assertTrue("water is overused", water.get() >= 0);
		assertEquals(0, field.cardinality());
	}

	private static class InStreamWrapper {
		private final int L;
		private final PipedOutputStream pout;
		private final BitSet field;

		public InStreamWrapper(int L, BitSet field, final PipedOutputStream pout) {
			this.L = L;
			this.field = field;
			this.pout = pout;
		}

		public void header() throws IOException {
			pout.write("6 4000\n".getBytes());
			pout.flush();
		}

		public void writeField() throws IOException {
			int N = field.cardinality();
			System.err.println("write field, " + N);
			pout.write((Integer.toString(N) + '\n').getBytes());
			pout.flush();
			if (N == 0) return;

			int idx = 0;
			for (int y = 0; y < L; y++) {
				for (int x = 0; x < L; x++) {
					if (!field.get(idx + x)) continue;
					pout.write((Integer.toString(x) + ' ' + Integer.toString(y) + '\n').getBytes());
				}
				idx += L;
			}
			pout.flush();
		}
	}

	private static class OutStreamWrapper extends Thread {
		private volatile boolean isStop = false;

		private final AtomicInteger water;
		private final int L;
		private final BitSet field;
		private final InStreamWrapper outWrapper;
		private final Scanner in;

		public OutStreamWrapper(final AtomicInteger water, int L, BitSet field, final InStreamWrapper outWrapper, PipedInputStream pin) {
			this.water = water;
			this.L = L;
			this.field = field;
			this.outWrapper = outWrapper;
			in = new Scanner(pin);
		}

		@Override
		public void interrupt() {
			if (!isStop) {
				isStop = true;
				super.interrupt();
			}
		}

		@Override
		public void run() {
			while (!isStop) {
				String line = in.nextLine();
				System.err.println("got in string, " + line);
				char op = line.charAt(0);
				int pos = line.indexOf(' ', 3);
				int x = Integer.parseInt(line.substring(2, pos));
				int y = Integer.parseInt(line.substring(pos + 1));

				field.clear(y * L + x);
				switch (op) {
					case 'J':
						water.addAndGet(-600);
						break;

					case 'H':
						midBox(x, y);
						water.addAndGet(-1200);
						break;

					default: // C
						midBox(x, y);
						bigBox(x, y);
						water.addAndGet(-2100);
						break;
				}

				try {
					outWrapper.writeField();
				} catch (IOException ex) {
					ex.printStackTrace();
					return;
				}

				if (water.get() <= 0 || field.cardinality() == 0) return;
			} // while
		}

		private void midBox(int x, int y) {
			int idx = y * L;
			field.clear(idx + x + 1);
			field.clear(idx + L + x);
			field.clear(idx + L + x + 1);
		}

		private void bigBox(int x, int y) {
			int idx = y * L;
			field.clear(idx + x + 2);
			idx += L;
			field.clear(idx + L + x + 2);
			idx += L;
			field.clear(idx + x);
			field.clear(idx + x + 1);
			field.clear(idx + x + 2);
		}

	}

	private static void verifyTestOutput(String text, final Collection<String> expected) {
		final Set<String> set = new HashSet<>(expected);
		for (final StringTokenizer tok = new StringTokenizer(text, "\n"); tok.hasMoreTokens();) {
			String line = tok.nextToken();
			assertTrue("not expected line: " + line, set.remove(line));
		}
		assertTrue("not all lines produced", set.isEmpty());
	}
}
