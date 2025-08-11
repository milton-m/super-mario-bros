package com.game.object;

import java.awt.Graphics;

import com.game.main.util.TileConstants;
import com.game.object.util.Coin;
import com.game.object.util.Hittable;
import com.game.object.util.ObjectTag;

public class LuckyBoxCoin extends Block implements Hittable {
	private boolean hit = false;
	private Coin coin;
	
	/**
	 * Creates a coin lucky box.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public LuckyBoxCoin(float x, float y) {
		super(x, y, TileConstants.LUCKY_BOX_INDEX);
	}

	@Override
	public ObjectTag getTag() {
		return ObjectTag.LUCKY_BOX;
	}
	
	@Override
	public void tick() {
		if (coin != null) {
			if (coin.shouldRemove()) {
				coin = null;
				return;
			}
			coin.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		if (coin != null) {
			coin.render(g);
		}
		super.render(g);
	}
	
	@Override
	public void hit() {
		if (!hit) {
			hit = true;
			setSpriteIndex(TileConstants.EMPTY_BOX_INDEX);
			coin = new Coin(getX(), getY(), getWidth(), getHeight());
		}
	}
}
