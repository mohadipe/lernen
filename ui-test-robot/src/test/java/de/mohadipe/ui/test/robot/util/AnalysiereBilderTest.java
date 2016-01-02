package de.mohadipe.ui.test.robot.util;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.image.BufferedImage;

import org.junit.Test;

public class AnalysiereBilderTest {

	@Test
	public void ladeBildPrintRGB() throws AWTException {
		BufferedImage muenze01 = new BilderLaden(null).ladeBildByName("Muenze05.bmp");
//		BufferedImage muenze01 = new BilderLaden(null).ladeSchlafen02();
		int width = muenze01.getWidth();
		int height = muenze01.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int clr = muenze01.getRGB(i, j);
				Color color = new Color(clr, true);
				System.out.println("W: " + i + "; H: " + j + "; RGB: " + color.toString());
			}
		}
	}
	
}
