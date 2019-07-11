package org.vadim;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SolutionTest {
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
	public void testMain() {
		String GAME = ""
				+ "5 2 10\n" + 
				"11 6 11 10 10\n" + 
				"3 3\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("Fuse was blown.\n", bufOut.toString());
	}

}
