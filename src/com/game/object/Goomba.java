package com.game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Animation;
import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.Handler;
import com.game.object.util.ObjectTag;

public class Goomba extends MultipleBoundObject {
	private static final float SPRITE_WIDTH = Texture.getUnitWidth();
	private static final float SPRITE_HEIGHT = Texture.getUnitWidth();
	private static final float MOVING_SPEED = 2.5f;
	
	private static BufferedImage[] enemySprites = Game.getTexture().getEnemySprites();
	
	private Handler handler;
	private Animation walkAnimation;
	
	private int animationSpeed = 15;
	private boolean active;
	private boolean shouldRemove = false;

	/**
	 * Creates a goomba object.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param handler the game's handler
	 */
	public Goomba(float x, float y, Handler handler) {
		super(x, y, SPRITE_WIDTH, SPRITE_HEIGHT);
		this.handler = handler;
		
		walkAnimation = new Animation(animationSpeed, enemySprites[0], enemySprites[1]);
		
		setVelX(-MOVING_SPEED);
	}
	
	@Override
	public ObjectTag getTag() {
		return ObjectTag.ENEMY;
	}
	
	@Override
	public void tick() {
		if (!active) {
			if (getX() < handler.getPlayer().getWindowRight()) {
				active = true;
			} else {
				return;
			}
		}
		
		setX(getX() + getVelX());
		setY(getY() + getVelY());
		
		applyGravity();
		
		collision();
		walkAnimation.tick();
	}
	
	@Override
	public void render(Graphics g) {
		if (!active) return;
		
		walkAnimation.drawAnimation(g, (int) getX(), (int) getY(), 
				(int) getWidth(), (int) getHeight());
		renderBounds(g);
	}
	
	@Override
	public boolean shouldRemove() {
		return shouldRemove;
	}

	private void collision() {
		for (BoundObject temp : handler.getBoundObjs()) {
			if (temp.getTag() == ObjectTag.BRICK && ((Brick) temp).hasDebris()) continue;
			
			if (temp.getTag() == ObjectTag.PLAYER) {
				if (!((Player) temp).isDamageImmune()) {
					if (getBoundsTop().intersects(temp.getBounds()) || getBounds().intersects( ((MultipleBoundObject) temp).getBoundsBottom())) {
						shouldRemove = true;
						temp.setVelY(Player.getVelJumpKill());
						continue;
					}
					
					if (getBounds().intersects(temp.getBounds())) {
						((Player) temp).doDamage();
					}
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
