package de.mohadipe.ui.test.robot.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;

public class BilderLaden {

	public final static String KLICK_01 = "klick01.bmp";
	public final static String KLICK_02 = "klick02.bmp";
	public final static String KLICK_03 = "klick03.bmp";
	public final static String KLICK_04 = "klick04.bmp";
	public final static String WIEDERHOLEN = "wiederholen.bmp";
	public final static String WIEDERHOLEN_02 = "wiederholen02.bmp";
	public final static String WIEDERHOLEN_03 = "wiederholen03.bmp";
	public final static String WIEDERHOLEN_04 = "wiederholen04.bmp";
	public final static String WIEDERHOLEN_05 = "wiederholen05.bmp";
	public final static String WIEDERHOLEN_06 = "wiederholen06.bmp";
	public final static String X = "x.bmp";
	public final static String BROWSER_X = "browser_x.bmp";
	public static final String PRODUZIEREN_01 = "produzieren01.bmp";
	public static final String GUTES_HUFEISEN = "gutesHufeisen.bmp";
	
	private GrafikDateiPfadeService pfadeService = new GrafikDateiPfadeService(
			true);

	public BilderLaden(GrafikDateiPfadeService service) {
		if (service == null) {
			pfadeService = new GrafikDateiPfadeService(
					true);
		} else {
			pfadeService = service;
		}
	}

	public BufferedImage ladeScreenShotFoEVerknuepfung() {
		String screenShotName = pfadeService.getPath()
				+ "DesktopScreenshot.bmp";
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
		String screenShotName = pfadeService.getPath() + "StartseiteFoE.bmp";
		BufferedImage screenShot;
		try {
			screenShot = ImageIO.read(new File(screenShotName));
			return screenShot;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BufferedImage ladeAuswahlServerRugnir() {
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

	public BufferedImage ladeRotesIcon() {
		return ladeBild(KLICK_01);
	}

	public BufferedImage ladeSpielenButton() {
		return ladeBild(KLICK_02);
	}

	public BufferedImage ladeServerRugnirButton() {
		return ladeBild(KLICK_03);
	}

	public BufferedImage ladeOkButton() {
		return ladeBild(KLICK_04);
	}

	public BufferedImage ladeMuenzVergleich01() {
		return ladeBild(WIEDERHOLEN);
	}

	public BufferedImage ladeMuenzVergleich02() {
		return ladeBild(WIEDERHOLEN_02);
	}

	public BufferedImage ladeMuenzVergleich03() {
		return ladeBild(WIEDERHOLEN_03);
	}

	public BufferedImage ladeMuenzVergleich04() {
		return ladeBild(WIEDERHOLEN_04);
	}

	public BufferedImage ladeMuenzVergleich05() {
		return ladeBild(WIEDERHOLEN_05);
	}

	public BufferedImage ladeMuenzVergleich06() {
		return ladeBild(WIEDERHOLEN_06);
	}
}