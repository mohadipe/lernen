package de.mohadipe.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;

public class BilderLaden {

	private String ausgangspunkt;
	private static final String ide_test_icon_pfad = "\\src\\test\\resources\\icons\\";
	private GrafikDateiPfadeService pfadeService = new GrafikDateiPfadeService(true);
	
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

}
