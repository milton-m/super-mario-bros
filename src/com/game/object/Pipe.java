package com.game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.ObjectTag;

public class Pipe extends BoundObject {
	private static final float SPRITE_WIDTH = 2*Texture.getUnitWidth();
	private static final float SPRITE_HEIGHT = Texture.getUnitWidth();
	
	private static BufferedImage[] pipeSprites = Game.getTexture().getPipeSprites();
	
	private int spriteIndex;
	private boolean enterable;
	
	/**
	 * Creates a pipe object.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param spriteIndex sprite's index
	 * @param enterable whether Mario can enter
	 */
	public Pipe(float x, float y, int spriteIndex, boolean enterable) {
		super(x, y, SPRITE_WIDTH, SPRITE_HEIGHT);
		this.enterable = enterable;
		this.spriteIndex = spriteIndex;
	}

	@Override
	public ObjectTag getTag() {
		return ObjectTag.PIPE;
	}
	
	@Override
	public void tick() {
		// should be empty for Pipe class
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(pipeSprites[spriteIndex], (int) getX(), (int) getY(), 
				(int) getWidth(), (int) getHeight(), null);
	}
	
	@Override
	public boolean shouldRemove() {
		return false; // Pipe objects should never be removed
	}
}
