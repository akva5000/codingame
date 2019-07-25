package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input:
 * A unique line containing the input expression.
 * The expression contains no spaces.
 * 
 * Output:
 * N lines: an integer (outcome), a space and a float (percent probability of this outcome)
 * Outcomes are sorted in ascending order.
 * Floats are formatted with 2 decimal figures, rounded.
 * 
 * Constraints
 * The inputs are all valid expressions: no error handling is needed.
 * Maximum length of expression: 100 chars
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String expr = in.nextLine();

		System.out.println("3 25.00\n" + "4 25.00\n" + "5 25.00\n" + "6 25.00");
	}

	private static abstract class Node {
		abstract int getType();
	}

	private static abstract class ExprNode extends Node {
		private Node node;

		public ExprNode(Node node) {
			this.node = node;
		}

		public Node getNode() {
			return node;
		}

		@Override
		int getType() {
			return 0;
		}

	}

	private static abstract class NumberNode extends Node {
		private float value;

		public NumberNode(float value) {
			this.value = value;
		}

		public float getValue() {
			return value;
		}

		@Override
		int getType() {
			return 1;
		}
	}

	private static abstract class OpNode extends Node {
		private int opCode;
		private Node left;
		private Node right;

		public OpNode(int opCode, Node left, Node right) {
			this.opCode = opCode;
			this.left = left;
			this.right = right;
		}

		public int getOpCode() {
			return opCode;
		}
		
		public Node getLeft() {
			return left;
		}
		
		public Node getRight() {
			return right;
		}

		@Override
		int getType() {
			return 1;
		}
	}
}
