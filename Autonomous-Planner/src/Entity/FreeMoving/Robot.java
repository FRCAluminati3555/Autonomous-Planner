package Entity.FreeMoving;

import java.util.ArrayList;

import com.Engine.RenderEngine.Font.Font;
import com.Engine.RenderEngine.Font.Render.TextMesh;
import com.Engine.RenderEngine.Font.Render.TextRenderProperties;
import com.Engine.RenderEngine.Util.RenderStructs.Transform;
import com.Engine.Util.Vectors.Vector2f;
import com.Engine.Util.Vectors.Vector3f;
import com.Engine.Util.Vectors.Vector4f;

import Entity.FreeMoving.Action.Action;
import Entity.FreeMoving.Action.Robot.MoveDistance;
import Entity.FreeMoving.Action.Robot.Turn;
import Main.Assets;
import Main.Handler;
import Utils.Util;

public class Robot extends Entity {
	public static float lineElevation = 2;
	
	private TextMesh text;
	private TextRenderProperties textProperties;
	
	private int teamNumber;

	private float distanceBetweenWheels;
	
	//Current
	private Spawn spawn;
	private Vector3f color;
	
	private RobotInformationPassThrough robotInfo;
	private ArrayList<Action> path;
	
	private boolean running;
	
	public Robot(Handler handler, int teamNumber) {
		super(handler, Assets.getModel(teamNumber), Assets.getTexture(teamNumber));
		
		robotInfo = handler.getRobotInfo(teamNumber);
		spawn = robotInfo.getSpawn();
		path = robotInfo.getPath();
		
		this.teamNumber = teamNumber;
		this.movementSpeed = new Vector2f(Assets.getSpeed(teamNumber));
		this.distanceBetweenWheels = Assets.getDistanceBetweenWheels(teamNumber);
		this.color = Vector3f.random(1);

		spawn();
		
		text = Assets.generateTextMesh(Integer.toString(teamNumber), handler.getWindow());
		text.setShader(Font.BillboardShader);
		textProperties = new TextRenderProperties(new Transform(new Vector3f(), new Vector3f(), new Vector3f(1)), new Vector4f(1, 1, 1, 1));
		
//		InformationUtil.readPathRobot(handler, this);
	}
	
	public void spawn() { 
		setAngle(90);
		setPosition2D(spawn.getLocation());
//		edit = true;
	}
	
	public void start() {
		if(path != null) {
			for(Action action : path) {
				action.setComplete(false);
				action.setStarted(false);
			}
		}
		
		running = true;
	}
	
	public void stop() {
		running = false;
	}
	
	public void update(float delta) {
		if(running) {
			if(path != null) {
				for(Action action : path) {
					if(!action.isComplete()) {
						if(!action.hasStarted())
							action.start(this);
						
						action.update(delta);
						return;
					}
				}
			}
		}
	}
	
	@Override
	public void render() {
		super.render();
		
		textProperties.getTransform().setTranslation(Util.to3D(getPosition2D().add(text.getSize().x / 2.0, 0), 5));
		text.render(textProperties);
		
//		if(path != null) {
//			for(Action action : path) {
//				if(!action.isComplete()) {
//					if(action instanceof MoveDistance) {
//						drawLine(new Vector2f(), ((MoveDistance) action).getTarget());
//					}
//				}
//			}
//		}
		
		Vector2f position = spawn.getLocation().add(getWidth() / 2, getHeight());//, getHeight() / 2);
		float angle = 90;
		
		if(path != null) {
			for(Action action : path) {
				if(action instanceof MoveDistance) {
					MoveDistance a = (MoveDistance) action;
					Vector2f target = new Vector2f(Math.cos(Math.toRadians(angle)) * a.getDistanceTiles() + position.x, 
												   Math.sin(Math.toRadians(angle)) * a.getDistanceTiles() + position.y);
					
					drawLine(position, target);//.add(new Vector2f(getHeight() / 2).multiply(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle)))));
					
					position = target;
				} else if(action instanceof Turn) {
					Turn a = (Turn) action;
					angle += a.getAngle();
					
//					position = getFrontAndCenterPosition(position, angle);
				}
			}
		}
		
//		drawLine(new Vector2f(), getFrontAndCenterPosition()); 
	}
	
	public Vector2f getFrontAndCenterPosition(Vector2f position, float angle ) {
//		float a = (float) Math.toRadians(angle);
//		float radius = -getHeight();
//		
//		Vector2f target = new Vector2f(Math.cos(a) * radius + position.x, 
//				   Math.sin(a) * radius + position.y);
		
		return position;
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
	
	public boolean isRunning() { return running; }
	
	public void setPath(ArrayList<Action> path) { this.path = path; }
	
	public Spawn getSpawn() { return spawn; }
	public void setSpawn(Spawn spawn) { this.spawn = spawn; }
	
	public Vector2f getFrontAndCenterPosition() {
		float angle = (float) Math.toRadians(getAngle());
		float radius = getHeight() / 2;
		Vector2f position = getPosition2D();
		
		Vector2f target = new Vector2f(-Math.cos(angle) * radius + position.x, 
				   Math.sin(angle) * radius + position.y);
		
		return target;
	}
	
	public Vector2f getBackAndCenterPosition() {
		float angle = (float) Math.toRadians(getAngle());
		float radius = -getHeight() / 2f;
		Vector2f position = getPosition2D();
		
		Vector2f target = new Vector2f(Math.cos(angle) * radius + position.x, 
				   Math.sin(angle) * radius + position.y);
		
		return target;
	}
	
	public RobotInformationPassThrough getInfo() { return robotInfo; }
	
	public float getDistanceBetweenWheels() { return distanceBetweenWheels; }
	public int getTeamNumber() { return teamNumber; }
}
