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


public class Solution2Test {
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
	public void testMain1() {
		String GAME ="3\n" + 
				"0 0\n" + 
				"1 1\n" + 
				"2 2\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("4\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() {
		String GAME ="3\n" + 
				"1 2\n" + 
				"0 0\n" + 
				"2 2\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("4\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() {
		String GAME ="1\n" + 
				"1 1\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("0\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() {
		String GAME ="3\n" +
				"-9 2\n" +
				"-5 -3\n" + 
				"3 -4\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("18\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() {
		String GAME ="3\n"
				+	"2 3\n"
				+ "1 1\n"
				+ "4 4\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("6\n", bufOut.toString());
	}
	
	@Test
	public void testMain6() {
		String GAME ="8\n"
		+ "-28189131 593661218\n"
		+ "102460950 1038903636\n"
		+ "938059973 -816049599\n"
		+ "-334087877 -290840615\n"
		+ "842560881 -116496866\n"
		+ "-416604701 	690825290\n"
		+ "19715507 470868309\n"
		+ "846505116 -694479954\n";

		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("6066790161\n", bufOut.toString());
	}
	
	@Test
	public void testMain7() {
		String GAME ="11\n"
		+ "-162526110 -252675912\n"
		+ "-4895917 -240420085\n"
		+ "141008358 -106615672\n"
		+ "206758372 -63665546\n"
		+ "88473194 -37289256\n"
		+ "202531345 73399429\n"
		+ "-135195154 157092065\n"
		+ "171101176 161166515\n"
		+ "-266264470 191334680\n"
		+ "-205060869 233111863\n"
		+ "-137959173 262220087\n";

		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("2178614523\n", bufOut.toString());
	}
	
}
