
package com.ec.generator;

import java.util.Random;

import com.ec.Individual;
import com.ec.node.Node;
import com.ec.node.functionNode.AndNode;
import com.ec.node.functionNode.IfNode;
import com.ec.node.functionNode.NotNode;
import com.ec.node.functionNode.OrNode;

public abstract class Generator {

	/**
	 * Default probability for stopping growth of tree
	 */
	protected static double growStopProbability = 0.25;

	protected static final int FULLTREE = 4;

	protected static final int GROWTREE = 5;

	protected static Random random = new Random();

	protected abstract Node getRandomTerminal(Node parent);

	protected abstract double fitness(Node node);

	protected Node generateRoot(int range) {
		switch (random.nextInt(range)) {
		case 0:
			return new NotNode();
		case 1:
			return new OrNode();
		case 2:
			return new AndNode();
		case 3:
			return new IfNode();
		case 4:
			return getRandomTerminal(null);
		default:
			return null;
		}
	}

	public Node fullTree(int depth) {
		Node root = generateRoot(Generator.FULLTREE);

		int rootCapacity = root.getChildren().capacity();
		if (rootCapacity == 1) {
			root.children.add(0, fillTree(root, depth - 1));
		} else if (rootCapacity == 2) {
			root.children.add(0, fillTree(root, depth - 1));
			root.children.add(1, fillTree(root, depth - 1));
		} else if (rootCapacity == 3) {
			root.children.add(0, fillTree(root, depth - 1));
			root.children.add(1, fillTree(root, depth - 1));
			root.children.add(2, fillTree(root, depth - 1));
		}
		return root;
	}
	public Node fillTree(Node parent, int depth) {
		if (depth > 0) {
			switch (random.nextInt(Generator.FULLTREE)) {
			case 0:
				NotNode node = new NotNode(parent);
				node.children.add(fillTree(node, depth - 1));
				return node;
			case 1:
				OrNode orNode = new OrNode(parent);
				orNode.children.add(0, fillTree(orNode, depth - 1));
				orNode.children.add(1, fillTree(orNode, depth - 1));
				return orNode;
			case 2:
				AndNode andNode = new AndNode(parent);
				andNode.children.add(0, fillTree(andNode, depth - 1));
				andNode.children.add(1, fillTree(andNode, depth - 1));
				return andNode;
			case 3:
				IfNode ifNode = new IfNode(parent);
				ifNode.children.add(0, fillTree(ifNode, depth - 1));
				ifNode.children.add(1, fillTree(ifNode, depth - 1));
				ifNode.children.add(2, fillTree(ifNode, depth - 1));
				return ifNode;
			default: // This should NEVER happen!!
				return null;
			}
		} else {
			return getRandomTerminal(parent);
		}
	}

	public Node growTree(int depth) {
		Node root = generateRoot(Generator.GROWTREE);

		if (root.getChildren() == null) {
			return root;
		} else {
			int childSize = root.getChildren().capacity();

			if (childSize == 1) {
				root.children.add(0, recGrowTree(root, depth - 1));
			} else if (childSize == 2) {
				root.children.add(0, recGrowTree(root, depth - 1));
				root.children.add(1, recGrowTree(root, depth - 1));
			} else if (childSize == 3) {
				root.children.add(0, recGrowTree(root, depth - 1));
				root.children.add(1, recGrowTree(root, depth - 1));
				root.children.add(2, recGrowTree(root, depth - 1));
			}
			return root;
		}
	}

	public Node recGrowTree(Node parent, int depth) {
		if (depth > 0) {
			switch (random.nextInt(4)) {
			case 0:
				if (Generator.growStopProbability < random.nextFloat()) {
					NotNode node = new NotNode(parent);
					node.children.add(recGrowTree(node, depth - 1));
					return node;
				} else {
					return getRandomTerminal(parent);
				}
			case 1:
				if (Generator.growStopProbability < random.nextFloat()) {
					OrNode orNode = new OrNode(parent);
					orNode.children.add(0, recGrowTree(orNode, depth - 1));
					orNode.children.add(1, recGrowTree(orNode, depth - 1));
					return orNode;
				} else {
					return getRandomTerminal(parent);
				}
			case 2:
				if (Generator.growStopProbability < random.nextFloat()) {
					AndNode andNode = new AndNode(parent);
					andNode.children.add(0, recGrowTree(andNode, depth - 1));
					andNode.children.add(1, recGrowTree(andNode, depth - 1));
					return andNode;
				} else {
					return getRandomTerminal(parent);
				}
			case 3:
				if (Generator.growStopProbability < random.nextFloat()) {
					IfNode ifNode = new IfNode(parent);
					ifNode.children.add(0, recGrowTree(ifNode, depth - 1));
					ifNode.children.add(1, recGrowTree(ifNode, depth - 1));
					ifNode.children.add(2, recGrowTree(ifNode, depth - 1));
					return ifNode;
				} else {
					return getRandomTerminal(parent);
				}
			default:
				return null;
			}
		} else {
			return getRandomTerminal(parent);
		}
	}

	public Individual createIndividual(Node root) {
		return new Individual(root, fitness(root));
	}

}
