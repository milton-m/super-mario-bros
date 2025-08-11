package com.game.main.util;

import java.awt.Color;

public class TileConstants {
	// RGB:s
	public static final int VOID_RGB = new Color(255, 255, 255).getRGB();

	public static final int GROUND_RGB = new Color(127, 127, 127).getRGB();
	public static final int BRICK_RGB = new Color(185, 122, 87).getRGB();
	public static final int STAIR_RGB = new Color(136, 0, 21).getRGB();
	public static final int LUCKY_BOX_COIN_RGB = new Color(255, 242, 0).getRGB();
	public static final int LUCKY_BOX_POWER_UP_RGB = new Color(255, 127, 39).getRGB();
	public static final int PIPE_NECK_RGB = new Color(181, 230, 29).getRGB();
	public static final int PIPE_HEAD_RGB = new Color(34, 177, 76).getRGB();
	
	public static final int SMALL_HILL_RGB = new Color(181, 230, 29).getRGB();
	public static final int LARGE_HILL_RGB = new Color(34, 177, 76).getRGB();
	public static final int MEDIUM_CLOUD_RGB = new Color(127, 127, 127).getRGB();
	public static final int LARGE_CLOUD_RGB = new Color(0, 0, 0).getRGB();
	public static final int SMALL_CLOUD_RGB = new Color(195, 195, 195).getRGB();
	public static final int SMALL_BUSH_RGB = new Color(153, 217, 234).getRGB();
	public static final int LARGE_BUSH_RGB = new Color(63, 72, 204).getRGB();
	public static final int MEDIUM_BUSH_RGB = new Color(112, 146, 190).getRGB();
	public static final int FLAG_RGB = new Color(185, 122, 87).getRGB();
	public static final int CASTLE_RGB = new Color(136, 0, 21).getRGB();
	
	public static final int MARIO_RGB = new Color(237, 28, 36).getRGB();
	public static final int GOOMBA_CENTERED_RGB = new Color(185, 122, 87).getRGB(); // centered at block
	public static final int GOOMBA_BETWEEN_RGB = new Color(136, 0, 21).getRGB(); // in between blocks

	// sprite indices
	public static final int GROUND_INDEX = 0;
	public static final int BRICK_INDEX = 2;
	public static final int STAIR_INDEX = 28;
	public static final int LUCKY_BOX_INDEX = 24;
	public static final int EMPTY_BOX_INDEX = 3;
	public static final int PIPE_NECK_INDEX = 2;
	public static final int PIPE_HEAD_INDEX = 0;	
	
	public static final int SMALL_HILL_INDEX = 0;
	public static final int LARGE_HILL_INDEX = 1;
	public static final int MEDIUM_CLOUD_INDEX = 2;
	public static final int LARGE_CLOUD_INDEX = 3;
	public static final int SMALL_CLOUD_INDEX = 4;
	public static final int SMALL_BUSH_INDEX = 5;
	public static final int LARGE_BUSH_INDEX = 6;
	public static final int MEDIUM_BUSH_INDEX = 7;
	public static final int FLAG_INDEX = 8;
	public static final int CASTLE_INDEX = 9;
	
	public static final int MUSHROOM_INDEX = 0;
	
	// layer indices
	public static final int CASTLE_LAYER_INDEX = 2;
	public static final int OTHER_LAYER_INDEX = 1;
}
