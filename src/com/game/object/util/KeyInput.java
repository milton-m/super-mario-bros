package com.game.object.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.Map;

public class KeyInput extends KeyAdapter {
	private static final float VEL_JUMP = -15f;
	private static final float VEL_WALK = 8f;
	
	private Map<KeyId, Boolean> keyDown;
	private Handler handler;
	
	/**
	 * Creates a KeyInput object.
	 * 
	 * @param handler the game's handler
	 */
	public KeyInput(Handler handler) {
		keyDown = new EnumMap<>(KeyId.class);
		for (KeyId keyId : KeyId.values()) {
			keyDown.put(keyId, false);
		}
				
		this.handler = handler;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		// jump
		if (key == KeyEvent.VK_J) {
			if (!keyDown.get(KeyId.J) && handler.getPlayer().isOnGround()) {
				handler.getPlayer().setVelY(VEL_JUMP);
				keyDown.put(KeyId.J, true);
			}
		}
		
		// move left
		if (key == KeyEvent.VK_A) {
			handler.getPlayer().setVelX(-VEL_WALK);
			keyDown.put(KeyId.A, true);
		}
		
		// move right
		if (key == KeyEvent.VK_D) {
			handler.getPlayer().setVelX(VEL_WALK);
			keyDown.put(KeyId.D, true);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_J) {
			keyDown.put(KeyId.J, false);
		}
		
		if (key == KeyEvent.VK_A) {
			keyDown.put(KeyId.A, false);
		}
		
		if (key == KeyEvent.VK_D) {
			keyDown.put(KeyId.D, false);
		}
		
		if (!keyDown.get(KeyId.A) && !keyDown.get(KeyId.D)) {
			handler.getPlayer().setVelX(0);
		}
	}
	
	private enum KeyId {
		J, A, D
	}
}




