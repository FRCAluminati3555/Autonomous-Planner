package Main;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CCW;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import com.Engine.PhysicsEngine.PhysicsEngine;
import com.Engine.PhysicsEngine.Detection.Intersection.Tests.MovingEllipsoidMeshIntersectionTest;
import com.Engine.PhysicsEngine.Render.PhysicsShader;
import com.Engine.RenderEngine.Font.Font;
import com.Engine.RenderEngine.GLFunctions.BlendFunc;
import com.Engine.RenderEngine.GLFunctions.DepthTest;
import com.Engine.RenderEngine.New_Pipeline.FBO.FBO;
import com.Engine.RenderEngine.New_Pipeline.FBO.FBO_Types.Attachment;
import com.Engine.RenderEngine.New_Pipeline.FBO.RenderBuffer;
import com.Engine.RenderEngine.Shaders.Shader;
import com.Engine.RenderEngine.Window.Window;
import com.Engine.Util.Vectors.Vector3f;

import World.World;
import World.Tiles.Render.TileInstanceModel;

public class Game {
	private Window window;
	private PhysicsEngine physicsEngine;
	
	private Handler handler;
	private World world;
	
	private FBO antiAliasing;
	private DepthTest depth;
	
	public static PhysicsShader physicsShader;
	public static int screenWidth = 800, screenHeight = 600;
	
	/** 
	 * THE PLAN:
	 * 		- Edit Movement 
	 * 			- Editable values for distances
	 * 			- Different Types of Turns
	 * 			- Different Velocities at each 
	 * 			- From Center or from front?
	 * 
	 * 			- Pickup / Deposit Cubes   -|
	 * 			- Wait Function            -| <- Render?
	 * 												- Different Symbol Models at each Point location with colors
	 * 		
	 * 		- Make Positions Changeable (Different Spawn Locations)
	 * 			- List of spawn locations that are all selectable and generateable <- Add to a scroll pane of radio buttons		
	 * 
	 * 		-Different Type Of Actions
	 * 			- Add Action popup 
	 * 				- Different Types of actions and properties of those actions
	 * 			- Editable TextFields for properties for those actions
	 * 		- Scoring
	 * 			- Render Score
	 * 			- Timer -> Score per second
	 * 			- Pickup and Deposit Cubes <- Another Type Of Action
	 * 				- Inventory
	 * 		- Create an interpretable file for robot to read and base auto off of
	 *		- Make UI Look Nice
	 *		- Add in the rest of the field
	 *			- Walls
	 *			- Scale
	 *			- Bleachers? 
	 *				- Little People?
	 *			- Ref Stands
	 */
	public void init() throws LWJGLException {
		window = new Window();
		window.setTitle("Auto Planner");
		window.setFPS(60);
		window.initDisplay(screenWidth, screenHeight);
		
		BlendFunc.normal().enable();
		depth = DepthTest.normal();
		
		antiAliasing = new FBO(window.getWidth(), window.getHeight(), 4);
		antiAliasing.attach(new RenderBuffer(antiAliasing), Attachment.ColourBuffer);
		antiAliasing.attach(new RenderBuffer(antiAliasing), Attachment.DepthBuffer);
		FBO.SCREEN_FBO.screenResized(window);
		
		physicsEngine = new PhysicsEngine();
		physicsShader = new PhysicsShader();
		Assets.init();
		
		handler = new Handler(this);
		handler.init();
		world = handler.getWorld();
		
		physicsEngine.addIntersectionTest(new MovingEllipsoidMeshIntersectionTest());
		
		Shader.unbind(); 
		Shader.setSkyColor(new Vector3f());
	}
	
	public void loop() {
		double frameTimeAvg = 0.0;
		int frameAvgCounter = 0;
		
		while(!window.isCloseRequested()) {
			handler.getKeyManager().update();
			handler.getMouseManager().update();
//			physicsEngine.simulate((float) window.getFrameTime());
			
//			window.setTitle("2.5D Life: " + window.getFrameTime());
			float delta = (float) window.getFrameTime();
			if(delta < 2 && delta != 0)
				world.update((float) window.getFrameTime());
			world.render();
			
			//Rendering
			antiAliasing.bind();
			antiAliasing.clear();
			
			Shader.setProjectionMatrix(world.getCamera().getPorjectionMatrix());
			Shader.setViewMatrix(world.getCamera().getViewMatrix());
			glClear((Keyboard.isKeyDown(Keyboard.KEY_E) ? 0 : GL_COLOR_BUFFER_BIT) | GL_DEPTH_BUFFER_BIT);
			
			glEnable(GL_CULL_FACE);
			glFrontFace(GL_CCW);
			glCullFace(GL_BACK);

			depth.enable();
			
			TileInstanceModel.TILE_SHADER.getRenderer().render(world.getCamera());
			TileInstanceModel.TILE_SHADER.getRenderer().clear();
			
			Assets.defaultShader.getRenderer().render(world.getCamera());
			Assets.defaultShader.getRenderer().clear();
			
			physicsShader.getRenderer().render(world.getCamera());
			physicsShader.getRenderer().clear();
			
			Font.BillboardShader.getRenderer().render(world.getCamera());
			Font.BillboardShader.getRenderer().clear();

			FBO.unbind();
			antiAliasing.resolve();
			
			//Last
			window.update();
			
			if(window.wasResized()) {
			    FBO.SCREEN_FBO.screenResized(window);
				world.getCamera().setAspect(window.getAspectRatio());
			    world.getCamera().recalculate();
			    
				Shader.unbind(); 
			}
			
			if (frameAvgCounter >= 50) {
				frameTimeAvg /= (double) frameAvgCounter;
				window.setTitle("Life 2.5D | FPS: " + (int) (1.0 / frameTimeAvg) + "\t FrameTime: " + (float) frameTimeAvg);

				frameTimeAvg = 0.0;
				frameAvgCounter = 0;
			} else {
				frameTimeAvg += window.getFrameTime();
				frameAvgCounter++;
			}
		}
	}
	
	public void cleanUp() {
		window.destroy();
		System.exit(0);
	}
	
	public Window getWindow() { return window; }
	public PhysicsEngine getPhysicsEngine() { return physicsEngine; }
}
