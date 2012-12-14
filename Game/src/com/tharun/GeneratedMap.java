package com.tharun;
/**
 * @author Tharun Tej Tammineni
 * A01626417.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
 
 
/**
 * This Class is used to generate a map  automatically.
 *  
 */
public class GeneratedMap 
{
	int INPUT_SIZE = 1001;
	private   int[][] gridMap;
	Random r = new Random();
	char[] candy = { Constants.SMALL_CANDY, Constants.BIG_CANDY};
	Scanner sc = new Scanner(System.in);
	int rows, columns, timeLimit;
	private enum DIRECTIONS 
	{
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);  // Calls the constructor of the Directions which is defined as ENum
		private final int bit;
		private final int dx;
		private final int dy;
		private DIRECTIONS opposite;
 
		//  static initializer to resolve forward references
		static 
		{
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
 
		private DIRECTIONS(int bit, int xDirection, int yDirection) 
		{
			this.bit = bit;
			this.dx = xDirection;
			this.dy = yDirection;
		}
	};
 
	
	private void formMaze(int rows, int columns)  
	{
		this.rows = rows/2;
		this.columns = columns/2;
		gridMap = new int[this.rows][this.columns];
		generateMaze(0, 0);
	}
	public void createMap() throws IOException
	{
		do
		{
		System.out.println("Enter the number of Rows.");
		columns = sc.nextInt();
		System.out.println("Enter the number of Columns.");
		rows = sc.nextInt();
		if(rows <=3 || columns <=3)
			System.out.println("The values of Rows and Columns should be greater than 3. Please redo..");
		System.out.println();
		} while(columns <=3 || rows <=3); // Rows and columns should be greater than 3.
		 
		
//		System.out.println("Enter the Time Limit");
		timeLimit =columns* rows; //Time Limit is calculated based on the product of rows and columns. 
		 //Write the maze to file
		FileWriter fstream = new FileWriter(Constants.TEXT_FILE_LOCATION);
		BufferedWriter out = new BufferedWriter(fstream);
		 
		  
		  if(columns%2 == 0)
			  out.write(String.valueOf(columns+1)+" ");
			  else
				  out.write(String.valueOf(columns)+" ");
		  if(rows % 2 == 0)
			  out.write(String.valueOf(rows+1)+ " ");
		  else
			  out.write(String.valueOf(rows)+ " ");
		    
		  
		  out.write(String.valueOf(timeLimit)+ " ");
		  out.write("\n");
		  formMaze(rows,columns) ;
			
		  for (int i = 0; i < columns; i++) 
			{
				 for (int j = 0; j < rows; j++) 
				{
					out.write((gridMap[j][i] & 1) == 0 ? "XX" : "X.");
				}
				out.write("X");
				out.write("\n");
				
			 	for (int j = 0; j < rows; j++) 
				{
					out.write((gridMap[j][i] & 8) == 0 ? "X." : "..");
				}
				out.write("X");
				out.write("\n");
				
			}
			 	for (int j = 0; j < rows; j++) 
			{
				out.write("XX");
			}
			out.write("X");
			out.write("\n");
			out.close();
			createFinalMap();
	}
	
	

	private void generateMaze(int currentXposition, int currentYpostion) 
	{
		DIRECTIONS[] dirs = DIRECTIONS.values();
		Collections.shuffle(Arrays.asList(dirs));
		for (DIRECTIONS dir : dirs)
		{
			int nextXposition = currentXposition + dir.dx;
			int nextYposition = currentYpostion + dir.dy;
			if (checkBounds(nextXposition, rows) && checkBounds(nextYposition, columns)
					&& (gridMap[nextXposition][nextYposition] == 0)) 
			{
				gridMap[currentXposition][currentYpostion] |= dir.bit;
				gridMap[nextXposition][nextYposition] |= dir.opposite.bit;
				generateMaze(nextXposition, nextYposition);
			}
		}
	}
 
	private static boolean checkBounds(int v, int upper) 
	{
		return (v >= 0) && (v < upper);
	}

	
	private void createFinalMap() throws IOException 
	{
		FileInputStream stream = new FileInputStream(Constants.TEXT_FILE_LOCATION);
		DataInputStream din = new DataInputStream(stream);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(din));
		String stringLine[];
		int i,j,index=0;
		String input[] = new String[INPUT_SIZE];
		char[][] elementsString = null;
		
		ArrayList<Integer> pathXCoordinate = new ArrayList<Integer>();
		ArrayList<Integer> pathYCoordinate = new ArrayList<Integer>();
		stringLine = bufferedReader.readLine().split(" ");
		int r = Integer.parseInt(stringLine[0]);
		int c = Integer.parseInt(stringLine[1]);
		int timeLimit = Integer.parseInt(stringLine[2]);
		for ( j = 0; j < r; j++)
			input[j] = bufferedReader.readLine();

		elementsString = new char[j][c];
		
		for (i = 0; i < j; i++)
		{
			if (input[i] != null) 
			{
				elementsString[index] = input[i].toCharArray();
				index++;
			}
		}
		
		for(i=0;i<r;i++)
		{
			for(j=0;j<c;j++)
			{
				if(elementsString[i][j]==Constants.PATH)
				{
					pathXCoordinate.add(i);
					pathYCoordinate.add(j);
				}
			}
		}
		Random random = new Random();
		int randIndex= Math.abs(random.nextInt()%(pathXCoordinate.size()-1));
	 	elementsString[pathXCoordinate.get(randIndex)][pathYCoordinate.get(randIndex)] =Constants.ROBOT;
		pathXCoordinate.remove(randIndex);
		pathYCoordinate.remove(randIndex);
		if(pathXCoordinate.size() == 1)
		{
			int randomIndex= Math.abs(random.nextInt()%(pathXCoordinate.size()));
			elementsString[pathXCoordinate.get(randomIndex)][pathYCoordinate.get(randomIndex)] = candy[Math.abs(random.nextInt()%2)]; 
		}
		else
		{
			for(int k = 0; k< pathXCoordinate.size()/2; k++)
			{
				int randomIndex= Math.abs(random.nextInt()%(pathXCoordinate.size()-1));
				elementsString[pathXCoordinate.get(randomIndex)][pathYCoordinate.get(randomIndex)] = candy[Math.abs(random.nextInt()%2)]; 
			}
			
		}
		
		
		FileWriter fstream = new FileWriter(Constants.TEXT_FILE_LOCATION);
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(String.valueOf(r)+" ");
		  out.write(String.valueOf(c)+" ");
		  out.write(String.valueOf(timeLimit));
		  out.write("\n");
		  for(i=0;i<r;i++)
			{
				for(j=0;j<c;j++)
				{
					out.write(elementsString[i][j]);
				}
				out.write("\n");
			}
		  out.close();
	}
	
	
}