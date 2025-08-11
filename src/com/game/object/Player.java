package com.game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Animation;
import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.Handler;
import com.game.object.util.Hittable;
import com.game.object.util.ObjectTag;

public class Player extends MultipleBoundObject {
	private static final int SMALL_HEALTH = 1;
	private static final int LARGE_HEALTH = 2;
	private static final int STARTING_HEALTH = SMALL_HEALTH;
	private static final int COUNTER_BLOCK = 20;
	private static final int COUNTER_MAX = 100;
	private static final float SPRITE_WIDTH = Texture.getUnitWidth();
	private static final float SPRITE_SMALL_HEIGHT = Texture.getUnitWidth();
	private static final float SPRITE_LARGE_HEIGHT = 2 * Texture.getUnitWidth();
	private static final float VEL_JUMP_KILL = -7.5f;
	
	private static BufferedImage[] marioLSprites = Game.getTexture().getMarioLSprites();
	private static BufferedImage[] marioSSprites = Game.getTexture().getMarioSSprites();
	
	private Handler handler;
	private Animation marioLWalk, marioSWalk;
	private BufferedImage[] currSprites;
	private Animation currAnimation;
	
	private int health;
	private float maxX;
	private int animationSpeed = 5;
	private int counter;
	private boolean hideSprite = false;
	private boolean damageImmune = false;
	private boolean onGround = false;
	private boolean forward = true;
	
	/**
	 * Creates a player object.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param handler the game's handler
	 * @param health player's health
	 */
	public Player(float x, float y, Handler handler, int health) {
		super(x, y, SPRITE_WIDTH, -1f);
		this.handler = handler;
		
		marioLWalk = new Animation(animationSpeed, marioLSprites[1], marioLSprites[2], marioLSprites[3]);
		marioSWalk = new Animation(animationSpeed, marioSSprites[1], marioSSprites[2], marioSSprites[3]);
				
		setHealth(health);
	}
	
	/**
	 * Returns the small health constant.
	 * 
	 * @return small health constant
	 */
	public static int getSmallHealth() {
		return SMALL_HEALTH;
	}
	
	/**
	 * Returns the large health constant.
	 * 
	 * @return large health constant
	 */
	public static int getLargeHealth() {
		return LARGE_HEALTH;
	}
	
	/**
	 * Returns the starting health constant.
	 * 
	 * @return starting health constant
	 */
	public static int getStartingHealth() {
		return STARTING_HEALTH;
	}
	
	/**
	 * Returns the constant for jump kill velocity.
	 * 
	 * @return constant for jump kill velocity
	 */
	public static float getVelJumpKill() {
		return VEL_JUMP_KILL;
	}
	
	@Override
	public ObjectTag getTag() {
		return ObjectTag.PLAYER;
	}

	@Override
	public void tick() {
		if (damageImmune) {
			if (counter < COUNTER_MAX) {
				if (counter % COUNTER_BLOCK == 0) {
					hideSprite = !hideSprite;
				}
				counter++;
			} else {
				counter = 0;
				hideSprite = false;
				damageImmune = false;
			}
		}
		
		setX(Math.max(getX() + getVelX(), -getCameraX()));
		setY(getY() + getVelY());
		
		applyGravity();
		
		maxX = Math.max(maxX, getX());
		collision();
		currAnimation.tick();
	}

	@Override
	public void render(Graphics g) {
		if (hideSprite) return;
		
		if (!onGround) {
			if (forward) {
				g.drawImage(currSprites[5], (int) getX(), (int) getY(), 
						(int) getWidth(), (int) getHeight(), null);
			} else {
				g.drawImage(currSprites[5], (int) (getX() + getWidth()), (int) getY(), 
						(int) -getWidth(), (int) getHeight(), null);
			}
		} else if (getVelX() > 0) {
			currAnimation.drawAnimation(g, (int) getX(), (int) getY(), 
					(int) getWidth(), (int) getHeight());
			forward = true;
		} else if (getVelX() < 0) {
			currAnimation.drawAnimation(g, (int) (getX() + getWidth()), (int) getY(), 
					(int) -getWidth(), (int) getHeight());
			forward = false;
		} else {
			g.drawImage(currSprites[0], (int) getX(), (int) getY(), 
					(int) getWidth(), (int) getHeight(), null);
		}
		renderBounds(g);
	}
	
	@Override
	public boolean shouldRemove() {
		return false; // Player objects should never be removed
	}

	/**
	 * Returns the player's health.
	 * 
	 * @return player's health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets the player's health.
	 * 
	 * @param health player's health
	 */
	public void setHealth(int health) {
		if (health == SMALL_HEALTH) {
			if (this.health == LARGE_HEALTH) {
				float diff = SPRITE_LARGE_HEIGHT - SPRITE_SMALL_HEIGHT;
				setY(getY() + diff);
			}
			currSprites = marioSSprites;
			currAnimation = marioSWalk;
			setHeight(SPRITE_SMALL_HEIGHT);
		} else if (health == LARGE_HEALTH) {
			if (this.health == SMALL_HEALTH) {
				float diff = SPRITE_LARGE_HEIGHT - SPRITE_SMALL_HEIGHT;
				setY(getY() - diff);
			}
			currSprites = marioLSprites;
			currAnimation = marioLWalk;
			setHeight(SPRITE_LARGE_HEIGHT);
		} else {
			System.exit(0);
		}
		updateBoundSize();
		this.health = health;
	}
	
	/**
	 * Returns the camera's position.
	 * 
	 * @return camera's position
	 */
	public int getCameraX() {
		return (int) (-maxX) + Game.getWindowWidth()/2 + Game.getCameraOffsetX();
	}
	
	/**
	 * Returns the window's right x-coordinate.
	 * 
	 * @return window's right x-coordinate
	 */
	public int getWindowRight() {
		return -getCameraX() + Game.getWindowWidth(); 
	}
	
	/**
	 * Determines whether this player is damage immune.
	 * 
	 * @return {@code true} if the player is damage immune; {@code false} otherwise
	 */
	public boolean isDamageImmune() {
		return damageImmune;
	}
	
	/**
	 * Determines whether this object is on ground.
	 * 
	 * @return {@code true} if the object is on ground; {@code false} otherwise
	 */
	public boolean isOnGround() {
		return onGround;
	}
	
	/**
	 * Does damage to the player.
	 */
	public void doDamage() {
		damageImmune = true;
		setHealth(health - 1);
	}
	
	private void collision() {
		onGround = false;
		for (BoundObject temp : handler.getBoundObjs()) {
			if (temp == this) continue;
			if (temp.getTag() == ObjectTag.BRICK && ((Brick) temp).hasDebris()) continue;
			
			if (getBoundsBottom().intersects(temp.getBounds())) {
				setY(temp.getY() - getHeight());
				setVelY(0);
				onGround = true;
				continue;
			}
			
			if (getBoundsTop().intersects(temp.getBounds())) {
				setY(temp.getY() + temp.getHeight());
				setVelY(0);
				
				if (temp instanceof Hittable hittable) hittable.hit();
				continue;
			}
			
			if (getBoundsRight().intersects(temp.getBounds())) {
				setX(temp.getX() - getWidth());
				continue;
			}
			
			if (getBoundsLeft().intersects(temp.getBounds())) {
				setX(temp.getX() + temp.getWidth());
				continue;
			}
		}
	}
}







