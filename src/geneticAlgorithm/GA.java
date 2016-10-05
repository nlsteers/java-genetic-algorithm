package geneticAlgorithm;

/**
 * Created by nsteers on 20/03/2014.
 */
public class GA {

        public static void main(String[] args) {

            // Set candidate solution
            int solution;
            int population;

            System.out.println("Set the Solution");
            System.out.println("Set the Population");


            FitnessCalc.setSolution("1111000000000000000010001000000010000000010111000000100000001111");

            // Create initial population
            Population myPop = new Population(50, true);

            // Evolve population until optimum solution
            int generationCount = 0;
            while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
                generationCount++;
                System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
                myPop = Algorithm.evolvePopulation(myPop);
            }
            System.out.println("Solution found!");
            System.out.println("Generation: " + generationCount);
            System.out.println("Genes:");
            System.out.println(myPop.getFittest());

        }
    }
