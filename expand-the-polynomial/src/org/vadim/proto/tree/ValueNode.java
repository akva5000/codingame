package org.vadim.proto.tree;

/**
 * value = A*x^M
 * 
 * @author akva
 */
public class ValueNode extends Node {
	private int a;
	private int m;

	public static ValueNode buildZero() {
		return new ValueNode(0, 0);
	}

	public static ValueNode build(int a, int m) {
		return new ValueNode(a, m);
	}

	public ValueNode() {
	}

	private ValueNode(int a, int m) {
		this.a = a;
		this.m = m;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public void changeSig() {
		a = -a;
	}

	@Override
	public NodeType getType() {
		return NodeType.VALUE;
	}

	public String toNoSigString() {
		if (a == 0) return "";
		StringBuilder buf = new StringBuilder(10);
		if ((a == -1 || a == 1) && m != 0) {
			if (a == -1) buf.append('-');
		} else buf.append(a);
		if (m != 0) buf.append('x');
		if (m > 1) buf.append('^').append(m);
		return buf.toString();

	}

	@Override
	public String toString() {
		if (a == 0) return "";
		StringBuilder buf = new StringBuilder(10);
		if (a > 0) buf.append('+');
		if ((a == -1 || a == 1) && m != 0) {
			if (a == -1) buf.append('-');
		} else buf.append(a);
		if (m != 0) buf.append('x');
		if (m > 1) buf.append('^').append(m);
		return buf.toString();
	}

}
