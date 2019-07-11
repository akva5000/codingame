package org.vadim;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SolutionTest {
	private InputStream sysIn;
	private PrintStream sysOut;

	@Before
	public void before() {
		sysIn = System.in;
		sysOut = System.out;
	}

	@After
	public void after() {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}

	@Test
	public void testMain() {
		String GAME ="5\n" +
	  "3\n" +
		"X X X X X\n" +
		"X C X X X\n" +
		"X X X X X\n" +
		"X X X X X\n" +
		"X X X X X\n";
		
//		String GAME ="5\n" +
//			  "3\n" +
//				"X X X X X\n" +
//				"X X X X X\n" +
//				"X X C X X\n" +
//				"X X X X X\n" +
//				"X X X X X\n";
		
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution.main(new String[0]);
		sysOut.println(bufOut.toString());
	}

}
