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
		String GAME = "1\n" + 
				"2419+*+IE\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("42\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "1\n" + 
				"\"txet elpmiS\"CCCCCCCCCCCE\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("Simple text\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "8\n" + 
				"v > v\n" + 
				"  C \"\n" + 
				" vC GS<\n" + 
				"> \"GC\"^\n" + 
				"  C \"\n" + 
				"  G C\n" + 
				" C\" C\n" + 
				" >^ E\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("CGCGCG\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() throws Exception {
		String GAME = "8\n" + 
				">0111>>>>>>>>v\n" + 
				"v<<<<<<<<<<<<<\n" + 
				">>>>>>\"olleH\"v\n" + 
				"^CC<<<CGFunge \n" + 
				">\" ,\"^Rulz    \n" + 
				"|<<<<<<CCCCC<<\n" + 
				">>>>\"!dlroW \"v\n" + 
				"E<<<<CCCCCCC<<\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("Hello, Hello, Hello, Hello World!\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() throws Exception {
		String GAME = "10\n" + 
				"39DD1+*+DI  >11091+   v>v\n" + 
				" v  \" on the wall\"    < D \n" + 
				">>     \"reeb fo\"      v S\n" + 
				"0v<\" bottles \"        < C\n" + 
				"X>DSC_SvPD      _25*+Cv |\n" + 
				"       *v   IS        < P\n" + 
				"^IDX-1 <>  SC        0v X\n" + 
				"v   \"pass it around\"  < 1\n" + 
				">    \" ,nwod eno ekat\" ^-\n" + 
				" Sing it!   ^+55D-1X_ESD<\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("99 bottles of beer on the wall\n" + 
				"99 bottles of beer\n" + 
				"take one down, pass it around\n" + 
				"98 bottles of beer on the wall\n" + 
				"98 bottles of beer\n" + 
				"take one down, pass it around\n" + 
				"97 bottles of beer on the wall\n" + 
				"97 bottles of beer\n" + 
				"take one down, pass it around\n", bufOut.toString());
	}
	
	@Test
	public void testMain6() throws Exception {
		String GAME = "4\n" + 
				"                v Go down!\n" + 
				"Skip next line  S\n" + 
				"Short line\n" + 
				"Finish here --> > \"!yaY\" CCCCE\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("Yay!\n", bufOut.toString());
	}
}
