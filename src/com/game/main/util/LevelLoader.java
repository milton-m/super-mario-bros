package com.game.main.util;

import java.awt.image.BufferedImage;

import com.game.gfx.BufferedImageLoader;
import com.game.gfx.Texture;
import com.game.object.BackgroundObject;
import com.game.object.Block;
import com.game.object.Brick;
import com.game.object.Goomba;
import com.game.object.LuckyBoxCoin;
import com.game.object.LuckyBoxPowerUp;
import com.game.object.Pipe;
import com.game.object.Player;
import com.game.object.util.Handler;

public class LevelLoader {
	private static final String PARENT_FOLDER = "/level";
	private static final int UNIT_WIDTH = Texture.getUnitWidth();
	
	private Handler handler;
	
	/**
	 * Creates a level loader object.
	 * 
	 * @param handler the game's handler
	 */
	public LevelLoader(Handler handler) {
		this.handler = handler;
	}

	/**
	 * Load in the foreground, background and entities.
	 */
	public void loadLevel() {
		setForeground(PARENT_FOLDER + "/1_1_foreground.png");
		setBackground(PARENT_FOLDER + "/1_1_background.png");
		setEntities(PARENT_FOLDER + "/1_1_entities.png");
	}
	
	private void setForeground(String levelForegroundPath) {
		BufferedImage levelTiles = BufferedImageLoader.loadImage(levelForegroundPath);
		
		int width = levelTiles.getWidth();
		int height = levelTiles.getHeight();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int pixel = levelTiles.getRGB(col, row);
				
				if (pixel == TileConstants.VOID_RGB) { continue; }
				
				if (pixel == TileConstants.GROUND_RGB) { // ground
					handler.addObj(new Block(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.GROUND_INDEX));
				} else if (pixel == TileConstants.BRICK_RGB) { // brick
					handler.addObj(new Brick(col*UNIT_WIDTH, row*UNIT_WIDTH));
				} else if (pixel == TileConstants.STAIR_RGB) { // stair
					handler.addObj(new Block(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.STAIR_INDEX));
				} else if (pixel == TileConstants.LUCKY_BOX_COIN_RGB) { // lucky box coin
					handler.addObj(new LuckyBoxCoin(col*UNIT_WIDTH, row*UNIT_WIDTH));
				} else if (pixel == TileConstants.LUCKY_BOX_POWER_UP_RGB) { // lucky box power-up
					handler.addObj(new LuckyBoxPowerUp(col*UNIT_WIDTH, row*UNIT_WIDTH, handler));
				} else if (pixel == TileConstants.PIPE_NECK_RGB) { // pipe neck
					handler.addObj(new Pipe(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.PIPE_NECK_INDEX, false));
				} else if (pixel == TileConstants.PIPE_HEAD_RGB) { // pipe head
					handler.addObj(new Pipe(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.PIPE_HEAD_INDEX, false));
				}
			}
		}
	}
	
	private void setBackground(String levelBackgroundPath) {
		BufferedImage levelTiles = BufferedImageLoader.loadImage(levelBackgroundPath);
		
		int width = levelTiles.getWidth();
		int height = levelTiles.getHeight();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int pixel = levelTiles.getRGB(col, row);
				
				if (pixel == TileConstants.VOID_RGB) { continue; }
				
				if (pixel == TileConstants.SMALL_HILL_RGB) { // small hill
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.SMALL_HILL_INDEX, TileConstants.OTHER_LAYER_INDEX));
				} else if (pixel == TileConstants.LARGE_HILL_RGB) { // large hill
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.LARGE_HILL_INDEX, TileConstants.OTHER_LAYER_INDEX));
				} else if (pixel == TileConstants.MEDIUM_CLOUD_RGB) { // medium cloud
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.MEDIUM_CLOUD_INDEX, TileConstants.OTHER_LAYER_INDEX));
				} else if (pixel == TileConstants.LARGE_CLOUD_RGB) { // large cloud
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.LARGE_CLOUD_INDEX, TileConstants.OTHER_LAYER_INDEX));
				} else if (pixel == TileConstants.SMALL_CLOUD_RGB) { // small cloud
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.SMALL_CLOUD_INDEX, TileConstants.OTHER_LAYER_INDEX));
				} else if (pixel == TileConstants.SMALL_BUSH_RGB) { // small bush
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.SMALL_BUSH_INDEX, TileConstants.OTHER_LAYER_INDEX));
				} else if (pixel == TileConstants.LARGE_BUSH_RGB) { // large bush
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.LARGE_BUSH_INDEX, TileConstants.OTHER_LAYER_INDEX));
				} else if (pixel == TileConstants.MEDIUM_BUSH_RGB) { // medium bush
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.MEDIUM_BUSH_INDEX, TileConstants.OTHER_LAYER_INDEX));
				} else if (pixel == TileConstants.FLAG_RGB) { // flag
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.FLAG_INDEX, TileConstants.OTHER_LAYER_INDEX));
				} else if (pixel == TileConstants.CASTLE_RGB) { // castle
					handler.addObj(new BackgroundObject(col*UNIT_WIDTH, row*UNIT_WIDTH, 
							TileConstants.CASTLE_INDEX, TileConstants.CASTLE_LAYER_INDEX));
				}
			}
		}
		
		handler.sortBackgroundObjs();
	}
	
	private void setEntities(String levelEntitiesPath) {
		BufferedImage levelTiles = BufferedImageLoader.loadImage(levelEntitiesPath);
		
		int width = levelTiles.getWidth();
		int height = levelTiles.getHeight();
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int pixel = levelTiles.getRGB(col, row);
				
				if (pixel == TileConstants.VOID_RGB) { continue; }
				
				if (pixel == TileConstants.MARIO_RGB) { // Mario
					handler.setPlayer(new Player(col*UNIT_WIDTH, row*UNIT_WIDTH, handler, 
							Player.getStartingHealth()));
				} else if (pixel == TileConstants.GOOMBA_CENTERED_RGB) { // centered goomba
					handler.addObj(new Goomba(col*UNIT_WIDTH, row*UNIT_WIDTH, handler));
				} else if (pixel == TileConstants.GOOMBA_BETWEEN_RGB) { // between goomba
					handler.addObj(new Goomba(col*UNIT_WIDTH + UNIT_WIDTH/2, row*UNIT_WIDTH, handler));
				}
			}
		}
	}
}
