/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m.model;


import java.util.Random;

/**
 *
 * @author Fabrice
 */
public class Individual {

    private UseMatrix useMatrix;

    private Utility utility;

    public int[] genotype = new int[Parameters.GENOTYPE_LENGTH];

    public double fitness; 
    
    public double Sum;
    
    public double Score;
    
     public boolean verbose;

    public Individual() {

     useMatrix = new UseMatrix();
     useMatrix.setUp(); 
    }   

    // Create a random individual
    public void generateIndividual() {
        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genotype.length; i++) {
            int gene = rn.nextInt(5) + 1;
            genotype[i] = gene;
        }
    }

    public int getGene(int index) {
        return genotype[index];
    }

    public void setGene(int index, int gene) {
        this.genotype[index] = gene;
    }

    public int size() {
        return genotype.length;
    }

    public int[] getGenotype() {
        return genotype;
    }

            public void randGenes()
            {
                Random rand = new Random();
                for (int i = 0; i < Parameters.GENOTYPE_LENGTH; ++i) {
                    this.setGene(i, rand.nextInt(2));
                }
            }
            public void setNoveltyFitness(double fitness)
            {
             this.Score = fitness;
            }

            public double getNoveltyFitness()
            {
		return this.Score;
	     }
            @Override
            public String toString()
            {
                String geneString = "";
                for (int i = 0; i < genotype.length; i++) {
                    geneString += getGene(i);
                }
                return geneString;
            }

    public void evaluateCoupling() {

//  -------calculate coupling fitness of an individual------------------------ 
        double objectiveCouplingFitness = 0.0;
        int externalCoupleCount = 0;

        for (int i = 0; i < useMatrix.NUMBER_OF_METHODS; i++) {
            for (int j = 0; j < useMatrix.NUMBER_OF_ATTRIBUTES; j++) {
                if (genotype[i] != genotype[j + useMatrix.NUMBER_OF_METHODS]
                        && useMatrix.uses[i][j] == true) {  // 'Out of class' or external couple

                    externalCoupleCount++;
                }
            }
        }

//pre-conditions
        assert externalCoupleCount > 0 : "prevent didvision of zero error";

        assert externalCoupleCount <= useMatrix.NUMBER_OF_USES : "external couple count error with couple count of  " + externalCoupleCount;

        objectiveCouplingFitness = externalCoupleCount / (double) useMatrix.NUMBER_OF_USES;
        fitness = objectiveCouplingFitness;
        
//post-conditions
        assert objectiveCouplingFitness <= 1.0 : "objective coupling value is " + objectiveCouplingFitness;
        assert objectiveCouplingFitness >= 0.0 : "objective coupling value is " + objectiveCouplingFitness;

    }
            // ------calculate elegance characterisation of an individual---------------

                public void evaluateElegance() {

                 double eleganceCharacterisation = 0.0;
                 verbose=false;

                    assert genotype != null : "genotype null";

                    int[] classSizes = new int[useMatrix.NUMBER_OF_CLASSES];

                    for (int i = 0; i < classSizes.length; i++) {
                        classSizes[i] = 0;
                    }

                    for (int i = 0; i < (useMatrix.NUMBER_OF_METHODS + useMatrix.NUMBER_OF_ATTRIBUTES); i++) {
                        assert genotype[i] <= classSizes.length : "invalid genotype value: :" + genotype[i];

                        classSizes[genotype[i] - 1]++;
                    }

//pre-condition
        assert classSizes != null;

        eleganceCharacterisation = utility.StandardDeviation(classSizes);
        fitness = eleganceCharacterisation;
//post-condition
        assert eleganceCharacterisation >= 0.0 :
                "elegance characterisation value is " + eleganceCharacterisation;
    }
       

}
