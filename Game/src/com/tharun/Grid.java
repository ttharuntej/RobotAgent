/**
 * @author Tharun Tej Tammineni.
 * A01626417.
 */
package com.tharun;

import java.util.ArrayList;
import java.util.HashMap;

 /**
 * Grid extends from World.
 * It has the grid array, height and width of the grid.
 *  
 *
 */
public class Grid extends World
{
	
	/** The height. */
	int height;
	
	/** The width. */
	int width;
	
	/** The grid world. */
	char gridWorld[][];
	
	/** The candy count. */
	int candyCount=0;
	
	/** The enemy count. */
	int enemyCount = 0;
	
	/** The enemy index. */
	int enemyIndex = 0;
	
	/** The enemy pos in list. */
	public static int enemyPosInList=0;
	
	/** The enemies count. */
	public static int enemiesCount = 0;
	
	/** The current enemy postion. */
	int currentEnemyPostion=0;
	
	/** The total possible enemies in grid. */
	public final int totalPossibleEnemiesInGrid = 5;
	
	/** The enemy array. */
	ArrayList<Character> enemyArray = new ArrayList<Character>();
	
	public int invisibleCloakCount = 0;  
	 
 	/** The robot. */
 	Robot robot;
	 
 	/** The current enemy x position. */
 	int currentEnemyXPosition =0;
	 
 	/** The walking sentinel enemy postion. */
 	public static int walkingSentinelEnemyPostion = 0;
	 
 	/** The walking sentinel postions in array list. */
 	public ArrayList<Integer> walkingSentinelPostionsInArrayList = new ArrayList<Integer>();
	 
 	/** The current enemy y position. */
 	int currentEnemyYPosition = 0;
	
	/** The grid cell array list obj. */
	ArrayList<GridCell> gridCellArrayListObj = new ArrayList<GridCell>();// Creating a List of gridCells.
    
    /** The postions of enemies. */
    ArrayList<Integer> postionsOfEnemies = new ArrayList<Integer>();
    
    /** The walking sentinel hash map. */
    HashMap<Integer, Character> walkingSentinelHashMap = new  HashMap<Integer, Character>();
	

    /** The warpzoneEntry ArrayList to maintain starting location. */
    ArrayList<WarpZone> warpZoneEntry = new ArrayList<WarpZone>();
    
    /** The warpzoneExit ArrayList to maintain ending location. */
    ArrayList<WarpZone> warpZoneExit  = new ArrayList<WarpZone>();
    
    ArrayList<Integer> warpZoneXCoordinates = new ArrayList<Integer>();
    
    ArrayList<Integer> warpZoneYCoordinates = new ArrayList<Integer>();
    
    
    boolean isAlive = true;// To check whether the Robot is still alive or not. Used in enemies to represent the status.
    
	/**
	 * Instantiates a new grid.
	 */
	public Grid()
	{

	}

/**
 * This is the constructor that assigns the values.
 *
 * @param rows the rows
 * @param columns the columns
 * @param map the map
 * @param timeLimit the time limit
 */
	public Grid(int rows, int columns, char map[][], int timeLimit) 
	{
		this.height = rows;
		this.width = columns;
		this.gridWorld = map;
		this.timeLimit = timeLimit;
	}

	
	/**
	 * Form grid cells.
	 */
	public void formGridCells() // Here we are converting the array of Grid to gridCell array Objects.
	{
		for (int i = 0; i < height; i++) 
		{
			for (int j = 0; j < width; j++) 
			{
				GridCell gridCellObj = new GridCell(i, j, gridWorld[i][j]);
				gridCellArrayListObj.add(gridCellObj);
			}
		}
	}

	/**
	 * Gets the candy count in grid.
	 *
	 * @return the candy count in grid
	 */
	public int getCandyCountInGrid()//Gets the total number of candies present in the Grid.
	{
		for (int i = 0; i < height; i++) 
		{
			for (int j = 0; j < width; j++) 
			{
				if (gridWorld[i][j] == Constants.SMALL_CANDY || gridWorld[i][j] == Constants.BIG_CANDY)
					candyCount++;

			}
		}
		return candyCount;
	}
	
	/**
	 * Used to retun the number of enemies present in the grid.
	 * @return enemyCount
	 */
	public int getEnemiesCountInGrid()
	{
		for (int i = 0; i < height; i++) 
		{
			for (int j = 0; j < width; j++) 
			{
				if (gridWorld[i][j] == Constants.DUMB_ENEMY || gridWorld[i][j] == Constants.SENTINEL_ENEMY||gridWorld[i][j] == Constants.WALKING_SENTINEL || gridWorld[i][j] == Constants.PLAYER_CHASER|| gridWorld[i][j] == Constants.CANDY_CHASER)
					enemyCount++;
			}
		}
		enemiesCount=enemyCount;
		return enemyCount;
	}
	
	
	/**
	 * Used to get the Actor object at particular location by passing the row and colum as parameters.
	 *
	 * @param row the row
	 * @param colummn the colummn
	 * @return Actor
	 */
	public Actor getElementAtLocation(int row, int colummn)	// Here we are storing in Arraylist so to compute the exact value based on row,column we compute the following formula.
	{
		int elementLocationIndex = width * row + colummn;
	 	GridCell gridCell = gridCellArrayListObj.get(elementLocationIndex);
		return gridCell.a ;

	}
	
	/**
	 * Sets the element at location.
	 *
	 * @param row the row
	 * @param column the column
	 * @param actor the actor
	 */
	public void setElementAtLocation(int row, int column, char actor)// Setting the element in particular location based on the x, y positions.
	{
		
		int elementLocationIndex = width * row + column;
		GridCell gridCellObj = new GridCell(row, column, actor);
		gridCellArrayListObj.set(elementLocationIndex, gridCellObj); 
	 	
	}
	
	/**
	 * By passing the actor charecter we are returning respective Object.
	 *
	 * @param actor the actor
	 * @return Robot Object
	 */
	public Robot getEnemyObject(char actor)
	{
		if(actor == Constants.DUMB_ENEMY)
		   {
			   robot = new DumbEnemy();
		   }
		else  if(actor == Constants.SENTINEL_ENEMY)
		   {
			   robot = new SentinelEnemy();
		   }
		else if(actor == Constants.WALKING_SENTINEL)
		   {
			   robot = new WalkingSentinelEnemy();
		   }
		else if(actor == Constants.PLAYER_CHASER)
		   {
			   robot = new SmartEnemy();
		   }
		else if(actor == Constants.CANDY_CHASER)
		   {
			   robot = new WatcherEnemy();
		   }
		return robot;
	}
	
	/**
	 * This is used to caluclate the order in which the eniemies are present in the grid.
	 */
	public void  calculateEnemyOrderInGrid()
	{
		int counter = 0;
		
		for(int k = 0; k< height; k++)
			for(int l=0; l<width; l++)
			{
				if(gridWorld[k][l] == Constants.DUMB_ENEMY || gridWorld[k][l] == Constants.SENTINEL_ENEMY||gridWorld[k][l] == Constants.WALKING_SENTINEL || gridWorld[k][l] == Constants.PLAYER_CHASER|| gridWorld[k][l] == Constants.CANDY_CHASER)
				{
					enemyArray.add(gridWorld[k][l]);
					postionsOfEnemies.add(width*k+l);
					if(gridWorld[k][l]== Constants.WALKING_SENTINEL)
					{
						walkingSentinelHashMap.put(counter, 'r');
						walkingSentinelPostionsInArrayList.add(counter);
					}
					counter++;
				 
				}
			}
//		return enemyArray;
		
	}
	/**
	 * Used to return the order of enmies in a grid.
	 * @return enemyArray.
	 */
	public ArrayList<Character> getEnemyOrderInGrid()
	{
		return enemyArray;
	}
	
	/**
	 * Used to rturn the postion of enemies.
	 *
	 * @return postionsOfEnemies.
	 */
	public ArrayList<Integer> getEnemyPostion()
	{
		return postionsOfEnemies;
	}
	
	
	/**
	 * Used to set the postions of enemies.
	 *
	 * @param pos the pos
	 * @param location the location
	 */
	public void setEnemyPostion(int pos, int location)
	{
		postionsOfEnemies.set(pos, location);
	}
	
	/**
	 * Used to remove an enmemy after dying by passing its coordinates.
	 *
	 * @param x the x
	 * @param y the y
	 */
	
	public void removeEnemyAfterDying(int x, int y)
	{
		int elementIndex = 0 ;
		int location = width*x+y;
		for(int m=0;m<postionsOfEnemies.size();m++)
		{
			if(postionsOfEnemies.get(m)== location)
			{
			  elementIndex	= m;
				break;
			}
		}
	 	enemyArray.remove(elementIndex);
		postionsOfEnemies.remove(elementIndex);
	}
	
	/**
	 * This function is used to create the warp zones maintaining the starting and terminating positions.
	 */
	public void formWarpZones()
	{
		for (int i = 0; i < height; i++) 
		{
			for (int j = 0; j < width; j++) 
			{
				boolean flag = true;
				if ((int)gridWorld[i][j] >= 49 && (int) gridWorld[i][j] <= 57)// Here 49 is ascii value of 1 and 57 is ascii value of 9.(int)char returns you ascii value.
				{
						for(int k=0; k<warpZoneEntry.size();k++)
						{
							if(warpZoneEntry.get(k).value == gridWorld[i][j])// If the value is occuring second time then override that index with the repeating value into second arraylist. 
							{
								WarpZone warp = new WarpZone(gridWorld[i][j], i, j);
								warpZoneExit.remove(k);
								warpZoneExit.add(k,warp);
								flag = false;
							}
						}
						if(flag)
						{
							WarpZone warpZone = new WarpZone(gridWorld[i][j],i,j);
							warpZoneEntry.add(warpZone);
							warpZoneExit.add(warpZone);// Just add into two arraylists initailly.
						}
				}
		}
	}
//		for(int l = 0 ; l<warpZoneEntry.size(); l++)
//		{
//			System.out.println(warpZoneEntry.get(l).value);
//			System.out.println(warpZoneEntry.get(l).x);
//			System.out.println(warpZoneEntry.get(l).y);
//			System.out.println("*******************************");
//		}
//		
//		for(int l = 0 ; l<warpZoneExit.size(); l++)
//		{
//			System.out.println("---------------------------------");
//			System.out.println(warpZoneExit.get(l).value);
//			System.out.println(warpZoneExit.get(l).x);
//			System.out.println(warpZoneExit.get(l).y);
//			System.out.println("*******************************");
//		}
	}
	
	
	public ArrayList<Integer> giveNextWarpZoneLocation(int x, int y)
	{
		boolean flag = true;
		ArrayList<Integer> location = new ArrayList<Integer>();
		for(int l = 0 ; l<warpZoneEntry.size(); l++)
			{
			   if (warpZoneEntry.get(l).x == x && warpZoneEntry.get(l).y == y)
			   {
				   location.add(warpZoneExit.get(l).x);
				   location.add(warpZoneExit.get(l).y);
//				   location = width*warpZoneExit.get(l).x+warpZoneExit.get(l).y;
				   flag = false;
				  break ;
			   }
			}
		if(flag)
		{
			for(int l = 0 ; l<warpZoneExit.size(); l++)
			{
				 if (warpZoneExit.get(l).x == x && warpZoneExit.get(l).y == y)
				   {
					 location.add(warpZoneEntry.get(l).x);
					 location.add(warpZoneEntry.get(l).y);
//					  location = width*warpZoneEntry.get(l).x+warpZoneEntry.get(l).y;
					  break;
				   }
					
			}
		}
			
		return location;
	}
	
	public char getWarpZoneElement(int x, int y)
	{
		char type = 0;
		boolean flag = true;
		for(int l = 0 ; l<warpZoneEntry.size(); l++)
		{
		   if (warpZoneEntry.get(l).x == x && warpZoneEntry.get(l).y == y)
		   {
			   type = warpZoneExit.get(l).type;
			   flag = false;
			  break ;
		   }
		}
	if(flag)
	{
		for(int l = 0 ; l<warpZoneExit.size(); l++)
		{
			 if (warpZoneExit.get(l).x == x && warpZoneExit.get(l).y == y)
			   {
				 type = warpZoneEntry.get(l).type;
//				  location = width*warpZoneEntry.get(l).x+warpZoneEntry.get(l).y;
				  break;
			   }
				
		}
	}
	return type;
	}
	
	public void formWarpZoneLocations()
	{
		for(int  i=0; i <warpZoneEntry.size();i++)
		{
			warpZoneXCoordinates.add(warpZoneEntry.get(i).x);
			warpZoneYCoordinates.add(warpZoneEntry.get(i).y);
		}
		for(int j =0; j< warpZoneExit.size(); j++)
		{
			warpZoneXCoordinates.add(warpZoneExit.get(j).x);
			warpZoneYCoordinates.add(warpZoneExit.get(j).y);
		
		}
	}
}
