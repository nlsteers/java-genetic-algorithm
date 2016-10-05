package com.ec.node;

import java.util.Vector;

public abstract class Mux6Node extends Node {

	public static final byte mask = 48;

	@Override
	public int countNodes() {
		return 0;
	}

	@Override
	public Vector<Node> enumerate() {
		return new Vector<Node>(0);
	}

	@Override
	public Vector<Node> enumBounded(int remainingLevels, int incomingDepth) {
		return new Vector<Node>(0);
	}

	@Override
	public int getDepth() {
		return 0;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
