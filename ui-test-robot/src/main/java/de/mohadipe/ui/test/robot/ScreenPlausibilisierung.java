package de.mohadipe.ui.test.robot;

import java.awt.image.BufferedImage;

public class ScreenPlausibilisierung {
	private final BufferedImage screen;

	public ScreenPlausibilisierung(final BufferedImage screen) {
		this.screen = screen;
	}
	
	public boolean isKoordinateAufScreen(Koordinaten2D koordinaten2d) {
		if (koordinaten2d.x < 0 || 
				screen.getWidth() < koordinaten2d.x) {
			return false;
		}
		if (koordinaten2d.y < 0 || 
				screen.getHeight() < koordinaten2d.y) {
			return false;
		}
		return true;
	}
	
}
