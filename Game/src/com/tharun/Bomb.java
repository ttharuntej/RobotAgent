package com.tharun;


 /**
 * The Class Bomb.
 *
 * @author Tharun Tej Tammineni.
 * A01626417.
 */
//Bomb Class used to identify bomb and replace the type with B.Type is inherited from Actor class.
public class Bomb extends Actor 
{
	
	/**
	 * Constructor for Bomb Class and it is extended from Actor where type is set to 'B';.
	 */
	public Bomb()
	{
		this.type = Constants.BOMB;
	}
}
