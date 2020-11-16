package net.sf.jclec;

import java.util.EventObject;

/**
 * Algorithm event.
 * 
 * @author Sebastian Ventura
 */

public class AlgorithmEvent extends EventObject 
{
	/////////////////////////////////////////////////////////////////
	// --------------------------------------- Serialization constant
	/////////////////////////////////////////////////////////////////

	/** Generated by Eclipse */
	
	private static final long serialVersionUID = -485187902934505060L;

	/////////////////////////////////////////////////////////////////
	// --------------------------------------------------- Attributes
	/////////////////////////////////////////////////////////////////

	/** Algorithm */
	
	protected IAlgorithm algorithm;
	
	/** Exception */
	
	protected Exception exception;
	
	/////////////////////////////////////////////////////////////////
	// ------------------------------------------------- Constructors
	/////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @param algorithm
	 */
	
	public AlgorithmEvent(IAlgorithm algorithm) 
	{
		this(algorithm, null);
	}

	/**
	 * 
	 * @param algorithm
	 * @param exception
	 */
	
	public AlgorithmEvent(IAlgorithm algorithm, Exception exception) 
	{
		super(algorithm);
		this.algorithm = algorithm;
		this.exception = exception;
	}

	/////////////////////////////////////////////////////////////////
	// ----------------------------------------------- Public methods
	/////////////////////////////////////////////////////////////////
	
	// Accessors
	
	/**
	 *  Access to algorithm.
	 *  
	 * @return Actual algorithm
	 */
	
	public final IAlgorithm getAlgorithm()
	{
		return algorithm;
	}
	
	/**
	 * Access to event exception.
	 * 
	 * @return Actual exception (null if none)
	 */
	
	public final Exception getException() 
	{
		return exception;
	}
}
