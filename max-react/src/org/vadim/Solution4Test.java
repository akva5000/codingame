package org.vadim;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution4Test {
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
		String GAME = "2 2\n" + 
				"5 -9\n" + 
				"6 9\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution4.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("15\n", bufOut.toString());
	}
	
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "3 3\n" + 
				"-5 -8 -2\n" + 
				"-1 -1 -7\n" + 
				"-9 -2 -3\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution4.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("-1\n", bufOut.toString());
	}
}
