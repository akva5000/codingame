package org.vadim;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution1Test {
	private InputStream sysIn;
	private PrintStream sysOut;

	@Before
	public void setUp() throws Exception {
		sysIn = System.in;
		sysOut = System.out;
	}

	@After
	public void tearDown() throws Exception {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}

	@Test
	public void testMain1() throws Exception {
		String GAME = "3\n" + "5 too high\n" + "1 too high\n" + "2 right on\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("Alice cheated in round 2\n", bufOut.toString());
	}

	@Test
	public void testMain2() throws Exception {
		String GAME = "8\n"
				+ "50 too low\n"
				+ "55 too low\n"
				+ "45 too low\n"
				+ "44 too low\n"
				+ "62 too high\n"
				+ "80 too high\n"
				+ "51 too low\n"
				+ "61 right on\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("No evidence of cheating\n", bufOut.toString());
	}

	@Test
	public void testMain3() throws Exception {
		String GAME = "3\n"
				+ "33 too low\n"
				+ "21 too low\n"
				+ "32 too high\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("Alice cheated in round 3\n", bufOut.toString());
	}

}
