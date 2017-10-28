import java.awt.*;
import robocode.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Choxmi
 */
public class TeamStrikers extends BravoBot{
    
    boolean peek; 
    double moveAmount; 
    boolean wallhit = false;

	public void run() {
		
		Color purple = new Color(135,62,141);
		Color white = new Color(255,255,255);
		setBodyColor(purple);
		setGunColor(white);
		setRadarColor(purple);
		setBulletColor(white);
		setScanColor(purple);

		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		peek = false;

		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		peek = true;
                
		turnRight(90);
               
                

		while (true) {
			peek = true;
			ahead(moveAmount);
			peek = false;
			turnRight(35);
		}
	}
	
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		} 
		else {
			ahead(100);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
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
            
		if (peek) {
			scan();
		}
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
