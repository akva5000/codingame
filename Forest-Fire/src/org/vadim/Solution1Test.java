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
	public void setUp() {
		sysIn = System.in;
		sysOut = System.out;
		service = arg -> {
			try {
				Solution1.main(arg);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new IllegalStateException("ERROR", ex);
			}
		};
	}

	@After
	public void tearDown() throws Exception {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}
	
	@Test
	public void testMain1() throws Exception {
		String GAME = "3 2000\n"
				+ "2\n"
				+ "0 0\n"
				+ "1 2\n"
				+ "2\n"
				+ "0 0\n"
				+ "1 2\n"
				+ "0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("J 1 2\n"
				+ "J 0 0\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "5 2000\n"
				+ "5\n"
				+ "0 2\n"
				+ "0 3\n"
				+ "0 4\n"
				+ "1 3\n"
				+ "1 4\n"

				+ "5\n"
				+ "0 2\n"
				+ "0 3\n"
				+ "0 4\n"
				+ "1 3\n"
				+ "1 4\n"
				
				+ "5\n"
				+ "0 2\n"
				+ "0 3\n"
				+ "0 4\n"
				+ "1 3\n"
				+ "1 4\n"

				+ "0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("H 0 3\n" + 
				"J 0 2\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "6 4000\n"
				+ "10\n"
				+ "0 5\n"
				+ "2 2\n"
				+ "2 3\n"
				+ "3 1\n"
				+ "3 2\n"
				+ "3 3\n"
				+ "4 1\n"
				+ "4 4\n"
				+ "5 4\n"
				+ "5 5\n"
				
				+ "10\n"
				+ "0 5\n"
				+ "2 2\n"
				+ "2 3\n"
				+ "3 1\n"
				+ "3 2\n"
				+ "3 3\n"
				+ "4 1\n"
				+ "4 4\n"
				+ "5 4\n"
				+ "5 5\n"
				
				+ "10\n"
				+ "0 5\n"
				+ "2 2\n"
				+ "2 3\n"
				+ "3 1\n"
				+ "3 2\n"
				+ "3 3\n"
				+ "4 1\n"
				+ "4 4\n"
				+ "5 4\n"
				+ "5 5\n"
				
				+ "10\n"
				+ "0 5\n"
				+ "2 2\n"
				+ "2 3\n"
				+ "3 1\n"
				+ "3 2\n"
				+ "3 3\n"
				+ "4 1\n"
				+ "4 4\n"
				+ "5 4\n"
				+ "5 5\n"
				
				+ "10\n"
				+ "0 5\n"
				+ "2 2\n"
				+ "2 3\n"
				+ "3 1\n"
				+ "3 2\n"
				+ "3 3\n"
				+ "4 1\n"
				+ "4 4\n"
				+ "5 4\n"
				+ "5 5\n"
				
				+ "10\n"
				+ "0 5\n"
				+ "2 2\n"
				+ "2 3\n"
				+ "3 1\n"
				+ "3 2\n"
				+ "3 3\n"
				+ "4 1\n"
				+ "4 4\n"
				+ "5 4\n"
				+ "5 5\n"
				
				+ "10\n"
				+ "0 5\n"
				+ "2 2\n"
				+ "2 3\n"
				+ "3 1\n"
				+ "3 2\n"
				+ "3 3\n"
				+ "4 1\n"
				+ "4 4\n"
				+ "5 4\n"
				+ "5 5\n"

				+ "0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("J 0 5\n" + 
				"J 5 5\n" + 
				"J 5 4\n" + 
				"J 4 4\n" + 
				"H 2 2\n" + 
				"J 4 1\n" + 
				"J 3 1\n", bufOut.toString());
	}
	
}
