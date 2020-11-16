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
public class Archive {

    Individual[] archiveIndividual;
    int archiveSize = 20;
    double leastFittest = 0.0;
    double Score = 0;
    double noveltyScore = 0;
    public double[] noveltyscores;

    public int[] genotype = new int[Parameters.GENOTYPE_LENGTH];

        public Archive() {

            archiveIndividual = new Individual[archiveSize];
            noveltyscores = new double[archiveSize];

        }

        public int[] getGenotype() {
            return genotype;
        }

            public Individual saveArchive(int index, Individual indiv, double score) {

                    archiveIndividual[index] = indiv;
                    archiveIndividual[index].setNoveltyFitness(score);

                    noveltyscores[index] = score;
                    archiveIndividual[index].Sum = score;
                    Score = score;

                return indiv;
            }
                // Best novelty score in archive when archive.size < 20  

                public double getArchiveMaxValue(double[] scores) {

                    double maxValue = scores[0];
                    for (int i = 1; i < noveltyscores.length; i++) {
                        if (scores[i] > maxValue) {
                            maxValue = scores[i];
                        }
                    }
                    return maxValue;
                }

                        //Best novelty score when archive.size > 20
                        public Individual getArchiveHighestNoveltyScore() {
                            double maxFit = archiveIndividual[0].getNoveltyFitness();
                            int maxFitIndex = 0;
                            for (int i = 0; i < archiveIndividual.length; i++) {
                                if (maxFit <= archiveIndividual[i].getNoveltyFitness()) {
                                    maxFit = archiveIndividual[i].getNoveltyFitness();
                                    maxFitIndex = i;
                                }
                            }

                            noveltyScore = archiveIndividual[maxFitIndex].getNoveltyFitness();
                            return archiveIndividual[maxFitIndex];
                        }
        //Get index of least fittest individual

        public int getLeastFittestIndex() {
            double minFitVal = archiveIndividual[0].getNoveltyFitness();
            int minFitIndex = 0;
            for (int i = 0; i < archiveIndividual.length; i++) {

                if (minFitVal > archiveIndividual[i].getNoveltyFitness()) {
                    minFitVal = archiveIndividual[i].getNoveltyFitness();
                    minFitIndex = i;
                }
            }
            leastFittest = archiveIndividual[minFitIndex].getNoveltyFitness();

            return minFitIndex;
        }

}
