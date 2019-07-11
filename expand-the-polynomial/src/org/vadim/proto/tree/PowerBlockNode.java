package org.vadim.proto.tree;

public class PowerBlockNode extends BlockNode {
	private int m;

	public PowerBlockNode() {
		super();
	}

	public PowerBlockNode(ValueNode[] values, int m) {
		super(values);
		this.m = m;
	}
	
	public int getM() {
		return m;
	}
	
	@Override
	public NodeType getType() {
		return NodeType.POW_BLOCK;
	}
}
