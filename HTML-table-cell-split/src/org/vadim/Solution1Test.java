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
		String GAME = "2\n"
				+ "1,1 1,2\n"
				+ "1,1\n"
				+ "2 C\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("2,1 1,2\n"
				+ "1,1 1,1\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "3\n"
				+ "1,3 3,1\n"
				+ "1,2 1,1 1,1\n"
				+ "1,1 1,1\n"
				+ "4 C\n";
		
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1,3 4,1\n"
				+ "1,2 1,1 1,1 1,1\n"
				+ "1,1 2,1\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "4\n"
				+ "2,1 1,3\n"
				+ "1,3 1,1\n"
				+ "1,1\n"
				+ "2,1\n"
				+ "3 C\n";
		
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("3,1 1,3\n"
				+ "1,3 1,1 1,1\n"
				+ "2,1\n"
				+ "3,1\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() throws Exception {
		String GAME = "3\n"
				+ "1,1 1,2\n"
				+ "1,2\n"
				+ "1,1\n"
				+ "3 C\n";
		
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1,1 2,2\n"
				+ "1,2\n"
				+ "1,1 1,1\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() throws Exception {
		String GAME = "3\n"
				+ "1,3 3,1\n"
				+ "1,2 1,1 1,1\n"
				+ "1,1 1,1\n"
				+ "3 R\n";

		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1,4 3,1\n"
				+ "1,3 1,1 1,2\n"
				+ "1,1\n"
				+ "1,1 1,1\n", bufOut.toString());
	}
	
	@Test
	public void testMain6() throws Exception {
		String GAME = "5\n"
				+ "1,1 1,2 1,1 2,1\n"
				+ "1,3 1,2 2,1\n"
				+ "1,1 1,1 1,3\n"
				+ "3,2\n"
				+ "1,1\n"
				+ "5 C\n";

		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1,4 3,1\n"
				+ "1,3 1,1 1,2\n"
				+ "1,1\n"
				+ "1,1 1,1\n", bufOut.toString());
	}

}
