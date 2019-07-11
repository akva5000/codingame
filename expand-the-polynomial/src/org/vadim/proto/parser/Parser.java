package org.vadim.proto.parser;

import java.util.ArrayList;
import java.util.List;

import org.vadim.proto.tree.BlockNode;
import org.vadim.proto.tree.PowerBlockNode;
import org.vadim.proto.tree.ValueNode;

public class Parser {

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
}
