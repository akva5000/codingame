package org.vadim.proto.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.vadim.proto.tree.BlockNode;
import org.vadim.proto.tree.NodeType;
import org.vadim.proto.tree.PowerBlockNode;
import org.vadim.proto.tree.ValueNode;

public class Optimizer {

	public static List<ValueNode> optimize(List<BlockNode> nodes) {
		if (nodes.size() == 1) {
			if (nodes.get(0).getType() == NodeType.VALUE) return Collections.singletonList(nodes.get(0).getValues()[0]);
		}

		convPowBlocks(nodes);

		while (nodes.size() > 1) {
			BlockNode b = blocksMul((BlockNode) nodes.get(0), nodes.get(1));
			nodes.remove(1);
			nodes.set(0, b);
		}

		List<ValueNode> rc = new ArrayList<>();
		for (int i = 0; i < nodes.get(0).getValues().length; i++) {
			rc.add(nodes.get(0).getValues()[i]);
		}

		if (rc.size() > 1) {
			Collections.sort(rc, (n1, n2) -> Integer.compare(n2.getM(), n1.getM()));
			int i = 0;
			while (i < rc.size() - 1) {
				if (rc.get(i).getM() == rc.get(i + 1).getM()) {
					ValueNode n = ValueNode.build(rc.get(i).getA() + rc.get(i + 1).getA(), rc.get(i).getM());
					rc.remove(i);
					rc.set(i, n);
					if (i == rc.size() - 1) ++i;
				} else {
					++i;
				}
			}
		}

		int i = 0;
		while (i < rc.size()) {
			if (rc.get(i).getA() == 0) {
				rc.remove(i);
			} else {
				++i;
			}
		}
		return rc;
	}

	private static void convPowBlocks(List<BlockNode> nodes) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getType() != NodeType.POW_BLOCK) continue;

			PowerBlockNode n = (PowerBlockNode) nodes.get(i);
			if (n.getValues().length == 1) {
				ValueNode v = n.getValues()[0];
				v.setA((int) Math.pow(v.getA(), n.getM()));
				v.setM(v.getM() * n.getM());
				nodes.set(i, new BlockNode(new ValueNode[] { v }));
				continue;
			}

			if (n.getValues().length == 2) {
				ValueNode v1 = ValueNode.build(n.getValues()[0].getA() * n.getValues()[0].getA(),
						2 * n.getValues()[0].getM());
				ValueNode v2 = ValueNode.build(2 * n.getValues()[0].getA() * n.getValues()[1].getA(),
						n.getValues()[0].getM() + n.getValues()[1].getM());
				ValueNode v3 = ValueNode.build(n.getValues()[1].getA() * n.getValues()[1].getA(),
						2 * n.getValues()[1].getM());
				nodes.set(i, new BlockNode(new ValueNode[] { v1, v2, v3 }));
				continue;
			}

			System.err.println("more than 2 value in power");
			System.exit(-1);
		}
	}

	private static BlockNode blocksMul(BlockNode b1, BlockNode b2) {
		ValueNode[] rc = new ValueNode[b1.getValues().length * b2.getValues().length];
		int pos = 0;
		for (int i = 0; i < b1.getValues().length; i++) {
			for (int j = 0; j < b2.getValues().length; j++) {
				rc[pos++] = nodesMul(b1.getValues()[i], b2.getValues()[j]);
			}
		}

		return new BlockNode(rc);
	}

	private static ValueNode nodesMul(ValueNode n1, ValueNode n2) {
		if (n1.getA() == 0 || n2.getA() == 0) return ValueNode.buildZero();
		return ValueNode.build(n1.getA() * n2.getA(), n1.getM() + n2.getM());
	}

}
