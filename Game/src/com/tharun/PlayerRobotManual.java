package com.tharun;
/**
 * 
 * @author tharun
 *
 */

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;


//  Robot moves in manual mode based on the directions given by users.
/**
 * The Class PlayerRobotManual.
 */
public class PlayerRobotManual extends Robot {
 	
	 /** The current x postion. */
	 static int currentXPostion = 0;
	
	/** The current y postion. */
	static int currentYPostion = 0;
	
	/** The timelimit. */
	public static int timelimit = 0;
	
	/** The enemy count. */
	public static int enemyCount;
	
	/** The DEFAUL t_ char. */
	public final char DEFAULT_CHAR= 'Z';
	
	/** The br. */
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	World world = new World();
	/** The c. */
Candy c = new Candy(Constants.SMALL_CANDY);
	
	/** The C. */
	Candy C = new Candy(Constants.BIG_CANDY);
	
	/** The enemy priority array. */
	ArrayList<Character> enemyPriorityArray = new ArrayList<Character>();
	
	/** The enemy robot. */
	Robot enemyRobot= null;
	
	/** The flying bomb. */
	FlyingBomb flyingBomb = new FlyingBomb();
	 
	/**
	 * Constructor where type is set to Constants.ROBOT;.
	 */
	public PlayerRobotManual() {
		this.type = Constants.ROBOT;
	}

/**
 * Move method where we define the move of robot.
 *
 * @param grid the grid
 */
	public void move(Grid grid) {
		timelimit = grid.timeLimit;
		grid.calculateEnemyOrderInGrid();
		enemyPriorityArray = grid.getEnemyOrderInGrid();
		
		for (int i = 0; i < grid.height; i++)
			for (int j = 0; j < grid.width; j++)
			{
				if (grid.gridWorld[i][j] == Constants.ROBOT)
				{
					move(grid, i, j);
					break;
				}
			}
	}

/**
 * Used to move the elements in grid.
 *
 * @param grid the grid
 * @param row the row
 * @param column the column
 */
	private void move(Grid grid, int row, int column) {
		try {
			int height = grid.height;
			int width = grid.width;
			int candyCount = grid.getCandyCountInGrid();
			enemyCount = grid.getEnemiesCountInGrid();
			grid.formGridCells();
			grid.formWarpZones();
			grid.formWarpZoneLocations();
			currentXPostion = row;
			currentYPostion = column;
			int flyBombCounter=0;
			boolean flag= false;
			char flyBombDirection=DEFAULT_CHAR;
			int currentCandyCount = 0; // Incremented when it picks up a
										// candy.displaying the initial grid
			printScore(grid);
			print(grid.gridCellArrayListObj, height, width);
			System.out.println();
			while (true) {
				grid.isAlive = true;
				if (candyCount == currentCandyCount) {

					System.out.println();
					System.out.println("SUCCESS");
					break;
//					System.exit(1);
				}
				
				else if (grid.timeLimit == grid.time) {// Reached the maximum number of steps so stop and quit.
					System.out.println();
					printScore(grid);
					grid.setElementAtLocation(currentXPostion, currentYPostion,
							Constants.PATH);
					grid.setElementAtLocation(currentXPostion,
							currentYPostion - 1, Constants.PATH);
					currentYPostion = currentYPostion - 1;
					print(grid.gridCellArrayListObj, height, width);
					grid.time++;
					 System.out.println("");
					 System.out.println("\n");
					 System.out.println("FAILURE");
					 System.out.println();
					 System.out.println("OOPS ...  YOU HAVE LOST THE GAME");
					 System.out.println();
					 System.out.println("DONT WORRY GIVE ONE MORE TRY..");
					 System.out.println("\n");
					break;
//					System.exit(0);
				}

				int nextXPosition = currentXPostion;
				int nextYPosition = currentYPostion;
				System.out.println();
				String dirStr = br.readLine();
				String[] inputStrings = dirStr.split(" ");
				char direction;
				char operation = DEFAULT_CHAR;
				if(inputStrings.length>1)
				{
				 operation = inputStrings[0].toCharArray()[0];
				 direction = inputStrings[1].toCharArray()[0];
				}
				else
				{
					direction = inputStrings[0].toCharArray()[0];
					
				}
				if (direction == 'u') {// if direction is up
					nextXPosition = currentXPostion - 1;
					nextYPosition = currentYPostion;
				} else if (direction == 'd') {// if direction is down
					nextXPosition = currentXPostion + 1;
					nextYPosition = currentYPostion;
				} else if (direction == 'l') {// if direction is  left
					nextXPosition = currentXPostion;
					nextYPosition = currentYPostion - 1;
				} else if (direction == 'r') {// if direction is  right
					nextXPosition = currentXPostion;
					nextYPosition = currentYPostion + 1;
				} else if( direction == 'w')// if direction is  just to wait
				{
					grid.time++;
 				}
				else{
					System.out
							.println("INVALID CHARECTER. Enter either l or u or d or r");
					grid.time--;

				}
				boolean setFlag = true;
				boolean robotFlag = false;
				if (nextXPosition < 0 || nextXPosition > height - 1
						|| nextYPosition < 0 || nextYPosition > width - 1 || direction =='w' ||grid
								.getElementAtLocation(nextXPosition, nextYPosition).type == Constants.BOMB || grid.getElementAtLocation(nextXPosition,
										nextYPosition).type == Constants.WALL || grid
										.getElementAtLocation(nextXPosition, nextYPosition).type == Constants.FLYING_BOMB
										||grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.WALL ||
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.DUMB_ENEMY ||
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.SENTINEL_ENEMY ||
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.WALKING_SENTINEL ||
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.PLAYER_CHASER ||
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.CANDY_CHASER ||
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.BOMB || (operation == Constants.THROW_BOMB && (int)grid.getElementAtLocation(nextXPosition,
													nextYPosition).type >= 49 && (int)grid.getElementAtLocation(nextXPosition,
															nextYPosition).type <= 57 ) ) {// checking the bounds
//					grid.time++;
//					printScore(grid);
//					print(grid.gridCellArrayListObj, height, width);
				}
				else
				{
			 	for(int k = 0 ; k <grid.warpZoneEntry.size();k++ )
				{
					if(currentXPostion == grid.warpZoneEntry.get(k).x && currentYPostion == grid.warpZoneEntry.get(k).y)
					 {
						if(grid.getElementAtLocation(currentXPostion, currentYPostion).type == Constants.ROBOT_HOLDING_BOMB)
							robotFlag = true;
						 
						grid.setElementAtLocation(currentXPostion, currentYPostion, grid.warpZoneEntry.get(k).value);
						 
						 setFlag =false;
						 break;
					 }
					if(setFlag)
					{
						if(currentXPostion == grid.warpZoneExit.get(k).x && currentYPostion == grid.warpZoneExit.get(k).y)
						 {
							if(grid.getElementAtLocation(currentXPostion, currentYPostion).type == Constants.ROBOT_HOLDING_BOMB)
								robotFlag = true;
							
							 grid.setElementAtLocation(currentXPostion, currentYPostion, grid.warpZoneExit.get(k).value);
							 setFlag =false;  
							 break;
						 }
					}
				}
				}
				if (nextXPosition < 0 || nextXPosition > height - 1
						|| nextYPosition < 0 || nextYPosition > width - 1) {// checking the bounds
					grid.time++;
					System.out.println();
					printScore(grid);
					print(grid.gridCellArrayListObj, height, width);
				}
				 
				else if ((grid
						.getElementAtLocation(nextXPosition, nextYPosition).type == Constants.BOMB || grid.getElementAtLocation(nextXPosition,
								nextYPosition).type == Constants.WALL || grid
								.getElementAtLocation(nextXPosition, nextYPosition).type == Constants.FLYING_BOMB ) && operation == DEFAULT_CHAR) {
					grid.time++;// THis is just to take no action when the neighbour is bomb or wall.
 					System.out.println();
				} else if (grid.getElementAtLocation(nextXPosition,
						nextYPosition).type == Constants.SMALL_CANDY && operation == DEFAULT_CHAR) {
					if(grid.getElementAtLocation(currentXPostion, currentYPostion).type == Constants.ROBOT_HOLDING_BOMB || robotFlag)// if the existing type is  holding the bomb then it would be replaced with bomb.
					{
						grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT_HOLDING_BOMB);
					}
					else
					{
						grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT);
					}
					if(setFlag)
					grid.setElementAtLocation(currentXPostion, currentYPostion,
							Constants.PATH);
					grid.time++;
					currentCandyCount++;

					grid.score += c.getCandyScore();
 			} else if (grid.getElementAtLocation(nextXPosition,
						nextYPosition).type == Constants.BIG_CANDY && operation == DEFAULT_CHAR)

				{
					if(grid.getElementAtLocation(currentXPostion, currentYPostion).type == Constants.ROBOT_HOLDING_BOMB || robotFlag)
					{
						grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT_HOLDING_BOMB);
					}
					else
					{
						grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT);
					}
					if(setFlag)
					grid.setElementAtLocation(currentXPostion, currentYPostion,
							Constants.PATH);
				

					grid.time++;
					currentCandyCount++;
					 
					grid.score += C.getCandyScore();
 				} 
 			else if (grid.getElementAtLocation(nextXPosition,
					nextYPosition).type == Constants.INVISIBLE_CLOAK && operation == DEFAULT_CHAR)

			{
				if(grid.getElementAtLocation(currentXPostion, currentYPostion).type == Constants.ROBOT_HOLDING_BOMB || robotFlag)
				{
					grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT_HOLDING_BOMB);
				}
				else
				{
					grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT);
				}
				if(setFlag)
				grid.setElementAtLocation(currentXPostion, currentYPostion,
						Constants.PATH);
				if(flag)
				grid.invisibleCloakCount = grid.invisibleCloakCount+10;
				else
				{	flag = true;
					grid.invisibleCloakCount = 10;
				}
				 
				grid.time++;

			} 			
 			else if (grid.getElementAtLocation(nextXPosition,
						nextYPosition).type == Constants.PATH && operation == DEFAULT_CHAR) {
					
					if(grid.getElementAtLocation(currentXPostion, currentYPostion).type == Constants.ROBOT_HOLDING_BOMB || robotFlag)
					{
						grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT_HOLDING_BOMB);
					}
					else
					{
						grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT);
					}
					if(setFlag)
					grid.setElementAtLocation(currentXPostion, currentYPostion,
							Constants.PATH);
					 
					grid.time++;
					
 				} 
 				else if ((int)grid.getElementAtLocation(nextXPosition,
						nextYPosition).type >= 49 && (int)grid.getElementAtLocation(nextXPosition,
								nextYPosition).type <= 57 && operation == DEFAULT_CHAR )//Comparing the ascii values from 1 to 9
 				{
 					ArrayList<Integer> location  = new ArrayList<Integer>();
 					location= grid.giveNextWarpZoneLocation(nextXPosition, nextYPosition);
 					nextXPosition = location.get(0);
 					nextYPosition = location.get(1); 
    
					if(grid.getElementAtLocation(currentXPostion, currentYPostion).type == Constants.ROBOT_HOLDING_BOMB || robotFlag)
					{
						grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT_HOLDING_BOMB);
					}
					else
					{
						grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.ROBOT);
					}
					if(setFlag)
					grid.setElementAtLocation(currentXPostion, currentYPostion,
							grid.getWarpZoneElement(nextXPosition, nextYPosition));
					 
					grid.time++;
					
 				}
 				else if(grid.getElementAtLocation(nextXPosition, nextYPosition).type == Constants.BOMB  && operation == Constants.GRAB_BOMB )
				{
					if( grid.getElementAtLocation(currentXPostion,currentYPostion).type !=Constants.ROBOT_HOLDING_BOMB)
					{
						grid.setElementAtLocation(currentXPostion, currentYPostion,
								Constants.ROBOT_HOLDING_BOMB);
						grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.PATH);
						flyBombCounter =0;	
						grid.time++;
					}
					else
					{
						grid.time++;
					}
 				}
				else if(operation == Constants.THROW_BOMB && flyBombCounter==0 && (grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.WALL ||
																  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.DUMB_ENEMY ||
																  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.SENTINEL_ENEMY ||
																  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.WALKING_SENTINEL ||
																  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.PLAYER_CHASER ||
																  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.CANDY_CHASER ||
																  grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.BOMB ||
																  nextXPosition < 0 || nextXPosition > height - 1 ||
																  nextYPosition < 0 || nextYPosition > width - 1
																))
				{
        		}
				else if(operation == Constants.THROW_BOMB && flyBombCounter==0 &&grid.getElementAtLocation(currentXPostion, currentYPostion).type ==Constants.ROBOT_HOLDING_BOMB&& grid.getElementAtLocation(nextXPosition, nextYPosition).type ==Constants.PATH) 
				{  	// All these actions are done to handle the bomb cases.
					flyBombCounter++;
					flyBombDirection = direction;
					flyingBomb.currentXPostion.add(currentXPostion);
					flyingBomb.currentYPostion.add(currentYPostion);
					flyingBomb.nextXPostion.add(nextXPosition);
					flyingBomb.nextYPostion.add(nextYPosition);
					flyingBomb.direction.add(direction);
					grid.setElementAtLocation(currentXPostion, currentYPostion,
							Constants.ROBOT);
					grid.setElementAtLocation(nextXPosition, nextYPosition, Constants.FLYING_BOMB);
					grid.time++;
				}
				if ((nextXPosition >= 0 && nextXPosition < height)
						&& (nextYPosition >= 0 && nextYPosition < width) && grid.getElementAtLocation(nextXPosition,
								nextYPosition).type != Constants.WALL && grid.getElementAtLocation(nextXPosition,
										nextYPosition).type != Constants.BOMB && operation != Constants.GRAB_BOMB && operation != Constants.THROW_BOMB &&
										grid.getElementAtLocation(nextXPosition, nextYPosition).type != Constants.FLYING_BOMB &&
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type !=Constants.DUMB_ENEMY &&
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type !=Constants.SENTINEL_ENEMY &&
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type !=Constants.WALKING_SENTINEL &&
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type !=Constants.PLAYER_CHASER &&
										  grid.getElementAtLocation(nextXPosition, nextYPosition).type !=Constants.CANDY_CHASER 
										  ) {
					currentXPostion = nextXPosition;
					currentYPostion = nextYPosition;
				}
				if (candyCount == currentCandyCount) {

					System.out.println();
					printScore(grid);
					print(grid.gridCellArrayListObj, height, width);
					System.out.println("\n");
					System.out.println("SUCCESS");
					System.out.println();
					System.out.println("WOWWWWWW.... YOU HAVE WON THE GAME");
					System.out.println("\n");
					System.out.println("PLAY AGAIN");
					System.out.println("\n\n");
					break;
//					System.exit(1);
				}

				//Calling the enemis to move .
				grid.walkingSentinelEnemyPostion=0;
 				for(int k=0; k<enemyPriorityArray.size();k++ )
				{
					grid.enemyPosInList = k;
					enemyRobot = grid.getEnemyObject(enemyPriorityArray.get(k));// Getting the enemy dynamically. 
					int pos= grid.getEnemyPostion().get(k);
					grid.currentEnemyXPosition= pos / grid.width; 
						grid.currentEnemyYPosition = pos % grid.width;
							enemyRobot.move(grid);
				}
 				if(!grid.isAlive)
 				{
 					break; // Robot is dead and breaking the loop.
 				}
 				
 				
//				for(int l=0; l<1//flyingBomb.currentXPostion.size(); l++)
				{// Calling the bomb in the object.
					flyingBomb.counter = flyingBomb.currentXPostion.size();
					flyingBomb.move(grid);
					if(!flyingBomb.isRobotAlive)
					{
						printScore(grid);
						print(grid.gridCellArrayListObj, height, width);
						 System.out.println("");
						 System.out.println("\n");
						 System.out.println("FAILURE");
						 System.out.println();
						 System.out.println("OOPS ...  YOU HAVE LOST THE GAME");
						 System.out.println();
						 System.out.println("DONT WORRY GIVE ONE MORE TRY..");
						 System.out.println("\n");
						break;
//						System.exit(1);
					}
				}
				
				 
				printScore(grid);// Printing the score.
				print(grid.gridCellArrayListObj, height, width);// Printing the grid cell array.
				if(flag && grid.invisibleCloakCount > 0 )
				{
					System.out.println();
					System.out.println("The Robot will be Invisible mode for next " + grid.invisibleCloakCount + " steps.");
					if(grid.invisibleCloakCount >0)
					grid.invisibleCloakCount-- ;
				}
			}

		} catch (Exception e) {
				e.printStackTrace();
		}

	}

/**
 * Used to print the GridCell.
 *
 * @param gridCellArrayListObj the grid cell array list obj
 * @param height the height
 * @param width the width
 */
	public  void print(ArrayList<GridCell> gridCellArrayListObj, int height,
			int width) {
		int counter = 0;
		for (int i = 0; i < gridCellArrayListObj.size(); i++) {
			if (counter == width) {
				System.out.println();
				counter = 0;
				i--;
			} else {
				System.out.print(gridCellArrayListObj.get(i).a.type);
				counter++;
			}
		}
	}

/**
 * Used to print the Score.
 *
 * @param grid the grid
 */
	public void printScore(Grid grid) {
		System.out.println("Time limit:" + grid.timeLimit);
		System.out.println("Current time:" + grid.time);
		System.out.println("Enemies left:" + grid.enemiesCount);
		System.out.println("Score :" + grid.score);
		System.out.println();
	}
}
