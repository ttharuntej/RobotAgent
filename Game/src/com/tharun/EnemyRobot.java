package com.tharun;


// TODO: Auto-generated Javadoc

/**
 * The Class EnemyRobot.
 *
 * @author Tharun Tej Tammineni.
 */
/**
 * 
 * Enemy RObot is the base calss which defines a move method and this method is called in all the enemies.
 *
 */
public  class EnemyRobot extends Robot 
{

	  /** The player robot. */
  	PlayerRobotManual playerRobot = new PlayerRobotManual();
		
		/* (non-Javadoc)
		 * @see com.tharun.Robot#move(com.tharun.Grid)
		 */
		@Override
		public void move(Grid grid)
		{
			
		}
		
		/**
		 * This is used to check the 4 directions of the Enemy robot if there is an Robot it will kill.
		 *
		 * @param grid the grid
		 * @param enemy the enemy
		 */
		public void move(Grid grid, char enemy) 
		{

			 int row = grid.currentEnemyXPosition;
			 int column = grid.currentEnemyYPosition;
			 if(row+1 <grid.height)
			 if((grid.getElementAtLocation(row+1, column).type == Constants.ROBOT || grid.getElementAtLocation(row+1, column).type == Constants.ROBOT_HOLDING_BOMB) &&!((grid.warpZoneXCoordinates.contains(row+1) && grid.warpZoneYCoordinates.contains(column)) ) ) 
			 {
				updateGrid(grid, row+1, column, enemy);
			 }
			 if(row-1 >=0)
			 if((grid.getElementAtLocation(row-1, column).type == Constants.ROBOT || grid.getElementAtLocation(row-1, column).type == Constants.ROBOT_HOLDING_BOMB )&&!((grid.warpZoneXCoordinates.contains(row-1) && grid.warpZoneYCoordinates.contains(column)) ) )
			 {
				 updateGrid(grid, row-1, column, enemy);
			 }
			 if(column+1 < grid.width)
			 if((grid.getElementAtLocation(row, column+1).type == Constants.ROBOT || grid.getElementAtLocation(row, column+1).type == Constants.ROBOT_HOLDING_BOMB )&&!((grid.warpZoneXCoordinates.contains(row) && grid.warpZoneYCoordinates.contains(column+1)) ) )
			 {
				 updateGrid(grid, row, column+1, enemy);
			 }
			 if(column-1 >=0)
			 if((grid.getElementAtLocation(row, column-1).type == Constants.ROBOT || grid.getElementAtLocation(row, column-1).type == Constants.ROBOT_HOLDING_BOMB )&&!((grid.warpZoneXCoordinates.contains(row ) && grid.warpZoneYCoordinates.contains(column-1)) ) ) 
			 {
				 updateGrid(grid, row, column-1, enemy);
			 }
		}
		
		/**
		 * It is used to exit the game if a neighbour is the robot.
		 *
		 * @param grid the grid
		 * @param row the row
		 * @param col the col
		 * @param enemy the enemy
		 */
		private void updateGrid(Grid grid, int row, int col, char enemy)
		{
			 grid.setElementAtLocation(grid.currentEnemyXPosition, grid.currentEnemyYPosition, Constants.PATH);
			 grid.setElementAtLocation(row, col, enemy);
 			 playerRobot.printScore(grid);
			 playerRobot.print(grid.gridCellArrayListObj, grid.height, grid.width);
			 System.out.println("");
			 System.out.println("\n");
			 System.out.println("FAILURE");
			 System.out.println();
			 System.out.println("OOPS ...  YOU HAVE LOST THE GAME");
			 System.out.println();
			 System.out.println("DONT WORRY GIVE ONE MORE TRY..");
			 System.out.println("\n");
			
			 grid.isAlive = false;
 		}
	
	
	
}
