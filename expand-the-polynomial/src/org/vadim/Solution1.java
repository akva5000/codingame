package org.vadim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Input
 * A partially or not factorized polynomial.
 * 
 * Output
 * The expanded polynomial written in the standard way:
 * * x^1 is written x
 * * 1x^3 is written x^3
 * * 0x^2 and x^0 are not written
 * 
 * Constraints
 * All the coefficients are integers (positive, null or negative).
 * All coefficients are in decreasing order: (x^3 then x^2 then x^1 then x^0)
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String poly = in.next();
		List<ValueNode> rc = optimize(parse(poly));
		System.out.print(rc.get(0).toNoSigString());
		for (int i = 1; i < rc.size(); i++) {
			System.out.print(rc.get(i));
		}
		System.out.println();
	}

	/**
	 * Nodes definition
	 */
	public static enum NodeType {
		EXPR, BLOCK, POW_BLOCK, VALUE;
	}

	public static abstract class Node {
		public abstract NodeType getType();
	}

	public static class BlockNode extends Node {
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
			for (ValueNode n : values) {
				buf.append(n.toString()).append(' ');
			}
			return buf.toString();
		}
	}

	public static final class PowerBlockNode extends BlockNode {
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

	public static final class ValueNode extends Node {
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

	/**
	 * Parser
	 */
	public static List<BlockNode> parse(String text) {
		ParseContext ctx = new ParseContext();
		ctx.pos = 0;
		ctx.text = text;

		ArrayList<BlockNode> nodes = new ArrayList<>(10);
		while (ctx.pos < ctx.text.length()) {
			nodes.add(parseBlock(ctx));
		}

		return nodes;
	}

	private static BlockNode parseBlock(ParseContext ctx) {
		if (ctx.text.charAt(ctx.pos) == '*') ++ctx.pos;
		if (ctx.text.charAt(ctx.pos) != '(') return new BlockNode(new ValueNode[] { parseValue(ctx) });

		++ctx.pos;
		BlockNode block = new BlockNode();
		while (ctx.pos < ctx.text.length()) {
			char ch = ctx.text.charAt(ctx.pos);
			if (ch == ')') {
				++ctx.pos;
				break;
			}
			block.addValue(parseValue(ctx));
		}

		if (ctx.pos < ctx.text.length() && ctx.text.charAt(ctx.pos) == '^') {
			++ctx.pos;
			// power block
			int m = parseNumber(ctx);
			if (m == 0) return new BlockNode(new ValueNode[] { ValueNode.build(1, 0) });
			if (m > 1) return new PowerBlockNode(block.getValues(), m);
		}

		return block;
	}

	private static ValueNode parseValue(ParseContext ctx) {
		int sig = 0;
		int a = 0;
		int m = 0;
		if (ctx.text.charAt(ctx.pos) == '-') {
			sig = 1;
			++ctx.pos; // skip -
		} else if (ctx.text.charAt(ctx.pos) == '+') {
			++ctx.pos; // skip +
		}

		if (ctx.text.charAt(ctx.pos) >= '0' && ctx.text.charAt(ctx.pos) <= '9') { // parse number
			a = parseNumber(ctx);
		} else {
			a = 1;
		}

		// parse x^m
		if (ctx.pos < ctx.text.length() && ctx.text.charAt(ctx.pos) == 'x') {
			++ctx.pos;
			if (ctx.pos < ctx.text.length() && ctx.text.charAt(ctx.pos) == '^') {
				++ctx.pos;
				m = parseNumber(ctx);
			} else {
				m = 1;
			}
		}

		if (sig == 1) a = -a;
		return ValueNode.build(a, m);
	}

	private static int parseNumber(ParseContext ctx) {
		int p = ctx.pos;
		while (p < ctx.text.length() && ctx.text.charAt(p) >= '0' && ctx.text.charAt(p) <= '9') ++p;
		int a = Integer.valueOf(ctx.text.substring(ctx.pos, p));
		ctx.pos = p;
		return a;
	}

	private static class ParseContext {
		public int pos = 0;
		public String text;
	}

	/**
	 * expression Optimizer
	 */
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
				ValueNode v1 = ValueNode.build(n.getValues()[0].getA() * n.getValues()[0].getA(), 2 * n.getValues()[0].getM());
				ValueNode v2 = ValueNode.build(2 * n.getValues()[0].getA() * n.getValues()[1].getA(),
						n.getValues()[0].getM() + n.getValues()[1].getM());
				ValueNode v3 = ValueNode.build(n.getValues()[1].getA() * n.getValues()[1].getA(), 2 * n.getValues()[1].getM());
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
