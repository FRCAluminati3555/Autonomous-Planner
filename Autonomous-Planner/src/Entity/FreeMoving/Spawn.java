package Entity.FreeMoving;

import com.Engine.Util.Vectors.Vector2f;

import Utils.Util;

public class Spawn {
	public static float startZ = 1;
	public static float xOffSet = 1;
	
	private String name;
	private Vector2f location;

	private Spawn(String name, Vector2f location) {
		this.name = name;
		this.location = location;
	}
	
	public Spawn(String name, float distanceFromRightWall) {
		this(name, new Vector2f(Util.metersToTiles(distanceFromRightWall) + xOffSet, startZ));
	}
	
	public Spawn() {
		this("",  0);
	}
	
	public Vector2f getLocation() { return location; }
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public void setDistanceFromRightWallTiles(float distance) { this.location = new Vector2f(distance + xOffSet, startZ); }
	public void setDistanceFromRightWallMeters(float distanceMeters) { this.location = new Vector2f(Util.metersToTiles(distanceMeters) + xOffSet, startZ); }
	
	public float getDistanceMeters() { return Util.tilesToMeters(location.x - xOffSet); }
}
