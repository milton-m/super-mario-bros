package com.game.object;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class MultipleBoundObject extends BoundObject {
	private static final int BOUND_THICKNESS = 5; // thickness of left and right bound
	private static final int GROUND_BUFFER_MARGIN = 1; // buffer for bottom bound to avoid 
													  // flickering between non-/contact
	
	private Rectangle bottomRect;
	private Rectangle topRect;
	private Rectangle rightRect;
	private Rectangle leftRect;
	
	/**
	 * Constructs the internal state for a multiple bound object.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param width sprite's width
	 * @param height sprite's height
	 */
	public MultipleBoundObject(float x, float y, float width, float height) {
		super(x, y, width, height);
		
		bottomRect = new Rectangle((int) (getX() + getWidth()/4),
				(int) (getY() + getHeight()/2),
				(int) getWidth()/2,
				(int) getHeight()/2 + GROUND_BUFFER_MARGIN);
		topRect = new Rectangle((int) (getX() + getWidth()/4),
				(int) getY(),
				(int) getWidth()/2,
				(int) getHeight()/2);
		rightRect = new Rectangle((int) (getX() + getWidth() - BOUND_THICKNESS),
				(int) (getY() + BOUND_THICKNESS),
				BOUND_THICKNESS,
				(int) (getHeight() - 2*BOUND_THICKNESS));
		leftRect = new Rectangle((int) getX(),
				(int) (getY() + BOUND_THICKNESS),
				BOUND_THICKNESS,
				(int) (getHeight() - 2*BOUND_THICKNESS));
	}
	
	//@Override
	public Rectangle getBounds() {
		super.getBounds().setLocation((int) getX(), (int) getY());
		return super.getBounds();
	}
	
	/**
	 * Returns the bottom bound rectangle.
	 * 
	 * @return bottom bound rectangle
	 */
	public Rectangle getBoundsBottom() {
		bottomRect.setLocation((int) (getX() + getWidth()/4), (int) (getY() + getHeight()/2));
		return bottomRect;
	}
	
	/**
	 * Returns the top bound rectangle.
	 * 
	 * @return top bound rectangle
	 */
	public Rectangle getBoundsTop() {
		topRect.setLocation((int) (getX() + getWidth()/4), (int) getY());
		return topRect;
	}
	
	/**
	 * Returns the right bound rectangle.
	 * 
	 * @return right bound rectangle
	 */
	public Rectangle getBoundsRight() {
		rightRect.setLocation((int) (getX() + getWidth() - BOUND_THICKNESS), 
				(int) (getY() + BOUND_THICKNESS));
		return rightRect;
	}
	
	/**
	 * Returns the left bound rectangle.
	 * 
	 * @return left bound rectangle
	 */
	public Rectangle getBoundsLeft() {
		leftRect.setLocation((int) getX(), (int) (getY() + BOUND_THICKNESS));
		return leftRect;
	}
	
	/**
	 * Updates the bound rectangle sizes.
	 */
	public void updateBoundSize() {
		int width = (int) getWidth();
		int height = (int) getHeight();
		
		getBounds().setSize(width, height);
		getBoundsBottom().setSize(width/2, height/2 + GROUND_BUFFER_MARGIN);
		getBoundsTop().setSize(width/2, height/2);
		getBoundsRight().setSize(BOUND_THICKNESS, height - 2*BOUND_THICKNESS);
		getBoundsLeft().setSize(BOUND_THICKNESS, height - 2*BOUND_THICKNESS);
	}
	
	/**
	 * Renders the object's bound rectangles.
	 * 
	 * @param g game's graphics object
	 */
	public void renderBounds(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g;
//		
//		g.setColor(Color.yellow);
//		g2d.draw(getBounds());
//		
//		g.setColor(Color.red);
//		g2d.draw(getBoundsBottom());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsTop());
	}
}
