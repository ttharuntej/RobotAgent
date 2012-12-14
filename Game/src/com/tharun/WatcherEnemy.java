	package com.tharun;
/**
 * 
 * @author tharun
 *
 */
	/**
	 * 
	 *Move next to a candy and wait for player to grab it. 
	 */
public class WatcherEnemy extends EnemyRobot 
{
	ChaserEnemy ce = new ChaserEnemy();
	PlayerRobotManual playerRobot = new PlayerRobotManual();
	PlayerRobotAuto playerAuto = new PlayerRobotAuto();
	public static int LOCATION_DEFAULT_VALUE = 373473437;
	
	/**
	 * Constructor setting type to Constants.candyChaser;
	 */
	  public WatcherEnemy()
	  {
		  this.type = Constants.CANDY_CHASER;
		
	  }
	@Override
	public void move(Grid grid)
	{
		if(!(grid.invisibleCloakCount >0))
		super.move(grid,Constants.CANDY_CHASER);
		int currentPostion =grid.postionsOfEnemies.get(grid.enemyPosInList); 
		int postion = ce.move(grid, Constants.CANDY_CHASER, 'C',grid.postionsOfEnemies.get(grid.enemyPosInList));
		if(postion != LOCATION_DEFAULT_VALUE)
		{
		int nextXpos= postion / grid.width; 
		int nextYpost = postion % grid.width;
		int currentXPos = currentPostion / grid.width;
		int currentYPos = currentPostion % grid.width;
		if(grid.getElementAtLocation(nextXpos, nextYpost).type == Constants.PATH)// Moves only if the postion is path.
		{
			grid.setElementAtLocation(nextXpos, nextYpost, Constants.CANDY_CHASER);
			grid.setElementAtLocation(currentXPos, currentYPos, Constants.PATH);
			int location = grid.width*nextXpos+ nextYpost;
			grid.setEnemyPostion(grid.enemyPosInList, location);
		}
		}
	}

}
