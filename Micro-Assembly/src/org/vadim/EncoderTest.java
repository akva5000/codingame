package org.vadim;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncoderTest {
	
	@Test
	public void testEncodeMov() {
		int[] cmd;
		
		// MOV regDst regSrc
		cmd = Encoder.encode("MOV a d");
		assertNotNull(cmd);
		assertEquals(1, cmd.length);
		assertEquals(0B00001100, cmd[0]);

		// MOV regDst number
		cmd = Encoder.encode("MOV a 3");
		assertNotNull(cmd);
		assertEquals(2, cmd.length);
		assertEquals(0B00010000, cmd[0]);
		assertEquals(3, cmd[1]);
	}

	@Test
	public void testEncodeAdd() {
		int[] cmd;
		
		// ADD regDst regSrc1 regSrc2
		cmd = Encoder.encode("ADD a b d");
		assertNotNull(cmd);
		assertEquals(2, cmd.length);
		assertEquals(0B01000100, cmd[0]);
		assertEquals(3, cmd[1]);
		
		// ADD regDst regSrc number
		cmd = Encoder.encode("ADD a b 5");
		assertNotNull(cmd);
		assertEquals(2, cmd.length);
		assertEquals(0B01010100, cmd[0]);
		assertEquals(5, cmd[1]);
		
		// ADD regDst number regSrc
		cmd = Encoder.encode("ADD a 5 b");
		assertNotNull(cmd);
		assertEquals(2, cmd.length);
		assertEquals(0B01100100, cmd[0]);
		assertEquals(5, cmd[1]);
		
		// ADD regDst number1 number2
		cmd = Encoder.encode("ADD a 5 7");
		assertNotNull(cmd);
		assertEquals(3, cmd.length);
		assertEquals(0B01110000, cmd[0]);
		assertEquals(5, cmd[1]);
		assertEquals(7, cmd[2]);
	}
	
	@Test
	public void testEncodeSub() {
		int[] cmd;
		
		// SUB regDst regSrc1 regSrc2
		cmd = Encoder.encode("SUB a b d");
		assertNotNull(cmd);
		assertEquals(2, cmd.length);
		assertEquals(0B10000100, cmd[0]);
		assertEquals(3, cmd[1]);
		
		// SUB regDst regSrc number
		cmd = Encoder.encode("SUB a b 5");
		assertNotNull(cmd);
		assertEquals(2, cmd.length);
		assertEquals(0B10010100, cmd[0]);
		assertEquals(5, cmd[1]);
		
		// SUB regDst number regSrc
		cmd = Encoder.encode("SUB a 5 b");
		assertNotNull(cmd);
		assertEquals(2, cmd.length);
		assertEquals(0B10100100, cmd[0]);
		assertEquals(5, cmd[1]);
		
		// SUB regDst number1 number2
		cmd = Encoder.encode("SUB a 5 7");
		assertNotNull(cmd);
		assertEquals(3, cmd.length);
		assertEquals(0B10110000, cmd[0]);
		assertEquals(5, cmd[1]);
		assertEquals(7, cmd[2]);
	}
	
	@Test
	public void testEncodeJne() {
		int[] cmd;
		
		// JNE number regSrc1 regSrc2
		cmd = Encoder.encode("JNE 100 b d");
		assertNotNull(cmd);
		assertEquals(2, cmd.length);
		assertEquals(0B11000111, cmd[0]);
		assertEquals(100, cmd[1]);
		
		// JNE number1 regSrc number2
		cmd = Encoder.encode("JNE 100 b 30");
		assertNotNull(cmd);
		assertEquals(3, cmd.length);
		assertEquals(0B11110001, cmd[0]);
		assertEquals(30, cmd[1]);
		assertEquals(100, cmd[2]);
	}

}
