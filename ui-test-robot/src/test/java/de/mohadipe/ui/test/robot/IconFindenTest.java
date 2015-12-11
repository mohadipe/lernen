package de.mohadipe.ui.test.robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IconFindenTest {
	private String ausgangspunkt;
	private static final String ide_test_icon_pfad = "\\src\\test\\resources\\icons\\";
	private static final String ide_main_icon_pfad = "\\src\\main\\resources\\icons\\";

	@Before
	public void setup() {
		ausgangspunkt = Paths.get("").toAbsolutePath().toString();
	}
	
	@Test
	public void findeVerknuepfungMitRotemIcon() throws IOException, IconNotFoundException {
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 1417;
		expected.y = 232;
		String screenShotName = ausgangspunkt + Paths.get("").resolve(ide_test_icon_pfad).resolve("DesktopScreenshot.bmp").toString();
		BufferedImage screenShot = ImageIO.read(new File(screenShotName));
		IconFinden iconFinden = new IconFinden();
		String iconName = ausgangspunkt + Paths.get("").resolve(ide_main_icon_pfad).resolve(IconFinden.KLICK_01).toString();
		iconFinden.findeIcon(iconName, screenShot);
		Assert.assertThat(iconFinden.getKoordinaten(), Matchers.equalTo(expected));
	}
	
	@Test(expected=RuntimeException.class)
	public void grafikNichtVorhanden() throws IOException, IconNotFoundException {
		String screenShotName = ausgangspunkt + Paths.get("").resolve(ide_test_icon_pfad).resolve("DesktopScreenshot.bmp").toString();
		BufferedImage screenShot = ImageIO.read(new File(screenShotName));
		String iconName = "dieseGrafikGibtEsNicht.bmp";

		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIcon(iconName, screenShot);
	}
	
	@Test(expected=IconNotFoundException.class)
	public void iconNichtAufScreenGefunden() throws IOException, IconNotFoundException {
		String screenShotName = ausgangspunkt + Paths.get("").resolve(ide_test_icon_pfad).resolve("DesktopScreenshot.bmp").toString();
		BufferedImage screenShot = ImageIO.read(new File(screenShotName));
		IconFinden iconFinden = new IconFinden();
		String iconName = ausgangspunkt + Paths.get("").resolve(ide_main_icon_pfad).resolve(IconFinden.KLICK_02).toString();
		iconFinden.findeIcon(iconName, screenShot);
		
	}
}
