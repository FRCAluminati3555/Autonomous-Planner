package Entity.FreeMoving.Action.Robot;

import Entity.FreeMoving.Robot;
import Entity.FreeMoving.Action.Action;

public class WaitAction extends Action {
	private long startTime;
	private float duration;
	
	public WaitAction(float duration) {
		this.duration = duration;
	}

	@Override
	public void start(Robot robot) {
		super.start(robot);
		
		startTime = System.currentTimeMillis();
	}
	
	public String toString() {
		return "Wait " + duration;
	}
	
	@Override
	public void update(float delta) {
		if(System.currentTimeMillis() - startTime > duration * 1000.0)
			complete = true;
	}
	
	public void setTime(float seconds) { this.duration = seconds; }
	public float getTime() { return duration; }
}
