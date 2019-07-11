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
		sysOut.flush();
		while(sysIn.available() > 0) sysIn.read();
		System.setIn(sysIn);
		System.setOut(sysOut);
	}
	
	@Test
	public void testMain1() throws Exception {
		String GAME = "10 10\n" + 
				"##########\n" + 
				"#        #\n" + 
				"#  S   W #\n" + 
				"#        #\n" + 
				"#  $     #\n" + 
				"#        #\n" + 
				"#@       #\n" + 
				"#        #\n" + 
				"#E     N #\n" + 
				"##########\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("SOUTH\n" + 
				"SOUTH\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"NORTH\n" + 
				"NORTH\n" + 
				"NORTH\n" + 
				"NORTH\n" + 
				"NORTH\n" + 
				"NORTH\n" + 
				"WEST\n" + 
				"WEST\n" + 
				"WEST\n" + 
				"WEST\n" + 
				"SOUTH\n" + 
				"SOUTH\n", bufOut.toString());
	}
	
	@Test
	public void testMain2() throws Exception {
		String GAME = "5 5\n" +
				"#####\n" +
				"#@  #\n" + 
				"#   #\n" + 
				"#  $#\n" +
				"#####\n"; 
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("SOUTH\n" + 
				"SOUTH\n" + 
				"EAST\n" + 
				"EAST\n", bufOut.toString());
	}
	
	@Test
	public void testMain3() throws Exception {
		String GAME = "10 10\n" +
				"##########\n" +
				"# @      #\n" + 
				"# B      #\n" + 
				"#XXX     #\n" + 
				"# B      #\n" + 
				"#    BXX$#\n" + 
				"#XXXXXXXX#\n" + 
				"#        #\n" + 
				"#        #\n" +
				"##########\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n", bufOut.toString());
	}
	
	@Test
	public void testMain4() throws Exception {
		String GAME = "9 10\n" +
			  "##########\n" +
				"#  @     #\n" + 
				"#  B     #\n" + 
				"#  S   W #\n" + 
				"# XXX    #\n" + 
				"#  B   N #\n" + 
				"# XXXXXXX#\n" + 
				"#       $#\n" +
				"##########\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"NORTH\n" + 
				"NORTH\n" + 
				"WEST\n" + 
				"WEST\n" + 
				"WEST\n" + 
				"WEST\n" + 
				"SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n", bufOut.toString());
	}
	
	@Test
	public void testMain5() throws Exception {
		String GAME = "15 15\n" +
				"###############\n" +
				"#      IXXXXX #\n" +
				"#  @          #\n" +
				"#             #\n" +
				"#             #\n" +
				"#  I          #\n" +
				"#  B          #\n" +
				"#  B   S     W#\n" +
				"#  B   T      #\n" +
				"#             #\n" +
				"#         T   #\n" +
				"#         B   #\n" +
				"#            $#\n" +
				"#        XXXX #\n" +
				"###############\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				
				"WEST\n" + 
				"WEST\n" +
				
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" +
				"EAST\n" +
				"EAST\n" +
				"EAST\n" +
				"EAST\n" +
				"EAST\n" +
				"EAST\n" +
				"EAST\n" +
				"EAST\n" +
				
				"SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"WEST\n" +
				"WEST\n" +
				"WEST\n" +
				"WEST\n" +
				"WEST\n" +
				"WEST\n" +
				
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				
				"EAST\n" +
				"EAST\n" +
				"EAST\n", bufOut.toString());
	}
	
	@Test
	public void testMain6() throws Exception {
		String GAME = "30 15\n" +
				"###############\n" +
				"#  #@#I  T$#  #\n" + 
				"#  #    IB #  #\n" + 
				"#  #     W #  #\n" + 
				"#  #      ##  #\n" + 
				"#  #B XBN# #  #\n" + 
				"#  ##      #  #\n" + 
				"#  #       #  #\n" + 
				"#  #     W #  #\n" + 
				"#  #      ##  #\n" + 
				"#  #B XBN# #  #\n" + 
				"#  ##      #  #\n" + 
				"#  #       #  #\n" + 
				"#  #     W #  #\n" + 
				"#  #      ##  #\n" + 
				"#  #B XBN# #  #\n" + 
				"#  ##      #  #\n" + 
				"#  #       #  #\n" + 
				"#  #       #  #\n" + 
				"#  #      ##  #\n" + 
				"#  #  XBIT #  #\n" + 
				"#  #########  #\n" + 
				"#             #\n" + 
				"# ##### ##### #\n" + 
				"# #     #     #\n" + 
				"# #     #  ## #\n" + 
				"# #     #   # #\n" + 
				"# ##### ##### #\n" + 
				"#             #\n" +
				"###############\n";
		ByteArrayInputStream bufIn = new ByteArrayInputStream(GAME.getBytes());
		System.setIn(bufIn);

		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		Solution1.main(new String[0]);
		sysOut.println(bufOut.toString());
		assertEquals("SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				
				"WEST\n" + 
				"WEST\n" +
				
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				"NORTH\n" +
				
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" + 
				"EAST\n" +
				"EAST\n" +
				"EAST\n" +
				
				"SOUTH\n" + 
				"SOUTH\n" + 
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				
				"SOUTH\n" +
				"SOUTH\n" +
				"SOUTH\n" +
				
				"EAST\n" +
				"EAST\n" +
				"EAST\n" +
				
				"NORTH\n", bufOut.toString());
	}
	
	
	
}
