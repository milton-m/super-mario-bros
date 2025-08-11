package com.game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.main.Game;
import com.game.main.util.TileConstants;
import com.game.object.util.Hittable;
import com.game.object.util.ObjectTag;

public class Brick extends Block implements Hittable {
	private boolean hit;
	private Debris debris;

	/**
	 * Creates a brick object.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Brick(float x, float y) {
		super(x, y, TileConstants.BRICK_INDEX);
	}

	@Override
	public ObjectTag getTag() {
		return ObjectTag.BRICK;
	}
	
	@Override
	public void tick() {
		if (hit) {
			debris.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		if (hit) {
			debris.render(g);
		} else {
			super.render(g);
		}
	}
	
	@Override
	public void hit() {
		hit = true;
		debris = new Debris(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public boolean shouldRemove() {
		if (debris == null) return false;
		return debris.shouldRemove();
	}
	
	/**
	 * Determines whether this object has debris.
	 * 
	 * @return {@code true} if the object has debris; {@code false} otherwise
	 */
	public boolean hasDebris() {
		return debris != null;
	}
	
	private static class Debris {
		private static BufferedImage[] debrisSprites = Game.getTexture().getDebrisSprites();
		
		private static final float INITIAL_VEL_X = 2f; 
		private static final float INITIAL_VEL_Y = -7f;
		private static final float VEL_BOOST = -2f; 
		
		private float width, height, velX, velY;
		private float[] x, y;
		
		private Debris(float x, float y, float width, float height) {
			this.x = new float[4];
			this.y = new float[4];
			
			this.x[0] = x - (0.5f * width);
			this.x[1] = x + (0.5f * width);
			this.x[2] = x - (0.5f * width);
			this.x[3] = x + (0.5f * width);
			
			this.y[0] = y - (0.5f * width);
			this.y[1] = y - (0.5f * width);
			this.y[2] = y + (0.5f * width);
			this.y[3] = y + (0.5f * width);
			
			this.width = width/2f;
			this.height = height/2f;
			
			velX = INITIAL_VEL_X;
			velY = INITIAL_VEL_Y;
		}
		
		private void applyGravity() {
			velY += BoundObject.getGravity();
		}
		
		private void tick() {
			x[0] += -velX;
			x[1] += velX;
			x[2] += -velX;
			x[3] += velX;
			
			y[0] += velY + VEL_BOOST;
			y[1] += velY + VEL_BOOST;
			y[2] += velY;
			y[3] += velY;
			
			applyGravity();
		}
		
		private boolean shouldRemove() {
			return y[0] > Game.getWindowHeight();
		}
		
		private void render(Graphics g) {
			for (int i = 0; i < debrisSprites.length; i++) {
				g.drawImage(debrisSprites[i], (int) x[i], (int) y[i], (int) width, (int) height, null);
			}
		}
	}
}
