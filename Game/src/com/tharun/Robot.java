package com.tharun;
/**
 * 
 * @author tharun
 *
 */
//This class extends Actor and acts as a base class for player and enemy robots.
public abstract class Robot  extends Actor
{
	String robotName; // Can be player or enemy.
	public abstract void move(Grid grid);
	 
}
