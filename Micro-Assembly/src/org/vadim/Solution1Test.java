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
		String GAME = "1 2 3 -4\n" + 
				"2\n" + 
				"MOV b 3\n" + 
				"MOV c a\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1 3 1 -4\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "2 3 4 5\n" + 
				"3\n" + 
				"ADD a b 1\n" + 
				"ADD b 2 7\n" + 
				"ADD c a b\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("4 9 13 5\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "14 2 21 9\n" + 
				"3\n" + 
				"SUB a a a\n" + 
				"SUB d 12 a\n" + 
				"SUB b 15 3\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("0 12 21 12\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() throws Exception {
		String GAME = "3 5 7 9\n" + 
				"2\n" + 
				"SUB b b 1\n" + 
				"JNE 0 b 0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("3 0 7 9\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() throws Exception {
		String GAME = "0 -2 -3 -4\n" + 
				"4\n" + 
				"MOV a -1\n" + 
				"SUB b c -3\n" + 
				"ADD d d -1\n" + 
				"JNE 2 d -10\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("-1 0 -3 -10\n", bufOut.toString());
	}
	
	@Test
	public void testMain6() throws Exception {
		String GAME = "0 10 0 0\n" + 
				"3\n" + 
				"ADD a a b\n" + 
				"SUB b b 1\n" + 
				"JNE 0 b 0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("55 0 0 0\n", bufOut.toString());
	}
	
	@Test
	public void testMain7() throws Exception {
		String GAME = "1 3 3 7\n" + 
				"9\n" + 
				"MOV a 10\n" + 
				"MOV b 5\n" + 
				"MOV c b\n" + 
				"SUB c c 1\n" + 
				"ADD a a c\n" + 
				"JNE 3 c 0\n" + 
				"SUB b b 1\n" + 
				"JNE 2 b c\n" + 
				"SUB d 0 d\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("30 0 0 -7\n", bufOut.toString());
	}
	
	@Test
	public void testMain8() throws Exception {
		String GAME = "0 7 5 3\n" + 
				"13\n" + 
				"ADD a a b\n" + 
				"SUB c c 1\n" + 
				"JNE 0 c 0\n" + 
				"MOV b a\n" + 
				"SUB c d 1\n" + 
				"ADD a a b\n" + 
				"SUB c c 1\n" + 
				"JNE 5 c 0\n" + 
				"SUB b 0 d\n" + 
				"JNE 11 a -105\n" + 
				"MOV a 0\n" + 
				"ADD d d b\n" + 
				"SUB b b b\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("105 0 0 0\n", bufOut.toString());
	}
}
