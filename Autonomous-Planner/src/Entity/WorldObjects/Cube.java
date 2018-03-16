package Entity.WorldObjects;

import Entity.WorldObjects.Lot.Field;
import Main.Assets;
import Main.Handler;

public class Cube extends WorldObject {

	public Cube(Handler handler, Field lot) {
		super(handler, lot, Assets.powerCubeModel, Assets.powerCubeTexture);
		
	}

	@Override
	public void update(float delta) {
		
	}
}
