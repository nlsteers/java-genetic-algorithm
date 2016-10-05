
package com.ec;

import com.ec.node.Node;

public class Individual implements Comparable<Individual> {


	private Node node;

	private double fitness;


	public Individual(Node node, double fitness) {
		this.node = node;
		this.fitness = fitness;
	}

	public Node getNode() {
		return node;
	}

	public double getFitness() {
		return fitness;
	}


	public void setNode(Node node) {
		this.node = node;
	}


	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	@Override
	public int compareTo(Individual individual) {
		return Double.compare(individual.fitness, this.fitness);
	}

	@Override
	public String toString() {
		return fitness + " : " + node.toString();
	}
}
