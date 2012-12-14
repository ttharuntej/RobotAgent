package com.tharun;

/**
 * @author Tharun Tej Tammineni.
 */

/**
 * 
 * World is the Base class which contains the  details like time limit, score, time in
 * the grid.
 */
public class World
{
	public int timeLimit ;  // Total Time Limit given by user.
	public int time ;		// Time consumed by player.
	public int score;		// Score obtained by user.
	
	/**
	 * Used to get the Time Limit in the game.
	 * @return
	 */
	public int getTimeLimit() 
	{
		return timeLimit;
	}
	/**
	 * 
	 * Use to set Time limt
	 * @param timeLimit
	 */
	public void setTimeLimit(int timeLimit) 
	{
		this.timeLimit = timeLimit;
	}
	/**
	 * Used to get time limt
	 * @return time limit
	 */
	public int getTime() 
	{
		return time;
	}
	/**
	 * Used to set Time
	 * @param time
	 */
	public void setTime(int time) 
	{
		this.time = time;
	}
	/**
	 * Used to get Score in the game.
	 * @return score
	 */
	public int getScore() 
	{
		return score;
	}
	
	/**
	 * Used to set Score in the game.
	 * @param score
	 */
	public void setScore(int score) 
	{
		this.score = score;
	}
	 
}
	