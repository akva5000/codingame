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
		String GAME ="1\n" + 
				"ghibcadef\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("abcdefghi\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() {
		String GAME ="-1\n" + 
				"abcdefghi\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("ghibcadef\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() {
		String GAME ="-1\n" + 
				"hello world\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("worlelhlo d\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() {
		String GAME ="-6\n" + 
				"hello^worlds\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("hrlellwo^ods\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() {
		String GAME ="5\n" + 
				"hitoeplmu eneicldts aide  tsxt \n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("this is a mutliple encoded text\n", bufOut.toString());
	}
	
	@Test
	public void testMain53() {
		String TEXT = "123456";
		int rounds = 1;
		System.setIn(new ByteArrayInputStream(("-" + rounds + '\n' + TEXT + '\n').getBytes()));

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		String encText = bufOut.toString();
		System.err.println("enc=" + encText);
		bufOut.reset();
		
		System.setIn(new ByteArrayInputStream((String.valueOf(rounds) + '\n' + encText).getBytes()));
		Solution2.main(new String[0]);
		String decText = bufOut.toString();
		
		assertEquals(TEXT + '\n', decText);
	}
	
	@Test
	public void testMain54() {
		String GAME ="-5\n" + 
				"this^is^a^mutliple^encoded^text\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("hitoeplmu^eneicldts^aide^^tsxt^\n", bufOut.toString());
	}
	
	@Test
	public void testMain55() {
		String GAME ="5\n" + 
				"hitoeplmu^eneicldts^aide^^tsxt^t\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("this^is^a^mutliple^encoded^text\n", bufOut.toString());
	}
	
	@Test
	public void testMain6() {
		String GAME ="1\n" + 
				"worlelhlo^d\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));
		
		Solution2.main(new String[0]);
		sysOut.println(bufOut.toString());
		
		assertEquals("hello^world\n", bufOut.toString());
	}

}
