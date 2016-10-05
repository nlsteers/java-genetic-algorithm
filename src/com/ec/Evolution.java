
package com.ec;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import com.ec.generator.Mux6Generator;
import com.ec.node.Node;

/**
 * Manages the evolution of the 4-to-1 Multiplexer
 */
public class Evolution {

	/**
	 * Generation counter.
	 */
	private int currGen;

	/**
	 * Default population size.
	 */
	private int populationSize = 300;

	/**
	 * Default tree depth.
	 */
	public static final int maxDepth = 6;

	private int evolutionType;

	/**
	 * Crossover probability.
	 */
	private double cxProb = 0.7;

	/**
	 * Mutation probability.
	 */
	private double mtProb = 0.9;


	private static final Random random = new Random();

	private Individual individual;

	public Individual[] population;


	private Mux6Generator mux6;

	public Evolution(int type) {
		this.evolutionType = type;
		this.population = new Individual[populationSize];
	}


	public Evolution(int type, int populationSize) {
		this.evolutionType = type;
		this.populationSize = populationSize;
		this.population = new Individual[populationSize];
	}

	public void evolve() {
		generatePopulation();
        evolveMux6();

	}



	private void generatePopulation() {

			mux6 = new Mux6Generator();
			generateMux6Population();

	}

	private void generateMux6Population() {
		int tDepth1 = maxDepth - 1;
		int tDepth2 = maxDepth - 2;

		try {
			int i = 0;
			while (true) {
				// Grow Tree
				population[i] = mux6.createIndividual(mux6.growTree(maxDepth));
				population[i + 1] = mux6.createIndividual(mux6
						.growTree(tDepth1));
				population[i + 2] = mux6.createIndividual(mux6
						.growTree(tDepth2));

				// Full Tree
				population[i + 3] = mux6.createIndividual(mux6
						.fullTree(maxDepth));
				population[i + 4] = mux6.createIndividual(mux6
						.fullTree(tDepth1));
				population[i + 5] = mux6.createIndividual(mux6
						.fullTree(tDepth2));
				i += 6;
			}
		} catch (IndexOutOfBoundsException iob) {
		}
	}

    private void evolveMux6() {
        individual = getBestIndividual();

        while (individual.getFitness() != 1.0) {
            System.out.println("Generation : " + currGen + " Individual: "
                    + individual.toString());

            Individual[] selectedIndividuals;
            Individual[] newPopulation = new Individual[populationSize];

            for (int i = 0; i < populationSize; i += 2) {

                // Perform selection
                selectedIndividuals = selection();
                Node[] crossovered = probCrossover(
                        selectedIndividuals[0].getNode(),
                        selectedIndividuals[1].getNode());

                newPopulation[i] = mux6
                        .createIndividual(mutate(crossovered[0]));
                newPopulation[i + 1] = mux6
                        .createIndividual(mutate(crossovered[1]));
            }

            population = newPopulation;

            Individual tmp = getBestIndividual();
            if (tmp.getFitness() > individual.getFitness()) {
                individual = tmp;
            }
            currGen++;
        }
        System.out.println("Generation : " + currGen + " Individual: "
                + individual.toString());
    }

	private Node[] probCrossover(Node father, Node mother) {
		Node[] children = { father, mother };

		if (random.nextDouble() > cxProb) {
			children = crossover(father.clone(null), mother.clone(null));
		}
		return children;
	}



	private Node mutate(Node node) {
		if (evolutionType == EvolutionType.MUX6) {
			return probMutate6(node.clone(null));
		} else {
			return node;
		}
	}
    private Node[] crossover(Node father, Node mother) {

        int fd = father.getDepth();
        int md = mother.getDepth();
        if ((fd != 0) && (md != 0)) {
            if (father.getDepth() > mother.getDepth()) {
                Node tmp = father;
                father = mother;
                mother = tmp;
            }
            Vector<Node> fenum = father.enumerate();

            int randomPoint = random.nextInt(fenum.size());
            Node p1 = fenum.get(randomPoint);
            int p1depth = p1.getDepth();
            int depthLeft = maxDepth - p1.getLevel();
            Vector<Node> menum = mother.enumBounded(depthLeft, p1depth);

            randomPoint = random.nextInt(menum.size());
            Node p2 = menum.get(randomPoint);
            int c1 = random.nextInt(p1.children.size());
            int c2 = random.nextInt(p2.children.size());
            Node swapFromF = p1.children.get(c1);
            Node swapFromM = p2.children.get(c2);
            swapFromF.setParent(p2);
            swapFromM.setParent(p1);
            p1.children.set(c1, swapFromM);
            p2.children.set(c2, swapFromF);
            Node[] n = { father, mother };
            return n;
        } else {
            if (father.getDepth() == 0 && mother.getDepth() != 0) {
                Vector<Node> menum = mother.enumerate();
                int randomPoint = random.nextInt(menum.size());
                Node p2 = menum.get(randomPoint);

                int c2 = random.nextInt(p2.children.size());
                Node swapFromM = p2.children.get(c2);
                father.setParent(p2);
                p2.children.set(c2, father);
                father.setParent(p2);
                swapFromM.setParent(null);

                Node[] n = { swapFromM, mother };
                return n;
            } else if (father.getDepth() == 0 && mother.getDepth() != 0) {
                System.out.println("Mother's depth is 0");
                Vector<Node> fenum = father.enumerate();

                int randomPoint = random.nextInt(fenum.size());
                Node p1 = fenum.get(randomPoint);

                int c1 = random.nextInt(p1.children.size());
                Node swapFromF = p1.children.get(c1);
                p1.children.set(c1, mother);
                mother.setParent(p1);
                swapFromF.setParent(null);
                Node[] n = { swapFromF, father };
                return n;
            } else {
                Node[] n = { mother, father };
                return n;
            }
        }
    }



	private Node mutateMux6(Node root) {
		Vector<Node> enumeration = root.enumerate();

		if (enumeration.isEmpty()) {
			return mux6.growTree(maxDepth);
		} else {
			Node nodeToMutate = enumeration.get(random.nextInt(enumeration
					.size()));

			int noOfChildrenMutateNode = nodeToMutate.getChildren().size();
			int depth = random.nextInt(maxDepth - nodeToMutate.getLevel());

			if (noOfChildrenMutateNode == 1) {
				nodeToMutate.children.set(0,
						mux6.recGrowTree(nodeToMutate, depth));
			} else if (noOfChildrenMutateNode == 2) {
				nodeToMutate.children.set(0,
						mux6.recGrowTree(nodeToMutate, depth));
				nodeToMutate.children.set(1,
						mux6.recGrowTree(nodeToMutate, depth));
			} else if (noOfChildrenMutateNode == 3) {
				nodeToMutate.children.set(0,
						mux6.recGrowTree(nodeToMutate, depth));
				nodeToMutate.children.set(1,
						mux6.recGrowTree(nodeToMutate, depth));
				nodeToMutate.children.set(2,
						mux6.recGrowTree(nodeToMutate, depth));
			}

		}

		if (root.getDepth() > 6) {
			System.out.println("ERR: BAD DEPTH");
		}

		return root;
	}

	static class EvolutionType {
		public static final int MUX6 = 0;

	}

    private Node probMutate6(Node node) {
        if (random.nextDouble() > mtProb) {
            return mutateMux6(node);
        } else
            return node;
    }

    private Individual[] selection() {
        Individual[] luckyIndividuals = new Individual[10];

        // add a random individual from the population to the array
        for (int i = 0; i < luckyIndividuals.length; i++) {
            luckyIndividuals[i] = population[random.nextInt(populationSize)];
        }

        // select the two fittest individuals from the array
        Arrays.sort(luckyIndividuals);
        Individual[] selectedIndividuals = { luckyIndividuals[0],
                luckyIndividuals[1] };

        return selectedIndividuals;
    }


    private Individual getBestIndividual() {
        Individual bestIndividual = population[0];

        for (int i = 0; i < populationSize; i++) {
            if (population[i].getFitness() > bestIndividual.getFitness()) {
                bestIndividual = population[i];
            }
        }

        return bestIndividual;
    }
}
