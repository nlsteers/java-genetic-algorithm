
package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;
import com.ec.node.Node;

public class D1 extends Mux6Node {


	public static final byte mask = 4;

	public D1(Node node) {
		this.parent = node;
	}

	@Override
	public boolean eval(int input) {
		return ((input & D1.mask) != 0);
	}

	@Override
	public Node clone(Node parent) {
		return new D1(parent);
	}
}
