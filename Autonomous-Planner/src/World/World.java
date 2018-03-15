package World;

import java.util.ArrayList;

import com.Engine.PhysicsEngine.Render.Vector.VectorModel;
import com.Engine.RenderEngine.Lights.Light;
import com.Engine.RenderEngine.Util.Camera;
import com.Engine.Util.Vectors.Vector2f;
import com.Engine.Util.Vectors.Vector3f;

import Entity.FreeMoving.Robot;
import Entity.WorldObjects.Switch;
import Entity.WorldObjects.WorldObject;
import Entity.WorldObjects.Lot.Field;
import Input.CameraMovement;
import Main.Assets;
import Main.Game;
import Main.Handler;
import Utils.Util;
import World.Tiles.Render.TileInstanceModel;

public class World {
	private Handler handler;

	private Camera camera;
	private CameraMovement cameraMovement;
	
	private ArrayList<Light> sun;
	private Field field;

	private Robot[] robots;
	
	private int replaceIndex;
	private int replaceTeamNumber;
	
	public World(Handler handler) {
		this.handler = handler;
		
		camera = new Camera(70, (float) handler.getWidth() / (float) handler.getHeight(), 0.3f, 1000);
		cameraMovement = new CameraMovement(handler, camera, new Vector3f(40, 40, -30), 10, 10, .15f);
//		cameraMovement = new CameraMovement(handler, camera, new Vector3f(0, 10, 10), 10, 10, .15f);
		
		sun = new ArrayList<>();
	}
	
	public void init() {
		VectorModel.init(Game.physicsShader);
		
		field = new Field(handler, new Vector3f(), new Vector2f(82, 80));
		sun.add(new Light(new Vector3f(41, 100, 20), new Vector3f(1), new Vector3f(.5, 0, 0)));
		
		robots = new Robot[3];
		
//		robots[0] = new Robot(handler, 3555, 40);
//		robots[1] = new Robot(handler, 1729, 70);
//		robots[2] = new Robot(handler, 1010, 10);
		
		s = new Switch(handler, field);
		
		place(s, new Vector2f(22, 36));
		replaceIndex = -1;
		
		replaceRobot(0, 3555);
		
//		robots[0] = new Robot(handler, 3555);
//		handler.getFrame().updateRobotNumbers(3555);
	}
	
	private Switch s;
	
	public boolean place(WorldObject object, Vector3f position, float angle) {
		object.setAngle(angle);
		object.setPosition3D(position);
		return object.addToTile(object.getLot().getTile(object.getPosition3D().subtract(object.getWidth() / 2, 0, object.getHeight() / 2)));
	}
	
	public boolean place(WorldObject object, Vector2f position) {
		object.setPosition2D(position);
		return object.addToTile(object.getLot().getTile(object.getPosition3D().subtract(object.getWidth() / 2, 0, object.getHeight() / 2)));
	}
	
	public boolean place(WorldObject object) {
		return object.addToTile(object.getLot().getTile(object.getPosition3D().subtract(object.getWidth() / 2, 0, object.getHeight() / 2)));
	}
	
	public void drawLine(Vector3f from, Vector3f to, Vector3f color) {
		VectorModel.renderVector(to.subtract(from).multiply(-1, 1, -1), from, color);
	}
	
	public void update(float delta) {
		cameraMovement.update(delta);

		if(replaceIndex != -1) {//Need to make the robot on the OpenGL thread, not the Swing / AWT Thread
			robots[replaceIndex] = new Robot(handler, replaceTeamNumber);
			replaceIndex = -1;
		}
		
		for(Robot robot : robots) {
			if(robot != null)
				robot.update(delta);
		}
		
		field.update(delta);
	}
	
	public void render() {
		field.render();
		
		for(Robot robot : robots) {
			if(robot != null)
				robot.render();
		}
		
//		drawLine(new Vector3f(s.getPosition3D().x, 0, 0), s.getPosition3D().subtract(0, 0, s.getHeight() / 2), new Vector3f(1, 0, 0));
		
//		System.out.println(new Vector3f(s.getPosition3D().x, 0, 0).distance(s.getPosition3D().subtract(0, 0, s.getHeight() / 2)));
		
		Assets.defaultShader.bind();
		Assets.defaultShader.loadLights(sun);
		TileInstanceModel.TILE_SHADER.bind();
		TileInstanceModel.TILE_SHADER.loadLights(sun);
	}
	
	public void replaceRobot(int index, int teamNumber) {
//		robots[index] = new Robot(handler, teamNumber, x);
		this.replaceIndex = index;
		this.replaceTeamNumber = teamNumber;
	}
	
	public Robot[] getRobots() { return robots; }
	
	public Robot getRobot(int teamNumber) { 
		for(Robot robot : robots) 
			if(robot != null && robot.getTeamNumber() == teamNumber)
				return robot;
		return null;
	}
	
	public Field getField() { return field; }
	
	public Camera getCamera() { return camera; }
	public CameraMovement getCameraMovement() { return cameraMovement; }
}
