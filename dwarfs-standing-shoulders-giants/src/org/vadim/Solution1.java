package org.vadim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * <pre>
 * Input:
 * Line 1: The number N of relationships of influence.
 * N following lines: a relationship of influence between two people in the form of X (whitespace) Y, 
 *    which indicates that X influences Y.
 * The relationships of influence are listed in any order.
 * 
 * Output:
 * The number of people involved in the longest succession of influences.
 * 
 * Constraints
 * 0 < N < 10000
 * 0 < X, Y < 10000
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // the number of relationships of influence

		Node root = null;
		{
			final Set<Integer> childNodes = new HashSet<>();
			final Map<Integer, Node> nodes = new HashMap<>();
			for (int i = 0; i < n; i++) {
				int x = in.nextInt(); // a relationship of influence between two people (x influences y) x -> y
				int y = in.nextInt();
				Node nx = nodes.computeIfAbsent(x, key -> new Node());
				Node ny = nodes.computeIfAbsent(y, key -> new Node());
				nx.down.add(ny);
				childNodes.add(y);
			}

			for (Map.Entry<Integer, Node> en : nodes.entrySet()) {
				if (childNodes.contains(en.getKey())) continue;
				root = en.getValue();
				break;
			}
			childNodes.clear();
			nodes.clear();
		}

		int maxPath = 0;
		List<Node> layer = new ArrayList<>();
		layer.add(root);
		List<Node> tmp = new ArrayList<>();
		while (!layer.isEmpty()) {
			for (Node node : layer) {
				tmp.addAll(node.down);
			}
			layer.clear();
			List<Node> t = tmp;
			tmp = layer;
			layer = t;
			++maxPath;
		}

		System.out.println(maxPath);
	}

	private static class Node {
		List<Node> down = new ArrayList<>(1);
	}
}
