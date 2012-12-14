package com.tharun;

/**
 * 
 * @author tharun
 *
 */
/**
 * 
 *Moves until an obstacle is found. When an obstacle is found, rotate right until it can move forward, 
 *or wait if all four directions are blocked. The first move ﻿is always to the right.
 *
 */
public class WalkingSentinelEnemy extends EnemyRobot 
{
	PlayerRobotManual playerRobot = new PlayerRobotManual();
	EnemyRobot enemyRobot = new EnemyRobot();
	public  char DEFAULT_DIRECTION_WALKING_SENTINEL = 'r';
	char direction;
	public WalkingSentinelEnemy()
	{
		this.type = Constants.WALKING_SENTINEL;
		 
	}
	  
	@Override
	public void move(Grid grid) 
	{	boolean flag = true;
	
	
	int counter = 0;
	while(flag)
	{
		if(counter >=4)
		{
			int location = grid.width*grid.currentEnemyXPosition + grid.currentEnemyYPosition;
			grid.setEnemyPostion(grid.enemyPosInList, location);
			break;
		}
//		 direction = grid.walkingSentinelHashMap.get(grid.enemyPosInList);
		direction = grid.walkingSentinelHashMap.get(grid.walkingSentinelPostionsInArrayList.get(grid.walkingSentinelEnemyPostion));
		int currentXPostion = grid.currentEnemyXPosition;
		int currentYPostion = grid.currentEnemyYPosition;
		
		if(!(grid.invisibleCloakCount >0))
		super.move(grid, Constants.WALKING_SENTINEL);
		
		
		int nextXPosition = currentXPostion;
		int nextYPosition = currentYPostion;
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
 
 		if( nextXPosition>= 0 && nextXPosition<grid.height  &&
				 nextYPosition>= 0 && nextYPosition < grid.width && 
				 grid.getElementAtLocation(nextXPosition, nextYPosition).type == Constants.PATH)
		{
	
 					grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.WALKING_SENTINEL);
					grid.setElementAtLocation(currentXPostion, currentYPostion,
							Constants.PATH);
					flag= false;
 		}
		else// there was an object so it enters into this loop and based on current direction it will change the direction.
		{
			if(direction == 'd')
				grid.walkingSentinelHashMap.put(grid.walkingSentinelPostionsInArrayList.get(grid.walkingSentinelEnemyPostion),'l') ;
			else if(direction == 'r')
				grid.walkingSentinelHashMap.put(grid.walkingSentinelPostionsInArrayList.get(grid.walkingSentinelEnemyPostion),'d') ;
			else if(direction == 'l')
				grid.walkingSentinelHashMap.put(grid.walkingSentinelPostionsInArrayList.get(grid.walkingSentinelEnemyPostion),'u') ;
			else if(direction == 'u')
				grid.walkingSentinelHashMap.put(grid.walkingSentinelPostionsInArrayList.get(grid.walkingSentinelEnemyPostion),'r') ;
			flag = true;
		}
		int location = grid.width*nextXPosition + nextYPosition;
		grid.setEnemyPostion(grid.enemyPosInList, location);
	}
	grid.walkingSentinelEnemyPostion++;
	}

 
}
