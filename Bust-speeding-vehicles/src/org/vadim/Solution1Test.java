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
		String GAME = "50\n" 
				+ "3\n"
				+ "RSLJ97 134 1447409503\n"
				+ "RSLJ97 268 1447411388\n"
				+ "RSLJ97 403 1447412242\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("RSLJ97 268\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "90\n"
				+ "9\n"
				+ "PAZD54 50 1447413071\n"
				+ "PAZD54 150 1447416671\n"
				+ "PAZD54 250 1447420211\n"
				+ "DJSS87 50 1447408801\n"
				+ "DJSS87 150 1447417501\n"
				+ "DJSS87 250 1447421101\n"
				+ "LSKD97 50 1447417436\n"
				+ "LSKD97 150 1447424636\n"
				+ "LSKD97 250 1447431836\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("PAZD54 150\n", bufOut.toString());
	}
	
}
