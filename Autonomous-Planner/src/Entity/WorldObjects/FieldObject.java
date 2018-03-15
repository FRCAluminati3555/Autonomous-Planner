package Entity.WorldObjects;

import Entity.WorldObjects.Lot.Field;
import Main.Assets;
import Main.Handler;

public class FieldObject extends WorldObject {

	public FieldObject(Handler handler, Field field) {
		super(handler, field, Assets.fieldModel, Assets.fieldTexture);
		
	}

	@Override
	public void update(float delta) {
		
	}
}
