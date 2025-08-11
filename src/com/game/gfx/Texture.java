package com.game.gfx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Texture {
	private static final String PARENT_FOLDER = "/tile";
	private static final Color BACKGROUND_COLOR = new Color(151, 141, 255);
	
	private static final int MARIO_L_COUNT = 21;
	private static final int MARIO_S_COUNT = 14;
	private static final int TILE_1_COUNT = 28;
	private static final int TILE_2_COUNT = 33;
	private static final int PIPE_COUNT = 4;
	private static final int DEBRIS_COUNT = 4;
	private static final int ITEM_COUNT = 14;
	private static final int ITEM_GROUP_COUNT = 4;
	private static final int ENEMY_COUNT = 6;
	private static final int BACKGROUND_COUNT = 10;
	
	private static final int UNIT_WIDTH = 16; // width for block, Mario and others
	
	private BufferedImage playerSheet, blockSheet, tileSheet, itemSheet, enemySheet, backgroundSheet;
	private BufferedImage[] marioLSprites, marioSSprites, tileSprites, pipeSprites, debrisSprites,
		itemSprites, enemySprites, backgroundSprites;

	/**
	 * Creates a texture object.
	 */
	public Texture() {
		marioLSprites = new BufferedImage[MARIO_L_COUNT];
		marioSSprites = new BufferedImage[MARIO_S_COUNT];
		tileSprites = new BufferedImage[TILE_1_COUNT + TILE_2_COUNT];
		pipeSprites = new BufferedImage[PIPE_COUNT];
		debrisSprites = new BufferedImage[DEBRIS_COUNT];
		itemSprites = new BufferedImage[ITEM_COUNT];
		enemySprites = new BufferedImage[ENEMY_COUNT];
		backgroundSprites = new BufferedImage[BACKGROUND_COUNT];
		
		playerSheet = BufferedImageLoader.loadImage(PARENT_FOLDER + "/mario_and_luigi.png");
		blockSheet = BufferedImageLoader.loadImage(PARENT_FOLDER + "/item_and_brick_blocks.png");
		tileSheet = BufferedImageLoader.loadImage(PARENT_FOLDER + "/tileset.png");
		itemSheet = BufferedImageLoader.loadImage(PARENT_FOLDER + "/items_objects_and_npcs.png");
		enemySheet = BufferedImageLoader.loadImage(PARENT_FOLDER + "/enemies_and_bosses.png");
		backgroundSheet = BufferedImageLoader.loadImage(PARENT_FOLDER + "/background_flag_and_castle.gif");
		
		extractPlayerSprites();
		extractTileSprites();
		extractPipeSprites();
		extractDebrisSprites();
		extractItemSprites();
		extractEnemySprites();
		extractBackgroundSprites();
	}
	
	/**
	 * Returns the unit width constant. 
	 * 
	 * @return unit width constant
	 */
	public static int getUnitWidth() {
		return UNIT_WIDTH;
	}
	
	/**
	 * Returns the background color constant.
	 * 
	 * @return background color constant
	 */
	public static Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}
	
	/**
	 * Returns the Mario large sprites.
	 * 
	 * @return Mario large sprites
	 */
	public BufferedImage[] getMarioLSprites() {
		return marioLSprites;
	}
	
	/**
	 * Returns the Mario small sprites.
	 * 
	 * @return Mario small sprites
	 */
	public BufferedImage[] getMarioSSprites() {
		return marioSSprites;
	}
	
	/**
	 * Returns the tile sprites.
	 * 
	 * @return tile sprites
	 */
	public BufferedImage[] getTileSprites() {
		return tileSprites;
	}
	
	/**
	 * Returns the pipe sprites.
	 * 
	 * @return pipe sprites
	 */
	public BufferedImage[] getPipeSprites() {
		return pipeSprites;
	}
	
	/**
	 * Returns the debris sprites.
	 * 
	 * @return debris sprites
	 */
	public BufferedImage[] getDebrisSprites() {
		return debrisSprites;
	}
	
	/**
	 * Returns the item sprites.
	 * 
	 * @return item sprites
	 */
	public BufferedImage[] getItemSprites() {
		return itemSprites;
	}
	
	/**
	 * Returns the enemy sprites.
	 * 
	 * @return enemy sprites
	 */
	public BufferedImage[] getEnemySprites() {
		return enemySprites;
	}
	
	/**
	 * Returns the background sprites.
	 * 
	 * @return background sprites
	 */
	public BufferedImage[] getBackgroundSprites() {
		return backgroundSprites;
	}
	
	private void extractPlayerSprites() {
		int xOff = 80;
		int yOff = 1;
		int width = UNIT_WIDTH;
		int height = 2*UNIT_WIDTH;
		
		for (int i = 0; i < MARIO_L_COUNT; i++) {
			marioLSprites[i] = playerSheet.getSubimage(xOff + i*(width+1), yOff, width, height);
		}
		
		yOff += height + 1;
		height = UNIT_WIDTH;
		
		for (int i = 0; i < MARIO_S_COUNT; i++) {
			marioSSprites[i] = playerSheet.getSubimage(xOff + i*(width+1), yOff, width, height);
		}
	}
	
	private void extractTileSprites() {
		int xOff = 0;
		int yOff = 0;
		int width = UNIT_WIDTH;
		int height = UNIT_WIDTH;	
		
		for (int i = 0; i < TILE_1_COUNT; i++) {
			tileSprites[i] = tileSheet.getSubimage(xOff + i*width, yOff, width, height);
		}
		
		yOff += height;
		
		for (int i = 0; i < TILE_2_COUNT; i++) {
			tileSprites[i + TILE_1_COUNT] = tileSheet.getSubimage(xOff + i*width, yOff, width, height);
		}
	}
	
	private void extractPipeSprites() {
		int xOff = 0;
		int yOff = 8*UNIT_WIDTH;
		int width = 2*UNIT_WIDTH;
		int height = UNIT_WIDTH;
		
		pipeSprites[0] = tileSheet.getSubimage(xOff, yOff, width, height);
		pipeSprites[1] = tileSheet.getSubimage(xOff + width, yOff, width, height);
		pipeSprites[2] = tileSheet.getSubimage(xOff, yOff + height, width, height);
		pipeSprites[3] = tileSheet.getSubimage(xOff + width, yOff + height, width, height);
	}
	
	private void extractDebrisSprites() {
		int xOff = 304;
		int yOff = 112;
		int width = UNIT_WIDTH/2;
		int height = UNIT_WIDTH/2;
		
		debrisSprites[0] = blockSheet.getSubimage(xOff, yOff, width, height);
		debrisSprites[1] = blockSheet.getSubimage(xOff + width, yOff, width, height);
		debrisSprites[2] = blockSheet.getSubimage(xOff, yOff + height, width, height);
		debrisSprites[3] = blockSheet.getSubimage(xOff + width, yOff + height, width, height);	
	}
	
	private void extractItemSprites() {
		// mushrooms
		
		int xOff = 0;
		int yOff = 0;
		int width = UNIT_WIDTH;
		int height = UNIT_WIDTH;	
		int nbrFilled = 0;
		
		itemSprites[0] = itemSheet.getSubimage(xOff, yOff, width, height);
		itemSprites[1] = itemSheet.getSubimage(xOff + width, yOff, width, height);
		
		// flowers
		
		nbrFilled += 2;
		yOff += 2*height;
		
		for (int i = 0; i < ITEM_GROUP_COUNT; i++) {
			itemSprites[i + nbrFilled] = itemSheet.getSubimage(xOff + i*width, yOff, width, height);
		}
		
		// starman
		
		nbrFilled += ITEM_GROUP_COUNT;
		yOff += height;
		
		for (int i = 0; i < ITEM_GROUP_COUNT; i++) {
			itemSprites[i + nbrFilled] = itemSheet.getSubimage(xOff + i*width, yOff, width, height);
		}
		
		// coins
		
		nbrFilled += ITEM_GROUP_COUNT;
		yOff += 4*height;
		
		for (int i = 0; i < ITEM_GROUP_COUNT; i++) {
			itemSprites[i + nbrFilled] = itemSheet.getSubimage(xOff + i*width, yOff, width, height);
		}
	}

	private void extractEnemySprites() {
		// goombas
		
		int xOff = 0;
		int yOff = 16;
		int width = UNIT_WIDTH;
		int height = UNIT_WIDTH;	

		enemySprites[0] = enemySheet.getSubimage(xOff, yOff, width, height);
		enemySprites[1] = enemySheet.getSubimage(xOff + width, yOff, width, height);
		
		xOff = 32;
		yOff = 24;
		height = UNIT_WIDTH/2;
		
		enemySprites[2] = enemySheet.getSubimage(xOff, yOff, width, height);
		
		// koopas
		
		xOff = 96; 
		yOff = 8;
		width = UNIT_WIDTH;
		height = 3*UNIT_WIDTH/2;
		
		enemySprites[3] = enemySheet.getSubimage(xOff, yOff, width, height);
		enemySprites[4] = enemySheet.getSubimage(xOff + width, yOff, width, height);
		
		xOff = 160;
		yOff = 17;
		width = UNIT_WIDTH;
		height = 14;
		
		enemySprites[5] = enemySheet.getSubimage(xOff, yOff, width, height);
	}
	
	private void extractBackgroundSprites() {
		// hills
		
		int xOff = 48;
		int yOff = 176;
		int width = 3*UNIT_WIDTH;
		int height = 19;
		int leftMargin = 0;
		int topMargin = 13;
		
		backgroundSprites[0] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);
		
		xOff = 99;
		yOff = 160;
		width = 5*UNIT_WIDTH;
		height = 35;
		
		backgroundSprites[1] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);
		
		// clouds
		
		xOff = 46;
		yOff = 198;
		width = 3*UNIT_WIDTH;
		height = 3*UNIT_WIDTH/2;
		leftMargin = UNIT_WIDTH/2;
		topMargin = 0;

		backgroundSprites[2] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);
		
		xOff = 6*UNIT_WIDTH;
		width = 4*UNIT_WIDTH;
		
		backgroundSprites[3] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);
		
		xOff = 162;
		width = 2*UNIT_WIDTH;
		
		backgroundSprites[4] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);
		
		// bushes
		
		xOff = 51;
		yOff = 253;
		width = 2*UNIT_WIDTH;
		height = UNIT_WIDTH;
		
		backgroundSprites[5] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);
		
		xOff = 85;
		width = 4*UNIT_WIDTH;
		
		backgroundSprites[6] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);

		xOff = 151;
		width = 3*UNIT_WIDTH;
		
		backgroundSprites[7] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);
		
		// flag
		
		xOff = 260;
		yOff = 46;
		width = 5*UNIT_WIDTH/4;
		height = 19*UNIT_WIDTH/2;
		leftMargin = UNIT_WIDTH/2;
		topMargin = UNIT_WIDTH/2;
		
		backgroundSprites[8] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);
		
		// castle
		
		xOff = 272;
		yOff = 218;
		width = 5*UNIT_WIDTH;
		height = 5*UNIT_WIDTH;
		leftMargin = 0;
		topMargin = 0;
		
		backgroundSprites[9] = addMargin(backgroundSheet.getSubimage(xOff, yOff, width, height), 
				leftMargin, topMargin);
	}

	private static BufferedImage addMargin(BufferedImage sprite, int leftMargin, int topMargin) {
		BufferedImage newSprite = new BufferedImage(leftMargin + sprite.getWidth(), 
				topMargin + sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newSprite.createGraphics();
		g2d.drawImage(sprite, leftMargin, topMargin, null);
	    g2d.dispose();
	    return newSprite;
	}
}







