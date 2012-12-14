 
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
public class MazePath   {
	Grid grid;
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
	 
	 
	public ArrayList<GridCell> move(Grid grid) {
		this.grid = grid;
		Set<Integer> removeSetElements = null;
		ArrayList<HashMap<String, Integer>> path = null;
		rows = grid.height;
		columns = grid.width;

		grid.formGridCells();
	 
		int gridCandyCount = grid.getCandyCountInGrid();
		robotPostion = findRobotPostion(grid.gridWorld, rows, columns);
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
			 finalPath = new HashSet<Integer>();
			 set.removeAll(set);
			set.add(robotPostion);
			boolean flag = true;
			if (gridCandyCount == candyCount) {
 				return grid.gridCellArrayListObj;
 			}

			index = 0;
			while (!set.isEmpty())// set contains all the locations of robot and
									// paths i.e Constants.PATH ;
			{
				Object[] setArray = set.toArray();
				int location = (Integer) setArray[index];
				index++;

				removeSetElements.add(location); // Storing the elements that we
													// are removing in order to
													// avoid readding them
													// again.
				set.remove(0);
				int x = location / columns; // Calculating x, y CO-Ordinates
											// from location which is 1-D array.
				int y = location % columns;
				if (grid.getElementAtLocation(x, y).type == Constants.ROBOT
						|| grid.getElementAtLocation(x, y).type == Constants.PATH || grid.getElementAtLocation(x, y).type == Constants.WALL) {

					index = 0;
					if (grid.getElementAtLocation(x, y).type == Constants.ROBOT)
						robotAndPathLocationSet.add(location);
					else if (grid.getElementAtLocation(x, y).type == Constants.PATH )// &&
																			// !removeSetElements.contains(location))
						robotAndPathLocationSet.add(location);
					
					else if((grid.getElementAtLocation(x, y).type == Constants.WALL )  )
					{
						 
						robotAndPathLocationSet.add(location);
						 
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

 				if (grid.getElementAtLocation(x, y).type == Constants.BIG_CANDY
						|| grid.getElementAtLocation(x, y).type == Constants.SMALL_CANDY) {
					char checkType = grid.getElementAtLocation(x, y).type;
					path = checkDirection(location);
					Iterator iterator;
					ArrayList<String> key = new ArrayList<String>();
					ArrayList<Integer> directionValues = new ArrayList<Integer>();
					int counter = 0;
					for (int i = 0; i < path.size(); i++) // Traversing the path which has the shortest distance  to candy from Robot.
					{
						HashMap<String, Integer> h = new HashMap<String, Integer>();
						h = path.get(i);
						iterator = h.entrySet().iterator();

						while (iterator.hasNext()) {

							String mapArray = iterator.next().toString();
							String[] stringArray = mapArray.split(":");
							char map[] = mapArray.toCharArray();
							if ((map[0] == Constants.BIG_CANDY)) // Here key has the value of  opposite as we are traversing from the  Candy.
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
						int currentXPostion = locationValue / columns;   
						int currentYPostion = locationValue % columns;
						int robotXPosition = robotPostion / columns;
						int robotYPosition = robotPostion % columns;
					 
						
						grid.setElementAtLocation(currentXPostion,
								currentYPostion, Constants.PATH);
						if (k == 0)// Setting the Score for last element which
									// is Candy.
						{
 
						}
						flag = false;
 
						 
					}

					  // As you ate the candy you should reduce the
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
	private ArrayList<HashMap<String, Integer>> checkDirection(int location) // Recursive function that calculates the optimized path and  returns the path.
	{
		if (location == robotPostion)
			return directionsPath;
		 
		
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

 
}
