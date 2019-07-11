package org.vadim;

import static org.junit.Assert.*;

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
	public void testMain1() {
		String GAME = "d5\n" + 
				"2\n" + 
				"0 c1\n" + 
				"1 e8\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
//		sysOut.println(bufOut.toString());
		assertEquals("Rd5-a5\n" + 
				"Rd5-b5\n" + 
				"Rd5-c5\n" + 
				"Rd5-d1\n" + 
				"Rd5-d2\n" + 
				"Rd5-d3\n" + 
				"Rd5-d4\n" + 
				"Rd5-d6\n" + 
				"Rd5-d7\n" + 
				"Rd5-d8\n" + 
				"Rd5-e5\n" + 
				"Rd5-f5\n" + 
				"Rd5-g5\n" + 
				"Rd5-h5\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() {
		String GAME = "d5\n" + 
				"3\n" +
				"0 g5\n" + 
				"0 d2\n" + 
				"1 d7\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("Rd5-a5\n" + 
				"Rd5-b5\n" + 
				"Rd5-c5\n" + 
				"Rd5-d1\n" + 
				"Rd5-d2\n" + 
				"Rd5-d3\n" + 
				"Rd5-d4\n" + 
				"Rd5-d6\n" + 
				"Rd5-d7\n" + 
				"Rd5-d8\n" + 
				"Rd5-e5\n" + 
				"Rd5-f5\n" + 
				"Rd5-g5\n" + 
				"Rd5-h5\n", bufOut.toString());
	}
}
