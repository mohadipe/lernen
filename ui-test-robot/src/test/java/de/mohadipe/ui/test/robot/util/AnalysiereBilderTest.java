package de.mohadipe.ui.test.robot.util;

import java.awt.image.BufferedImage;

import org.junit.Test;

public class AnalysiereBilderTest {

	@Test
	public void ladeBildPrintRGB() {
		BufferedImage muenze01 = new BilderLaden(null).ladeEinPixel();
		int width = muenze01.getWidth();
		int height = muenze01.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				System.out.println("W: " + i + "; H: " + j + "; RGB: " + muenze01.getRGB(i, j));
			}
		}
	}
	
}
