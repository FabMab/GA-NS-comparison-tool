package net.sf.jclec.exprtree.fun;

import net.sf.jclec.exprtree.fun.Argument;

public class Z extends Argument<Double> 
{
	/** Generated by Eclipse */
	
	private static final long serialVersionUID = 2253423213094035300L;

	public Z() 
	{
		super(Double.class, 2);
	}
	
	// java.lang.Object methods
	
	public boolean equals(Object other)
	{
		return other instanceof Z;
	}	
	
	public String toString()
	{
		return "Z";
	}
}
