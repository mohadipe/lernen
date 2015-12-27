package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.image.BufferedImage;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.Koordinaten2D;
import de.mohadipe.ui.test.robot.util.BilderLaden;

public class FindeGrafikInGrafikTest {

	@Test
	public void findeVerknuepfungRotesIcon() {
		BufferedImage zuDurchsuchende = new BilderLaden(null).ladeScreenShotFoEVerknuepfung();
		BufferedImage zuFindende = new BilderLaden(null).ladeRotesIcon();
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, zuDurchsuchende);
		findeGrafikInGrafik.ausfuehren();
		
		Koordinaten2D koordinaten = (Koordinaten2D) findeGrafikInGrafik.getDaten(AufgabeDaten.KOORDINATE);
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 1417; expected.y = 232;
		Assert.assertEquals(expected, koordinaten);
	}
	
	@Test
	public void findeMuenzeHerausgezoomt() {
		BufferedImage zuFindende = new BilderLaden(null).ladeMuenzVergleich10();
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(zuFindende);
		BufferedImage zuDurchsuchende = new BilderLaden(null).screenHerausgezoomt02();
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, zuDurchsuchende);
	
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertTrue(findeGrafikInGrafik.isErfolgreich());
	}
	
	@Test
	public void findeWerkzeugeHerausgezoomt() {
		BufferedImage zuFindende = new BilderLaden(null).ladeWerkzeugVergleich09();
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, new BilderLaden(null).screenHerausgezoomt());
	
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertTrue(findeGrafikInGrafik.isErfolgreich());		
	}
}
