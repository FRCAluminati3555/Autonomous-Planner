package Entity.FreeMoving;

import com.Engine.Util.Vectors.Vector2f;

public class Spawn {
	public static float startZ = 155;
	
	private String name;
	private Vector2f location;

	private Spawn(String name, Vector2f location) {
		this.name = name;
		this.location = location;
	}
	
	public Spawn(String name, float distanceFromLeftWall) {
		this(name, new Vector2f(distanceFromLeftWall, startZ));
	}
	
	public Spawn() {
		this("",  0);
	}
	
	public Vector2f getLocation() { return location; }
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	public void setDistanceFromLeftWall(float distance) { this.location = new Vector2f(distance, startZ); }
}
