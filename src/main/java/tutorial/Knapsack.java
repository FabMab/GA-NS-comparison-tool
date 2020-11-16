 package tutorial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.sf.jclec.IConfigure;
import net.sf.jclec.IFitness;
import net.sf.jclec.IIndividual;
import net.sf.jclec.base.AbstractEvaluator;
import net.sf.jclec.binarray.BinArrayIndividual;
import net.sf.jclec.fitness.SimpleValueFitness;
import net.sf.jclec.fitness.ValueFitnessComparator;

import org.apache.commons.configuration.Configuration;

/**
 * Knapsack optimization
 * 
 * @author Alberto Cano
 * @author Jose Maria Luna
 * @author Juan Luis Olmo
 * @author Amelia Zafra
 * @author Sebastian Ventura
 */

public class Knapsack extends AbstractEvaluator implements IConfigure
{
	/////////////////////////////////////////////////////////////////
	// --------------------------------------- Serialization constant
	/////////////////////////////////////////////////////////////////
	
	/** Generated by Eclipse */
	
	private static final long serialVersionUID = -2635335580011827514L;
	
	/////////////////////////////////////////////////////////////////
	// --------------------------------------------------- Properties
	/////////////////////////////////////////////////////////////////
	
	/** Maximize of minimize functions? */
	
	protected boolean maximize;	
	
	/** Number of Products */
	
	private int numberOfProducts = 1;
	
	/** Maximum Number of each Product */
	
	private int maxOfProducts = 1;
	
	/** KnapSack Weights List */
	
	List<Integer> weights = new ArrayList<Integer>();
	
	/** KnapSack Profits List */
	
	List<Integer> profits = new ArrayList<Integer>();
	
	/** Maximum weigth (restriction) */
	
	protected double maxWeight = 200.3;
	
	/////////////////////////////////////////////////////////////////
	// ---------------------------------------------- Class variables
	/////////////////////////////////////////////////////////////////
	
	private Comparator<IFitness> COMPARATOR;
	
	/////////////////////////////////////////////////////////////////
	// ------------------------------------------------- Constructors
	/////////////////////////////////////////////////////////////////
	/**
	* Empty constructor.
	*/
	public Knapsack()
	{
		super();
	}
	
	/////////////////////////////////////////////////////////////////
	// ------------------------------- Setting and getting properties
	/////////////////////////////////////////////////////////////////
	
	/** Is this evaluator being used to maximize a function?
	* @return true if evaluator is used to maximize function, false
	otherwise. */
	public boolean isMaximize()
	{
		return maximize;
	}
	
	/** Set the maximize flag.
	* @param maximize Actual maximize flag. */
	public void setMaximize(boolean maximize)
	{
		this.maximize = maximize;
	}
	
	/////////////////////////////////////////////////////////////////
	// ------------------------ Overwriting AbstractEvaluator methods
	/////////////////////////////////////////////////////////////////
	
	@Override
	protected void evaluate(IIndividual ind)
	{
		// Individual genotype
		byte [] genotype = ((BinArrayIndividual)ind).getGenotype();
		// Total weigth and profit
		int totalweigth = 0, totalprofit = 0;
		// Calculate weight
		for (int i=0; i<genotype.length; i++) {
			totalweigth += genotype[i]*weights.get(i);
			totalprofit += genotype[i]*profits.get(i);
		}
		
		// Calculate profit (if necessary)
		if (totalweigth <= maxWeight) {
			// Set individual fitness
			ind.setFitness(new SimpleValueFitness(totalprofit));
		}
		else{
			ind.setFitness(new SimpleValueFitness(-totalprofit));
		}
		
	}
	
	public void configure(Configuration settings)
	{
		//get max-number-products
		maxOfProducts = settings.getInt("products.max-each-product",1);
		// get number-products
		numberOfProducts = settings.getList("products.product[@weight]").size();
		// get max-weight
		maxWeight = settings.getInt("products.max-weight",1);
		
		
		for(int i=0; i<numberOfProducts; i++)
		{
			for(int j=0; j<maxOfProducts; j++)
			{
				profits.add(settings.getInt("products.product("+i+")[@profit]"));
				weights.add(settings.getInt("products.product("+i+")[@weight]"));
			}
		}
		
		// Maximize flag
		setMaximize(true);
	}
	
	/////////////////////////////////////////////////////////////////
	// ---------------------------- Implementing IEvaluator interface
	/////////////////////////////////////////////////////////////////
	
	/**
	 * {@inheritDoc}
	 */
	
	public Comparator<IFitness> getComparator()
	{
		// Set fitness comparator (if necessary)
		if (COMPARATOR == null)
			COMPARATOR = new ValueFitnessComparator(!maximize);
	
		// Return comparator
		return COMPARATOR;
	}
}