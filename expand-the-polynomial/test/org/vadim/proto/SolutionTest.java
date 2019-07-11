package org.vadim.proto;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.vadim.proto.logic.Optimizer;
import org.vadim.proto.parser.Parser;
import org.vadim.proto.tree.BlockNode;
import org.vadim.proto.tree.ValueNode;

public class SolutionTest {

	@Test
	public void test1() {
		List<BlockNode> nodes = Parser.parse("(x-1)*(x+2)");
		List<ValueNode> rc = Optimizer.optimize(nodes);
		for(ValueNode n : rc) {
			System.out.print(n);
		}
		System.out.println();
	}
	
	@Test
	public void test2() {
		List<BlockNode> nodes = Parser.parse("(x-2)^2");
		List<ValueNode> rc = Optimizer.optimize(nodes);
		for(ValueNode n : rc) {
			System.out.print(n);
		}
		System.out.println();
	}

}
