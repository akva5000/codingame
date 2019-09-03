package org.vadim;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution2Test {
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
		service = arg -> Solution2.main(arg);
	}

	@After
	public void tearDown() throws Exception {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}
	
	@Test
	public void testMain1() throws Exception {
		String GAME = "2\n"
				+ "1 1 1\n"
				+ "1 3 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("CCW\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "3\n"
				+ "1 1 1\n"
				+ "1 3 1\n"
				+ "1 7 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("NOT MOVING\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "5\n"
				+ "0 0 1\n"
				+ "4 0 1\n"
				+ "2 2 1\n"
				+ "2 4 1\n"
				+ "2 0 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("CCW\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() throws Exception {
		String GAME = "3\n"
				+ "0 0 3\n"
				+ "0 4 1\n"
				+ "3 4 2\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("NOT MOVING\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() throws Exception {
		String GAME = "5\n"
				+ "0 0 1\n"
				+ "4 0 1\n"
				+ "2 2 1\n"
				+ "2 4 1\n"
				+ "2 0 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("CCW\n", bufOut.toString());
	}
	
	@Test
	public void testMain6() throws Exception {
		String GAME = "8\n"
				+ "0 0 1\n"
				+ "0 12 3\n"
				+ "0 6 1\n"
				+ "0 16 1\n"
				+ "0 8 1\n"
				+ "3 16 2\n"
				+ "0 4 1\n"
				+ "0 2 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("NOT MOVING\n", bufOut.toString());
	}
	
}
