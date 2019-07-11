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
		String GAME = "5\n" + 
				"45\n" + 
				"# # # # *\n" + 
				"# - . . #\n" + 
				"# # - . #\n" + 
				"# # # - #\n" + 
				"# # # # #\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
//		sysOut.println(bufOut.toString());
		assertEquals(
				"    *    \n" + 
				"   # #   \n" + 
				"  # . #  \n" + 
				" # . . # \n" + 
				"# - - - #\n" + 
				" # # # # \n" + 
				"  # # #  \n" + 
				"   # #   \n" + 
				"    #    \n", bufOut.toString());
	}
	
	@Test
	public void testMain2() {
		String GAME = "5\n" + 
				"135\n" + 
				"# # # # #\n" + 
				"# - . . #\n" + 
				"# # - . #\n" + 
				"# # # - #\n" + 
				"# # # # *\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		assertEquals(
				"    *    \n" + 
				"   # #   \n" + 
				"  # - #  \n" + 
				" # . # # \n" + 
				"# . - # #\n" + 
				" # . # # \n" + 
				"  # - #  \n" + 
				"   # #   \n" + 
				"    #    \n", bufOut.toString());
	}
	
	@Test
	public void testMain3() {
		String GAME = "5\n" + 
				"315\n" + 
				"* # # # #\n" + 
				"# - . . #\n" + 
				"# # - . #\n" + 
				"# # # - #\n" + 
				"# # # # #\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		assertEquals(
				"    *    \n" + 
				"   # #   \n" + 
				"  # - #  \n" + 
				" # # . # \n" + 
				"# # - . #\n" + 
				" # # . # \n" + 
				"  # - #  \n" + 
				"   # #   \n" + 
				"    #    \n", bufOut.toString());
	}
	
	@Test
	public void testMain4() {
		String GAME = "5\n" + 
				"225\n" + 
				"# # # # #\n" + 
				"# - . . #\n" + 
				"# # - . #\n" + 
				"# # # - #\n" + 
				"* # # # #\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		assertEquals(
				"    *    \n" + 
				"   # #   \n" + 
				"  # # #  \n" + 
				" # # # # \n" + 
				"# - - - #\n" + 
				" # . . # \n" + 
				"  # . #  \n" + 
				"   # #   \n" + 
				"    #    \n", bufOut.toString());
	}
}
