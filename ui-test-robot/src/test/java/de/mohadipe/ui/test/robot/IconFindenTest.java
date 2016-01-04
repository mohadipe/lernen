package de.mohadipe.ui.test.robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;
import de.mohadipe.ui.test.robot.util.BilderLaden;
import de.mohadipe.ui.test.robot.util.ImageToFarbBlockUtil;

public class IconFindenTest {

	private BilderLaden bilder = new BilderLaden(null);
	private int muenzTolleranz = 50;
	private int werkzeugTolleranz = 159;
	private int schlafTolleranz = 21;
	private int popupTolleranz = 0;
	
	
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
	
	@Test
	public void findeMuenzeAnhandFarbe() throws IconNotFoundException {
		IconFinden iconFinden = new IconFinden();
		BufferedImage currentScreen = bilder.ladeScreenshotMuenzenAbholbereit();
		BufferedImage muenzRefImage = bilder.ladeBildByName("MuenzReferenz.bmp");
		FarbBlock muenzFarbBlock = ImageToFarbBlockUtil.erstelleFarbBlock(muenzRefImage);
		iconFinden.findeFarbBlockAufScreen(currentScreen, muenzFarbBlock, muenzTolleranz);
		Koordinaten2D koordinaten = iconFinden.getKoordinaten();
		Assert.assertNotNull(koordinaten);
		Koordinaten2D expected = new Koordinaten2D();
		expected.x=236;
		expected.y=564;
		Assert.assertEquals(expected, koordinaten);
	}
	
	@Test
	public void findeMuenzTeilInMuenzImage() throws IconNotFoundException {
		BufferedImage screen = new BilderLaden(null).ladeBildByName("Muenze15.bmp");
		BufferedImage image = new BilderLaden(null).ladeBildByName("MuenzReferenz.bmp");
		
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIconByColorAehnlichkeit(image, screen, muenzTolleranz);
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 17;
		expected.y = 13;
		Assert.assertEquals(expected, iconFinden.getKoordinaten());
	}
	
	@Test
	public void findeWerkzeugTeilInWerkzeugImage() throws IconNotFoundException {
		BufferedImage screen = new BilderLaden(null).ladeBildByName("Werkzeug07.bmp");
		BufferedImage image = new BilderLaden(null).ladeBildByName("WerkzeugReferenz.bmp");
		
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIconByColorAehnlichkeit(image, screen, 100);
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 10;
		expected.y = 10;
		Assert.assertEquals(expected, iconFinden.getKoordinaten());
	}
	
	@Test
	public void findeWerkzeugTeilInScreenImage() throws IconNotFoundException {
		BufferedImage screen = new BilderLaden(null).ladeScreenshotWerkzeugeAbholbereit();
		BufferedImage image = new BilderLaden(null).ladeBildByName("WerkzeugReferenz.bmp");
		
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIconByColorAehnlichkeit(image, screen, werkzeugTolleranz);
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 10;
		expected.y = 10;
		Assert.assertEquals(expected, iconFinden.getKoordinaten());
	}
	
	@Test
	public void findeSchlafenTeilInSchlafenImage() throws IconNotFoundException {
		BufferedImage screen = new BilderLaden(null).ladeBildByName("Schlafen01.bmp");
		BufferedImage image = new BilderLaden(null).ladeBildByName("SchlafenReferenz.bmp");
		
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIconByColorAehnlichkeit(image, screen, 30);
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 9;
		expected.y = 19;
		Assert.assertEquals(expected, iconFinden.getKoordinaten());
	}
	
	@Test
	public void findePopupTeilInPopupImage() throws IconNotFoundException {
		BufferedImage screen = new BilderLaden(null).ladeBildByName("x.bmp");
		BufferedImage image = new BilderLaden(null).ladeBildByName("PopupReferenz.bmp");
		
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIconByColorAehnlichkeit(image, screen, popupTolleranz);
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 6;
		expected.y = 5;
		Assert.assertEquals(expected, iconFinden.getKoordinaten());
	}
	
	@Test(expected = IconNotFoundException.class)
	public void findeKeineMuenzen() throws IconNotFoundException {
		BufferedImage screen = new BilderLaden(null).ladeBildByName("LeereStadt.bmp");
		BufferedImage image = new BilderLaden(null).ladeBildByName("MuenzReferenz.bmp");
		
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIconByColorAehnlichkeit(image, screen, muenzTolleranz);
	}
	
	@Test(expected = IconNotFoundException.class)
	public void findeKeineWerkzeuge() throws IconNotFoundException {
		BufferedImage screen = new BilderLaden(null).ladeBildByName("LeereStadt.bmp");
		BufferedImage image = new BilderLaden(null).ladeBildByName("WerkzeugReferenz.bmp");
		
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIconByColorAehnlichkeit(image, screen, werkzeugTolleranz);
	}
	
	@Test(expected = IconNotFoundException.class)
	public void findeKeinSchlafen() throws IconNotFoundException {
		BufferedImage screen = new BilderLaden(null).ladeBildByName("LeereStadt.bmp");
		BufferedImage image = new BilderLaden(null).ladeBildByName("SchlafenReferenz.bmp");
		
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIconByColorAehnlichkeit(image, screen, schlafTolleranz);
	}
	
	@Test(expected = IconNotFoundException.class)
	public void findeKeinPopup() throws IconNotFoundException {
		BufferedImage screen = new BilderLaden(null).ladeBildByName("LeereStadt.bmp");
		BufferedImage image = new BilderLaden(null).ladeBildByName("PopupReferenz.bmp");
		
		IconFinden iconFinden = new IconFinden();
		iconFinden.findeIconByColorAehnlichkeit(image, screen, popupTolleranz);
	}
	
	@Test
	public void findeAlleMuenzenAufScreen() {
		List<Koordinaten2D> erwarteteKoordinaten = new ArrayList<Koordinaten2D>();
		erwarteteKoordinaten.add(new Koordinaten2D(935, 548));
		erwarteteKoordinaten.add(new Koordinaten2D(997, 517));
		erwarteteKoordinaten.add(new Koordinaten2D(997, 579));
		erwarteteKoordinaten.add(new Koordinaten2D(1056, 546));
		erwarteteKoordinaten.add(new Koordinaten2D(1059, 610));
		erwarteteKoordinaten.add(new Koordinaten2D(1122, 579));
		erwarteteKoordinaten.add(new Koordinaten2D(1122, 637));
		erwarteteKoordinaten.add(new Koordinaten2D(1184, 610));
		IconFinden iconFinden = new IconFinden();
		
		BufferedImage screen = bilder.ladeBildByName("WerkzeugeMuenzenAbholen.bmp");
		BufferedImage muenzReferenz = bilder.ladeBildByName("MuenzReferenz.bmp");
		FarbBlock muenzFarbBlock = ImageToFarbBlockUtil.erstelleFarbBlock(muenzReferenz);
		List<Koordinaten2D> findeAlleMuenzen = iconFinden.findeAlleFarbBloecke(screen, muenzFarbBlock, muenzTolleranz);
		
		Assert.assertTrue("Weniger als 15 Treffer.", 15 < findeAlleMuenzen.size());
		for (Koordinaten2D koordinaten2d : erwarteteKoordinaten) {
			Assert.assertTrue(findeAlleMuenzen.contains(koordinaten2d));
		}
	}
	
	@Test
	public void findeAlleWerkzeugeAufScreen() {
		IconFinden iconFinden = new IconFinden();
		
		BufferedImage screen = new BilderLaden(null).ladeBildByName("WerkzeugeMuenzenAbholen.bmp");
		BufferedImage werkzeugReferenz = bilder.ladeBildByName("WerkzeugReferenz.bmp");
		FarbBlock werkzeugFarbBlock = ImageToFarbBlockUtil.erstelleFarbBlock(werkzeugReferenz);

		List<Koordinaten2D> findeAlleWerkzeuge = iconFinden.findeAlleFarbBloecke(screen, werkzeugFarbBlock, werkzeugTolleranz);
		Assert.assertEquals(7, findeAlleWerkzeuge.size());
	}
}
