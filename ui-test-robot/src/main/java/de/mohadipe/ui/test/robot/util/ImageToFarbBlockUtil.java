package de.mohadipe.ui.test.robot.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

import de.mohadipe.ui.test.robot.FarbBlock;
import de.mohadipe.ui.test.robot.Koordinaten2D;

public class ImageToFarbBlockUtil {

	public static FarbBlock erstelleFarbBlock(BufferedImage image) {
		FarbBlock tmp = new FarbBlock();
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				tmp.addFarbe(new Koordinaten2D(w, h), getColor(image, w, h));
			}
		}
		return tmp;
	}
	
	private static Color getColor(BufferedImage currentScreen, int x, int y) {
		try {
			int rgb = currentScreen.getRGB(x, y);
			return new Color(rgb, true);
		} catch (ArrayIndexOutOfBoundsException e) {
			return new Color(0, true);
		}
	}
}
