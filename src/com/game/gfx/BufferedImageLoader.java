package com.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	/**
	 * Loads in an image from specified path. 
	 * 
	 * @param path path of the image
	 * @return the image
	 * @throws IllegalArgumentException if illegal path
	 * @throws RuntimeException if failed to load image from legal path
	 */
	public static BufferedImage loadImage(String path) {
		try {
			URL url = BufferedImageLoader.class.getResource(path);
			if (url == null) {
				throw new IllegalArgumentException("Image not found: " + path);
			}
			return ImageIO.read(url);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load image: " + path, e);
		}
	}
}
