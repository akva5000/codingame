package org.vadim;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution2Test {
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
	
//	@Test
	public void testMain1() {
		String GAME = "6\n" + 
				"3 2 2 2 1 5\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("-3\n", bufOut.toString());
	}
	
//	@Test
	public void testMain2() {
		String GAME = "6\n" + 
				"1 2 5 10 2 5\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("-8\n", bufOut.toString());
	}

//	@Test
	public void testMain5() {
		String GAME = "6\n" + 
				"3 2 4 2 1 5\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("-3\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() {
		String GAME = "6\n" + 
				"5 3 4 2 3 1\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("-4\n", bufOut.toString());
	}
	
}
