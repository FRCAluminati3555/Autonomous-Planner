package Entity.FreeMoving.Action.Robot;

import com.Engine.Util.Vectors.Vector2f;

import Entity.FreeMoving.Robot;
import Entity.FreeMoving.Action.MultiAction;
import Main.Handler;

public class MoveToAction extends MultiAction {
	private Robot robot;
	private Vector2f target;
	
	public MoveToAction(Handler handler, Robot robot, Vector2f target) {
		super(handler);
		this.robot = robot;
		this.target = target;
	}
	
	@Override
	public void start() {
		super.start();
		
		subActions.add(new TurnToAction(handler, robot, target));
		subActions.add(new GoToAction(handler, robot, target));
		
//		entity.addAction(new TurnToAction(handler, entity, worldObject.getPosition2D()));
	}
}
