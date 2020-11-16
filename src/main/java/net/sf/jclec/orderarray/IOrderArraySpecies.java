/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.jclec.orderarray;

import net.sf.jclec.IIndividual;

import net.sf.jclec.ISpecies;

import net.sf.jclec.util.intset.IIntegerSet;



/**
 *
 * @author Brice
 */
public interface IOrderArraySpecies extends ISpecies {
    
  /**

	 * Factory method.

	 * 

	 * @param genotype Individual genotype.

	 * 

	 * @return A new instance of represented class

	 */

	

	public IIndividual createIndividual(int [] genotype);

	

	/**

	 * Informs about individual genotype length.

	 * 

	 * @return getGenotypeSchema().length

	 */

	

	public int getGenotypeLength();

	

	/**

	 * @return This genotype schema

	 */

	

	public IIntegerSet [] getGenotypeSchema();  
    
}
