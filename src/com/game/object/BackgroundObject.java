package com.game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.main.Game;
import com.game.object.util.ObjectTag;

public class BackgroundObject extends GameObject {
	private static BufferedImage[] backgroundSprites = Game.getTexture().getBackgroundSprites();
	
	private int spriteIndex;
	private int layerIndex;
	
	/**
	 * Creates a background object. 
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param spriteIndex sprite's index
	 * @param layerIndex index for layering
	 */
	public BackgroundObject(float x, float y, int spriteIndex, int layerIndex) {
		super(x, y, backgroundSprites[spriteIndex].getWidth(), backgroundSprites[spriteIndex].getHeight());
		this.spriteIndex = spriteIndex;
		this.layerIndex = layerIndex;
	}
	
	@Override
	public ObjectTag getTag() {
		return ObjectTag.BACKGROUND;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(backgroundSprites[spriteIndex], (int) getX(), (int) getY(), 
				(int) getWidth(), (int) getHeight(), null);
	}
	
	/**
	 * Returns the layer index.
	 * 
	 * @return layer index
	 */
	public int getLayerIndex() {
		return layerIndex;
	}
}
