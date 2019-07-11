package org.vadim.proto.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.vadim.proto.parser.Parser;
import org.vadim.proto.tree.BlockNode;
import org.vadim.proto.tree.Node;
import org.vadim.proto.tree.NodeType;

public class ParserTest {

	@Test
	public void testParse1() {
		List<BlockNode> rc = Parser.parse("5");
		assertNotNull(rc);
		assertEquals(1, rc.size());
		assertEquals(NodeType.VALUE, rc.get(0).getType());
	}
	
	@Test
	public void testParse2() {
		List<BlockNode> rc = Parser.parse("(x-1)*(x+2)");
		assertNotNull(rc);
		assertEquals(2, rc.size());
		assertEquals(NodeType.BLOCK, rc.get(0).getType());
		assertEquals(NodeType.BLOCK, rc.get(1).getType());
	}
	
	@Test
	public void testParse3() {
		List<BlockNode> rc = Parser.parse("(x-1)(x+2)");
		assertNotNull(rc);
		assertEquals(2, rc.size());
		assertEquals(NodeType.BLOCK, rc.get(0).getType());
		assertEquals(NodeType.BLOCK, rc.get(1).getType());
	}

	@Test
	public void testParse4() {
		List<BlockNode> rc = Parser.parse("(x-1)(x+2)^5");
		assertNotNull(rc);
		assertEquals(2, rc.size());
		assertEquals(NodeType.BLOCK, rc.get(0).getType());
		assertEquals(NodeType.POW_BLOCK, rc.get(1).getType());
	}
}
