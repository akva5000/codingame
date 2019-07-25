package org.vadim;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AluTest {
	private Alu.Processor processor = new Alu.Processor();
	private Alu alu = new Alu(processor);

	@Before
	public void before() {
		processor.register[0] = 0; // a
		processor.register[1] = 0; // b
		processor.register[2] = 0; // c
		processor.register[3] = 0; // d
		processor.setPc(0);
	}

	@Test
	public void testExecMov() {
		// MOV regDst regSrc
		processor.register[3] = 33; // d
		alu.execCmd(new int[] { 0B00001100 });
		assertEquals(33, processor.register[0]);
		assertEquals(0, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);

		// MOV regDst number
		alu.execCmd(new int[] { 0B00010000, 66 });
		assertEquals(66, processor.register[0]);
		assertEquals(00, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);
	}

	@Test
	public void testExecAdd() {
		// ADD regDst regSrc1 regSrc2
		processor.register[3] = 33; // d
		processor.register[1] = 11; // b
		alu.execCmd(new int[] { 0B01001100, 1 });
		assertEquals(44, processor.register[0]);
		assertEquals(11, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);

		// ADD regDst regSrc number
		alu.execCmd(new int[] { 0B01011100, 22 });
		assertEquals(55, processor.register[0]);
		assertEquals(11, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);

		// ADD regDst number regSrc
		alu.execCmd(new int[] { 0B01101100, 44 });
		assertEquals(77, processor.register[0]);
		assertEquals(11, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);

		// ADD regDst number1 number2
		alu.execCmd(new int[] { 0B01110000, 5, 10 });
		assertEquals(15, processor.register[0]);
		assertEquals(11, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);
	}

	@Test
	public void testExecSub() {
		// SUB regDst regSrc1 regSrc2
		processor.register[3] = 33; // d
		processor.register[1] = 11; // b
		alu.execCmd(new int[] { 0B10001100, 1 });
		assertEquals(22, processor.register[0]);
		assertEquals(11, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);

		// SUB regDst regSrc number
		alu.execCmd(new int[] { 0B10011100, 22 });
		assertEquals(11, processor.register[0]);
		assertEquals(11, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);

		// SUB regDst number regSrc
		alu.execCmd(new int[] { 0B10101100, 44 });
		assertEquals(11, processor.register[0]);
		assertEquals(11, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);

		// SUB regDst number1 number2
		alu.execCmd(new int[] { 0B10110000, 15, 5 });
		assertEquals(10, processor.register[0]);
		assertEquals(11, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);
	}

	@Test
	public void testExecJNE() {
		// JNE number regSrc1 regSrc2
		processor.register[0] = 33; // a
		processor.register[3] = 33; // d
		alu.execCmd(new int[] { 0B11001100, 5 });
		assertEquals(33, processor.register[0]);
		assertEquals(0, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);

		// JNE number1 regSrc number2
		alu.execCmd(new int[] { 0B11110011, 33, 5 });
		assertEquals(33, processor.register[0]);
		assertEquals(0, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);

		// JNE number regSrc1 regSrc2
		processor.register[0] = 11; // a
		alu.execCmd(new int[] { 0B11001100, 5 });
		assertEquals(11, processor.register[0]);
		assertEquals(0, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);
		assertEquals(5, processor.getPc());

		// JNE number1 regSrc number2
		alu.execCmd(new int[] { 0B11110011, 33, 5 });
		assertEquals(11, processor.register[0]);
		assertEquals(0, processor.register[1]);
		assertEquals(0, processor.register[2]);
		assertEquals(33, processor.register[3]);
		assertEquals(5, processor.getPc());
	}
}
