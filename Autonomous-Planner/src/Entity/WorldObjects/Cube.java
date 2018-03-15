package Entity.WorldObjects;

import com.Engine.RenderEngine.Textures.Texture2D;

import Entity.WorldObjects.Lot.Field;
import Entity.WrapperBodies.WrapperModel;
import Main.Handler;

public class Cube extends WorldObject {

	public Cube(Handler handler, Field lot, WrapperModel wrapperModel, Texture2D texture) {
		super(handler, lot, wrapperModel, texture);
		
	}

	@Override
	public void update(float delta) {
		
	}
}
