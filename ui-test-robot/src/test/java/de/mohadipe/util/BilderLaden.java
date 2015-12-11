package de.mohadipe.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class BilderLaden {

	private String ausgangspunkt;
	private static final String ide_test_icon_pfad = "\\src\\test\\resources\\icons\\";
	
	public BufferedImage ladeScreenShotFoEVerknuepfung() {
		ausgangspunkt = Paths.get("").toAbsolutePath().toString();
		String screenShotName = ausgangspunkt + Paths.get("").resolve(ide_test_icon_pfad).resolve("DesktopScreenshot.bmp").toString();
		BufferedImage screenShot;
		try {
			screenShot = ImageIO.read(new File(screenShotName));
			return screenShot;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public BufferedImage ladeStartseiteFoE() {
		ausgangspunkt = Paths.get("").toAbsolutePath().toString();
		String screenShotName = ausgangspunkt + Paths.get("").resolve(ide_test_icon_pfad).resolve("StartseiteFoE.bmp").toString();
		BufferedImage screenShot;
		try {
			screenShot = ImageIO.read(new File(screenShotName));
			return screenShot;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BufferedImage waehleServerRugnir() {
		ausgangspunkt = Paths.get("").toAbsolutePath().toString();
		String screenShotName = ausgangspunkt + Paths.get("").resolve(ide_test_icon_pfad).resolve("AuswahlServer.bmp").toString();
		BufferedImage screenShot;
		try {
			screenShot = ImageIO.read(new File(screenShotName));
			return screenShot;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
