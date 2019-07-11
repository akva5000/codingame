package org.vadim.proto.tree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.vadim.proto.tree.ValueNode;

public class ValueNodeTest {

	@Test
	public void testToNoSigString() {
		ValueNode rc = ValueNode.build(0, 5);
		assertEquals("", rc.toNoSigString());
		
		rc.setM(1);
		assertEquals("", rc.toNoSigString());
		
		rc.setM(0);
		assertEquals("", rc.toNoSigString());
		
		rc.setA(1);
		assertEquals("1", rc.toNoSigString());
		
		rc.setA(-1);
		assertEquals("-1", rc.toNoSigString());
		
		rc.setA(10);
		assertEquals("10", rc.toNoSigString());
		
		rc.setM(1);
		rc.setA(1);
		assertEquals("x", rc.toNoSigString());
		
		rc.setA(-1);
		assertEquals("-x", rc.toNoSigString());
		
		rc.setM(2);
		rc.setA(1);
		assertEquals("x^2", rc.toNoSigString());
	}

	@Test
	public void testToString() {
		ValueNode rc = ValueNode.build(0, 5);
		assertEquals("", rc.toString());
		
		rc.setM(1);
		assertEquals("", rc.toString());
		
		rc.setM(0);
		assertEquals("", rc.toString());
		
		rc.setA(1);
		assertEquals("+1", rc.toString());
		
		rc.setA(-1);
		assertEquals("-1", rc.toString());
		
		rc.setA(10);
		assertEquals("+10", rc.toString());
		
		rc.setM(1);
		rc.setA(1);
		assertEquals("+x", rc.toString());
		
		rc.setA(-1);
		assertEquals("-x", rc.toString());
		
		rc.setM(2);
		rc.setA(1);
		assertEquals("+x^2", rc.toString());
	}

}
