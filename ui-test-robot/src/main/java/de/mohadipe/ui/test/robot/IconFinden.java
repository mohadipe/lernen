package de.mohadipe.ui.test.robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IconFinden {
	public final static String KLICK_01 = "klick01.bmp";
	public final static String KLICK_02 = "klick02.bmp";
	public final static String KLICK_03 = "klick03.bmp";
	public final static String KLICK_04 = "klick04.bmp";
	public final static String WIEDERHOLEN = "wiederholen.bmp";
	public final static String WIEDERHOLEN_02 = "wiederholen02.bmp";
	public final static String WIEDERHOLEN_03 = "wiederholen03.bmp";
	public final static String WIEDERHOLEN_04 = "wiederholen04.bmp";
	public final static String X = "x.bmp";
	public final static String BROWSER_X = "browser_x.bmp";

	private Koordinaten2D koordinaten = null;
	private int xKoordinate = 0;
	private int yKoordinate = 0;

	public void findeIcon(final String fileName, final BufferedImage currentScreen) throws IconNotFoundException {
		try {
			BufferedImage icon = ImageIO.read(new File(fileName));
			int anzahlPixelIcon = icon.getWidth() * icon.getHeight();
			for (int x = 0; x < currentScreen.getWidth(); x++) {
				for (int y = 0; y < currentScreen.getHeight(); y++) {
					boolean matches = true;
					int uebereinstimmendePixel = 0;
					for (int x2 = 0; x2 < icon.getWidth() && matches; x2++) {
						for (int y2 = 0; y2 < icon.getHeight() && matches; y2++) {
							if (icon.getRGB(x2, y2) != currentScreen.getRGB(x
									+ x2, y + y2)) {
								matches = false;
							} else {
								// Ein Pixel vom Icon und vom Screen stimmen ueberein.
								uebereinstimmendePixel++;
							}
						}
					}
					if (matches && anzahlPixelIcon == uebereinstimmendePixel) {
						koordinaten = new Koordinaten2D();
						System.out.println("Icon X: " + x);
						koordinaten.x = x;
						xKoordinate = x;
						System.out.println("Icon Y: " + y);
						koordinaten.y = y;
						yKoordinate = y;
						return;
					}
				}
			}
			System.out.println("Icon-Width: " + icon.getWidth() + " Icon-Height: " + icon.getHeight());
		} catch (IOException e) {
			throw new RuntimeException("Datei nicht gefunden.", e);
		}
		System.out.println("Screen-Width: " + currentScreen.getWidth() + " Screen-Height: " + currentScreen.getHeight());
		throw new IconNotFoundException(fileName, currentScreen);
	}

	public int getxKoordinate() {
		return xKoordinate;
	}

	public int getyKoordinate() {
		return yKoordinate;
	}
	
	public Koordinaten2D getKoordinaten() {
		return koordinaten;
	}
}
