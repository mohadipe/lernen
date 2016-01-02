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
	public static final String WIEDERHOLEN_07 = "wiederholen07.bmp";
	private static final String WIEDERHOLEN_08 = "wiederholen08.bmp";
	private static final String WIEDERHOLEN_09 = "wiederholen09.bmp";
	private static final String WIEDERHOLEN_10 = "wiederholen10.bmp";;
	public final static String X = "x.bmp";
	public final static String BROWSER_X = "browser_x.bmp";
	public static final String PRODUZIEREN_01 = "produzieren01.bmp";
	public static final String GUTES_HUFEISEN = "gutesHufeisen.bmp";
	private static final String HERAUSGEZOOMT = "screenVielAbholbereit.bmp";
	private static final String HERAUSGEZOOMT_02 = "screenVielAbholbereit02.bmp";
	private static final String EIN_PIXEL = "EinPixel.bmp";
	private static final String X2 = "x2.bmp";
	private static final String SCHLAFEN = "schlafen.bmp";
	
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

	public BufferedImage ladeWerkzeugVergleich05() {
		return ladeBild(WIEDERHOLEN_05);
	}

	public BufferedImage ladeMuenzVergleich06() {
		return ladeBild(WIEDERHOLEN_06);
	}

	public BufferedImage ladeMuenzVergleich07() {
		return ladeBild(WIEDERHOLEN_07);
	}

	public BufferedImage screenHerausgezoomt() {
		return ladeBild(HERAUSGEZOOMT);
	}

	public BufferedImage ladeMuenzVergleich08() {
		return ladeBild(WIEDERHOLEN_08);
	}

	public BufferedImage ladeWerkzeugVergleich09() {
		return ladeBild(WIEDERHOLEN_09);
	}

	public BufferedImage ladeMuenzVergleich10() {
		return ladeBild(WIEDERHOLEN_10);
	}

	public BufferedImage screenHerausgezoomt02() {
		return ladeBild(HERAUSGEZOOMT_02);
	}

	public BufferedImage ladeEinPixel() {
		return ladeBild(EIN_PIXEL);
	}

	public BufferedImage ladeBrowserClose() {
		return ladeBild(BROWSER_X);
	}

	public BufferedImage ladePopUpClose() {
		return ladeBild(X2);
	}

	public BufferedImage ladeSchlafen() {
		return ladeBild(SCHLAFEN);
	}

	public BufferedImage ladeWerkzeug() {
		return ladeBild("werkzeug.bmp");
	}

	public BufferedImage ladePopupX() {
		return ladeBild("popup_x.bmp");
	}

	public BufferedImage ladeMuenze02() {
		return ladeBild("Muenze02.bmp");
	}

	public BufferedImage ladeMuenze03() {
		return ladeBild("Muenze03.bmp");
	}

	public BufferedImage ladeMuenze04() {
		return ladeBild("Muenze04.bmp");
	}

	public BufferedImage ladeWerkzeug01() {
		return ladeBild("Werkzeug01.bmp");
	}

	public BufferedImage ladeSchlafen01() {
		return ladeBild("Schlafen01.bmp");
	}

	public BufferedImage ladePopup01() {
		return ladeBild("Popup01.bmp");
	}

	public BufferedImage ladeMuenze01() {
		return ladeBild("Muenze01.bmp");
	}

	public BufferedImage ladeWerkzeug02() {
		return ladeBild("Werkzeug02.bmp");
	}

	public BufferedImage ladeSchlafen02() {
		return ladeBild("Schlafen02.bmp");
	}

	public BufferedImage ladeWerkzeug03() {
		return ladeBild("Werkzeug03.bmp");
	}

	public BufferedImage ladeBildByName(String string) {
		return ladeBild(string);
	}

	public BufferedImage ladeX3() {
		return ladeBild("x3.bmp");
	}
}