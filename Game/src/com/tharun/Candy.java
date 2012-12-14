package com.tharun;
/**
 * 
 * @author Tharun Tej Tammineni.
 *A01626417.
 */
//Candy Class is used to represent the candies present in the world.
public class Candy extends Actor 
{
	  
	 	int candyScore =0 ; 
	 	int candyCount = 0;
	 	public Candy(char type)  // Based on the type of Candy we will assign the score.
	 	{
	 		/**
	 		 * If type is small c it means candy is small and candy score is given 1.
	 		 */
	 		if(type == Constants.SMALL_CANDY)
	 		{
	 			this.type = Constants.SMALL_CANDY;
	 			candyScore = Constants.SMALL_CANDY_SCORE;
	 		}
	 		/**
	 		 * If candy is big then score is given as 5.
	 		 */
	 		else if(type == Constants.BIG_CANDY)
	 		{
	 			this.type = Constants.BIG_CANDY;
	 			candyScore = Constants.BIG_CANDY_SCORE;
	 			
	 		}
	 			
	 	}
	 	
	  
		public Candy() {
			// TODO Auto-generated constructor stub
		}


		/**
		 * Used to get the candy score
		 * @return candyScore
		 */
		public int getCandyScore() {
			return candyScore;
		}

 
	  
}
