package com.tharun;

import java.util.ArrayList;

 /**
 * The Class FlyingBomb.
 *
 * @author tharun
 */
/**
 * This class represent the flying bomb in which if it collides with and object it will explode.
 * @author tharun
 *
 */
public class FlyingBomb extends Actor 
{
	
	/** The grid. */
	public Grid grid;
	
	/**
	 * Instantiates a new flying bomb.
	 */
	public FlyingBomb()
	{
		this.type = Constants.FLYING_BOMB;
	}
	
	/** The is robot alive. */
	boolean isRobotAlive = true;
	
	/** The current x postion. */
	ArrayList<Integer> currentXPostion = new ArrayList<Integer>();
	
	/** The current y postion. */
	ArrayList<Integer> currentYPostion = new ArrayList<Integer>();
	
	/** The next x postion. */
	ArrayList<Integer> nextXPostion = new ArrayList<Integer>();
	
	/** The next y postion. */
	ArrayList<Integer> nextYPostion = new ArrayList<Integer>();
	
	/** The direction. */
	ArrayList<Character> direction = new ArrayList<Character>();
	
	/** The candy type. */
	ArrayList<Character> candyType = new ArrayList<Character>();
	
	/** The candy x cordinate. */
	ArrayList<Integer> candyXCordinate = new ArrayList<Integer>();
	
	/** The candy y cordinate. */
	ArrayList<Integer> candyYCordinate = new ArrayList<Integer>();
	
	/** The store candy x cordinate. */
	ArrayList<Integer> storeCandyXCordinate = new ArrayList<Integer>();
	
	/** The store candy y cordinate. */
	ArrayList<Integer> storeCandyYCordinate = new ArrayList<Integer>();
	
	/** The store candy type. */
	ArrayList<Character> storeCandyType = new ArrayList<Character>();
	
	ArrayList<Character> storeWarpType = new ArrayList<Character>();
	
	ArrayList<Integer> storeWarpXCoordinate = new ArrayList<Integer>();
	
	ArrayList<Integer> storeWarpYCoordinate = new ArrayList<Integer>();
	
	ArrayList<Character> storeInvisibleType = new ArrayList<Character>();
	ArrayList<Integer> storeInvisibleXCoordinate = new ArrayList<Integer>();
	ArrayList<Integer> storeInvisibleYCoordinate = new ArrayList<Integer>();
	/** The i. */
	int counter,i;
	
	/**
	 * This is used to calculate the 8 directions of the flying bomb and replaces them respectively.
	 *
	 * @param grid the grid
	 */
	public void move(Grid grid)
	{
		isRobotAlive = true;
		this.grid = grid;
		counter =0;
		boolean flag = true;
		for(i =0; i <currentXPostion.size();i++)
		{
			
			char dir = direction.get(i);
			if (dir == 'u') {
				nextXPostion.set(i,currentXPostion.get(i) - 1);
				nextYPostion.set(i, currentYPostion.get(i)) ;
			} else if (dir == 'd') {
				nextXPostion.set(i, currentXPostion.get(i) +1);
				nextYPostion.set(i, currentYPostion.get(i)) ;
			} else if (dir == 'l') {
				nextXPostion.set(i, currentXPostion.get(i));
				nextYPostion.set(i,currentYPostion.get(i)-1);
			} else if (dir == 'r') {
				nextXPostion.set(i,currentXPostion.get(i) ) ;
				nextYPostion.set(i, currentYPostion.get(i)+1); 
			}
			
			if( nextXPostion.get(i) >= 0 && nextXPostion.get(i)<grid.height  &&
					 nextYPostion.get(i) >= 0 && nextYPostion.get(i) < grid.width)
			 {
				if(grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.PATH )
				{
					if(storeCandyXCordinate.size() > 0 )    // Checking if the Candy is there.
					{
						for(int j = 0 ; j< storeCandyXCordinate.size();j++)
						{
							
						
						if(storeCandyXCordinate.get(j) ==currentXPostion.get(i) && storeCandyYCordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeCandyXCordinate.get(j), storeCandyYCordinate.get(j), storeCandyType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeCandyXCordinate.remove(j);
							 storeCandyYCordinate.remove(j);
							 storeCandyType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
								flag = false;
						 }
						}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
					if(flag)
					if(storeWarpXCoordinate.size() > 0 ) // Checking if the warpzone is saved
					{
						for(int j = 0 ; j< storeWarpXCoordinate.size();j++)
						{
							
						
						if(storeWarpXCoordinate.get(j) ==currentXPostion.get(i) && storeWarpYCoordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeWarpXCoordinate.get(j), storeWarpYCoordinate.get(j), storeWarpType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeWarpXCoordinate.remove(j);
							 storeWarpYCoordinate.remove(j);
							 storeWarpType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
								flag = false;
						 }
					}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
					if(flag)
					if(storeInvisibleXCoordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeInvisibleXCoordinate.size();j++)
						{
							
						
						if(storeInvisibleXCoordinate.get(j) ==currentXPostion.get(i) && storeInvisibleYCoordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeInvisibleXCoordinate.get(j), storeInvisibleYCoordinate.get(j), storeInvisibleType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeInvisibleXCoordinate.remove(j);
							 storeInvisibleYCoordinate.remove(j);
							 storeInvisibleType.remove(j);
							 flag =false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
								flag  = false;
						 }
					}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
				 }
  	 if(grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.SMALL_CANDY )
			 {
				 	candyType.add(Constants.SMALL_CANDY);
					candyXCordinate.add(nextXPostion.get(i));
					candyYCordinate.add(nextYPostion.get(i));
					storeCandyXCordinate.add(nextXPostion.get(i));
					storeCandyYCordinate.add(nextYPostion.get(i));
					storeCandyType.add(Constants.SMALL_CANDY);
					
					if(storeCandyXCordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeCandyXCordinate.size();j++)
						{
							
						
						if(storeCandyXCordinate.get(j) ==currentXPostion.get(i) && storeCandyYCordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeCandyXCordinate.get(j), storeCandyYCordinate.get(j), storeCandyType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeCandyXCordinate.remove(j);
							 storeCandyYCordinate.remove(j);
							 storeCandyType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 						 }
						}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
					if(flag)
					if(storeWarpXCoordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeWarpXCoordinate.size();j++)
						{
							
						
						if(storeWarpXCoordinate.get(j) ==currentXPostion.get(i) && storeWarpYCoordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeWarpXCoordinate.get(j), storeWarpYCoordinate.get(j), storeWarpType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeWarpXCoordinate.remove(j);
							 storeWarpYCoordinate.remove(j);
							 storeWarpType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 						 }
					}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
					if(flag)
					if(storeInvisibleXCoordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeInvisibleXCoordinate.size();j++)
						{
							
						
						if(storeInvisibleXCoordinate.get(j) ==currentXPostion.get(i) && storeInvisibleYCoordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeInvisibleXCoordinate.get(j), storeInvisibleYCoordinate.get(j), storeInvisibleType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeInvisibleXCoordinate.remove(j);
							 storeInvisibleYCoordinate.remove(j);
							 storeInvisibleType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 						 }
					}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
					
 					
			 }
			 if(grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.BIG_CANDY )
			 {
				 	candyType.add(Constants.BIG_CANDY);
					candyXCordinate.add(nextXPostion.get(i));
					candyYCordinate.add(nextYPostion.get(i));
					storeCandyXCordinate.add(nextXPostion.get(i));
					storeCandyYCordinate.add(nextYPostion.get(i));
					storeCandyType.add(Constants.BIG_CANDY);
					if(storeInvisibleXCoordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeInvisibleXCoordinate.size();j++)
						{
							
						
						if(storeInvisibleXCoordinate.get(j) ==currentXPostion.get(i) && storeInvisibleYCoordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeInvisibleXCoordinate.get(j), storeInvisibleYCoordinate.get(j), storeInvisibleType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeInvisibleXCoordinate.remove(j);
							 storeInvisibleYCoordinate.remove(j);
							 storeInvisibleType.remove(j);
							 flag =false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 						 }
					}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
					
					if(flag)
					if(storeCandyXCordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeCandyXCordinate.size();j++)
						{
							
						
						if(storeCandyXCordinate.get(j) ==currentXPostion.get(i) && storeCandyYCordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeCandyXCordinate.get(j), storeCandyYCordinate.get(j), storeCandyType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeCandyXCordinate.remove(j);
							 storeCandyYCordinate.remove(j);
							 storeCandyType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 						 }
						}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
					
					if(flag)
					if(storeWarpXCoordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeWarpXCoordinate.size();j++)
						{
							
						
						if(storeWarpXCoordinate.get(j) ==currentXPostion.get(i) && storeWarpYCoordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeWarpXCoordinate.get(j), storeWarpYCoordinate.get(j), storeWarpType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeWarpXCoordinate.remove(j);
							 storeWarpYCoordinate.remove(j);
							 storeWarpType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 						 }
					}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
					
 					
			 }
			 
			 if(grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == 'i' )
			 {
				 	storeInvisibleType.add('i');
					storeInvisibleXCoordinate.add(nextXPostion.get(i));
					storeInvisibleYCoordinate.add(nextYPostion.get(i));
					
					if(storeCandyXCordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeCandyXCordinate.size();j++)
						{
							
						
						if(storeCandyXCordinate.get(j) ==currentXPostion.get(i) && storeCandyYCordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeCandyXCordinate.get(j), storeCandyYCordinate.get(j), storeCandyType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeCandyXCordinate.remove(j);
							 storeCandyYCordinate.remove(j);
							 storeCandyType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 						 }
						}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
					if(flag)
					if(storeWarpXCoordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeWarpXCoordinate.size();j++)
						{
							
						
						if(storeWarpXCoordinate.get(j) ==currentXPostion.get(i) && storeWarpYCoordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeWarpXCoordinate.get(j), storeWarpYCoordinate.get(j), storeWarpType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeWarpXCoordinate.remove(j);
							 storeWarpYCoordinate.remove(j);
							 storeWarpType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 						 }
					}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
	 				if(flag)
					if(storeInvisibleXCoordinate.size() > 0 )
					{
						for(int j = 0 ; j< storeInvisibleXCoordinate.size();j++)
						{
							
						
						if(storeInvisibleXCoordinate.get(j) ==currentXPostion.get(i) && storeInvisibleYCoordinate.get(j) ==currentYPostion.get(i) )
						 {
							grid.setElementAtLocation(storeInvisibleXCoordinate.get(j), storeInvisibleYCoordinate.get(j), storeInvisibleType.get(j));
							 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
							 storeInvisibleXCoordinate.remove(j);
							 storeInvisibleYCoordinate.remove(j);
							 storeInvisibleType.remove(j);
							 flag = false;
							 break;
						 }
						 else
						 {
							 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
										Constants.PATH);
							 
								grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 						 }
					}
					}
					else
					{
					grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
							Constants.PATH);
				 
					grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
					}
			 }
			 
   			boolean setFlag = true;
	 			if(( (int)grid.getElementAtLocation(nextXPostion.get(i),
	 								nextYPostion.get(i)).type >= 49 && (int)grid.getElementAtLocation(nextXPostion.get(i),
	 										nextYPostion.get(i)).type <= 57))
	 			{
	 				for(int k = 0 ; k <grid.warpZoneEntry.size();k++ )
	 				{
	 					if(nextXPostion.get(i) == grid.warpZoneEntry.get(k).x && nextYPostion.get(i) == grid.warpZoneEntry.get(k).y)
	 					 {
	 						storeWarpType.add( grid.warpZoneEntry.get(k).value);
  	 						 setFlag =false;
	 						 break;
	 					 }
	 					if(setFlag)
	 					{
	 						if(nextXPostion.get(i) == grid.warpZoneExit.get(k).x && nextYPostion.get(i) == grid.warpZoneExit.get(k).y)
	 						 {
	 							storeWarpType.add(grid.warpZoneExit.get(k).value);
	 							 setFlag =false;  
	 							 break;
	 						 }
	 					}
	 				}
 	 				storeWarpXCoordinate.add(nextXPostion.get(i));
	 				storeWarpYCoordinate.add(nextYPostion.get(i));
 	 				System.out.println();
	 				if(storeCandyXCordinate.size() > 0 )
	 				{
	 					for(int j = 0 ; j< storeCandyXCordinate.size();j++)
	 					{
	  					if(storeCandyXCordinate.get(j) ==currentXPostion.get(i) && storeCandyYCordinate.get(j) ==currentYPostion.get(i) )
	 					 {
	 						grid.setElementAtLocation(storeCandyXCordinate.get(j), storeCandyYCordinate.get(j), storeCandyType.get(j));
	 						 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
	 						 storeCandyXCordinate.remove(j);
	 						 storeCandyYCordinate.remove(j);
	 						 storeCandyType.remove(j);
	 						 flag = false;
	 						 break;
	 					 }
	 					 else
	 					 {
	 						 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
	 									Constants.PATH);
	 						 
	 							grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 	 					 }
	 					}
	 				}
	 				else
	 				{
	 				grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
	 						Constants.PATH);
	 			 
	 				grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
	 				}
	 				if(flag)
	 				if(storeWarpXCoordinate.size() > 0 )
	 				{
	 					for(int j = 0 ; j< storeWarpXCoordinate.size();j++)
	 					{
	 					if(storeWarpXCoordinate.get(j) ==currentXPostion.get(i) && storeWarpYCoordinate.get(j) ==currentYPostion.get(i) )
	 					 {
	 						grid.setElementAtLocation(storeWarpXCoordinate.get(j), storeWarpYCoordinate.get(j), storeWarpType.get(j));
	 						 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
	 						 storeWarpXCoordinate.remove(j);
	 						 storeWarpYCoordinate.remove(j);
	 						 storeWarpType.remove(j);
	 						 flag = false;
	 						 break;
	 					 }
	 					 else
	 					 {
	 						 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
	 									Constants.PATH);
	 						 
	 							grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 	 					 }
	 				}
	 				}
	 				else
	 				{
	 				grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
	 						Constants.PATH);
	 			 
	 				grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
	 				}
	 				if(flag)
	 				if(storeInvisibleXCoordinate.size() > 0 )
	 				{
	 					for(int j = 0 ; j< storeInvisibleXCoordinate.size();j++)
	 					{
	 	 					if(storeInvisibleXCoordinate.get(j) ==currentXPostion.get(i) && storeInvisibleYCoordinate.get(j) ==currentYPostion.get(i) )
	 					 {
	 						grid.setElementAtLocation(storeInvisibleXCoordinate.get(j), storeInvisibleYCoordinate.get(j), storeInvisibleType.get(j));
	 						 grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
	 						 storeInvisibleXCoordinate.remove(j);
	 						 storeInvisibleYCoordinate.remove(j);
	 						 storeInvisibleType.remove(j);
	 						 flag = false;
	 						 break;
	 					 }
	 					 else
	 					 {
	 						 grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
	 									Constants.PATH);
	 						 
	 							grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
 	 					 }
	 				}
	 				}
	 				else
	 				{
	 				grid.setElementAtLocation(currentXPostion.get(i), currentYPostion.get(i),
	 						Constants.PATH);
	 			 
	 				grid.setElementAtLocation(nextXPostion.get(i), nextYPostion.get(i), Constants.FLYING_BOMB);
	 				}
 	 				
	 			}
	  	 }
			
			
 			 if(    nextXPostion.get(i) < 0 || nextXPostion.get(i)>grid.height - 1 ||
					 nextYPostion.get(i) < 0 || nextYPostion.get(i) > grid.width - 1||
					 grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.BOMB || 
					 grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.WALL ||
					 grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.DUMB_ENEMY || 
					 grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.SENTINEL_ENEMY|| 
					 grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.WALKING_SENTINEL || 
					 grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.PLAYER_CHASER ||
					 grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.CANDY_CHASER ||
					 grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.ROBOT ||
					 grid.getElementAtLocation(nextXPostion.get(i), nextYPostion.get(i)).type == Constants.ROBOT_HOLDING_BOMB 
					
							 
					 
				)
			 	{
				  
				 for(int r = nextXPostion.get(i)-1; r<= nextXPostion.get(i)+1; r++)
				 {
					 for(int c = nextYPostion.get(i)-1; c<= nextYPostion.get(i)+1; c++)
					 {
						 if(canMove(r, c))
						 {
						 if( r>= 0 && r<grid.height  && c>= 0 && c< grid.width)
						 {
							 if(storeCandyXCordinate.size() > 0 )
								{
									for(int j = 0 ; j< storeCandyXCordinate.size();j++)
									{
										if(storeCandyXCordinate.get(j) ==r && storeCandyYCordinate.get(j) ==c )
										 {
											grid.setElementAtLocation(storeCandyXCordinate.get(j), storeCandyYCordinate.get(j), storeCandyType.get(j));
											 storeCandyXCordinate.remove(j);
											 storeCandyYCordinate.remove(j);
											 storeCandyType.remove(j);
											 flag = false;
											 break;
										 }
										 else
										 {
											 grid.setElementAtLocation(r,c, Constants.PATH);
 										 }
									 }
								}
							 else
							 {
								 grid.setElementAtLocation(r,c, Constants.PATH);
							 }
							
							 if(flag) 
							 if(storeWarpXCoordinate.size() > 0)
							 {
								 for(int k =0; k< storeWarpXCoordinate.size(); k++)
								 {
									 if(storeWarpXCoordinate.get(k) == r && storeWarpYCoordinate.get(k) == c)
									 {
										 grid.setElementAtLocation(storeWarpXCoordinate.get(k), storeWarpYCoordinate.get(k), storeWarpType.get(k));
										 storeWarpType.remove(k);
										 storeWarpXCoordinate.remove(k);
										 storeWarpYCoordinate.remove(k);
										 flag = false;
										 break;
									 }
									 else
									 {
										 grid.setElementAtLocation(r, c, Constants.PATH);
 									 }
								 }
							 }
							 if(flag) 
							 if(storeInvisibleXCoordinate.size() > 0)
							 {
								 for(int k =0; k< storeInvisibleXCoordinate.size(); k++)
								 {
									 if(storeInvisibleXCoordinate.get(k) == r && storeInvisibleYCoordinate.get(k) == c)
									 {
										 grid.setElementAtLocation(storeInvisibleXCoordinate.get(k), storeInvisibleYCoordinate.get(k), storeInvisibleType.get(k));
										 storeInvisibleType.remove(k);
										 storeInvisibleXCoordinate.remove(k);
										 storeInvisibleYCoordinate.remove(k);
										 break;
									 }
									 else
									 {
										 grid.setElementAtLocation(r, c, Constants.PATH);
									 }
								 }
							 }
							 
							 
						 }
			  			 
						 }
					 }
				 }
				 currentXPostion.remove(i);
				 currentYPostion.remove(i);
				 nextXPostion.remove(i);
				 nextYPostion.remove(i);
				 direction.remove(i);
				 --i;
			 	}
			 else{
			 
			 currentXPostion.set(i, nextXPostion.get(i)) ;
				currentYPostion.set(i, nextYPostion.get(i)) ;
			 }
 			 
 			 
 			 
 			 
 			 
 			 
 			 
 			 
		}
	}
	
	/**
	 * Returns true or false to enter that position or not.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	
	private boolean canMove(int x, int y)
	{
		if( x <0 ||x> grid.height-1 || y <0 || y > grid.width-1) 
		{
			return true;
		}
		if(grid.getElementAtLocation(x, y).type == Constants.BIG_CANDY || grid.getElementAtLocation(x, y).type == Constants.SMALL_CANDY ||((int) grid.getElementAtLocation(x, y).type >=49 && (int)grid.getElementAtLocation(x, y).type <=57) || grid.getElementAtLocation(x, y).type == 'i' )
		{
			return false;
		}
		 
		if(grid.getElementAtLocation(x, y).type == 'F' || grid.getElementAtLocation(x, y).type == Constants.DUMB_ENEMY || grid.getElementAtLocation(x, y).type == Constants.WALKING_SENTINEL || grid.getElementAtLocation(x, y).type == Constants.PLAYER_CHASER|| grid.getElementAtLocation(x, y).type == Constants.CANDY_CHASER)
		{
			grid.setElementAtLocation(x, y,Constants.PATH);
			grid.removeEnemyAfterDying(x, y);
			grid.enemiesCount--;
			return true;
		}
		if(grid.getElementAtLocation(x, y).type == Constants.ROBOT || grid.getElementAtLocation(x, y).type == Constants.ROBOT_HOLDING_BOMB )
		{
			isRobotAlive = false;
			grid.setElementAtLocation(x, y,Constants.PATH);
			return true;
		}
		else if(grid.getElementAtLocation(x, y).type == Constants.BOMB)
		{
			grid.setElementAtLocation(x, y, Constants.PATH);
			if(canMove(x+1,y))
			{
				if(x+1>= 0 && x+1<grid.height  &&
						 y>= 0 && y < grid.width)
				 grid.setElementAtLocation(x+1,y, Constants.PATH);
			}
			if(canMove(x-1,y))
			{
				if(x-1>= 0 && x-1<grid.height  &&
						 y>= 0 && y < grid.width)
				 grid.setElementAtLocation(x-1,y, Constants.PATH);
					
			}
			if(canMove(x,y+1))
			{
				if(x>= 0 && x<grid.height  &&
						 y+1>= 0 && y+1 < grid.width)
				 grid.setElementAtLocation(x,y+1, Constants.PATH);
					
			}
			if(canMove(x,y-1))
			{
				if(x>= 0 && x<grid.height  &&
						 y-1>= 0 && y-1 < grid.width)
				 grid.setElementAtLocation(x,y-1, Constants.PATH);
					
			}
			if(canMove(x+1,y+1))
			{
				if(x+1>= 0 && x+1<grid.height  &&
						 y+1>= 0 && y+1 < grid.width)
				 grid.setElementAtLocation(x+1,y+1, Constants.PATH);
					
			}
			if(canMove(x+1,y-1))
			{
				if(x+1>= 0 && x+1<grid.height  &&
						 y-1>= 0 && y-1 < grid.width)
				 grid.setElementAtLocation(x+1,y-1, Constants.PATH);
					
			}
			if(canMove(x-1,y+1))
			{
				if(x-1>= 0 && x-1<grid.height  &&
						 y+1>= 0 && y+1 < grid.width)
				 grid.setElementAtLocation(x-1,y+1, Constants.PATH);
					
			}
			if(canMove(x-1,y-1))
			{
				if(x-1>= 0 && x-1<grid.height  &&
						 y-1>= 0 && y-1 < grid.width)
				 grid.setElementAtLocation(x-1,y-1, Constants.PATH);
					
			}
		 	
		}
		 
		return true;
		
	}
}
