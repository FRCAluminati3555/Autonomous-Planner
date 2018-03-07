package Entity.FreeMoving.Action.Robot;

import Entity.FreeMoving.Action.Action;
import Main.Handler;

public class WaitAction extends Action {
	private long startTime;
	private float duration;
	
	public WaitAction(Handler handler, float duration) {
		super(handler);
		
		this.duration = duration;
	}

	@Override
	public void start() {
		super.start();
		
		startTime = System.currentTimeMillis();
	}
	
	@Override
	public void update(float delta) {
		if(System.currentTimeMillis() - startTime > duration * 1000.0)
			complete = true;
	}
}
