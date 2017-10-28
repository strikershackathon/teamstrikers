package Strikers;
import robocode.AlphaBot;
import java.awt.*;
import robocode.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RAMEE
 */
public class Strikers extends AlphaBot{
    
    boolean peek; 
	double moveAmount; 

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
		turnGunRight(90);
		turnRight(90);

		while (true) {
			peek = true;
			ahead(moveAmount);
			peek = false;
			turnRight(90);
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
		fire(4);
		if (peek) {
			scan();
		}
	}
}
