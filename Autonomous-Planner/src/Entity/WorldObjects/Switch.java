package Entity.WorldObjects;

import Entity.WorldObjects.Lot.Field;
import Main.Assets;
import Main.Handler;

public class Switch extends WorldObject {
	
	
	public Switch(Handler handler, Field lot) {
		super(handler, lot, Assets.switchModel, Assets.blankTexture);
		
	}

	@Override
	public void update(float delta) {
		
	}
}
