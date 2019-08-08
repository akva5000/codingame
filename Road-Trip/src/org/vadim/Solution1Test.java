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
	@FunctionalInterface
	interface IService {
		void main(String[] arg);
	}

	private InputStream sysIn;
	private PrintStream sysOut;
	private IService service;
	
	@Before
	public void setUp() throws Exception {
		sysIn = System.in;
		sysOut = System.out;
		service = arg -> Solution1.main(arg);
	}

	@After
	public void tearDown() throws Exception {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}
	
	@Test
	public void testMain1() throws Exception {
		String GAME = "4 10 5\n" + 
				"9 4\n" + 
				"7 10\n" + 
				"9 3\n" + 
				"6 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("7\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "3 10 7\n" + 
				"13 -4\n" + 
				"120 -9\n" + 
				"31 -73\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("0\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "12 20 5\n" + 
				"12 1\n" + 
				"4 -5\n" + 
				"6 10\n" + 
				"9 -11\n" + 
				"12 20\n" + 
				"32 11\n" + 
				"511 -22\n" + 
				"31 -64\n" + 
				"54 23\n" + 
				"9 101\n" + 
				"67 -12\n" + 
				"43 -14\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("156\n", bufOut.toString());
	}
}
