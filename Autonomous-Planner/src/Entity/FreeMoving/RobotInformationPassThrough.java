package Entity.FreeMoving;

import java.util.ArrayList;

import Main.Handler;

public class RobotInformationPassThrough {
	private Handler handler;
	private Spawn spawn;
	
	private ArrayList<Spawn> spawns;
	private int teamNumber;
	
	public RobotInformationPassThrough(Handler handler, int teamNumber) {
		this.handler = handler;
		this.teamNumber = teamNumber;
		
		spawn = new Spawn();
		spawns = new ArrayList<>();
	}
	
	public int getTeamNumber() { return teamNumber; }
	
	public void addSpawn(Spawn spawn) { spawns.add(spawn); }
	public void setSpawn(Spawn spawn) {
		if(!spawns.contains(spawn)) 
			spawns.add(spawn);
		
		this.spawn = spawn;
		
		Robot robot = handler.getWorld().getRobot(teamNumber);
		
		if(robot != null) {
			robot.setPosition2D(spawn.getLocation());
		}
	}
	
	public ArrayList<Spawn> getSpawns() { return spawns; }
	public Spawn getSpawn() { return spawn; }
}