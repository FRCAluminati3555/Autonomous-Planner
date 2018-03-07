package Entity.FreeMoving.Action.Robot;

import com.Engine.Util.Vectors.Vector2f;

import Entity.FreeMoving.Entity;
import Entity.FreeMoving.Robot;
import Entity.FreeMoving.Action.Action;
import Main.Handler;
import Utils.Util;

public class GoToAction extends Action {
	private Robot robot;
	private Vector2f target;
	private Vector2f step;
	
	public GoToAction(Handler handler, Robot robot, Vector2f target) {
		super(handler);
		
		this.robot = robot;
		this.target = target;
	}
	
	@Override
	public void start() {
		super.start();
		
		Vector2f startLocation = robot.getPosition2D();
		
		step = target.subtract(startLocation).divide(target.subtract(startLocation).length()).multiply(robot.getMovementSpeed());
	}
	
	@Override
	public void update(float delta) {
		if(Util.withinRange(robot.getPosition2D(), target, .2f)) {
			complete = true;
//			robot.setPosition2D(toGridLocation);
		} else {
			robot.push(step, delta);
		}
	}
}

class Move extends Action {
	private Entity entity;
	
	private Vector2f toGridLocation;
	private Vector2f startLocation;
	private Vector2f step;
	
	public Move(Handler handler, Entity entity, Vector2f toGridLocation) {
		super(handler);
		
		this.entity = entity;
		this.toGridLocation = toGridLocation;
	}
	
	public Move(Handler handler, Entity entity, int x, int z) {
		this(handler, entity, new Vector2f(x, z));
	}

	@Override
	public void start() {
		super.start();
		
		startLocation = entity.getPosition2D();
		
		step = toGridLocation.subtract(startLocation).divide(toGridLocation.subtract(startLocation).length()).multiply(entity.getMovementSpeed());
	}
	
	@Override
	public void update(float delta) {
		
	}
}
