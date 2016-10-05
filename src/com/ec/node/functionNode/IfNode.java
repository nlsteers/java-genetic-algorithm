
package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

public class IfNode extends ThreeChildNode {

	public IfNode() {
		this(null);
	}

	public IfNode(Node parent) {
		this.parent = parent;
		children = new Vector<Node>(3);
	}

	public IfNode(Node parent, Node leftChild, Node middleChild, Node rightChild) {
		this.parent = parent;

		children = new Vector<Node>(3);
		children.add(0, leftChild);
		children.add(1, middleChild);
		children.add(2, rightChild);
	}

	@Override
	public Node clone(Node parent) {
		if (parent == null) {
			IfNode ifNode = new IfNode();
			ifNode.children.add(0, getLeftChild().clone(ifNode));
			ifNode.children.add(1, getMiddleChild().clone(ifNode));
			ifNode.children.add(2, getRightChild().clone(ifNode));
			return ifNode;
		} else {
			IfNode ifNode = new IfNode(parent);
			ifNode.children.add(0, getLeftChild().clone(ifNode));
			ifNode.children.add(1, getMiddleChild().clone(ifNode));
			ifNode.children.add(2, getRightChild().clone(ifNode));
			return ifNode;
		}
	}

	@Override
	public boolean eval(int input) {
		return getLeftChild().eval(input) ? getMiddleChild().eval(input)
				: getRightChild().eval(input);
	}

	@Override
	public String toString() {
		return "If[" + getLeftChild().toString() + ","
				+ getMiddleChild().toString() + ","
				+ getRightChild().toString() + "]";
	}

}
