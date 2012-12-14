package com.tharun;
/**
 * @author Tharun Tej Tammineni.
 */
import java.util.Random;
 
/**
 * This class is used to operate the enemy in the random directions.
 */
public class DumbEnemy extends EnemyRobot {
 
	/**
	 * Constructor that is used to declare the type to Constants.dumbEnemy.
	 */
	public DumbEnemy() {
		this.type = Constants.DUMB_ENEMY;
	}
	
	/** The player robot. */
	PlayerRobotManual playerRobot = new PlayerRobotManual();
	
	/* (non-Javadoc)
	 * @see com.tharun.EnemyRobot#move(com.tharun.Grid)
	 */
	@Override
	public void move(Grid grid)
	{
		Random r = new Random();
		boolean flag = true;
		int counter = 0;
		while(flag) // We repeat this loop untill the enemy moves in atleast one direction.
		{
			if(counter >=4)
			{
				int location = grid.width*grid.currentEnemyXPosition + grid.currentEnemyYPosition;
				grid.setEnemyPostion(grid.enemyPosInList, location);
			
				break;
			}
		char[] directionArray = { 'l', 'r', 'u', 'd' }; // Giving the four directions.
		int currentXPostion = grid.currentEnemyXPosition;
		int currentYPostion = grid.currentEnemyYPosition;
		if(!(grid.invisibleCloakCount >0))
		super.move(grid, Constants.DUMB_ENEMY);
		int nextXPosition = currentXPostion;
		int nextYPosition = currentYPostion;
		char direction = directionArray[Math.abs(r.nextInt() % 4)];//Calculating the Direction randomly

		if (direction == 'u') 
		{
			nextXPosition = currentXPostion - 1;
			nextYPosition = currentYPostion;
			counter++;
		}
		else if (direction == 'd') 
		{
			nextXPosition = currentXPostion + 1;
			nextYPosition = currentYPostion;
			counter++;
		}
		else if (direction == 'l') 
		{
			nextXPosition = currentXPostion;
			nextYPosition = currentYPostion - 1;
			counter++;
		} 
		else if (direction == 'r') 
		{
			nextXPosition = currentXPostion;
			nextYPosition = currentYPostion + 1;
			counter++;
		}
		if (nextXPosition < 0 || nextXPosition > grid.height - 1
				|| nextYPosition < 0 || nextYPosition > grid.width - 1) 
		{
			flag=true; continue;
		}
		else if(grid.getElementAtLocation(nextXPosition, nextYPosition).type == Constants.PATH)
		{
			grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.DUMB_ENEMY);
			grid.setElementAtLocation(currentXPostion, currentYPostion,
					Constants.PATH);
			flag=false;
		}
		else if (grid.getElementAtLocation(nextXPosition, nextYPosition).type == Constants.ROBOT || grid.getElementAtLocation(nextXPosition, nextYPosition).type == Constants.ROBOT_HOLDING_BOMB)
		{
			if(!(grid.invisibleCloakCount >0))
			{
			updateGrid(grid, nextXPosition, nextYPosition, currentXPostion, currentYPostion);
			flag = false;
			}
			else
			{
				flag = true;
			}
			
		}
		else// If other than path and robot what ever comes try to find the direction.
		{
				flag = true;
		}
		int location = grid.width*nextXPosition + nextYPosition;
		grid.setEnemyPostion(grid.enemyPosInList, location);

		}
 
	}
	
	/**
	 * Used to update grid and exit when it kills the robot.
	 *
	 * @param grid the grid
	 * @param row the row
	 * @param col the col
	 * @param currentXPostion the current x postion
	 * @param currentYPostion the current y postion
	 */
	private void updateGrid(Grid grid, int row, int col, int currentXPostion, int currentYPostion)
	{
		 grid.setElementAtLocation(currentXPostion, currentYPostion, Constants.PATH);
		 grid.setElementAtLocation(row, col, Constants.DUMB_ENEMY);
		 grid.time++;
		 playerRobot.printScore(grid);
		 playerRobot.print(grid.gridCellArrayListObj, grid.height, grid.width);
		 System.out.println("");
		 System.out.println("Failure");
		 System.out.println("OOPS ...  YOU HAVE LOST THE GAME");
		System.out.println("\n\n\n\n\n\n\n\n");
		 System.exit(1);
	}
}