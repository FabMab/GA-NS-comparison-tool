package net.sf.jclec.realarray;

import java.util.ArrayList;

import net.sf.jclec.ISystem;
import net.sf.jclec.Population;
import net.sf.jclec.IIndividual;

import net.sf.jclec.IMutatorTest;

import net.sf.jclec.util.range.IRange;
import net.sf.jclec.util.range.Closure;
import net.sf.jclec.util.range.Interval;

import net.sf.jclec.util.random.Ranecu;
import net.sf.jclec.util.random.IRandGen;
import net.sf.jclec.util.random.IRandGenFactory;

/**
 * Unit tests for all RealArrayMutator subclasses
 *  
 * @author Sebastian Ventura
 *
 * @param <M> Mutator to test
 */

public abstract class RealArrayMutatorTest<M extends RealArrayMutator> extends IMutatorTest<M> 
{
	public RealArrayMutatorTest(Class<M> mutatorClass, String name) 
	{
		super(mutatorClass, name);
	}

	@Override
	protected ISystem createContext() 
	{
		// System that act as context
		Population pop = new Population();
		// Dummy random generators factory
		IRandGenFactory randGenFactory = new IRandGenFactory() {

			/** Generated by Eclipse */
			
			private static final long serialVersionUID = -8396371271024344889L;

			public IRandGen createRandGen() 
			{
				return new Ranecu(12345, 67890);
			}
		};
		// Assign random generators factory to this system		
		pop.setRandGenFactory(randGenFactory);
		// Create IIntegerRange array
		IRange [] ranges = new IRange[4];
		ranges[0] = new Interval(-5.12, 5.12, Closure.ClosedClosed);
		ranges[1] = new Interval(-5.12, 5.12, Closure.ClosedClosed);
		ranges[2] = new Interval(-5.12, 5.12, Closure.ClosedClosed);
		ranges[3] = new Interval(-5.12, 5.12, Closure.ClosedClosed);
		// System species
		RealArrayIndividualSpecies species = 
			new RealArrayIndividualSpecies(ranges);
		// Assign species to this system
		pop.setSpecies(species);
		// Sets "mutationContext" property
		return pop;
	}

	/**
	 * Mutant is (-4.5, -3.9, 2.1, 4.5)
	 * 
	 * {@inheritDoc}
	 */
	
	@Override
	protected void createParents() 
	{
		parents = new ArrayList<IIndividual>();
		parents.add(new RealArrayIndividual(new double [] {-4.5, -3.9, 2.1, 4.5}));
	}
}
