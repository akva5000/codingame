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
		String GAME = "4 4\n" + 
				".oo.o...oo..ooo.oooo....o...oo..ooo.oooo____o...oo..ooo.oooo____o...oo..ooo.oooo\n" + 
				"o..o................____________________________________________________________\n" + 
				".oo.........................................____________________________________\n" + 
				"................................................................________________\n" + 
				"4\n" + 
				"o...\n" + 
				"....\n" + 
				"....\n" + 
				"....\n" + 
				"4\n" + 
				"o...\n" + 
				"....\n" + 
				"....\n" + 
				"....\n" + 
				"+\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("oo..\n" + 
				"....\n" + 
				"....\n" + 
				"....\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "4 4\n" + 
				".oo.o...oo..ooo.oooo....o...oo..ooo.oooo____o...oo..ooo.oooo____o...oo..ooo.oooo\n" + 
				"o..o................____________________________________________________________\n" + 
				".oo.........................................____________________________________\n" + 
				"................................................................________________\n" + 
				"4\n" + 
				"ooo.\n" + 
				"____\n" + 
				"____\n" + 
				"____\n" + 
				"4\n" + 
				"ooo.\n" + 
				"....\n" + 
				"....\n" + 
				"....\n" + 
				"+\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("o...\n" + 
				"....\n" + 
				"....\n" + 
				"....\n" + 
				"o...\n" + 
				"....\n" + 
				"....\n" + 
				"....\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "4 4\n" +
				".oo.o...oo..ooo.oooo....o...oo..ooo.oooo....o...oo..ooo.oooo....o...oo..ooo.oooo\n" + 
				"o..o................____________________________________________________________\n" + 
				".oo.....................................________________________________________\n" + 
				"............................................................____________________\n" + 
					"16\n" + 
				"o...\n" + 
				"....\n" + 
				"....\n" + 
				"....\n" + 
				"....\n" + 
				"____\n" + 
				"____\n" + 
				"....\n" + 
				"oo..\n" + 
				"____\n" + 
				"____\n" + 
				"____\n" + 
				"....\n" + 
				"____\n" + 
				"....\n" + 
				"....\n" + 
				"20\n" + 
				"oooo\n" + 
				"....\n" + 
				"....\n" + 
				"....\n" + 
				"ooo.\n" + 
				"____\n" + 
				"____\n" + 
				"____\n" + 
				"oo..\n" + 
				"____\n" + 
				"____\n" + 
				"....\n" + 
				"....\n" + 
				"____\n" + 
				"____\n" + 
				"....\n" + 
				"oo..\n" + 
				"____\n" + 
				"____\n" + 
				"....\n" +
				"*\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("o...\n" + 
				"....\n" + 
				"....\n" + 
				"....\n" + 
				"o...\n" + 
				"....\n" + 
				"....\n" + 
				"....\n", bufOut.toString());
	}
}
