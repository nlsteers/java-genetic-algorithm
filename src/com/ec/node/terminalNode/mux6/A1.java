
package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;
import com.ec.node.Node;


public class A1 extends Mux6Node {

	public static final byte mask = 16;


	public A1(Node node) {
		this.parent = node;
	}

	@Override
	public boolean eval(int input) {
		return ((input & A1.mask) != 0);
	}

	@Override
	public Node clone(Node parent) {
		return new A1(parent);
	}
}
