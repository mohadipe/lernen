package de.mohadipe.ui.test.robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

import de.mohadipe.ui.test.robot.aufgabe.Block;

public class IconFinden {
	public final static String KLICK_01 = "klick01.bmp";
	public final static String KLICK_02 = "klick02.bmp";
	public final static String KLICK_03 = "klick03.bmp";
	public final static String KLICK_04 = "klick04.bmp";
	public final static String WIEDERHOLEN = "wiederholen.bmp";
	public final static String WIEDERHOLEN_02 = "wiederholen02.bmp";
	public final static String WIEDERHOLEN_03 = "wiederholen03.bmp";
	public final static String WIEDERHOLEN_04 = "wiederholen04.bmp";
	public final static String WIEDERHOLEN_05 = "wiederholen05.bmp";
	public final static String X = "x.bmp";
	public final static String BROWSER_X = "browser_x.bmp";
	public static final String PRODUZIEREN_01 = "produzieren01.bmp";
	public static final String GUTES_HUFEISEN = "gutesHufeisen.bmp";

	private Koordinaten2D koordinaten = null;

	public void findeIcon(final String fileName,
			final BufferedImage currentScreen) throws IconNotFoundException {
		System.out.println("Read: " + fileName);
		BufferedImage icon;
		try {
			icon = ImageIO.read(new File(fileName));
			findeIcon(icon, currentScreen);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private int indexSaveRGB(BufferedImage image, int x2, int y2)
			throws UngueltigeScreenKoordinate {
		ScreenPlausibilisierung screenPlausibilisierung = new ScreenPlausibilisierung(
				image);
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		koordinaten2d.x = x2;
		koordinaten2d.y = y2;
		if (screenPlausibilisierung.isKoordinateAufScreen(koordinaten2d)) {
			return image.getRGB(x2, y2);
		}
		throw new UngueltigeScreenKoordinate(image, x2, y2);
	}

	public Koordinaten2D getKoordinaten() {
		return koordinaten;
	}

	public void findeFarbeAufScreen(BufferedImage currentScreen) throws IconNotFoundException {
		int rgb = -10083840;
		int anzahlUebereinstimmenderFelder = 1;
		Block block = new Block(anzahlUebereinstimmenderFelder);
		List<Block> treffer = new ArrayList<Block>();
		for (int x = 0; x < currentScreen.getWidth(); x++) {
			for (int y = 0; y < currentScreen.getHeight(); y++) {
				int cSRGB = currentScreen.getRGB(x, y);
				if (cSRGB == rgb) {
					try {
						block.addKoordinate(x, y);
					} catch (GehoertNichtZuBlockException e) {
						block = new Block(anzahlUebereinstimmenderFelder);
					}
				}
				if (block.isBlockVoll()) {
					treffer.add(block);
					block = new Block(anzahlUebereinstimmenderFelder);
				}
			}
		}
		if (treffer.isEmpty()) {
			throw new IconNotFoundException(null, currentScreen);
		}
		try {
			List<Block> kombinationen = kombiniereBloecke(treffer);
			kombinationen.sort(new Comparator<Block>() {

				@Override
				public int compare(Block o1, Block o2) {
					if (o1.getAnzahlKoordinaten() < o2.getAnzahlKoordinaten()) {
						return 1;
					}
					if (o1.getAnzahlKoordinaten() > o2.getAnzahlKoordinaten()) {
						return -1;
					}
					return 0;
				}
			});
			Block block2 = kombinationen.get(0);
			this.koordinaten = block2.getKoordinaten();
		} catch (GehoertNichtZuBlockException e) {
			e.printStackTrace();
			throw new IconNotFoundException(null, currentScreen);
		}
	}
	
	private List<Block> kombiniereBloecke(List<Block> einzelneBloecke) throws GehoertNichtZuBlockException {
		List<Block> kombinationen = new ArrayList<Block>();
		for (Block block : einzelneBloecke) {
			Block tmp = new Block(block.getKantenLaenge());
			tmp.addBlock(block);
			for (Block block2 : einzelneBloecke) {
				try {
					tmp.addBlock(block2);
				} catch (GehoertNichtZuBlockException e) {
					continue;
				}
			}
			if (!kombinationen.contains(tmp)) {
				kombinationen.add(tmp);
			}
		}
		return kombinationen;
	}
	
	public void findeIcon(BufferedImage icon, BufferedImage currentScreen) throws IconNotFoundException {
		int anzahlPixelIcon = icon.getWidth() * icon.getHeight();
		for (int x = 0; x < currentScreen.getWidth(); x++) {
			for (int y = 0; y < currentScreen.getHeight(); y++) {
				boolean matches = true;
				int uebereinstimmendePixel = 0;
				for (int x2 = 0; x2 < icon.getWidth() && matches; x2++) {
					for (int y2 = 0; y2 < icon.getHeight() && matches; y2++) {
						try {
							int rgbIcon = indexSaveRGB(icon, x2, y2);
							int rgbScreen = indexSaveRGB(currentScreen, x + x2,
									y + y2);
							if (rgbIcon != rgbScreen) {
								matches = false;
							} else {
								// Ein Pixel vom Icon und vom Screen stimmen
								// ueberein.
								uebereinstimmendePixel++;
							}
						} catch (UngueltigeScreenKoordinate e) {
							System.out.println(e);
						}
					}
				}
				if (matches && anzahlPixelIcon == uebereinstimmendePixel) {
					koordinaten = new Koordinaten2D();
					System.out.println("Icon X: " + x);
					koordinaten.x = x;
					System.out.println("Icon Y: " + y);
					koordinaten.y = y;
					return;
				}
			}
		}
		System.out.println("Icon-Width: " + icon.getWidth() + " Icon-Height: "
				+ icon.getHeight());
		System.out.println("Screen-Width: " + currentScreen.getWidth()
				+ " Screen-Height: " + currentScreen.getHeight());
		throw new IconNotFoundException(icon, currentScreen);
	}
}
