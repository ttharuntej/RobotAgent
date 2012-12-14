package com.tharun;
 
/**
 * The Class Actor.
 *
 * @author Tharun Tej Tammineni.
 * A01626417.
 */

// Base Class for all the objects in the grid.The default actor will be '.' which represents the path.
public class Actor 
{
	   
   	/** The type. */
   	char type;
	   /**
	    * Constructor for Actor Class where type is initialized to '.';
	    */
	public Actor()
	{
		this.type = Constants.PATH;
	}
	

}
