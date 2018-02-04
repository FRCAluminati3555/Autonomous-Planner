package World;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

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
import World.Tiles.Render.TileInstanceModel;

public class World {
	private Handler handler;

	private Camera camera;
	private CameraMovement cameraMovement;
	
	private ArrayList<Light> sun;
	private Field field;

	private Robot[] robots;
	
	public World(Handler handler) {
		this.handler = handler;
		
		camera = new Camera(70, (float) handler.getWidth() / (float) handler.getHeight(), 0.3f, 1000);
		cameraMovement = new CameraMovement(handler, camera, new Vector3f(30, 56, 192), 10, 10, .15f);
//		cameraMovement = new CameraMovement(handler, camera, new Vector3f(0, 10, 10), 10, 10, .15f);
		
		sun = new ArrayList<>();
	}
	
	public void init() {
		VectorModel.init(Game.physicsShader);
		
		field = new Field(handler, new Vector3f(), new Vector2f(82, 164));
		sun.add(new Light(new Vector3f(41, 100, 91), new Vector3f(1), new Vector3f(.5, 0, 0)));
		
		robots = new Robot[3];
		
		robots[0] = new Robot(handler, Assets.r3555Model, Assets.r3555Texture, new Vector2f(40, 155), new Vector3f(0, 0, 1), 5, 3555);
		robots[1] = new Robot(handler, Assets.r3555Model, Assets.r3555Texture, new Vector2f(70, 155), new Vector3f(0, 1, 0), 5, 1729);
		robots[2] = new Robot(handler, Assets.r3555Model, Assets.r3555Texture, new Vector2f(10, 155), new Vector3f(1, 0, 0), 5, 1010);
		
		place(new Switch(handler, field), new Vector2f(22, 128));
//		r3555.addAction(new MoveToAction(handler, r3555, new Vector2f(20, 150)));
		
//		r3555.addAction(new TurnToAction(handler, r3555, new Vector2f()));
//		r3555.addAction(new WaitAction(handler, 3));
//		r3555.addAction(new TurnToAction(handler, r3555, new Vector2f(82, 0)));
//		r3555.addAction(new WaitAction(handler, 3));
//		r3555.addAction(new TurnToAction(handler, r3555, new Vector2f(82, 164)));
//		r3555.addAction(new WaitAction(handler, 3));
//		r3555.addAction(new TurnToAction(handler, r3555, new Vector2f(0, 164)));
//		r3555.addAction(new WaitAction(handler, 3));
//		r3555.addAction(new TurnToAction(handler, r3555, new Vector2f(82, 164)));
//		r3555.addAction(new WaitAction(handler, 3));
//		r3555.addAction(new TurnToAction(handler, r3555, new Vector2f(82, 0)));
//		r3555.addAction(new WaitAction(handler, 3));
//		r3555.addAction(new TurnToAction(handler, r3555, new Vector2f()));
	}
	
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
//		System.out.println("From: " + from + ", To: " + to);
		
		VectorModel.renderVector(to.subtract(from).multiply(-1, 1, -1), from, color);
	}
	
	public void update(float delta) {
		cameraMovement.update(delta);

		if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_UP)) {
			for(Robot robot : robots)
				robot.start();
		}
		
		if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_DOWN)) {
			for(Robot robot : robots) {
				robot.stop();
				robot.spawn();
			}
		}
		
		if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_NUMPAD1)) {
			robots[0].edit();
			robots[1].disableEdit();
			robots[2].disableEdit();
		}
		
		if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_NUMPAD2)) {
			robots[1].edit();
			robots[0].disableEdit();
			robots[2].disableEdit();
		}
		
		if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_NUMPAD3)) {
			robots[2].edit();
			robots[0].disableEdit();
			robots[1].disableEdit();
		}
		
		for(Robot robot : robots)
			robot.update(delta);
		
		field.update(delta);
	}
	
	public void render() {
		field.render();
		
		for(Robot robot : robots)
			robot.render();
		
		Assets.defaultShader.bind();
		Assets.defaultShader.loadLights(sun);
		TileInstanceModel.TILE_SHADER.bind();
		TileInstanceModel.TILE_SHADER.loadLights(sun);
	}
	
	public Field getField() { return field; }
	
	public Camera getCamera() { return camera; }
	public CameraMovement getCameraMovement() { return cameraMovement; }
}
