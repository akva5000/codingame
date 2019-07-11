package org.vadim;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution3Test {
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
		service = arg -> Solution3.main(arg);
	}

	@After
	public void tearDown() throws Exception {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}
	
	@Test
	public void testMain1() throws Exception {
		String GAME = "10 10\n" + 
				"0 0 0 0 0 0 0 0 9 0\n" + 
				"0 0 0 0 0 0 9 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0\n" + 
				"0 20 0 0 8 0 0 0 6 0\n" + 
				"0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 6 0 0 6 0 0 0\n" + 
				"10 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 6 0 6 0 0 0 8 0\n" + 
				"0 0 0 0 0 0 6 0 0 0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("1\n" + 
				"AAAABBBCCC\n" + 
				"AAAABBBCCC\n" + 
				"AAAABBBCCC\n" + 
				"AAAADDDDEE\n" + 
				"AAAADDDDEE\n" + 
				"FFGGGHHHEE\n" + 
				"FFGGGHHHII\n" + 
				"FFJJKKLLII\n" + 
				"FFJJKKLLII\n" + 
				"FFJJKKLLII\n", bufOut.toString());
	}

	@Test
	public void testMain2() throws Exception {
		String GAME = "15 20\n" +
				"0 0 0 0 0 0 0 0 0 9 0 0 0 0 0\n" + 
				"0 8 0 0 0 6 0 0 6 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 10 0 0 0 0 6 0 6 0 0 0 0\n" + 
				"0 10 0 0 0 0 0 0 0 0 0 0 15 0 0\n" + 
				"0 0 0 14 0 0 9 0 0 0 6 0 0 0 0\n" + 
				"0 0 0 0 0 12 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 6 0 0 0 12 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 8 0 0\n" + 
				"0 0 0 0 0 9 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 14 0 0 0 0 0 0 8 0 0 0 0 0 24\n" + 
				"0 0 0 0 0 0 0 0 0 6 0 0 6 0 0\n" + 
				"0 0 0 9 0 0 6 0 0 28 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 8 0 0 0 0 0 0 0\n" + 
				"0 0 0 10 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 15 0 6 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"8 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("20\n" +
				"AABBCCDDDDDDDDD\n" + 
				"AABBCCEEEEEEFFF\n" + 
				"AABBCCGGGHHHFFF\n" + 
				"AABBIIGGGHHHFFF\n" + 
				"JJBBIIKKKLLLFFF\n" + 
				"JJMMIIKKKLLLFFF\n" + 
				"JJMMIIKKKNNNNNN\n" + 
				"JJMMIIOOONNNNNN\n" + 
				"JJMMIIOOOPPPPQQ\n" + 
				"RRMMSSSTTPPPPQQ\n" + 
				"RRMMSSSTTUUVVQQ\n" + 
				"RRMMSSSTTUUVVQQ\n" + 
				"RRWWWXXTTUUVVQQ\n" + 
				"RRWWWXXYYZZZZQQ\n" + 
				"RRWWWXXYYZZZZQQ\n" + 
				"RRaaaaaYYZZZZQQ\n" + 
				"bbaaaaaYYZZZZQQ\n" + 
				"bbcccccddZZZZQQ\n" + 
				"bbcccccddZZZZQQ\n" + 
				"bbcccccddZZZZQQ\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "20 20\n"	+
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 65 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 15 0 0 0 0 0\n" + 
				"18 0 0 0 0 0 0 0 0 9 0 0 0 6 0 0 0 0 0 6\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 0 0\n" + 
				"0 0 0 0 0 0 0 8 0 0 0 0 0 0 0 6 8 0 0 0\n" + 
				"10 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 8 0 6 0 0 8 0 0 6 0 0 0 0 0 0\n" + 
				"8 0 0 0 0 0 0 0 0 0 0 6 0 0 0 6 0 0 0 8\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 6 0 0 6 0 0 0 6 0 0 0 12 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 16 0 0 10 0 0 0 0 0 6 0 0 0 0 0 0 0\n" + 
				"9 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 6 0 0 0 0 0 8 0 0 0 0\n" + 
				"0 0 0 0 0 9 0 9 0 0 0 0 0 6 0 0 8 0 8 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 0\n" + 
				"0 0 0 0 0 0 26 0 0 0 0 0 0 8 0 0 0 0 0 0\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("32\n" + 
				"AAAAAAAAAAAAABBBCCCC\n" + 
				"AAAAAAAAAAAAABBBCCCC\n" + 
				"AAAAAAAAAAAAABBBDDDD\n" + 
				"AAAAAAAAAAAAABBBDDDD\n" + 
				"AAAAAAAAAAAAABBBEEFF\n" + 
				"GGGGGGGGGHHHIIJJEEFF\n" + 
				"GGGGGGGGGHHHIIJJEEFF\n" + 
				"KKKKKLLLLHHHIIJJMMMM\n" + 
				"KKKKKLLLLNNOOPPPMMMM\n" + 
				"QQQQRRSSSNNOOPPPTTTT\n" + 
				"QQQQRRSSSNNOOUUUTTTT\n" + 
				"VVVVRRWWWNNXXUUUYYYY\n" + 
				"VVVVRRWWWZZXXaaaYYYY\n" + 
				"VVVVbbbbbZZXXaaaYYYY\n" + 
				"VVVVbbbbbZZcccddeeff\n" + 
				"ggghhhiiijjcccddeeff\n" + 
				"ggghhhiiijjkkkddeeff\n" + 
				"ggghhhiiijjkkkddeeff\n" + 
				"lllllllllllllmmmmnnn\n" + 
				"lllllllllllllmmmmnnn\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() throws Exception {
		String GAME = "30 30\n"	+
				"6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 45 0 0 0 0 0 0 0 0 8 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 9 0 0 0 0 9 0 0 0 0 0 6 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 21 0 0 0 0\n" + 
				"0 0 15 0 0 0 0 0 8 0 0 0 0 0 0 0 0 0 0 12 0 0 6 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 10 0 0 0 0 0 0 0 0 0 0 0 10 0 0 0 8 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 21 0 0 0 0 0 0 0 0 6 8 0 0 6 0 0 15 0 0\n" + 
				"0 0 0 0 0 0 0 0 308 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 20\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 6 0 0 0 0 0 8 6 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 0 0 12 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 18 0 0 8 0 15 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 0 0 0 0 0 0 0 0 0 0 0 0 8\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 16 0 0 0 0 0 0 6 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 10 0 0 0 0 9 0 0 0 9 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 9 0 0 0 0 0 0 0 0 0 0 10\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 6 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 10 0 0 0 0 0 0 15 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 96 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		service.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("32\n" + 
				"AAAAAAAAAAAAABBBCCCC\n" + 
				"AAAAAAAAAAAAABBBCCCC\n" + 
				"AAAAAAAAAAAAABBBDDDD\n" + 
				"AAAAAAAAAAAAABBBDDDD\n" + 
				"AAAAAAAAAAAAABBBEEFF\n" + 
				"GGGGGGGGGHHHIIJJEEFF\n" + 
				"GGGGGGGGGHHHIIJJEEFF\n" + 
				"KKKKKLLLLHHHIIJJMMMM\n" + 
				"KKKKKLLLLNNOOPPPMMMM\n" + 
				"QQQQRRSSSNNOOPPPTTTT\n" + 
				"QQQQRRSSSNNOOUUUTTTT\n" + 
				"VVVVRRWWWNNXXUUUYYYY\n" + 
				"VVVVRRWWWZZXXaaaYYYY\n" + 
				"VVVVbbbbbZZXXaaaYYYY\n" + 
				"VVVVbbbbbZZcccddeeff\n" + 
				"ggghhhiiijjcccddeeff\n" + 
				"ggghhhiiijjkkkddeeff\n" + 
				"ggghhhiiijjkkkddeeff\n" + 
				"lllllllllllllmmmmnnn\n" + 
				"lllllllllllllmmmmnnn\n", bufOut.toString());
	}
}
