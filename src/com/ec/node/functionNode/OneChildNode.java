
package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.Evolution;
import com.ec.node.Node;

public abstract class OneChildNode extends Node {

	@Override
	public int countNodes() {
		return getChild().countNodes() + 1;
	}

	@Override
	public Vector<Node> enumerate() {
		Vector<Node> v = new Vector<Node>();
		v.add(this);

		Vector<Node> cenum = this.getChild().enumerate();
		if (null != cenum) {
			v.addAll(cenum);
		}

		return v;
	}

	@Override
	public Vector<Node> enumBounded(int remainingDepth, int subtreeDepth) {
		Vector<Node> v = new Vector<Node>();
		int mydepth = this.getDepth();
		int leftSpace = Evolution.maxDepth - this.getLevel();
		if ((mydepth <= remainingDepth) && (leftSpace >= subtreeDepth)) {
			v.add(this);
		}
		Vector<Node> cenum = this.getChild().enumBounded(remainingDepth, subtreeDepth);
		v.addAll(cenum);
		return v;
	}

	@Override
	public int getDepth() {
		return getChild().getDepth() + 1;
	}


	public Node getChild() {
		return children.get(0);
	}


	public void setChild(Node child) {
		children.set(0, child);
	}
}
