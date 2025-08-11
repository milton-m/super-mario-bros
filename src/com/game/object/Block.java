package com.game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.ObjectTag;

public class Block extends BoundObject {
	private static final float SPRITE_WIDTH = Texture.getUnitWidth();
	private static final float SPRITE_HEIGHT = Texture.getUnitWidth();
	
	private static BufferedImage[] tileSprites = Game.getTexture().getTileSprites();
	
	private int spriteIndex;
	
	/**
	 * Creates a block object.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param spriteIndex sprite's index
	 */
	public Block(float x, float y, int spriteIndex) {
		super(x, y, SPRITE_WIDTH, SPRITE_HEIGHT);
		this.spriteIndex = spriteIndex;
	}
	
	@Override
	public ObjectTag getTag() {
		return ObjectTag.BLOCK;
	}

	@Override
	public void tick() {
		// should be empty for Block class
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(tileSprites[spriteIndex], (int) getX(), (int) getY(), 
				(int) getWidth(), (int) getHeight(), null);
	}
	
	@Override
	public boolean shouldRemove() {
		return false; // Block objects should never be removed
	}
	
	/**
	 * Sets the sprite's index.
	 * 
	 * @param spriteIndex sprite's index
	 */
	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
}
