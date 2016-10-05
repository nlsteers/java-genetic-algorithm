
package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;
import com.ec.node.Node;


public class A0 extends Mux6Node {


	public static final byte mask = 32;


	public A0(Node parent) {
		this.parent = parent;
	}

	@Override
	public boolean eval(int input) {
		return ((input & A0.mask) != 0);
	}

	@Override
	public Node clone(Node parent) {
		return new A0(parent);
	}
}
