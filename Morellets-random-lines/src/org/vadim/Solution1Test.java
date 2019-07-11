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
	public void before() {
		sysIn = System.in;
		sysOut = System.out;
	}

	@After
	public void after() {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}
	
	@Test
	public void testMain0() {
		String GAME = 
				"-2 1 0 -1\n" +
				"1\n" +
				"1 1 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());

		assertEquals("ON A LINE\n", bufOut.toString());
	}

	@Test
	public void testMain1() {
		String GAME = 
				"1 1 0 0\n" +
				"1\n" +
				"1 2 3\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());

		assertEquals("YES\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() {
		String GAME =
				"3 2 -2 -2\n" + 
				"1\n" + 
				"1 2 3\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("YES\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() {
		String GAME = "3 2 -2 -2\n" 
				+ "2\n"
				+ "1 2 3\n"
				+ "1 1 1\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("YES\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() {
		String GAME =
				"-5 3 4 2\n" + 
				"2\n" + 
				"-7 6 -9\n"	+
				"-6 10 -7\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("NO\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() {
		String GAME ="7 -7 5 -5\n" + 
				"7\n" + 
				"-3 5 3\n"
				+ "9 -2 10\n"
				+ "-3 6 9\n"
				+ "-4 -2 -5\n"
				+ "0 1 3\n"
				+ "8 2 -6\n"
				+ "1 6 -9\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("NO\n", bufOut.toString());
	}
	
	@Test
	public void testMain6() {
		String GAME ="-5 3 4 2\n" + 
				"10\n" +
				"-7 6 -9\n" +
				"-6 10 -7\n" +
				"-3 -4 -2\n" +
				"-3 1 0\n" +
				"-3 8 3\n" +
				"1 -5 -4\n" +
				"3 -8 10\n" +
				"3 9 -5\n" +
				"8 3 -2\n" +
				"10 4 -3\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("NO\n", bufOut.toString());
	}
	
	
}
