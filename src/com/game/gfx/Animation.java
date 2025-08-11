package com.game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {	
	private int speed;
	
	private int count = 0;
	private int index = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	/**
	 * Creates an animation object.
	 * 
	 * @param speed number of ticks between frames
	 * @param images animation frames
	 */
	public Animation(int speed, BufferedImage... images) {
		this.speed = speed;
		this.images = images;
		currentImg = images[index];
	}
	
	/**
	 * Ticks state by incrementing count.
	 */
	public void tick() {
		count++;
		if (count > speed) {
			count = 0;
			nextFrame();
		}
	}
	
	/**
	 * Draws the current frame.
	 * 
	 * @param g game's graphics object
	 * @param x sprite x-coordinate
	 * @param y sprite y-coordinate
	 * @param width sprite width
	 * @param height sprite height
	 */
	public void drawAnimation(Graphics g, int x, int y, int width, int height) {
		g.drawImage(currentImg, x, y, width, height, null);
	}
	
	private void nextFrame() {
		index++;
		if (index >= images.length) {
			index = 0;
		}
		
		currentImg = images[index];
	}
}
