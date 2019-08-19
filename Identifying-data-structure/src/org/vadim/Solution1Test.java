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
				+ "i1 i2 i3 o1 o2 o3\n"
				+ "i3 i2 i1 o1 o2 o3\n"
				+ "i2 i1 i3 i1 o3 o2\n"
				+ "i1 i1 i1 o1 o1 o1\n"
				+ "i1 o2\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("queue\n"
				+ "stack\n"
				+ "priority queue\n"
				+ "unsure\n"
				+ "mystery\n", bufOut.toString());
	}
	
}
