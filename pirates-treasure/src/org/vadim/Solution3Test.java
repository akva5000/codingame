package org.vadim;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution3Test {
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
		String GAME = "2\n" + 
				"2\n" + 
				"0 1\n" + 
				"1 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
//		sysOut.println(bufOut.toString());
		assertEquals("0 0\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() {
		String GAME = "4\n" + 
				"4\n" + 
				"0 1 0 1\n" + 
				"0 1 1 1\n" +
				"0 0 0 0\n" +
				"0 0 0 0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		assertEquals("2 0\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() {
		String GAME = "4\n" + 
				"4\n" + 
				"0 0 0 0\n" + 
				"0 0 0 0\n" +
				"0 1 1 1\n" +
				"0 1 0 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		assertEquals("2 3\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() {
		String GAME = "4\n" + 
				"4\n" + 
				"0 0 0 0\n" + 
				"1 1 0 0\n" +
				"0 1 0 0\n" +
				"1 1 0 0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		assertEquals("0 2\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() {
		String GAME = "4\n" + 
				"4\n" + 
				"0 0 0 0\n" + 
				"1 0 1 1\n" +
				"0 0 1 0\n" +
				"1 0 1 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		assertEquals("3 2\n", bufOut.toString());
	}
	
	@Test
	public void testMain6() {
		String GAME = "4\n"
				+ "4\n" + 
				"1 1 1 0\n" + 
				"1 0 1 0\n" + 
				"1 1 1 1\n" + 
				"0 0 1 1\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		assertEquals("1 1\n", bufOut.toString());
	}
	
	@Test
	public void testMain7() {
		String GAME = "5\n"
				+ "7\n" + 
				"	0 0 1 1 0\n" + 
				"	0 1 0 0 1\n" + 
				"	0 1 1 1 0\n" + 
				"	0 1 0 1 1\n" + 
				"	1 1 1 1 0\n" + 
				"	0 1 0 0 1\n" + 
				"	1 0 0 0 0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		assertEquals("2 3\n", bufOut.toString());
	}
	
}
