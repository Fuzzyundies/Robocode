package sj;
import robocode.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Dodger3 - a robot by (your name here)
 */
public class Dodger5 extends Robot
{
	double prevEnergy = 100, distance, sentryX, sentryY, distanceSentry, bearing;
	int movementDirection = 1,gunDirection = 1;
	
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		turnRadarRight(360);
		while(true) {
		move();
		}
	}

	public void move(){
		gunDirection = -gunDirection;
		if(distance > 150 && distanceSentry > 150)
		{
			movementDirection = -movementDirection;
			turnRight(bearing+90-30*movementDirection);
			ahead((distance/4+25)*movementDirection);
			turnGunRight(180*gunDirection);
		}
		else
		{
			turnRight(bearing+90-30*movementDirection);
			movementDirection = -movementDirection;
			ahead((distance/4+40)*movementDirection);
			turnGunRight(180*gunDirection);
		}

	}
	public void onScannedRobot(ScannedRobotEvent e) {
		if(!e.isSentryRobot()){
	    double changeInEnergy =
	      prevEnergy-e.getEnergy();
		  bearing = e.getBearing();
		  distance = e.getDistance();
			if (changeInEnergy>0 &&changeInEnergy<=3) {
		         movementDirection = -movementDirection;
		     }
		int i = 1;
		if(distance <=150)
			i = 3;
		else if(distance <=550)
			i = 2;
		else 
			i = 1;
	    fire ( i ) ;
	    
	    // Track the energy level
	    prevEnergy = e.getEnergy();
		}
		else
		{
			distanceSentry = e.getDistance();
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		//back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		movementDirection = -movementDirection;
	}	
}
