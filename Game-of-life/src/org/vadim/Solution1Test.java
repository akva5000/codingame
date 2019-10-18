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
		String GAME = "3 3\n"
				+ "000\n"
				+ "111\n"
				+ "000\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("010\n"
				+ "010\n"
				+ "010\n", bufOut.toString());
	}
	
}
