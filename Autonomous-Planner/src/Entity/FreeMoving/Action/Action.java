package Entity.FreeMoving.Action;

import Entity.FreeMoving.Robot;
import Entity.FreeMoving.Action.Robot.MoveDistance;
import Entity.FreeMoving.Action.Robot.Turn;
import Entity.FreeMoving.Action.Robot.WaitAction;
import Utils.Util;

public abstract class Action {
	@FunctionalInterface
	interface GenAction {
		public Action genAction(String[] components);
	}
	
	public static enum Actions {
		Forward("Forward", components -> {
			return new MoveDistance(Util.parseFloat(components[components.length - 2]), Util.parseFloat(components[components.length - 1]));
		}),
		
		Turn("Turn", components -> {
			return new Turn(Util.parseFloat(components[components.length - 2]), Util.parseFloat(components[components.length - 1]));
		}),
		
		Wait("Wait", components -> {
			return new WaitAction(Util.parseFloat(components[components.length - 1]));
		});
		
		private String name;
		private GenAction generator;
		
		private Actions(String name, GenAction generator) {
			this.name = name;
			this.generator = generator;
		}
		
		public String getName() { return name; }
		public Action genAction(String[] components) { return generator.genAction(components); }
	}
	
	protected Robot robot;
	protected boolean started;
	protected boolean complete;
	
	public Action() {
		
	}

	public void start(Robot robot) {
		this.robot = robot;
		
		started = true;
	}
	
	public static Action parseAction(String string) {
		//Drag Turn Right 15 .5 -> Drag Turn <Direction> <Degrees> <Speed (meters per second)>
		//Center Turn Right 10 1 -> Center Turn <Direction> <Degrees> <Speed (meters per second)>
		//Forward 10 1.5 -> Forward <distance> <Speed (meters per second)>
		//Wait 10 -> Wait <Seconds>
		
		String[] values = string.split(" ");
		String name = values[0];
		
		for(Actions a : Actions.values()) 
			if(a.getName().equals(name)) 
				return a.genAction(values);
		return null;
	}
	
	public abstract void update(float delta);
	
	public boolean isComplete() { return complete; }
	public boolean hasStarted() { return started; }
	
	public void setComplete(boolean complete) { this.complete = complete; }
	public void setStarted(boolean started) { this.started = started; }
}
