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
import com.Engine.RenderEngine.Util.RenderStructs.Transform;
import com.Engine.Util.Vectors.Vector2f;
import com.Engine.Util.Vectors.Vector3f;
import com.Engine.Util.Vectors.Vector4f;

import Entity.FreeMoving.Action.Robot.MoveToAction;
import Input.MousePicker;
import Main.Assets;
import Main.Handler;
import Utils.InformationUtil;
import Utils.Util;

public class Robot extends Entity {
	public static float lineElevation = 2;
	
	private TextMesh text;
	private TextRenderProperties textProperties;
	
	private int teamNumber;

	private float distanceBetweenWheels;
	
	private boolean edit;
	
	private int vectorEditIndex;
	
	private ArrayList<Vector2f> rl;
	private ArrayList<Vector2f> lr;
	private ArrayList<Vector2f> rr;
	private ArrayList<Vector2f> ll;
	
	//Current
	private ArrayList<Vector2f> verticies;
	
	private Vector2f spawn;
	private Vector3f color;
	
	public Robot(Handler handler, int teamNumber, float x) {
		super(handler, Assets.getModel(teamNumber), Assets.getTexture(teamNumber));
		
		this.spawn = new Vector2f(x, handler.getWorld().getField().getHeight() - getHeight());
		this.teamNumber = teamNumber;
		this.movementSpeed = new Vector2f(Assets.getSpeed(teamNumber));
		this.distanceBetweenWheels = Assets.getDistanceBetweenWheels(teamNumber);
		this.color = Vector3f.random(1);

		vectorEditIndex = -1;

		rl = new ArrayList<>();
		lr = new ArrayList<>();
		ll = new ArrayList<>();
		rr = new ArrayList<>();
		
		verticies = rr;
		
		spawn();
		
		text = Assets.generateTextMesh(Integer.toString(teamNumber), handler.getWindow());
		text.setShader(Font.BillboardShader);
		textProperties = new TextRenderProperties(new Transform(new Vector3f(), new Vector3f(), new Vector3f(1)), new Vector4f(1, 1, 1, 1));
		
		InformationUtil.readPath(this);
	}
	
	public void spawn() { 
		setAngle(90);
		setPosition2D(spawn);
//		edit = true;
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

//		if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_RIGHT)) {
//			listIndex++;
//			
//			if(listIndex == 4)
//				listIndex = 0;
//			
//			if(listIndex == 0)
//				verticies = ll;
//			else if(listIndex == 1)
//				verticies = lr;
//			else if(listIndex == 2)
//				verticies = rl;
//			else
//				verticies = rr;
//		} else if(handler.getKeyManager().keyJustPressed(Keyboard.KEY_LEFT)) {
//			listIndex--;
//			
//			if(listIndex == -1)
//				listIndex = 3;
//			
//			if(listIndex == 0)
//				verticies = ll;
//			else if(listIndex == 1)
//				verticies = lr;
//			else if(listIndex == 2)
//				verticies = rl;
//			else
//				verticies = rr;
//		}
		
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

					if(hit.distance(getPosition2D()) < getDimensions().length() / 2f) {//Click On Robot
						vectorEditIndex = -2;
					} else {
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
					}
				} else {
					vectorEditIndex = -1;
					spawn = getPosition2D();
				}
			} else if(vectorEditIndex == -2) {//Move Robot
				Vector2f position = Util.to2D(MousePicker.calculateHitPosition(lineElevation));
				position = position.capMax(handler.getWorld().getField().getDimensions().x - getWidth(), position.getY());
				position = position.capMin(0, position.getY());
				setX(position.x);
			} else if(vectorEditIndex != -1) {
				Vector2f position = Util.to2D(MousePicker.calculateHitPosition(lineElevation));
				
				if(Keyboard.isKeyDown(Keyboard.KEY_F)) {
					Vector2f anchor = null;
					if(vectorEditIndex == 0)
						anchor = getPosition2D();
					else
						anchor = verticies.get(vectorEditIndex - 1);
					
					if(Math.abs(anchor.x - position.x) > Math.abs(anchor.y - position.y)) {
						if(position.getX() < + handler.getWorld().getField().getWidth() && position.x > 0)
							position.y = anchor.y;
					} else if(position.y < handler.getWorld().getField().getHeight() && position.y > 0)
							position.x = anchor.x;
				}
				
				verticies.get(vectorEditIndex).set(position);
			}
		}
	}
	
	@Override
	public void render() {
		super.render();
		
		textProperties.getTransform().setTranslation(Util.to3D(getPosition2D().subtract(text.getSize().x / 2.0, 0), 5));
		text.render(textProperties);
		
		if(verticies.size() > 0) {
			drawLine(getPosition2D(), verticies.get(0));
			
			if(verticies.size() > 1) {
				for(int i = 0; i < verticies.size() - 1; i++) {
					drawLine(verticies.get(i), verticies.get(i + 1));
				}
			}
		}
	}
	
	public void setGameData(int index) {
		if(index == 0)
			verticies = rr;
		else if(index == 1)
			verticies = ll;
		else if(index == 2)
			verticies = lr;
		else 
			verticies = rl;
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
	
	public Vector2f getSpawn() { return spawn; }
	public void setSpawn(Vector2f spwan) { this.spawn = spwan; }
	
	public void edit() { this.edit = true; }
	
	public ArrayList<Vector2f> getRl() { return rl; }
	public ArrayList<Vector2f> getLr() { return lr; }
	public ArrayList<Vector2f> getRr() { return rr; }
	public ArrayList<Vector2f> getLl() { return ll; }

	public void disableEdit() { this.edit = false; }
	
	public float getDistanceBetweenWheels() { return distanceBetweenWheels; }
	public int getTeamNumber() { return teamNumber; }
}
