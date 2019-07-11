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
	public void testMain1() {
		String GAME ="2\n" + 
				"Hugo\n" + 
				"Guillaume\n" + 
				"10 5 3*18 15 5 4 8\n" + 
				"5 5 10 2*19 5 6 2*5 1 20 1\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("Hugo\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() {
		String GAME ="2\n" + 
				"Henry\n" + 
				"Herve\n" + 
				"2*5 5 5 2*19 5 6 10 1 20 1\n" + 
				"3*17 5 12 X X 15 3*16 20\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("Herve\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() {
		String GAME ="2\n" + 
				"Noemie\n" + 
				"Nicolas\n" + 
				"2 5 5 19 5 6 10 1 20 1 2 5\n" + 
				"3*17 5 12 5 2 3 15 9 20 3\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("Nicolas\n", bufOut.toString());
	}
}
