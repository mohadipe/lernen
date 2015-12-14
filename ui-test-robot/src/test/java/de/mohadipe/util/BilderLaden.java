package de.mohadipe.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;
public class BilderLaden {

	private GrafikDateiPfadeService pfadeService = new GrafikDateiPfadeService(true);
	
	public BufferedImage ladeScreenShotFoEVerknuepfung() {
		String screenShotName = pfadeService.getPath() + "DesktopScreenshot.bmp";
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
		String screenShotName = pfadeService.getPath() +  "StartseiteFoE.bmp";
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
		String screenShotName = pfadeService.getPath() + "AuswahlServer.bmp";
		BufferedImage screenShot;
		try {
			screenShot = ImageIO.read(new File(screenShotName));
			return screenShot;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BufferedImage ladeScreenshotMuenzenAbholbereit() {
		return ladeBild("MuenzeAbholbereit.bmp");
	}
	
	public BufferedImage ladeScreenshotMuenzenMitSternAbholbereit() {
		return ladeBild("MuenzeMitSternAbholbereit.bmp");
	}
	
	private BufferedImage ladeBild(String screenShotName) {
		String filePath = pfadeService.getPath() + screenShotName;
		BufferedImage screenShot;
		try {
			screenShot = ImageIO.read(new File(filePath));
			return screenShot;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BufferedImage ladeScreenshotWerkzeugeAbholbereit() {
		return ladeBild("WerkzeugeSchmiedeAbholbereit.bmp");
	}

	public BufferedImage ladeScreenshotWerkzeugeProduzierbar() {
		return ladeBild("WerkzeugeSchmiedeProduzierbar.bmp");
	}

	public BufferedImage ladeScreenshotSchmiedeProduktion() {
		return ladeBild("SchmiedeProduktion.bmp");
	}

}