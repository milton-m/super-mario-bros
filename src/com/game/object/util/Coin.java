package com.game.object.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Animation;
import com.game.main.Game;
import com.game.object.BoundObject;

public class Coin {
	private static BufferedImage[] itemSprites = Game.getTexture().getItemSprites();
	
	private static final float INITIAL_VEL_X = 0f; 
	private static final float INITIAL_VEL_Y = -9f;
	private static final int ANIMATION_SPEED = 2;
	
	private Animation animation;
	
	private float width, height, velX, velY;
	private float x, y;
	private final float initialY;
	
	/**
	 * Creates a coin object.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param width sprite's width
	 * @param height sprite's height
	 */
	public Coin(float x, float y, float width, float height) {
		animation = new Animation(ANIMATION_SPEED, itemSprites[10], itemSprites[11], 
				itemSprites[12], itemSprites[13]);
		
		this.x  = x;
		this.y = y - height;
		initialY = this.y;
		
		this.width = width;
		this.height = height;
		
		velX = INITIAL_VEL_X;
		velY = INITIAL_VEL_Y;
	}
	
	/**
	 * Ticks the object's state
	 */
	public void tick() {		
		x += velX;
		y += velY;
		
		applyGravity();
		
		animation.tick();
	}
	
	/**
	 * Determines whether this object should be removed from the game.
	 * 
	 * @return {@code true} if the object should be removed; {@code false} otherwise
	 */
	public boolean shouldRemove() {
		return y > initialY;
	}
	
	/**
	 * Renders the sprite.
	 * 
	 * @param g game's graphics object
	 */
	public void render(Graphics g) {
		animation.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
	}
	
	private void applyGravity() {
		velY += BoundObject.getGravity();
	}
}
