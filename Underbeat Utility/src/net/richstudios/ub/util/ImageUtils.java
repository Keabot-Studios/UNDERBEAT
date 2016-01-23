package net.richstudios.ub.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageUtils {

	public static BufferedImage replaceColors(BufferedImage image, Color[] from, Color[] to) {
		if (from.length != to.length)
			return null;
		BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				for (int c = 0; c < from.length; c++) {
					if (image.getRGB(x, y) == from[c].getRGB()) {
						out.setRGB(x, y, to[c].getRGB());
					}
				}
			}
		}
		return out;
	}

	public static BufferedImage replaceColor(BufferedImage image, Color from, Color to) {
		BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (image.getRGB(x, y) == from.getRGB()) {
					out.setRGB(x, y, to.getRGB());
				}
			}
		}
		return out;
	}

	public static Color[] getWhiteScale() {
		return new Color[] { new Color(255, 255, 255), new Color(128, 128, 128), new Color(64, 64, 64), new Color(0, 0, 0) };
	}
}
