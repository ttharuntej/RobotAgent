package com.tharun;

/**
 * @author Tharun Tej Tammineni.
 * A01626417.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * This class is used to calculate the shortest paths to respective desitnations
 * from source.
 * 
 */
public class ChaserEnemy {
	Grid grid;
	static int dirCount = 0;
	public static int DEFAULT_VALUE = 9999;
	public static int LOCATION_DEFAULT_VALUE = 373473437;
	public static int left = 0, right = 0, up = 0, down = 0;

	public static int columns = 0, rows = 0, index;
	public int robotPostion;
	Integer locationValue;
	ArrayList<HashMap<String, Integer>> directionsPath = null;
	ArrayList<Integer> robotAndPathLocationSet = null;
	ArrayList<Integer> set = null;
	Set<Integer> finalPath = null;
/**
 * This returns the immediate path that should return.
 * @param grid
 * @param source
 * @param target
 * @param enemyPosInList
 * @return location
 */
	public Integer move(Grid grid, char source, char target, int enemyPosInList) {
		this.grid = grid;

		char target1 = 0;
		if (target == Constants.ROBOT)
			target1 = Constants.ROBOT_HOLDING_BOMB;
		if (target == Constants.BIG_CANDY)
			target1 = Constants.SMALL_CANDY;
		Set<Integer> removeSetElements = null;
		ArrayList<HashMap<String, Integer>> path = null;
		rows = grid.height;
		columns = grid.width;

		robotPostion = enemyPosInList;// findRobotPostion(grid.gridWorld, rows,
										// columns, source);
		left = DEFAULT_VALUE;
		right = DEFAULT_VALUE;
		up = DEFAULT_VALUE;
		down = DEFAULT_VALUE;
		locationValue = LOCATION_DEFAULT_VALUE;
		directionsPath = new ArrayList<HashMap<String, Integer>>();
		robotAndPathLocationSet = new ArrayList<Integer>();
		removeSetElements = new HashSet<Integer>();
		set = new ArrayList<Integer>();
		new ArrayList<HashMap<String, Integer>>();
		finalPath = new HashSet<Integer>();
		set.removeAll(set);
		set.add(robotPostion);
		boolean flag = true;
		index = 0;
		while (!set.isEmpty())// set contains all the locations of robot and
								// paths i.e Constants.PATH ;
		{
			Object[] setArray = set.toArray();
			int location = (Integer) setArray[index];
			index++;

			removeSetElements.add(location); // Storing the elements that we are removing in order to avoid readding them again.
			set.remove(0);
			int x = location / columns; // Calculating x, y CO-Ordinates
										// from location which is 1-D array.
			int y = location % columns;
			if ((grid.getElementAtLocation(x, y).type == source && flag == true) /* Here  will be having  multiple sources so we maintaining the flag and will enter only first time*/
																					 
																					
					|| grid.getElementAtLocation(x, y).type == Constants.PATH) {

				index = 0;
				if (grid.getElementAtLocation(x, y).type == source
						&& flag == true) {
					robotAndPathLocationSet.add(location);
					flag = false;
				} else if (grid.getElementAtLocation(x, y).type == Constants.PATH)// &&
																		// !removeSetElements.contains(location))
					robotAndPathLocationSet.add(location);
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

			else if (grid.getElementAtLocation(x, y).type == target
					|| grid.getElementAtLocation(x, y).type == target1) {
				char checkType = grid.getElementAtLocation(x, y).type;
				path = checkDirection(location);
				Iterator iterator;
				ArrayList<String> key = new ArrayList<String>();
				ArrayList<Integer> directionValues = new ArrayList<Integer>();
				int counter = 0;
				for (int i = 0; i < path.size(); i++) // Traversing the path which has the shortest distance to candy from Robot.
				{
					HashMap<String, Integer> h = new HashMap<String, Integer>();
					h = path.get(i);
					iterator = h.entrySet().iterator();

					while (iterator.hasNext()) {

						String mapArray = iterator.next().toString();
						String[] stringArray = mapArray.split(":");
						char map[] = mapArray.toCharArray();
						if ((map[0] == Constants.BIG_CANDY)) // Here key has the value of direction which is opposite as we are traversing from the Candy.
						{
							if (counter == 0) {
								key.add(map[0] + "");
								String locValueStr = stringArray[1].split("=")[1];
								locationValue = Integer.parseInt(locValueStr);
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
				if (directionValues.size() > 0) {
					if (directionValues.get(directionValues.size() - 2) != null) // Return  last but one element as last  element  is  postion of source.
						// robotPostion = locationValue;
						locationValue = directionValues.get(directionValues
								.size() - 2);
				} else
					locationValue = LOCATION_DEFAULT_VALUE;

				break;
			} else
				index = 0;
		}
		return locationValue;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<HashMap<String, Integer>> checkDirection(int location) // Recursive function that calculates the optimized  path and returns  path.
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

			checkDirection(left);// calling recursively left direction
		} else if (robotAndPathLocationSet.contains(right)) {// checking in the direction right.

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
		} else if (robotAndPathLocationSet.contains(up)) {// checking in direction up
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
		} else if (robotAndPathLocationSet.contains(down)) {// checking in direction down.
			ArrayList<Integer> tempSet = new ArrayList<Integer>();

			tempHashMap.put("Candy:" + dirCount, location);
			directionsPath.add(tempHashMap);

			tempHashMap = new HashMap<String, Integer>();

			tempHashMap.put("d:" + dirCount, down);
			directionsPath.add(tempHashMap);
			dirCount++;

			for (int i = 0; i < robotAndPathLocationSet.size(); i++) {// Iterating the loop and adding it into temporary set.
				if (!(robotAndPathLocationSet.get(i) == down)) {
					tempSet.add(robotAndPathLocationSet.get(i));
				} else {
					break;
				}
			}
			robotAndPathLocationSet = tempSet;
			checkDirection(down);// Calling recuersively.
		}
		return directionsPath;
	}

}
