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
	
	private Robot r3555;
	
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
		
		r3555 = new Robot(handler, Assets.r3555Model, Assets.r3555Texture, new Vector2f(40, 155), 5, 3555);
		
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
	
	public void drawLine(Vector3f from, Vector3f to) {
//		System.out.println("From: " + from + ", To: " + to);
		
		VectorModel.renderVector(to.subtract(from).multiply(-1, 1, -1), from, new Vector3f(0, 0, 1));
	}
	
	public void update(float delta) {
		cameraMovement.update(delta);

		if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_UP))
			r3555.start();
		if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_DOWN)) {
			r3555.stop();
			r3555.spawn();
		}
		
		field.update(delta);
		r3555.update(delta);
	}
	
	public void render() {
		field.render();
		
//		drawLine(new Vector3f(.5, 0, .5), new Vector3f(2, 1, 2));
		
		r3555.render();
		
		Assets.defaultShader.bind();
		Assets.defaultShader.loadLights(sun);
		TileInstanceModel.TILE_SHADER.bind();
		TileInstanceModel.TILE_SHADER.loadLights(sun);
	}
	
	public Field getField() { return field; }
	
	public Camera getCamera() { return camera; }
	public CameraMovement getCameraMovement() { return cameraMovement; }
}
