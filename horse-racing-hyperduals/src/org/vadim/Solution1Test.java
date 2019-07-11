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
		String GAME ="10\n" + 
				"6850207 0\n" + 
				"8707138 0\n" + 
				"8028585 0\n" + 
				"3635318 0\n" + 
				"8612162 0\n" + 
				"6854699 0\n" + 
				"7106093 0\n" + 
				"3721952 0\n" + 
				"2670046 0\n" + 
				"1746583 0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("4492\n", bufOut.toString());
	}
}
