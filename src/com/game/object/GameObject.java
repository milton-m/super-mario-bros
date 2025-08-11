package com.game.object;

import java.awt.Graphics;

import com.game.object.util.ObjectTag;

public abstract class GameObject {
	private static final float SCALE = 3f;
	
	private float x, y;
	private float width, height;

	/**
	 * Constructs the internal state for a game object.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordiate
	 * @param width sprite's width
	 * @param height sprite's height
	 */
	public GameObject(float x, float y, float width, float height) {
		this.x = x * SCALE;
		this.y = y * SCALE;
		this.width = width * SCALE;
		this.height = height * SCALE;
	}
	
	/**
	 * Returns the object's tag.
	 * 
	 * @return object's tag
	 */
	public abstract ObjectTag getTag();
	
	/**
	 * Renders the sprite.
	 * 
	 * @param g game's graphics object
	 */
	public abstract void render(Graphics g);
	
	/**
	 * Sets the x-coordinate.
	 * 
	 * @param x x-coordinate
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Sets the y-coordinate.
	 * 
	 * @param y y-coordinate
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Sets the height.
	 * 
	 * @param height (unscaled) sprite height
	 */
	public void setHeight(float height) {
		this.height = height*SCALE;
	}
	
	/**
	 * Returns the x-coordinate.
	 * 
	 * @return x-coordinate
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Returns the y-coordinate.
	 * 
	 * @return y-coordinate
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Returns the width.
	 * 
	 * @return sprite's width
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Returns the height.
	 * 
	 * @return sprite's height
	 */
	public float getHeight() {
		return height;
	}
}








