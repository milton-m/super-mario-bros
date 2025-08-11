package com.game.gfx;

import com.game.object.Player;

public class Camera {
	private int x;
	
	/**
	 * Ticks the camera position.
	 * 
	 * @param player the player
	 */
	public void tick(Player player) {
		x = player.getCameraX();
	}
	
	/**
	 * Returns the current camera position.
	 * 
	 * @return current camera position
	 */
	public int getX() {
		return x;
	}
}
