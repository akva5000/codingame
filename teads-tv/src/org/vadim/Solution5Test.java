package org.vadim;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution5Test {
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
	public void testMain1() {
		String GAME = "7\n" + 
				"1 2\n" + 
				"2 3\n" + 
				"3 4\n" + 
				"3 7\n" + 
				"4 5\n" + 
				"4 6\n" + 
				"7 8\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution5.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("2\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() {
		String GAME = "7\n" + 
				"1 2\n" + 
				"1 3\n" + 
				"3 4\n" + 
				"3 5\n" + 
				"2 6\n" + 
				"6 7\n" + 
				"6 8\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution5.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("3\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() {
		String GAME = "13\n" + 
				"	6 8\n" + 
				"	15 1\n" + 
				"	1 14\n" + 
				"	0 3\n" + 
				"	15 13\n" + 
				"	9 15\n" + 
				"	2 5\n" + 
				"	14 10\n" + 
				"	4 9\n" + 
				"	7 2\n" + 
				"	8 7\n" + 
				"	3 4\n" + 
				"	1 6\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution5.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("5\n", bufOut.toString());
	}
}
