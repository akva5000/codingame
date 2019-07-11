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
		String GAME = "2\n"
				+ "Bowser\n"
				+ "Mario\n"
				+ "1\n"
				+ "Mario killed Bowser\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("Name: Bowser\n"
				+ "Killed: None\n"
				+ "Killer: Mario\n"
				+ "\n"
				+ "Name: Mario\n"
				+ "Killed: Bowser\n"
				+ "Killer: Winner\n", bufOut.toString());
	}

	@Test
	public void testMain2() throws Exception {
		String GAME = "5\n"
				+ "Ann\n"
				+ "Isaac\n"
				+ "Mary\n"
				+ "Max\n"
				+ "Thomas\n"
				+ "4\n"
				+ "Max killed Isaac\n"
				+ "Isaac killed Mary\n"
				+ "Mary killed Max\n"
				+ "Thomas killed Ann\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("Name: Ann\n"
				+ "Killed: None\n"
				+ "Killer: Thomas\n"
				+ "\n"
				+ "Name: Isaac\n"
				+ "Killed: Mary\n"
				+ "Killer: Max\n"
				+ "\n"
				+ "Name: Mary\n"
				+ "Killed: Max\n"
				+ "Killer: Isaac\n"
				+ "\n"
				+ "Name: Max\n"
				+ "Killed: Isaac\n"
				+ "Killer: Mary\n"
				+ "\n"
				+ "Name: Thomas\n"
				+ "Killed: Ann\n"
				+ "Killer: Winner\n", bufOut.toString());
	}
}
