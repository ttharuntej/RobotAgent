 
package com.tharun;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * @author tharun
 */

/**
 * Player Robot in auto mode. This extends Robot Base class and implements the move method.
 *  
 */
public class PlayerRobotAuto extends Robot {
	Grid grid;
	Candy smallCandy = new Candy(Constants.SMALL_CANDY);
	Candy bigCandy = new Candy(Constants.BIG_CANDY);
	int candyCount = 0;
	static int dirCount = 0;
	public static int DEFAULT_VALUE = 9999;
	public static int left = 0,right=0, up=0,down=0;
	
	public static int columns = 0, rows = 0, index;
	public int robotPostion;
	Integer locationValue;
	ArrayList<HashMap<String, Integer>> directionsPath = null;
	ArrayList<Integer> robotAndPathLocationSet = null;
	ArrayList<Integer> set = null;
	Set<Integer> finalPath = null;
	ArrayList<Integer> warpStartZone = new ArrayList<Integer>();
	ArrayList<Integer> warpExitZone = new ArrayList<Integer>();
	ArrayList<Integer> warpXCoordinate = new ArrayList<Integer>();
	ArrayList<Integer> warpYCoordinate = new ArrayList<Integer>();
	ArrayList<Character> warpType = new ArrayList<Character>();
	@Override
	public void move(Grid grid) {
		this.grid = grid;
		Set<Integer> removeSetElements = null;
		ArrayList<HashMap<String, Integer>> path = null;
		rows = grid.height;
		columns = grid.width;

		grid.formGridCells();
		grid.formWarpZones();
		int gridCandyCount = grid.getCandyCountInGrid();
		robotPostion = findRobotPostion(grid.gridWorld, rows, columns);
		printScore();
		print(grid.gridCellArrayListObj, rows, columns);
		boolean failureFlag = false;
		while (true) {
			left = DEFAULT_VALUE;
			right = DEFAULT_VALUE;
			up = DEFAULT_VALUE;
			down = DEFAULT_VALUE;
			directionsPath = new ArrayList<HashMap<String, Integer>>();
			robotAndPathLocationSet = new ArrayList<Integer>();
			removeSetElements = new HashSet<Integer>();
			ArrayList<Integer> warpZone =  new ArrayList<Integer>();
			set = new ArrayList<Integer>();
			new ArrayList<HashMap<String, Integer>>();
			warpStartZone = new ArrayList<Integer>();
			warpExitZone = new ArrayList<Integer>();
			finalPath = new HashSet<Integer>();
			warpXCoordinate = new ArrayList<Integer>();
			warpYCoordinate = new ArrayList<Integer>();
			warpType = new ArrayList<Character>();
			set.removeAll(set);
			set.add(robotPostion);
			boolean flag = true;
			
			if(failureFlag)
			{	break;
			}
			else if (gridCandyCount == candyCount) {
				System.out.println();
				System.out.println("\n");
				System.out.println("SUCCESS");
				System.out.println();
				System.out.println("WOWWWWWW.... YOU HAVE WON THE GAME");
				System.out.println("\n");
				System.out.println("PLAY AGAIN");
				System.out.println("\n\n");
				break;
			//	System.exit(1);
			}

			index = 0;
			while (!set.isEmpty())// set contains all the locations of robot and
									// paths i.e Constants.PATH ;
			{
				Object[] setArray = set.toArray();
				int location = (Integer) setArray[index];
				index++;

				removeSetElements.add(location); // Storing the elements that we are removing in order to  avoid readding them again.
				set.remove(0);
				int x = location / columns; // Calculating x, y CO-Ordinates
											// from location which is 1-D array.
				int y = location % columns;
				if (grid.getElementAtLocation(x, y).type == Constants.ROBOT
						|| grid.getElementAtLocation(x, y).type == Constants.PATH || ((int)grid.getElementAtLocation(x, y).type >=49 && (int)grid.getElementAtLocation(x, y).type <=57)) {

					index = 0;
					if (grid.getElementAtLocation(x, y).type == Constants.ROBOT)
						robotAndPathLocationSet.add(location);
					else if (grid.getElementAtLocation(x, y).type == Constants.PATH ) 
						robotAndPathLocationSet.add(location);
					
					else if(  ((int)grid.getElementAtLocation(x, y).type >=49 && (int)grid.getElementAtLocation(x, y).type <=57))
					{
						warpZone =  new ArrayList<Integer>();
						if(!robotAndPathLocationSet.contains(location))
						robotAndPathLocationSet.add(location);
						warpStartZone.add(location);
						warpZone=grid.giveNextWarpZoneLocation(x, y);
						warpXCoordinate.add(warpZone.get(0));
						warpYCoordinate.add(warpZone.get(1));
						warpType.add(grid.getElementAtLocation(x, y).type);
					 	location = grid.width* warpZone.get(0)+ warpZone.get(1);
						if(!robotAndPathLocationSet.contains(location))
						robotAndPathLocationSet.add(location);
						warpExitZone.add(location);
					}
					
					if ((location + 1) % columns != 0
							&& !removeSetElements.contains(location + 1)
							&& !set.contains(location + 1))
						set.add(location + 1);
					if ((location) % columns != 0
							&& !removeSetElements.contains(location - 1)
							&& !set.contains(location - 1))
						set.add(location - 1);
					if (!(location - columns < 0)
							&& !removeSetElements.contains(location - columns)
							&& !set.contains(location - columns))
						set.add(location - columns);
					if (!(location + columns >= rows * columns)
							&& !removeSetElements.contains(location + columns)
							&& !set.contains(location + columns))
						set.add(location + columns);
				}

				if (grid.getElementAtLocation(x, y).type == Constants.WALL
						|| grid.getElementAtLocation(x, y).type == Constants.BOMB) {
					index = 0;
				}

				if (grid.getElementAtLocation(x, y).type == Constants.BIG_CANDY
						|| grid.getElementAtLocation(x, y).type == Constants.SMALL_CANDY) {
					char checkType = grid.getElementAtLocation(x, y).type;
					path = checkDirection(location);
					Iterator iterator;
					ArrayList<String> key = new ArrayList<String>();
					ArrayList<Integer> directionValues = new ArrayList<Integer>();
					int counter = 0;
					for (int i = 0; i < path.size(); i++) // Traversing the path
															// which has the
															// shortest distance
															// to candy from
															// Robot.
					{
						HashMap<String, Integer> h = new HashMap<String, Integer>();
						h = path.get(i);
						iterator = h.entrySet().iterator();

						while (iterator.hasNext()) {

							String mapArray = iterator.next().toString();
							String[] stringArray = mapArray.split(":");
							char map[] = mapArray.toCharArray();
							if ((map[0] == Constants.BIG_CANDY)) // Here key has the value of
													// direction which is
													// opposite as we are
													// traversing from the
													// Candy.
							{
								if (counter == 0) {
									key.add(map[0] + "");
									String locValueStr = stringArray[1]
											.split("=")[1];
									locationValue = Integer
											.parseInt(locValueStr);
									directionValues.add(locationValue);
									counter++;
								} else {
									continue;
								}
							} else {
								key.add(map[0] + "");
								String locValueStr = stringArray[1].split("=")[1];
								locationValue = Integer.parseInt(locValueStr);
								directionValues.add(locationValue);
							}

						}
					}

					for (int k = key.size() - 1; k >= 0; k--) {
						locationValue = directionValues.get(k);
						String direction = key.get(k);
						int currentXPostion = locationValue / columns; // Calculating x, y CO-Ordinates  from  location which is 1-D array.
						int currentYPostion = locationValue % columns;
						int robotXPosition = robotPostion / columns;
						int robotYPosition = robotPostion % columns;
						grid.setElementAtLocation(robotXPosition,
								robotYPosition, Constants.PATH);
						for(int a=0; a< warpXCoordinate.size();a++)
						{
							if(robotXPosition==warpXCoordinate.get(a) &&robotYPosition == warpYCoordinate.get(a))
							{
								grid.setElementAtLocation(robotXPosition,
										robotYPosition, warpType.get(a));
								break;
							}
							else
							{
								grid.setElementAtLocation(robotXPosition,
										robotYPosition, Constants.PATH);
							}
						}
						
						grid.setElementAtLocation(currentXPostion,
								currentYPostion, Constants.ROBOT);
						if (k == 0)// Setting the Score for last element which
									// is Candy.
						{
							if (checkType == Constants.BIG_CANDY) {
								grid.score += bigCandy.getCandyScore();

							} else if (checkType == Constants.SMALL_CANDY) {
								grid.score += smallCandy.getCandyScore();

							}
						}
						if (!flag) {
							// add delay
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							printScore();
							print(grid.gridCellArrayListObj, rows, columns);
						}
						flag = false;
						grid.setElementAtLocation(currentXPostion,
								currentYPostion, Constants.PATH);
						for(int b=0; b< warpXCoordinate.size();b++)
						{
							if(currentXPostion==warpXCoordinate.get(b) && currentYPostion== warpYCoordinate.get(b))
							{
								grid.setElementAtLocation(currentXPostion,
										currentYPostion, warpType.get(b));
								break;
							}	
							else 
								grid.setElementAtLocation(currentXPostion,
								currentYPostion, Constants.PATH);
						}
						if (grid.time == grid.timeLimit) {
							// printScore();
							// print(grid.gridCellArrayListObj, rows,columns);
							// System.out.println();
							 System.out.println("");
							 System.out.println("\n");
							 System.out.println("FAILURE");
							 System.out.println();
							 System.out.println("OOPS ...  YOU HAVE LOST THE GAME");
							 System.out.println();
							 System.out.println("DONT WORRY GIVE ONE MORE TRY..");
							 System.out.println("\n");
							 failureFlag = true;
							 break;
//							System.exit(1);
						}
						grid.time++;
					}

					grid.time--; // As you ate the candy you should reduce the
									// count once.
					candyCount++;
					robotPostion = locationValue;
					break;
				}
			}
		}
	}

	private int findRobotPostion(char[][] gridArray, int rows, int columns) {
		int robotLocation = 0;
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				if (gridArray[i][j] == Constants.ROBOT) {
					robotLocation = columns * i + j;
					break;
				}

			}
		return robotLocation;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<HashMap<String, Integer>> checkDirection(int location)  
	{
		if (location == robotPostion)
			return directionsPath;
		for(int k =0;k<warpStartZone.size();k++)
		{
			if(location ==warpStartZone.get(k))
			{
				location = warpExitZone.get(k);
				break;
			}
			else if(location == warpExitZone.get(k))
			{
				location = warpStartZone.get(k);
				break;
			}
		}
		
		if ((location + 1) % columns != 0)
			right = location + 1;
		if ((location) % columns != 0)
			left = location - 1;
		if (!(location - columns < 0))
			up = location - columns;
		if (!(location + columns >= rows * columns))
			down = location + columns;
		HashMap<String, Integer> tempHashMap = new HashMap<String, Integer>();
		if (robotAndPathLocationSet.contains(left)) {

			ArrayList<Integer> tempSet = new ArrayList<Integer>();

			tempHashMap.put("Candy:" + dirCount, location);
			directionsPath.add(tempHashMap);

			tempHashMap = new HashMap<String, Integer>();

			tempHashMap.put("l:" + dirCount, left);
			directionsPath.add(tempHashMap);
			dirCount++;
			for (int i = 0; i < robotAndPathLocationSet.size(); i++) {
				if (!(robotAndPathLocationSet.get(i) == left)) {
					tempSet.add(robotAndPathLocationSet.get(i));
				} else {
					break;
				}
			}
			robotAndPathLocationSet = tempSet;

			checkDirection(left);
		} else if (robotAndPathLocationSet.contains(right)) {

			ArrayList<Integer> tempSet = new ArrayList<Integer>();

			tempHashMap.put("Candy:" + dirCount, location);
			directionsPath.add(tempHashMap);

			tempHashMap = new HashMap<String, Integer>();

			tempHashMap.put("r:" + dirCount, right);
			directionsPath.add(tempHashMap);
			dirCount++;
			for (int i = 0; i < robotAndPathLocationSet.size(); i++) {
				if (!(robotAndPathLocationSet.get(i) == right)) {
					tempSet.add(robotAndPathLocationSet.get(i));
				} else {
					break;
				}
			}
			robotAndPathLocationSet = tempSet;
			checkDirection(right);
		} else if (robotAndPathLocationSet.contains(up)) {
			ArrayList<Integer> tempSet = new ArrayList<Integer>();

			tempHashMap.put("Candy:" + dirCount, location);
			directionsPath.add(tempHashMap);

			tempHashMap = new HashMap<String, Integer>();

			tempHashMap.put("u:" + dirCount, up);
			directionsPath.add(tempHashMap);
			dirCount++;
			for (int i = 0; i < robotAndPathLocationSet.size(); i++) {
				if (!(robotAndPathLocationSet.get(i) == up)) {
					tempSet.add(robotAndPathLocationSet.get(i));
				} else {
					break;
				}
			}
			robotAndPathLocationSet = tempSet;
			checkDirection(up);
		} else if (robotAndPathLocationSet.contains(down)) {
			ArrayList<Integer> tempSet = new ArrayList<Integer>();

			tempHashMap.put("Candy:" + dirCount, location);
			directionsPath.add(tempHashMap);

			tempHashMap = new HashMap<String, Integer>();

			tempHashMap.put("d:" + dirCount, down);
			directionsPath.add(tempHashMap);
			dirCount++;

			for (int i = 0; i < robotAndPathLocationSet.size(); i++) {
				if (!(robotAndPathLocationSet.get(i) == down)) {
					tempSet.add(robotAndPathLocationSet.get(i));
				} else {
					break;
				}
			}
			robotAndPathLocationSet = tempSet;
			checkDirection(down);
		}
		return directionsPath;
	}

	private void printScore() {
		System.out.println("");
		System.out.println("Time limit:" + grid.timeLimit);
		System.out.println("Current time:" + grid.time);
		System.out.println("Score:" + grid.score);

	}

	private void print(ArrayList<GridCell> gridCellArrayListObj, int height,
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
		System.out.println("");
	}
}
