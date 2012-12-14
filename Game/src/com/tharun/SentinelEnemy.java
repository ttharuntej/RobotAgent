package com.tharun;
/**|
 * 
 * @author tharun
 *
 */

// This enemy will move only when the robot is in the 4 directions of enemy.
public class SentinelEnemy extends EnemyRobot 
{
	public SentinelEnemy()
	  {
		  this.type = Constants.SENTINEL_ENEMY;
	  }
	/**
	 * Here we are calling the base class move method because this functionality is common for all the enemies.
	 */
	  public void move(Grid grid)
	  {
			if(!(grid.invisibleCloakCount >0))
		  super.move(grid, Constants.SENTINEL_ENEMY);
	  }
 
	 
}
