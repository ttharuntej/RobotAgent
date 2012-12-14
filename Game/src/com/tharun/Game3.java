package com.tharun;

/**
 * @author Tharun Tej Tammineni
 * A01626417.
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The Class Game1.
 */
public class Game3 {
	public static int playerMode = 0;
	public static int gridType = 0;
	static String textFile = null;
	public final static int INPUT_SIZE = 1001;
	public final static int MODE_VARIABLE_SIZE = 1;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String args[]) {
		Grid grid;
		PlayerRobotManual playerRobot = new PlayerRobotManual();
		GeneratedMap gm = new GeneratedMap();
		char[] mode = new char[MODE_VARIABLE_SIZE];
		while (true) {
			int i = 0, j = 0, index = 0;
			char[][] elementsString = null;
			String input[] = new String[INPUT_SIZE];
			displayMenu();
			if (gridType == 2) // Option 1 loading from file.
			{
				try {
					gm.createMap();
				} catch (IOException e) {
					e.printStackTrace();
				}
				textFile = Constants.TEXT_FILE_LOCATION;
			}
			FileInputStream fstream = null;
			try {
				fstream = new FileInputStream(textFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine[] = null;

			try {
				strLine = br.readLine().split(" ");
			} catch (IOException e) {
				e.printStackTrace();
			}
			int rows = Integer.parseInt(strLine[0]);
			int columns = Integer.parseInt(strLine[1]);
			int timelimit = Integer.parseInt(strLine[2]);
			for (j = 0; j < rows; j++)
				try {
					input[j] = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}

			elementsString = new char[j][columns];
			/**
			 * Splitting each line of input that was read and storing in
			 * elementsString array so start will be at starting location (0,0).
			 */
			for (i = 0; i <= j; i++) {
				if (input[i] != null) {
					elementsString[index] = input[i].toCharArray();
					index++;
				}
			}
			grid = new Grid(rows, columns, elementsString, timelimit);// Forming
																		// the
																		// Grid.

			if (playerMode == 2)// Robot Playing in Auto-Mode
			{
				PlayerRobotAuto playerRobotAuto = new PlayerRobotAuto();
				playerRobotAuto.move(grid);
			} else if (playerMode == 1)// Robot Playing in Manual-Mode
			{
				playerRobot.move(grid); // Calling the move method of Player
										// Robot.
			}
		}
	}

	// Used to display the Menu for User.
	public static void displayMenu() {
		textFile = null;
		playerMode = 0;
		boolean flag = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome To Play the Exciting Game: Challenge the Enemy bots");

		while (flag) {
			System.out.println("Choose the player mode you want to play");
			System.out.println("1. Manual Robot");
			System.out.println("2. Auto Robot");
			System.out.println("3. Help");
			System.out.println("4. About");
			System.out.println("5. Exit");
			System.out.println();

			playerMode = scan.nextInt();
			int digit = (int) playerMode;

			if (!(playerMode >= 1 && playerMode <= 5)) {
				System.out
						.println("You have entered wrong choice. Please enter either 1 or 2 or 3 or 4 or 5");
				flag = true;
			} else {
				flag = false;
			}
			switch (playerMode) {

			case 3:
				try {flag = true;
					help();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			case 4:
				System.out
						.println("Name of the Game: Challenge the Enemy bots.");
				System.out.println("Game Developed by Tharun Tej Tammineni");
				System.out.println("A01626417");
				flag = true;
				break;
			case 5:
				System.out.println("Game Exited Bye Bye..");
				System.exit(0);

			}
		}
		flag = true;
		while (flag) {
			System.out
					.println("1. Start the game with a map loaded from a file.");
			System.out.println("2. Start the game with a generated maze map.﻿");
			System.out.println("3. Help");
			System.out.println("4. About");
			System.out.println("5. Exit");
			System.out.println();
			gridType = scan.nextInt();
			if (!(gridType >= 1 && gridType <= 5)) {
				System.out
						.println("You have entered wrong choice. Please enter either 1 or 2 or 3");
				flag = true;
			} else {
				flag = false;
			}

			switch (gridType) {
			case 1:
				System.out.println("Enter the path of the file:It should be as follows Driver:\\Folder\\FileName.txt  \n For Example:C:\\Maps\\generatedMap.txt ");
				System.out.println();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				try {
					textFile = br.readLine();
				} catch (IOException e) {
				 	e.printStackTrace();
				}
			case 2:
				break;
			case 3:
				try {
					help();
				} catch (IOException e) {
			 		e.printStackTrace();
				}
				flag = true;
				break;
			case 4:
				System.out
						.println("Name of the Game: Challenge the Enemy bots.");
				System.out.println("Game Developed by Tharun Tej Tamminei");
				System.out.println("A01626417");
				flag = true;
				break;
			case 5:
				System.out.println("Game Exited Bye Bye..");
				System.exit(0);

			}
		}

	}

	private static void help() throws IOException {
		FileInputStream fstream = new FileInputStream(
				Constants.HELP_FILE_LOCATION);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			// Print the content on the console
			System.out.println(strLine);
		}
	}

}
