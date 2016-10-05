
package com.ec;

import com.ec.Evolution.EvolutionType;

public class Main {

	public static void main(String args[]) {
		Evolution evolution = new Evolution(EvolutionType.MUX6, 400);
		evolution.evolve();
	}
}