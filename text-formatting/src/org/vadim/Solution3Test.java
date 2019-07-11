package org.vadim;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution3Test {
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
	
	/**
	 * Only a single space between words (remove excessive spaces) - aaa   bbb | aaa bbb
	 */
	@Test
	public void testNoSpaceRepetition() {
		String GAME = "Aaa   bbb ccc  ddd\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);

		assertEquals("Aaa bbb ccc ddd\n", bufOut.toString());
	}
	
	/**
	 * Remove repeated punctuation marks.
	 */
	@Test
	public void testNoPunctuationRepetition() {
		String GAME = "Aaa,, bbb\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		sysOut.println(bufOut.toString());

		assertEquals("Aaa, bbb\n", bufOut.toString());
	}
	
	/**
	 * Remove repeated punctuation marks.
	 */
	@Test
	public void testNoPunctuationRepetition2() {
		String GAME = "Aaa... Bbb\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		sysOut.println(bufOut.toString());

		assertEquals("Aaa. Bbb\n", bufOut.toString());
	}
	
	/**
	 * No spaces before punctuation marks - aaa , bbb | aaa, bbb
	 */
	@Test
	public void testNoSpaceBeforePunctuation() {
		String GAME = "Aaa , bbb , ccc\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		sysOut.println(bufOut.toString());

		assertEquals("Aaa, bbb, ccc\n", bufOut.toString());
	}
	
	/**
	 * One space after each punctuation mark in front of a letter;
	 */
	@Test
	public void testSpaceAfterPunctuation() {
		String GAME = "Aaa,b.Cccc\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		sysOut.println(bufOut.toString());

		assertEquals("Aaa, b. Cccc\n", bufOut.toString());
	}
	
	/**
	 * Use only lowercase letters, except for the beginning of the sentence (after a dot);
	 */
	@Test
	public void testLetterCase() {
		String GAME = "aAa. cCcC\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		sysOut.println(bufOut.toString());

		assertEquals("Aaa. Cccc\n", bufOut.toString());
	}
	
	/**
	 * Use only lowercase letters, except for the beginning of the sentence (after a dot);
	 */
	@Test
	public void testSolution1() {
		String GAME = "wn sn,,, Both lh; Wn fer, , , Bh cy...sre\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution3.main(new String[0]);
		sysOut.println(bufOut.toString());

		assertEquals("Wn sn, both lh; wn fer, bh cy. Sre\n", bufOut.toString());
	}

}
