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
		String GAME = "4.5+0i\n"
				+ "10\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "-0.65812-0.452i\n"
				+ "124\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("124\n", bufOut.toString());
	}

	@Test
	public void testMain3() throws Exception {
		String GAME = "0.465+0.354i\n"
				+ "20\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("12\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() throws Exception {
		String GAME = "-1.1+0.0i\n"
				+ "45\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("45\n", bufOut.toString());
	}	


}
