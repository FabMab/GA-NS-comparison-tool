/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m.model;

import java.util.Arrays;
import java.util.ArrayList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


/**
 *
 * @author Fabrice
 */
public class AlgorithmController {

    public static int MAX_GEN = 0;
    public static ArrayList<ArrayList<String>> MODEL;

    public final NumberAxis xAxis = new NumberAxis();
    public final NumberAxis yAxis = new NumberAxis();
    public final LineChart<Number, Number> NSChart = new LineChart<>(xAxis, yAxis);
    public static XYChart.Series NSCseries = new XYChart.Series();
    public static XYChart.Series NSEseries = new XYChart.Series();

    public AlgorithmController() {

    }

        public static void NSController() {
            System.out.println("Initializing job");

            //  create an initial pouplation
            Population myPop = new Population(true);
            Archive myArch = new Archive();
            System.out.println("Algorithm started");
            System.out.println("Generation: 0");
            System.out.println("--------------- ");
            if (Evolver.FITNESS_MEASURE == 1) {
                myPop.calculateCoupling();
            }
            if (Evolver.FITNESS_MEASURE == 2) {
                myPop.calculateElegance();
            }
            myPop.calculateNovelty();
            myArch.saveArchive(0, myPop.getHighestNoveltyScore(), myPop.getHighestNoveltyScore().Sum);
            System.out.println("  ");
            System.out.println(" Novelty score put in archive = " + myPop.noveltyScore);
            System.out.println(" Archive scores afer evolution 0 : " + Arrays.toString(myArch.noveltyscores));
            System.out.println(" Individual put in archive : " + myPop.getHighestNoveltyScore());
            System.out.println(" Archive afer evolution 0 : " + Arrays.toString(myArch.archiveIndividual));
            System.out.println("  ");
            System.out.println("Best Novelty score= " + myArch.getArchiveMaxValue(myArch.noveltyscores));
            System.out.println("  ");

    //      Evolve population for MAX_GEN generations
            for (int i = 1; i < MAX_GEN + 1; i++) {

                System.out.println("Generation: " + i);
                System.out.println("--------------- ");
                System.out.println("  ");

                myPop = Evolver.evolvePopulation(myPop);

                if (i < 20) {
                    myArch.saveArchive(i, myPop.getHighestNoveltyScore(), myPop.getHighestNoveltyScore().Sum);
                } else {
                    int leastFittestIndex = myArch.getLeastFittestIndex();
                    myArch.archiveIndividual[leastFittestIndex] = myArch.saveArchive(leastFittestIndex, myPop.getHighestNoveltyScore(), myPop.getHighestNoveltyScore().Sum);

                }

                if (Evolver.FITNESS_MEASURE == 1) {
                    NSCseries.getData().add(new XYChart.Data(i, myArch.getArchiveMaxValue(myArch.noveltyscores)));
                } else {
                    NSEseries.getData().add(new XYChart.Data(i, myArch.getArchiveMaxValue(myArch.noveltyscores)));
                }

                System.out.println(" Novelty score put in archive = " + myPop.noveltyScore);
                System.out.println(" Archive scores afer evolution " + i + " : " + Arrays.toString(myArch.noveltyscores));
                System.out.println(" Individual put in archive : " + myPop.getHighestNoveltyScore());
                System.out.println(" Archive after evolution " + i + " : " + Arrays.toString(myArch.archiveIndividual));
                System.out.println("  ");
                System.out.println("Best Novelty score= " + myArch.getArchiveMaxValue(myArch.noveltyscores));
                System.out.println("  ");
            }

            System.out.println("Algorithm finished");
            System.out.println("--End of execution---  ");
            System.out.println("  ");
            System.out.println("Final Archive : " + Arrays.toString(myArch.archiveIndividual));
            System.out.println("Best Individual in archive : " + myArch.getArchiveHighestNoveltyScore());
            System.out.println("  ");

            ClassModelController c = new ClassModelController();
            System.out.println("Class model: " + c.classModel(myArch.getArchiveHighestNoveltyScore().getGenotype()));
        }

}
