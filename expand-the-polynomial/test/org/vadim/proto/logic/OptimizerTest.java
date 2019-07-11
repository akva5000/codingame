package org.vadim.proto.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.vadim.proto.logic.Optimizer;
import org.vadim.proto.tree.BlockNode;
import org.vadim.proto.tree.ValueNode;

public class OptimizerTest {

	@Test
	public void testHandle1() {
		ArrayList<BlockNode> nodes = new ArrayList<>();
		nodes.add(new BlockNode(new ValueNode[] { ValueNode.build(5, 0) }));
		List<ValueNode> rc = Optimizer.optimize(nodes);
		assertNotNull(rc);
		assertEquals(1, rc.size());
	}

	@Test
	public void testHandle2() {
		ArrayList<BlockNode> nodes = new ArrayList<>();
		nodes.add(new BlockNode(new ValueNode[] { ValueNode.build(5, 1), ValueNode.build(3, 3) }));
		List<ValueNode> rc = Optimizer.optimize(nodes);
		assertNotNull(rc);
		assertEquals(2, rc.size());
	}

	@Test
	public void testHandle3() {
		ArrayList<BlockNode> nodes = new ArrayList<>();
		nodes.add(new BlockNode(new ValueNode[] { ValueNode.build(5, 2), ValueNode.build(12, 2) }));
		List<ValueNode> rc = Optimizer.optimize(nodes);
		assertNotNull(rc);
		assertEquals(1, rc.size());
		System.out.println(rc.get(0).toString());
	}
	
	@Test
	public void testHandle4() {
		ArrayList<BlockNode> nodes = new ArrayList<>();
		nodes.add(new BlockNode(new ValueNode[] { 
				ValueNode.build(5, 2), 
				ValueNode.build(12, 2),
				ValueNode.build(3, 4),
				ValueNode.build(6, 1),
				ValueNode.build(1, 1),
				ValueNode.build(20, 0),
				}));
		List<ValueNode> rc = Optimizer.optimize(nodes);
		assertNotNull(rc);
		assertEquals(4, rc.size());
		for(ValueNode n : rc) {
			System.out.print(n.toString());
			System.out.print(" ");
		}
		System.out.println();
	}
}
