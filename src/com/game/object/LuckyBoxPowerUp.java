package com.game.object;

import java.awt.Graphics;

import com.game.main.util.TileConstants;
import com.game.object.util.Handler;
import com.game.object.util.Hittable;
import com.game.object.util.ObjectTag;

public class LuckyBoxPowerUp extends Block implements Hittable {
	private Handler handler;
	
	private boolean hit = false;
	private float mushroomX;
	private float mushroomY;
	
	/**
	 * Creates power up lucky box.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param handler the game's handler
	 */
	public LuckyBoxPowerUp(float x, float y, Handler handler) {
		super(x, y, TileConstants.LUCKY_BOX_INDEX);
		this.handler = handler;
		mushroomX = x;
		mushroomY = y;
	}
	
	@Override
	public ObjectTag getTag() {
		return ObjectTag.LUCKY_BOX;
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	@Override
	public void hit() {
		if (!hit) {
			hit = true;
			setSpriteIndex(TileConstants.EMPTY_BOX_INDEX);
			handler.addObj(new Mushroom(mushroomX, mushroomY, handler));
		}
	}
}
