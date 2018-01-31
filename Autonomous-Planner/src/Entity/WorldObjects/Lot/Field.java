package Entity.WorldObjects.Lot;

import com.Engine.Util.Vectors.Vector2f;
import com.Engine.Util.Vectors.Vector3f;

import Main.Handler;
import Utils.Util;
import World.Tiles.Tile;
import World.Tiles.Render.TileInstanceModel;

public class Field {
	public static final TileInstanceModel tileInstanceModel = new TileInstanceModel();
	
	private Tile[][] tiles;
	private Vector3f position;
	private Vector2f dimensions;

	public Field(Handler handler, Vector3f position, Vector2f dimensions) {
		this.position = position;
		this.dimensions = dimensions;
		
		tiles = new Tile[(int) (dimensions.x * 2)][(int) (dimensions.y * 2)];
		
		for(int x = 0; x < tiles.length; x++)
		for(int z = 0; z < tiles[x].length; z++)
			tiles[x][z] = new Tile(handler, this, new Vector3f(x / 2f, 0, z / 2f));
	}
	
	public void update(float delta) {
		for(Tile[] temp : tiles)
		for(Tile tile : temp)
			tile.update(delta);
	}
	
	public void render() {
		for(Tile[] temp : tiles)
		for(Tile tile : temp)
			tile.render(tileInstanceModel);
	}

	public Tile[][] getTiles() { return tiles; }
	public Tile getTile(Vector2f position) { return tiles[(int) (position.x * 2)][(int) (position.y * 2)]; }
	public Tile getTile(Vector3f position) { return getTile(Util.to2D(position)); }
	
	public Vector3f getPosition() { return position; }
	public Vector2f getDimensions() { return dimensions; }
	public int getWidth() { return (int) dimensions.x; }
	public int getHeight() { return (int) dimensions.y; }
}