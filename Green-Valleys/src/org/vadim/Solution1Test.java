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
		String GAME = "5\n" 
				+ "5\n"
				+ "8 9 9 8 7\n"
				+ "8 2 3 2 7\n"
				+ "6 4 5 4 8\n"
				+ "9 8 4 2 7\n"
				+ "7 8 9 6 5\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("2\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "1200\n" 
				+ "4\n"
				+ "1230 1241 1223 1244\n"
				+ "1002 1014 1223 1244\n"
				+ "1230 1241 1072 1244\n"
				+ "1230 1132 1118 1171\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1072\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "100\n" 
				+ "4\n"
				+ "120 134 172 141 154\n"
				+ "171 100 121 91 132\n"
				+ "165 51 120 179 141\n"
				+ "162 73 145 81 87\n"
				+ "120 134 172 79 154\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("51\n", bufOut.toString());
	}
}
