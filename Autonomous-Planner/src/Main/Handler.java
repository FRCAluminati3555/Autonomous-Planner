package Main;

import com.Engine.RenderEngine.Window.Window;

import Input.KeyManager;
import Input.MouseManager;
import UI.Frame;
import World.World;

public class Handler {
	private Game game;
	private World world;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private Frame frame;
	
	public Handler(Game game) {
		this.game = game;
		
		keyManager = new KeyManager();
	}

	public void init() {
		world = new World(this);
		world.init();
		
		mouseManager = new MouseManager(this);
		frame = new Frame(this);
	}
	
	public Frame getFrame() { return frame; }
	public Game getGame() { return game; }
	public World getWorld() { return world; }
	public Window getWindow() { return game.getWindow(); }
	public KeyManager getKeyManager() { return keyManager; }
	public MouseManager getMouseManager() { return mouseManager; }
	public int getWidth() { return game.getWindow().getWidth(); }
	public int getHeight() { return game.getWindow().getHeight(); }
}