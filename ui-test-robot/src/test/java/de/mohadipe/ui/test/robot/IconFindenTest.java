package de.mohadipe.ui.test.robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;
import de.mohadipe.ui.test.robot.util.BilderLaden;

public class IconFindenTest {

	@Test
	public void findeVerknuepfungMitRotemIcon() throws IOException, IconNotFoundException {
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 1417;
		expected.y = 232;
		BufferedImage screenShot = new BilderLaden(null).ladeScreenShotFoEVerknuepfung();
		IconFinden iconFinden = new IconFinden();
		GrafikDateiPfadeService grafikDateiPfadeService = new GrafikDateiPfadeService(true);
		String iconName = grafikDateiPfadeService.getPath() + IconFinden.KLICK_01;
		iconFinden.findeIcon(iconName, screenShot);
		Assert.assertThat(iconFinden.getKoordinaten(), Matchers.equalTo(expected));
	}
	
	@Test
	public void findeMuenzeMitStern() throws IOException, IconNotFoundException {
		GrafikDateiPfadeService grafikDateiPfadeService = new GrafikDateiPfadeService(true);
		String iconName = grafikDateiPfadeService.getPath() + IconFinden.WIEDERHOLEN_04;
		String screenShotName = grafikDateiPfadeService.getPath() + "MuenzeMitSternAbholbereit.bmp";
		BufferedImage screenShot = ImageIO.read(new File(screenShotName));
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIcon(iconName, screenShot);
		Koordinaten2D actual = iconFinden.getKoordinaten();
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 1092;
		expected.y = 601;
		Assert.assertThat(actual, Matchers.equalTo(expected));
	}
	
	@Test(expected=RuntimeException.class)
	public void grafikNichtVorhanden() throws IOException, IconNotFoundException {
		GrafikDateiPfadeService grafikDateiPfadeService = new GrafikDateiPfadeService(true);
		String screenShotName = grafikDateiPfadeService.getPath() + "DesktopScreenshot.bmp";
		BufferedImage screenShot = ImageIO.read(new File(screenShotName));
		String iconName = "dieseGrafikGibtEsNicht.bmp";

		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIcon(iconName, screenShot);
	}
	
	@Test(expected=IconNotFoundException.class)
	public void iconNichtAufScreenGefunden() throws IOException, IconNotFoundException {
		GrafikDateiPfadeService grafikDateiPfadeService = new GrafikDateiPfadeService(true);
		String screenShotName = grafikDateiPfadeService.getPath() + "DesktopScreenshot.bmp";
		BufferedImage screenShot = ImageIO.read(new File(screenShotName));
		IconFinden iconFinden = new IconFinden();
		String iconName = grafikDateiPfadeService.getPath() + IconFinden.KLICK_02;
		iconFinden.findeIcon(iconName, screenShot);
	}
	
	@Test
	public void findeSpielenButton() throws IconNotFoundException {
		BufferedImage ladeStartseiteFoE = new BilderLaden(null).ladeStartseiteFoE();
		BufferedImage ladeSpielenButton = new BilderLaden(null).ladeSpielenButton();
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIcon(ladeSpielenButton, ladeStartseiteFoE);
		Koordinaten2D koordinaten = iconFinden.getKoordinaten();
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 1181;
		expected.y = 510;
		Assert.assertEquals(expected, koordinaten);
	}
}
