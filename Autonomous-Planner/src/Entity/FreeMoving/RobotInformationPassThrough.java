package Entity.FreeMoving;

import java.util.ArrayList;

import Entity.FreeMoving.Action.Action;
import Main.Handler;
import Utils.TwoKeyMap;

public class RobotInformationPassThrough {
	private Handler handler;
	private Spawn spawn;
	private String ownership;
	
	private ArrayList<Spawn> spawns;
	private int teamNumber;
	
	private TwoKeyMap paths;
	private ArrayList<Action> path;
	
	public RobotInformationPassThrough(Handler handler, int teamNumber) {
		this.handler = handler;
		this.teamNumber = teamNumber;
		
		spawn = new Spawn();
		spawns = new ArrayList<>();
		
		paths = new TwoKeyMap();
		path = new ArrayList<>();
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
			robot.setSpawn(spawn);
		}
		
		if(ownership != null) {
			setPath(spawn.getName(), ownership);
		}
	}
	
	public void setOwnership(String ownership) {
		this.ownership = ownership;
		
		if(spawn != null) {
			setPath(spawn.getName(), ownership);
		}
	}
	
	public TwoKeyMap getPaths() { return paths; }
	public ArrayList<Action> getPath() { return path; }
	
	public void setPath(String location, String ownership) { setPath(paths.get(location, ownership)); }
	public void setPath(ArrayList<Action> path) { 
		this.path = path;
		
		Robot robot = handler.getWorld().getRobot(teamNumber);
		
		if(robot != null)
			robot.setPath(path);
		
		handler.getFrame().getRobotPanel(teamNumber).setPathList(path);
	}
	
	public ArrayList<Spawn> getSpawns() { return spawns; }
	public Spawn getSpawn() { return spawn; }
}