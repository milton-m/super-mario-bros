package com.game.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.game.gfx.Camera;
import com.game.gfx.Texture;
import com.game.gfx.Window;
import com.game.main.util.LevelLoader;
import com.game.object.util.Handler;
import com.game.object.util.KeyInput;

public class Game extends Canvas implements Runnable {

	// GAME CONSTANTS
	private static final int MILLIS_PER_SEC = 1_000;
	private static final int NANOS_PER_SEC = 1_000_000_000;
	private static final double TICKS_PER_SEC = 60.0;
	private static final double NANOS_PER_TICK = NANOS_PER_SEC / TICKS_PER_SEC;
	private static final int NUMBER_BUFFERS = 3;
	private static final String TITLE = "Super Mario Bros";
	
	private static final int WINDOW_WIDTH = 960;
	private static final int WINDOW_HEIGHT = 720;
	private static final int CAMERA_OFFSET_X = -2*Texture.getUnitWidth();
	private static final int CAMERA_OFFSET_Y = 3*Texture.getUnitWidth();
	
	// GAME VARIABLES
	private boolean running;
	
	// GAME COMPONENTS
	private Thread thread;
	private Handler handler;
	private Camera cam;
	private static Texture tex;
	private LevelLoader levelLoader;
	
	/**
	 * Creates a game object.
	 */
	public Game() {
		initialize();
	}

	/**
	 * Launch the program.
	 * 
	 * @param args console arguments (not used)
	 */
	public static void main(String[] args) {
		new Game();
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double deltaTick = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		
		while (running) {
			long now = System.nanoTime();
			deltaTick += (now - lastTime) / NANOS_PER_TICK;
			lastTime = now;
			
			while (deltaTick >= 1) {
				tick();
				updates++;
				deltaTick--;
			}
			
			if (running) {
				render();
				frames++;
			}
			
			// code for capping FPS at 60 (comment out above two blocks)
//			while (deltaTick >= 1) { 
//				tick();
//				updates++;
//				render();
//				frames++;
//				deltaTick--;
//			}
			
			if (System.currentTimeMillis() - timer > MILLIS_PER_SEC) {
				timer += MILLIS_PER_SEC;
				System.out.println("FPS: " + frames + " TPS: " + updates);
				updates = 0;
				frames = 0;
			}
		}	
		stop();
	}
	
	/**
	 * Returns the window height.
	 * 
	 * @return window height
	 */
	public static int getWindowHeight() {
		return WINDOW_HEIGHT;
	}
	
	/**
	 * Returns the window width.
	 * 
	 * @return window width
	 */
	public static int getWindowWidth() {
		return WINDOW_WIDTH;
	}
	
	/**
	 * Returns camera's x-offset.
	 * 
	 * @return camera x-offset
	 */
	public static int getCameraOffsetX() {
		return CAMERA_OFFSET_X;
	}
	
	/**
	 * Returns the texture object.
	 * 
	 * @return texture object
	 */
	public static Texture getTexture() {
		return tex;
	}
	
	private void initialize() {
		tex = new Texture();
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		levelLoader = new LevelLoader(handler);
		levelLoader.loadLevel();
		
		cam = new Camera();
		new Window(WINDOW_WIDTH, WINDOW_HEIGHT, TITLE, this);
		
		start();
	}
	
	private synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private synchronized void stop() {
		running = false;
	}
	
	private void tick() {
		handler.tick();
		cam.tick(handler.getPlayer());
	}
	
	private void render() {
		BufferStrategy buf = this.getBufferStrategy();
		if (buf == null) {
			this.createBufferStrategy(NUMBER_BUFFERS);
			return;
		}
		
		// draw graphics
		Graphics g = buf.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(Texture.getBackgroundColor());
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

		g2d.translate(cam.getX(), CAMERA_OFFSET_Y);
		handler.render(g);
		g2d.translate(-cam.getX(), -CAMERA_OFFSET_Y);
		
		// clean for next frame
		g.dispose();
		buf.show();
	}
}






