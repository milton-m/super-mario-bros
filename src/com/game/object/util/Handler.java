package com.game.object.util;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.game.object.BackgroundObject;
import com.game.object.BoundObject;
import com.game.object.GameObject;
import com.game.object.Player;

public class Handler {
	private List<BoundObject> entityObjs;
	private List<BoundObject> boundObjs; // player and blocks/pipes (not enemies, mushrooms) etc.
	private List<BackgroundObject> backgroundObjs;
	private Player player;
	
	/**
	 * Creates a handler object.
	 */
	public Handler() {
		entityObjs = new ArrayList<>();
		boundObjs = new ArrayList<>();
		backgroundObjs = new ArrayList<>();
	}
	
	/**
	 * Ticks the objects.
	 */
	public void tick() {
		for (Iterator<BoundObject> it = entityObjs.iterator(); it.hasNext();) {
			BoundObject obj = it.next();
			obj.tick();
			if (obj.shouldRemove()) it.remove();
		}
		
		for (Iterator<BoundObject> it = boundObjs.iterator(); it.hasNext();) {
			BoundObject obj = it.next();
			obj.tick();
			if (obj.shouldRemove()) it.remove();
		}
	}
	
	/**
	 * Renders the object sprites.
	 * 
	 * @param g game's graphics object
	 */
	public void render(Graphics g) {
		for (GameObject obj : backgroundObjs) {
			obj.render(g);
		}
		
		for (GameObject obj : entityObjs) {
			obj.render(g);
		}
		
		for (GameObject obj : boundObjs) {
			obj.render(g);
		}
	}
	
	/**
	 * Adds an object to the game.
	 * 
	 * @param obj object to add
	 */
	public void addObj(GameObject obj) {
		if (obj.getTag() == ObjectTag.POWER_UP || obj.getTag() == ObjectTag.ENEMY) {
			entityObjs.add((BoundObject) obj);
		} else if (obj instanceof BoundObject) {
			boundObjs.add((BoundObject) obj);
		} else {
			backgroundObjs.add((BackgroundObject) obj);
		}
	}
	
	/**
	 * Removes an object from the game.
	 * 
	 * @param obj object to remove
	 */
	public void removeObj(GameObject obj) {
		if (obj.getTag() == ObjectTag.POWER_UP || obj.getTag() == ObjectTag.ENEMY) {
			entityObjs.remove((BoundObject) obj);
		} else if (obj instanceof BoundObject) {
			boundObjs.remove((BoundObject) obj);
		} else {
			backgroundObjs.remove((BackgroundObject) obj);
		}
	}
	
	/**
	 * Returns the entity objects.
	 * 
	 * @return entity objects
	 */
	public List<BoundObject> getEntityObjs() {
		return entityObjs;
	}
	
	/**
	 * Returns the bound objects.
	 * 
	 * @return bound objects
	 */
	public List<BoundObject> getBoundObjs() {
		return boundObjs;
	}
	
	/**
	 * Sorts the background objects by layer index.
	 */
	public void sortBackgroundObjs() {
		backgroundObjs.sort(Comparator.comparingInt(obj -> obj.getLayerIndex()));
	}
	
	/**
	 * Sets the game's player.
	 * 
	 * @param player game's player
	 * @return {@code 0} if player is successfully set; {@code -1} if already set
	 */
	public int setPlayer(Player player) {
		if (this.player != null) {
			return -1;
		}
		
		addObj(player);
		this.player = player;
		return 0;
	}
	
	/**
	 * Returns the game's player.
	 * 
	 * @return game's player
	 */
	public Player getPlayer() {
		return player;
	}
}




