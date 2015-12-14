package de.mohadipe.ui.test.robot;

import java.awt.image.BufferedImage;

import org.junit.Test;

import de.mohadipe.util.BilderLaden;

public class ScreenGetRGBTest {

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void rgbVonKoordinatenAusserhalbDesScreens(){
		BufferedImage screen = new BilderLaden().ladeScreenShotFoEVerknuepfung();
		screen.getRGB(2000, 2000);
	}
}
