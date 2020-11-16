/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.jclec.orderarray;

import net.sf.jclec.ISpecies;

import net.sf.jclec.base.AbstractRecombinator;

/**
 * OrderArrayIndividual (and subclasses) specific recombinator. 
 * @author Brice
 */
public abstract class OrderArrayRecombinator extends AbstractRecombinator {
    
    /////////////////////////////////////////////////////////////////

	// --------------------------------------------------- Attributes

	/////////////////////////////////////////////////////////////////

	

	private static final long serialVersionUID = -285985989834167887L;

	

	/** Individual species */

	

	protected IOrderArraySpecies species;

	

	/////////////////////////////////////////////////////////////////

	// ------------------------------------------------- Constructors

	/////////////////////////////////////////////////////////////////

	

	/**

	 * Empty constructor.

	 */

	

	public OrderArrayRecombinator() 

	{

		super();

	}



	/////////////////////////////////////////////////////////////////

	// -------------------------------------------- Protected methods

	/////////////////////////////////////////////////////////////////	

	

	// AbstractRecombinator methods

	

	/**

	 * Sets ppl = 2

	 * 

	 * {@inheritDoc}

	 */

	

	@Override

	protected void setPpl() 

	{

		this.ppl = 2;

	}



	/**

	 * Sets spl = 2

	 * 

	 * {@inheritDoc}

	 */



	@Override

	protected void setSpl() 

	{

		this.spl = 2;

	}



	/**

	 * {@inheritDoc}

	 */

	

	@Override	

	protected void prepareRecombination()

	{

		// Sets individual species

		ISpecies spc = context.getSpecies();

		if (spc instanceof IOrderArraySpecies) {

			this.species = (IOrderArraySpecies) spc;

		}

		else {

			throw new IllegalStateException("Invalid population species");

		}		

	}
    
}
