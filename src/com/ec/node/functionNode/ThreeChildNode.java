
package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.Evolution;
import com.ec.node.Node;

public abstract class ThreeChildNode extends Node {

	@Override
	public int countNodes() {
		return getLeftChild().countNodes() + getMiddleChild().countNodes()
				+ getRightChild().countNodes() + 3;
	}

	@Override
	public Vector<Node> enumerate() {
		Vector<Node> v = new Vector<Node>();
		v.add(this);

		Vector<Node> lenum = this.getLeftChild().enumerate();
		if (null != lenum) {
			v.addAll(lenum);
		}

		Vector<Node> menum = this.getMiddleChild().enumerate();
		if (null != menum) {
			v.addAll(menum);
		}

		Vector<Node> renum = this.getRightChild().enumerate();
		if (null != renum) {
			v.addAll(renum);
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
		Vector<Node> lenum = this.getLeftChild().enumBounded(remainingDepth,
				subtreeDepth);
		v.addAll(lenum);
		Vector<Node> menum = this.getMiddleChild().enumBounded(remainingDepth,
				subtreeDepth);
		v.addAll(menum);
		Vector<Node> renum = this.getRightChild().enumBounded(remainingDepth,
				subtreeDepth);
		v.addAll(renum);
		return v;
	}

	@Override
	public int getDepth() {
		return Math.max(getLeftChild().getDepth(), Math.max(getMiddleChild()
				.getDepth(), getRightChild().getDepth())) + 1;
	}


	public Node getLeftChild() {
		return children.get(0);
	}


	public void setLeftChild(Node leftChild) {
		children.set(0, leftChild);
	}

	public Node getMiddleChild() {
		return children.get(1);
	}

	public void setMiddleChild(Node middleChild) {
		children.set(1, middleChild);
	}

	public Node getRightChild() {
		return children.get(2);
	}

	public void setRightChild(Node rightChild) {
		children.set(2, rightChild);
	}

}
