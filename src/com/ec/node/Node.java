
package com.ec.node;

import java.util.Vector;

public abstract class Node {


	public Node parent;

	public Vector<Node> children;

	public abstract int getDepth();

	public abstract boolean eval(int input);

	public abstract Vector<Node> enumerate();

	public abstract Vector<Node> enumBounded(int remainingDepth,
			int subtreeDepth);

	public abstract int countNodes();

	public abstract Node clone(Node parent);

	public int getLevel() {
		if (parent == null)
			return 0;
		else
			return parent.getLevel() + 1;
	}

	public Vector<Node> getChildren() {
		return children;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}
