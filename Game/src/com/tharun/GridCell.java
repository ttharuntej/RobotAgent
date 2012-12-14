package com.tharun;
 
/**
 * The Class GridCell.
 *
 * @author Tharun Tej Tammineni.
 */
/**
 * Grid Cell is a class that contains the object in each cell.
 * 
 */
public class GridCell 
{
	
	/** The y pos. */
	int xPos, yPos;
    
    /** The a. */
    Actor a;
    
    /**
     * Instantiates a new grid cell.
     *
     * @param xPosition the x position
     * @param yPosition the y position
     * @param actor the actor
     */
    public GridCell(int xPosition, int yPosition, char actor) // Based on the actor we are creating respective object.
    {
	  xPos = xPosition;
	  yPos = yPosition;
	   if(actor == Constants.PATH)
	   {
		   a = new Actor();
	   }
	   if( actor == Constants.ROBOT)
	   {
		   a = new PlayerRobotManual();// creating PlayerRobotManual object.
	   }
	   if(actor == 'x' || actor == Constants.WALL)
	   {
		   a = new Wall();// wall object.
	   }
	   if(actor == 'b' || actor == Constants.BOMB)
	   {
		   a = new Bomb(); // Bomb objection.
	   }
	   if(actor == Constants.SMALL_CANDY ) 
	   {
		   a = new Candy(Constants.SMALL_CANDY); // candy object of type small.
	   }
	   if(actor == Constants.SMALL_CANDY ) 
	   {
		   a = new Candy(Constants.SMALL_CANDY);
	   }
	   if(actor == Constants.BIG_CANDY)
	   {
		   a = new Candy(Constants.BIG_CANDY);// candy object of type Big.
	   }
	   if(actor == Constants.DUMB_ENEMY)
	   {
		   a = new DumbEnemy(); // object of dumb enemy which moves in random direction.
	   }
	   if(actor == Constants.SENTINEL_ENEMY)
	   {
		   a = new SentinelEnemy();// THis will be at one fixed postion and will kill the robot if its in neighbour cell.
	   }
	   if(actor == Constants.WALKING_SENTINEL)
	   {
		   a = new WalkingSentinelEnemy();// object for walking sentinel enemy.
	   }
	   if(actor == Constants.PLAYER_CHASER)
	   {
		   a = new SmartEnemy();// object for player chaser enemy.
	   }
	   if(actor == Constants.CANDY_CHASER)
	   {
		   a = new WatcherEnemy();// object for candy chaser enemy.
	   }
	   if(actor == Constants.ROBOT_HOLDING_BOMB)
	   {
		   a = new PlayerRobotHoldingBomb();// object used to repersent the player holding bomb.
	   }
	   if(actor == Constants.FLYING_BOMB)
	   {
		   a = new FlyingBomb(); // Object used to represent the flying bomb.
	   }
	   if((int)actor >= 49 && (int)actor <=57)
	   {
		   a = new WarpZone(actor);
	   }
	   if(actor == 'i')
	   {
		   a = new InvisibleCloak();
				    
	   }
	}
    
    /**
     * Uset to get the Xpostion.
     *
     * @return the x pos
     */
	public int getxPos() 
	{
		return xPos;
	}

	/**
	 * Used to set the XPostion.
	 *
	 * @param xPos the new x pos
	 */
	public void setxPos(int xPos) 
	{
		this.xPos = xPos;
	}

	/**
	 * Uset to get the yPostion.
	 *
	 * @return the y pos
	 */
	public int getyPos() 
	{
		return yPos;
	}

	/**
	 * Used to set the Ypostion.
	 *
	 * @param yPos the new y pos
	 */
	public void setyPos(int yPos) 
	{
		this.yPos = yPos;
	}
}
