package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.image.BufferedImage;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.util.BilderLaden;

public class FindeEineVonMehrerenGrafikenTest {

	private BilderLaden bilder = new BilderLaden(null);
	@Test
	public void vierMuenzVariantenEinTreffer() {
		BufferedImage screenMuenzeStern = bilder.ladeScreenshotMuenzenMitSternAbholbereit();
		BufferedImage muenzVariante01 = bilder.ladeMuenzVergleich01();
		FindeGrafikInGrafik findeMuenze01 = new FindeGrafikInGrafik(muenzVariante01);
		findeMuenze01.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, screenMuenzeStern);
		BufferedImage muenzVariante02 = bilder.ladeMuenzVergleich02();
		FindeGrafikInGrafik findeMuenze02 = new FindeGrafikInGrafik(muenzVariante02);
		findeMuenze02.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, screenMuenzeStern);
		BufferedImage muenzVariante03 = bilder.ladeMuenzVergleich03();
		FindeGrafikInGrafik findeMuenze03 = new FindeGrafikInGrafik(muenzVariante03);
		findeMuenze03.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, screenMuenzeStern);
		BufferedImage muenzVariante04 = bilder.ladeMuenzVergleich04();
		FindeGrafikInGrafik findeMuenze04 = new FindeGrafikInGrafik(muenzVariante04);
		findeMuenze04.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, screenMuenzeStern);
		
		findeMuenze01.ausfuehren();
		findeMuenze02.ausfuehren();
		findeMuenze03.ausfuehren();
		findeMuenze04.ausfuehren();
		
		Assert.assertFalse(findeMuenze01.isErfolgreich());
		Assert.assertFalse(findeMuenze02.isErfolgreich());
		Assert.assertFalse(findeMuenze03.isErfolgreich());
		Assert.assertTrue(findeMuenze04.isErfolgreich());
	}
}
