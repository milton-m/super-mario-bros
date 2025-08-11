package com.game.object;

import java.awt.Rectangle;

public abstract class BoundObject extends GameObject {
	private static final float GRAVITY = 0.5f;

	private float velX, velY;
	private Rectangle rect;

	/**
	 * Constructs the internal state for a bound object.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param width sprite's width
	 * @param height sprite's height
	 */
	public BoundObject(float x, float y, float width, float height) {
		super(x, y, width, height);
		rect = new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}
	
	/**
	 * Ticks the object's state
	 */
	public abstract void tick();
	
	/**
	 * Determines whether this object should be removed from the game.
	 * 
	 * @return {@code true} if the object should be removed; {@code false} otherwise
	 */
	public abstract boolean shouldRemove();
	
	/**
	 * Returns the bound rectangle.
	 * 
	 * @return bound rectangle
	 */
	public Rectangle getBounds() {
		return rect;
	}
	
	/**
	 * Returns the gravity constant.
	 * 
	 * @return gravity constant
	 */
	public static float getGravity() {
		return GRAVITY;
	}

	/**
	 * Applies gravity to object's velocity.
	 */
	public void applyGravity() {
		velY += GRAVITY;
	}
	
	/**
	 * Sets the x-velocity.
	 * 
	 * @param velX x-velocity
	 */
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	/**
	 * Sets the y-velocity.
	 * 
	 * @param velY y-velocity
	 */
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	/**
	 * Returns the x-velocity.
	 * 
	 * @return x-velocity
	 */
	public float getVelX() {
		return velX;
	}
	
	/**
	 * Returns the y-velocity.
	 * 
	 * @return y-velocity
	 */
	public float getVelY() {
		return velY;
	}
}








