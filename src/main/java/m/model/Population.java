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
import java.util.List;
import java.util.ArrayList;

public class Population {

    Individual[] individuals;

    int population = Parameters.POP_SIZE;

    double fittest = 0.0;
    double secondFittest = 0.0;
    double leastFittest = 0.0;
    double eleganceFittest = 0.0;
    double eleganceSecondFittest = 0.0;
    double distance = 0.0;
    double sum = 0.0;
    double noveltyScore = 0;
    double secondHighest = 0.0;
    public double score = 0.0;
    int sumCount;
    public int index;

    private static List<Double> scores;

    //Create population 
    public Population(boolean initialise) {

        individuals = new Individual[population];

        //initialise population 
        if (initialise) {
            //loop and create individuals

            for (int i = 0; i < individuals.length; i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }

        }

    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public void setPopulation(Individual[] newPop) {

        System.arraycopy(newPop, 0, this.individuals, 0, Parameters.POP_SIZE);
    }

    public Individual[] getPopulation() {
        return this.individuals;
    }

    //Calculate  objective fitness of each individual in population
    public void calculateCoupling() {

        for (int i = 0; i < individuals.length; i++) {
            individuals[i].evaluateCoupling();
        }
    }
    //Calculate elegance characterisation of each individual in population

    public void calculateElegance() {

        for (int i = 0; i < individuals.length; i++) {
            individuals[i].evaluateElegance();
        }
        getEleganceFittest();
    }

    //Get the fittest individual
    public Individual getFittest() {
        double maxFit = individuals[0].fitness;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].fitness) {
                maxFit = individuals[i].fitness;
                maxFitIndex = i;
            }
        }

        fittest = individuals[maxFitIndex].fitness;
        System.out.println(fittest);
        return individuals[maxFitIndex];

    }

    //Get the second most fittest individual
    public Individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].fitness > individuals[maxFit1].fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].fitness > individuals[maxFit2].fitness) {
                maxFit2 = i;
            }
        }

        secondFittest = individuals[maxFit2].fitness;
//        System.out.println(secondFittest);

        return individuals[maxFit2];

    }

    public double getFittestValue() {
        fittest = getFittest().fitness;
        return fittest;
    }

    public Individual getEleganceFittest() {
        double minFit = individuals[0].fitness;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFit > individuals[i].fitness) {
                minFit = individuals[i].fitness;
                minFitIndex = i;
            }
        }

        eleganceFittest = individuals[minFitIndex].fitness;
        return individuals[minFitIndex];
    }

    public Individual getEleganceSecondFittest() {
        int minFit1 = 0;
        int minFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].fitness < individuals[minFit1].fitness) {
                minFit2 = minFit1;
                minFit1 = i;
            } else if (individuals[i].fitness < individuals[minFit2].fitness) {
                minFit2 = i;
            }
        }

        eleganceSecondFittest = individuals[minFit2].fitness;
        return individuals[minFit2];

    }

    //Get index of least fittest individual
    public int getLeastFittestIndex() {
        double minFitVal = individuals[0].Sum;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFitVal >= individuals[i].Sum) {
                minFitVal = individuals[i].Sum;
                minFitIndex = i;
            }
        }
        leastFittest = individuals[minFitIndex].Sum;
        System.out.println(minFitIndex);
        System.out.println(leastFittest);

        return minFitIndex;
    }

    //Get individual with highest novelty score
    public Individual getHighestNoveltyScore() {
        double maxFit = individuals[0].Sum;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].Sum) {
                maxFit = individuals[i].Sum;
                maxFitIndex = i;
            }
        }

        noveltyScore = individuals[maxFitIndex].Sum;

        return individuals[maxFitIndex];
    }

    //Get the individual with second highest noveltyscore
    public Individual getSecondHighestNoveltyScore() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].Sum > individuals[maxFit1].Sum) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].Sum > individuals[maxFit2].Sum) {
                maxFit2 = i;
            }
        }

        secondHighest = individuals[maxFit2].Sum;
        return individuals[maxFit2];
    }

    public double calculateSumOfDistances(int index) {
        sumCount = 0;
        double genome = individuals[index].fitness;

        sum = 0;

        for (int i = 0; i < individuals.length; i++) {
            index = i;

            if (genome < individuals[i].fitness) {
                distance = individuals[i].fitness - genome;
                sum = sum + distance;
                sumCount++;

            } else if (genome > individuals[i].fitness) {
                distance = genome - individuals[i].fitness;
                sum = sum + distance;
                sumCount++;
            }
        }
        return sum;
    }

    public void calculateNovelty() {

        scores = new ArrayList<Double>();

        for (int i = 0; i < individuals.length; i++) {
            individuals[i].Sum = score;
            score = calculateSumOfDistances(i) / (double) sumCount;
            scores.add(score);
        }
    }

    public int size() {
        return individuals.length;
    }

    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }

}
