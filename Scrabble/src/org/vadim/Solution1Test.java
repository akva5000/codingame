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
		String GAME = "5\n" + 
				"because\n" + 
				"first\n" + 
				"these\n" + 
				"could\n" + 
				"which\n" + 
				"hicquwh\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("which\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "10\n" +
				"after\n" + 
				"repots\n" + 
				"user\n" + 
				"powers\n" + 
				"these\n" + 
				"time\n" + 
				"know\n" + 
				"from\n" + 
				"could\n" + 
				"people\n" +
				"tsropwe\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("powers\n", bufOut.toString());
	}
	
}
