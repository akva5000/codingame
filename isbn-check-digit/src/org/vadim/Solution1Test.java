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
		String GAME ="5\n" + 
				"0306406152\n" + 
				"1145185215X\n" + 
				"9780306406157\n" + 
				"9780306406154\n" + 
				"978043551907X\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("3 invalid:\n" + 
				"1145185215X\n" + 
				"9780306406154\n" + 
				"978043551907X\n", bufOut.toString());
	}
}
