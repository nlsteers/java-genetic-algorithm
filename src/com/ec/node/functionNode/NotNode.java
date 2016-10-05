
package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

public class NotNode extends OneChildNode {


	public NotNode() {
		this(null);
	}

	public NotNode(Node parent) {
		this.parent = parent;
		children = new Vector<Node>(1);
	}

	public NotNode(Node parent, Node child) {
		this.parent = parent;
		children = new Vector<Node>(1);
		children.add(0, child);
	}

	@Override
	public Node clone(Node parent) {
		if (parent == null) {
			NotNode notNode = new NotNode();
			notNode.children.add(0, getChild().clone(notNode));
			return notNode;
		} else {
			NotNode notNode = new NotNode(parent);
			notNode.children.add(0, getChild().clone(notNode));
			return notNode;
		}
	}

	@Override
	public boolean eval(int input) {
		return !getChild().eval(input);
	}

	@Override
	public String toString() {
		return "Not[" + getChild().toString() + "]";
	}
}
