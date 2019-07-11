package org.vadim;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution3Test {
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
		service = (arg) -> Solution3.main(arg);
	}

	@After
	public void tearDown() throws Exception {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}
	
	@Test
	public void testMain1() throws Exception {
		String GAME = "3\n" + 
				"100\n" + 
				"20\n" + 
				"20\n" + 
				"40\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("IMPOSSIBLE\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "3\n" + 
				"100\n" + 
				"40\n" + 
				"40\n" + 
				"40\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("33\n" + 
				"33\n" + 
				"34\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "3\n" + 
				"100\n" + 
				"100\n" + 
				"1\n" + 
				"60\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1\n" + 
				"49\n" + 
				"50\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() throws Exception {
		String GAME = "3\n" + 
				"3\n" + 
				"3\n" + 
				"3\n" + 
				"3\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1\n" + 
				"1\n" + 
				"1\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() throws Exception {
		String GAME = "3\n" + 
				"100\n" + 
				"10\n" + 
				"100\n" + 
				"100\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("10\n" + 
				"45\n" + 
				"45\n", bufOut.toString());
	}
	
}
