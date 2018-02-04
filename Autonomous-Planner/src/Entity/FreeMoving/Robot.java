package Entity.FreeMoving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.Engine.Demo.RenderTester;
import com.Engine.RenderEngine.Font.Font;
import com.Engine.RenderEngine.Font.TextMeshStitcher;
import com.Engine.RenderEngine.Font.Render.TextMesh;
import com.Engine.RenderEngine.Font.Render.TextRenderProperties;
import com.Engine.RenderEngine.Textures.Texture2D;
import com.Engine.RenderEngine.Util.RenderStructs.Transform;
import com.Engine.Util.Vectors.Vector2f;
import com.Engine.Util.Vectors.Vector3f;
import com.Engine.Util.Vectors.Vector4f;

import Entity.FreeMoving.AI.Action.Human.MoveToAction;
import Entity.WrapperBodies.WrapperModel;
import Input.MousePicker;
import Main.Handler;
import Utils.Util;

public class Robot extends Entity {
	public static float lineElevation = 2;
	
	private TextMesh text;
	private TextRenderProperties textProperties;
	
	private int teamNumber;

	private float distanceBetweenWheels;
	
	private boolean edit;
	
	private int listIndex;
	private int vectorEditIndex;
	
	private ArrayList<Vector2f> rl;
	private ArrayList<Vector2f> lr;
	private ArrayList<Vector2f> rr;
	private ArrayList<Vector2f> ll;
	
	//Current
	private ArrayList<Vector2f> verticies;
	
	private Vector2f spawn;
	private Vector3f color;
	
	public Robot(Handler handler, WrapperModel wrapperModel, Texture2D texture, Vector2f spawn, Vector3f color, float movementSpeed, int teamNumber) {
		super(handler, wrapperModel, texture);
		
		this.spawn = spawn;
		this.teamNumber = teamNumber;
		this.movementSpeed = new Vector2f(movementSpeed);
		this.color = color;

		vectorEditIndex = -1;

		rl = new ArrayList<>();
		lr = new ArrayList<>();
		ll = new ArrayList<>();
		rr = new ArrayList<>();
		
		verticies = rr;
		
		distanceBetweenWheels = 5;
		spawn();
		
		edit = true;
		
		Font font = Font.loadFont(RenderTester.class.getResourceAsStream("/Consolas.qFnt"));
		
		text = TextMeshStitcher.createMesh(String.valueOf(teamNumber), 
				font, 40, handler.getWindow().getAspectRatio(), new Vector2f(-1));
		text.setShader(Font.BillboardShader);
		textProperties = new TextRenderProperties(new Transform(new Vector3f(), new Vector3f(), new Vector3f(1)), new Vector4f(1, 1, 1, 1));
	}
	
	public void spawn() { 
		setAngle(90);
		setPosition2D(spawn);
	}
	
	public void start() {
		vectorEditIndex = -1;
		edit = false;
		
		for(Vector2f vector : verticies) {
			addAction(new MoveToAction(handler, this, vector));
		}
	}
	
	public void stop() {
		actionQueue.getActions().clear();
	}
	
	public void update(float delta) {
		actionQueue.update(delta);

		if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_RIGHT)) {
			listIndex++;
			
			if(listIndex == 4)
				listIndex = 0;
			
			if(listIndex == 0)
				verticies = ll;
			else if(listIndex == 1)
				verticies = lr;
			else if(listIndex == 2)
				verticies = rl;
			else
				verticies = rr;
		} else if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_LEFT)) {
			listIndex--;
			
			if(listIndex == -1)
				listIndex = 3;
			
			if(listIndex == 0)
				verticies = ll;
			else if(listIndex == 1)
				verticies = lr;
			else if(listIndex == 2)
				verticies = rl;
			else
				verticies = rr;
		}
		
		if(edit) {
			if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_DELETE)) {
				if(vectorEditIndex != -1) {
					verticies.remove(vectorEditIndex);
					vectorEditIndex = -1;
				}
			} else if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_RETURN)) {
				if(vectorEditIndex == -1)
					vectorEditIndex = verticies.size();
				else
					vectorEditIndex += 1;
				verticies.add(vectorEditIndex, new Vector2f(Util.to2D(MousePicker.calculateHitPosition(lineElevation))));
			}
			
			else if(handler.getMouseManager().keyJustReleased(0)) {
				if(vectorEditIndex > -1) {
					vectorEditIndex += 1;
					verticies.add(vectorEditIndex, new Vector2f(Util.to2D(MousePicker.calculateHitPosition(lineElevation))));
				}
			} else if(handler.getMouseManager().keyJustReleased(1)) {
				if(vectorEditIndex == -1) {
					Vector2f hit = Util.to2D(MousePicker.calculateHitPosition(lineElevation));
					HashMap<Float, Vector2f> distances = new HashMap<>();
					
					for(Vector2f vector : verticies) {
						float distance = vector.distance(hit);
						if(distance < 2)
							distances.put(distance, vector);
					}
					
					Set<Float> list = distances.keySet();
					if(list.size() == 0)
						return;
					if(list.size() > 1) {
						float distance = Float.MAX_VALUE;
						for(Float f : list) {
							if(f < distance)
								distance = f;
						}
						
						vectorEditIndex = verticies.indexOf((distances.get(distance)));
					} else {
						Vector2f shortest = distances.get(list.iterator().next());
						vectorEditIndex = verticies.indexOf(shortest);
					}
				} else 
					vectorEditIndex = -1;
			} else if(vectorEditIndex != -1) {
				verticies.get(vectorEditIndex).set(Util.to2D(MousePicker.calculateHitPosition(lineElevation)));
			}
		}
	}
	
	@Override
	public void render() {
		super.render();
		
		textProperties.getTransform().setTranslation(Util.to3D(getPosition2D().subtract(text.getSize().x / 2.0, 0), 5));
		text.render(textProperties);
		
		if(verticies.size() > 0) {
			drawLine(spawn.add(getWidth() / 2, getHeight() / 2), verticies.get(0));
			
			if(verticies.size() > 1) {
				for(int i = 0; i < verticies.size() - 1; i++) {
					drawLine(verticies.get(i), verticies.get(i + 1));
				}
			}
		}
	}
	
	private void drawLine(Vector2f from, Vector2f to) {
		handler.getWorld().drawLine(Util.to3D(from, lineElevation), Util.to3D(to, lineElevation), color);
	}
	
//	public void update(float delta) {
//		if(!started) {
//			startDrive();
//			started = true;
//		}
//		
//		drive(20f, 20f, delta);
//	}
//	
//	public void startDrive() {
//		startTime = System.currentTimeMillis();
//	}
//	
//	public void drive(float leftSpeed, float rightSpeed, float delta) {
//		if(leftSpeed == rightSpeed) {
//			float angle = body.getRenderProperties().getTransform().getRotation().y;
//			push(new Vector2f(leftSpeed * Math.cos(angle - 90), leftSpeed * Math.sin(angle - 90)), delta);
//			return;
//		}
//		
//		float velocityDifference = leftSpeed - rightSpeed;
//		
//		float r = (.5f * leftSpeed) / velocityDifference;
//		
//		float angularSpeed = velocityDifference / distanceBetweenWheels;
//
//		float time = (float) ((System.currentTimeMillis() - startTime) / 1000.0);
//		Vector2f translate = new Vector2f(Math.sin(angularSpeed * time), Math.cos(angularSpeed * time)).multiply(r * angularSpeed);
//		
//		rotate((float) (Math.toDegrees(angularSpeed)) * delta);
//		
//		push(translate.multiply(1, -1), delta);
//	}
	
	public void edit() { this.edit = true; }
	public void disableEdit() { this.edit = false; }
	
	public float getDistanceBetweenWheels() { return distanceBetweenWheels; }
	public int getTeamNumber() { return teamNumber; }
}
