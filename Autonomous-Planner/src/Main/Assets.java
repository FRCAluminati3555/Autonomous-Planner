package Main;

import com.Engine.RenderEngine.Shaders.Default.DefaultShader;
import com.Engine.RenderEngine.Textures.Texture2D;

import Entity.WrapperBodies.WrapperModel;
import Utils.AssetLoader;

public class Assets {
	public static DefaultShader defaultShader;

	public static WrapperModel tileModel;
	public static Texture2D sampleFloorTextures;
	
	public static WrapperModel r3555Model;
	public static Texture2D r3555Texture;
	
	public static WrapperModel switchModel;
	public static Texture2D switchTexture;
	
	public static Texture2D blankTexture;
	
	public static enum TileTextureIndecies {
		GRASS(0), CRACKED_STONE_BRICK(1), COBBLESTONE(2), STONE_BRICK(3), ORANGE_STONE(4), PURPLE_STONE(5), WHITE_BRICK(6);
		
		private int num;
		
		private TileTextureIndecies(int num) {
			this.num = num;
		}
		
		public int getValue() { return num; }
	};
	
	public static void init() {
		defaultShader = new DefaultShader();
//		defaultShader.getRenderer().usingFrustumCulling(false);
		
		tileModel = new WrapperModel(AssetLoader.MODEL_PATH + "Tile.obj", defaultShader);
		sampleFloorTextures = AssetLoader.loadTexture(AssetLoader.TEXTURE_PATH + "Carpet.jpg");
		sampleFloorTextures.setNumberOfRows(1);
		
		blankTexture = AssetLoader.loadTexture(AssetLoader.TEXTURE_PATH + "Blank.png");
		
		r3555Model = new WrapperModel(AssetLoader.MODEL_PATH + "r3555.obj", defaultShader);
		r3555Texture = blankTexture;//AssetLoader.loadTexture(AssetLoader.TEXTURE_PATH + "r3555.png");
		
		switchModel = new WrapperModel(AssetLoader.MODEL_PATH + "Switch.obj", defaultShader);
		switchTexture = blankTexture;
	}
}
