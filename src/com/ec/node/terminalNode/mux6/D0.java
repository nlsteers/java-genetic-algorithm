
package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;
import com.ec.node.Node;


public class D0 extends Mux6Node {


	public static final byte mask = 8;

	public D0(Node node) {
		this.parent = node;
	}

	@Override
	public boolean eval(int input) {
		return ((input & D0.mask) != 0);
	}

	@Override
	public Node clone(Node parent) {
		return new D0(parent);
	}
}
