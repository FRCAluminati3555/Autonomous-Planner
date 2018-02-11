package Entity.FreeMoving;

import com.Engine.RenderEngine.Textures.Texture2D;
import com.Engine.Util.Vectors.Vector2f;
import com.Engine.Util.Vectors.Vector3f;

import Entity.FreeMoving.AI.Action.Action;
import Entity.FreeMoving.AI.Action.ActionQueue;
import Entity.WrapperBodies.WrapperModel;
import Entity.WrapperBodies.WrapperStaticBody;
import Main.Handler;
import Utils.Util;

public abstract class Entity {
	protected Handler handler;
	protected ActionQueue actionQueue;
	
	protected WrapperStaticBody body;
	protected Vector2f movementSpeed;

	public Entity(Handler handler, WrapperModel wrapperModel, Texture2D texture) {
		this.handler = handler;
		
		actionQueue = new ActionQueue();
		body = new WrapperStaticBody(wrapperModel, texture);
	}
	
	public void move(Vector2f velocity, float delta) {
		float angle = Util.getAngle(velocity);
		
		setAngle(angle);
		body.add(velocity.multiply(delta));
	}
	
	public void push(Vector2f velocity, float delta) {
		body.add(velocity.multiply(delta));
	}
	
	public void update(float delta) {
		actionQueue.update(delta);
	}
	
	public void render() { body.render(); }
	
	public void addAction(Action a) { if(a != null) actionQueue.add(a); }
	
	public float getX() { return body.getX(); }
	public float getZ() { return body.getZ(); }
	
	public Vector2f getMovementSpeed() { return movementSpeed; }
	
	public Vector2f getPosition2D() { return body.getPosition2D(); }
	public Vector3f getPosition3D() { return body.getPosition3D(); }
	
	public Vector2f getDimensions() { return body.getDimensions(); }
	public float getWidth() { return body.getWidth(); }
	public float getHeight() { return body.getHeight(); }
	
	public void setPosition2D(Vector2f position) { body.setPosition2D(position.add(getWidth() / 2, getHeight() / 2)); }
	public void setPosition2D(float x, float z) { setPosition2D(new Vector2f(x, z)); }
	
	public void setX(float x) { body.setX(x + (getWidth() / 2)); }
	public void setZ(float z) { body.setZ(z + (getHeight() / 2)); }
	
	public void setPosition3D(Vector3f position) { body.setPosition3D(position.add(getWidth() / 2, 0, getHeight() / 2)); }
	
	public Vector2f roundPosToGrid() { return body.roundPosToGrid(); }
	
	public float getPosAngle() { return body.getPosAngle(); }
	public float getAngle() { return body.getAngle(); }
	public void setAngle(float angle) { body.getRenderProperties().getTransform().setRotation(new Vector3f(0, angle, 0)); }
	public void rotate(float angle) { body.getRenderProperties().getTransform().rotate(new Vector3f(0, angle, 0)); }
	
	public WrapperStaticBody getBody() { return body; }
}
