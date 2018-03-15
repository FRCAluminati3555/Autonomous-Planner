package Entity.FreeMoving.Action.Robot;

import com.Engine.Util.Vectors.Vector2f;

import Entity.FreeMoving.Robot;
import Entity.FreeMoving.Action.Action;
import Utils.Util;

public class MoveDistance extends Action {
	private Vector2f target;
	private Vector2f step;
	
	private float distance, speed;
	
	public MoveDistance(float distance, float speed) {
		setDistance(distance);
		setSpeed(speed);
	}
	
	@Override
	public void start(Robot robot) {
		super.start(robot);
		
		Vector2f startLocation = robot.getFrontAndCenterPosition();
		
//		System.out.println(robot.getPosAngle());
		
		float angle = (float) Math.toRadians(robot.getAngle());
		target = new Vector2f(-Math.cos(angle) * distance + startLocation.x, Math.sin(angle) * distance + startLocation.y);
		
//		System.out.println(target.subtract(startLocation));
		
		step = target.subtract(startLocation).divide(target.subtract(startLocation).length()).multiply(speed);
	}
	
	@Override
	public void update(float delta) {
		if(Util.withinRange(robot.getFrontAndCenterPosition(), target, .2f)) {
			complete = true;
		} else {
			robot.push(step, delta);
		}
	}
	
	public Vector2f getTarget() {
		return target == null ? new Vector2f() : target;
	}
	
	public String toString() {
		return "Forward " + getDistanceMeters() + " " + getSpeed();
	}
	
	public float getDistanceMeters() { return Util.tilesToMeters(distance); }
	public float getDistanceTiles() { return distance; }
	
	public void setDistance(float distance) {
		this.distance = Util.metersToTiles(distance);
	}
	
	public float getSpeed() { return Util.tilesPerSecondToMetersPerSecond(speed); }
	public void setSpeed(float speed) { 
		this.speed = Util.metersPerSecondToTilesPerSecond(speed);
	}
}
