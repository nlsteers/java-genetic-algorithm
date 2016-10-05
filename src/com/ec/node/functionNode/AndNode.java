
package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

public class AndNode extends TwoChildNode {

	public AndNode() {
		this(null);
	}


	public AndNode(Node parent) {
		this.parent = parent;
		children = new Vector<Node>(2);
	}

	public AndNode(Node parent, Node leftChild, Node rightChild) {
		this.parent = parent;

		children = new Vector<Node>(2);
		children.add(0, leftChild);
		children.add(1, rightChild);
	}

	@Override
	public Node clone(Node parent) {
		if (parent == null) {
			AndNode andNode = new AndNode();
			andNode.children.add(0, getLeftChild().clone(andNode));
			andNode.children.add(1, getRightChild().clone(andNode));
			return andNode;
		} else {
			AndNode andNode = new AndNode(parent);
			andNode.children.add(0, getLeftChild().clone(andNode));
			andNode.children.add(1, getRightChild().clone(andNode));
			return andNode;
		}
	}

	@Override
	public boolean eval(int input) {
		return getLeftChild().eval(input) && getRightChild().eval(input);
	}

	@Override
	public String toString() {
		return "And[" + getLeftChild().toString() + ","
				+ getRightChild().toString() + "]";
	}

}
