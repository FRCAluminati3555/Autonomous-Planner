package Main;

import java.io.File;
import java.util.HashMap;

import com.Engine.Demo.RenderTester;
import com.Engine.RenderEngine.Font.Font;
import com.Engine.RenderEngine.Font.TextMeshStitcher;
import com.Engine.RenderEngine.Font.Render.TextMesh;
import com.Engine.RenderEngine.Shaders.Default.DefaultShader;
import com.Engine.RenderEngine.Textures.Texture2D;
import com.Engine.RenderEngine.Window.Window;
import com.Engine.Util.Vectors.Vector2f;

import Entity.WrapperBodies.WrapperModel;
import Utils.AssetLoader;

public class Assets {
	public static DefaultShader defaultShader;

	public static WrapperModel tileModel;
	public static Texture2D sampleFloorTextures;
	
	public static WrapperModel defaultModel;
	public static Texture2D defaultTexture;
	
//	public static WrapperModel r3555Model;
//	public static Texture2D r3555Texture;
	
	public static WrapperModel switchModel;
	public static Texture2D switchTexture;
	
	public static Texture2D blankTexture;

	public static WrapperModel fieldModel;
	public static Texture2D fieldTexture;
	
	public static WrapperModel powerCubeModel;
	public static Texture2D powerCubeTexture;
	
	public static Font font;
	
	public static enum TileTextureIndecies {
		GRASS(0), CRACKED_STONE_BRICK(1), COBBLESTONE(2), STONE_BRICK(3), ORANGE_STONE(4), PURPLE_STONE(5), WHITE_BRICK(6);
		
		private int num;
		
		private TileTextureIndecies(int num) {
			this.num = num;
		}
		
		public int getValue() { return num; }
	};
	
	public static HashMap<Integer, Float> distanceBetweenWheels = new HashMap<>();
	private static void initDistanceBetweenWheels() {
		
	}
	
	public static float getDistanceBetweenWheels(int teamNumber) {
		if(!distanceBetweenWheels.containsKey(teamNumber))
			return 5;
		else 
			return distanceBetweenWheels.get(teamNumber);
	}
	
	public static HashMap<Integer, Float> speeds = new HashMap<>();
	private static void initSpeeds() {
		
	}
	
	public static float getSpeed(int teamNumber) {
		if(!speeds.containsKey(teamNumber))
			return 6;
		else 
			return speeds.get(teamNumber);
	}
	
	public static WrapperModel getModel(int teamNumber) {
		File file = new File(AssetLoader.MODEL_PATH + "r" + teamNumber + ".obj");
		if(!file.exists()) 
			return Assets.defaultModel;
		return new WrapperModel(AssetLoader.MODEL_PATH + "r" + teamNumber + ".obj", defaultShader);
	}
	
	public static Texture2D getTexture(int teamNumber) {
		File file = new File(AssetLoader.TEXTURE_PATH + "r" + teamNumber + ".png");
		if(!file.exists()) 
			return Assets.blankTexture;
		return AssetLoader.loadTexture(AssetLoader.TEXTURE_PATH + "r" + teamNumber + ".png");
	}
	
	public static TextMesh generateTextMesh(String string, Window window) {
		return TextMeshStitcher.createMesh(string, font, 40, window.getAspectRatio(), new Vector2f(-1));
	}
	
	public static void init() {
		initDistanceBetweenWheels();
		initSpeeds();
		
		defaultShader = new DefaultShader();
//		defaultShader.getRenderer().usingFrustumCulling(false);
		
		font = Font.loadFont(RenderTester.class.getResourceAsStream("/Consolas.qFnt"));
		
		defaultModel = new WrapperModel(AssetLoader.MODEL_PATH + "Default.obj", defaultShader);
		
		tileModel = new WrapperModel(AssetLoader.MODEL_PATH + "Tile.obj", defaultShader);
		sampleFloorTextures = AssetLoader.loadTexture(AssetLoader.TEXTURE_PATH + "Carpet.jpg");
		sampleFloorTextures.setNumberOfRows(1);
		
		blankTexture = AssetLoader.loadTexture(AssetLoader.TEXTURE_PATH + "Blank.png");
		
		powerCubeModel = new WrapperModel(AssetLoader.MODEL_PATH + "Power-Cube.obj", defaultShader);
		powerCubeTexture = blankTexture;
		
		fieldModel = new WrapperModel(AssetLoader.MODEL_PATH + "Field.obj", defaultShader);
		fieldTexture = blankTexture;
		
//		r3555Model = new WrapperModel(AssetLoader.MODEL_PATH + "r3555.obj", defaultShader);
//		r3555Texture = blankTexture;//AssetLoader.loadTexture(AssetLoader.TEXTURE_PATH + "r3555.png");
		
		switchModel = new WrapperModel(AssetLoader.MODEL_PATH + "Switch.obj", defaultShader);
		switchTexture = blankTexture;
	}
}
