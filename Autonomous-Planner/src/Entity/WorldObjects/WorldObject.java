package Entity.WorldObjects;

import java.util.ArrayList;

import com.Engine.RenderEngine.Textures.Texture2D;
import com.Engine.Util.Vectors.Vector2f;
import com.Engine.Util.Vectors.Vector3f;

import Entity.WorldObjects.Lot.Field;
import Entity.WrapperBodies.WrapperModel;
import Entity.WrapperBodies.WrapperStaticBody;
import Main.Handler;
import World.Tiles.Tile;

public abstract class WorldObject {
	protected Handler handler;
	protected Field lot;
	protected WrapperStaticBody body;
	protected ArrayList<Tile> tiles;

	protected Vector2f front;
	
	public WorldObject(Handler handler, Field lot, WrapperModel wrapperModel, Texture2D texture) {
		this.handler = handler;
		this.lot = lot;
		
		body = new WrapperStaticBody(wrapperModel, texture);
		handler.getGame().getPhysicsEngine().add(body.getStaticBody());
		front = new Vector2f(getWidth(), 0);
		
		tiles = new ArrayList<>();
	}
	
	public void render() {
		body.render();
	}
	
	public void masterUpdate(float delta) {
		update(delta);
	}
	
	public boolean addToTile(Tile tile) {
		//Check the tiles near it to confirm nothing is in the way
		if(!tiles.isEmpty()) {
			System.err.println("List Not empty");
			return false;
		}

		for(float x = tile.getX(); x < tile.getX() + getWidth(); x += .5) {
			for(float z = tile.getZ(); z < tile.getZ() + getHeight(); z += .5) {
				if(lot.getTile(new Vector3f(x, getY(), z)).containsAnything())
					return false;
			}
		}
		
		for(float x = tile.getX(); x < tile.getX() + getWidth(); x += .5) {
			for(float z = tile.getZ(); z < tile.getZ() + getHeight(); z += .5) { 
				Tile tempTile = lot.getTile(new Vector3f(x, getY(), z));
				tempTile.add(this);
				tiles.add(tempTile);
			}
		}
		
		setPosition2D(tile.getPosition2D());
		return true;
	}
	
	public abstract void update(float delta);
	
	public void removeFromTile() {
		for(Tile tile : tiles) 
			tile.remove(this);
		tiles.clear();
	}
	
	public void cleanUp() {
		handler.getGame().getPhysicsEngine().remove(body.getStaticBody());
	}
	
	public float getX() { return body.getX(); }
	public float getY() { return body.getY(); }
	public float getZ() { return body.getZ(); }
	
	public float getWidth() { return body.getWidth(); }
	public float getHeight() { return body.getHeight(); }
	public float getHeightY() { return body.getHeightY(); }
	
	public Vector3f getPosition3D() { return body.getPosition3D(); }
	public Vector2f getPosition2D() { return body.getPosition2D(); }
	public Vector2f getFront() { return front; }
	
	public Vector3f getTopLeftPosition3D() { return body.getPosition3D().subtract(getWidth() / 2, 0, getHeight() / 2); }
	public Vector2f getTopLeftPosition2D() { return body.getPosition2D().subtract(getWidth() / 2,    getHeight() / 2); }

	public void setPosition2D(float x, float z) { setPosition2D(new Vector2f(x, z)); }
	public void setPosition2D(Vector2f position) {
		position = position.add(getWidth() / 2, getHeight() / 2);
		body.setPosition2D(position);
	}
	
	public void setPosition3D(float x, float y, float z) { setPosition3D(new Vector3f(x, y, z)); }
	public void setPosition3D(Vector3f position) {
		position = position.add(getWidth() / 2, 0, getHeight() / 2);
		body.setPosition3D(position);
	}
	
	public void setAngle(float angle) {
		if(angle == 0) 
			front = new Vector2f(getWidth(), 0);
		if(angle == 90) 
			front = new Vector2f(0, -1);
		if(angle == 180)
			front = new Vector2f(-1, 0);
		if(angle == 270)
			front = new Vector2f(0, getHeight());

		body.setAngle(angle); 
	}

	public Field getLot() { return lot; }	
	public WrapperStaticBody getBody() { return body; }
	public ArrayList<Tile> getTiles() { return tiles; }
}
