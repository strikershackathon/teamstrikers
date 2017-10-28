/**
 * Copyright (c) 2001-2017 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 */



import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;
import robocode.CharlieBot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;


/**
 * @author Choxmi
 */
public class TeamStrikers extends CharlieBot {
	int turnDirection = 1; // Clockwise or counterclockwise
        double initEnergy = 0;
        boolean wallhit = false;
	/**
	 * run: Spin around looking for a target
	 */
	public void run() {
                initEnergy = getEnergy();
		// Set colors
		setBodyColor(Color.lightGray);
		setGunColor(Color.gray);
		setRadarColor(Color.darkGray);

		while (true) {
			turnRight(10 * turnDirection);
		}
	}

	/**
	 * onScannedRobot:  We have a target.  Go get it.
	 */
	public void onRobotDetected(ScannedRobotEvent e) {

		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}

		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}
		turnRight(e.getBearing());

                if(getEnergy()<(initEnergy*0.5)){
                    turnRight(30);
                    ahead(40);
                }else{
                
		// Determine a shot that won't kill the robot...
		// We want to ram him instead for bonus points
		double distance = e.getDistance();
                if(distance > 800) //this conditions adjust the fire force according the distance of the scanned robot.
                    fire(1);
                else if(distance > 600 && distance <= 800)
                    fire(2);
                else if(distance > 400 && distance <= 600)
                    fire(3);
                else if(distance > 200 && distance <= 400)
                    fire(4);
                else if(distance < 200)
	        fire(5);
		ahead(40);
		scan(); // Might want to move ahead again!
                }
	}

	/**
	 * onHitRobot:  Turn to face robot, fire hard, and ram him again!
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}
		turnRight(e.getBearing());

		// Determine a shot that won't kill the robot...
		// We want to ram him instead for bonus points
		if (e.getEnergy() > 16) {
			fire(3);
		} else if (e.getEnergy() > 10) {
			fire(2);
		} else if (e.getEnergy() > 4) {
			fire(1);
		} else if (e.getEnergy() > 2) {
			fire(.5);
		} else if (e.getEnergy() > .4) {
			fire(.1);
		}
		ahead(40); // Ram him again!
	}
        
        @Override
        public void onHitByBullet(HitByBulletEvent event) {
            super.onHitByBullet(event);
                turnRight(90);
                ahead(75);

                if(wallhit){
                    back(75);
                    wallhit = false;
                }
        }

        @Override
        public void onHitWall(HitWallEvent event) {
            wallhit = true;
        }
}
