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
		String GAME = "57\n" + 
				"70545\n" + 
				"31 PLAIN 30\n" + 
				"18 PLAIN 35\n" + 
				"14 PLAIN 32\n" + 
				"25 ODD\n" + 
				"13 PLAIN 9\n" + 
				"14 PLAIN 34\n" + 
				"32 ODD\n" + 
				"26 PLAIN 9\n" + 
				"29 EVEN\n" + 
				"7 PLAIN 21\n" + 
				"32 PLAIN 29\n" + 
				"0 PLAIN 7\n" + 
				"7 PLAIN 34\n" + 
				"13 PLAIN 14\n" + 
				"22 PLAIN 8\n" + 
				"25 PLAIN 28\n" + 
				"11 PLAIN 20\n" + 
				"14 ODD\n" + 
				"23 ODD\n" + 
				"13 PLAIN 22\n" + 
				"2 ODD\n" + 
				"23 EVEN\n" + 
				"17 ODD\n" + 
				"30 EVEN\n" + 
				"28 PLAIN 28\n" + 
				"5 PLAIN 36\n" + 
				"13 EVEN\n" + 
				"22 PLAIN 11\n" + 
				"5 EVEN\n" + 
				"32 PLAIN 25\n" + 
				"13 ODD\n" + 
				"10 EVEN\n" + 
				"28 ODD\n" + 
				"15 PLAIN 2\n" + 
				"33 EVEN\n" + 
				"29 ODD\n" + 
				"1 EVEN\n" + 
				"19 PLAIN 12\n" + 
				"0 PLAIN 34\n" + 
				"24 EVEN\n" + 
				"16 PLAIN 36\n" + 
				"4 EVEN\n" + 
				"35 PLAIN 13\n" + 
				"14 PLAIN 34\n" + 
				"30 ODD\n" + 
				"13 EVEN\n" + 
				"29 ODD\n" + 
				"7 EVEN\n" + 
				"18 PLAIN 20\n" + 
				"33 ODD\n" + 
				"24 PLAIN 28\n" + 
				"34 PLAIN 34\n" + 
				"33 EVEN\n" + 
				"32 EVEN\n" + 
				"10 EVEN\n" + 
				"13 ODD\n" + 
				"35 PLAIN 26\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1153\n", bufOut.toString());
	}
	
}
