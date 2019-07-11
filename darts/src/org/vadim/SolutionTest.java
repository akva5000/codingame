package org.vadim;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SolutionTest {
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
	public void testMain() {
//		String GAME = "20\n" + 
//				"2\n" + 
//				"Will\n" + 
//				"Jill\n" + 
//				"4\n" + 
//				"Will 0 0\n" + 
//				"Jill 0 0\n" + 
//				"Will 20 20\n" + 
//				"Jill 0 0\n";
		
		
		String GAME = "20\n" +
				"5\n" +
				"Joe\n" + 
				"Molly\n" +
				"Brandon\n" +
				"Eric\n" +
				"Louis\n" +
				"10\n" + 
				"Joe 0 0\n" + 
				"Molly 0 0\n" +
				"Brandon 0 0\n" + 
				"Eric -50 50\n" +
				"Louis 50 -50\n" + 
				"Joe 10 10\n" +
				"Molly -10 10\n" +
				"Brandon -10 -10\n" +
				"Eric 5 5\n" +
				"Louis 0 0\n";
				
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution.main(new String[0]);
		sysOut.println(bufOut.toString());
//		assertEquals("Jill 30\n" + 
//				"Will 15\n", bufOut.toString());

	}

}
