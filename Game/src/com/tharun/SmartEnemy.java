package com.tharun;

/**
 * 
 * @author tharun
 *
 */
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This enemy would calculate the path to the robot and chases the player robot.
 */
public class SmartEnemy extends EnemyRobot {

	PlayerRobotAuto playerAuto = new PlayerRobotAuto();
	ArrayList<HashMap<String, Integer>> path = null;

	ChaserEnemy ce = new ChaserEnemy();

	/**
	 * SmartEnemy constructor sets the type to Constants.playerChaser;
	 */
	public SmartEnemy() {
		this.type = Constants.PLAYER_CHASER;
	}

	/**
	 * We are moving based on the path returned by the function and will proceed
	 * only if its a path.
	 */
	public void move(Grid grid) {
		if (!(grid.invisibleCloakCount > 0)) {
			super.move(grid, Constants.PLAYER_CHASER);

			int currentPostion = grid.postionsOfEnemies
					.get(grid.enemyPosInList);
			try {

				int postion = ce.move(grid, Constants.PLAYER_CHASER,
						Constants.ROBOT,
						grid.postionsOfEnemies.get(grid.enemyPosInList));

				int nextXpos = postion / grid.width;
				int nextYpost = postion % grid.width;
				int currentXPos = currentPostion / grid.width;
				int currentYPos = currentPostion % grid.width;
				if (grid.getElementAtLocation(nextXpos, nextYpost).type == Constants.PATH) {
					grid.setElementAtLocation(nextXpos, nextYpost,
							Constants.PLAYER_CHASER);
					grid.setElementAtLocation(currentXPos, currentYPos,
							Constants.PATH);
					int location = grid.width * nextXpos + nextYpost;
					grid.setEnemyPostion(grid.enemyPosInList, location);
				}
				//
			} catch (Exception e) {

			}
		}
	}
}
