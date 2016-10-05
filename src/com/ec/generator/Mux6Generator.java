
package com.ec.generator;

import com.ec.node.Node;
import com.ec.node.terminalNode.mux6.*;

public class Mux6Generator extends Generator {

	private static final int resMask = 8;

	private static final int addrShift = 4;


	private static final boolean[] correctAnswer = getCorrectAnswer();

	@Override
	public Node getRandomTerminal(Node root) {
		switch (random.nextInt(6)) {
		case 0:
			return new A0(root);
		case 1:
			return new A1(root);
		case 2:
			return new D0(root);
		case 3:
			return new D1(root);
		case 4:
			return new D2(root);
		case 5:
			return new D3(root);
		default:
			return null;
		}
	}

	@Override
	protected double fitness(Node node) {
		int count = 0;

		for (int i = 0; i < correctAnswer.length; i++) {
			if (correctAnswer[i] == node.eval(i)) {
				count++;
			}
		}
		return count / 64d;
	}


	private static boolean[] getCorrectAnswer() {
		boolean[] arr = new boolean[64];
		for (int i = 0; i < 64; i++) {
			arr[i] = computeAnswer(i);
		}
		return arr;
	}

	private static boolean computeAnswer(int input) {
		int addr = input >> addrShift;
		int invaddr = 3 - addr;
		int resmask = resMask >> addr;
		int val = input & resmask;
		int res = val >> invaddr;
		return res != 0;
	}
}
