
package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

public class OrNode extends TwoChildNode {

	public OrNode() {
		this(null);
	}

	public OrNode(Node parent) {
		this.parent = parent;
		children = new Vector<Node>(2);
	}


	public OrNode(Node parent, Node leftChild, Node rightChild) {
		this.parent = parent;

		children = new Vector<Node>(2);
		children.add(0, leftChild);
		children.add(1, rightChild);
	}

	@Override
	public Node clone(Node parent) {
		if (parent == null) {
			OrNode orNode = new OrNode();
			orNode.children.add(0, getLeftChild().clone(orNode));
			orNode.children.add(1, getRightChild().clone(orNode));
			return orNode;
		} else {
			OrNode orNode = new OrNode(parent);
			orNode.children.add(0, getLeftChild().clone(orNode));
			orNode.children.add(1, getRightChild().clone(orNode));
			return orNode;
		}
	}

	@Override
	public boolean eval(int input) {
		return getLeftChild().eval(input) || getRightChild().eval(input);
	}

	@Override
	public String toString() {
		return "Or[" + getLeftChild().toString() + ","
				+ getRightChild().toString() + "]";
	}
}
