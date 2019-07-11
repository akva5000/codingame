package org.vadim.proto.tree;

import java.util.Arrays;

public class BlockNode extends Node {
	protected ValueNode[] values;

	public BlockNode() {
		values = new ValueNode[0];
	}

	public BlockNode(ValueNode[] values) {
		this.values = values;
	}

	public void addValue(ValueNode value) {
		this.values = Arrays.copyOf(this.values, this.values.length + 1);
		this.values[this.values.length - 1] = value;
	}

	public ValueNode[] getValues() {
		return values;
	}

	@Override
	public NodeType getType() {
		return NodeType.BLOCK;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		for(ValueNode n : values) {
			buf.append(n.toString()).append(' ');
		}
		return buf.toString();
	}
}
