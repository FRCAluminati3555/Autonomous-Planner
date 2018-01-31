package Entity.FreeMoving.AI.Action.Human;

import com.Engine.Util.Vectors.Vector2f;

import Entity.FreeMoving.Robot;
import Entity.FreeMoving.AI.Action.Action;
import Main.Handler;
import Utils.Util;

public class TurnToAction extends Action {
	private Robot robot;
	private Vector2f target;
	
	private float targetAngle;
	private float step;
	
	public TurnToAction(Handler handler, Robot robot, Vector2f target) {
		super(handler);
		
		this.robot = robot;
		this.target = target;
	}
	
	@Override
	public void start() {
		super.start();
		
		targetAngle = Util.getPosAngle(robot.getPosition2D(), target);

		System.out.println("Current: " + robot.getPosAngle() + ", Target: " + targetAngle);
		
		float angularSpeed = robot.getMovementSpeed().x / robot.getDistanceBetweenWheels();
		float difference = robot.getPosAngle() - targetAngle;
		if(difference < 0)
			difference += 360;
		
		step = (float) (Math.toDegrees(difference > 180 ? angularSpeed : -angularSpeed));
	}
	
	@Override
	public void update(float delta) {
		robot.rotate(step * delta);
		
		if(Util.withinRange(robot.getPosAngle(), targetAngle, 3))
			complete = true;
	}
}
