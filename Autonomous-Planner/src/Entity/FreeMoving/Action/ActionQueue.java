package Entity.FreeMoving.Action;

import java.util.ArrayList;

import Entity.FreeMoving.Robot;

public class ActionQueue {
	private Robot robot;
	private ArrayList<Action> actions;
	
	public ActionQueue(Robot robot) {
		this.robot = robot;
		
		actions = new ArrayList<>();
	}

	public void update(float delta) {
		if(!actions.isEmpty()) {
			if(actions.get(0).isComplete()) {
				actions.remove(0);
			} else if(!actions.get(0).hasStarted()) {
				actions.get(0).start(robot);
			} else
				actions.get(0).update(delta);
		}
	}
	
	public ArrayList<Action> getActions() { return actions; }
	public boolean add(Action a) { return actions.add(a); }
	
	public void setActions(ArrayList<Action> actions) { this.actions = actions; }
}
