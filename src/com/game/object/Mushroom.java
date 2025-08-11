package com.game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.main.util.TileConstants;
import com.game.object.util.Handler;
import com.game.object.util.ObjectTag;

public class Mushroom extends MultipleBoundObject {
	private static final float SPRITE_WIDTH = Texture.getUnitWidth();
	private static final float SPRITE_HEIGHT = Texture.getUnitWidth();
	private static final int SPRITE_INDEX = TileConstants.MUSHROOM_INDEX;
	private static final float POPPING_UP_SPEED = 1.5f;
	private static final float MOVING_SPEED = 4f;
	
	private static BufferedImage[] itemSprites = Game.getTexture().getItemSprites();
	
	private Handler handler;
	
	private boolean isPoppingUp = true;
	private boolean shouldRemove = false;
	private float mushroomFinalY;
	
	/**
	 * Creates a mushroom object.
	 * 
	 * @param x x-coorinate
	 * @param y y-coordinate
	 * @param handler the game's handler
	 */
	public Mushroom(float x, float y, Handler handler) {
		super(x, y, SPRITE_WIDTH, SPRITE_HEIGHT);
		this.handler = handler;
		
		setVelX(MOVING_SPEED);
		mushroomFinalY = getY() - getHeight();
	}
	
	@Override
	public ObjectTag getTag() {
		return ObjectTag.POWER_UP;
	}
	
	@Override
	public void tick() {
		if (isPoppingUp) {
			if (getY() <= mushroomFinalY) {
				setY(mushroomFinalY);
				isPoppingUp = false;
			} else {
				setY(getY() - POPPING_UP_SPEED);
			}
		} else {
			setX(getX() + getVelX());
			setY(getY() + getVelY());
			
			applyGravity();
			
			collision();
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(itemSprites[SPRITE_INDEX], (int) getX(), (int) getY(), 
				(int) getWidth(), (int) getHeight(), null);
		renderBounds(g);
	}
	
	@Override
	public boolean shouldRemove() {
		return shouldRemove;
	}

	private void collision() {
		for (BoundObject temp : handler.getBoundObjs()) {
			if (temp.getTag() == ObjectTag.BRICK && ((Brick) temp).hasDebris()) continue;
			
			if (temp.getTag() == ObjectTag.PLAYER && getBounds().intersects(temp.getBounds())) {
				shouldRemove = true;
				if (((Player) temp).getHealth() == Player.getSmallHealth()) {
					handler.getPlayer().setHealth(Player.getLargeHealth());
				}
				continue;
			}
			
			if (getBoundsBottom().intersects(temp.getBounds())) {
				setY(temp.getY() - getHeight());
				setVelY(0);
				continue;
			}
			
			if (getBoundsRight().intersects(temp.getBounds())) {
				setX(temp.getX() - getWidth());
				
				// only change direction if not falling. When falling, getVelY() > getGravity().
				// When walking, getVelY() == getGravity() due to applyGravity() in tick().
				if (getVelY() <= getGravity()) {
					setVelX(-MOVING_SPEED);
				}
				continue;
			}
			
			if (getBoundsLeft().intersects(temp.getBounds())) {
				setX(temp.getX() + temp.getWidth());
				
				if (getVelY() <= getGravity()) {
					setVelX(MOVING_SPEED);
				}
				continue;
			}
		}
	}
}
