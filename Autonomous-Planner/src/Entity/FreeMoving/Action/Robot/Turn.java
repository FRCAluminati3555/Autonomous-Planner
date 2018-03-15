package Entity.FreeMoving.Action.Robot;

import Entity.FreeMoving.Robot;
import Entity.FreeMoving.Action.Action;
import Utils.Util;

public class Turn extends Action {
	private float angle, speed;
	private float step;
	
	private float targetAngle;
	
	public Turn(float angle, float speed) {
		setAngle(angle);
		setSpeed(speed);
	}
	
	@Override
	public void start(Robot robot) {
		super.start(robot);
		
		float angularSpeed = speed / robot.getDistanceBetweenWheels();
		float difference = robot.getPosAngle() - angle;
		
		if(difference < 0)
			difference += 360;

		targetAngle = difference;
		
		step = (float) (Math.toDegrees(difference > 180 ? angularSpeed * Math.copySign(1, angle) : -angularSpeed * Math.copySign(1, angle)));
	}
	
	@Override
	public void update(float delta) {
		robot.rotate(step * delta);
		
		if(Util.withinRange(robot.getPosAngle(), targetAngle, 1))
			complete = true;
	}
	
	public String toString() {
		return "Turn " + angle + " " + getSpeed();
	}
	
	public float getAngle() { return angle; }
	public void setAngle(float angle) { this.angle = angle; }
	
	public float getSpeed() { return Util.tilesPerSecondToMetersPerSecond(speed); }
	public void setSpeed(float speed) { 
		this.speed = Util.metersPerSecondToTilesPerSecond(speed);
	}
}
