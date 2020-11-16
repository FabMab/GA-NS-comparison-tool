/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m.model;


/**
 *
 * @author Fabrice
 */
public class Evolver {

    private static final boolean elitism = true;
    
   
    public final static int POP_SIZE= 100;
    public final static int GENOTYPE_LENGTH= 31;
    public final static int NUMBER_OF_CLASSES= 5;
    public static double CROSSOVER_RATE= 0;
    public static double MUTATION_RATE = 0;
    public final static int COUPLING=1;
    public final static int ELEGANCE=2;
    public static int FITNESS_MEASURE;

    public int[] genotype = new int[GENOTYPE_LENGTH];

    public Evolver() {

    }
// Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(false);

        if (elitism) {
            newPopulation.saveIndividual(0, pop.getHighestNoveltyScore());
        }
            // Crossover population
            int elitismOffset;
            if (elitism) {
                elitismOffset = 1;
            } else {
                elitismOffset = 0;
            }
        // Loop over the population size and create new individuals with crossover
        for (int i = elitismOffset; i < POP_SIZE; i++) {
            Individual indiv1 = pop.getHighestNoveltyScore();
            Individual indiv2 = pop.getSecondHighestNoveltyScore();
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv); 
        }
        // Mutate population
            for (int i = elitismOffset; i < newPopulation.size(); i++) {
                mutate(newPopulation.getIndividual(i));
            }
        //   Calculate coupling ,elegance fitness measures and novelty scores of new population 
                if (FITNESS_MEASURE==COUPLING){
                newPopulation.calculateCoupling();
                }
                    if (FITNESS_MEASURE==ELEGANCE){
                    newPopulation.calculateElegance();    
                    }
                       newPopulation.calculateNovelty();

                return newPopulation;
            }
 
    // Crossover individuals
    public static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual crossedOver = new Individual();
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= CROSSOVER_RATE) {
                crossedOver.setGene(i, indiv1.getGene(i));
            } else {
                crossedOver.setGene(i, indiv2.getGene(i));
            }
        }
        return crossedOver;
    }

    // Mutate a tour using swap mutation
    public static void mutate(Individual mutated) {
        // Loop through elements
        for (int pos1 = 0; pos1 < mutated.size(); pos1++) {
            // Apply mutation rate
            if (Math.random() < MUTATION_RATE) {
                // Get a second random element
                int pos2 = (int) (mutated.size() * Math.random());
               // Get genes fom mutated individualat target position
                int gene1 = mutated.getGene(pos1);
                int gene2 = mutated.getGene(pos2);
                // Swap them around
                mutated.setGene(pos2, gene1);
                mutated.setGene(pos1, gene2);
            }
        }
    }
}
