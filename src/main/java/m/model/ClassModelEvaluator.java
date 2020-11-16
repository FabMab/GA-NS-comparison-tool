 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m.model;

import java.util.Comparator;

import net.sf.jclec.IFitness;
import net.sf.jclec.intarray.IntArrayIndividual;
import net.sf.jclec.IIndividual;
import net.sf.jclec.base.AbstractEvaluator;
import net.sf.jclec.IConfigure;
import net.sf.jclec.fitness.SimpleValueFitness;
import net.sf.jclec.fitness.ValueFitnessComparator;
import org.apache.commons.configuration.Configuration;


/**
 *
 * 
 */
public class ClassModelEvaluator extends AbstractEvaluator implements IConfigure {

   public static final int COUPLING =1;
   public static final int ELEGANCE =2; 
   public static int FITNESS_MEASURE;
  
    
    
    protected boolean maximize = false;
  
    private Comparator<IFitness> COMPARATOR;
    
   private UseMatrix useMatrix;

    
 public ClassModelEvaluator()
   {
            super(); 
    
   }
 
 /**  @return true if evaluator is used to maximise function, false
	otherwise. */
	public boolean isMaximize()
	{
		return maximize;
	}
	
	/** Set the maximise flag.
	* @param maximize Actual maximise flag. */
	public void setMaximize(boolean maximize)
	{
		this.maximize = maximize;
	}
     
        
@Override
    protected void evaluate(IIndividual ind) {

        
// Individual genotype
       
      int[] genotype = ((IntArrayIndividual) ind).getGenotype();
        
        assert genotype != null : "genotype null";

        
        double objectiveCouplingFitness = 0.0;
        
        
//   objective coupling fitness 
int externalCoupleCount = 0;

    
        for (int i = 0; i < UseMatrix.NUMBER_OF_METHODS; i++)
        {
            for (int j = 0; j < UseMatrix.NUMBER_OF_ATTRIBUTES; j++)
            {
//          
                if (genotype[i] != genotype[ j + UseMatrix.NUMBER_OF_METHODS ]
                         && useMatrix.uses[i][j] == true ) 
                
                {  // 'Out of class' or external couple
                      externalCoupleCount++;
                }
            }  
          
        }

//pre-conditions
        assert externalCoupleCount > 0 : "prevent didvision of zero error";
        
        assert externalCoupleCount <= UseMatrix.NUMBER_OF_USES : "external couple count error with couple count of  " + externalCoupleCount;

       objectiveCouplingFitness = externalCoupleCount / (double) UseMatrix.NUMBER_OF_USES;
//post-conditions
        assert objectiveCouplingFitness <= 1.0 : "objective coupling value is " + objectiveCouplingFitness;
        assert objectiveCouplingFitness >= 0.0 : "objective coupling value is " + objectiveCouplingFitness;

            // ------calculate elegance chacterisation---------------

                    double eleganceCharacterisation = 0.0; 

                    int[] classSizes = new int [UseMatrix.NUMBER_OF_CLASSES];

                    for (int i = 0; i < classSizes.length; i++)
                    {
                        classSizes[i]= 0;
                    }


                    for (int i = 0; i < (UseMatrix.NUMBER_OF_METHODS + UseMatrix.NUMBER_OF_ATTRIBUTES); i++)
                    {
                       assert genotype[ i ] <= classSizes.length : "invalid genotype value: :" + genotype[ i ];

                        classSizes[genotype[i] - 1 ]++;
                    }

//pre-condition
        assert classSizes != null;

        eleganceCharacterisation = Utility.StandardDeviation(classSizes);

//post-condition
        assert eleganceCharacterisation >= 0.0 :
                "elegance characterisation value is " + eleganceCharacterisation;
      
                                //----sets the fitness value for the individual--------

                                if ( FITNESS_MEASURE== COUPLING )
                                {
                                  ind.setFitness(new SimpleValueFitness(objectiveCouplingFitness));  
                                }

                                if ( FITNESS_MEASURE== ELEGANCE )
                                {
                                 ind.setFitness(new SimpleValueFitness(eleganceCharacterisation)) ;  
                                }

                                else{
                                        assert false : "Impossible selection criterion";
                                    }

                                   }

            public void configure(Configuration settings)
            {  
            useMatrix = new UseMatrix(); 
            useMatrix.setUp();           
            }
      public Comparator<IFitness> getComparator()
            {
                    // Set fitness comparator (if necessary)
                    if (COMPARATOR == null)
                            COMPARATOR = new ValueFitnessComparator(!maximize);

                    // Return comparator
                    return COMPARATOR;
            } 
}